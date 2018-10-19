package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORedirect;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.directtoweb.NextPageDelegate;
import com.webobjects.directtoweb.QueryPageInterface;
import com.webobjects.eocontrol.EOGenericRecord;

import er.directtoweb.pages.ERD2WQueryPage;
import er.extensions.appserver.ERXSession;
import er.extensions.eof.ERXGenericRecord;
import er.modern.look.pages.*;

public class BootstrapInspectPage extends ERMODInspectPage implements NextPageDelegate {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	WOComponent nextPage;
	
	public BootstrapInspectPage(WOContext context) {
        super(context);
    }
	
	public void checkNextPage() {
		WOComponent page = context().page();
		 if (page instanceof QueryPageInterface) {
			 // we don't put query pages on the stack
			 // they are boring to cancel back to.
			 //
		 } else {
			nextPage = context().page();
		 }
	}
	public WOActionResults smartEditAction() {
		checkNextPage();
		EditPageInterface nextPage = D2W.factory().editPageForEntityNamed(d2wContext().entity().name(), session());
		 ERXGenericRecord object = (ERXGenericRecord) object();
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	public WOActionResults smartFindAction() {
		checkNextPage();
		 QueryPageInterface nextPage = D2W.factory().queryPageForEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	public WOActionResults smartInspectAction(ERXGenericRecord object) {
		 InspectPageInterface nextPage = D2W.factory().inspectPageForEntityNamed(d2wContext().entity().name(), session());
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	
	public WOActionResults smartCreateAction() {
		checkNextPage();
		 EditPageInterface nextPage = D2W.factory().editPageForNewObjectWithEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPage(context().page());
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}

	@Override
	public WOComponent nextPage(WOComponent sender) {
		 D2WPage page = (D2WPage) sender;
		 if (page instanceof QueryPageInterface) {
			 ERD2WQueryPage qp = (ERD2WQueryPage) page;
			 return qp.returnPage;
		 } else {
			 ERXGenericRecord object = (ERXGenericRecord) page.object();
			 if (!object.isNewObject()) {
				 return (WOComponent) smartInspectAction(object);
			 }
		 }

		 
		 return this;
	}

}