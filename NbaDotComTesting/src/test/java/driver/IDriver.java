package driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

public interface IDriver {


    public MutableCapabilities setOptions(String... args);

    public  WebDriver getDriver();

    public void initializeDriver();

    public void dispose();
}
