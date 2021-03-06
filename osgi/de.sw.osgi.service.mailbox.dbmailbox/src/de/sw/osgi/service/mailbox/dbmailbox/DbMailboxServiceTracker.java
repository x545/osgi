/**
 * 
 */
package de.sw.osgi.service.mailbox.dbmailbox;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import de.sw.osgi.service.mailbox.api.Mailbox;

/**
 * @author psi
 *
 */
public class DbMailboxServiceTracker extends ServiceTracker {
	
	public DbMailboxServiceTracker(BundleContext context) {
		// damit erhalten wir unten die ServiceReference zum DataSource-Service
		super(context, DataSource.class.getName(), null);
	}

	/* (non-Javadoc)
	 * @see org.osgi.util.tracker.ServiceTracker#addingService(org.osgi.framework.ServiceReference)
	 */
	@Override
	public Object addingService(ServiceReference reference) {
		// Beispiel für: stellen DbMailbox-Service zur Verfügung, welcher abhängig ist vom DataSource-Service
		// DataSource-Service holen (sind abhängig davon)
		DataSource service = (DataSource) context.getService(reference);

		ServiceRegistration reg = null;
		if (null != service) {
			try {
				Connection connection = service.getConnection("root", "passwort");
				Mailbox dbMailbox = new DbMailbox(connection);

				Properties properties = new Properties();
				properties.put(Mailbox.NAME_PROPERTY, "dbmailbox");

				// DbMailbox als neu verfügbaren Mailbox-Service registrieren
				reg = context.registerService(Mailbox.class.getName(), dbMailbox, properties);

				// Reference zum Mailbox-Service
				return reg;
			} catch (SQLException e) {
				e.printStackTrace();
				
				return null;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.osgi.util.tracker.ServiceTracker#removedService(org.osgi.framework.ServiceReference, java.lang.Object)
	 */
	@Override
	public void removedService(ServiceReference reference, Object service) {
		ServiceRegistration reg = (ServiceRegistration) service;
		
		reg.unregister();
		
		super.removedService(reference, service);
	}

}
