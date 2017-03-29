package com.wikia.webdriver.pageobjectsfactory.pageobject.auth.register;

import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.FormPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.auth.signin.SignInPage;

public interface RegisterPage extends FormPage {

  String pageTitle = "Join Fandom Today";

  RegisterPage typeEmailAddress(String email);

  RegisterPage typeUsername(String username);

  RegisterPage typePassword(String password);

  RegisterPage typeBirthdate(String month, String day, String year);

  SignInPage navigateToSignIn();

}
