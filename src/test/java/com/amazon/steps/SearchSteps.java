package com.amazon.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.amazon.pages.SearchPage;
import com.amazon.utils.DriverManager;
import java.time.Duration;
import static org.junit.Assert.*;

public class SearchSteps {
    private WebDriver driver;
    private SearchPage searchPage;
    private WebDriverWait wait;

    public SearchSteps() {
        this.driver = DriverManager.getDriver();
        this.searchPage = new SearchPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("Kullanici Amazon ana sayfasina gider")
    public void kullanici_amazon_ana_sayfasina_gider() {
        driver.get("https://www.amazon.com");
    }

    @When("Kullanici arama kutusuna {string} yazar")
    public void kullanici_arama_kutusuna_yazar(String searchTerm) {
        searchPage.enterSearchTerm(searchTerm);
    }

    @When("Kullanici arama butonuna tiklar")
    public void kullanici_arama_butonuna_tiklar() {
        searchPage.clickSearchButton();
    }

    @Then("Kullanici arama sonuclarini görmeli")
    public void kullanici_arama_sonuclarini_gormeli() {
        assertTrue("Arama sonuçları görüntülenemedi", searchPage.areSearchResultsDisplayed());
    }

    @Then("Sonuclarda {string} kelimesi bulunmali")
    public void sonuclarda_kelimesi_bulunmali(String searchTerm) {
        assertTrue("Arama sonuçlarında '" + searchTerm + "' kelimesi bulunamadı", 
            searchPage.doesSearchResultContain(searchTerm));
    }
} 