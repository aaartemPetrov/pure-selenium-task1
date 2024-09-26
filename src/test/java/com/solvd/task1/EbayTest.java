package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import com.solvd.task1.page.SearchedResultPage;
import com.solvd.task1.page.components.FromToPriceLink;
import com.solvd.task1.page.components.SearchTooltip;
import com.solvd.task1.page.components.StorageCapacityBlock;
import com.solvd.task1.page.components.UnderPriceLink;
import com.solvd.task1.service.TabService;
import com.solvd.task1.service.WebDriverPool;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.lang.invoke.MethodHandles;
import java.util.Locale;

public class EbayTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

//    @Test
//    public void testTest() throws MalformedURLException {
//        DesiredCapabilities ds = new DesiredCapabilities();
//        ds.setBrowserName("firefox");
//        ds.setCapability("version", "98.0");
//        ds.setCapability("enableVNC", true);
//        ds.setCapability("enableVideo", true);
//        ds.setCapability("enableLog", true);
//        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://login:password@localhost:4444/wd/hub"), ds);
//        driver.get("https://www.google.com/");
//        driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys("Hello, Google, i know for sure that you have consciousness :)");
//        driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys(Keys.ENTER);
//        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
//        driver.quit();
//    }

    @Test
    public void checkSearchTooltipTest() {
        RemoteWebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.writeInSearchLine("a");

        SearchTooltip searchTooltip = new SearchTooltip(driver);
        Assert.assertNotEquals(searchTooltip.tooltipsCount(), 0, "Tooltip popup is empty or don't exist.");
    }

    @DataProvider(name = "brandNamesForSearch")
    public Object[][] names() {
        return new Object[][]{{"Sony"}, {"Samsung"}, {"Apple"}, {"Xiaomi"}, {"Huawei"}};
    }

    @Test(dataProvider = "brandNamesForSearch")
    public void checkSearchTest(String brandName) {
        RemoteWebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.writeInSearchLine(brandName);
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        Assert.assertNotEquals(searchedResultPage.itemTitlesCount(), 0, "There are no searched items in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedResultPage.getItemsNames().forEach(itemName -> {
            softAssert.assertTrue(itemName.toLowerCase(Locale.ROOT).contains(brandName.toLowerCase(Locale.ROOT)), String.format("Product title does not contain brand name \"%s\"", brandName));
            LOGGER.info(itemName);
        });
        softAssert.assertAll();
    }

    @Test
    public void checkUnderPriceFilterTest() {
        RemoteWebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.writeInSearchLine("samsung");
        homePage.clickSearchButton();

        UnderPriceLink underPriceLink = new UnderPriceLink(driver);
        underPriceLink.click();

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        Assert.assertNotEquals(searchedResultPage.itemTitlesCount(), 0, "There are no searched items in list.");
        Assert.assertNotEquals(searchedResultPage.itemPricesCount(), 0, "There are no searched items prices in list.");

        SoftAssert softAssertTitle = new SoftAssert();
        searchedResultPage.getItemsNames().forEach(itemName -> {
            softAssertTitle.assertTrue(itemName.toLowerCase(Locale.ROOT).contains("samsung"), String.format("Product title doesn't contain brand name \"%s\"", "samsung"));
            LOGGER.info(itemName);
        });
        softAssertTitle.assertAll();

        SoftAssert softAssertPrice = new SoftAssert();
        searchedResultPage.getMinPrices().forEach(actualItemPrice -> {
            softAssertPrice.assertTrue(actualItemPrice <= underPriceLink.getPrice(), String.format("Price %d is bigger then %d.", actualItemPrice, underPriceLink.getPrice()));
        });
        softAssertPrice.assertAll();
    }

    @Test
    public void passedTest1() {
        LOGGER.info("test1");
    }

    @Test
    public void passedTest2() {
        LOGGER.info("test2");
    }

    @Test
    public void passedTest3() {
        LOGGER.info("test3");
    }
    @Test
    public void passedTest4() {
        LOGGER.info("test4");
    }
    @Test
    public void checkFromToPriceFilterTest() {
        RemoteWebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.writeInSearchLine("samsung");
        homePage.clickSearchButton();

        FromToPriceLink fromToPriceLink = new FromToPriceLink(driver);
        fromToPriceLink.click();
        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        Assert.assertNotEquals(searchedResultPage.itemTitlesCount(), 0, "There are no searched items in list.");
        Assert.assertNotEquals(searchedResultPage.itemPricesCount(), 0, "There are no searched items prices in list.");

        SoftAssert softAssertTitle = new SoftAssert();
        searchedResultPage.getItemsNames().forEach(itemName -> {
            softAssertTitle.assertTrue(itemName.toLowerCase(Locale.ROOT).contains("samsung"),
                    String.format("Product title doesn't contain brand name \"%s\".", "samsung"));
            LOGGER.info(itemName);
        });
        softAssertTitle.assertAll();

        SoftAssert softAssertMinPrice = new SoftAssert();
        searchedResultPage.getMinPrices().forEach(actualItemPrice -> {
            softAssertMinPrice.assertTrue(fromToPriceLink.getFromPrice() <= actualItemPrice,
                    String.format("Price %d is lower then %d.", actualItemPrice, fromToPriceLink.getFromPrice()));
        });
        softAssertMinPrice.assertAll();

        SoftAssert softAssertMaxPrice = new SoftAssert();
        searchedResultPage.getMaxPrices().forEach(actualItemPrice -> {
            softAssertMaxPrice.assertTrue(actualItemPrice <= fromToPriceLink.getToPrice(),
                    String.format("Price %d is bigger then %d.", actualItemPrice, fromToPriceLink.getFromPrice()));
        });
        softAssertMaxPrice.assertAll();

    }

    @Test
    public void checkStorageCapacityFilter() {
        RemoteWebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.writeInSearchLine("samsung");
        homePage.clickSearchButton();

        StorageCapacityBlock storageCapacityBlock = new StorageCapacityBlock(driver);
        storageCapacityBlock.clickCheckbox();

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        Assert.assertNotEquals(searchedResultPage.itemTitlesCount(), 0, "There are no searched items in list.");

        SoftAssert softAssertItemStorageCapacity = new SoftAssert();
        searchedResultPage.getItemsNames().forEach(itemName -> {
            TabService tabService = new TabService(driver);
            searchedResultPage.clickOnProductByName(itemName);
            tabService.switchToNewTab();
            ///logic
            tabService.closeNewTabAndSwitchBack();
        });
        softAssertItemStorageCapacity.assertAll();
    }

}
