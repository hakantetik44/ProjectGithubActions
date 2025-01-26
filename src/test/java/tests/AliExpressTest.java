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
        // Chrome Flags
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-features=IsolateOrigins,site-per-process");
        
        // Performance flags
        options.addArguments("--aggressive-cache-discard");
        options.addArguments("--disable-cache");
        options.addArguments("--disable-application-cache");
        options.addArguments("--disable-offline-load-stale-cache");
        options.addArguments("--disk-cache-size=0");
        
        // Memory flags
        options.addArguments("--disable-features=TranslateUI");
        options.addArguments("--disable-features=BlinkGenPropertyTrees");
        options.addArguments("--disable-features=LazyFrameLoading");
        options.addArguments("--disable-features=PreloadMediaEngagementData");
        
        // Page load strategy
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        
        // Timeouts
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        homePage = new AliExpressHomePage(driver, wait);
        
        try {
            // Set language cookie
            driver.get("about:blank");
            driver.manage().addCookie(new Cookie("aep_usuc_f", "site=glo&c_tp=USD&region=US&b_locale=en_US"));
            
            // Navigate to homepage
            driver.get("https://www.aliexpress.com");
            System.out.println("Ana sayfaya gidildi: " + driver.getCurrentUrl());
            
            // Wait for page load
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Setup sırasında hata: " + e.getMessage());
            Allure.addAttachment("Setup Error", e.getMessage());
            if (driver != null) {
                Allure.addAttachment("Setup Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            }
        }
    }

    @Test(priority = 1)
    @Description("Ürün arama testi")
    @Story("Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchProduct() throws InterruptedException {
        try {
            // Ana sayfaya git ve bekle
            homePage.navigateToHomePage();
            Thread.sleep(5000);
            Allure.addAttachment("Homepage Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            
            // Ürün ara
            String searchTerm = "laptop";
            homePage.searchProduct(searchTerm);
            Thread.sleep(3000);
            Allure.addAttachment("Search Results Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            
            // URL ve sayfa bilgilerini logla
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            Allure.addAttachment("Current URL", currentUrl);
            
            // Arama sonuçlarını doğrula
            homePage.verifySearchResults(searchTerm);
            Allure.addAttachment("Final Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            
        } catch (Exception e) {
            System.out.println("Test sırasında hata: " + e.getMessage());
            Allure.addAttachment("Error Details", e.getMessage());
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            throw e;
        }
    }

    @AfterMethod
    @Description("Test sonrası temizlik")
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test başarısız: " + result.getName());
            System.out.println("Hata: " + result.getThrowable().getMessage());
            Allure.addAttachment("Failure Details", result.getThrowable().getMessage());
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @AfterClass
    @Description("Test ortamını temizle")
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Driver başarıyla kapatıldı");
            }
        } catch (Exception e) {
            System.out.println("Driver kapatılırken hata: " + e.getMessage());
        }
    }
} 