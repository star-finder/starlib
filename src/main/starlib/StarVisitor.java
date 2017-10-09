package starlib;

import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.EqNullTerm;
import starlib.formula.pure.EqTerm;
import starlib.formula.pure.NEqNullTerm;
import starlib.formula.pure.NEqTerm;

public class StarVisitor {

	public void visit(NEqTerm term) {}
	
	public void visit(EqNullTerm term) {}
	
	public void visit(NEqNullTerm term) {}
	
	public void visit(EqTerm term) {}
	
	public void visit(ComparisonTerm term) {}
	
	public void visit(HeapTerm term) {}
	
	public void visit(PointToTerm term) {}
	
	public void visit(Formula formula) {}
	
	public void visit(HeapFormula formula) {}
	
	public void visit(PureFormula formula) {}
}
