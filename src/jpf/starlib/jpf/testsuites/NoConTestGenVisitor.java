package starlib.jpf.testsuites;

import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.NullExpression;
import starlib.formula.pure.ComparisonTerm;

/*
 * This class initializes variables with their default constructions, i.e. no arguments.
 */
public class NoConTestGenVisitor extends TestGenVisitor {

	public NoConTestGenVisitor(TestGenVisitor that) {
		super(that);
	}
	
	private void genConcreteVars(Variable var) {
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
		
		if(comp == Comparator.NE) {
			if(isVar1 && isVar2) {
				// NEqTerm: what happens if x = 5?
				genConcreteVars((Variable)exp1);
				genConcreteVars((Variable)exp2);
			}
			if(isVar1 && isNull2) {
				// NEqNullTerm
				genConcreteVars((Variable)exp1);
			}
			return;
		}
		
		//EqTerm
		if(comp == Comparator.EQ && isVar1 && isVar2) {
			// FIXME: check old code before renaming
			super.visit(term);
			
			Variable var1 = (Variable) exp1;
			Variable var2 = (Variable) exp2;
			
			if (!initVars.contains(var1) && !initVars.contains(var2)) {
				initVars.add(var1);
				initVars.add(var2);
				test.append(makeDeclAndInit(var1,"new " + var1.getType() + "()"));
				test.append(makeDeclAndInit(var2, standardizeName(var1)));
			}
		}
	}
}
