package com.wikia.webdriver.elements.mercury.pages.login;

import com.wikia.webdriver.elements.mercury.components.login.LoginArea;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;

/**
 * Created by neptunooo on 06/06/16.
 */
public class SignInPage extends WikiBasePageObject{

    @Getter(lazy = true)
    private final LoginArea loginArea = new LoginArea(driver);
}

