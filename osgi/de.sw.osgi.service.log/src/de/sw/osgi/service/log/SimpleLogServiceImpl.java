package de.sw.osgi.service.log;

public class SimpleLogServiceImpl implements SimpleLogService {

	public void log(String message) {
		System.out.println(message);
	}
	
}
