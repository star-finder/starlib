package starlib.formula.pure;

import java.util.HashMap;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Variable;

public abstract class PureTerm {
	
	public abstract PureTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap);
	
	public PureTerm copy() {
		return this;
	}
	
	public void updateType(HashMap<String, String> knownTypeVars) {
		return;
	}

	public abstract void accept(StarVisitor visitor);
	
}
