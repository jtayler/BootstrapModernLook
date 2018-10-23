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
    
	public Object relationshipValue()  {
		EOEnterpriseObject obj = (EOEnterpriseObject) objectPropertyValue();
		if (obj == null) {
			return null;
		}
		String key = d2wContext().keyWhenRelationship();
		return (Object) obj.valueForKey(key);
	}
	
	public String linkURL() {
		String linkUrl ="";
		String action = (String) valueForBinding("action");
		ERXGenericRecord obj = (ERXGenericRecord) objectPropertyValue();
		boolean secure = ERXComponentUtilities.booleanValueForBinding(this, "secure", 
				ERXRequest.isRequestSecure(context().request()));
		boolean includeSessionID = context().hasSession() && context().session().storesIDsInURLs();
		linkUrl = BMLRouteUrlUtils.actionUrlForRecord(context(), 
				obj, 
				"inspect", null, null, secure, includeSessionID);

		return linkUrl;
	}

	public WOActionResults inspectActionLink() {
		EOEnterpriseObject obj = (EOEnterpriseObject) objectPropertyValue();
		InspectPageInterface ipi = D2W.factory().inspectPageForEntityNamed(obj.entityName(), session());
		ipi.setObject(obj);
		ipi.setNextPage(context().page());
		return (WOActionResults)ipi;
	}

}