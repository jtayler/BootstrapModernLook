package wo.bml.components;

import com.webobjects.appserver.WOContext;

import er.modern.directtoweb.components.ERMDWizardDetailedBanner;

public class BMLWizardDetailedBanner extends ERMDWizardDetailedBanner {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLWizardDetailedBanner(WOContext context) {
        super(context);
    }
	
	public String listItemClass() {
		String result = "";
		if (tabItem != null && tabItem.equals(currentTab())) {
			result = "nav-item ";
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
		String result = "";
		if (tabItem != null && tabItem.equals(currentTab())) {
			result = "nav-link active ";
		} 
		if (index == tabSectionsContents().count() - 1) {
			result = "nav-link last-item " + result;
		}
		if (index == 0) {
			result = "nav-link first-item " + result;
		}
		return result.equals("") ? null : result;
	}

}