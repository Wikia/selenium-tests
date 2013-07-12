package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialPageObject extends WikiBasePageObject {

    @FindBy(css = "#WikiaPageHeader > h2")
    protected WebElement specialPageHeader;

    public SpecialPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifySpecialPage() {
        waitForTextToBePresentInElementByElement(
            specialPageHeader, "Special page"
        );
        PageObjectLogging.log(
            "SpecialPageLoaded",
            "Special Page is loaded",
            true, driver
        );
    }

    public void verifySpecialPageRedirection(String specialPage) {
        verifyURLcontains(specialPage);
        PageObjectLogging.log(
            "RedirectedToSpecialPage",
            "User is redirected to special page",
            true, driver
        );
    }

}
