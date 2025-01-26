package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AliExpressHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    public AliExpressHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By searchBox = By.cssSelector("input[type='search'], input[placeholder*='search'], #search-words, .search--keyword--15P08Ji");
    
    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Uyku sırasında kesinti: " + e.getMessage());
        }
    }
    
    public void searchProduct(String product) {
        try {
            System.out.println("Sayfa yüklenmesi bekleniyor...");
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
            
            System.out.println("Arama kutusu bekleniyor...");
            WebElement searchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            
            System.out.println("Arama kutusuna tıklanıyor...");
            searchElement.click();
            sleep(1000);
            
            System.out.println("Arama metni yazılıyor: " + product);
            searchElement.clear();
            searchElement.sendKeys(product);
            sleep(1000);
            
            System.out.println("Enter tuşuna basılıyor...");
            searchElement.sendKeys(Keys.ENTER);
            
            System.out.println("Arama tamamlandı. Yeni URL: " + driver.getCurrentUrl());
            
        } catch (Exception e) {
            System.out.println("Arama sırasında hata oluştu: " + e.getMessage());
            System.out.println("Mevcut URL: " + driver.getCurrentUrl());
            throw e;
        }
    }
} 