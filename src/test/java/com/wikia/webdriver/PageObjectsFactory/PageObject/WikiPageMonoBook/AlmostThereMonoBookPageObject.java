/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BaseMonoBookPageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AlmostThereMonoBookPageObject extends BaseMonoBookPageObject {

    @FindBy(xpath="//h1[contains(text(),'Confirm your email')]")
    private WebElement confirmEmailText; 
    @FindBy(css=".general-messaging b")
    private WebElement userEmailSelector;
    @FindBy(css=".link[value='Send me another confirmation email']")
    private WebElement sendAnotherMail;

    public AlmostThereMonoBookPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        changeToMonoBook();
    }

    public void verifyAlmostTherePage() {
        verifySkinChanged();
        verifyConfirmEmailText();
        verifyUserEmailPresent(Properties.email);
        verifySendAnotherMailText();
    }


    public ConfirmationMonoBookPageObject enterActivationLink()	{
        String www = getActivationLinkFromMail();
        String wwwMonoBook = www + "?useskin=monobook";
        getUrl(wwwMonoBook);
        PageObjectLogging.log(
            "enterActivationLink",
            "activation page is displayed with monobook skin", true, driver
        );
        return new ConfirmationMonoBookPageObject(driver);
    }

    private String getActivationLinkFromMail() { 
        String www = MailFunctions.getActivationLinkFromMailContent(
            MailFunctions.getFirstMailContent(Properties.email, Properties.emailPassword)
        );
        www = www.replace("=", "");
        PageObjectLogging.log(
            "getActivationLinkFromMail",
            "activation link is visible in email content: " + www, true
        );
        return www;
    }

    private void verifyConfirmEmailText() {
        waitForElementByElement(confirmEmailText);
        PageObjectLogging.log(
            "verifyEmailConfirmText",
            "Confirm your email text is present", true
        );
    }

    private void verifyUserEmailPresent(String email) {
        waitForElementByElement(userEmailSelector);
         Assertion.assertEquals(
            email, userEmailSelector.getText()
        );
        PageObjectLogging.log("usersEmailFound",
            "User's email from form is present", true
        );
    }

    private void verifySendAnotherMailText() {
        waitForElementByElement(sendAnotherMail);
        PageObjectLogging.log(
            "verifySendAnotherMailText",
            "Send another email link is visible", true
        );
    }
}
