package com.amazon.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.URL;

public class Hooks {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: " + scenario.getName());
        Allure.addAttachment("Test Start Time", 
            LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            
            String seleniumUrl = System.getProperty("selenium.grid.url", "http://selenium-chrome:4444/wd/hub");
            driver = new RemoteWebDriver(new URL(seleniumUrl), options);
            
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            logger.info("Driver initialized successfully with Selenium Grid");
        } catch (Exception e) {
            logger.error("Failed to initialize driver: " + e.getMessage());
            Allure.addAttachment("Driver Init Error", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Scenario failed: " + scenario.getName());
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failed Screenshot", "image/png", 
                    new ByteArrayInputStream(screenshot), "png");
                Allure.addAttachment("Error Log", scenario.getId());
            } catch (Exception e) {
                logger.error("Failed to take screenshot: " + e.getMessage());
            }
        } else {
            logger.info("Scenario passed: " + scenario.getName());
            Allure.addAttachment("Test End Time", 
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        if (driver != null) {
            driver.quit();
            logger.info("Driver closed successfully");
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}