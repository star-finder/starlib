package starlib.formula.heap;

import java.util.List;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Variable;

public abstract class HeapTerm {
	
	// variables of current term, first variable is the root
	protected Variable[] vars;
	
	public HeapTerm substitute(Variable[] fromVars, Variable[] toVars,
			Map<String,String> existVarSubMap) {
		return null;
	}
	
	public Variable getRoot() {
		return vars[0];
	}
	
	public Variable[] getVars() {
		return vars;
	}
	
	public HeapTerm copy() {
		return this;
	}
	
	public void updateType(List<Variable> knownTypeVars) {
		return;
	}
	
	public abstract String toS2SATString();
	
	public void accept(StarVisitor visitor) {}

	protected String getParams(int start) {
		int length = vars.length;
		assert length > 0;
				
		StringBuilder params = new StringBuilder();
		for (int i = start; i < length - 1; i++) {
			params.append(vars[i] + ",");
		}
		
		if (length > start) {
			params.append(vars[length - 1]);
		}
		return params.toString();
	}
}
