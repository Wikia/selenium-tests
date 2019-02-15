package com.wikia.webdriver.pageobjectsfactory.pageobject.google;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMainPage extends BasePageObject {

  @FindBy(css = "#identifierId")
  private WebElement emailField;
  @FindBy(css="#identifierNext")
  private WebElement nextButton;
  @FindBy(css = "#password input[type='password']")
  private WebElement passwordField;
  @FindBy(css = "#passwordNext")
  private WebElement loginButton;


  public void login(String googleEmail, String googlePassword) {
    getUrl(URLsContent.GOOGLE_LOGIN_PAGE);
    performLogin(googleEmail, googlePassword);
  }

  private void performLogin(String email, String password) {
    typeEmail(email);
    wait.forXMilliseconds(3500);
    clickNextButton();
    WebDriverWait wait = new WebDriverWait(driver, 20);
//    wait.forXMilliseconds(3500);
    typePassword(password);
//    wait.forXMilliseconds(3500);
    clickLoginButton();
  }

  public void register() {
    //TODO
  }
  private void clickLoginButton() {
    waitAndClick(loginButton);
  }

  private void clickNextButton() {
    waitAndClick(nextButton);
  }

  private void typePassword(String password) {
    wait.forXMilliseconds(1500);
    fillInput(passwordField, password);
  }

  private void typeEmail(String email) {
    fillInput(emailField, email);
  }

  public void googleLogout() {
    getUrl(URLsContent.GOOGLE_LOGOUT);
  }

}
