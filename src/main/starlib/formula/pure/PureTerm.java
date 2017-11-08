package starlib.formula.pure;

import java.util.HashMap;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Variable;

public abstract class PureTerm {
	
	public abstract PureTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap);
		
	public Variable[] getVars() {
		return null;
	}
	
	public PureTerm copy() {
		return this;
	}
	
	public void updateType(HashMap<String, String> knownTypeVars) {
		return;
	}

	public abstract void accept(StarVisitor visitor);
	
	/*
	 * This method check if the term is an equal comparison between a variable
	 * and something else, e.g. another variable or null
	 */
	public boolean isEqVar() {
		return false;
	}
}
