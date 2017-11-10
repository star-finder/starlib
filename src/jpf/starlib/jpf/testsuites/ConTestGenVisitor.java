package starlib.jpf.testsuites;

import java.util.Set;

import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;
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
			test.append(makeDeclAndInitWithConstructor(var));
		}
	}
	
	@Override
	public void visit(ComparisonTerm term) {
		Expression exp1 = term.getExp1();
		Expression exp2 = term.getExp2();
		Comparator comp = term.getComparator();
		boolean isVar1 = exp1 instanceof Variable;
		boolean isVar2 = exp2 instanceof Variable;
		boolean isNull2 = exp2 instanceof NullExpression;
	
		//WIP
		
		Set<Variable> vars1 = exp1.getVars();
		Set<Variable> vars2 = exp2.getVars();
		
		if (comp == Comparator.EQ && isVar1 && 
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
			
			if (isInstanceVariable(var))
				test.append("\t\t" + name.replace("this_", objName + ".") + " = " + value + ";\n");
			else if (isClassVariable(var))
				test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = " + value + ";\n");
			else
				test.append("\t\t" + type + " " + name + " = " + value + ";\n");
			
		}
		
		if (comp == Comparator.EQ && isVar2 && 
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
			
			if (isInstanceVariable(var))
				test.append("\t\t" + name.replace("this_", objName + ".") + " = " + value + ";\n");
			else if (isClassVariable(var))
				test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = " + value + ";\n");
			else
				test.append("\t\t" + type + " " + name + " = " + value + ";\n");
			
			System.out.println("Reach here 2 ================");

		}
	}

	
	@Override
	public void visit(EqTerm term) {
		Variable var1 = term.getVar1();
		Variable var2 = term.getVar2();
		
		if (initVars.contains(var2) && !initVars.contains(var1)) {
			initVars.add(var1);
			
			String name1 = standardizeName(var1);
			String name2 = standardizeName(var2);
			
			String type = var1.getType();
			
			if (isInstanceVariable(var1) || isClassVariable(var1))
				test.append("\t\t" + name1 + " = " + name2 + ";\n");
			else
				test.append("\t\t" + type + " " + name1 + " = " + name2 + ";\n");
		}
		
		if (initVars.contains(var1) && !initVars.contains(var2)) {
			initVars.add(var2);
			
			String name1 = standardizeName(var1);
			String name2 = standardizeName(var2);
			
			String type = var2.getType();
			
			if (isInstanceVariable(var2) || isClassVariable(var2))
				test.append("\t\t" + name2 + " = " + name1 + ";\n");
			else
				test.append("\t\t" + type + " " + name2 + " = " + name1 + ";\n");
		}
	}
	
	
	@Override
	public void visit(EqNullTerm term) {
		Variable var = term.getVar();
		
		if (!initVars.contains(var)) {
			initVars.add(var);
			
			String name = var.getName();
			String type = var.getType();
			
			if (isInstanceVariable(var))
				test.append("\t\t" + name.replace("this_", objName + ".") + " = null;\n");
			else if (isClassVariable(var))
				test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = null;\n");
			else
				test.append("\t\t" + type + " " + name + " = null;\n");
		}
	}
}
