package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORedirect;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.directtoweb.D2WCustomComponent;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSArray;
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

	public boolean isPublicPage() {
		NSArray<String> entities = (NSArray) d2wContext().valueForKey("publicEntityNames");
		if (entities != null && entities instanceof NSArray) {
			String entityName = relatedObject().entityName();
			boolean isPublicPage = entities.contains(entityName);
			return isPublicPage;
		}
		return false;
	}
	
	public EOGenericRecord relatedObject() {
		String key = (String) keyForD2WKey("inspectActionKeypath");
		EOGenericRecord relatedObject = (EOGenericRecord) valueForKeyPath(key);
		if ( relatedObject == null ) {
			relatedObject = (EOGenericRecord) object();
		}
		return relatedObject;
	}
	
	public String linkURL() {
		boolean secure = ERXComponentUtilities.booleanValueForBinding(this, "secure", ERXRequest.isRequestSecure(context().request()));
		boolean includeSessionID = context().hasSession() && context().session().storesIDsInURLs();
		String linkUrl = BMLRouteUrlUtils.actionUrlForRecord(context(), (ERXGenericRecord) relatedObject(), null, null, null, secure, includeSessionID);
		
		return linkUrl;
	}

	public WOActionResults inspectObjectAction() {
		InspectPageInterface ipi = D2W.factory().inspectPageForEntityNamed(object().entityName(), session());
		ipi.setObject(object());
		ipi.setNextPage(context().page());
		return (WOActionResults)ipi;
	}
	
	public String objectPropertyValuePath() {
		EOGenericRecord object = (EOGenericRecord) objectPropertyValue();
		return (String) object.valueForKeyPath(keyWhenRelationship());
	}
	
	public WOActionResults inspectRelatedObjectAction() {
		String key = (String) keyForD2WKey("inspectActionKeypath");
		EOGenericRecord relatedObject = (EOGenericRecord) valueForKeyPath(key);
		//EOGenericRecord relatedObject = (EOGenericRecord) objectPropertyValue();

		InspectPageInterface ipi = D2W.factory().inspectPageForEntityNamed(relatedObject.entityName(), session());
		ipi.setObject(relatedObject);
		ipi.setNextPage(context().page());
		return (WOActionResults)ipi;
	}
	
	public String cardImageSrc() {
		return valueForD2WKey("card-img-top");
	}

	public String inspectAction() {
		return valueForD2WKey("inspectObjectAction");
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
	
	public String cardLinkString() {
		return keyForD2WKey("cardImageLink");
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