package starlib.solver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import starlib.GlobalVariables;
import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Formula;
import starlib.formula.PureFormula;
import starlib.formula.Utilities;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredMap;

public class Solver {
	
	private static String s2sat = "s2sat";

	private static boolean ret = false;
	
	private static boolean retEntail = false;
	
	private static StringBuilder model = new StringBuilder();
	
	private static Process p = null;
	
	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public static void terminate() {
		executor.shutdown();
		/*
		try {
		    if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
		    	executor.shutdownNow();
		    } 
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
		//*/
	}
	
	public static boolean checkEntail(Formula lhs, Formula rhs) {
		retEntail = false;
		
		File file = printToFile(lhs, rhs);
		
		if (file != null) {
			boolean ret = checkEntail(file);
			return ret;
		}
		
		return false;
	}
	
	private static File printToFile(Formula lhs, Formula rhs) {
		try {
			File file = File.createTempFile("entail", null);
//			System.out.println(file.getAbsolutePath());
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));

			DataNode[] dns = DataNodeMap.getAll();
			for (int i = 0; i < dns.length; i++) {
				String dn = dns[i].toS2SATString();
				bw.write(dn + "\n");
			}

			InductivePred[] preds = InductivePredMap.getAll();
			for (int i = 0; i < preds.length; i++) {
				String pred = preds[i].toS2SATString();
				bw.write(pred + "\n");
			}

			String problem = "checkentail " + lhs.toS2SATString() + " |- " + rhs.toS2SATString() +  ".\n";
			bw.write(problem);

			bw.flush();
			bw.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	private static boolean checkEntail(File file) {
		try {
			Future future = null;
			String cmd = s2sat + " " + file.getAbsolutePath();
			
			Runnable check = new Thread() {
				@Override
				public void run() {
					try {
						p = Runtime.getRuntime().exec(cmd);
						
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

						String s = br.readLine();
						
						while (s != null) {
							if (s.contains("Valid")) {
								retEntail = true;
								break;
							}
							
							if (s.contains("Fail")) {
								retEntail = false;
								break;
							}
							
							s = br.readLine();
						}
						
						br.close();
						p.waitFor();
					} catch (Exception e) {

					}
				}
			};
			
			if(executor.isShutdown()) {
				executor = Executors.newSingleThreadExecutor();
			}

			future = executor.submit(check);
			
			/*
			int maxTime = MAX_TIME;
			
			String s = c.getProperty("star.max_time");
			if (s != null) {
				maxTime = Integer.parseInt(s);
			}
			//*/
			
			future.get(GlobalVariables.MAX_TIME, TimeUnit.SECONDS);

			return retEntail;
		} catch (Exception e) {
			retEntail = false;
			if (p.isAlive()) p.destroyForcibly();
				
			return false;
		}
	}
	
	public static List<Formula> preprocess(Formula pre, Formula f) {
		List<Formula> fs = new ArrayList<Formula>();

		Set<Variable> vars = new HashSet<Variable>();
		CollectVarsVisitor visitor = new CollectVarsVisitor(vars);
		visitor.visit(f);

		if (vars.isEmpty()) {
			Formula res = pre.copy();

			PureFormula pf = f.getPureFormula();
			for (PureTerm pt : pf.getPureTerms()) {
				ComparisonTerm ct = (ComparisonTerm) pt;
				res.addComparisonTerm(ct.getComparator(), ct.getExp1(), ct.getExp2());
			}

			fs.add(res);
			return fs;
		} else {
			ArrayList<Variable> arr = new ArrayList<Variable>(vars);

			Collections.sort(arr, new Comparator<Variable>() {

				@Override
				public int compare(Variable var1, Variable var2) {
					int count1 = 0, count2 = 0;
					String name1 = var1.getName();
					String name2 = var2.getName();

					for (int i = 0; i < name1.length(); i++) {
						char c = name1.charAt(i);
						if (c == '.')
							count1++;
					}

					for (int i = 0; i < name2.length(); i++) {
						char c = name2.charAt(i);
						if (c == '.')
							count2++;
					}

					return count1 - count2;
				}

			});

			Variable var = arr.get(0);
			String name = var.getName();
			String rootName = name.substring(0, name.indexOf('.'));
			String fieldName = name.substring(name.indexOf('.') + 1, name.length());

			if (Utilities.isNull(pre, rootName)) {
				return fs;
			} else {
				HeapTerm ht = Utilities.findHeapTerm(pre, rootName);
				if (ht == null) {
					return fs;
				} else if (ht instanceof PointToTerm) {
					PointToTerm pt = (PointToTerm) ht;
					DataNode dn = DataNodeMap.find(pt.getType());
					Variable[] fields = dn.getFields();

					int i = 0;
					for (i = 0; i < fields.length; i++) {
						if (fields[i].getName().equals(fieldName))
							break;
					}
					
					if (i >= fields.length)
						return fs;

					Variable symF = pt.getVarsNoRoot()[i];

					Variable[] fromVars = new Variable[arr.size()];
					Variable[] toVars = new Variable[arr.size()];

					arr.toArray(fromVars);

					for (i = 0; i < arr.size(); i++) {
						Variable fromVar = fromVars[i];
						String prefix = name;

						if (fromVar.getName().startsWith(prefix)) {
							String toVarName = fromVar.getName().replace(prefix, symF.getName());
							toVars[i] = new Variable(toVarName, fromVar.getType());
						} else {
							toVars[i] = new Variable(fromVar.getName(), fromVar.getType());
						}
					}

					Formula newF = f.substitute(fromVars, toVars, null);

					fs.addAll(preprocess(pre, newF));

					return fs;
				} else {
					InductiveTerm it = (InductiveTerm) ht;
					for (int index = 0; index < it.unfold().length; index++) {
						Formula newPre = pre.copy();
						newPre.unfold(it, index);

						if (Utilities.isNull(newPre, rootName)) {
							continue;
						} else {
							PointToTerm pt = (PointToTerm) Utilities.findHeapTerm(newPre, rootName);
							DataNode dn = DataNodeMap.find(pt.getType());
							Variable[] fields = dn.getFields();

							int i = 0;
							for (i = 0; i < fields.length; i++) {
								if (fields[i].getName().equals(fieldName))
									break;
							}

							if (i >= fields.length)
								continue;
							
							Variable symF = pt.getVarsNoRoot()[i];

							Variable[] fromVars = new Variable[arr.size()];
							Variable[] toVars = new Variable[arr.size()];

							arr.toArray(fromVars);

							for (i = 0; i < arr.size(); i++) {
								Variable fromVar = fromVars[i];
								String prefix = name;

								if (fromVar.getName().startsWith(prefix)) {
									String toVarName = fromVar.getName().replace(prefix, symF.getName());
									toVars[i] = new Variable(toVarName, fromVar.getType());
								} else {
									toVars[i] = new Variable(fromVar.getName(), fromVar.getType());
								}
							}

							Formula newF = f.substitute(fromVars, toVars, null);

							fs.addAll(preprocess(newPre, newF));
						}

					}
					return fs;
				}
			}
		}
	}
	
	public static boolean checkSat(List<Formula> fs) {
		for (Formula f : fs) {
			if (checkSat(f)) return true;
		}
		
		return false;
	}
	
	public static boolean checkSat(Formula f) {
//		System.out.println(f);
//		System.out.println(f.getDepth());
		
		ret = false; model = new StringBuilder();

		if (f.getDepth() > GlobalVariables.MAX_DEPTH) {
			return false;
		} else {
			// return true;
			File file = printToFile(f);
			
			if (file != null) {
				boolean ret = checkSat(file);
				return ret;
			}
			
			return false;
		}
	}

	private static File printToFile(Formula f) {
		try {
			File file = File.createTempFile("sat", null);
//			System.out.println(file.getAbsolutePath());
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));

			DataNode[] dns = DataNodeMap.getAll();
			for (int i = 0; i < dns.length; i++) {
				String dn = dns[i].toS2SATString();
				bw.write(dn + "\n");
			}

			InductivePred[] preds = InductivePredMap.getAll();
			for (int i = 0; i < preds.length; i++) {
				String pred = preds[i].toS2SATString();
				bw.write(pred + "\n");
			}

			String problem = "checksat " + f.toS2SATString() + ".\n";
			bw.write(problem);

			bw.flush();
			bw.close();
			return file;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	private static boolean checkSat(File file) {
		try {
			Future future = null;
			String cmd = s2sat + " " + file.getAbsolutePath();
			
			Runnable check = new Thread() {
				@Override
				public void run() {
					try {
						p = Runtime.getRuntime().exec(cmd);
						
						BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

						String s = br.readLine();
						
						boolean readModel = false;
						while (s != null) {
							// System.out.println(s);

							if (s.contains("cex:")) {
								readModel = true;
							}

							if (s.contains(": SAT")) {
								ret = true;
								readModel = false;
								break;
							} else if (s.contains(": UNSAT")) {
								ret = false; model = new StringBuilder();
								readModel = false;
								break;
							}

							if (readModel) {
								if (s.contains("cex:"))
									model.append(s.substring(s.indexOf("cex:")));
								else if (s.contains("Pure Assigment"))
									model.append(";" + s);
								else if (!s.contains("true"))
									model.append(s);
							}

							s = br.readLine();
						}

						br.close();
						p.waitFor();
					} catch (Exception e) {

					}
				}
			};
			
			if(executor.isShutdown()) {
				executor = Executors.newSingleThreadExecutor();
			}

			future = executor.submit(check);
			
			/*
			int maxTime = MAX_TIME;
			
			String s = c.getProperty("star.max_time");
			if (s != null) {
				maxTime = Integer.parseInt(s);
			}
			//*/
			
			future.get(GlobalVariables.MAX_TIME, TimeUnit.SECONDS);

			return ret;
		} catch (Exception e) {
			ret = false; model = new StringBuilder();
			if (p.isAlive()) p.destroyForcibly();
				
			return false;
		}
	}

	public static String getModel() {
		return model.toString();
	}

}
