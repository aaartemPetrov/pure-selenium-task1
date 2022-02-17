package com.solvd.task1;

import com.solvd.task1.page.*;
import com.solvd.task1.page.components.*;
import com.solvd.task1.service.TabService;
import com.solvd.task1.service.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class EbayTest extends AbstractTest {

    @Test
    public void checkSearchTooltipTest() {
        WebDriver driver = WebDriverPool.get();
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
        WebDriver driver = WebDriverPool.get();
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
        WebDriver driver = WebDriverPool.get();
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
        searchedResultPage.getIntItemPrices().forEach(itemPrices -> {
            int actualItemPrice = Integer.parseInt(itemPrices[0]);
            softAssertPrice.assertTrue(actualItemPrice <= underPriceLink.getPrice(), String.format("Price %d is bigger then %d", actualItemPrice, underPriceLink.getPrice()));
        });
        softAssertPrice.assertAll();
    }

    @Test
    public void checkFromToPriceFilterTest() {
        WebDriver driver = WebDriverPool.get();
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
            softAssertTitle.assertTrue(itemName.toLowerCase(Locale.ROOT).contains("samsung"), String.format("Product title doesn't contain brand name \"%s\"", "samsung"));
            LOGGER.info(itemName);
        });
        softAssertTitle.assertAll();

        SoftAssert softAssertPrice = new SoftAssert();
        searchedResultPage.getIntItemPrices().forEach(itemPrices -> {
            if (itemPrices.length == 1) {
                int actualPrice = Integer.parseInt(itemPrices[0]);
                softAssertPrice.assertTrue(fromToPriceLink.getFromPrice() <= actualPrice && actualPrice <= fromToPriceLink.getToPrice(), String.format("Price %d is not in range from %d to %d", actualPrice, fromToPriceLink.getFromPrice(), fromToPriceLink.getToPrice()));
            } else {
                int actualMinPrice = Integer.parseInt(itemPrices[0]);
                int actualMaxPrice = Integer.parseInt(itemPrices[1]);
                softAssertPrice.assertTrue(fromToPriceLink.getFromPrice() <= actualMinPrice && actualMaxPrice <= fromToPriceLink.getToPrice(), String.format("Price %d-%d is not in range from %d to %d", actualMinPrice, actualMaxPrice, fromToPriceLink.getFromPrice(), fromToPriceLink.getToPrice()));
            }
        });
        softAssertPrice.assertAll();
    }

    @Test
    public void checkStorageCapacityFilter() {
        WebDriver driver = WebDriverPool.get();
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
