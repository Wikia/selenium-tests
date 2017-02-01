package com.wikia.webdriver.elements.fandom.components;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class LoadMore extends BasePageObject {

    @FindBy(css = ".more-stories .load-more-btn")
    private WebElement loadMoreButton;

    public boolean isButtonDisplayed() {
        try {
            return loadMoreButton.isDisplayed();
        } catch (ElementNotFoundException e) {
            PageObjectLogging.log("LoadMore", "Load More button is not displayed. " + e.getMessage(), false);
            return false;
        }
    }

    public boolean waitForMorePosts() {
        try {
            WebElement element = loadPosts();
            return element.isDisplayed();
        } catch (ElementNotFoundException e) {
            PageObjectLogging.log("LoadMore", "More posts did not load. " + e.getMessage(), false);
            return false;
        }
    }

    public WebElement loadPosts() {
        loadMoreButton.click();

        return wait.forElementPresent(By.cssSelector("[tracking-label='more-top-stories.item-15']"));
    }

}
