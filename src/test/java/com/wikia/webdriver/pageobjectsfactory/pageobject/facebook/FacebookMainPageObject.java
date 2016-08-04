package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookMainPageObject extends WikiBasePageObject {

  @FindBy(css = "#email")
  private WebElement emailField;
  @FindBy(css = "#pass")
  private WebElement passwordField;
  @FindBy(css = "#loginbutton")
  private WebElement loginButton;

  public FacebookMainPageObject(WebDriver driver) {
    super();
  }

  public FacebookUserPageObject login(String facebookEmail, String facebookPassword) {
    typeEmail(facebookEmail);
    typePassword(facebookPassword);
    clickLoginButton();
    return new FacebookUserPageObject(driver);
  }

  public void clickLoginButton() {
    wait.forElementVisible(loginButton);
    loginButton.click();
    PageObjectLogging.log("clickLoginButton", "facebook login button clicked", true);
  }

  private void typePassword(String password) {
    wait.forElementVisible(passwordField);
    passwordField.sendKeys(password);
  }

  private void typeEmail(String email) {
    wait.forElementVisible(emailField);
    emailField.sendKeys(email);
  }
}
