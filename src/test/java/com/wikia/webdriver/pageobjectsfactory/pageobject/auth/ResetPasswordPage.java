package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;


import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetPasswordPage extends BasePageObject implements FormPage {

  @FindBy(id = "newPassword")
  private WebElement newPasswordInput;
  @FindBy(id = "confirmNewPassword")
  private WebElement newPasswordConfirmationInput;
  @FindBy(id = "resetPasswordSubmit")
  private WebElement resetPasswordButton;
  @FindBy (css = ".second-card .auth-header")
  private WebElement secondCardHeader;

  private static final String PASSWORD_RESET_SUCCESS = "Your password has been reset.";

  private AuthPageContext authContext;

  public ResetPasswordPage(String url) {
    driver.get(url);
    authContext = new AuthPageContext();
  }

  @Override public String getError() {
    return new FormError().getError();
  }

  @Override public void submit() {
    waitAndClick(resetPasswordButton);
  }

  @Override public FormPage open() {
    throw new UnsupportedOperationException("Error trying to open a detached window in old tab");
  }

  @Override public boolean isDisplayed() {
    return authContext.isHeaderDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return !wait.forElementVisible(resetPasswordButton).isEnabled();
  }

  private void fillNewPassword(String password) {
    wait.forElementVisible(newPasswordInput).sendKeys(password);
  }

  private void fillConfirmedPassword(String password) {
    wait.forElementVisible(newPasswordConfirmationInput).sendKeys(password);
  }

  public void setNewPassword(String password) {
    fillNewPassword(password);
    fillConfirmedPassword(password);
    submit();
  }

  public boolean newPasswordSetSuccessfully() {
    return authContext.confirmationDisplayed(PASSWORD_RESET_SUCCESS);
  }
}
