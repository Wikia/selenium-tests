package com.wikia.webdriver.testcases.mobile.mercury;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

/**
 * Created by Qaga on 2015-06-29.
 */
public class Login extends NewTestTemplate {

    @Test()
    public void ValidUserCanLogIn() {
        LoginPage loginPage = new LoginPage(driver).get();
        loginPage.logUserIn(config.getCredentials().userName10, config.getCredentials().password10);

        loginPage.getNav().isUserLoggedIn(config.getCredentials().userName);
    }
}
