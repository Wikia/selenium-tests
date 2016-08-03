package com.wikia.webdriver.elements.fandom.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.xpath.operations.String;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by psko on 8/1/16.
 */
public class SearchInput extends BasePageObject {

    @FindBy(css = ".search-field")
    private WebElement searchFieldInput;

    @FindBy(css = ".entry-title")
    private WebElement postInstance;

    @FindBy(linkText = "Articles")
    private WebElement postsList;

    @FindBy(linkText = "Videos")
    private WebElement videosList;

    @FindBy(css = ".clickable-anchor")
    private WebElement postTitle;

    @FindBy(css = ".entry-title")
    private WebElement postTitleFinal;

    public SearchInput searchInputValue() {
        searchFieldInput.sendKeys("TEST");
        return this;
    }

    public SearchInput searchInputSubmit() {
        searchFieldInput.submit();
        return this;

    }

    public SearchInput firstPostInstance() {
        postInstance.click();
        return this;
    }

    public SearchInput searchResultsPosts() {
        postsList.click();
        return this;
    }

    public SearchInput searchResultsVideos() {
        videosList.click();
        return this;
    }

    public SearchInput getPostTitle() {
        postTitle.getText();
        return this;
    }

    public SearchInput getPostTitleFinal() {
        postTitleFinal.getText();
        return this;
    }
}
