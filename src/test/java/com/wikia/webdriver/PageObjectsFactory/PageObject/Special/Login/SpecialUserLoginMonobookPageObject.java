/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BaseMonoBookPageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialUserLoginMonobookPageObject extends BaseMonoBookPageObject {

    @FindBy(css= "input[name='username']")
    private WebElement loginFiled;
    @FindBy(css= "input[name='password']")
    private WebElement passwordFiled;
    @FindBy(css= ".login-button.big[type='submit']")
    private WebElement submitButton;

    public SpecialUserLoginMonobookPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        String specialLoginPageUrl = Domain + URLsContent.specialUserLogin;
        getUrl(specialLoginPageUrl);
        changeToMonoBook();
        PageObjectLogging.log(
            "openSpecialLoginPageWithMonobook",
            "login page with monobook skin selected loading for more then 30 seconds",
            true, driver
        );
        PageObjectLogging.log(
            "openSpecialLoginPageWithMonobook",
            "Login page with monobook opened",
            true, driver
        );
    }

    public void fillLoginForm(String username, String password, String usernameEnc) {
        waitForElementByElement(loginFiled);
        loginFiled.sendKeys(username);
        waitForElementByElement(passwordFiled);
        passwordFiled.sendKeys(password);
        PageObjectLogging.log(
            "fillLoginForm",
            "login form on special page filled",
            true
        );
    }

    public void submitForm() {
        waitForElementByElement(submitButton);
        clickAndWait(submitButton);
        PageObjectLogging.log(
            "submitSpecialLoginForm",
            "login form on special page submitted",
            true
        );
    }
}
