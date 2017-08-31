package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;

import java.time.LocalDate;

public interface RegisterPage extends FormPage {

  RegisterPage typeEmailAddress(String email);

  RegisterPage typeUsername(String username);

  RegisterPage typePassword(String password);

  RegisterPage typeBirthday(int month, int day, int year);

  SignInPage navigateToSignIn();

  void signUp(String email, String username, String password, LocalDate birthday);

  void signUp(SignUpUser user);

  RegisterPage fillForm(SignUpUser user);

}
