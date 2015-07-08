package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Qaga on 2015-06-30.
 */
public class LoginPage extends WikiBasePageObject {

  @FindBy(css = "#loginUsername")
  private WebElement usernameField;

  @FindBy(css = "#loginPassword")
  private WebElement passwordField;

  @FindBy(css = "#loginSubmit")
  private WebElement submitButton;

  @FindBy(css = "small.error")
  private WebElement errorMessage;

  private NavigationSideComponentObject nav;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void logUserIn(String username, String password) {
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    submitButton.click();
  }

  public LoginPage get() {
    driver.get(urlBuilder.getUrlForWiki("glee") + "login" + "?redirect=" + urlBuilder
        .getUrlForWiki("glee"));

    return this;
  }

  public NavigationSideComponentObject getNav() {
    if (nav == null) {
      nav = new NavigationSideComponentObject(driver);
    }

    return nav;
  }

  public String getErrorMessage() {
    return errorMessage.getText();

  }

  public boolean isSubmitButtonDisabled() {
    return "true".equals(submitButton.getAttribute("disabled"));
  }
}
