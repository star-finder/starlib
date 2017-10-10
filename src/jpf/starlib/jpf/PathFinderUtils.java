package starlib.jpf;

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
