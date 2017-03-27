package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

public interface RegisterPage {
  RegisterPage open();

  RegisterPage typeEmailAddress(String email);

  RegisterPage typeUsername(String username);

  RegisterPage typePassword(String password);

  RegisterPage typeBirthdate(String month, String day, String year);

  void clickSignUpSubmitButton();

  boolean doesErrorMessageContainText();

  void verifyBirthdateError();
}
