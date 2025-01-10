package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class SearchResultPage {
    private WebDriver driver;

    @FindBy(css = ".s-result-item")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//span[@class='a-color-state a-text-bold']")
    private WebElement searchKeyword;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean areSearchResultsDisplayed() {
        return !searchResults.isEmpty();
    }

    public boolean isSearchKeywordPresent(String keyword) {
        return searchKeyword.getText().contains(keyword);
    }
} 