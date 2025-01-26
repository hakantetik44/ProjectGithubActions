package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.*;
import utils.AliExpressConstants;
import java.time.Duration;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Cookie;

public class AliExpressTest {
    private WebDriver driver;
    private AliExpressHomePage homePage;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--headless");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        
        // Add language preference
        options.addArguments("--lang=en-US");
        options.addArguments("--accept-lang=en-US,en;q=0.9");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        homePage = new AliExpressHomePage(driver);
        
        // Set language cookie before navigating
        driver.get("https://www.aliexpress.com");
        driver.manage().addCookie(new Cookie("aep_usuc_f", "site=glo&c_tp=USD&region=US&b_locale=en_US"));
        
        // Navigate to homepage
        driver.get(AliExpressConstants.BASE_URL);
        System.out.println("Ana sayfaya gidildi: " + AliExpressConstants.BASE_URL);
        
        try {
            Thread.sleep(5000); // Sayfa tam olarak yüklenene kadar bekle
        } catch (InterruptedException e) {
            System.out.println("Bekleme sırasında kesinti: " + e.getMessage());
        }
    }

    @Test
    public void testSearchProduct() {
        String searchTerm = "laptop";
        homePage.searchProduct(searchTerm);
        System.out.println("Ürün araması yapıldı: " + searchTerm);
        System.out.println("Mevcut URL: " + driver.getCurrentUrl());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver kapatıldı");
        }
    }
} 