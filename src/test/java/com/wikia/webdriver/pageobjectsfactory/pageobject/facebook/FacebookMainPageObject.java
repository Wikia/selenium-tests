package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class FacebookMainPageObject extends WikiBasePageObject {

  @FindBy(css = "#email")
  private WebElement emailField;
  @FindBy(css = "#pass")
  private WebElement passwordField;
  @FindBy(css = "#loginbutton")
  private WebElement loginButton;

  public FacebookMainPageObject(WebDriver driver) {
    super(driver);
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
    LOG.success("clickLoginButton", "facebook login button clicked");
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
