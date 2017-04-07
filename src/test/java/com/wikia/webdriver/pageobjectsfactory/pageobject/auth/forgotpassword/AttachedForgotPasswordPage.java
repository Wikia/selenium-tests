package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;


import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormError;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedForgotPasswordPage extends BasePageObject implements ForgotPasswordPage {

  @FindBy(id = "forgotPasswordSubmit")
  private WebElement requestLinkButton;
  @FindBy(id = "loginUsername")
  private WebElement usernameField;

  private static final String PASS_REQUESTED_SUCCESS = "Thanks! Please check your email.";

  private AuthPageContext authContext;

  public AttachedForgotPasswordPage() {
    authContext = new AuthPageContext();
  }

  public void submit() {
    wait.forElementVisible(requestLinkButton).click();
  }

  @Override public FormPage open() {
    driver.get(urlBuilder.getUrlForWiki() + URLsContent.USER_FORGOT_PASSWORD);
    return this;
  }

  @Override public boolean isDisplayed() {
    return authContext.isHeaderDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return wait.forElementVisible(requestLinkButton).isEnabled();
  }

  public void requestLinkForUsername(String username) {
    fillInput(usernameField, username);
    submit();
  }

  @Override public String getError() {
    return new FormError().getError();
  }

  protected boolean isConfirmationDisplayed() {
    return authContext.confirmationDisplayed(PASS_REQUESTED_SUCCESS);
  }

}
