package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WEditBoolean;

import er.directtoweb.components.bool.ERD2WCustomEditBoolean;
import er.extensions.foundation.ERXStringUtilities;

public class BMLBooleanSelector extends D2WEditBoolean {
	
	/**
	 * A custom boolean selector (defaults to "Yes", "No", and "All") for use as, for instance, a boolean search filter.
	 * 
	 * @author jtayler
	 *
	 * @binding same as Wonder componenet
	 */
	
	private static final long serialVersionUID = 1L;
	private String _itemValue;

	public BMLBooleanSelector(WOContext context) {
        super(context);
    }
	
	public String buttonID() {
		return ERXStringUtilities.safeIdentifierName("button" + _itemValue);
	}
	
	public String uiMode() {
		return "radio";
	}
}