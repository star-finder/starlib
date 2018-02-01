package starlib.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
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

		HeapFormula hf = f.getHeapFormula();
		for (HeapTerm ht : hf.getHeapTerms()) {
			PointToTerm pt = (PointToTerm) ht;
			res.addPointToTerm(pt.getVars(), pt.getType());
		}
		
		PureFormula pf = f.getPureFormula();
		for (PureTerm pt : pf.getPureTerms()) {
			ComparisonTerm ct = (ComparisonTerm) pt;
			res.addComparisonTerm(ct.getComparator(), ct.getExp1(), ct.getExp2());
		}
		
		List<Formula> results = preprocess(res, new HashMap<String,String>());
		return results;
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
		Map<String,Set<String>> tmp = new HashMap<String,Set<String>>();

		Iterator<String> keysIt = aliasMap.keySet().iterator();
			
		while (keysIt.hasNext()) {
			String key = keysIt.next();
			if (key.contains(oldName)) {
				Set<String> values = aliasMap.get(key);
				String suffix = key.substring(oldName.length());
							
				tmp.put(newName + suffix, values);
				keysIt.remove();
			}
		}
		
		for (Entry<String, Set<String>> entry : tmp.entrySet()) {
			String key = entry.getKey();
			if (aliasMap.containsKey(key)) {
				aliasMap.get(key).addAll(entry.getValue());
			} else {
				aliasMap.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	private static void updateValuesAliasMap(Map<String,Set<String>> aliasMap, String oldName, String newName) {
		for (String key : aliasMap.keySet()) {
			Iterator<String> valuesIt = aliasMap.get(key).iterator();
			Set<String> values = new HashSet<String>();
				
			while (valuesIt.hasNext()) {
				String value = valuesIt.next();
				if (value.contains(oldName)) {
					String suffix = value.substring(oldName.length());
					values.add(newName + suffix);
						
					valuesIt.remove();
				}
			}
				
			aliasMap.get(key).addAll(values);
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
		String s1 = "newNode_3->rbt_TreeMap__Entry(key_4,value_5,left_6,right_7,parent_8,color_9) * this_root->rbt_TreeMap__Entry(key_665,value_666,left_667,right_668,parent_669,color_670) * rbtE(right_668,this_root,key_665,maxE_674,sizeR_675,bhR_676) * left_667->rbt_TreeMap__Entry(key_677,value_678,left_679,right_680,parent_681,color_682) * rbtE(left_679,left_667,minE_671,key_677,sizeL_683,bhL_684) * rbtE(right_680,left_667,key_677,key_665,sizeR_685,bhR_686) & this_root != null & key < key_665 & left_667 != null & key >= key_677 & key != key_677 & right_680 = null & this_modCount_1 = (this_modCount + 1) & this_size_2 = (this_size + 1) & left_6_10 = null & right_7_11 = null & color_9_12 = 1 & key_4_13 = key & value_5_14 = value & parent_8_15 = left_667 & right_680_16 = newNode_3 & color_9_17 = 0 & right_680_16 != null & right_680_16 != this_root & this_root.left.right_16.parent.color = 0 & this_root.left.right_16 != null & this_root.left.right_16 != null & this_root.left.right_16.parent != null & this_root.left.right_16.parent.parent != null & this_root.left.right_16.parent = this_root.left.right_16.parent.parent.left & this_root.left.right_16 != null & this_root.left.right_16.parent != null & this_root.left.right_16.parent.parent != null & this_root.left.right_16.parent.parent.right != null & parent_669 = null & color_670 = 1 & this_size = ((sizeL_672 + sizeR_675) + 1) & bhL_673 = bhR_676 & minE_671 < key_677 & key_677 < key_665 & parent_681 = this_root & color_682 = 1 & sizeL_672 = ((sizeL_683 + sizeR_685) + 1) & bhL_684 = bhR_686 & bhL_673 = (1 + bhL_684)";
		String s2 = "this_root.left.right_16.parent.color = 0";
		
//		System.out.println(f);
		
		for (PureTerm pt : pf.getPureTerms()) {
			if (f.toString().equals(s1)) {
				int i = 0;
				i++;
			}
			
			if (pt.toString().equals(s2)) {
				int i = 0;
				i++;
			}
			
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
						
						rootName = newName;
						
						for (int j = i + 1; j < varNameSplit.length; j++) {
							newName += "." + varNameSplit[j];
						}
						
						var.setName(newName);
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
