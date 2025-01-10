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
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;

public class Hooks {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: " + scenario.getName());
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            
            String seleniumUrl = System.getProperty("selenium.grid.url", "http://selenium-chrome:4444/wd/hub");
            driver = new RemoteWebDriver(new URL(seleniumUrl), options);
            
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            
            logger.info("WebDriver başarıyla başlatıldı");
        } catch (Exception e) {
            logger.error("WebDriver başlatılamadı: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    logger.error("Scenario failed: " + scenario.getName());
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Failed Screenshot", "image/png", 
                        new ByteArrayInputStream(screenshot), "png");
                    Allure.addAttachment("Page URL", driver.getCurrentUrl());
                    Allure.addAttachment("Page Source", driver.getPageSource());
                }
            } catch (Exception e) {
                logger.error("Screenshot alınamadı: " + e.getMessage());
            } finally {
                driver.quit();
                logger.info("Driver kapatıldı");
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}