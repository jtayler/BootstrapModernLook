package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.directtoweb.D2WCustomComponent;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.foundation.NSDictionary;

import er.extensions.appserver.ERXRequest;
import er.extensions.components.ERXComponentUtilities;
import er.extensions.eof.ERXGenericRecord;
import wo.bml.classes.BMLRouteUrlUtils;

public class BMLCard extends D2WCustomComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLCard(WOContext context) {
        super(context);
    }
	
	public String cardHeader() {
		return valueForD2WKey("card-header");
	}

	public String linkURL() {
		String linkUrl ="";
		String action = (String)valueForBinding("action");

		boolean secure = ERXComponentUtilities.booleanValueForBinding(this, "secure", ERXRequest.isRequestSecure(context().request()));
		boolean includeSessionID = context().hasSession() && context().session().storesIDsInURLs();
		linkUrl = BMLRouteUrlUtils.actionUrlForRecord(context(), (ERXGenericRecord) object(), action, null, null, secure, includeSessionID);

		return linkUrl;
	}

	public WOActionResults inspectAction() {
		InspectPageInterface ipi = D2W.factory().inspectPageForEntityNamed(object().entityName(), session());
		ipi.setObject(object());
		ipi.setNextPage(context().page());
		return (WOActionResults)ipi;
	}
	
	public String cardImageSrc() {
		return valueForD2WKey("card-img-top");
	}

	public String cardFooterText() {
		return valueForD2WKey("card-footer");
	}

	public String cardTitle() {
		return valueForD2WKey("card-title");
	}
	
	public String cardLink() {
		return valueForD2WKey("card-link");
	}
	
	public String cardLinkTitle() {
		return keyForD2WKey("card-link-title");
	}
	
	public String cardText() {
		return valueForD2WKey("card-text");
	}

	public String keyForD2WKey(String d2wKey) {
		D2WContext ctx = d2wContext();
		NSDictionary<String, String> cardSectionsContents=(NSDictionary<String, String>) ctx.valueForKey("cardSectionsContents");
		String key = (String) cardSectionsContents.valueForKey(d2wKey);
	    return key;
	}

	public String valueForD2WKey(String d2wKey) {
		String key = (String) keyForD2WKey(d2wKey);
	    return (String) valueForKeyPath(key);
	}
}