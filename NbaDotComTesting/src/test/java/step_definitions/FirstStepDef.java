package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class FirstStepDef {
    WebDriver driver;
    Duration TIMEOUT = Duration.ofSeconds(30L);
    @Given("Launch the browser")
    public void launchTheBrowser() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        //options.addArguments("--disable-setuid-sandbox");

        //options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--remote-debugging-port=9222");
        //options.addArguments("--disable-extensions");
        //options.addArguments("--disable-gpu");
        //options.addArguments("start-maximized");
        //options.addArguments("disable-infobars");
        //options.addArguments("webdriver.chrome.driver=/Users/francisco.morelos/.M2/repository/webdriver");
        //options.addArguments("--headless");

        driver= new ChromeDriver(options);
    }


    @When("Hit Google on your browser")
    public void hitGoogleOnYourBrowser() {
        driver.navigate().to("https://www.google.com/");
    }

    @Then("Enter {string} in the search text box")
    public void enterInTheSearchTextBox(String arg0) {
        WebElement search_txt = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.name("q"))));
        search_txt.sendKeys(arg0+ Keys.ENTER);
    }

    @And("Select the first result")
    public void selectTheFirstResult() throws InterruptedException {
        (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.className("LC20lb"))));
        List <WebElement> results = driver.findElements(By.className("LC20lb"));

        results.get(0).click();
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("cucumber"));
        Thread.sleep(3000);
        driver.quit();
    }
}
