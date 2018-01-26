package starlib.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class Preprocessor {
	
	public static List<Formula> preprocess(Formula pre, Formula f) {		
		Formula res = pre.copy();

		PureFormula pf = f.getPureFormula();
		for (PureTerm pt : pf.getPureTerms()) {
			ComparisonTerm ct = (ComparisonTerm) pt;
			res.addComparisonTerm(ct.getComparator(), ct.getExp1(), ct.getExp2());
		}
		
		return preprocess(res, new HashMap<String,String>());
	}
	
	private static String getName(PointToTerm pt, String root, String field, String suffix) {
		DataNode dn = DataNodeMap.find(pt.getType());
		Variable[] fields = dn.getFields();

		int i = 0;
		for (i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			
			if (field.equals(fieldName)) {
				return pt.getVarsNoRoot()[i].getName() + suffix;
			}
		}
		
		return null;
	}
	
	private static void updateAliasNameMap(Set<String> alias, Map<String,String> nameMap,
			String rootName, String field, String newName) {
		if (alias == null) return;
		
		for (String name : alias) {
			if (name.equals(rootName)) continue;
			
			String oldName = name + "." + field;
			boolean updated = false;
			
			for (String key : nameMap.keySet()) {
				if (key.contains(oldName)) {
					nameMap.put(key, newName);
					break;
				}
			}
			
			if (!updated) nameMap.put(oldName, newName);
		}
	}
	
	private static void updateKeysAliasMap(Map<String,Set<String>> aliasMap, String oldName, String newName) {
		Iterator<String> keysIt = aliasMap.keySet().iterator();
		Set<String> values = null;
		
		while (keysIt.hasNext()) {
			String key = keysIt.next();
			if (key.equals(oldName)) {
				values = aliasMap.get(key);
				keysIt.remove();
				break;
			}
		}
			
		if (values != null) {
			aliasMap.put(newName, values);
		}
	}
	
	private static void updateValuesAliasMap(Map<String,Set<String>> aliasMap, String oldName, String newName) {
		for (String key : aliasMap.keySet()) {
			Iterator<String> valuesIt = aliasMap.get(key).iterator();
			boolean removedValue = false;
			
			while (valuesIt.hasNext()) {
				String value = valuesIt.next();
				if (value.equals(oldName)) {
					valuesIt.remove();
					removedValue = true;
					break;
				}
			}
			
			if (removedValue) {
				aliasMap.get(key).add(newName);
			}
		}
	}
	
	private static void updateAliasMap(Map<String,Set<String>> aliasMap, String oldName, String newName) {
		updateKeysAliasMap(aliasMap, oldName, newName);
		updateValuesAliasMap(aliasMap, oldName, newName);
	}
	
	private static void removeOldNameMap(String name, Map<String,String> nameMap) {
		if (!name.contains("_")) return;
		
		String origName = name.substring(0, name.lastIndexOf('_'));
		
		Iterator<String> it = nameMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.contains("_")) {
				key = key.substring(0, key.lastIndexOf('_'));
			}
			
			if (origName.equals(key)) {
				it.remove();
			}
		}
	}
	
	public static List<Formula> preprocess(Formula f, Map<String,String> nameMap) {
		List<Formula> fs = new ArrayList<Formula>();
		
		PureFormula pf = f.getPureFormula();
		for (PureTerm pt : pf.getPureTerms()) {
			List<Variable> vars = new ArrayList<Variable>();
			CollectVarsVisitor visitor = new CollectVarsVisitor(vars);
			visitor.visit((ComparisonTerm) pt);
			
			for (int indexVar = 0; indexVar < vars.size(); indexVar++) {
				Variable var = vars.get(indexVar);
				
				String varName = var.getName();
				String[] varNameSplit = varName.split("\\.");
								
				String rootName = varNameSplit[0];
				for (int i = 1; i < varNameSplit.length; i++) {
					HeapTerm ht = Utilities.findHeapTermNoRoot(f, rootName);
					
					if (ht == null) {
						return fs;
					} else if (ht instanceof PointToTerm) {
						String newName = null;
						String oldName = rootName + "." + varNameSplit[i];
						
						String field = varNameSplit[i];
						String suffix = "";
						if (field.contains("_")) {
							suffix = field.substring(field.lastIndexOf('_'), field.length());
							field = field.substring(0, field.lastIndexOf('_'));
						}
							
						
						if (nameMap.containsKey(oldName)) {
							newName = nameMap.get(oldName);
						} else {
							newName = getName((PointToTerm) ht, rootName, field, suffix);
							if (newName == null) return fs;
							
							removeOldNameMap(oldName, nameMap);
							nameMap.put(oldName, newName);
						}
						
						// update alias here
						updateAliasMap(f.getAliasMap(), oldName, newName);
						updateAliasNameMap(f.getAlias(rootName), nameMap, rootName, field, newName);
						
						for (int j = i + 1; j < varNameSplit.length; j++) {
							newName += "." + varNameSplit[j];
						}
						
						var.setName(newName); // wrong
						rootName = newName;
					} else if (ht instanceof InductiveTerm) {
						InductiveTerm it = (InductiveTerm) ht;
						Formula[] unfoldFs = it.unfold();
						
						for (int j = 0; j < unfoldFs.length; j++) {
							Formula copyF = f.copy();
							copyF.unfold(it, j);
							
							fs.addAll(preprocess(copyF, new HashMap<String,String>(nameMap)));
						}
						
						return fs;
					}
				}
			}
		}
		
		fs.add(f);
		return fs;
	}

}
