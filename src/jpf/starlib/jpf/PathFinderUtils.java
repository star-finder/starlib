package starlib.jpf;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.ClassInfo;
import gov.nasa.jpf.vm.FieldInfo;
import gov.nasa.jpf.vm.LocalVarInfo;
import gov.nasa.jpf.vm.MethodInfo;
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
	
	public static String standardizeName(Variable var, String objName, String clsName,
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
	
	public static HashMap<String, String> initTypeVarMap(ClassInfo ci, MethodInfo mi) {
		String clsName = ci.getSimpleName();
		LocalVarInfo[] args = mi.getArgumentLocalVars();
		FieldInfo[] insFields = ci.getInstanceFields();
		FieldInfo[] staFields = ci.getDeclaredStaticFields();
		HashMap<String, String> knownTypeVars = new HashMap<String, String>();
		
		for (LocalVarInfo arg : args) {
			if (!arg.getName().equals("this")) {				
				String name = arg.getName();
				String type = toJavaType(arg.getType());
				
				knownTypeVars.put(name, type);
			}
		}
		
		for (FieldInfo field : insFields) {
			String name = "this_" + field.getName();
			String type = toJavaType(field.getType());
				
			knownTypeVars.put(name, type);
		}
		
		for (FieldInfo field : staFields) {
			String name = clsName + "_" + field.getName();
			String type = toS2SATType(field.getType());
				
			knownTypeVars.put(name, type);
		}
		return knownTypeVars;
	}
	
	public static String toS2SATType(String type) {
		if (type.contains("."))
			type = type.replaceAll("\\.", "_");
		
		if (type.contains("$"))
			type = type.replaceAll("$", "__");
		
		return type;
	}
	
	public static String toJavaType(String type) {
		if (type.contains("__"))
			type = type.replaceAll("__", "$$");
		
		if (type.contains("_"))
			type = type.replaceAll("_", ".");
		
		return type;
	}
	
//	public static String standardizeType(String type) {
//		if (type.contains("."))
//			type = type.substring(type.lastIndexOf('.') + 1);
//		
//		if (type.contains("$"))
//			type = type.substring(type.lastIndexOf('$') + 1);
//		
//		return type;
//	}
	
	public static void writeToFile(StringBuffer test, Config conf, ClassInfo ci, MethodInfo mi) {
		String fileName = ci.getSimpleName() + "_" + mi.getName() + "1.java";
		String path = conf.getProperty("star.test_path");
		// create the directory if it does not exist
		try {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			} else{
				// FileUtils.cleanDirectory(dir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			PrintWriter pw = new PrintWriter(path + "/" + fileName, "UTF-8");
			pw.println(test.toString());
			
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
