package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORedirect;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.EditPageInterface;

import er.extensions.appserver.ERXSession;
import er.modern.look.pages.*;

public class BootstrapInspectPage extends ERMODInspectPage {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BootstrapInspectPage(WOContext context) {
        super(context);
    }
	
	public WOActionResults smartEditAction() {
//		 WORedirect page = (WORedirect) ((ERXSession) session()).application().pageWithName("WORedirect", session().context());
		 EditPageInterface nextPage = D2W.factory().editPageForEntityNamed(d2wContext().entity().name(), session());
		 nextPage.setNextPage(context().page());
		return (WOActionResults) nextPage;
	}
}