package tests;

import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AliExpressHomePage;
import java.time.Duration;
import java.io.ByteArrayInputStream;

@Epic("AliExpress E2E Tests")
@Feature("Shopping Flow")
public class AliExpressTest {
    private WebDriver driver;
    private AliExpressHomePage homePage;
    private WebDriverWait wait;

    @BeforeClass
    @Description("Test ortamını hazırla")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // Yeni headless modu
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        homePage = new AliExpressHomePage(driver, wait);
        
        // Set language cookie before navigating
        driver.get("https://www.aliexpress.com");
        driver.manage().addCookie(new Cookie("aep_usuc_f", "site=glo&c_tp=USD&region=US&b_locale=en_US"));
        
        // Navigate to homepage
        driver.get("https://www.aliexpress.com");
        System.out.println("Ana sayfaya gidildi: https://www.aliexpress.com");
        
        try {
            Thread.sleep(5000); // Sayfa tam olarak yüklenene kadar bekle
        } catch (InterruptedException e) {
            System.out.println("Bekleme sırasında kesinti: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    @Description("Ürün arama testi")
    @Story("Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchProduct() throws InterruptedException {
        try {
            homePage.navigateToHomePage();
            Thread.sleep(5000); // Sayfa yüklenme bekleme
            
            String searchTerm = "laptop";
            homePage.searchProduct(searchTerm);
            Thread.sleep(3000); // Arama sonuçları bekleme
            
            // URL ve sayfa kaynağını logla
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Source Length: " + driver.getPageSource().length());
            
            // Arama sonuçlarını doğrula
            homePage.verifySearchResults(searchTerm);
            
        } catch (Exception e) {
            // Hata durumunda screenshot al
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            throw e;
        }
    }

    @AfterMethod
    @Description("Test sonrası temizlik")
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Test başarısız olduğunda screenshot al
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @AfterClass
    @Description("Test ortamını temizle")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver kapatıldı");
        }
    }
} 