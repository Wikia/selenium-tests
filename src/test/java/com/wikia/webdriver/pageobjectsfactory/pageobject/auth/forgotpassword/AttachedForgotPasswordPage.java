package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormError;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;
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

  @Override public FormPage open() {
    return null;
  }

  public void requestLinkForUsername(String username) {
    fillInput(usernameField, username);
    submit();
  }

  @Override public String getError() {
    return FormError.getError();
  }
}
