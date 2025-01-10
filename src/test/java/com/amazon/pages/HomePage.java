package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "input#twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(css = "input#nav-search-submit-button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        driver.get("https://www.amazon.com");
        wait.until(ExpectedConditions.titleContains("Amazon"));
        wait.until(ExpectedConditions.visibilityOf(searchBox));
    }

    public void enterSearchText(String searchText) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            searchBox.clear();
            searchBox.sendKeys(searchText);
        } catch (Exception e) {
            throw new RuntimeException("Arama kutusu bulunamadı veya tıklanamadı: " + e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Arama butonu bulunamadı veya tıklanamadı: " + e.getMessage());
        }
    }
}