package com.solvd.task1.service;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class WebDriverPool {

    private static final Map<Long, RemoteWebDriver> threadIdDrivers = new HashMap<>();

    public static RemoteWebDriver get() {
        return threadIdDrivers.get(Thread.currentThread().getId());
    }

    public static void add(RemoteWebDriver driver) {
        threadIdDrivers.put(Thread.currentThread().getId(), driver);
    }

    public static Set<Map.Entry<Long, RemoteWebDriver>> entrySet() {
        return threadIdDrivers.entrySet();
    }

}
