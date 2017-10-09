package starlib.formula.heap;

import java.util.List;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Variable;

public abstract class HeapTerm {
	
	public HeapTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		return null;
	}
	
	public Variable[] getVars() {
		return null;
	}
	
	public HeapTerm copy() {
		return this;
	}
	
	public void updateType(List<Variable> knownTypeVars) {
		return;
	}
	
	public abstract String toS2SATString();
	
	public void accept(StarVisitor visitor) {}
}
