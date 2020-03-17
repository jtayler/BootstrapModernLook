package wo.bml.components;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORedirect;
import com.webobjects.directtoweb.ConfirmPageInterface;
import com.webobjects.directtoweb.D2W;
import com.webobjects.directtoweb.D2WComponent;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.directtoweb.D2WPage;
import com.webobjects.directtoweb.EditPageInterface;
import com.webobjects.directtoweb.InspectPageInterface;
import com.webobjects.directtoweb.NextPageDelegate;
import com.webobjects.directtoweb.QueryPageInterface;
import com.webobjects.eoaccess.EOEntity;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOGenericRecord;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

import er.directtoweb.pages.ERD2WPage;
import er.directtoweb.pages.ERD2WQueryPage;
import er.extensions.appserver.ERXSession;
import er.extensions.eof.ERXEOAccessUtilities;
import er.extensions.eof.ERXFetchSpecification;
import er.extensions.eof.ERXGenericRecord;
import er.modern.look.pages.*;

public class BootstrapInspectPage extends ERMODInspectPage implements NextPageDelegate {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	WOComponent nextPage;
	public String currentRelationshipKey;
	
	public BootstrapInspectPage(WOContext context) {
        super(context);
    }
	
    public String relationshipPageConfiguration() {
    	return "List" + relationshipEntityName();
    }
    
    public String relationshipEntityName() {
    	return object().classDescriptionForDestinationKey(currentRelationshipKey).entityName();    		
    }
    
    public D2WContext relationshipContext() {
    	D2WContext ctx = new D2WContext();
//    	EOClassDescription classDesc = object().classDescriptionForDestinationKey(currentRelationshipKey);
    	EOEntity entity = ERXEOAccessUtilities.entityNamed(object().editingContext(), relationshipEntityName());
    	ctx.setEntity(entity);
    	ctx.setTask("list");
    	return ctx;
    }
    
    public WOActionResults editRelationshipAction() {
		//EditPageInterface newEditPage = D2W.factory().editPageForEntityNamed(relationshipEntityName(), session());

		EditPageInterface newEditPage = D2W.factory().editPageForNewObjectWithConfigurationNamed("Create" + relationshipEntityName(), session());
		InspectPageInterface inspectPage = D2W.factory().inspectPageForEntityNamed(object().entityName(), session());
		inspectPage.setObject(object());
 		//EOGenericRecord object = Episode.createEpisode(object().editingContext(), true, "", (Podcast) object());
 		EOGenericRecord newobject = (EOGenericRecord) ((ERD2WPage) newEditPage).object();
 		
 		
 		//EOGenericRecord localInstance = ((_Episode) object()).localInstanceIn(newobject.editingContext()));
 		EOGenericRecord localInstance = (EOGenericRecord)EOUtilities.localInstanceOfObject(newobject.editingContext(), object());

 		localInstance.addObjectToBothSidesOfRelationshipWithKey(newobject, currentRelationshipKey);
		newEditPage.setObject(newobject);
		newEditPage.setNextPage((WOComponent) inspectPage);
		return (WOComponent) newEditPage;
    	
    }
    
    public NSArray<EOGenericRecord> fetchRelation(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    	String enS = object().classDescriptionForDestinationKey(currentRelationshipKey).entityName();
    	ERXFetchSpecification<EOGenericRecord> fetchSpec = new ERXFetchSpecification<EOGenericRecord>(enS, qualifier, sortOrderings);
        //fetchSpec.setIsDeep(true);
        NSArray<EOGenericRecord> eoObjects = fetchSpec.fetchObjects(editingContext);
        return eoObjects;
      }


    public  NSArray<EOGenericRecord> listForCurrentRelationshipKey() {
		//EOQualifier qual = EOQualifier.qualifierWithQualifierFormat("podcast = " + ((ERXGenericRecord) object()) , null);
    	String tntity = object().inverseForRelationshipKey(currentRelationshipKey);
    	
    	EOQualifier qual = EOQualifier.qualifierWithQualifierFormat(tntity + ".id = " + ((ERXGenericRecord) object()).primaryKey() , null);
    	return fetchRelation(editingContext(), qual, null);
    }
	public void checkNextPage() {
		WOComponent page = context().page();
		 if (page instanceof QueryPageInterface) {
			 // we don't put query pages on the stack
			 // they are boring to cancel back to.
			 //
		 } else {
			nextPage = context().page();
		 }
	}
	public WOActionResults smartEditAction() {
		checkNextPage();
		EditPageInterface nextPage = D2W.factory().editPageForEntityNamed(d2wContext().entity().name(), session());
		 ERXGenericRecord object = (ERXGenericRecord) object();
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	public WOActionResults smartDeleteAction() {
		System.out.print("calling smartDeleteAction");

		 ERXGenericRecord object = (ERXGenericRecord) object();
		 object.editingContext().deleteObject(object);
		 object.editingContext().saveChanges();
		 
		return (WOActionResults) nextPage();
	}
	
	public WOActionResults smartFindAction() {
		checkNextPage();
		 QueryPageInterface nextPage = D2W.factory().queryPageForEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	public WOActionResults smartInspectAction(ERXGenericRecord object) {
		 InspectPageInterface nextPage = D2W.factory().inspectPageForEntityNamed(d2wContext().entity().name(), session());
		 nextPage.setObject(object);
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}
	
	
	public WOActionResults smartCreateAction() {
		checkNextPage();
		 EditPageInterface nextPage = D2W.factory().editPageForNewObjectWithEntityNamed(d2wContext().entity().name(), session());
		 //nextPage.setNextPage(context().page());
		 nextPage.setNextPageDelegate(this);
		return (WOActionResults) nextPage;
	}

	@Override
	public WOComponent nextPage(WOComponent sender) {
		 D2WPage page = (D2WPage) sender;
		 if (page instanceof QueryPageInterface) {
			 ERD2WQueryPage qp = (ERD2WQueryPage) page;
			 return qp.returnPage;
		 } else {
			 ERXGenericRecord object = (ERXGenericRecord) page.object();
			 if (!object.isNewObject()) {
				 return (WOComponent) smartInspectAction(object);
			 }
		 }

		 
		 return this;
	}

}