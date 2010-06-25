/**
 * 
 */
package de.sw.osgi.service.mailbox.api;

/**
 * @author psi
 *
 */
public interface Mailbox {

	Object NAME_PROPERTY = new String("name");

	long[] getAllMessages();
}
