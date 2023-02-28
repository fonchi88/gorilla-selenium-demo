package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverFactory implements IDriver{

    private ChromeOptions options;

    WebDriver driver;
    @Override
    public MutableCapabilities setOptions(String... args) {
        Log.debug("Setting up chrome options");

        options = new ChromeOptions();

        for(int i = 0; i < args.length; i++){
            options.addArguments(args);
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

    @Override
    public void initializeDriver() {
        Log.debug("Initializing  Driver");

        WebDriverManager.chromedriver().setup();

        try{
            if(options == null){
                options = new ChromeOptions();
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444"),options);
        }catch(MalformedURLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dispose() {
        Log.debug("Dropping driver session");
        driver.close();
        driver.quit();
        options = null;
        driver = null;
    }
}
