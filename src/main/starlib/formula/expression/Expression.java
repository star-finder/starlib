package starlib.formula.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import starlib.formula.Variable;

public interface Expression {
	
	public List<Variable> getVars();
	
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap);
	
	public void updateType(HashMap<String, String> knownTypeVars);
	
	/*
	public List<Variable> getVars() {
		return new ArrayList<Variable>();
	}
	
	public Expression substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		return null;
	}
	
	public void updateType(HashMap<String, String> knownTypeVars) {
		return;
	}
	//*/

}
