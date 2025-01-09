package com.amazon.steps;

import com.amazon.pages.HomePage;
import com.amazon.pages.SearchResultPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class SearchSteps {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultPage searchResultPage;

    public SearchSteps() {
        this.driver = Hooks.getDriver();
        this.homePage = new HomePage(driver);
        this.searchResultPage = new SearchResultPage(driver);
    }

    @Given("Kullanici Amazon ana sayfasina gider")
    public void kullaniciAmazonAnaSayfasinaGider() {
        homePage.navigateToHomePage();
    }

    @When("Kullanici arama kutusuna {string} yazar")
    public void kullaniciAramaKutusunaYazar(String searchText) {
        homePage.enterSearchText(searchText);
    }

    @And("Kullanici arama butonuna tiklar")
    public void kullaniciAramaButonunaTiklar() {
        homePage.clickSearchButton();
    }

    @Then("Kullanici arama sonuclarini görmeli")
    public void kullaniciAramaSonuclariniGormeli() {
        Assert.assertTrue("Arama sonuçları görüntülenemiyor", 
            searchResultPage.areSearchResultsDisplayed());
    }

    @And("Sonuclarda {string} kelimesi bulunmali")
    public void sonuclardaKelimesiBulunmali(String keyword) {
        Assert.assertTrue("Aranan kelime sonuçlarda bulunamadı", 
            searchResultPage.isSearchKeywordPresent(keyword));
    }
} 