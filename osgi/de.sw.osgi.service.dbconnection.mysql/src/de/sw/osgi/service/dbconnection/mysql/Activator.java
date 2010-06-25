package de.sw.osgi.service.dbconnection.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
//		String url =
//			"jdbc:mysql://localhost:3306/mysql";
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection service = DriverManager.getConnection(url, "root", "59468,14");
		DataSource service = new MysqlConnectionPoolDataSource();

		context.registerService(DataSource.class.getName(), service, null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
	}

}
