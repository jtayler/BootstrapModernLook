package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.directtoweb.NextPageDelegate;

import er.extensions.eof.ERXGenericRecord;
import er.modern.look.pages.ERMODTabInspectPage;

public class BMLTabInspectPage extends ERMODTabInspectPage implements NextPageDelegate {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	WOComponent nextPage = context().page();

	public BMLTabInspectPage(WOContext context) {
        super(context);
    }
	
	public WOActionResults smartEditAction() {
		nextPage = context().page();
		 EditPageInterface nextPage = D2W.factory().editPageForEntityNamed(d2wContext().entity().name(), session());
		 ERXGenericRecord object = (ERXGenericRecord) object();
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	public WOActionResults smartInspectAction(ERXGenericRecord object) {
		 InspectPageInterface nextPage = D2W.factory().inspectPageForEntityNamed(d2wContext().entity().name(), session());
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	
	public WOActionResults smartCreateAction() {
		nextPage = context().page();
		 EditPageInterface nextPage = D2W.factory().editPageForNewObjectWithEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPage(context().page());
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}

	@Override
	public WOComponent nextPage(WOComponent sender) {
		 D2WPage page = (D2WPage) sender;
		 ERXGenericRecord object = (ERXGenericRecord) page.object();
		 if (!object.isNewObject()) {
			 return (WOComponent) smartInspectAction(object);
		 }
		 
		 return nextPage;
	}

}