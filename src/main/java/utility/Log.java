package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

	public static Logger log = LogManager.getLogger();

	public static void startTest(String testcasename) {
		log.info("Test starts " + testcasename);
	}

	public static void endTest(String testcasename) {
		log.info("Test ends " + testcasename);
	}

	public static void info(String msg) {
		log.info(msg);
	}

	public static void warn(String msg) {
		log.warn(msg);
	}

}
