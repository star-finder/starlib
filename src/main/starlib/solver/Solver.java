package starlib.solver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import gov.nasa.jpf.Config;
import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Formula;
import starlib.predicate.InductivePred;
import starlib.predicate.InductivePredMap;

public class Solver {

	private static int MAX_DEPTH = 3;
	
	private static int MAX_TIME = 1;

	private static String s2sat = "s2sat";

	private static boolean ret = false;
	
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
	
	public static boolean checkSat(List<Formula> fs, Config c) {
		for (Formula f : fs) {
			if (checkSat(f, c)) return true;
		}
		
		return false;
	}
	
	public static boolean checkSat(Formula f, Config c) {
//		System.out.println(f);
//		System.out.println(f.getDepth());
		
		ret = false; model = new StringBuilder();
		
		int maxDepth = MAX_DEPTH;

		String s = c.getProperty("star.max_depth");
		if (s != null) {
			maxDepth = Integer.parseInt(s);
		}

		if (f.getDepth() > maxDepth) {
			return false;
		} else {
			// return true;
			File file = printToFile(f);
			
			if (file != null) {
				boolean ret = checkSat(file, c);
				return ret;
			}
			
			return false;
		}
	}

	private static File printToFile(Formula f) {
		try {
			File file = File.createTempFile("sat", null);
			System.out.println(file.getAbsolutePath());
			
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

	
	
	private static boolean checkSat(File file, Config c1) {
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
			
			int maxTime = MAX_TIME;
			
			String s = c.getProperty("star.max_time");
			if (s != null) {
				maxTime = Integer.parseInt(s);
			}
			
			future.get(maxTime, TimeUnit.SECONDS);

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
