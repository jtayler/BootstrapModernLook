package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;

import er.extensions.appserver.navigation.ERXNavigationItem;
import er.extensions.appserver.navigation.ERXNavigationMenu;
import er.extensions.appserver.navigation.ERXNavigationState;

public class BMLNavigationMenu extends ERXNavigationMenu {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ERXNavigationItem a2NavigationItem;

	public BMLNavigationMenu(WOContext context) {
        super(context);
    }
	
	public NSArray<?> itemsForLevel2() {
		String name = aNavigationItem.name();
		ERXNavigationState state = navigationState();
		NSArray<?> navigationState = NSArray.componentsSeparatedByString(name, ".");
        navigationState().setState(navigationState);                    
        NSArray items = itemsForLevel(2);
        navigationState().setState(state.state());                 

		return items;
	}
}