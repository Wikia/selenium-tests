package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

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
  @FindBy(css = ".UserLoginFacebookLeft input[type='submit']")
  private WebElement createAccountButton;
  @FindBy(css = "#u_0_4")
  private WebElement editInfoProvided;
  @FindBy(css = "input[type='checkbox'][value='email']")
  private WebElement emailCheckbox;

  String winHandleBefore;

  public FacebookSignupModalComponentObject(WebDriver driver, String winHandleBeforeFBClick) {
    super(driver);
    winHandleBefore = winHandleBeforeFBClick;
  }

  public void acceptWikiaAppPolicy() {
    // If policies are already accepted, give facebook popup window time to disappear
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      PageObjectLogging.log("acceptWikiaAppPolicy", e.getMessage(), false);
    }

    Set<String> handles = driver.getWindowHandles();

    if (handles.size() > 1) {
      for (String winHandle : handles) {
        //Switch to new window opened
        driver.switchTo().window(winHandle);
      }
      waitForElementByElement(appTermsConfirmButton);
      appTermsConfirmButton.click();
      PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps privacy policy", true);
      // Switch back to original browser (first window)
      driver.switchTo().window(winHandleBefore);
    } else {
      PageObjectLogging.log("acceptWikiaAppPolicy", "wikia apps policies allready accepted", true);
    }
  }

  public void acceptWikiaAppPolicyNoEmail() {

    Set<String> handles = driver.getWindowHandles();

    if (handles.size() > 1) {
      for (String winHandle : handles) {
        //Switch to new window opened
        driver.switchTo().window(winHandle);
      }
      waitForElementByElement(editInfoProvided);
      editInfoProvided.click();
      PageObjectLogging.log("acceptWikiaAppPolicyNoEmail", "editing info provided", true);
      emailCheckbox.click();
      PageObjectLogging.log("acceptWikiaAppPolicyNoEmail", "unchecked the email checkboxbox", true);
      waitForElementByElement(appTermsConfirmButton);
      appTermsConfirmButton.click();
      // Switch back to original browser (first window)
      driver.switchTo().window(winHandleBefore);
    } else {
      PageObjectLogging.log("acceptWikiaAppPolicy", "wikia apps policies already accepted", true);
    }
  }

  public void typeUserName(String userName) {
    waitForElementByElement(usernameField);
    usernameField.sendKeys(userName);
    PageObjectLogging.log("typeUserName", "username " + userName + " typed into the field", true);
  }

  public void typePassword(String password) {
    waitForElementByElement(passwordField);
    passwordField.sendKeys(password);
    PageObjectLogging.log("typePassword", "password typed into the field", true);
  }

  public void typeEmail(String email) {
    waitForElementByElement(emailField);
    emailField.sendKeys(email);
    PageObjectLogging.log("typeEmail", "email typed into the field", true);
  }

  public void createAccount() {
    waitForElementByElement(createAccountButton);
    createAccountButton.click();
    PageObjectLogging.log("createAccount", "Create account button clicked", true);
  }

  public void createAccountNoEmail(String email, String emailPassword, String userName, String password) {
    acceptWikiaAppPolicyNoEmail();
    MailFunctions.deleteAllEmails(email, emailPassword);
    typeUserName(userName);
    typePassword(password);
    typeEmail(email);
    createAccount();
  }
}
