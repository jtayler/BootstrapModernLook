package wo.bml.components;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.foundation.ERXValueUtilities;
import er.modern.look.pages.ERMODWizardCreationPage;
import er.modern.look.pages.ERMODWizardCreationPage.Keys;

public class BMLWizardCreationPage extends ERMODWizardCreationPage {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLWizardCreationPage(WOContext context) {
        super(context);
    }
	
    public WOComponent stepAt(Integer step) {
        // if we had an error message and are going back, we don't want the message
        // to show up on the previous page; the error message will reappear
        // when the user gets back to the initial page
        errorMessages = new NSMutableDictionary<Object, Object>();
        setCurrentTab(tabSectionsContents().objectAtIndex(step));
        return null;
    }
    
    public boolean showBanner() {
    	boolean should = ERXValueUtilities.booleanValue(d2wContext().valueForKey("showBanner"));
        return (should && (tabSectionsContents().count() > 1));
    }

	

}