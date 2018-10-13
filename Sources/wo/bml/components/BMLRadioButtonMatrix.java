package wo.bml.components;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXRadioButtonMatrix;

public class BMLRadioButtonMatrix extends ERXRadioButtonMatrix {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLRadioButtonMatrix(WOContext context) {
        super(context);
    }
	
	public String buttonClass = "btn btn-light";
	
    public String activeClass() {
        if (selection()!=null && selection().equals(currentItem)) {
            return " active";
        }

        return " inactive";
    }


}