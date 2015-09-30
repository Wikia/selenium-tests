package com.wikia.webdriver.pageobjectsfactory.pageobject.special.login;

import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Karol 'kkarolk' Kujawiak
 */

public class SpecialUserLoginPageObject extends SpecialPageObject {

  private static final String DISABLED_ACCOUNT_MESSAGE = "Your account has been disabled by Wikia.";

  @FindBy(css = ".WikiaArticle input[name='username']")
  private WebElement userName;
  @FindBy(css = ".WikiaArticle input[name='password']")
  private WebElement password;
  @FindBy(css = ".WikiaArticle input[name='newpassword']")
  private WebElement newPassword;
  @FindBy(css = ".WikiaArticle input[name='retype']")
  private WebElement retypeNewPassword;
  @FindBy(css = ".WikiaArticle input.login-button.big")
  private WebElement loginButton;
  @FindBy(css = ".WikiaArticle .forgot-password")
  private WebElement forgotPasswordLink;
  @FindBy(css = ".UserLogin .error-msg")
  private WebElement messagePlaceholder;

  public SpecialUserLoginPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  private void typeInUserName(String name) {
    wait.forElementVisible(userName);
    userName.clear();
    userName.sendKeys(name);
    LOG.log("typeInUserName", name + " user name typed", LOG.Type.SUCCESS);
  }

  private void typeInPassword(String pass) {
    wait.forElementVisible(password);
    password.clear();
    password.sendKeys(pass);
    LOG.log("typeInUserPassword", "password typed", LOG.Type.SUCCESS);
  }

  private void typeInNewPassword(String pass) {
    wait.forElementVisible(newPassword);
    newPassword.sendKeys(pass);
    LOG.log("typeInNewPassword", "new password retyped", true, driver);
  }

  private void retypeInNewPassword(String pass) {
    wait.forElementVisible(retypeNewPassword);
    retypeNewPassword.sendKeys(pass);
    LOG.log("typeInNewPassword", "new password retyped", true, driver);
  }

  private void clickLoginButton() {
    wait.forElementVisible(loginButton);
    loginButton.click();
    LOG.log("clickLoginButton", "login button clicked", LOG.Type.SUCCESS);
  }

  private void clickForgotPasswordLink() {
    wait.forElementVisible(forgotPasswordLink);
    scrollAndClick(forgotPasswordLink);
  }

  public void loginAndVerify(String name, String password, String wikiURL) {
    openSpecialUserLogin(wikiURL);
    login(name, password);
    verifyUserLoggedIn(name);
  }

  public void login(String name, String pass) {
    typeInUserName(name);
    typeInPassword(pass);
    clickLoginButton();
  }

  public void remindPassword(String name, String apiToken) {
    Assertion.assertEquals(resetForgotPasswordTime(name, apiToken),
                           ApiActions.API_ACTION_FORGOT_PASSWORD_RESPONSE);
    typeInUserName(name);
    clickForgotPasswordLink();
  }

  public String setNewPassword() {
    String randomPassword = Configuration.getCredentials().password + getTimeStamp();
    typeInNewPassword(randomPassword);
    retypeInNewPassword(randomPassword);
    clickLoginButton();
    LOG.log("setNewPassword", "new password is set", true, driver);
    return randomPassword;
  }

  public void verifyMessageAboutNewPassword(String userName) {
    wait.forElementVisible(messagePlaceholder);
    String message = PageContent.NEW_PASSWORD_SENT_MESSAGE.replace("%userName%", userName);
    wait.forTextInElement(messagePlaceholder, message);
    LOG.log("newPasswordSentMessage", "Message about new password sent present",
            true, driver);
  }

  public void verifyClosedAccountMessage() {
    wait.forElementVisible(messagePlaceholder);
    Assertion.assertEquals(messagePlaceholder.getText(), DISABLED_ACCOUNT_MESSAGE);
  }
}
