/**
 * 
 */
package de.sw.osgi.service.mailbox.consumer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import de.sw.osgi.service.mailbox.api.Mailbox;

/**
 * @author psi
 *
 */
public class MailboxServiceTracker extends ServiceTracker {
	
	public MailboxServiceTracker(BundleContext context) {
		// damit erhalten wir unten die ServiceReference zum Mailbox-Service
		super(context, Mailbox.class.getName(), null);
	}

	/* (non-Javadoc)
	 * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
	 */
	@Override
	public Object addingService(ServiceReference reference) {
		String mboxName = (String) reference.getProperty((String) Mailbox.NAME_PROPERTY);
		
		Mailbox service = (Mailbox) context.getService(reference);
		try {
			int msgCount = service.getAllMessages().length;
			System.out.println("New mailbox " + mboxName + " registered with " + msgCount + " messages.");
			
			return service;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
	 */
	@Override
	public void removedService(ServiceReference reference, Object service) {
		String mboxName = (String) reference.getProperty((String) Mailbox.NAME_PROPERTY);

		try {
			int msgCount = ((Mailbox)service).getAllMessages().length;
			System.out.println("Mailbox " + mboxName + " with " + msgCount + " messages unregistered.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		super.removedService(reference, service);
	}

}
