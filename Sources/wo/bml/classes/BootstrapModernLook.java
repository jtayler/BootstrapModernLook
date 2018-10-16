package wo.bml.classes;

import er.extensions.ERXFrameworkPrincipal;

public class BootstrapModernLook extends ERXFrameworkPrincipal {
	protected static BootstrapModernLook sharedInstance;
	@SuppressWarnings("unchecked")
	public final static Class<? extends ERXFrameworkPrincipal> REQUIRES[] = new Class[] {};

	static {
		setUpFrameworkPrincipalClass(BootstrapModernLook.class);
	}

	public static BootstrapModernLook sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = sharedInstance(BootstrapModernLook.class);
		}
		return sharedInstance;
	}

	@Override
	public void finishInitialization() {
		log.debug("BootstrapModernLook loaded");
	}
}
