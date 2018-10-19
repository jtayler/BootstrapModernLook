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
	
	public boolean isSelectedState = true;
	
	public BMLNavigationMenuItem(WOContext context) {
        super(context);
    }
	
    public String linkClass() {
        if(level() == 0) {
            return "";
        }
        BMLNavigationMenu parent = (BMLNavigationMenu) parent();
        boolean isSelected = parent.isSelectedState(this.navigationItem());
        boolean isDisabled = isDisabled();
        boolean show = parent.showLevel2();
        String linkstring = "";
        if (show) {
            linkstring = "dropdown-toggle nav-link nav-link-" + level() + (isSelected ? " active" : (isDisabled ? " disabled" : ""));
        } else {
            linkstring = "nav-link nav-link-" + level() + (isSelected ? " active" : (isDisabled ? " disabled" : ""));
        }
        return linkstring;
    }


	
}