package de.sw.osgi.service.mailbox.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import de.sw.osgi.service.mailbox.api.Mailbox;

public class Activator implements BundleActivator {

	private ServiceTracker tracker;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		ServiceReference reference = context.getServiceReference(Mailbox.class.getName());
		
//		if (null != reference) {
//			Mailbox service = (Mailbox) context.getService(reference);
//
//			if (null != service) {
//				try {
//					service.getAllMessages();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					context.ungetService(reference);
//				}
//			}
//		}
		tracker = new MailboxServiceTracker(context);
		tracker.open();
		
		Mailbox service = (Mailbox) tracker.getService(reference);
//		if (null != service) {
//			long[] messages = service.getAllMessages();
//			System.out.println("You have " +  messages.length + " messages.");
//		} else {
//			System.out.println("No mailbox available.");
//		}
//		context.ungetService(reference);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		tracker.close();
		tracker = null;
	}

}
