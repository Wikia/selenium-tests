package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class FacebookSignupModalComponentObject extends WikiBasePageObject {

  @FindBy(css = "button[name='__CONFIRM__']")
  private WebElement appTermsConfirmButton;
  @FindBy(css = ".UserLoginFacebookLeft input[name='username']")
  private WebElement usernameField;
  @FindBy(css = ".UserLoginFacebookLeft input[name='password']")
  private WebElement passwordField;
  @FindBy(css = ".UserLoginFacebookLeft input[name='email']")
  private WebElement emailField;
  @FindBy(css = ".UserLoginFacebookRight input[name='username']")
  private WebElement existingUsernameField;
  @FindBy(css = ".UserLoginFacebookRight input[name='password']")
  private WebElement existingPasswordField;
  @FindBy(css = ".UserLoginFacebookLeft input[type='submit']")
  private WebElement createAccountButton;
  @FindBy(css = ".UserLoginFacebookRight input[type='submit']")
  private WebElement loginExistingButton;
  @FindBy(css = "#u_0_4")
  private WebElement editInfoProvided;
  @FindBy(xpath = "//input[@type='checkbox'][@value='email']/..")
  private WebElement emailCheckbox;

  public FacebookSignupModalComponentObject(WebDriver driver) {
    super(driver);
  }

  public void acceptWikiaAppPolicy() {
    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
      @Nullable
      @Override
      public Boolean apply(WebDriver input) {
        return input.getWindowHandles().size() > 1;
      }
    });

    Object[] handles = driver.getWindowHandles().toArray();

    driver.switchTo().window(handles[1].toString());
    wait.forElementVisible(By.cssSelector("button[name='__CONFIRM__']"));
    appTermsConfirmButton.click();
    PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps privacy policy", true);
    driver.switchTo().window(handles[0].toString());
  }

  public void acceptWikiaAppPolicyNoEmail() {

    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
      @Nullable
      @Override
      public Boolean apply(WebDriver input) {
        return input.getWindowHandles().size() > 1;
      }
    });

    Object[] handles = driver.getWindowHandles().toArray();

    driver.switchTo().window(handles[1].toString());
    wait.forElementVisible(editInfoProvided);
    editInfoProvided.click();
    PageObjectLogging.log("acceptWikiaAppPolicyNoEmail", "editing info provided", true);
    wait.forElementVisible(emailCheckbox);
    emailCheckbox.click();
    PageObjectLogging.log("acceptWikiaAppPolicyNoEmail", "unchecked the email checkboxbox", true);
    wait.forElementVisible(By.cssSelector("button[name='__CONFIRM__']"));
    appTermsConfirmButton.click();
    driver.switchTo().window(handles[0].toString());

  }

  public void typeUserName(String userName) {
    wait.forElementVisible(usernameField);
    usernameField.sendKeys(userName);
    PageObjectLogging.log("typeUserName", "username " + userName + " typed into the field", true);
  }

  public void typePassword(String password) {
    wait.forElementVisible(passwordField);
    passwordField.sendKeys(password);
    PageObjectLogging.log("typePassword", "password typed into the field", true);
  }

  public void typeEmail(String email) {
    wait.forElementVisible(emailField);
    emailField.sendKeys(email);
    PageObjectLogging.log("typeEmail", "email typed into the field", true);
  }

  public void createAccount() {
    wait.forElementVisible(createAccountButton);
    createAccountButton.click();
    PageObjectLogging.log("createAccount", "Create account button clicked", true);
    waitForElementNotVisibleByElement(createAccountButton);
  }

  public void createAccountNoEmail(String email, String emailPassword, String userName,
      String password) {
    acceptWikiaAppPolicyNoEmail();
    MailFunctions.deleteAllEmails(email, emailPassword);
    typeUserName(userName);
    typePassword(password);
    typeEmail(email);
    createAccount();
  }

  public void loginExistingAccount(String userName, String password) {
    wait.forElementVisible(existingUsernameField);
    existingUsernameField.sendKeys(userName);
    PageObjectLogging.log("loginExistingAccount", "username " + userName + " typed into the field",
        true);
    wait.forElementVisible(existingPasswordField);
    existingPasswordField.sendKeys(password);
    PageObjectLogging.log("loginExistingAccount", "password " + password + " typed into the field",
        true);
    loginExistingButton.click();
  }
}
