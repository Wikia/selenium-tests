package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;

/**
 * Created by qaga on 2015-07-24.
 */
public class JoinPageObject extends BasePageObject {

  @FindBy(css = ".register-message:last-child")
  private WebElement joinTodayMessage;

  @FindBy(css = ".button.signup-provider-email")
  private WebElement registerWithEmailButton;

  @FindBy(css = "a.sign-in")
  private WebElement signInLink;

  public JoinPageObject(WebDriver driver) {
    super(driver);
  }

  public JoinPageObject get() {
    String redirectParameter = "";

    try {
      redirectParameter =
          URLEncoder.encode(urlBuilder.getUrlForWiki(Configuration.getWikiName()), "UTF-8");

    } catch (UnsupportedEncodingException e) {
      LOG.error("encoding", "problem occured during URL encoding");
    }
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "join" + "?redirect="
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
