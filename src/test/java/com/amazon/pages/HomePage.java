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

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        try {
            driver.get("https://www.amazon.com");
            wait.until(ExpectedConditions.titleContains("Amazon"));
            
            // JavaScript ile sayfanın tamamen yüklenmesini bekle
            ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            
            // Arama kutusunun görünür olmasını bekle
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));
            wait.until(ExpectedConditions.visibilityOf(searchBox));
            
        } catch (Exception e) {
            throw new RuntimeException("Ana sayfa yüklenemedi: " + e.getMessage());
        }
    }

    public void enterSearchText(String searchText) {
        try {
            // JavaScript ile elementi bul ve tıkla
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            element.clear();
            element.sendKeys(searchText);
        } catch (Exception e) {
            throw new RuntimeException("Arama kutusu bulunamadı: " + e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            // JavaScript ile arama butonunu bul ve tıkla
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-search-submit-button")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            throw new RuntimeException("Arama butonu bulunamadı: " + e.getMessage());
        }
    }
}