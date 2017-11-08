package starlib.formula.pure;

import java.util.HashMap;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Variable;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;

public class ComparisonTerm extends PureTerm {
	
	private Comparator comp;
	
	private Expression exp1;
	
	private Expression exp2;
	
	public ComparisonTerm(Comparator comp, Expression exp1, Expression exp2) {
		this.comp = comp;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public Comparator getComparator() {
		return comp;
	}
	
	public Expression getExp1() {
		return exp1;
	}
	
	public Expression getExp2() {
		return exp2;
	}
	
	@Override
	public PureTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		Expression newExp1 = (exp1 == null) ? null : exp1.substitute(fromVars, toVars, existVarSubMap);
		Expression newExp2 = (exp2 == null) ? null : exp2.substitute(fromVars, toVars, existVarSubMap);
		
		ComparisonTerm newTerm = new ComparisonTerm(comp, newExp1, newExp2);
		
		return newTerm;
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		exp1.updateType(knownTypeVars);
		exp2.updateType(knownTypeVars);
	}
	
	
	@Override
	public String toString() {
		return exp1.toString() + comp.toString() + exp2.toString();
	}

	@Override
	public void accept(StarVisitor visitor) {
		visitor.visit(this);
	}

}
