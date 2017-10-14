package starlib.jpf;

import gov.nasa.jpf.GenericProperty;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.VM;

public class NoErrorProperty extends GenericProperty {

	boolean triggered = false;
	String description = null;

	public NoErrorProperty(String description) {
		this.description = description;
	}

	@Override
	public NoErrorProperty clone() throws CloneNotSupportedException {
		return new NoErrorProperty(description);
	}

	@Override
	public boolean check(Search search, VM vm) {
		// This method returns true if the property is NOT violated
		return triggered;
	}

	@Override
	public String getErrorMessage() {
		return description;
	}

	@Override
	public void reset() {
		triggered = true;
	}
}
