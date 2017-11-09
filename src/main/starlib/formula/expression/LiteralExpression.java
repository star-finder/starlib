package starlib.formula.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import starlib.formula.Variable;

public class LiteralExpression implements Expression {
	
	private String value;
	
	public LiteralExpression(int value) {
		this.value = Integer.toString(value);
	}
	
	public LiteralExpression(long value) {
		this.value = Long.toString(value);
	}
	
	public LiteralExpression(double value) {
		this.value = Double.toString(value);
	}
	
	public LiteralExpression(String value) {
		this.value = value;
	}
	
	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		return this;
	}
	
	@Override
	public String toString() {
		return value;
	}

	@Override
	public List<Variable> getVars() {
		// return empty list
		return new ArrayList<Variable>();
	}

	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		// Do nothing
	}

}
