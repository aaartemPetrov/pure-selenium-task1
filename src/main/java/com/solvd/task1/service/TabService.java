package com.solvd.task1.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

public class TabService {

    private String oldTab;
    private List<String> newTabs;
    private RemoteWebDriver driver;

    public TabService(RemoteWebDriver driver) {
        this.driver = driver;
        this.oldTab = this.driver.getWindowHandle();
    }

    public void switchToNewTab() {
        this.newTabs = new ArrayList<>(this.driver.getWindowHandles());
        this.newTabs.remove(this.oldTab);
        this.driver.switchTo().window(this.newTabs.get(0));
    }

    public void closeNewTabAndSwitchBack() {
        this.driver.close();
        this.driver.switchTo().window(this.oldTab);
    }
}
