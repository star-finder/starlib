package starlib.jpf.testsuites;

import java.util.List;

import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.VariableExpression;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.EqNullTerm;
import starlib.formula.pure.EqTerm;
import starlib.jpf.PathFinderUtils;

public class ConTestGenVisitor extends TestGenVisitor {

	public ConTestGenVisitor(TestGenVisitor that) {
		super(that);
	}
	
	@Override
	public void visit(PointToTerm term) {
		Variable var = term.getRoot();
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
	public void visit(EqTerm term) {
		Variable var1 = term.getVar1();
		Variable var2 = term.getVar2();
		
		if (initVars.contains(var2) && !initVars.contains(var1)) {
			initVars.add(var1);
			
			String name1 = PathFinderUtils.standardizeName(var1, objName, clsName, insFields, staFields);
			String name2 = PathFinderUtils.standardizeName(var2, objName, clsName, insFields, staFields);
			
			String type = var1.getType();
			
			if (PathFinderUtils.isInstanceVariable(var1,insFields) || PathFinderUtils.isClassVariable(var1,clsName, staFields))
				test.append("\t\t" + name1 + " = " + name2 + ";\n");
			else
				test.append("\t\t" + type + " " + name1 + " = " + name2 + ";\n");
		}
		
		if (initVars.contains(var1) && !initVars.contains(var2)) {
			initVars.add(var2);
			
			String name1 = PathFinderUtils.standardizeName(var1, objName, clsName, insFields, staFields);
			String name2 = PathFinderUtils.standardizeName(var2, objName, clsName, insFields, staFields);
			
			String type = var2.getType();
			
			if (PathFinderUtils.isInstanceVariable(var2,insFields) || PathFinderUtils.isClassVariable(var2,clsName, staFields))
				test.append("\t\t" + name2 + " = " + name1 + ";\n");
			else
				test.append("\t\t" + type + " " + name2 + " = " + name1 + ";\n");
		}
	}
	
	@Override
	public void visit(ComparisonTerm term) {
		Comparator comp = term.getComparator();
		Expression exp1 = term.getExp1();
		Expression exp2 = term.getExp2();
		
		List<Variable> vars1 = exp1.getVars();
		List<Variable> vars2 = exp2.getVars();
		
		if (comp == Comparator.EQ && exp1 instanceof Variable && 
				!initVars.containsAll(vars1) && (vars2.isEmpty() || initVars.containsAll(vars2))) {
			Variable var = (Variable) exp1;
			initVars.add(var);
			
			String name = var.getName();
			String type = var.getType();
			String value = exp2.toString();
			
			if (type.equals("boolean")) {
				if (value.equals("0"))
					value = "false";
				else
					value = "true";
			}
			
			if (PathFinderUtils.isInstanceVariable(var,insFields))
				test.append("\t\t" + name.replace("this_", objName + ".") + " = " + value + ";\n");
			else if (PathFinderUtils.isClassVariable(var,clsName, staFields))
				test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = " + value + ";\n");
			else
				test.append("\t\t" + type + " " + name + " = " + value + ";\n");
		}
		
		if (comp == Comparator.EQ && exp2 instanceof Variable && 
				!initVars.containsAll(vars2) && (vars1.isEmpty() || initVars.containsAll(vars1))) {
			Variable var = (Variable) exp2;
			initVars.add(var);
			
			String name = var.getName();
			String type = var.getType();
			String value = exp1.toString();
			
			if (type.equals("boolean")) {
				if (value.equals("0"))
					value = "false";
				else
					value = "true";
			}
			
			if (PathFinderUtils.isInstanceVariable(var,insFields))
				test.append("\t\t" + name.replace("this_", objName + ".") + " = " + value + ";\n");
			else if (PathFinderUtils.isClassVariable(var,clsName, staFields))
				test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = " + value + ";\n");
			else
				test.append("\t\t" + type + " " + name + " = " + value + ";\n");
		}
	}
	
	@Override
	public void visit(EqNullTerm term) {
		Variable var = term.getVar();
		
		if (!initVars.contains(var)) {
			initVars.add(var);
			
			String name = var.getName();
			String type = var.getType();
			
			if (PathFinderUtils.isInstanceVariable(var,insFields))
				test.append("\t\t" + name.replace("this_", objName + ".") + " = null;\n");
			else if (PathFinderUtils.isClassVariable(var,clsName, staFields))
				test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = null;\n");
			else
				test.append("\t\t" + type + " " + name + " = null;\n");
		}
	}
}
