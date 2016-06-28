package com.wikia.webdriver.elements.mercury.pages.login;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.elements.mercury.components.login.LoginArea;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final LoginArea loginArea = new LoginArea(driver);

  @FindBy(css = ".error")
  private WebElement errorMessage;
  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;
  @FindBy(css = ".signup-providers li a")
  private WebElement connectWithFacebookButton;

  private Wait wait;

  public SignInPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.wait = new Wait(driver);
  }

  public String getErrorMessage() {
    wait.forElementVisible(errorMessage);

    return errorMessage.getText();
  }

  public SignInPage verifyErrorMessage(String errorMessage) {
    Assertion.assertEquals(getErrorMessage(), errorMessage);

    return this;
  }


  public SignInPage clickForgotPasswordLink() {
    wait.forElementClickable(forgottenPasswordLink);
    forgottenPasswordLink.click();

    return this;
  }

  public SignInPage isConnetctWithFacebookButtonVisible() {
    wait.forElementVisible(connectWithFacebookButton);

    return this;
  }
}

