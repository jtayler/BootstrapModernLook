package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORequest;

import er.directtoweb.components.strings.ERD2WEditString;

public class BMLCustomSelectString extends ERD2WEditString {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String string;
	public Integer index;
	
	public BMLCustomSelectString(WOContext context) {
        super(context);
    }
	
	public boolean isSelected() {
		String value = (String)value();
		boolean selected = string.equalsIgnoreCase(value);
		return selected;
	}
	
	public String selected() {
		String selected = isSelected() ? "selected":"";
		return selected;
	}
	
	
}