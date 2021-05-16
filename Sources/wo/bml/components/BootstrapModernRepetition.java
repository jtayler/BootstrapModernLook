package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.NextPageDelegate;
import com.webobjects.directtoweb.QueryPageInterface;

import er.modern.directtoweb.components.repetitions.ERMDSimpleListPageRepetition;

public class BootstrapModernRepetition extends ERMDSimpleListPageRepetition implements NextPageDelegate {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BootstrapModernRepetition(WOContext context) {
        super(context);
    }
	
	public WOComponent nextPage;
	
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
		return nextPage;
	}

}