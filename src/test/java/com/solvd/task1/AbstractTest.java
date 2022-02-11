package com.solvd.task1;

import com.solvd.task1.page.*;
import com.solvd.task1.page.components.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
    private final Map<Long, WebDriver> threadIdDrivers = new HashMap<>();

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/apetrov/Documents/SeleniumServer/chromedriver");
        this.threadIdDrivers.put(Thread.currentThread().getId(), new ChromeDriver());
    }

    @Test
    public void checkSearchTooltipTest() {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        AbstractPage.sendKeys(driver, homePage.getSearchInput(), "a");

        SearchTooltip searchTooltip = new SearchTooltip(driver);
        WebElement tooltip = searchTooltip.getSearchTooltip();
        Assert.assertNotNull(tooltip, "Tooltip is null.");
        Assert.assertFalse(searchTooltip.getTooltips().isEmpty(), "Tooltip popup is empty");
    }

    @DataProvider(name = "brandNamesForSearch")
    public Object[][] names() {
        return new Object[][]{{"Sony"}, {"Samsung"}, {"Apple"}, {"Xiaomi"}, {"Huawei"}};
    }

    @Test(dataProvider = "brandNamesForSearch")
    public void checkSearchTest(String brandName) {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        AbstractPage.sendKeys(driver, homePage.getSearchInput(), brandName);
        AbstractPage.click(driver, homePage.getSearchButton());

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        List<WebElement> searchedItems = searchedResultPage.getSearchedItemsTitles();
        Assert.assertFalse(searchedItems.isEmpty(), "There are no searched items in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedItems.forEach(searchedItem -> {
            softAssert.assertTrue(searchedItem.getText().toLowerCase(Locale.ROOT).contains(brandName.toLowerCase(Locale.ROOT)),
                    String.format("Product title does not contain brand name \"%s\"", brandName));
            LOGGER.info(searchedItem.getText());
        });
        softAssert.assertAll();
    }

    @Test
    public void checkUnderPriceFilterTest() {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        AbstractPage.sendKeys(driver, homePage.getSearchInput(), "samsung");
        AbstractPage.click(driver, homePage.getSearchButton());

        UnderPriceLink underPriceLink = new UnderPriceLink(driver);
        AbstractPage.click(driver, underPriceLink.getLink());

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        List<WebElement> searchedItemsTitles = searchedResultPage.getSearchedItemsTitles();
        List<WebElement> searchedItemsPrices = searchedResultPage.getSearchedItemsPrices();
        Assert.assertFalse(searchedItemsTitles.isEmpty(), "There are no searched items titles in list.");
        Assert.assertFalse(searchedItemsPrices.isEmpty(), "There are no searched items prices in list.");

        SoftAssert softAssertTitle = new SoftAssert();
        searchedItemsTitles.forEach(searchedItemTitle -> {
            softAssertTitle.assertTrue(searchedItemTitle.getText().toLowerCase(Locale.ROOT).contains("samsung"),
                    String.format("Product title doesn't contain brand name \"%s\"", "samsung"));
            LOGGER.info(searchedItemTitle.getText());
        });
        softAssertTitle.assertAll();

        SoftAssert softAssertPrice = new SoftAssert();
        searchedItemsPrices.forEach(searchedItemPrice -> {
            String searchedItemPriceString = searchedItemPrice.getText();
            searchedItemPriceString = StringUtils.replaceChars(searchedItemPriceString, ",", ".");
            int actualPrice = Integer.parseInt(StringUtils.substringBetween(searchedItemPriceString, "$", "."));
            softAssertPrice.assertTrue(actualPrice <= underPriceLink.getPrice(),
                    String.format("Price %d is bigger then %d", actualPrice, underPriceLink.getPrice()));
        });
        softAssertPrice.assertAll();
    }

    @Test
    public void checkFromToPriceFilterTest() {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        AbstractPage.sendKeys(driver, homePage.getSearchInput(), "samsung");
        AbstractPage.click(driver, homePage.getSearchButton());

        FromToPriceLink fromToPriceLink = new FromToPriceLink(driver);
        AbstractPage.click(driver, fromToPriceLink.getLink());

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        List<WebElement> searchedItemsTitles = searchedResultPage.getSearchedItemsTitles();
        List<WebElement> searchedItemsPrices = searchedResultPage.getSearchedItemsPrices();
        Assert.assertFalse(searchedItemsTitles.isEmpty(), "There are no searched items titles in list.");
        Assert.assertFalse(searchedItemsPrices.isEmpty(), "There are no searched items prices in list.");

        SoftAssert softAssertTitle = new SoftAssert();
        searchedItemsTitles.forEach(searchedItemTitle -> {
            softAssertTitle.assertTrue(searchedItemTitle.getText().toLowerCase(Locale.ROOT).contains("samsung"),
                    String.format("Product title doesn't contain brand name \"%s\"", "samsung"));
            LOGGER.info(searchedItemTitle.getText());
        });
        softAssertTitle.assertAll();

        SoftAssert softAssertPrice = new SoftAssert();
        searchedItemsPrices.forEach(searchedItemPrice -> {
            String searchedItemPriceString = searchedItemPrice.getText();
            searchedItemPriceString = StringUtils.replaceChars(searchedItemPriceString, ",", ".");
            String[] prices = StringUtils.substringsBetween(searchedItemPriceString, "$", ".");

            if (prices.length == 1) {
                int actualPrice = Integer.parseInt(prices[0]);
                softAssertPrice.assertTrue(fromToPriceLink.getFromPrice() <= actualPrice
                                && actualPrice <= fromToPriceLink.getToPrice(),
                        String.format("Price %d is not in range from %d to %d", actualPrice,
                                fromToPriceLink.getFromPrice(), fromToPriceLink.getToPrice()));
            } else {
                int actualMinPrice = Integer.parseInt(prices[0]);
                int actualMaxPrice = Integer.parseInt(prices[1]);
                softAssertPrice.assertTrue(fromToPriceLink.getFromPrice() <= actualMinPrice
                                && actualMaxPrice <= fromToPriceLink.getToPrice(),
                        String.format("Price %d-%d is not in range from %d to %d", actualMinPrice, actualMaxPrice,
                                fromToPriceLink.getFromPrice(), fromToPriceLink.getToPrice()));
            }
        });
        softAssertPrice.assertAll();
    }

    @Test
    public void checkStorageCapacityFilter() {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        AbstractPage.sendKeys(driver, homePage.getSearchInput(), "samsung");
        AbstractPage.click(driver, homePage.getSearchButton());

        StorageCapacityBlock storageCapacityBlock = new StorageCapacityBlock(driver);
        storageCapacityBlock.clickCheckbox();

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        List<WebElement> searchedItemsTitles = searchedResultPage.getSearchedItemsTitles();
        Assert.assertFalse(searchedItemsTitles.isEmpty(), "There are no searched items titles in list.");

        SoftAssert softAssertItemStorageCapacity = new SoftAssert();
        searchedResultPage.getSearchedItemsLinks().forEach(searchedItemLink -> {
            String oldTab = driver.getWindowHandle();
            AbstractPage.click(driver, searchedItemLink);
            List<String> newTab = new ArrayList<>(driver.getWindowHandles());
            newTab.remove(oldTab);
            driver.switchTo().window(newTab.get(0));

            ItemsSpecificBlock itemsSpecificBlock = new ItemsSpecificBlock(driver);
            String itemStorageCapacity = itemsSpecificBlock.getStorageCapacity().getText();
            itemStorageCapacity = StringUtils.deleteWhitespace(itemStorageCapacity);
            softAssertItemStorageCapacity.assertEquals(itemStorageCapacity.toLowerCase(Locale.ROOT),
                    storageCapacityBlock.getLabelText().toLowerCase(Locale.ROOT),
                    String.format("Product storage capacity is not %s.", storageCapacityBlock.getLabelText()));

            driver.close();
            driver.switchTo().window(oldTab);
        });
        softAssertItemStorageCapacity.assertAll();
    }

    @AfterMethod
    public void end() {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        driver.close();
        driver.quit();
    }

}
