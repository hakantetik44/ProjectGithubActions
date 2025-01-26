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
    
    // Locators
    private final By searchBox = By.id("search-words");
    private final By searchButton = By.cssSelector("button[data-spm-click*='search']");
    private final By cookieAcceptButton = By.className("btn-accept");
    private final By notificationDenyButton = By.cssSelector("div.Sk1_X._1-SOk");
    private final By searchResults = By.cssSelector(".search-card-item");
    private final By productTitles = By.cssSelector(".multi--titleText--nXeOvyr");

    public AliExpressHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Ana sayfaya git")
    public void navigateToHomePage() {
        driver.get("https://www.aliexpress.com");
        handlePopups();
    }

    @Step("Popup'ları kapat")
    private void handlePopups() {
        try {
            // Cookie popup'ını kapat
            if (isElementPresent(cookieAcceptButton)) {
                driver.findElement(cookieAcceptButton).click();
                System.out.println("Cookie popup'ı kapatıldı");
            }
        } catch (Exception e) {
            System.out.println("Cookie popup'ı kapatılamadı: " + e.getMessage());
        }

        try {
            // Bildirim popup'ını kapat
            if (isElementPresent(notificationDenyButton)) {
                driver.findElement(notificationDenyButton).click();
                System.out.println("Bildirim popup'ı kapatıldı");
            }
        } catch (Exception e) {
            System.out.println("Bildirim popup'ı kapatılamadı: " + e.getMessage());
        }
    }

    @Step("Ürün ara: {0}")
    public void searchProduct(String productName) {
        try {
            // Arama kutusunu bekle ve tıkla
            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            search.click();
            search.clear();
            search.sendKeys(productName);
            
            // Enter tuşuna bas
            search.sendKeys(Keys.ENTER);
            System.out.println("Ürün araması yapıldı: " + productName);
            
        } catch (Exception e) {
            System.out.println("Ürün araması yapılamadı: " + e.getMessage());
            throw e;
        }
    }

    @Step("Arama sonuçlarını doğrula: {0}")
    public void verifySearchResults(String searchTerm) {
        try {
            // Arama sonuçlarını bekle
            wait.until(ExpectedConditions.presenceOfElementLocated(searchResults));
            
            // Ürün başlıklarını al
            var titles = driver.findElements(productTitles);
            System.out.println("Bulunan ürün sayısı: " + titles.size());
            
            // En az bir ürün bulunduğunu doğrula
            Assert.assertTrue(titles.size() > 0, "Hiç ürün bulunamadı");
            
            // Ürün başlıklarını kontrol et
            boolean foundMatch = false;
            for (WebElement title : titles) {
                String titleText = title.getText().toLowerCase();
                if (titleText.contains(searchTerm.toLowerCase())) {
                    foundMatch = true;
                    break;
                }
            }
            
            Assert.assertTrue(foundMatch, "Arama terimi ile eşleşen ürün bulunamadı: " + searchTerm);
            System.out.println("Arama sonuçları doğrulandı");
            
        } catch (Exception e) {
            System.out.println("Arama sonuçları doğrulanamadı: " + e.getMessage());
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