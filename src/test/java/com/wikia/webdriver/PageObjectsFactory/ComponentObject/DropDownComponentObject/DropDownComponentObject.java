package com.wikia.webdriver.PageObjectsFactory.ComponentObject.DropDownComponentObject;

import com.wikia.webdriver.Common.DriverProvider.DriverProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class DropDownComponentObject extends WikiBasePageObject {

    public DropDownComponentObject(WebDriver driver, String Domain) {
            super(driver,Domain);
            PageFactory.initElements(driver, this);
    }

    @FindBy (css=".ajaxLogin")
    private WebElement loginDropdownTrigger;
    @FindBy (css="#UserLoginDropdown")
    private WebElement loginDropdown;
    @FindBy (css="#UserLoginDropdown input[name='username']")
    private WebElement formUsernameInput;
    @FindBy (css="#UserLoginDropdown input[name='password']")
    private WebElement formPassowrdInput;
    @FindBy (css="#UserLoginDropdown input[type='submit']")
    private WebElement formSubmitButton;
    @FindBy (css="#UserLoginDropdown .forgot-your-password-link")
    private WebElement formForgotPasswordLink;
    @FindBy (css="#UserLoginDropdown .wikia-button-facebook")
    private WebElement formConnectWithFbButton;
    @FindBy (css="#facebook #email")
    private WebElement facebookEmailInput;
    @FindBy (css="#facebook #pass")
    private WebElement facebookPasswordInput;
    @FindBy (css="#facebook input[name='login']")
    private WebElement facebookSubmitButton;

    public void openDropDown() {
        waitForElementByElement(loginDropdownTrigger);
        clickAndWait(loginDropdownTrigger);
        waitForElementInViewPort(loginDropdown);
        PageObjectLogging.log(
            "DropdownVisible",
            "Login dropdown is visible",
            true, driver
        );
    }

    public void remindPassword(String userName) {
        fillUserNameInput(userName);
        waitForElementByElement(formForgotPasswordLink);
        clickAndWait(logInModal);
    }

    public void logIn(String userName, String password) {
        fillUserNameInput(userName);
        fillPasswordInput(password);
        clickAndWait(formSubmitButton);
        PageObjectLogging.log(
            "LoginFormSubmitted",
            "Login form is submitted",
            true
        );
    }

    public void fillUserNameInput(String userName) {
        waitForElementByElement(formUsernameInput);
        sendKeys(formUsernameInput, userName);
        PageObjectLogging.log(
            "UsernameTyped",
            "UserName input is filled",
            true
        );
    }

    public void fillPasswordInput(String password) {
        waitForElementByElement(formPassowrdInput);
        sendKeys(formPassowrdInput, password);
        PageObjectLogging.log(
            "PasswordTyped",
            "Password input is filled",
            true
        );
    }

    public void logInViaFacebook() {
        clickAndWait(formConnectWithFbButton);
        PageObjectLogging.log(
            "logInDropDownFB",
            "facebook button clicked",
            true
        );
        waitForNewWindow();
        Object[] windows = driver.getWindowHandles().toArray();
        driver.switchTo().window(windows[1].toString());
        PageObjectLogging.log(
            "logInDropDownFB",
            "facebook popup window detected",
            true
        );
        PageObjectLogging.log(
            "logInDropDownFB",
            "switching to facebook pop-up window",
            true
        );

        waitForElementByElement(facebookEmailInput);
        facebookEmailInput.clear();
        facebookEmailInput.sendKeys(Properties.emailFB);
        PageObjectLogging.log(
            "fillLogin",
            "Login field on facebook form filled",
            true
        );

        waitForElementByElement(facebookPasswordInput);
        facebookPasswordInput.clear();
        facebookPasswordInput.sendKeys(Properties.passwordFB);
        PageObjectLogging.log(
            "fillPassword",
            "Password field on facebook form filled",
            true
        );

        clickAndWait(facebookSubmitButton);
        PageObjectLogging.log(
            "logInDropDownFB",
            "facebook log in submit button clicked",
            true
        );

        driver.switchTo().window(windows[0].toString());
        PageObjectLogging.log(
            "logInDropDownFB",
            "switching to main window",
            true
        );
    }
}
