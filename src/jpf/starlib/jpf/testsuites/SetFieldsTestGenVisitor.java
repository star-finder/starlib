package starlib.jpf.testsuites;

import gov.nasa.jpf.vm.FieldInfo;
import starlib.data.DataNode;
import starlib.data.DataNodeMap;
import starlib.formula.Variable;
import starlib.formula.heap.PointToTerm;
import starlib.jpf.PathFinderUtils;

public class SetFieldsTestGenVisitor extends TestGenVisitor {

	public SetFieldsTestGenVisitor(TestGenVisitor that) {
		super(that);
	}
		
	@Override
	public void visit(PointToTerm term) {
		Variable[] vars = term.getVars();
		for (FieldInfo field : insFields) {
			if (field.isFinal()) {
				String name = "this_" + field.getName();
				String name0 = vars[0].getName();
				
				if (name.equals(name0)) return;
			}
		}
		
		for (FieldInfo field : staFields) {
			if (field.isFinal()) {
				String name = clsName + "_" + field.getName();
				String name0 = vars[0].getName();
				
				if (name.equals(name0)) return;
			}
		}
		
		int length = vars.length;
		
		DataNode dn = DataNodeMap.find(term.getType());
		
		Variable[] fields = dn.getFields();
		
		for (int i = 1; i < length; i++) {
			String name0 = vars[0].getName();
			String nameI = vars[i].getName();
			
			if (nameI.startsWith("Anon_")) continue;
			
			// TODO Sang: why vars[0] is always in the loop?
			if (PathFinderUtils.isInstanceVariable(vars[0],insFields))
				name0 = name0.replace("this_", objName + ".");
			else if (PathFinderUtils.isClassVariable(vars[0],clsName, staFields))
				name0 = name0.replace(clsName + "_", clsName + ".");
			
			if (PathFinderUtils.isInstanceVariable(vars[i], insFields))
				nameI = nameI.replace("this_", objName + ".");
			else if (PathFinderUtils.isClassVariable(vars[i], clsName, staFields))
				nameI = nameI.replace(clsName + "_", clsName + ".");
			
			test.append("\t\t" + name0 + "." + fields[i - 1].getName() + " = " + nameI + ";\n");
		}
	}
}
