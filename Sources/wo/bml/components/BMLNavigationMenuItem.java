package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import er.extensions.appserver.navigation.ERXNavigationMenuItem;

public class BMLNavigationMenuItem extends ERXNavigationMenuItem {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NSArray items;
	
	public BMLNavigationMenuItem(WOContext context) {
        super(context);
    }
	
}