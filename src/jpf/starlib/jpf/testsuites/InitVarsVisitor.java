package starlib.jpf.testsuites;

import java.util.HashMap;
import java.util.HashSet;

import gov.nasa.jpf.vm.FieldInfo;
import starlib.StarVisitor;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.heap.HeapTerm;
import starlib.formula.pure.EqTerm;
import starlib.formula.pure.PureTerm;
import starlib.jpf.PathFinderUtils;

public class InitVarsVisitor extends StarVisitor {

	protected HashMap<String,String> knownTypeVars;
	protected HashSet<Variable> initVars;
	protected String objName;
	protected String clsName;
	protected FieldInfo[] insFields;
	protected FieldInfo[] staFields;
	
	public InitVarsVisitor(HashMap<String,String> knownTypeVars, HashSet<Variable> initVars,
			String objName, String clsName,
			FieldInfo[] insFields, FieldInfo[] staFields) {
		this.knownTypeVars = knownTypeVars;
		this.initVars = initVars;
		this.objName = objName;
		this.clsName = clsName;
		this.insFields = insFields;
		this.staFields = staFields;
	}
	
	public InitVarsVisitor(InitVarsVisitor that) {
		this.knownTypeVars = that.knownTypeVars;
		this.initVars = that.initVars;
		this.objName = that.objName;
		this.clsName = that.clsName;
		this.insFields = that.insFields;
		this.staFields = that.staFields;
	}
	
	@Override
	public void visit(HeapFormula formula) {
		int oldLength = initVars.size();
		while (true) {
			
			for (HeapTerm heapTerm : formula.getHeapTerms()) {
				heapTerm.accept(this);
			}
			
			int newLength = initVars.size();
			
			if (newLength == oldLength) break;
			else oldLength = newLength;
		}
	}
	
	@Override
	public void visit(PureFormula formula) {
		int oldLength = initVars.size();
		while (true) {
			
			for (PureTerm pureTerm : formula.getPureTerms()) {
				pureTerm.accept(this);
			}
			
			int newLength = initVars.size();
			
			if (newLength == oldLength) break;
			else oldLength = newLength;
		}
	}

}
