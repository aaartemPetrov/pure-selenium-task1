package com.solvd.task1.service;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebDriverPool {

    private static final Map<Long, WebDriver> threadIdDrivers = new HashMap<>();

    public static WebDriver get() {
        return threadIdDrivers.get(Thread.currentThread().getId());
    }

    public static void add(WebDriver driver) {
        threadIdDrivers.put(Thread.currentThread().getId(), driver);
    }

    public static Set<Map.Entry<Long, WebDriver>> entrySet() {
        return threadIdDrivers.entrySet();
    }

}
