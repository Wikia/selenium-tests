package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginPageObject {

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
  @FindBy(css = "a.header-callout-link span")
  private WebElement registerNowLink;
  @FindBy(css = "header.auth-header")
  private WebElement loginHeader;
  @FindBy(css = ".password-toggler")
  private WebElement passwordToggler;
  @FindBy(css = ".sign-in")
  private WebElement signInButton;

  private WebDriver driver;
  private Wait wait;
  private UrlBuilder urlBuilder;

  public LoginPageObject(WebDriver driver) {
    this.driver = driver;
    this.urlBuilder = new UrlBuilder();
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public LoginPageObject get() {
    String redirectParameter = "";

    try {
      redirectParameter =
          URLEncoder.encode(urlBuilder.getUrlForWiki(Configuration.getWikiName()), "UTF-8");

    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.log("encoding", "problem occured during URL encoding", false);
    }
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "/login" + "?redirect="
               + redirectParameter);
    return this;
  }

  public String getErrorMessage() {
    return errorMessage.getText();

  }

  public String getLoginHeaderText() {
    return loginHeader.getText();
  }

  public LoginPageObject clickOnSignInButton() {
    wait.forElementVisible(signInButton);
    signInButton.click();
    return this;
  }

  public void clickOnCloseButton() {
    closeButton.click();
  }

  public void clickOnRegisterLink() {
    registerNowLink.click();
  }

  public void clickOnPasswordToggler() {
    passwordToggler.click();
  }

  public boolean isSubmitButtonDisabled() {
    return "true".equals(submitButton.getAttribute("disabled"));
  }

  public Boolean isPasswordTogglerDisabled() {
    String togglerDisabled = passwordField.getAttribute("type");
    return "password".equals(togglerDisabled);
  }

  public Boolean isPasswordTogglerEnabled() {
    String togglerDisabled = passwordField.getAttribute("type");
    return "text".equals(togglerDisabled);
  }

  public void logUserIn(String username, String password) {
    wait.forElementVisible(usernameField);
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    submitButton.click();
  }

  public void typePassword(String password) {
    passwordField.sendKeys(password);
  }
}
