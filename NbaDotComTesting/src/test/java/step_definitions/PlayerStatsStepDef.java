package step_definitions;

import dataProviders.ConfigFileReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Iterator;

import java.util.List;

public class PlayerStatsStepDef {
    WebDriver driver;

    ConfigFileReader configs;

    Duration TIMEOUT = Duration.ofSeconds(30L);
    public PlayerStatsStepDef(Hooks hooks) {
        driver = hooks.dm.getDriver();
        //driver = hooks.driver;
        configs = hooks.CONFIGURATIONS;
    }


    @When("Hit NBA stats page on my computer")
    public void hitNbaStatsPageOnMyComputer(){
        driver.navigate().to(configs.getUrl()+"/stats");
    }



    @Then("Yesterdays Leaders is selected")
    public void yesterdaysLeadersIsSelected() {
        WebElement yesterdays_btn = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@class='Block_blockContent__6iJ_n'])[1]//button[contains(text(),'Yesterday')]"))));
        Assert.assertTrue(yesterdays_btn.getAttribute("data-active").toLowerCase().contains("true"));
    }

    @And("Top Five scorers are listed")
    public void topFiveScorersAreListed() {
        WebElement points_table = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("((//div[@class='Block_blockContent__6iJ_n'])[1]//table[@class='LeaderBoardPlayerCard_lbpcTable__q3iZD'])[1]"))));
        Assert.assertEquals(points_table.findElements(By.className("LeaderBoardPlayerCard_lbpcTableRow___Lod5")).size(),5);

        List<WebElement> playersNames = points_table.findElements(By.xpath(".//tr[contains(@class,'LeaderBoardPlayerCard_lbpcTableRow___Lod5')]//a[contains(@href,'stats')]"));

        Iterator<WebElement> itr = playersNames.iterator();

        while(itr.hasNext()){
            WebElement we = itr.next();

            Assert.assertTrue(we.getText().length()>0);
        }

    }

    @And("Names are not empty")
    public void namesAreNotEmpty() {
        WebElement points_table = (new WebDriverWait(driver,TIMEOUT)).ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("((//div[@class='Block_blockContent__6iJ_n'])[1]//table[@class='LeaderBoardPlayerCard_lbpcTable__q3iZD'])[1]"))));

        List<WebElement> playersNames = points_table.findElements(By.xpath(".//tr[contains(@class,'LeaderBoardPlayerCard_lbpcTableRow___Lod5')]//a[contains(@href,'stats')]"));

        Iterator<WebElement> itr = playersNames.iterator();

        while(itr.hasNext()){
            WebElement we = itr.next();

            Assert.assertTrue(we.getText().length()>0);
        }
    }
}
