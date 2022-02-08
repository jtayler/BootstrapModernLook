package wo.bml.components;

import com.webobjects.appserver.WOContext;

import er.directtoweb.components.ERD2WStatelessComponent;

public class BMLInputGroupWrapper extends ERD2WStatelessComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLInputGroupWrapper(WOContext context) {
        super(context);
    }

	/**
	 * @return the spanID
	 */
	public String spanID() {
		String spanID = valueForKeyPath("d2wContext.id") + "_Span";
		return spanID;
	}

}