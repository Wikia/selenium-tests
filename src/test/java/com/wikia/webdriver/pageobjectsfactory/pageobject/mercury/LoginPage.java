package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * @ownership Social
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

  @FindBy(css = "a.close")
  private WebElement closeButton;

  @FindBy(css = "a.footer-callout-link")
  private WebElement registerNowLink;

  @FindBy(css = "header.auth-header")
  private WebElement loginHeader;

  @FindBy(css = ".password-toggler")
  private WebElement passwordToggler;

  private NavigationSideComponentObject nav;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void logUserIn(String username, String password) {
    wait.forElementVisible(usernameField);
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    submitButton.click();
  }

  public LoginPage get() {
    String redirectParameter = "";

    try {
      redirectParameter =
          URLEncoder.encode(urlBuilder.getUrlForWiki(Configuration.getWikiName()), "UTF-8");

    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.log("encoding", "problem occured during URL encoding", false);
    }
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "login" + "?redirect="
               + redirectParameter);
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

  /**
   * Check if button is disabled for the @duration
   *
   * @param duration in seconds
   */
  public boolean isSubmitButtonDisabled(int duration) {
    changeImplicitWait((duration * 1000) / 4, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, duration, (duration * 1000) / 2)
          .until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
              return !isSubmitButtonDisabled();
            }
          });
      return false;
    } catch (TimeoutException e) {
      return true;
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public String getCloseButtonURL() {
    return closeButton.getAttribute("href");
  }

  public void clickOnCloseButton() {
    closeButton.click();
  }

  public void clickOnRegisterLink() {
    registerNowLink.click();
  }

  public String getLoginHeaderText() {
    return loginHeader.getText();
  }

  public void typePassword(String password) {
    passwordField.sendKeys(password);
  }

  public void clickOnPasswordToggler() {
    passwordToggler.click();
  }

  public Boolean isPasswordTogglerDisabled() {
    String togglerDisabled = passwordField.getAttribute("type");
    return "password".equals(togglerDisabled);
  }

  public Boolean isPasswordTogglerEnabled() {
    String togglerDisabled = passwordField.getAttribute("type");
    return "text".equals(togglerDisabled);
  }
}
