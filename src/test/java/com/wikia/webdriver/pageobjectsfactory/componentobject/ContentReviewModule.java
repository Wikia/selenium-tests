package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContentReviewModule extends WikiBasePageObject {

    @FindBy(css = ".content-review-module")
    private WebElement contentReviewModule;
    @FindBy(css = ".content-review-module-submit")
    private WebElement submitForReviewLink;

    public ContentReviewModule() {
        super();
    }

    public boolean isModuleVisible(){
        wait.forElementVisible(contentReviewModule, 5, 1);
        return true;
    }

    public boolean isModuleNotVisible(){
        waitForElementNotVisibleByElement(contentReviewModule, 5);
        return true;
    }

    public boolean isSubmitLinkVisible() {
        wait.forElementVisible(submitForReviewLink, 3, 1);
        return true;
    }

    public boolean isSubmitLinkNotVisible() {
        waitForElementNotVisibleByElement(submitForReviewLink, 3);
        return true;
    }
}
