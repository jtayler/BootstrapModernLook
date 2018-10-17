package wo.bml.components;

import com.webobjects.appserver.WOContext;

import er.directtoweb.components.strings.ERD2WEditLargeString;
import er.extensions.foundation.ERXStringUtilities;

public class BMLEditLargeString extends ERD2WEditLargeString {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLEditLargeString(WOContext context) {
        super(context);
    }
	
	public String textareaID() {
		return ERXStringUtilities.safeIdentifierName("textarea");
	}
	

}