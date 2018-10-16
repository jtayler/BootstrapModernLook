package wo.bml.classes;

import java.util.Enumeration;

import com.webobjects.appserver.WOContext;
import com.webobjects.directtoweb.D2WContext;
import com.webobjects.foundation.NSDictionary;

import er.extensions.eof.ERXGenericRecord;
import er.rest.routes.ERXRouteUrlUtils;

public class BMLRouteUrlUtils extends ERXRouteUrlUtils {

	public static String actionUrlForEntity(WOContext context, String entityName, Object entityID, String action, String format, NSDictionary<String, Object> queryParameters, boolean secure, boolean includeSessionID) {
		 String link = ERXRouteUrlUtils.actionUrlForEntity(context, entityName, entityID, action, format, queryParameters, secure, includeSessionID);
			
		 return cleanedLink(link);
	}

	public static String cleanedLink(String link) {
		D2WContext d2wContext = new D2WContext();
		@SuppressWarnings("rawtypes")
		NSDictionary dic = (NSDictionary) d2wContext.valueForKey("apacheShorteningStrings");
		Enumeration<?> e = dic.allKeys().objectEnumerator();
	    while (e.hasMoreElements()) {
	    	String key = (String) e.nextElement();
			if  (link.contains(key)) {
				String value = (String) dic.valueForKey(key);
				link = link.replace(key, value);
			}
	      }
		
		return link;
	}
	
	public static String actionUrlForRecord(WOContext context, ERXGenericRecord record, String action, String format, NSDictionary<String, Object> queryParameters, boolean secure, boolean includeSessionID) {
		String link = ERXRouteUrlUtils.actionUrlForRecord(context, record, action, format, queryParameters, secure, includeSessionID);
		
		return cleanedLink(link);
	}

}
