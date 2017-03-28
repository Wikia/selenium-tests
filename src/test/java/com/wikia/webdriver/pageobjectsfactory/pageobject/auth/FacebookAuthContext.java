package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.FacebookSignupModalComponentObject;

public interface FacebookAuthContext {

  FacebookSignupModalComponentObject clickFacebookSignUp();

  boolean isConnetctWithFacebookButtonVisible();

}
