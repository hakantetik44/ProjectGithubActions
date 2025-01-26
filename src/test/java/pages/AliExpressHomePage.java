package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class AliExpressHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;
    private final WebDriverWait longWait;
    
    // Locators
    private final By searchBox = By.cssSelector("input[type='search'], input[placeholder*='search'], #search-words, .search--keyword--15P08Ji");
    private final By searchButton = By.cssSelector("button[data-spm-click*='search']");
    private final By cookieAcceptButton = By.cssSelector(".btn-accept, .close-btn");
    private final By notificationDenyButton = By.cssSelector("div.Sk1_X._1-SOk, .close-btn");
    private final By searchResults = By.cssSelector("[class*='SearchProductFeed']");
    private final By productTitles = By.cssSelector("[class*='manhattan--titleText'], [class*='manhattan--container'] h1, .multi--titleText--nXeOvyr, .product-title");

    public AliExpressHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @Step("Ana sayfaya git")
    public void navigateToHomePage() {
        try {
            driver.get("https://www.aliexpress.com");
            // Sayfa yüklenmesini bekle
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            handlePopups();
        } catch (Exception e) {
            System.out.println("Ana sayfaya gidilemedi: " + e.getMessage());
            throw e;
        }
    }

    @Step("Popup'ları kapat")
    private void handlePopups() {
        try {
            // Cookie popup'ını kapat
            shortWait.until(ExpectedConditions.presenceOfElementLocated(cookieAcceptButton));
            if (isElementPresent(cookieAcceptButton)) {
                driver.findElement(cookieAcceptButton).click();
                System.out.println("Cookie popup'ı kapatıldı");
            }
        } catch (Exception e) {
            System.out.println("Cookie popup'ı kapatılamadı: " + e.getMessage());
        }

        try {
            // Bildirim popup'ını kapat
            shortWait.until(ExpectedConditions.presenceOfElementLocated(notificationDenyButton));
            if (isElementPresent(notificationDenyButton)) {
                driver.findElement(notificationDenyButton).click();
                System.out.println("Bildirim popup'ı kapatıldı");
            }
        } catch (Exception e) {
            System.out.println("Bildirim popup'ı kapatılamadı: " + e.getMessage());
        }
    }

    @Step("Ürün ara: {0}")
    public void searchProduct(String productName) throws InterruptedException {
        try {
            // Arama kutusunu bekle
            WebElement search = longWait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
            
            // JavaScript ile scroll ve focus
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", search);
            ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", search);
            Thread.sleep(1000);
            
            // Mevcut değeri temizle
            search.clear();
            Thread.sleep(500);
            
            // Değeri gir
            search.sendKeys(productName);
            Thread.sleep(1000);
            
            // URL'yi kontrol et
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Arama öncesi URL: " + currentUrl);
            
            // Enter tuşuna bas
            search.sendKeys(Keys.ENTER);
            System.out.println("Enter tuşuna basıldı");
            
            // URL değişimini bekle
            longWait.until(driver -> {
                String newUrl = driver.getCurrentUrl();
                System.out.println("Yeni URL: " + newUrl);
                return !newUrl.equals(currentUrl);
            });
            
            // Arama sonuçlarını bekle
            longWait.until(ExpectedConditions.presenceOfElementLocated(searchResults));
            System.out.println("Arama sonuçları yüklendi");
            
            // Son URL'yi logla
            System.out.println("Son URL: " + driver.getCurrentUrl());
            
        } catch (Exception e) {
            System.out.println("Ürün araması yapılamadı: " + e.getMessage());
            System.out.println("Mevcut URL: " + driver.getCurrentUrl());
            System.out.println("Sayfa kaynağı uzunluğu: " + driver.getPageSource().length());
            throw e;
        }
    }

    @Step("Arama sonuçlarını doğrula: {0}")
    public void verifySearchResults(String searchTerm) throws InterruptedException {
        try {
            // Arama sonuçlarını bekle
            longWait.until(ExpectedConditions.presenceOfElementLocated(searchResults));
            Thread.sleep(2000); // Sayfa yüklenmesi için ek bekleme
            
            // URL'de arama terimini kontrol et
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Mevcut URL: " + currentUrl);
            
            // URL kontrolü
            boolean isSearchTermInUrl = currentUrl.toLowerCase().contains(searchTerm.toLowerCase()) || 
                                      currentUrl.toLowerCase().contains("searchtext=" + searchTerm.toLowerCase()) ||
                                      currentUrl.toLowerCase().contains("wholesale-" + searchTerm.toLowerCase());
            
            // Arama sonuçları kontrolü
            boolean hasSearchResults = driver.findElements(searchResults).size() > 0;
            
            // Sonuçları değerlendir
            if (isSearchTermInUrl && hasSearchResults) {
                System.out.println("✓ Arama sonuçları başarıyla doğrulandı");
                Assert.assertTrue(true, "Arama sonuçları başarıyla doğrulandı. URL: " + currentUrl);
            } else {
                String errorMessage = "Arama sonuçları doğrulanamadı:\n";
                errorMessage += "- URL'de arama terimi var mı: " + isSearchTermInUrl + "\n";
                errorMessage += "- Arama sonuçları görüntülendi mi: " + hasSearchResults + "\n";
                errorMessage += "- Mevcut URL: " + currentUrl;
                
                Assert.fail(errorMessage);
            }
            
        } catch (Exception e) {
            System.out.println("Arama sonuçları doğrulanamadı: " + e.getMessage());
            System.out.println("Mevcut URL: " + driver.getCurrentUrl());
            throw e;
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            return !driver.findElements(locator).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
} 