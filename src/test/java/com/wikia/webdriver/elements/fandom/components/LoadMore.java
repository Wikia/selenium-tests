package com.wikia.webdriver.elements.fandom.components;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

/**
 * Created by liz_lux on 1/26/17.
 */
public class LoadMore extends BasePageObject {

    @FindBy(css = ".more-stories .load-more-btn")
    private WebElement loadMoreButton;

    public boolean isButtonDisplayed() {
        try {
            return loadMoreButton.isDisplayed();
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    public boolean areMorePostsDisplayed() {
        try {
            loadMoreButton.click();
            WebElement element = wait.forElementPresent(By.cssSelector("[tracking-label='more-top-stories.item-15']"));
            return element.isDisplayed();
        } catch (ElementNotFoundException e) {
            return false;
        }
    }
}
