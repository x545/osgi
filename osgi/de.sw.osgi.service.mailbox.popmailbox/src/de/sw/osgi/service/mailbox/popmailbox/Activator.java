package de.sw.osgi.service.mailbox.popmailbox;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.sw.osgi.service.mailbox.api.Mailbox;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		Mailbox service = new POPMailbox();
		
		Properties properties = new Properties();
		properties.put(Mailbox.NAME_PROPERTY, "pop3mailbox");
		
		context.registerService(Mailbox.class.getName(), service, properties);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

}
