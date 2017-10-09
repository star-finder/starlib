package starlib.jpf;

import java.util.List;

import starlib.formula.HeapFormula;
import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.VariableExpression;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.EqNullTerm;

public class ConcreteVisitor extends PathFinderVisitor {

	public ConcreteVisitor(PathFinderVisitor that) {
		super(that);
	}
	
	@Override
	public void visit(HeapFormula formula) {
		HeapTerm[] heapTerms = formula.getHeapTerms();
		
		int oldLength = initVars.size();
		
		while (true) {
			int length = heapTerms.length;
			
			for (int i = 0; i < length; i++) {
				heapTerms[i].accept(this);
			}
			
			int newLength = initVars.size();
			
			if (newLength == oldLength) break;
			else oldLength = newLength;
		}
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
	public void visit(ComparisonTerm term) {
		Comparator comp = term.getComparator();
		Expression exp1 = term.getExp1();
		Expression exp2 = term.getExp2();
		
		List<Variable> vars1 = exp1.getVars();
		List<Variable> vars2 = exp2.getVars();
		
		if (comp == Comparator.EQ && exp1 instanceof VariableExpression && 
				!initVars.containsAll(vars1) && (vars2.isEmpty() || initVars.containsAll(vars2))) {
			Variable var = ((VariableExpression) exp1).getVar();
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
		
		if (comp == Comparator.EQ && exp2 instanceof VariableExpression && 
				!initVars.containsAll(vars2) && (vars1.isEmpty() || initVars.containsAll(vars1))) {
			Variable var = ((VariableExpression) exp2).getVar();
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
