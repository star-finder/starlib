package starlib.formula.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import starlib.formula.Variable;

public class NullExpression implements Expression{

	private static final NullExpression INSTANCE = new NullExpression();
	
	private NullExpression(){}
	
	public static NullExpression getInstance() {
		return INSTANCE;
	}
	
	@Override
	public List<Variable> getVars() {
		// return empty list
		return new ArrayList<Variable>();
	}

	@Override
	public Expression substitute(Variable[] fromVars, Variable[] toVars, Map<String, String> existVarSubMap) {
		return INSTANCE;
	}

	@Override
	public void updateType(HashMap<String, String> knownTypeVars) {
		// Do nothing
	}
	
	@Override
	public String toString() {
		return "null";
	}
	
	@Override 
	public int hashCode() {
		return "null".hashCode();
	}

}
