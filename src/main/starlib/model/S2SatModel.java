package starlib.model;

/*
 * This class is used to process model returned by the S2SAT solver
 */
public class S2SatModel {

	public static String standarizeModel(String model) {
		String ret = model;

		ret = ret.substring(8, model.length());
		ret = ret.replaceAll("[\\[\\]]", "");

		if (ret.endsWith("@M")) {
			ret = ret.replaceAll("@M,", " *");
			ret = ret.replaceAll("@M", "");
		} else {
			ret = ret.replaceAll("@M,", " *");
			ret = ret.replaceAll("@M", " &");
		}

		return ret.substring(1);
	}
}
