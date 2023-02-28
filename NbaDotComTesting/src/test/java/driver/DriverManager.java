package driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {


    public static IDriver get(Browser browser){
        switch (browser){
            case CHROME:
                return new ChromeDriverFactory();
            case REMOTE:
                return new RemoteWebDriverFactory();
            default:
                return new ChromeDriverFactory();
        }
    }
}
