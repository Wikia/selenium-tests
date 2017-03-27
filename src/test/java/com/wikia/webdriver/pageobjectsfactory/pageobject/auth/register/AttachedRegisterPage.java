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


  @FindBy(css = "#signupEmail")
  private WebElement emailField;
  @FindBy(css = "#signupUsername")
  private WebElement usernameField;
  @FindBy(css = "#signupPassword")
  private WebElement passwordField;
  @FindBy(css = "#signupBirthDate")
  private WebElement birthdateField;
  @FindBy(css = ".birth-month")
  private WebElement birthMonthField;
  @FindBy(css = ".birth-day")
  private WebElement birthDayField;
  @FindBy(css = ".birth-year")
  private WebElement birthYearField;
  @FindBy(css = "#signupSubmit")
  private WebElement submitButton;
  @FindBy(css = "#signupForm div:nth-child(2) small")
  private WebElement usernameError;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[3]/small")
  private WebElement passwordError;
  @FindBy(css = "#signupForm > small.error")
  private WebElement genericError;


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

  @Override public void clickSignUpSubmitButton() {
    waitAndClick(submitButton);
  }


  @Override public boolean doesErrorMessageContainText() {
    return usernameError.getText().contains("Username is taken");
  }

  @Override public void verifyBirthdateError() {
    wait.forTextInElement(genericError, "We cannot complete your registration at this time");
  }

}


