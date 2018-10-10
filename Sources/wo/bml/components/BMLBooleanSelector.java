package wo.bml.components;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXBooleanSelector;

public class BMLBooleanSelector extends ERXBooleanSelector {
	
	/**
	 * A custom boolean selector (defaults to "Yes", "No", and "All") for use as, for instance, a boolean search filter.
	 * 
	 * @author jtayler
	 *
	 * @binding yesString the string to show for the "Yes" option
	 * @binding noString the string to show for the "No" option
	 * @binding noSelectionString the string to show for the "All" option
	 * @binding selection the selected value
	 */
	
	private static final long serialVersionUID = 1L;

	public BMLBooleanSelector(WOContext context) {
        super(context);
    }
	
	public String uiMode() {
		return "radio";
	}
}