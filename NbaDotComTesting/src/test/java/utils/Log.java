package utils;

import driver.ChromeDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final Logger logger = LogManager.getLogger(ChromeDriverFactory.class);

    public static void debug(String message){
        logger.debug(message);
    }
}
