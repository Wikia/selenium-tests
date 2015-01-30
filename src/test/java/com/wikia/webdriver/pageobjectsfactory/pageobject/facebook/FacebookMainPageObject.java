package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class FacebookMainPageObject extends WikiBasePageObject {

  public FacebookMainPageObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "#email")
  private WebElement emailField;
  @FindBy(css = "#pass")
  private WebElement passwordField;
  @FindBy(css = "#loginbutton")
  private WebElement loginButton;

  public FacebookUserPageObject login(String facebookEmail, String facebookPassword) {
    typeEmail(facebookEmail);
    typePassword(facebookPassword);
    clickLoginButton();
    return new FacebookUserPageObject(driver);
  }

  public void clickLoginButton() {
    waitForElementByElement(loginButton);
    loginButton.click();
    PageObjectLogging.log("clickLoginButton", "facebook login button clicked", true);
  }

  private void typePassword(String password) {
    waitForElementByElement(passwordField);
    passwordField.sendKeys(password);
  }

  private void typeEmail(String email) {
    waitForElementByElement(emailField);
    emailField.sendKeys(email);
  }

}
