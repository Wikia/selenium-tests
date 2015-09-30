package com.wikia.webdriver.pageobjectsfactory.pageobject.special.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.contentpatterns.ApiActions;
import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

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
    LOG.success("typeInUserName", name + " user name typed");
  }

  private void typeInPassword(String pass) {
    wait.forElementVisible(password);
    password.clear();
    password.sendKeys(pass);
    LOG.success("typeInUserPassword", "password typed");
  }

  private void typeInNewPassword(String pass) {
    wait.forElementVisible(newPassword);
    newPassword.sendKeys(pass);
    LOG.success("typeInNewPassword", "new password retyped", true);
  }

  private void retypeInNewPassword(String pass) {
    wait.forElementVisible(retypeNewPassword);
    retypeNewPassword.sendKeys(pass);
    LOG.success("typeInNewPassword", "new password retyped", true);
  }

  private void clickLoginButton() {
    wait.forElementVisible(loginButton);
    loginButton.click();
    LOG.success("clickLoginButton", "login button clicked");
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
    LOG.success("setNewPassword", "new password is set", true);
    return randomPassword;
  }

  public void verifyMessageAboutNewPassword(String userName) {
    wait.forElementVisible(messagePlaceholder);
    String message = PageContent.NEW_PASSWORD_SENT_MESSAGE.replace("%userName%", userName);
    wait.forTextInElement(messagePlaceholder, message);
    LOG.success("newPasswordSentMessage", "Message about new password sent present",true);
  }

  public void verifyClosedAccountMessage() {
    wait.forElementVisible(messagePlaceholder);
    Assertion.assertEquals(messagePlaceholder.getText(), DISABLED_ACCOUNT_MESSAGE);
  }
}
