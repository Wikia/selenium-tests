package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BaseAuthPage {

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
