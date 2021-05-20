package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.NextPageDelegate;
import com.webobjects.directtoweb.QueryPageInterface;

import er.modern.look.pages.ERMODListPage;

public class BMLListPage extends ERMODListPage implements NextPageDelegate {
    public BMLListPage(WOContext context) {
        super(context);
    }
    
    public WOComponent nextPage = null;
    
	public WOActionResults smartCreateAction() {
		nextPage = context().page();
		 EditPageInterface nextPage = D2W.factory().editPageForNewObjectWithEntityNamed(d2wContext().entity().name(), session());
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}

	public WOActionResults smartFindAction() {
		 QueryPageInterface nextPage = D2W.factory().queryPageForEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}

	@Override
	public WOComponent nextPage(WOComponent arg0) {
		// TODO Auto-generated method stub
		return nextPage;
	}

}