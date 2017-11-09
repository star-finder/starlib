package starlib.formula.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import starlib.formula.Variable;

public class BinaryExpression extends Expression {

	private Operator op;
	
	private Expression exp1;
	
	private Expression exp2;
	
	public BinaryExpression(Operator op, Expression exp1, Expression exp2) {
		this.op = op;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	@Override
	public Set<Variable> getVars() {
		Set<Variable> vars1 = exp1.getVars();
		Set<Variable> vars2 = exp2.getVars();
		
		vars1.addAll(vars2);
		
		return vars1;
	}
	
	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		Expression newExp1 = exp1.substitute(fromVars, toVars, existVarSubMap);
		Expression newExp2 = exp2.substitute(fromVars, toVars, existVarSubMap);
		BinaryExpression newBinaryExp = new BinaryExpression(op, newExp1, newExp2);
		
		return newBinaryExp;
	}
	
	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		exp1.updateType(knownTypeVars);
		exp2.updateType(knownTypeVars);
	}
	
	@Override
	public String toString() {
		return "(" + exp1.toString() + op.toString() + exp2.toString() + ")";
	}
	
}
