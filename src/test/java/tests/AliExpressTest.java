package tests;

import io.qameta.allure.*;
import io.qameta.allure.model.Status;
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
    @Step("Test ortamını hazırla")
    public void setUp() throws InterruptedException {
        try {
            Allure.step("Chrome ayarlarını yapılandır");
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
            
            Allure.step("WebDriver'ı başlat");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
            
            homePage = new AliExpressHomePage(driver, wait);
            
            Allure.step("Dil ayarlarını yapılandır");
            driver.get("https://www.aliexpress.com");
            Thread.sleep(2000); // Sayfanın yüklenmesini bekle
            
            // JavaScript ile cookie ayarla
            ((JavascriptExecutor) driver).executeScript(
                "document.cookie = 'aep_usuc_f=site=glo&c_tp=USD&region=US&b_locale=en_US; path=/; domain=aliexpress.com';"
            );
            
            Allure.step("Ana sayfaya git");
            driver.get("https://www.aliexpress.com");
            Allure.addAttachment("Initial URL", driver.getCurrentUrl());
            
            Thread.sleep(5000);
            Allure.addAttachment("Setup Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            
        } catch (Exception e) {
            Allure.step("Setup sırasında hata oluştu", Status.FAILED);
            Allure.addAttachment("Setup Error", e.getMessage());
            if (driver != null) {
                Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            }
            throw e;
        }
    }

    @Test(priority = 1)
    @Description("Ürün arama testi")
    @Story("Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchProduct() throws InterruptedException {
        try {
            Allure.step("Ana sayfaya git");
            homePage.navigateToHomePage();
            Thread.sleep(5000);
            Allure.addAttachment("Homepage Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            
            String searchTerm = "laptop";
            Allure.step("Ürün ara: " + searchTerm);
            homePage.searchProduct(searchTerm);
            Thread.sleep(3000);
            
            String currentUrl = driver.getCurrentUrl();
            Allure.addAttachment("Search URL", currentUrl);
            Allure.addAttachment("Search Results Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            
            Allure.step("Arama sonuçlarını doğrula");
            homePage.verifySearchResults(searchTerm);
            Allure.addAttachment("Final Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            
        } catch (Exception e) {
            Allure.step("Test sırasında hata oluştu", Status.FAILED);
            Allure.addAttachment("Error Message", e.getMessage());
            Allure.addAttachment("Error Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            throw e;
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Allure.step("Test başarısız oldu: " + result.getName(), Status.FAILED);
            Allure.addAttachment("Failure Message", result.getThrowable().getMessage());
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            Allure.step("WebDriver'ı kapat");
            if (driver != null) {
                driver.quit();
                Allure.addAttachment("Teardown Status", "Driver başarıyla kapatıldı");
            }
        } catch (Exception e) {
            Allure.step("Driver kapatılırken hata oluştu", Status.FAILED);
            Allure.addAttachment("Teardown Error", e.getMessage());
        }
    }
} 