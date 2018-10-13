package wo.bml.components;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXBooleanSelector;

public class BMLBooleanSelector extends ERXBooleanSelector {
	
	/**
	 * A custom boolean selector (defaults to "Yes", "No", and "All") for use as, for instance, a boolean search filter.
	 * 
	 * @author jtayler
	 *
	 * @binding same as Wonder componenet
	 */
	
	private static final long serialVersionUID = 1L;

	public BMLBooleanSelector(WOContext context) {
        super(context);
    }
	
	public String uiMode() {
		return "radio";
	}
}