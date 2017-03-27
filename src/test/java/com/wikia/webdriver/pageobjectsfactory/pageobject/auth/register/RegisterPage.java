package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

/**
 * Created by ogolec on 27.03.17.
 */
public interface RegisterPage {
  AttachedRegisterPage open();

  boolean isModalOpen();

  AttachedRegisterPage typeEmailAddress(String email);

  AttachedRegisterPage typeUsername(String username);

  AttachedRegisterPage typePassword(String password);

  AttachedRegisterPage typeBirthdate(String month, String day, String year);

  void clickSignUpSubmitButton();

  boolean doesErrorMessageContainText();

  void verifyBirthdateError();
}
