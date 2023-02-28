package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Log;

public class ChromeDriverFactory implements IDriver {

    //private static final Logger logger = LogManager.getLogger(ChromeDriverFactory.class);
    private WebDriver driver;

    private ChromeOptions options;
    @Override
    public MutableCapabilities setOptions(String... arg) {
        Log.debug("Setting up chrome options");

        options = new ChromeOptions();

        for(int i = 0; i < arg.length; i++){
            options.addArguments(arg);
        }

        return options;
    }

    @Override
    public WebDriver getDriver() {

        Log.debug("Return existing driver");
        if(driver!= null)
            return driver;

        Log.debug("I could not found an existing driver session");
        initializeDriver();

        Log.debug("Returning a newly fresh driver session");
        return driver;
    }

    public void initializeDriver(){
        Log.debug("Initializing Chrome Driver");

        WebDriverManager.chromedriver().setup();

        if(options != null)
            driver = new ChromeDriver(options);
        else
            driver = new ChromeDriver();
    }

    @Override
    public void dispose() {
        Log.debug("Dropping chrome driver session");
        driver.close();
        driver.quit();
        options = null;
        driver = null;
    }
}
