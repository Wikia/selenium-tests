package com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject;

import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.properties.Properties;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author Bogna 'bognix' Knychala
 */
public class DropDownComponentObject extends WikiBasePageObject {

  public DropDownComponentObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "#AccountNavigation")
  private WebElement loginDropdownTrigger;
  @FindBy(css = "#UserLoginDropdown")
  private WebElement loginDropdown;
  @FindBy(css = "#UserLoginDropdown input[name='username']")
  private WebElement formUsernameInput;
  @FindBy(css = "#UserLoginDropdown input[name='password']")
  private WebElement formPassowrdInput;
  @FindBy(css = "#UserLoginDropdown input[type='submit']")
  private WebElement formSubmitButton;
  @FindBy(css = "#UserLoginDropdown .forgot-password")
  private WebElement formForgotPasswordLink;
  @FindBy(css = "#UserLoginDropdown .wikia-button-facebook")
  private WebElement formConnectWithFbButton;
  @FindBy(css = "#facebook #email")
  private WebElement facebookEmailInput;
  @FindBy(css = "#facebook #pass")
  private WebElement facebookPasswordInput;
  @FindBy(css = "#facebook input[name='login']")
  private WebElement facebookSubmitButton;
  @FindBy(css = "#UserLoginDropdown .error-msg")
  private WebElement messagePlaceholder;
  @FindBy(css = "a.ajaxRegister")
  private WebElement signUpLink;

  /**
   * Open dropdown - we need to move mouse outside the element and move back to element to trigger
   * the event
   */
  public DropDownComponentObject openDropDown() {
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, 10, 2000).until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
          if (!loginDropdownTrigger.getAttribute("class").contains("active")) {
            ((JavascriptExecutor) driver).executeScript(
                "$j('.ajaxLogin .avatar-container').trigger('click')");
            return false;
          }
          return true;
        }
      });
    } finally {
      restoreDeaultImplicitWait();
    }

    PageObjectLogging.log(
        "DropdownVisible",
        "Login dropdown is visible",
        true, driver
    );

    return this;
  }

  public void remindPassword(String userName, String apiToken) {
    Assertion.assertEquals(
        ApiActions.API_ACTION_FORGOT_PASSWORD_RESPONSE,
        resetForgotPasswordTime(userName, apiToken));
    fillUserNameInput(userName);
    waitForElementByElement(formForgotPasswordLink);
    scrollAndClick(formForgotPasswordLink);
  }

  public void logIn(String userName, String password) {
    fillUserNameInput(userName);
    fillPasswordInput(password);
    scrollAndClick(formSubmitButton);
    PageObjectLogging.log(
        "LoginFormSubmitted",
        "Login form is submitted",
        true
    );
  }

  public void fillUserNameInput(String userName) {
    waitForElementByElement(formUsernameInput);
    formUsernameInput.clear();
    sendKeys(formUsernameInput, userName);
    PageObjectLogging.log(
        "UsernameTyped",
        "UserName input is filled",
        true
    );
  }

  public void fillPasswordInput(String password) {
    waitForElementByElement(formPassowrdInput);
    formPassowrdInput.clear();
    sendKeys(formPassowrdInput, password);
    PageObjectLogging.log(
        "PasswordTyped",
        "Password input is filled",
        true
    );
  }

  public void logInViaFacebook(String email, String password) {
    scrollAndClick(formConnectWithFbButton);
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
    facebookEmailInput.sendKeys(email);
    PageObjectLogging.log(
        "fillLogin",
        "Login field on facebook form filled",
        true
    );

    waitForElementByElement(facebookPasswordInput);
    facebookPasswordInput.clear();
    facebookPasswordInput.sendKeys(password);
    PageObjectLogging.log(
        "fillPassword",
        "Password field on facebook form filled",
        true
    );

    scrollAndClick(facebookSubmitButton);
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

  public SignUpPageObject clickSignUpLink() {
    signUpLink.click();

    return new SignUpPageObject(driver);
  }

  public void verifyMessageAboutNewPassword(String userName) {
    waitForElementByElement(messagePlaceholder);
    String newPasswordMsg = PageContent.NEW_PASSWORD_SENT_MESSAGE.replace("%userName%", userName);
    waitForTextToBePresentInElementByElement(messagePlaceholder, newPasswordMsg);
    PageObjectLogging.log(
        "MessageAboutPasswordSent",
        "Message about new password sent present",
        true
    );
  }

}
