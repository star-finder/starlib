package starlib.jpf;

import java.util.List;

import gov.nasa.jpf.vm.FieldInfo;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;

public class PathFinderTestGenerator extends PathFinderVisitor {

	public PathFinderTestGenerator(List<Variable> knownTypeVars, List<Variable> initVars, StringBuffer test,
			String objName, String clsName, FieldInfo[] insFields, FieldInfo[] staFields) {
		super(knownTypeVars, initVars, test, objName, clsName, insFields, staFields);
	}

	@Override
	public void visit(Formula formula) {
		ConcreteVisitor con = new ConcreteVisitor(this);
		NoConcreteVisitor ncon = new NoConcreteVisitor(this);
		SetFieldsVisitor setFields = new SetFieldsVisitor(this);

		HeapFormula heapFormula = formula.getHeapFormula();
		PureFormula pureFormula = formula.getPureFormula();

		heapFormula.accept(con);
		pureFormula.accept(con);
		pureFormula.accept(ncon);

		genDefaultVars();

		heapFormula.accept(setFields);
	}
	
	private void genDefaultVars() {
		if (knownTypeVars.size() == initVars.size())
			return;
		
		for (Variable var : knownTypeVars) {
			String name = var.getName();
			String type = var.getType();
			
			if (name.startsWith("Anon_")) continue;
				
			if (!initVars.contains(var)) {
				if (var.isPrim()) {
					String val = type.equals("boolean") ? "false" : "0";
					
					if (PathFinderUtils.isInstanceVariable(var, insFields))
						test.append("\t\t" + name.replace("this_", objName + ".") + " = " + val + ";\n");
					else if (PathFinderUtils.isClassVariable(var, clsName, staFields))
						test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = " + val + ";\n");
					else
						test.append("\t\t" + type + " " + name + " = " + val + ";\n");
				} else {
					if (PathFinderUtils.isInstanceVariable(var, insFields))
						test.append("\t\t" + name.replace("this_", objName + ".") + " = null;\n");
					else if (PathFinderUtils.isClassVariable(var, clsName, staFields))
						test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = null;\n");
					else
						test.append("\t\t" + type + " " + name + " = null;\n");
				}
			}
		}
	}
}
