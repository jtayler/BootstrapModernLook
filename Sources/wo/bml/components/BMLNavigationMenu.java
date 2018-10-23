package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOSession;
import com.webobjects.foundation.NSArray;

import er.extensions.appserver.navigation.ERXNavigationItem;
import er.extensions.appserver.navigation.ERXNavigationManager;
import er.extensions.appserver.navigation.ERXNavigationMenu;
import er.extensions.appserver.navigation.ERXNavigationMenuItem;
import er.extensions.appserver.navigation.ERXNavigationState;

public class BMLNavigationMenu extends ERXNavigationMenu {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    protected Boolean _isSelected;
    public ERXNavigationItem a2NavigationItem;
    String _sessionNavigationState;
	public BMLNavigationMenu(WOContext context) {
        super(context);
    }
	
	@Override
	public void awake() {
		super.awake();
		_sessionNavigationState = null;
	}
	
	public NSArray<?> itemsForLevel2() {
        NSArray<?> items = itemsForLevelAt(2);

		return items;
	}

	public NSArray<?> itemsForLevel3() {
        NSArray<?> items = itemsForLevelAt(3);

		return items;
	}

	public NSArray<?> itemsForLevelAt(Integer level) {
		String name = aNavigationItem.name();
		NSArray<?> navigationState = NSArray.componentsSeparatedByString(name, ".");
		
		sessionNavigationState();
		
        navigationState().setState(navigationState);                    
        NSArray<?> items = itemsForLevel(level);

		return items;
	}
	
    public boolean isSelectedState(ERXNavigationItem item) {
        if (item != null) {
    		String name = item.name();
//    		NSArray<?> navigationState = NSArray.componentsSeparatedByString(name, ".");
//            navigationState().setState(navigationState);                    
            _isSelected = sessionNavigationState() != null && sessionNavigationState().contains(name) ? Boolean.TRUE : Boolean.FALSE;
        } else {
        	return false;
        }
        return _isSelected.booleanValue();
    }
	
	public String sessionNavigationState() {
		if (_sessionNavigationState == null) {
			ERXNavigationState nstate = ERXNavigationManager.manager().navigationStateForSession(session());
			_sessionNavigationState = nstate + "";
		}

		return _sessionNavigationState;
	}

}