package com.amazon.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        try {
            // Chrome'un bot algılamasını azaltmak için user-agent ekle
            ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
            
            driver.get("https://www.amazon.com");
            
            // Sayfa yüklenene kadar bekle
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
            
            // Title kontrolü
            wait.until(ExpectedConditions.titleContains("Amazon"));
            
            // Arama kutusunu JavaScript ile bul
            WebElement searchInput = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return document.querySelector('#twotabsearchtextbox')");
                
            if (searchInput == null) {
                throw new RuntimeException("Arama kutusu bulunamadı");
            }
            
            // Görünür olmasını bekle
            wait.until(ExpectedConditions.visibilityOf(searchInput));
            
        } catch (TimeoutException e) {
            throw new RuntimeException("Sayfa yüklenme zaman aşımı: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Ana sayfa yüklenemedi: " + e.getMessage());
        }
    }

    public void enterSearchText(String searchText) {
        try {
            // JavaScript ile elementi bul ve değer ata
            ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('#twotabsearchtextbox').value = arguments[0]", 
                searchText);
            
        } catch (Exception e) {
            throw new RuntimeException("Arama kutusu bulunamadı: " + e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            // JavaScript ile tıklama
            ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('#nav-search-submit-button').click()");
            
        } catch (Exception e) {
            throw new RuntimeException("Arama butonu bulunamadı: " + e.getMessage());
        }
    }
}