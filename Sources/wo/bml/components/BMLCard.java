package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.directtoweb.D2WCustomComponent;
import com.webobjects.foundation.NSDictionary;

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

	public String cardImageSrc() {
		return valueForD2WKey("card-img-top");
	}

	public String cardTitle() {
		return valueForD2WKey("card-title");
	}
	
	public String cardLink() {
		return valueForD2WKey("card-link");
	}
	
	public String cardText() {
		return valueForD2WKey("card-text");
	}

	public String valueForD2WKey(String d2wKey) {
		D2WContext ctx = d2wContext();
		NSDictionary<String, String> cardSectionsContents=(NSDictionary<String, String>) ctx.valueForKey("cardSectionsContents");
		String key = (String) cardSectionsContents.valueForKey(d2wKey);
	    return (String) object().valueForKeyPath(key);
	}
}