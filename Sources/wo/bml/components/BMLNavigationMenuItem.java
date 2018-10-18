package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

import er.extensions.appserver.navigation.ERXNavigationMenu;
import er.extensions.appserver.navigation.ERXNavigationMenuItem;

public class BMLNavigationMenuItem extends ERXNavigationMenuItem {
    /**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	public BMLNavigationMenuItem(WOContext context) {
        super(context);
    }
	
    public String linkClass() {
        if(level() == 0) {
            return "";
        }
        BMLNavigationMenu parent = (BMLNavigationMenu) parent();
        boolean isSelected = isSelected();
        boolean isDisabled = isDisabled();
        boolean show = parent.showLevel2();
        String linkstring = "";
        if (show) {
            linkstring = "nav-link nav-link" + level() + (isSelected ? " active" : (isDisabled ? " disabled" : "")) + (items().count() == 0 ? "" : " dropdown-toggle");
        } else {
            linkstring = "nav-link nav-link" + level() + (isSelected ? " active" : (isDisabled ? " disabled" : ""));
        }
        return linkstring;
    }

    public NSArray<?> items() {
    	NSArray<?> items = ((BMLNavigationMenu) parent()).level2Items();
        return items;
    }

	
}