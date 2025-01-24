package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(css = ".s-result-list")
    private WebElement searchResults;

    @FindBy(css = ".s-breadcrumb")
    private WebElement searchBreadcrumb;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterSearchTerm(String searchTerm) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    public boolean areSearchResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchResults));
            return searchResults.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean doesSearchResultContain(String searchTerm) {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchBreadcrumb));
            String breadcrumbText = searchBreadcrumb.getText().toLowerCase();
            return breadcrumbText.contains(searchTerm.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
} 