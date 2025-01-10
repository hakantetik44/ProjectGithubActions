package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".s-result-item")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    private WebElement searchKeyword;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public boolean areSearchResultsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
        return !searchResults.isEmpty();
    }

    public boolean isSearchKeywordPresent(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchKeyword));
        return searchKeyword.getText().contains(keyword);
    }
}