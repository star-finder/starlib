// Generated from InductivePred.g4 by ANTLR 4.7

package starlib.predicate;

import starlib.formula.Formula;
import starlib.formula.HeapFormula;
import starlib.formula.PureFormula;
import starlib.formula.Variable;
import starlib.formula.expression.BinaryExpression;
import starlib.formula.expression.Comparator;
import starlib.formula.expression.Expression;
import starlib.formula.expression.LiteralExpression;
import starlib.formula.expression.NullExpression;
import starlib.formula.expression.Operator;
import starlib.formula.heap.HeapTerm;
import starlib.formula.heap.InductiveTerm;
import starlib.formula.heap.PointToTerm;
import starlib.formula.pure.ComparisonTerm;
import starlib.formula.pure.PureTerm;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InductivePredParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PRED=1, NULL=2, EQEQ=3, EQ=4, NE=5, GE=6, GT=7, LE=8, LT=9, PLUS=10, MINUS=11, 
		MUL=12, DIV=13, LB=14, RB=15, CM=16, SM=17, OR=18, AND=19, PT=20, STAR=21, 
		ID=22, INT=23, DOUBLE=24, WS=25;
	public static final int
		RULE_preds = 0, RULE_tail = 1, RULE_pred = 2, RULE_params = 3, RULE_param = 4, 
		RULE_formulas = 5, RULE_formula = 6, RULE_heapTerms = 7, RULE_heapTerm = 8, 
		RULE_pointToTerm = 9, RULE_inductiveTerm = 10, RULE_pureTerms = 11, RULE_pureTerm = 12, 
		RULE_comparisonTerm = 13, RULE_comp = 14, RULE_exp = 15, RULE_ter = 16, 
		RULE_lit = 17;
	public static final String[] ruleNames = {
		"preds", "tail", "pred", "params", "param", "formulas", "formula", "heapTerms", 
		"heapTerm", "pointToTerm", "inductiveTerm", "pureTerms", "pureTerm", "comparisonTerm", 
		"comp", "exp", "ter", "lit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'pred'", "'null'", "'=='", "'='", "'!='", "'>='", "'>'", "'<='", 
		"'<'", "'+'", "'-'", "'#'", "'/'", "'('", "')'", "','", "';'", "'||'", 
		"'&'", "'::'", "'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PRED", "NULL", "EQEQ", "EQ", "NE", "GE", "GT", "LE", "LT", "PLUS", 
		"MINUS", "MUL", "DIV", "LB", "RB", "CM", "SM", "OR", "AND", "PT", "STAR", 
		"ID", "INT", "DOUBLE", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "InductivePred.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InductivePredParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PredsContext extends ParserRuleContext {
		public InductivePred[] ips;
		public PredContext pred;
		public TailContext tail;
		public PredContext pred() {
			return getRuleContext(PredContext.class,0);
		}
		public TailContext tail() {
			return getRuleContext(TailContext.class,0);
		}
		public PredsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPreds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPreds(this);
		}
	}

	public final PredsContext preds() throws RecognitionException {
		PredsContext _localctx = new PredsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_preds);
		try {
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				((PredsContext)_localctx).pred = pred();

						((PredsContext)_localctx).ips =  new InductivePred[1];
						_localctx.ips[0] = ((PredsContext)_localctx).pred.ip;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				((PredsContext)_localctx).pred = pred();
				setState(40);
				((PredsContext)_localctx).tail = tail();

						int length = ((PredsContext)_localctx).tail.ips.length + 1;
						((PredsContext)_localctx).ips =  new InductivePred[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.ips[0] = ((PredsContext)_localctx).pred.ip;
							else _localctx.ips[i] = ((PredsContext)_localctx).tail.ips[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TailContext extends ParserRuleContext {
		public InductivePred[] ips;
		public PredContext pred;
		public TailContext tail;
		public TerminalNode SM() { return getToken(InductivePredParser.SM, 0); }
		public PredContext pred() {
			return getRuleContext(PredContext.class,0);
		}
		public TailContext tail() {
			return getRuleContext(TailContext.class,0);
		}
		public TailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitTail(this);
		}
	}

	public final TailContext tail() throws RecognitionException {
		TailContext _localctx = new TailContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_tail);
		try {
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(45);
				match(SM);
				setState(46);
				((TailContext)_localctx).pred = pred();

						((TailContext)_localctx).ips =  new InductivePred[1];
						_localctx.ips[0] = ((TailContext)_localctx).pred.ip;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				match(SM);
				setState(50);
				((TailContext)_localctx).pred = pred();
				setState(51);
				((TailContext)_localctx).tail = tail();

						int length = ((TailContext)_localctx).tail.ips.length + 1;
						((TailContext)_localctx).ips =  new InductivePred[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.ips[0] = ((TailContext)_localctx).pred.ip;
							else _localctx.ips[i] = ((TailContext)_localctx).tail.ips[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredContext extends ParserRuleContext {
		public InductivePred ip;
		public Token ID;
		public ParamsContext params;
		public FormulasContext formulas;
		public TerminalNode PRED() { return getToken(InductivePredParser.PRED, 0); }
		public TerminalNode ID() { return getToken(InductivePredParser.ID, 0); }
		public TerminalNode LB() { return getToken(InductivePredParser.LB, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RB() { return getToken(InductivePredParser.RB, 0); }
		public TerminalNode EQEQ() { return getToken(InductivePredParser.EQEQ, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public PredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPred(this);
		}
	}

	public final PredContext pred() throws RecognitionException {
		PredContext _localctx = new PredContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pred);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(PRED);
			setState(57);
			((PredContext)_localctx).ID = match(ID);
			setState(58);
			match(LB);
			setState(59);
			((PredContext)_localctx).params = params();
			setState(60);
			match(RB);
			setState(61);
			match(EQEQ);
			setState(62);
			((PredContext)_localctx).formulas = formulas();

					Variable[] ps = ((PredContext)_localctx).params.vars;
					Formula[] fs = ((PredContext)_localctx).formulas.fs;
					
					((PredContext)_localctx).ip =  new InductivePred((((PredContext)_localctx).ID!=null?((PredContext)_localctx).ID.getText():null), ps, fs);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public Variable[] vars;
		public ParamContext param;
		public ParamsContext params;
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public TerminalNode CM() { return getToken(InductivePredParser.CM, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_params);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				((ParamsContext)_localctx).param = param();

						((ParamsContext)_localctx).vars =  new Variable[1];
						_localctx.vars[0] = ((ParamsContext)_localctx).param.var;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				((ParamsContext)_localctx).param = param();
				setState(69);
				match(CM);
				setState(70);
				((ParamsContext)_localctx).params = params();

						int length = ((ParamsContext)_localctx).params.vars.length + 1;
						((ParamsContext)_localctx).vars =  new Variable[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.vars[i] = ((ParamsContext)_localctx).param.var;
							else _localctx.vars[i] = ((ParamsContext)_localctx).params.vars[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public Variable var;
		public Token ID;
		public TerminalNode ID() { return getToken(InductivePredParser.ID, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			((ParamContext)_localctx).ID = match(ID);

					((ParamContext)_localctx).var =  new Variable((((ParamContext)_localctx).ID!=null?((ParamContext)_localctx).ID.getText():null));
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulasContext extends ParserRuleContext {
		public Formula[] fs;
		public FormulaContext formula;
		public FormulasContext formulas;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public TerminalNode OR() { return getToken(InductivePredParser.OR, 0); }
		public FormulasContext formulas() {
			return getRuleContext(FormulasContext.class,0);
		}
		public FormulasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formulas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterFormulas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitFormulas(this);
		}
	}

	public final FormulasContext formulas() throws RecognitionException {
		FormulasContext _localctx = new FormulasContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formulas);
		try {
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				((FormulasContext)_localctx).formula = formula();

						((FormulasContext)_localctx).fs =  new Formula[1];
						_localctx.fs[0] = ((FormulasContext)_localctx).formula.f;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(81);
				((FormulasContext)_localctx).formula = formula();
				setState(82);
				match(OR);
				setState(83);
				((FormulasContext)_localctx).formulas = formulas();

						int length = ((FormulasContext)_localctx).formulas.fs.length + 1;
						((FormulasContext)_localctx).fs =  new Formula[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) _localctx.fs[i] = ((FormulasContext)_localctx).formula.f;
							else _localctx.fs[i] = ((FormulasContext)_localctx).formulas.fs[i - 1];
						}
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormulaContext extends ParserRuleContext {
		public Formula f;
		public HeapTermsContext heapTerms;
		public PureTermsContext pureTerms;
		public HeapTermsContext heapTerms() {
			return getRuleContext(HeapTermsContext.class,0);
		}
		public PureTermsContext pureTerms() {
			return getRuleContext(PureTermsContext.class,0);
		}
		public TerminalNode AND() { return getToken(InductivePredParser.AND, 0); }
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formula);
		try {
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				((FormulaContext)_localctx).heapTerms = heapTerms();

						HeapFormula hFormula = ((FormulaContext)_localctx).heapTerms.hFormula;
						PureFormula pFormula = new PureFormula();
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				((FormulaContext)_localctx).pureTerms = pureTerms();

						HeapFormula hFormula = new HeapFormula();
						PureFormula pFormula = ((FormulaContext)_localctx).pureTerms.pFormula;
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				((FormulaContext)_localctx).heapTerms = heapTerms();
				setState(95);
				match(AND);
				setState(96);
				((FormulaContext)_localctx).pureTerms = pureTerms();

						HeapFormula hFormula = ((FormulaContext)_localctx).heapTerms.hFormula;
						PureFormula pFormula = ((FormulaContext)_localctx).pureTerms.pFormula;
						((FormulaContext)_localctx).f =  new Formula(hFormula, pFormula);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeapTermsContext extends ParserRuleContext {
		public HeapFormula hFormula;
		public HeapTermContext heapTerm;
		public HeapTermsContext heapTerms;
		public HeapTermContext heapTerm() {
			return getRuleContext(HeapTermContext.class,0);
		}
		public TerminalNode STAR() { return getToken(InductivePredParser.STAR, 0); }
		public HeapTermsContext heapTerms() {
			return getRuleContext(HeapTermsContext.class,0);
		}
		public HeapTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heapTerms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterHeapTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitHeapTerms(this);
		}
	}

	public final HeapTermsContext heapTerms() throws RecognitionException {
		HeapTermsContext _localctx = new HeapTermsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_heapTerms);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				((HeapTermsContext)_localctx).heapTerm = heapTerm();

						((HeapTermsContext)_localctx).hFormula =  new HeapFormula(((HeapTermsContext)_localctx).heapTerm.term);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				((HeapTermsContext)_localctx).heapTerm = heapTerm();
				setState(105);
				match(STAR);
				setState(106);
				((HeapTermsContext)_localctx).heapTerms = heapTerms();

						HeapFormula oldHeapFormula = ((HeapTermsContext)_localctx).heapTerms.hFormula;
						HeapTerm[] oldHeapTerms = oldHeapFormula.getHeapTerms();
						
						int length = oldHeapTerms.length + 1;
						HeapTerm[] newHeapTerms = new HeapTerm[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) newHeapTerms[i] = ((HeapTermsContext)_localctx).heapTerm.term;
							else newHeapTerms[i] = oldHeapTerms[i - 1];
						}
						
						((HeapTermsContext)_localctx).hFormula =  new HeapFormula(newHeapTerms);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeapTermContext extends ParserRuleContext {
		public HeapTerm term;
		public PointToTermContext pointToTerm;
		public InductiveTermContext inductiveTerm;
		public PointToTermContext pointToTerm() {
			return getRuleContext(PointToTermContext.class,0);
		}
		public InductiveTermContext inductiveTerm() {
			return getRuleContext(InductiveTermContext.class,0);
		}
		public HeapTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heapTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterHeapTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitHeapTerm(this);
		}
	}

	public final HeapTermContext heapTerm() throws RecognitionException {
		HeapTermContext _localctx = new HeapTermContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_heapTerm);
		try {
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				((HeapTermContext)_localctx).pointToTerm = pointToTerm();

						((HeapTermContext)_localctx).term =  ((HeapTermContext)_localctx).pointToTerm.term;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(114);
				((HeapTermContext)_localctx).inductiveTerm = inductiveTerm();

						((HeapTermContext)_localctx).term =  ((HeapTermContext)_localctx).inductiveTerm.term;
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PointToTermContext extends ParserRuleContext {
		public HeapTerm term;
		public Token root;
		public Token type;
		public ParamsContext params;
		public TerminalNode PT() { return getToken(InductivePredParser.PT, 0); }
		public TerminalNode LT() { return getToken(InductivePredParser.LT, 0); }
		public TerminalNode GT() { return getToken(InductivePredParser.GT, 0); }
		public List<TerminalNode> ID() { return getTokens(InductivePredParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(InductivePredParser.ID, i);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public PointToTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointToTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPointToTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPointToTerm(this);
		}
	}

	public final PointToTermContext pointToTerm() throws RecognitionException {
		PointToTermContext _localctx = new PointToTermContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_pointToTerm);
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				((PointToTermContext)_localctx).root = match(ID);
				setState(120);
				match(PT);
				setState(121);
				((PointToTermContext)_localctx).type = match(ID);
				setState(122);
				match(LT);
				setState(123);
				match(GT);

						Variable[] vars = new Variable[1];
						vars[0] = new Variable((((PointToTermContext)_localctx).root!=null?((PointToTermContext)_localctx).root.getText():null));
						((PointToTermContext)_localctx).term =  new PointToTerm((((PointToTermContext)_localctx).type!=null?((PointToTermContext)_localctx).type.getText():null), vars);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(125);
				((PointToTermContext)_localctx).root = match(ID);
				setState(126);
				match(PT);
				setState(127);
				((PointToTermContext)_localctx).type = match(ID);
				setState(128);
				match(LT);
				setState(129);
				((PointToTermContext)_localctx).params = params();
				setState(130);
				match(GT);

						int length = ((PointToTermContext)_localctx).params.vars.length + 1;
						
						Variable[] vars = new Variable[length];
						for (int i = 0; i < length; i++) {
							if (i == 0) vars[i] = new Variable((((PointToTermContext)_localctx).root!=null?((PointToTermContext)_localctx).root.getText():null));
							else vars[i] = ((PointToTermContext)_localctx).params.vars[i - 1];
						}
						
						((PointToTermContext)_localctx).term =  new PointToTerm((((PointToTermContext)_localctx).type!=null?((PointToTermContext)_localctx).type.getText():null), vars);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InductiveTermContext extends ParserRuleContext {
		public HeapTerm term;
		public Token ID;
		public ParamsContext params;
		public TerminalNode ID() { return getToken(InductivePredParser.ID, 0); }
		public TerminalNode LB() { return getToken(InductivePredParser.LB, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode RB() { return getToken(InductivePredParser.RB, 0); }
		public InductiveTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inductiveTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterInductiveTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitInductiveTerm(this);
		}
	}

	public final InductiveTermContext inductiveTerm() throws RecognitionException {
		InductiveTermContext _localctx = new InductiveTermContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_inductiveTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			((InductiveTermContext)_localctx).ID = match(ID);
			setState(136);
			match(LB);
			setState(137);
			((InductiveTermContext)_localctx).params = params();
			setState(138);
			match(RB);

					((InductiveTermContext)_localctx).term =  new InductiveTerm((((InductiveTermContext)_localctx).ID!=null?((InductiveTermContext)_localctx).ID.getText():null), ((InductiveTermContext)_localctx).params.vars);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PureTermsContext extends ParserRuleContext {
		public PureFormula pFormula;
		public PureTermContext pureTerm;
		public PureTermsContext pureTerms;
		public PureTermContext pureTerm() {
			return getRuleContext(PureTermContext.class,0);
		}
		public TerminalNode AND() { return getToken(InductivePredParser.AND, 0); }
		public PureTermsContext pureTerms() {
			return getRuleContext(PureTermsContext.class,0);
		}
		public PureTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pureTerms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPureTerms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPureTerms(this);
		}
	}

	public final PureTermsContext pureTerms() throws RecognitionException {
		PureTermsContext _localctx = new PureTermsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pureTerms);
		try {
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				((PureTermsContext)_localctx).pureTerm = pureTerm();

						((PureTermsContext)_localctx).pFormula =  new PureFormula(((PureTermsContext)_localctx).pureTerm.term);
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				((PureTermsContext)_localctx).pureTerm = pureTerm();
				setState(145);
				match(AND);
				setState(146);
				((PureTermsContext)_localctx).pureTerms = pureTerms();

						PureFormula oldPureFormula = ((PureTermsContext)_localctx).pureTerms.pFormula;
						PureTerm[] oldPureTerms = oldPureFormula.getPureTerms();
						
						int length = oldPureTerms.length + 1;
						PureTerm[] newPureTerms = new PureTerm[length];
						
						for (int i = 0; i < length; i++) {
							if (i == 0) newPureTerms[i] = ((PureTermsContext)_localctx).pureTerm.term;
							else newPureTerms[i] = oldPureTerms[i - 1];
						}
						
						((PureTermsContext)_localctx).pFormula =  new PureFormula(newPureTerms);
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PureTermContext extends ParserRuleContext {
		public PureTerm term;
		public ComparisonTermContext comparisonTerm;
		public ComparisonTermContext comparisonTerm() {
			return getRuleContext(ComparisonTermContext.class,0);
		}
		public PureTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pureTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterPureTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitPureTerm(this);
		}
	}

	public final PureTermContext pureTerm() throws RecognitionException {
		PureTermContext _localctx = new PureTermContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pureTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			((PureTermContext)_localctx).comparisonTerm = comparisonTerm();

					((PureTermContext)_localctx).term =  ((PureTermContext)_localctx).comparisonTerm.term;
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparisonTermContext extends ParserRuleContext {
		public PureTerm term;
		public ExpContext exp1;
		public CompContext comp;
		public ExpContext exp2;
		public CompContext comp() {
			return getRuleContext(CompContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public ComparisonTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterComparisonTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitComparisonTerm(this);
		}
	}

	public final ComparisonTermContext comparisonTerm() throws RecognitionException {
		ComparisonTermContext _localctx = new ComparisonTermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comparisonTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			((ComparisonTermContext)_localctx).exp1 = exp(0);
			setState(155);
			((ComparisonTermContext)_localctx).comp = comp();
			setState(156);
			((ComparisonTermContext)_localctx).exp2 = exp(0);

					((ComparisonTermContext)_localctx).term =  new ComparisonTerm(((ComparisonTermContext)_localctx).comp.c, ((ComparisonTermContext)_localctx).exp1.e, ((ComparisonTermContext)_localctx).exp2.e);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompContext extends ParserRuleContext {
		public Comparator c;
		public TerminalNode EQ() { return getToken(InductivePredParser.EQ, 0); }
		public TerminalNode NE() { return getToken(InductivePredParser.NE, 0); }
		public TerminalNode GE() { return getToken(InductivePredParser.GE, 0); }
		public TerminalNode GT() { return getToken(InductivePredParser.GT, 0); }
		public TerminalNode LE() { return getToken(InductivePredParser.LE, 0); }
		public TerminalNode LT() { return getToken(InductivePredParser.LT, 0); }
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterComp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitComp(this);
		}
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_comp);
		try {
			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				match(EQ);

						((CompContext)_localctx).c =  Comparator.EQ;
					
				}
				break;
			case NE:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(NE);

						((CompContext)_localctx).c =  Comparator.NE;
					
				}
				break;
			case GE:
				enterOuterAlt(_localctx, 3);
				{
				setState(163);
				match(GE);

						((CompContext)_localctx).c =  Comparator.GE;
					
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
				match(GT);

						((CompContext)_localctx).c =  Comparator.GT;
					
				}
				break;
			case LE:
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				match(LE);

						((CompContext)_localctx).c =  Comparator.LE;
					
				}
				break;
			case LT:
				enterOuterAlt(_localctx, 6);
				{
				setState(169);
				match(LT);

						((CompContext)_localctx).c =  Comparator.LT;
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Expression e;
		public ExpContext exp1;
		public TerContext ter;
		public TerContext ter() {
			return getRuleContext(TerContext.class,0);
		}
		public TerminalNode NULL() { return getToken(InductivePredParser.NULL, 0); }
		public TerminalNode PLUS() { return getToken(InductivePredParser.PLUS, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(InductivePredParser.MINUS, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(174);
				((ExpContext)_localctx).ter = ter(0);

						((ExpContext)_localctx).e =  ((ExpContext)_localctx).ter.e;
					
				}
				break;
			case 2:
				{
				setState(177);
				match(NULL);
					
						((ExpContext)_localctx).e =  NullExpression.getInstance();
					
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(193);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(191);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(181);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(182);
						match(PLUS);
						setState(183);
						((ExpContext)_localctx).ter = ter(0);

						          		Expression exp1 = ((ExpContext)_localctx).exp1.e;
						          		Expression exp2 = ((ExpContext)_localctx).ter.e;
						          		
						          		((ExpContext)_localctx).e =  new BinaryExpression(Operator.PLUS, exp1, exp2);
						          	
						}
						break;
					case 2:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(186);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(187);
						match(MINUS);
						setState(188);
						((ExpContext)_localctx).ter = ter(0);

						          		Expression exp1 = ((ExpContext)_localctx).exp1.e;
						          		Expression exp2 = ((ExpContext)_localctx).ter.e;
						          		
						          		((ExpContext)_localctx).e =  new BinaryExpression(Operator.MINUS, exp1, exp2);
						          	
						}
						break;
					}
					} 
				}
				setState(195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TerContext extends ParserRuleContext {
		public Expression e;
		public TerContext ter1;
		public LitContext lit;
		public LitContext lit() {
			return getRuleContext(LitContext.class,0);
		}
		public TerminalNode MUL() { return getToken(InductivePredParser.MUL, 0); }
		public TerContext ter() {
			return getRuleContext(TerContext.class,0);
		}
		public TerminalNode DIV() { return getToken(InductivePredParser.DIV, 0); }
		public TerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterTer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitTer(this);
		}
	}

	public final TerContext ter() throws RecognitionException {
		return ter(0);
	}

	private TerContext ter(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TerContext _localctx = new TerContext(_ctx, _parentState);
		TerContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_ter, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(197);
			((TerContext)_localctx).lit = lit();

					((TerContext)_localctx).e =  ((TerContext)_localctx).lit.e;
				
			}
			_ctx.stop = _input.LT(-1);
			setState(212);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(210);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new TerContext(_parentctx, _parentState);
						_localctx.ter1 = _prevctx;
						_localctx.ter1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_ter);
						setState(200);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(201);
						match(MUL);
						setState(202);
						((TerContext)_localctx).lit = lit();

						          		Expression exp1 = ((TerContext)_localctx).ter1.e;
						          		Expression exp2 = ((TerContext)_localctx).lit.e;
						          		
						          		((TerContext)_localctx).e =  new BinaryExpression(Operator.MUL, exp1, exp2);
						          	
						}
						break;
					case 2:
						{
						_localctx = new TerContext(_parentctx, _parentState);
						_localctx.ter1 = _prevctx;
						_localctx.ter1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_ter);
						setState(205);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(206);
						match(DIV);
						setState(207);
						((TerContext)_localctx).lit = lit();

						          		Expression exp1 = ((TerContext)_localctx).ter1.e;
						          		Expression exp2 = ((TerContext)_localctx).lit.e;
						          	
						          		((TerContext)_localctx).e =  new BinaryExpression(Operator.DIV, exp1, exp2);
						          	
						}
						break;
					}
					} 
				}
				setState(214);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LitContext extends ParserRuleContext {
		public Expression e;
		public Token ID;
		public Token INT;
		public Token DOUBLE;
		public ExpContext exp;
		public TerminalNode ID() { return getToken(InductivePredParser.ID, 0); }
		public TerminalNode INT() { return getToken(InductivePredParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(InductivePredParser.DOUBLE, 0); }
		public TerminalNode LB() { return getToken(InductivePredParser.LB, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RB() { return getToken(InductivePredParser.RB, 0); }
		public LitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).enterLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InductivePredListener ) ((InductivePredListener)listener).exitLit(this);
		}
	}

	public final LitContext lit() throws RecognitionException {
		LitContext _localctx = new LitContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_lit);
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				((LitContext)_localctx).ID = match(ID);

						((LitContext)_localctx).e =  new Variable((((LitContext)_localctx).ID!=null?((LitContext)_localctx).ID.getText():null));
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				((LitContext)_localctx).INT = match(INT);

						((LitContext)_localctx).e =  new LiteralExpression((((LitContext)_localctx).INT!=null?((LitContext)_localctx).INT.getText():null));
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(220);
				((LitContext)_localctx).DOUBLE = match(DOUBLE);

						((LitContext)_localctx).e =  new LiteralExpression((((LitContext)_localctx).DOUBLE!=null?((LitContext)_localctx).DOUBLE.getText():null));
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(222);
				match(LB);
				setState(223);
				((LitContext)_localctx).exp = exp(0);
				setState(224);
				match(RB);

						((LitContext)_localctx).e =  ((LitContext)_localctx).exp.e;
					
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 16:
			return ter_sempred((TerContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean ter_sempred(TerContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00e8\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2.\n\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\5\39\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7Y\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bf\n\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tp\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\nx\n"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\5\13\u0088\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\5\r\u0098\n\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00ae\n\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21\u00b6\n\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\7\21\u00c2\n\21\f\21\16\21\u00c5\13\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u00d5"+
		"\n\22\f\22\16\22\u00d8\13\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\5\23\u00e6\n\23\3\23\2\4 \"\24\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$\2\2\2\u00ed\2-\3\2\2\2\48\3\2\2\2\6:\3\2\2\2\b"+
		"K\3\2\2\2\nM\3\2\2\2\fX\3\2\2\2\16e\3\2\2\2\20o\3\2\2\2\22w\3\2\2\2\24"+
		"\u0087\3\2\2\2\26\u0089\3\2\2\2\30\u0097\3\2\2\2\32\u0099\3\2\2\2\34\u009c"+
		"\3\2\2\2\36\u00ad\3\2\2\2 \u00b5\3\2\2\2\"\u00c6\3\2\2\2$\u00e5\3\2\2"+
		"\2&\'\5\6\4\2\'(\b\2\1\2(.\3\2\2\2)*\5\6\4\2*+\5\4\3\2+,\b\2\1\2,.\3\2"+
		"\2\2-&\3\2\2\2-)\3\2\2\2.\3\3\2\2\2/\60\7\23\2\2\60\61\5\6\4\2\61\62\b"+
		"\3\1\2\629\3\2\2\2\63\64\7\23\2\2\64\65\5\6\4\2\65\66\5\4\3\2\66\67\b"+
		"\3\1\2\679\3\2\2\28/\3\2\2\28\63\3\2\2\29\5\3\2\2\2:;\7\3\2\2;<\7\30\2"+
		"\2<=\7\20\2\2=>\5\b\5\2>?\7\21\2\2?@\7\5\2\2@A\5\f\7\2AB\b\4\1\2B\7\3"+
		"\2\2\2CD\5\n\6\2DE\b\5\1\2EL\3\2\2\2FG\5\n\6\2GH\7\22\2\2HI\5\b\5\2IJ"+
		"\b\5\1\2JL\3\2\2\2KC\3\2\2\2KF\3\2\2\2L\t\3\2\2\2MN\7\30\2\2NO\b\6\1\2"+
		"O\13\3\2\2\2PQ\5\16\b\2QR\b\7\1\2RY\3\2\2\2ST\5\16\b\2TU\7\24\2\2UV\5"+
		"\f\7\2VW\b\7\1\2WY\3\2\2\2XP\3\2\2\2XS\3\2\2\2Y\r\3\2\2\2Z[\5\20\t\2["+
		"\\\b\b\1\2\\f\3\2\2\2]^\5\30\r\2^_\b\b\1\2_f\3\2\2\2`a\5\20\t\2ab\7\25"+
		"\2\2bc\5\30\r\2cd\b\b\1\2df\3\2\2\2eZ\3\2\2\2e]\3\2\2\2e`\3\2\2\2f\17"+
		"\3\2\2\2gh\5\22\n\2hi\b\t\1\2ip\3\2\2\2jk\5\22\n\2kl\7\27\2\2lm\5\20\t"+
		"\2mn\b\t\1\2np\3\2\2\2og\3\2\2\2oj\3\2\2\2p\21\3\2\2\2qr\5\24\13\2rs\b"+
		"\n\1\2sx\3\2\2\2tu\5\26\f\2uv\b\n\1\2vx\3\2\2\2wq\3\2\2\2wt\3\2\2\2x\23"+
		"\3\2\2\2yz\7\30\2\2z{\7\26\2\2{|\7\30\2\2|}\7\13\2\2}~\7\t\2\2~\u0088"+
		"\b\13\1\2\177\u0080\7\30\2\2\u0080\u0081\7\26\2\2\u0081\u0082\7\30\2\2"+
		"\u0082\u0083\7\13\2\2\u0083\u0084\5\b\5\2\u0084\u0085\7\t\2\2\u0085\u0086"+
		"\b\13\1\2\u0086\u0088\3\2\2\2\u0087y\3\2\2\2\u0087\177\3\2\2\2\u0088\25"+
		"\3\2\2\2\u0089\u008a\7\30\2\2\u008a\u008b\7\20\2\2\u008b\u008c\5\b\5\2"+
		"\u008c\u008d\7\21\2\2\u008d\u008e\b\f\1\2\u008e\27\3\2\2\2\u008f\u0090"+
		"\5\32\16\2\u0090\u0091\b\r\1\2\u0091\u0098\3\2\2\2\u0092\u0093\5\32\16"+
		"\2\u0093\u0094\7\25\2\2\u0094\u0095\5\30\r\2\u0095\u0096\b\r\1\2\u0096"+
		"\u0098\3\2\2\2\u0097\u008f\3\2\2\2\u0097\u0092\3\2\2\2\u0098\31\3\2\2"+
		"\2\u0099\u009a\5\34\17\2\u009a\u009b\b\16\1\2\u009b\33\3\2\2\2\u009c\u009d"+
		"\5 \21\2\u009d\u009e\5\36\20\2\u009e\u009f\5 \21\2\u009f\u00a0\b\17\1"+
		"\2\u00a0\35\3\2\2\2\u00a1\u00a2\7\6\2\2\u00a2\u00ae\b\20\1\2\u00a3\u00a4"+
		"\7\7\2\2\u00a4\u00ae\b\20\1\2\u00a5\u00a6\7\b\2\2\u00a6\u00ae\b\20\1\2"+
		"\u00a7\u00a8\7\t\2\2\u00a8\u00ae\b\20\1\2\u00a9\u00aa\7\n\2\2\u00aa\u00ae"+
		"\b\20\1\2\u00ab\u00ac\7\13\2\2\u00ac\u00ae\b\20\1\2\u00ad\u00a1\3\2\2"+
		"\2\u00ad\u00a3\3\2\2\2\u00ad\u00a5\3\2\2\2\u00ad\u00a7\3\2\2\2\u00ad\u00a9"+
		"\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\37\3\2\2\2\u00af\u00b0\b\21\1\2\u00b0"+
		"\u00b1\5\"\22\2\u00b1\u00b2\b\21\1\2\u00b2\u00b6\3\2\2\2\u00b3\u00b4\7"+
		"\4\2\2\u00b4\u00b6\b\21\1\2\u00b5\u00af\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6"+
		"\u00c3\3\2\2\2\u00b7\u00b8\f\6\2\2\u00b8\u00b9\7\f\2\2\u00b9\u00ba\5\""+
		"\22\2\u00ba\u00bb\b\21\1\2\u00bb\u00c2\3\2\2\2\u00bc\u00bd\f\5\2\2\u00bd"+
		"\u00be\7\r\2\2\u00be\u00bf\5\"\22\2\u00bf\u00c0\b\21\1\2\u00c0\u00c2\3"+
		"\2\2\2\u00c1\u00b7\3\2\2\2\u00c1\u00bc\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4!\3\2\2\2\u00c5\u00c3\3\2\2\2"+
		"\u00c6\u00c7\b\22\1\2\u00c7\u00c8\5$\23\2\u00c8\u00c9\b\22\1\2\u00c9\u00d6"+
		"\3\2\2\2\u00ca\u00cb\f\5\2\2\u00cb\u00cc\7\16\2\2\u00cc\u00cd\5$\23\2"+
		"\u00cd\u00ce\b\22\1\2\u00ce\u00d5\3\2\2\2\u00cf\u00d0\f\4\2\2\u00d0\u00d1"+
		"\7\17\2\2\u00d1\u00d2\5$\23\2\u00d2\u00d3\b\22\1\2\u00d3\u00d5\3\2\2\2"+
		"\u00d4\u00ca\3\2\2\2\u00d4\u00cf\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4"+
		"\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7#\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9"+
		"\u00e6\3\2\2\2\u00da\u00db\7\30\2\2\u00db\u00e6\b\23\1\2\u00dc\u00dd\7"+
		"\31\2\2\u00dd\u00e6\b\23\1\2\u00de\u00df\7\32\2\2\u00df\u00e6\b\23\1\2"+
		"\u00e0\u00e1\7\20\2\2\u00e1\u00e2\5 \21\2\u00e2\u00e3\7\21\2\2\u00e3\u00e4"+
		"\b\23\1\2\u00e4\u00e6\3\2\2\2\u00e5\u00d9\3\2\2\2\u00e5\u00da\3\2\2\2"+
		"\u00e5\u00dc\3\2\2\2\u00e5\u00de\3\2\2\2\u00e5\u00e0\3\2\2\2\u00e6%\3"+
		"\2\2\2\22-8KXeow\u0087\u0097\u00ad\u00b5\u00c1\u00c3\u00d4\u00d6\u00e5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}