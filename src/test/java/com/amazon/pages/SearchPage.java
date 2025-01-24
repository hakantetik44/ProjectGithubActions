package com.amazon.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Çoklu locator stratejileri
    private final String[] SEARCH_BOX_SELECTORS = {
        "#twotabsearchtextbox",
        "input[name='field-keywords']",
        "input[type='text']",
        "input[aria-label*='Search']",
        "//input[@id='twotabsearchtextbox']",
        "//input[@name='field-keywords']"
    };

    private final String[] SEARCH_BUTTON_SELECTORS = {
        "#nav-search-submit-button",
        "input[type='submit']",
        "input.nav-input[type='submit']",
        ".nav-search-submit input",
        "//input[@value='Go']",
        "//input[@type='submit']"
    };

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    private WebElement findElement(String[] selectors) {
        WebElement element = null;
        String lastError = "";

        for (String selector : selectors) {
            try {
                // CSS Selector dene
                if (!selector.startsWith("//")) {
                    element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
                } else {
                    // XPath dene
                    element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                }
                if (element != null && element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
                lastError = e.getMessage();
                // JavaScript ile dene
                try {
                    String jsSelector = selector.startsWith("//") ? 
                        String.format("document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue", selector) :
                        String.format("document.querySelector(\"%s\")", selector);
                    
                    element = (WebElement) js.executeScript("return " + jsSelector);
                    if (element != null && (Boolean) js.executeScript("return arguments[0].offsetParent !== null", element)) {
                        return element;
                    }
                } catch (Exception jsError) {
                    lastError = jsError.getMessage();
                }
            }
        }

        if (element == null) {
            System.out.println("Failed to find element. Last error: " + lastError);
            throw new NoSuchElementException("Element not found with any of the provided selectors");
        }
        return element;
    }

    public void enterSearchTerm(String searchTerm) {
        try {
            // Sayfa yüklenene kadar bekle
            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
            Thread.sleep(2000); // Kısa bir bekleme ekleyelim

            WebElement searchBox = findElement(SEARCH_BOX_SELECTORS);
            
            // Farklı yöntemlerle değer girmeyi dene
            try {
                searchBox.clear();
                searchBox.sendKeys(searchTerm);
            } catch (Exception e) {
                js.executeScript("arguments[0].value = ''", searchBox);
                js.executeScript("arguments[0].value = arguments[1]", searchBox, searchTerm);
            }
            
            // Değerin girildiğinden emin ol
            wait.until(driver -> {
                String value = searchBox.getAttribute("value");
                return value != null && value.equals(searchTerm);
            });
            
        } catch (Exception e) {
            System.out.println("Error entering search term: " + e.getMessage());
            throw new RuntimeException("Failed to enter search term: " + searchTerm, e);
        }
    }

    public void clickSearchButton() {
        try {
            try {
                WebElement searchButton = findElement(SEARCH_BUTTON_SELECTORS);
                try {
                    searchButton.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click()", searchButton);
                }
            } catch (Exception e) {
                // Enter tuşu ile aramayı dene
                WebElement searchBox = findElement(SEARCH_BOX_SELECTORS);
                searchBox.sendKeys(Keys.ENTER);
            }
            
            // Sayfanın yüklenmesini bekle
            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
            
        } catch (Exception e) {
            System.out.println("Error clicking search button: " + e.getMessage());
            throw new RuntimeException("Failed to click search button", e);
        }
    }

    public boolean areSearchResultsDisplayed() {
        try {
            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
            Thread.sleep(2000); // Kısa bir bekleme

            // Farklı sonuç göstergelerini kontrol et
            return wait.until(driver -> {
                try {
                    // URL kontrolü
                    String currentUrl = driver.getCurrentUrl();
                    if (currentUrl.contains("/s?k=") || currentUrl.contains("/search")) {
                        return true;
                    }

                    // Sonuç elementlerini kontrol et
                    List<WebElement> results = driver.findElements(By.cssSelector(
                        ".s-result-list .s-result-item, .s-search-results .s-result-item"));
                    return !results.isEmpty();
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
            wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
            Thread.sleep(2000); // Kısa bir bekleme

            return wait.until(driver -> {
                try {
                    // URL'de arama terimi kontrolü
                    String currentUrl = driver.getCurrentUrl().toLowerCase();
                    if (currentUrl.contains(searchTerm.toLowerCase())) {
                        return true;
                    }

                    // Sayfa içeriğinde arama
                    String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();
                    return pageText.contains(searchTerm.toLowerCase());
                } catch (Exception e) {
                    return false;
                }
            });
        } catch (Exception e) {
            System.out.println("Error checking search term in results: " + e.getMessage());
            return false;
        }
    }
} 