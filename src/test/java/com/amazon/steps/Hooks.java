package com.amazon.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;

public class Hooks {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage",
                               "--disable-gpu", "--window-size=1920,1080", "--remote-allow-origins=*");
            options.setCapability("acceptInsecureCerts", true);
            
            String seleniumUrl = System.getProperty("selenium.grid.url", "http://selenium-chrome:4444/wd/hub");
            driver = new RemoteWebDriver(new URL(seleniumUrl), options);
            
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            
            Allure.addAttachment("Browser Session", seleniumUrl);
        } catch (Exception e) {
            Allure.addAttachment("Setup Error", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Failed Screenshot", "image/png", 
                        new ByteArrayInputStream(screenshot), "png");
                    Allure.addAttachment("Page URL", driver.getCurrentUrl());
                    Allure.addAttachment("Page Source", driver.getPageSource());
                }
            } catch (Exception e) {
                Allure.addAttachment("Teardown Error", e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}