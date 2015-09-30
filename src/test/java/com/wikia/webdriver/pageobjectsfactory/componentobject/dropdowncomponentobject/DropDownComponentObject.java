package com.wikia.webdriver.pageobjectsfactory.componentobject.dropdowncomponentobject;

import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.SignUpPageObject;

import org.openqa.selenium.By;
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

  private static final String LOGIN_DROPDOWN_TRIGGER_CSS = "#AccountNavigation";

  @FindBy(css = LOGIN_DROPDOWN_TRIGGER_CSS)
  private WebElement loginDropdownTrigger;
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
  @FindBy(css = "a[data-id='logout']")
  private WebElement logOutButton;

  public DropDownComponentObject(WebDriver driver) {
    super(driver);
  }

  /**
   * Open dropdown - we need to move mouse outside the element and move back to element to trigger
   * the event
   */
  public DropDownComponentObject openDropDown() {
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, 20, 2000).until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
          if (!driver.findElement(By.cssSelector(LOGIN_DROPDOWN_TRIGGER_CSS)).getAttribute("class")
              .contains("active")) {
            ((JavascriptExecutor) driver)
                .executeScript("$j('.ajaxLogin .avatar-container').trigger('click')");
            return false;
          }
          return true;
        }
      });
    } finally {
      restoreDeaultImplicitWait();
    }

    LOG.log("DropdownVisible", "Login dropdown is visible", true, driver);

    return this;
  }

  public void remindPassword(String userName, String apiToken) {
    Assertion.assertEquals(resetForgotPasswordTime(userName, apiToken),
                           ApiActions.API_ACTION_FORGOT_PASSWORD_RESPONSE);
    fillUserNameInput(userName);
    wait.forElementVisible(formForgotPasswordLink);
    scrollAndClick(formForgotPasswordLink);
  }

  public void logIn(String userName, String password) {
    fillUserNameInput(userName);
    fillPasswordInput(password);
    scrollAndClick(formSubmitButton);
    LOG.log("LoginFormSubmitted", "Login form is submitted", LOG.Type.SUCCESS);
  }

  public DropDownComponentObject clickLogOut() {
    logOutButton.click();

    return this;
  }

  public void fillUserNameInput(String userName) {
    wait.forElementVisible(formUsernameInput);
    formUsernameInput.clear();
    formUsernameInput.sendKeys(userName);
    LOG.log("UsernameTyped", "UserName input is filled", LOG.Type.SUCCESS);
  }

  public void fillPasswordInput(String password) {
    wait.forElementVisible(formPassowrdInput);
    formPassowrdInput.clear();
    formPassowrdInput.sendKeys(password);
    LOG.log("PasswordTyped", "Password input is filled", LOG.Type.SUCCESS);
  }

  public void logInViaFacebook(String email, String password) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    wait.forElementVisible(formConnectWithFbButton);
    // When clicking via selenium dropdown disappears
    js.executeScript("$('.wikia-button-facebook.sso-login-facebook').trigger('click')");
    LOG.log("logInDropDownFB", "facebook button clicked", LOG.Type.SUCCESS);
    waitForNewWindow();
    Object[] windows = driver.getWindowHandles().toArray();
    driver.switchTo().window(windows[1].toString());
    LOG.log("logInDropDownFB", "facebook popup window detected", LOG.Type.SUCCESS);
    LOG.log("logInDropDownFB", "switching to facebook pop-up window", LOG.Type.SUCCESS);

    wait.forElementVisible(facebookEmailInput);
    facebookEmailInput.clear();
    facebookEmailInput.sendKeys(email);
    LOG.log("fillLogin", "Login field on facebook form filled", LOG.Type.SUCCESS);

    wait.forElementVisible(facebookPasswordInput);
    facebookPasswordInput.clear();
    facebookPasswordInput.sendKeys(password);
    LOG.log("fillPassword", "Password field on facebook form filled", LOG.Type.SUCCESS);

    facebookSubmitButton.click();
    LOG.log("logInDropDownFB", "facebook log in submit button clicked", LOG.Type.SUCCESS);

    driver.switchTo().window(windows[0].toString());
    LOG.log("logInDropDownFB", "switching to main window", LOG.Type.SUCCESS);
  }

  public SignUpPageObject clickSignUpLink() {
    signUpLink.click();

    return new SignUpPageObject(driver);
  }

  public void verifyMessageAboutNewPassword(String userName) {
    wait.forElementVisible(messagePlaceholder);
    String newPasswordMsg = PageContent.NEW_PASSWORD_SENT_MESSAGE.replace("%userName%", userName);
    wait.forTextInElement(messagePlaceholder, newPasswordMsg);
    LOG.logResult("MessageAboutPasswordSent",
                  "Message about new password sent present",
                  true);
  }
}
