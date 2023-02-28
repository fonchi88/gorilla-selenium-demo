package step_definitions;

import dataProviders.ConfigFileReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;


public class LoginStepDef {
    WebDriver driver;

    ConfigFileReader configs;

    Duration TIMEOUT = Duration.ofSeconds(30L);
    public LoginStepDef(Hooks hooks){
        driver = hooks.dm.getDriver();
        configs = hooks.CONFIGURATIONS;
    }
    @When("Providing a valid {string} and {string}")
    public void providingAValidaUsernameAndPassword(String username, String password) {
        driver.navigate().to(configs.getUrl()+"/stats");
        WebElement signIn_btn = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Sign In')]/parent::button"))));

        signIn_btn.click();

        WebElement signInWithNbaId_btn = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@data-id='nba:navigation:nba-sign-in:link']"))));

        signInWithNbaId_btn.click();

        WebElement email_txt = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("email"))));

        WebElement password_txt = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));

        email_txt.sendKeys(username);

        password_txt.sendKeys(password);

        WebElement submit_btn = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("submit"))));

        submit_btn.click();
    }

    @Then("User features are visible")
    public void userFeaturesAreVisible() {

        Log.debug("Looking for sign out option");

        WebElement logout_btn = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),' Sign Out')]")));

        logout_btn.isDisplayed();

        Log.debug("Sign out option is present");
    }
}
