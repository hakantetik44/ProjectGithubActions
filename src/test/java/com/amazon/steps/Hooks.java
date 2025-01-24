package com.amazon.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
            
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            
            Allure.addAttachment("Browser Session", "http://localhost:4444/wd/hub");
            DriverManager.setDriver(driver);
        } catch (Exception e) {
            Allure.addAttachment("Setup Error", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
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
                DriverManager.removeDriver();
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}