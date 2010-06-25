/**
 * 
 */
package de.sw.osgi.service.mailbox.popmailbox;

import de.sw.osgi.service.mailbox.api.Mailbox;

/**
 * @author psi
 *
 */
public class POPMailbox implements Mailbox {

	public long[] getAllMessages() {
		long ret[] = {Long.parseLong("1")};
		return ret;
	}

}
