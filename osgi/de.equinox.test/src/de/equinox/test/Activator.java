package de.equinox.test;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private BundleContext context;
	private Thread thread;

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World!!");

		this.context = context;
		thread = new Thread(new BundleCountPollingRunnable());
		thread.start();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		thread.interrupt();
		System.out.println("Goodbye World!!");

		thread = null;
		context = null;
	}
	
	private class BundleCountPollingRunnable implements Runnable {


		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			try {
				while (!Thread.currentThread().isInterrupted()) {
					Bundle[] bundles = context.getBundles();
					System.out.println("Current bundle count: "
							+ bundles.length);
					Thread.sleep(2000);
				}
			} catch (InterruptedException e) {
				// Allow thread to exit
			}
		}
	}

}
