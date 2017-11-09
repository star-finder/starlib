package starlib.formula.expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import starlib.formula.Variable;

public abstract class Expression {
	
	protected static final Set<Variable> EMPTY_SET = new HashSet<Variable>();
	
	public abstract Set<Variable> getVars();
	
	public abstract Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap);
	
	public abstract void updateType(HashMap<String, String> knownTypeVars);

}
