package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.io.File;

public class AliExpressHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;
    private final WebDriverWait longWait;
    
    // Locators
    private final By searchBox = By.cssSelector("input[type='search'], input[placeholder*='search'], #search-words, .search--keyword--15P08Ji");
    private final By searchButton = By.cssSelector("button[data-spm-click*='search']");
    private final By cookieAcceptButton = By.cssSelector(".btn-accept, .close-btn, button[data-role='accept']");
    private final By notificationDenyButton = By.cssSelector("div.Sk1_X._1-SOk, .close-btn");
    private final By searchResults = By.cssSelector("[class*='manhattan--container'], .list--gallery--C2f2tvm, .manhattan--content--R3f7kN");
    private final By productTitles = By.cssSelector("[class*='manhattan--titleText'], .multi--titleText--nXeOvyr, h1.manhattan--titleText--WccSjUS");

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
                return newUrl.contains("wholesale") || newUrl.contains("w/") || newUrl.contains("search");
            });
            
            // Sayfanın yüklenmesini bekle
            Thread.sleep(5000);
            
            // Arama sonuçlarını bekle
            longWait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(searchResults),
                ExpectedConditions.presenceOfElementLocated(productTitles)
            ));
            System.out.println("Arama sonuçları yüklendi");
            
            // Son URL'yi logla
            System.out.println("Son URL: " + driver.getCurrentUrl());
            
        } catch (Exception e) {
            System.out.println("Ürün araması yapılamadı: " + e.getMessage());
            System.out.println("Mevcut URL: " + driver.getCurrentUrl());
            System.out.println("Sayfa kaynağı uzunluğu: " + driver.getPageSource().length());
            
            // Hata durumunda ekran görüntüsü al
            if (driver instanceof TakesScreenshot) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                System.out.println("Hata anı ekran görüntüsü alındı: " + screenshot.getAbsolutePath());
            }
            
            throw e;
        }
    }

    @Step("Arama sonuçlarını doğrula: {0}")
    public void verifySearchResults(String searchTerm) throws InterruptedException {
        try {
            // Sayfanın tamamen yüklenmesi için bekle
            Thread.sleep(5000);
            
            // URL'yi kontrol et
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            System.out.println("Mevcut URL: " + currentUrl);
            
            // URL kontrolü - daha geniş kontrol kriterleri
            boolean isSearchTermInUrl = currentUrl.contains(searchTerm.toLowerCase()) || 
                                      currentUrl.contains("wholesale") ||
                                      currentUrl.contains("/w/") ||
                                      currentUrl.contains("search");
            
            // Arama sonuçları kontrolü - birden fazla locator ile deneme
            boolean hasSearchResults = driver.findElements(searchResults).size() > 0 || 
                                     driver.findElements(productTitles).size() > 0;
            
            if (isSearchTermInUrl && hasSearchResults) {
                System.out.println("✓ Arama sonuçları başarıyla doğrulandı");
                Assert.assertTrue(true, "Arama sonuçları başarıyla doğrulandı. URL: " + currentUrl);
            } else {
                String errorMessage = "Arama sonuçları doğrulanamadı:\n";
                errorMessage += "- URL'de arama kriterleri var mı: " + isSearchTermInUrl + "\n";
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