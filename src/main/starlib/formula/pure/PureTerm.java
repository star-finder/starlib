package starlib.formula.pure;

import java.util.List;
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
	
	public void updateType(List<Variable> knownTypeVars) {
		return;
	}

	public abstract void accept(StarVisitor visitor);
}
