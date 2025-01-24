package com.amazon.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Alternatif locator'lar ekleyelim
    private By searchBoxLocator = By.cssSelector("#twotabsearchtextbox, input[name='field-keywords']");
    private By searchButtonLocator = By.cssSelector("#nav-search-submit-button, input[type='submit'][value='Go']");
    private By searchResultsLocator = By.cssSelector(".s-result-list, .s-search-results");
    private By searchBreadcrumbLocator = By.cssSelector(".s-breadcrumb, .a-breadcrumb");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Timeout süresini artırdık
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void enterSearchTerm(String searchTerm) {
        try {
            // Önce sayfanın yüklenmesini bekleyelim
            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
            
            // Element için birkaç farklı yöntem deneyelim
            WebElement searchBox = null;
            try {
                searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBoxLocator));
            } catch (TimeoutException e) {
                // JavaScript ile elementi bulmayı deneyelim
                searchBox = (WebElement) js.executeScript(
                    "return document.querySelector('#twotabsearchtextbox, input[name=\"field-keywords\"]')");
            }

            if (searchBox != null) {
                // JavaScript ile elementi temizleyip değer girelim
                js.executeScript("arguments[0].value = ''", searchBox);
                js.executeScript("arguments[0].value = arguments[1]", searchBox, searchTerm);
            } else {
                throw new ElementNotInteractableException("Search box could not be found or interacted with");
            }
        } catch (Exception e) {
            System.out.println("Error entering search term: " + e.getMessage());
            throw e;
        }
    }

    public void clickSearchButton() {
        try {
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
            js.executeScript("arguments[0].click()", searchButton);
        } catch (TimeoutException e) {
            // Enter tuşu ile aramayı deneyelim
            driver.findElement(searchBoxLocator).sendKeys(Keys.ENTER);
        }
    }

    public boolean areSearchResultsDisplayed() {
        try {
            // Sayfa yüklenene kadar bekleyelim
            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
            
            // Sonuçların görünmesini bekleyelim
            return wait.until(driver -> {
                try {
                    WebElement results = driver.findElement(searchResultsLocator);
                    return results.isDisplayed();
                } catch (Exception e) {
                    return false;
                }
            });
        } catch (Exception e) {
            System.out.println("Error checking search results: " + e.getMessage());
            return false;
        }
    }

    public boolean doesSearchResultContain(String searchTerm) {
        try {
            // Sayfa yüklenene kadar bekleyelim
            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
            
            // Sayfadaki tüm metni kontrol edelim
            String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();
            return pageText.contains(searchTerm.toLowerCase());
        } catch (Exception e) {
            System.out.println("Error checking search term in results: " + e.getMessage());
            return false;
        }
    }
} 