package com.solvd.task1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.solvd.task1.service.WebDriverPool;
import com.zebrunner.agent.core.registrar.Screenshot;


public class ScreenshotListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        if (!result.isSuccess()) {
            byte[] screenshotBytes = ((TakesScreenshot) WebDriverPool.get()).getScreenshotAs(OutputType.BYTES);
            Screenshot.upload(screenshotBytes, Calendar.getInstance().getTimeInMillis());
        }
    }
}