package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.foundation.NSDictionary;

import er.extensions.appserver.ERXRequest;
import er.extensions.components.ERXComponentUtilities;
import er.extensions.eof.ERXGenericRecord;
import er.modern.directtoweb.components.repetitions.ERMDSimpleListPageRepetition;
import wo.bml.classes.BMLRouteUrlUtils;

public class BMLCarouselRepetition extends ERMDSimpleListPageRepetition {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLCarouselRepetition(WOContext context) {
        super(context);
    }
	
	public String linkURL() {
		String linkUrl ="";
		String action = (String)valueForBinding("action");
		D2WContext ctx = d2wContext();
		EOGenericRecord obj = (EOGenericRecord)ctx.valueForKey("object");

		boolean secure = ERXComponentUtilities.booleanValueForBinding(this, "secure", ERXRequest.isRequestSecure(context().request()));
		boolean includeSessionID = context().hasSession() && context().session().storesIDsInURLs();
		linkUrl = BMLRouteUrlUtils.actionUrlForRecord(context(), (ERXGenericRecord) obj, action, null, null, secure, includeSessionID);

		return linkUrl;
	}

	public String activeClass() {
		String str = "carousel-item";
		if (rowIndex == 0) {
			str = "carousel-item active";
		}
		System.out.print("rowIndex " + str);
		return str;
	}
	
	public String cardText() {
		return valueForD2WKey("card-text");
	}

	public String cardImageSrc() {
		return valueForD2WKey("card-img-top");
	}

	public String cardLink() {
		return valueForD2WKey("card-link");
	}

	public String cardTitle() {
		return valueForD2WKey("card-title");
	}

	public String keyForD2WKey(String d2wKey) {
		D2WContext ctx = d2wContext();
		NSDictionary<String, String> cardSectionsContents=(NSDictionary<String, String>) ctx.valueForKey("carouselSectionsContents");
		String key = (String) cardSectionsContents.valueForKey(d2wKey);
	    return key;
	}

	public String valueForD2WKey(String d2wKey) {
		D2WContext ctx = d2wContext();
		String key = (String) keyForD2WKey(d2wKey);
		EOGenericRecord obj = (EOGenericRecord)ctx.valueForKey("object");
	    return (String) obj.valueForKeyPath(key);
	}

}