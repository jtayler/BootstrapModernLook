package wo.bml.components;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

import er.extensions.foundation.ERXValueUtilities;
import er.modern.directtoweb.components.ERMDWizardDetailedBanner;

public class BMLWizardDetailedBanner extends ERMDWizardDetailedBanner {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLWizardDetailedBanner(WOContext context) {
        super(context);
    }
	
    public boolean dontSubmitForm() {
    	return !ERXValueUtilities.booleanValue(valueForBinding("useFormSubmit"));
    }

    public WOComponent switchSubmitTab() {
        Integer selectedIndex = 1;

        return ((BMLWizardCreationPage) parent()).previousStep();
    }

	public String listItemClass() {
		String result = "";
		if (tabItem != null && tabItem.equals(currentTab())) {
			result = "nav-item active ";
		} 
		if (index == tabSectionsContents().count() - 1) {
			result = "nav-item last-item " + result;
		}
		if (index == 0) {
			result = "nav-item first-item " + result;
		}
		return result.equals("") ? null : result;
	}
	
	public String listLinkClass() {
		String result = "nav-link";
		if (tabItem != null && tabItem.equals(currentTab())) {
			result = result + " active ";
		} else {
			result = result + " disabled ";
		}
		if (index == tabSectionsContents().count() - 1) {
			result = result + " last-item";
		}
		if (index == 0) {
			result = result + " first-item";
		}
		return result;
	}

}