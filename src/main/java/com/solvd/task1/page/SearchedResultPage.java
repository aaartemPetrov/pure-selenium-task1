package com.solvd.task1.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchedResultPage extends AbstractPage {

    @FindBy(css = ".srp-results *[class=s-item__title]")
    private List<WebElement> itemsTitles;
    @FindBy(css = ".srp-results div.s-item__detail:first-of-type span.s-item__price")
    private List<WebElement> itemsPrices;
    @FindBy(css = ".srp-results a.s-item__link")
    private List<WebElement> itemsLinks;
    @FindBy(css = "#s0-14-11-6-3-query_answer1-answer-2-1-0-list li:nth-child(2)")
    private WebElement fromToPriceLink;

    public void clickOnProductByName(String productName) {
        this.itemsLinks.forEach(itemLink -> {
            System.out.println(itemLink.getText());
            if(productName.toLowerCase(Locale.ROOT).equals(itemLink.getText().toLowerCase(Locale.ROOT))) {
                this.click(itemLink);
            }
        });
    }

    public SearchedResultPage(RemoteWebDriver driver) {
        super(driver);
        setPageURL(driver.getCurrentUrl());
    }

    public List<String> getItemsNames() {
        return this.itemsTitles.stream()
                .map(itemTitle -> itemTitle.getText())
                .collect(Collectors.toList());
    }

    public List<Integer> getMinPrices() {
        return this.getItemPrices().stream()
                .map(itemPrices -> itemPrices.get(0))
                .collect(Collectors.toList());
    }

    public List<Integer> getMaxPrices() {
        return this.getItemPrices().stream()
                .map(itemPrices -> {
                    if(itemPrices.size() == 1) {
                        return itemPrices.get(0);
                    } else {
                        return itemPrices.get(1);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<List<Integer>> getItemPrices() {
        return this.itemsPrices.stream()
                .map(itemPrice -> {
                    String itemPriceString = itemPrice.getText();
                    itemPriceString = StringUtils.replaceChars(itemPriceString, ",", ".");
                    return Arrays.stream(StringUtils.substringsBetween(itemPriceString, "$", "."))
                            .map(price -> Integer.parseInt(price)).collect(Collectors.toList());
                })
                .collect(Collectors.toList());
    }

    public int itemTitlesCount() {
        return this.itemsTitles.size();
    }

    public int itemPricesCount() {
        return this.itemsTitles.size();
    }

}
