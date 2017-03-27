package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttachedRegisterPage extends BasePageObject implements RegisterPage {

  @Override public AttachedRegisterPage open() {
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

  @Override public RegisterPage typeEmailAddress(String email) {
    wait.forElementVisible(signupEmail).sendKeys(email);
    return this;
  }

  @Override public RegisterPage typeUsername(String username) {
    wait.forElementVisible(signupUsername).sendKeys(username);
    return this;
  }

  @Override public RegisterPage typePassword(String password) {
    wait.forElementVisible(signupPassword).sendKeys(password);
    return this;
  }

  @Override public RegisterPage typeBirthdate(String month, String day, String year) {
    wait.forElementVisible(signupBirthdate).click();

    wait.forElementVisible(signupBirthMonth).click();
    signupBirthMonth.sendKeys(month);

    wait.forElementVisible(signupBirthDay).click();
    signupBirthDay.sendKeys(day);

    wait.forElementVisible(signupBirthYear).click();
    signupBirthYear.sendKeys(year);

    return this;
  }

  @Override public void clickSignUpSubmitButton() {
    wait.forElementVisible(signupSubmitButton).click();
  }


  @Override public boolean doesErrorMessageContainText() {
    return usernameError.getText().contains("Username is taken");
  }

  @Override public void verifyBirthdateError() {
    wait.forTextInElement(genericError, "We cannot complete your registration at this time");
  }

}


