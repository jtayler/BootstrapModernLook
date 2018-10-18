package wo.bml.components;

import com.webobjects.appserver.WOContext;

import er.directtoweb.components.relationships.ERD2WQueryToManyField;

public class BMLQueryToManyField extends ERD2WQueryToManyField {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLQueryToManyField(WOContext context) {
        super(context);
    }
}