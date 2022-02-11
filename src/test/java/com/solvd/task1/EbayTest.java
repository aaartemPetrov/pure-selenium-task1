package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import com.solvd.task1.page.SearchedResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Locale;

public class EbayTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbayTest.class);
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/apetrov/Documents/SeleniumServer/chromedriver");
        this.driver = new ChromeDriver();
    }

    @Test
    public void checkSearchTest() {
        HomePage homePage = new HomePage(this.driver);
        homePage.writeInSearchLine("samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(this.driver);
        List<WebElement> searchedItems = searchedResultPage.getSearchedItemsTitles();
        Assert.assertFalse(searchedItems.isEmpty(), "There are no searched items in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedItems.forEach(searchedItem -> {
            softAssert.assertTrue(searchedItem.getText().toLowerCase(Locale.ROOT).contains("samsung"),
                    String.format("Product title doesn't contain brand name \"Samsung\""));
            LOGGER.info(searchedItem.getText());
        });
        softAssert.assertAll();
    }

    /*@Test
    public void checkSignInTest() {
        HomePage homePage = new HomePage(this.driver);
        AbstractPage.click(driver, homePage.getSignInLink());

        SignInPage signInPage = new SignInPage(this.driver);
        AbstractPage.sendKeys(driver, signInPage.getUserInput(), "apetrov@solvd.com");
        AbstractPage.click(driver, signInPage.getContinueButton());

        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.ByCssSelector.cssSelector("#errormsg")));
        Assert.assertEquals(errorMessage.getText(), "Oops, that's not a match.", "Error message at Sign In page not equals.");
    }*/

    @AfterMethod
    public void end() {
        this.driver.close();
        this.driver.quit();
    }

}
