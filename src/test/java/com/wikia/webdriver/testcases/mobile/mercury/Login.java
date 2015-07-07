package com.wikia.webdriver.testcases.mobile.mercury;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;
import org.testng.annotations.Test;

/**
 * Created by Qaga on 2015-06-29.
 */
public class Login extends NewTestTemplate {

    @Test()
    public void ValidUserCanLogIn() {
        LoginPage loginPage = new LoginPage(driver).get();
        loginPage.logUserIn(Configuration.getCredentials().userName10, Configuration.getCredentials().password10);

        Assertion.assertTrue(loginPage.getNav().isUserLoggedIn(Configuration.getCredentials().userName10));
    }
}
