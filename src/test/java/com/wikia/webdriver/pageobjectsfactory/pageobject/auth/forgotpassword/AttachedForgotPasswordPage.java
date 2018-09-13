package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.forgotpassword;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AttachedPageBase;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormError;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedForgotPasswordPage extends
    AttachedPageBase<AttachedForgotPasswordPage> implements ForgotPasswordPage {

  private static final String PASS_REQUESTED_SUCCESS = "Thanks! Please check your email.";
  @FindBy(id = "forgotPasswordSubmit")
  private WebElement requestLinkButton;
  @FindBy(id = "loginUsername")
  private WebElement usernameField;
  private AuthPageContext authContext;

  public AttachedForgotPasswordPage() {
    super(URLsContent.USER_FORGOT_PASSWORD);
    authContext = new AuthPageContext();
  }

  public void submit() {
    wait.forElementVisible(requestLinkButton).click();
  }

  @Override
  protected AttachedForgotPasswordPage getThis() {
    return this;
  }

  @Override
  public boolean isDisplayed() {
    return authContext.isHeaderDisplayed();
  }

  @Override
  public boolean submitButtonNotClickable() {
    return wait.forElementVisible(requestLinkButton).isEnabled();
  }

  public void requestLinkForUsername(String username) {
    fillInput(usernameField, username);
    submit();
  }

  @Override
  public String getError() {
    return new FormError().getError();
  }

  protected boolean isConfirmationDisplayed() {
    return authContext.confirmationDisplayed(PASS_REQUESTED_SUCCESS);
  }
}
