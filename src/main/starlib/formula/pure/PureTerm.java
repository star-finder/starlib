package starlib.formula.pure;

import java.util.List;
import java.util.Map;

import starlib.StarVisitor;
import starlib.formula.Variable;
import sun.reflect.FieldInfo;

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
	
	public void genConcreteVars(List<Variable> initVars, StringBuffer test, String objName, String clsName,
			FieldInfo[] insFields, FieldInfo[] staFields) {
		return;
	}
	
	public void genNoConcreteVars(List<Variable> initVars, StringBuffer test, String objName, String clsName,
			FieldInfo[] insFields, FieldInfo[] staFields) {
		return;
	}

	public abstract void accept(StarVisitor visitor);
}
