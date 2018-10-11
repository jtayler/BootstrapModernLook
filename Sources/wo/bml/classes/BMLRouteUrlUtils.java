package wo.bml.classes;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSDictionary;

import er.extensions.eof.ERXGenericRecord;
import er.rest.routes.ERXRouteUrlUtils;

public class BMLRouteUrlUtils extends ERXRouteUrlUtils {

	public static String actionUrlForEntity(WOContext context, String entityName, Object entityID, String action, String format, NSDictionary<String, Object> queryParameters, boolean secure, boolean includeSessionID) {
		 String link = ERXRouteUrlUtils.actionUrlForEntity(context, entityName, entityID, action, format, queryParameters, secure, includeSessionID);
			
		 return cleanedLink(link);
	}

	public static String cleanedLink(String link) {
		if  (link.contains("/Mtly")) {
			return link.replace("w/ra/Mtly", "event");
		}
		if  (link.contains("/Person")) {
			return link.replace("w/ra/Person", "person");
		}
		if  (link.contains("/Venue")) {
			return link.replace("w/ra/Venue", "venue");
		}
		if  (link.contains("w/wa/about")) {
			return link.replace("w/wa/about", "about");
		}
		if  (link.contains("w/wa/services")) {
			return link.replace("w/wa/services", "services");
		}
		if  (link.contains("w/wa/home")) {
			return link.replace("w/wa/home", "home");
		}
		return link;
	}
	
	public static String actionUrlForRecord(WOContext context, ERXGenericRecord record, String action, String format, NSDictionary<String, Object> queryParameters, boolean secure, boolean includeSessionID) {
		String link = ERXRouteUrlUtils.actionUrlForRecord(context, record, action, format, queryParameters, secure, includeSessionID);
		
		return cleanedLink(link);
	}

}
