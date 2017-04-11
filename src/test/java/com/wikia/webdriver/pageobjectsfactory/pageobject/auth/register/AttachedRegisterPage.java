package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormError;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedRegisterPage extends BasePageObject implements RegisterPage {

  @FindBy(id = "signupEmail")
  private WebElement emailField;
  @FindBy(id = "signupUsername")
  private WebElement usernameField;
  @FindBy(id = "signupPassword")
  private WebElement passwordField;
  @FindBy(id = "signupBirthDate")
  private WebElement birthdateField;
  @FindBy(css = ".birth-month")
  private WebElement birthMonthField;
  @FindBy(css = ".birth-day")
  private WebElement birthDayField;
  @FindBy(css = ".birth-year")
  private WebElement birthYearField;
  @FindBy(id = "signupSubmit")
  private WebElement submitButton;

  private AuthPageContext authContext;

  public AttachedRegisterPage() {
    authContext = new AuthPageContext();
  }

  @Override public AttachedRegisterPage open() {
    driver.get(urlBuilder.getUrlForWiki() + URLsContent.USER_SIGNUP);
    return this;
  }

  @Override public boolean isDisplayed() {
    return authContext.isHeaderDisplayed();
  }

  @Override public boolean submitButtonNotClickable() {
    return !wait.forElementVisible(submitButton).isEnabled();
  }

  @Override public RegisterPage typeEmailAddress(String email) {
    fillInput(emailField, email);
    return this;
  }

  @Override public RegisterPage typeUsername(String username) {
    fillInput(usernameField, username);
    return this;
  }

  @Override public RegisterPage typePassword(String password) {
    fillInput(passwordField, password);
    return this;
  }

  @Override public RegisterPage typeBirthdate(String month, String day, String year) {
    waitAndClick(birthdateField);

    waitAndClick(birthMonthField);
    birthMonthField.sendKeys(month);

    waitAndClick(birthDayField);
    birthDayField.sendKeys(day);

    waitAndClick(birthYearField);
    birthYearField.sendKeys(year);

    return this;
  }

  @Override public void submit() {
    waitAndClick(submitButton);
  }

  @Override public AttachedSignInPage navigateToSignIn() {
    return authContext.navigateToSignIn();
  }

  @Override public String getError() {
    return new FormError().getError();
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return authContext.clickFacebookSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    return authContext.isConnectWithFacebookButtonVisible();
  }

}
