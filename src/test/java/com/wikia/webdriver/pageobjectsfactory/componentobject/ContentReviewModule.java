package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ludwik on 2015-09-23.
 */
public class ContentReviewModule extends WikiBasePageObject {

    @FindBy(css = ".content-review-module")
    private WebElement contentReviewModule;
    @FindBy(css = "#content-review-module-submit")
    private WebElement submitForReviewLink;
    @FindBy(css = ".content-review-module-test-mode-enable")
    private WebElement enableTestModeButton;
    @FindBy(css = "button.content-review-module-test-mode-disable")
    private WebElement disableTestModeButton;

    public ContentReviewModule(WebDriver driver) {
        super(driver);
    }

    public boolean isModuleVisible(){
        try {
            wait.forElementVisible(contentReviewModule, 5, 1);
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public boolean isSubmitLinkVisible() {
        boolean isLinkVisible = false;
        try {
            wait.forElementVisible(submitForReviewLink, 3, 1);
            isLinkVisible = true;
        } catch(TimeoutException e) {
            isLinkVisible = false;
        }
        return isModuleVisible() && isLinkVisible;
    }

    public boolean isEnableTestModeButtonVisible() {
        boolean isButtonVisible = false;
        try {
            wait.forElementVisible(enableTestModeButton, 5, 1);
            isButtonVisible = true;
        } catch (TimeoutException e) {
            isButtonVisible = false;
        }
        return isModuleVisible() && isButtonVisible;
    }

    public ContentReviewModule clickEnableTestModeButton() {
        wait.forElementVisible(enableTestModeButton, 5, 1);
        enableTestModeButton.click();
        return this;
    }

    public ContentReviewModule clickDisableTestModeButton() {
        wait.forElementVisible(disableTestModeButton, 5, 1);
        disableTestModeButton.click();
        return this;
    }
}
