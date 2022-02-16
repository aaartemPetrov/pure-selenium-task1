package com.solvd.task1.service;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class TabService {

    private String oldTab;
    private List<String> newTabs;
    private WebDriver driver;

    public TabService(WebDriver driver) {
        this.driver = driver;
        this.oldTab = this.driver.getWindowHandle();
    }

    public void switchToNewTab() {
        this.newTabs = new ArrayList<>(driver.getWindowHandles());
        this.newTabs.remove(this.oldTab);
        this.driver.switchTo().window(this.newTabs.get(0));
    }

    public void closeNewTabAndSwitchBack() {
        this.driver.close();
        this.driver.switchTo().window(this.oldTab);
    }
}
