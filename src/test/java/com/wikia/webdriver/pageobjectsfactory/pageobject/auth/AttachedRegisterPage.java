package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedRegisterPage extends BaseAuthPage {

  public AttachedRegisterPage open() {
      driver.get(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_USER_SIGNUP);
      return this;
  }

  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement authModal;
  @FindBy(css = "#signupEmail")
  private WebElement signupEmail;
  @FindBy(css = "#signupUsername")
  private WebElement signupUsername;
  @FindBy(css = "#signupPassword")
  private WebElement signupPassword;
  @FindBy(css = "#signupBirthDate")
  private WebElement signupBirthdate;
  @FindBy(css = ".birth-month")
  private WebElement signupBirthMonth;
  @FindBy(css = ".birth-day")
  private WebElement signupBirthDay;
  @FindBy(css = ".birth-year")
  private WebElement signupBirthYear;
  @FindBy(css = "#signupSubmit")
  private WebElement signupSubmitButton;
  @FindBy(css = ".wikia-nav__avatar")
  private WebElement avatar;
  @FindBy(css = "#signupForm div:nth-child(2) small")
  private WebElement usernameError;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[3]/small")
  private WebElement passwordError;
  @FindBy(css = "#signupForm > small.error")
  private WebElement genericError;
  @FindBy(css = " header.auth-header")
  private WebElement registerHeader;

  public boolean isModalOpen() {
    return true;
  }

  public AttachedRegisterPage typeEmailAddress(String email) {
    wait.forElementVisible(signupEmail);
    signupEmail.sendKeys(email);
    return this;
  }

  public AttachedRegisterPage typeUsername(String username) {
    wait.forElementVisible(signupUsername);
    signupUsername.sendKeys(username);
    return this;
  }

  public AttachedRegisterPage typePassword(String password) {
    wait.forElementVisible(signupPassword);
    signupPassword.sendKeys(password);
    return this;
  }

  public AttachedRegisterPage typeBirthdate(String month, String day, String year) {
    wait.forElementVisible(signupBirthdate);
    signupBirthdate.click();

    wait.forElementVisible(signupBirthMonth);
    signupBirthMonth.click();
    signupBirthMonth.sendKeys(month);

    wait.forElementVisible(signupBirthDay);
    signupBirthDay.click();
    signupBirthDay.sendKeys(day);

    wait.forElementVisible(signupBirthYear);
    signupBirthYear.click();
    signupBirthYear.sendKeys(year);

    return this;
  }

  public void clickSignUpSubmitButton() {
    wait.forElementVisible(signupSubmitButton);
    signupSubmitButton.click();
  }


  public boolean doesErrorMessageContainText() {
    return usernameError.getText().contains("Username is taken");
  }

  public void verifyBirthdateError() {
    wait.forElementVisible(genericError);
    Assertion.assertEquals(genericError.getText(),
                           "We cannot complete your registration at this time");
  }

}


