/**
 * 
 */
package de.sw.osgi.service.mailbox.dbmailbox;

import java.sql.Connection;

import de.sw.osgi.service.mailbox.api.Mailbox;

/**
 * @author psi
 *
 */
public class DbMailbox implements Mailbox {

	private Connection connection;

	public DbMailbox(Connection connection) {
		this.connection = connection;
	}

	public long[] getAllMessages() {
		long ret[] = {
				Long.parseLong("1"), Long.parseLong("2") };
		return ret;
	}

}
