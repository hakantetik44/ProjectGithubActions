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

    @FindBy(xpath = "//input[@type='text' and @id='twotabsearchtextbox']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[@type='submit' and @id='nav-search-submit-button']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        try {
            driver.get("https://www.amazon.com");
            
            // Sayfa yüklenene kadar bekle
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
            
            // Title kontrolü
            wait.until(ExpectedConditions.titleContains("Amazon"));
            
            // Arama kutusunu bekle
            By searchBoxLocator = By.xpath("//input[@type='text' and @id='twotabsearchtextbox']");
            wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));
            wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
            
        } catch (TimeoutException e) {
            throw new RuntimeException("Sayfa yüklenme zaman aşımı: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Ana sayfa yüklenemedi: " + e.getMessage());
        }
    }

    public void enterSearchText(String searchText) {
        try {
            // Arama kutusunu bekle ve doldur
            wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            searchBox.clear();
            searchBox.sendKeys(searchText);
            
        } catch (TimeoutException e) {
            throw new RuntimeException("Arama kutusu zaman aşımı: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Arama kutusu bulunamadı: " + e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            // Arama butonunu bekle ve tıkla
            wait.until(ExpectedConditions.elementToBeClickable(searchButton));
            searchButton.click();
            
        } catch (TimeoutException e) {
            throw new RuntimeException("Arama butonu zaman aşımı: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Arama butonu bulunamadı: " + e.getMessage());
        }
    }
}