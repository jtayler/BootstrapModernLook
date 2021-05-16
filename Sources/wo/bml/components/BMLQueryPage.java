package wo.bml.components;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.ERD2WUtilities;

import er.extensions.foundation.ERXValueUtilities;
import er.modern.look.pages.ERMODQueryPage;
import er.modern.look.pages.ERMODQueryPage.Keys;

public class BMLQueryPage extends ERMODQueryPage {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLQueryPage(WOContext context) {
        super(context);
    }
	
	public WOComponent cancelAction() {
		//WOComponent page = super.cancelAction();
		return returnPage;
	}

}