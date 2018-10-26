package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.GoogleSignupModalComponent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AttachedPageBase;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.AuthPageContext;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormError;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.AttachedSignInPage;
import java.time.LocalDate;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedRegisterPage extends AttachedPageBase<AttachedRegisterPage> implements RegisterPage {

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
  @FindBy(css = ".password-toggler")
  private WebElement passwordToggler;

  private AuthPageContext authContext;

  public AttachedRegisterPage() {
    super(URLsContent.USER_SIGNUP);
    authContext = new AuthPageContext();
  }

  @Override
  protected AttachedRegisterPage getThis() {
    return this;
  }

  @Override
  public boolean isDisplayed() {
    return authContext.isHeaderDisplayed();
  }

  @Override
  public boolean submitButtonNotClickable() {
    return !wait.forElementVisible(submitButton).isEnabled();
  }

  @Override
  public boolean isPasswordMasked() {
    return "password".equals(passwordField.getAttribute("type"));
  }

  @Override
  public void togglePasswordVisibility() {
    waitAndClick(passwordToggler);
  }

  @Override
  public RegisterPage typeEmailAddress(String email) {
    fillInput(emailField, email);
    return this;
  }

  @Override
  public RegisterPage typeUsername(String username) {
    fillInput(usernameField, username);
    return this;
  }

  @Override
  public RegisterPage typePassword(String password) {
    fillInput(passwordField, password);
    return this;
  }

  @Override
  public RegisterPage typeBirthday(int month, int day, int year) {
    waitAndClick(birthdateField);

    waitAndClick(birthMonthField);
    birthMonthField.sendKeys(Integer.toString(month));

    waitAndClick(birthDayField);
    birthDayField.sendKeys(Integer.toString(day));

    waitAndClick(birthYearField);
    birthYearField.sendKeys(Integer.toString(year));

    return this;
  }

  @Override
  public void submit() {
    waitAndClick(submitButton);
  }

  @Override
  public AttachedSignInPage navigateToSignIn() {
    return authContext.navigateToSignIn();
  }

  @Override
  public void signUp(String email, String username, String password, LocalDate date) {
    fillForm(email, username, password, date).submit();
  }

  @Override
  public void signUp(SignUpUser user) {
    signUp(user.getEmail(), user.getUsername(), user.getPassword(), user.getBirthday());
  }

  private RegisterPage fillForm(String email, String username, String password, LocalDate date) {
    typeEmailAddress(email);
    typeUsername(username);
    typePassword(password);
    typeBirthday(date.getMonthValue(), date.getDayOfMonth(), date.getYear());

    return this;
  }

  @Override
  public RegisterPage fillForm(SignUpUser user) {
    return fillForm(user.getEmail(), user.getUsername(), user.getPassword(), user.getBirthday());
  }

  @Override
  public String getError() {
    return new FormError().getError();
  }

  public FacebookSignupModalComponentObject clickFacebookSignUp() {
    return authContext.clickFacebookSignUp();
  }

  public GoogleSignupModalComponent clickGoogleSignUp() {
    return authContext.clickGoogleSignUp();
  }

  public boolean isConnectWithFacebookButtonVisible() {
    return authContext.isConnectWithFacebookButtonVisible();
  }

  public boolean isConnectWithGoogleButtonVisible() {
    return authContext.isConnectWithGoogleButtonVisible();
  }
}
