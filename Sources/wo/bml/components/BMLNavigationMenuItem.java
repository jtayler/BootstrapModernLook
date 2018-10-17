package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSDictionary;

import er.extensions.appserver.navigation.ERXNavigationItem;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.extensions.appserver.navigation.ERXNavigationMenuItem;
import er.extensions.foundation.ERXProperties;
import er.extensions.localization.ERXLocalizer;

public class BMLNavigationMenuItem extends ERXNavigationMenuItem {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLNavigationMenuItem(WOContext context) {
        super(context);
    }
	
}