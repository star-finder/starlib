package starlib.jpf.testsuites;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import gov.nasa.jpf.vm.FieldInfo;
import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.jpf.PathFinderUtils;

public class TestGenVisitor extends InitVarsVisitor {
	
	protected StringBuffer test;

	public TestGenVisitor(HashMap<String,String> knownTypeVars, HashSet<Variable> initVars,
			String objName, String clsName, FieldInfo[] insFields, FieldInfo[] staFields, StringBuffer test) {
		super(knownTypeVars, initVars, objName, clsName, insFields, staFields);
		this.test = test;
	}
	
	public TestGenVisitor(TestGenVisitor that) {
		super(that);
		this.test = that.test;
	}

	@Override
	public void visit(Formula formula) {
		ConTestGenVisitor con = new ConTestGenVisitor(this);
		NoConTestGenVisitor ncon = new NoConTestGenVisitor(this);
		SetFieldsTestGenVisitor setFields = new SetFieldsTestGenVisitor(this);

		HeapFormula heapFormula = formula.getHeapFormula();
		PureFormula pureFormula = formula.getPureFormula();

		heapFormula.accept(con);
		pureFormula.accept(con);
		pureFormula.accept(ncon);

		genDefaultVars();

		heapFormula.accept(setFields);
	}
	
	@Override
	public void visit(HeapFormula formula) {
		for (HeapTerm heapTerm : formula.getHeapTerms()) {
			heapTerm.accept(this);
		}
	}
	
	private void genDefaultVars() {
		if (knownTypeVars.size() == initVars.size())
			return;
		
		// for (Variable var : knownTypeVars) {
		for(Entry<String, String> entry : knownTypeVars.entrySet()) {
			String name = entry.getKey(); // name is key, type is value
			String type = entry.getValue();
			Variable var = new Variable(name,type);
			
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
