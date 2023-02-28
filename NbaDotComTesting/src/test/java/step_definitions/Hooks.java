package step_definitions;

import dataProviders.ConfigFileReader;
import driver.Browser;
import driver.DriverManager;
import driver.IDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    public ConfigFileReader CONFIGURATIONS;

    IDriver dm;


    @Before
    public void BeforeSteps(){

        CONFIGURATIONS = new ConfigFileReader("nba_configuration_prod");

        dm = DriverManager.get(Browser.CHROME);
        dm.setOptions("--no-sandbox","--disable-dev-shm-usage","--ignore-ssl-errors=yes","--ignore-certificate-errors");
        dm.initializeDriver();
    }

    @After
    public void AfterSteps(Scenario scenario){
        dm.dispose();
    }
}
