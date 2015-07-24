package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by qaga on 2015-07-24.
 */
public class JoinPage extends BasePageObject {

    @FindBy(css = ".register-message:last-child")
    private WebElement joinTodayMessage;

    @FindBy(css = ".button.signup-provider-email")
    private WebElement registerWithEmailButton;

    @FindBy(css = "a.sign-in")
    private WebElement signInLink;

    public JoinPage(WebDriver driver) {
        super(driver);
    }

    public JoinPage get() {
        String redirectParameter = "";

        try {
            redirectParameter = URLEncoder.encode(urlBuilder.getUrlForWiki(Configuration.getWikiName()), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            PageObjectLogging.log("encoding", "problem occured during URL encoding", false);
        }
        driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "join" + "?redirect=" + redirectParameter);
        return this;
    }

    public String getJoinTodayText() {
        return joinTodayMessage.getText();
    }

    public void clickRegisterWithEmail() {
        waitForElementVisibleByElement(registerWithEmailButton);
        registerWithEmailButton.click();
    }

    public void clickSignInLink() {
        waitForElementVisibleByElement(signInLink);
        signInLink.click();
    }

}

