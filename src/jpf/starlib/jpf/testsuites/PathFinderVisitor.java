package starlib.jpf.testsuites;

import java.util.HashMap;
import java.util.HashSet;

import gov.nasa.jpf.vm.FieldInfo;
import starlib.StarVisitor;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.pure.EqTerm;
import starlib.formula.pure.PureTerm;
import starlib.jpf.PathFinderUtils;

public class PathFinderVisitor extends StarVisitor{

	protected HashMap<String,String> knownTypeVars;
	protected HashSet<Variable> initVars;
	protected StringBuffer test;
	protected String objName;
	protected String clsName;
	protected FieldInfo[] insFields;
	protected FieldInfo[] staFields;
	
	public PathFinderVisitor(HashMap<String,String> knownTypeVars, HashSet<Variable> initVars,
			StringBuffer test, String objName, String clsName,
			FieldInfo[] insFields, FieldInfo[] staFields) {
		this.knownTypeVars = knownTypeVars;
		this.initVars = initVars;
		this.test = test;
		this.objName = objName;
		this.clsName = clsName;
		this.insFields = insFields;
		this.staFields = staFields;
	}
	
	public PathFinderVisitor(PathFinderVisitor that) {
		this.knownTypeVars = that.knownTypeVars;
		this.initVars = that.initVars;
		this.test = that.test;
		this.objName = that.objName;
		this.clsName = that.clsName;
		this.insFields = that.insFields;
		this.staFields = that.staFields;
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
	
	@Override
	public void visit(EqTerm term) {
		Variable var1 = term.getVar1();
		Variable var2 = term.getVar2();
		
		if (initVars.contains(var2) && !initVars.contains(var1)) {
			initVars.add(var1);
			
			String name1 = PathFinderUtils.standarizeName(var1, objName, clsName, insFields, staFields);
			String name2 = PathFinderUtils.standarizeName(var2, objName, clsName, insFields, staFields);
			
			String type = var1.getType();
			
			if (PathFinderUtils.isInstanceVariable(var1,insFields) || PathFinderUtils.isClassVariable(var1,clsName, staFields))
				test.append("\t\t" + name1 + " = " + name2 + ";\n");
			else
				test.append("\t\t" + type + " " + name1 + " = " + name2 + ";\n");
		}
		
		if (initVars.contains(var1) && !initVars.contains(var2)) {
			initVars.add(var2);
			
			String name1 = PathFinderUtils.standarizeName(var1, objName, clsName, insFields, staFields);
			String name2 = PathFinderUtils.standarizeName(var2, objName, clsName, insFields, staFields);
			
			String type = var2.getType();
			
			if (PathFinderUtils.isInstanceVariable(var2,insFields) || PathFinderUtils.isClassVariable(var2,clsName, staFields))
				test.append("\t\t" + name2 + " = " + name1 + ";\n");
			else
				test.append("\t\t" + type + " " + name2 + " = " + name1 + ";\n");
		}
	}
}
