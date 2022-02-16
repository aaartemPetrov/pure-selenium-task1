package com.solvd.task1.page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SearchedResultPage {

    private final WebDriver driver;
    @FindBy(css = ".srp-results *[class=s-item__title]")
    private List<WebElement> itemsTitles;
    @FindBy(css = ".srp-results div.s-item__detail:first-of-type span.s-item__price")
    private List<WebElement> itemsPrices;
    @FindBy(css = ".srp-results a.s-item__link")
    private List<WebElement> itemsLinks;
    @FindBy(css = "#s0-14-11-6-3-query_answer1-answer-2-1-0-list li:nth-child(2)")
    private WebElement fromToPriceLink;


    public SearchedResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.driver.get(driver.getCurrentUrl());
    }

    public List<String> getItemsNames() {
        return itemsTitles.stream()
                .map(itemTitle -> itemTitle.getText())
                .collect(Collectors.toList());
    }

    public List<String[]> getIntItemPrices() {
        return itemsPrices.stream()
                .map(itemPrice -> {
                    String itemPriceString = itemPrice.getText();
                    itemPriceString = StringUtils.replaceChars(itemPriceString, ",", ".");
                    return StringUtils.substringsBetween(itemPriceString, "$", ".");
                })
                .collect(Collectors.toList());
    }

    public List<WebElement> getItemsTitles() {
        return this.itemsTitles;
    }

    public void setItemsTitles(List<WebElement> itemsTitles) {
        this.itemsTitles = itemsTitles;
    }

    public List<WebElement> getItemsPrices() {
        return this.itemsPrices;
    }

    public void setItemsPrices(List<WebElement> itemsPrices) {
        this.itemsPrices = itemsPrices;
    }

    public WebElement getFromToPriceLink() {
        return this.fromToPriceLink;
    }

    public void setFromToPriceLink(WebElement fromToPriceLink) {
        this.fromToPriceLink = fromToPriceLink;
    }

    public List<WebElement> getItemsLinks() {
        return this.itemsLinks;
    }

    public void setItemsLinks(List<WebElement> itemsLinks) {
        this.itemsLinks = itemsLinks;
    }

}
