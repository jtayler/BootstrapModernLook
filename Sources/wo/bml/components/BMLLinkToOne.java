package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.eocontrol.EOEnterpriseObject;

import er.directtoweb.components.ERD2WCustomComponentWithArgs;
import er.extensions.appserver.ERXRequest;
import er.extensions.components.ERXComponentUtilities;
import er.extensions.eof.ERXGenericRecord;
import wo.bml.classes.BMLRouteUrlUtils;

import com.webobjects.appserver.WOActionResults;

public class BMLLinkToOne extends ERD2WCustomComponentWithArgs {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLLinkToOne(WOContext context) {
        super(context);
    }
    
	public String _un_propertyValue()  {
		EOEnterpriseObject obj = (EOEnterpriseObject) object();
		String key = d2wContext().propertyKey();
		return (String) obj.valueForKey(key);
	}
	
	public String relationshipValue()  {
		EOEnterpriseObject obj = (EOEnterpriseObject) objectPropertyValue();
		String key = d2wContext().keyWhenRelationship();
		return (String) obj.valueForKey(key);
	}
	
	public String linkURL() {
		String linkUrl ="";
		String action = (String) valueForBinding("action");

		boolean secure = ERXComponentUtilities.booleanValueForBinding(this, "secure", 
				ERXRequest.isRequestSecure(context().request()));
		boolean includeSessionID = context().hasSession() && context().session().storesIDsInURLs();
		linkUrl = BMLRouteUrlUtils.actionUrlForRecord(context(), 
				(ERXGenericRecord) object(), 
				action, null, null, secure, includeSessionID);

		return linkUrl;
	}

	public WOActionResults inspectAction() {
		EOEnterpriseObject obj = (EOEnterpriseObject) objectPropertyValue();
		InspectPageInterface ipi = D2W.factory().inspectPageForEntityNamed(obj.entityName(), session());
		ipi.setObject(obj);
		ipi.setNextPage(context().page());
		return (WOActionResults)ipi;
	}

}