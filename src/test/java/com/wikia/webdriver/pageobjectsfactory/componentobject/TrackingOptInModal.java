package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrackingOptInModal extends BasePageObject {

    @FindBy(css = "body > div:nth-child(5) > div > div")
    WebElement modal;

    @FindBy(css = "body > div:nth-child(5) > div > div > div.C-09cRzrSguyeBx3D8aqC > div._36mdTyonPU0bxbU70dDO4f")
    WebElement acceptButton;

    public void clickAcceptButton() {
        try {
            wait.forElementClickable(acceptButton);
            acceptButton.click();
        } catch (Exception e) {
            PageObjectLogging.log("Could click accept button", e, true);
        }
    }
}
