package starlib.jpf;

import java.util.List;

import gov.nasa.jpf.vm.FieldInfo;
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
	
	public static void genDefaultVars(List<Variable> knownTypeVars, List<Variable> initVars,
			StringBuffer test, String objName, String clsName,
			FieldInfo[] insFields, FieldInfo[] staFields) {
		if (knownTypeVars.size() == initVars.size())
			return;
		
		for (Variable var : knownTypeVars) {
			String name = var.getName();
			String type = var.getType();
			
			if (name.startsWith("Anon_")) continue;
				
			if (!initVars.contains(var)) {
				if (var.isPrim()) {
					String val = type.equals("boolean") ? "false" : "0";
					
					if (isInstanceVariable(var,insFields))
						test.append("\t\t" + name.replace("this_", objName + ".") + " = " + val + ";\n");
					else if (isClassVariable(var,clsName, staFields))
						test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = " + val + ";\n");
					else
						test.append("\t\t" + type + " " + name + " = " + val + ";\n");
				} else {
					if (isInstanceVariable(var,insFields))
						test.append("\t\t" + name.replace("this_", objName + ".") + " = null;\n");
					else if (isClassVariable(var,clsName, staFields))
						test.append("\t\t" + name.replace(clsName + "_", clsName + ".") + " = null;\n");
					else
						test.append("\t\t" + type + " " + name + " = null;\n");
				}
			}
		}
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
}
