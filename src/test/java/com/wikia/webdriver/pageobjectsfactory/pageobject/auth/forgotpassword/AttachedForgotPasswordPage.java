package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedForgotPasswordPage extends BasePageObject implements ForgotPasswordPage {

  @FindBy(css = "#forgotPasswordSubmit")
  private WebElement requestLinkButton;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;

  public void submit() {
    wait.forElementVisible(requestLinkButton).click();
  }

  public void requestLinkForUsername(String username) {
    fillInput(usernameField, username);
    submit();
  }

}
