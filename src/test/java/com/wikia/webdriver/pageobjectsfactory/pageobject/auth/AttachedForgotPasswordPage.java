package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedForgotPasswordPage extends BaseAuthPage implements ForgotPasswordPage {

  @FindBy(css = "#forgotPasswordSubmit")
  private WebElement requestLinkButton;

  public void submit() {
    wait.forElementVisible(requestLinkButton).click();
  }

  public void requestLinkForUsername(String username) {
    typeUsername(username);
    submit();
  }

}
