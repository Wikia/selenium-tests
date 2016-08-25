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

public class JoinPageObject {

  @FindBy(css = ".register-message:last-child")
  private WebElement joinTodayMessage;

  @FindBy(css = ".button.signup-provider-email")
  private WebElement registerWithEmailButton;

  @FindBy(css = "a.sign-in")
  private WebElement signInLink;

  private WebDriver driver;
  private Wait wait;
  private UrlBuilder urlBuilder;

  public JoinPageObject(WebDriver driver) {
    this.driver = driver;
    this.urlBuilder = new UrlBuilder();
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public JoinPageObject get() {
    String redirectParameter = "";

    try {
      redirectParameter =
          URLEncoder.encode(urlBuilder.getUrlForWiki(Configuration.getWikiName()), "UTF-8");

    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.log("encoding", "problem occured during URL encoding", false);
    }
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "/join" + "?redirect="
               + redirectParameter);
    return this;
  }

  public String getJoinTodayText() {
    return joinTodayMessage.getText();
  }

  public void clickRegisterWithEmail() {
    wait.forElementVisible(registerWithEmailButton);
    registerWithEmailButton.click();
  }

  public void clickSignInLink() {
    wait.forElementVisible(signInLink);
    signInLink.click();
  }
}

