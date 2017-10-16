package starlib.jpf;

import java.util.ArrayList;
import java.util.List;

import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.LocalVarInfo;
import gov.nasa.jpf.vm.StackFrame;
import gov.nasa.jpf.vm.ThreadInfo;
import starlib.formula.Variable;

public class PathFinderUtils {
	
	public static boolean isInstanceVariable(Variable var, FieldInfo[] fields) {
		for (FieldInfo field : fields) {
			String fname = "this_" + field.getName();
			if (fname.equals(var.toString()))
				return true;
		}

		return false;
	}

	public static boolean isClassVariable(Variable var, String clsName, FieldInfo[] fields) {
		for (FieldInfo field : fields) {
			String fname = clsName + "_" + field.getName();
			if (fname.equals(var.toString()))
				return true;
		}

		return false;
	}
	
	public static String standarizeName(Variable var, String objName, String clsName,
			FieldInfo[] insFields, FieldInfo[] staFields) {
		String name = var.getName();
		
		if (isInstanceVariable(var,insFields))
			name = name.replace("this_", objName + ".");
		else if (isClassVariable(var,clsName, staFields))
			name = name.replace(clsName + "_", clsName + ".");
		
		return name;
	}
	
	public static void printErrorDetails(Search search) {
		System.out.println(search.getLastError().getDetails() + "\n");
		ThreadInfo[] threads = search.getVM().getThreadList().getThreads();
		for (ThreadInfo ti : threads) {
			for (StackFrame frame : ti) {
				if (!frame.isDirectCallFrame()) {
					System.out.println("\t" + frame.getStackTraceInfo() + "\n");
				}
			}
		}
	}
	
	/*
	public static void generate() {
		List<Variable> knownTypeVars = new ArrayList<Variable>();
		
		for (LocalVarInfo arg : args) {
			if (!arg.getName().equals("this")) {
				String name = arg.getName();
				String type = standarizeType(arg.getType());
				
				knownTypeVars.add(new Variable(name, type));
			}
		}
		
		for (FieldInfo field : insFields) {
			String name = "this_" + field.getName();
			String type = standarizeType(field.getType());
				
			knownTypeVars.add(new Variable(name, type));
		}
		
		for (FieldInfo field : staFields) {
			String name = clsName + "_" + field.getName();
			String type = standarizeType(field.getType());
				
			knownTypeVars.add(new Variable(name, type));
		}
	}
	//*/
}
