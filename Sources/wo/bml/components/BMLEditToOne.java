package wo.bml.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eoaccess.EORelationship;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.foundation.ERXUtilities;
import er.extensions.qualifiers.ERXKeyValueQualifier;
import er.modern.directtoweb.components.relationships.ERMD2WEditToOneTypeAhead;

public class BMLEditToOne extends ERMD2WEditToOneTypeAhead {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMLEditToOne(WOContext context) {
        super(context);
    }
	
	// this only fetches once at the start?
	// really?
	// we want to present a fetch when the field is entered 
	// and every letter that is typed!
	//  
	//
	public NSArray<Object> objectsFromContext() {
    	// uses restrictedChoiceKey from the model to fire this function.
		// 
        //String fetchSpecName = (String)d2wContext().valueForKey("restrictedChoiceKey");
        
    	String propertyKey = (String)d2wContext().valueForKey("propertyKey");
        EORelationship relationship = ERXUtilities.relationshipWithObjectAndKeyPath(object(), propertyKey);
        EOEditingContext ec = object().editingContext();
        String entityName = relationship.destinationEntity().name();
        String value = searchValue();
        String split[] = value.split(" ");

        String format = ""; 
        String orString = "";
        
        NSMutableArray<Object> args = new NSMutableArray<Object>();
        
        for (String term:split) {
        	String searchTerm = "*" + term + "*";
        	format = format + orString + keyWhenRelationship() + " CASEINSENSITIVELIKE %@";
        	orString = " or ";
            args.addObject(searchTerm);
        }

        EOKeyValueQualifier qual = (EOKeyValueQualifier) ERXKeyValueQualifier.qualifierWithQualifierFormat(format, args);

        EOFetchSpecification fetchSpec = new EOFetchSpecification(entityName, qual, null);
        fetchSpec.setFetchLimit(5);//5 is right to fill the list with options...
        
		NSArray<Object> array = ec.objectsWithFetchSpecification(fetchSpec);
		
        return array;
    }

}