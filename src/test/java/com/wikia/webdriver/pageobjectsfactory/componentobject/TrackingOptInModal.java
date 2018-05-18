package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrackingOptInModal extends BasePageObject {

    @FindBy(css = "body > div:nth-child(5) > div > div")
    WebElement modal;

    @FindBy(css = "body > div:nth-child(5) > div > div > div.C-09cRzrSguyeBx3D8aqC > div._36mdTyonPU0bxbU70dDO4f")
    WebElement acceptButton;

    @FindBy(css="div[data-tracking-opt-in-overlay]")
    private WebElement optInModalOverlay;

    public boolean isVisible() {
        try {
            wait.forElementVisible(optInModalOverlay);
            PageObjectLogging.logInfo("Tracking modal visible");

            return true;
        } catch (TimeoutException e) {
            PageObjectLogging.logInfo("Tracking modal not visible");

            return false;
        }
    }

    public void clickAcceptButton() {
        try {
            wait.forElementClickable(acceptButton);
            acceptButton.click();
        } catch (Exception e) {
            PageObjectLogging.log("Accept button clicked", e, false);
        }
    }

    public boolean isModalDisplayed() {
        boolean isVisible = false;
        try {
            wait.forElementVisible(modal);
            isVisible = true;
        } catch (Exception e) {
            PageObjectLogging.log("Modal is visible", e, false);
        }

        return isVisible;
    }
}
