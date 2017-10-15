package starlib.jpf.testsuites;

import starlib.formula.Variable;
import starlib.formula.pure.EqTerm;
import starlib.formula.pure.NEqNullTerm;
import starlib.formula.pure.NEqTerm;
import starlib.jpf.PathFinderUtils;

public class NoConcreteVisitor extends PathFinderVisitor {

	public NoConcreteVisitor(PathFinderVisitor that) {
		super(that);
	}

	@Override
	public void visit(NEqTerm term) {
		genConcreteVars(term.getVar1());
		genConcreteVars(term.getVar2());
	}

	private void genConcreteVars(Variable var) {
		if (!initVars.contains(var)) {
			initVars.add(var);
			
			String name = var.getName();
			String type = var.getType();
			
			if (PathFinderUtils.isInstanceVariable(var,insFields))
				test.append("\t\t" + name.replace("this_", objName + ".") + " = new " + type + "();\n");
			else if (PathFinderUtils.isClassVariable(var,clsName, staFields))
				test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = new " + type + "();\n");
			else
				test.append("\t\t" + type + " " + name + " = new " + type + "();\n");
		}
	}

	@Override
	public void visit(NEqNullTerm term) {
		genConcreteVars(term.getVar());
	}
	
	@Override
	public void visit (EqTerm term) {
		super.visit(term);
		Variable var1 = term.getVar1();
		Variable var2 = term.getVar2();
		
		if (!initVars.contains(var1) && !initVars.contains(var2)) {
			initVars.add(var1);
			initVars.add(var2);
			
			String name1 = var1.getName();
			String type1 = var1.getType();
			
			if (PathFinderUtils.isInstanceVariable(var1, insFields))
				test.append("\t\t" + name1.replace("this_", objName + ".") + " = new " + type1 + "();\n");
			else if (PathFinderUtils.isClassVariable(var1, clsName, staFields))
				test.append("\t\t" + name1.replace(clsName + "_", clsName + ".") + " = new " + type1 + "();\n");
			else
				test.append("\t\t" + type1 + " " + name1 + " = new " + type1 + "();\n");
			
			name1 = PathFinderUtils.standarizeName(var1, objName, clsName, insFields, staFields);
			
			String name2 = PathFinderUtils.standarizeName(var2, objName, clsName, insFields, staFields);
			String type2 = var2.getType();
			
			if (PathFinderUtils.isInstanceVariable(var2, insFields) || PathFinderUtils.isClassVariable(var2, clsName, staFields))
				test.append("\t\t" + name2 + " = " + name1 + ";\n");
			else
				test.append("\t\t" + type2 + " " + name2 + " = " + name1 + ";\n");
		}
	}
}
