package wo.bml.classes;

import er.extensions.ERXFrameworkPrincipal;

public class BootsrapModernLook extends ERXFrameworkPrincipal {
	protected static BootsrapModernLook sharedInstance;
	@SuppressWarnings("unchecked")
	public final static Class<? extends ERXFrameworkPrincipal> REQUIRES[] = new Class[] {};

	static {
		setUpFrameworkPrincipalClass(BootsrapModernLook.class);
	}

	public static BootsrapModernLook sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = sharedInstance(BootsrapModernLook.class);
		}
		return sharedInstance;
	}

	@Override
	public void finishInitialization() {
		log.debug("BootsrapModernLook loaded");
	}
}
