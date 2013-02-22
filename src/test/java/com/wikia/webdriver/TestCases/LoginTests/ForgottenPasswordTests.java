package com.wikia.webdriver.TestCases.LoginTests;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.DataProvider.LoginDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.DropDownComponentObject.DropDownComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ForgottenPasswordTests extends TestTemplate {

    @Test(
        groups = { "ForgottenPassword_001_dropdown", "ForgottenPassword" }
    )
    public void ForgottenPassword_001_dropdown() {
        CommonFunctions.logOut(driver);
        String userName = Properties.userNameForgottenPassword;

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        DropDownComponentObject dropdown = new DropDownComponentObject(driver, Global.DOMAIN);
        dropdown.openDropDown();
        dropdown.remindPassword(userName);

        dropdown.verifyMessageAboutNewPassword(userName);
        String newPassword = dropdown.receiveMailWithNewPassowrd();
        dropdown.openDropDown();
        dropdown.logIn(userName, newPassword);
        SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
        newPassword = special.setNewPassword();
        special.verifyUserLoggedIn(userName);

        CommonFunctions.logOut(driver);
        dropdown.openDropDown();
        dropdown.logIn(userName, newPassword);
        dropdown.verifyUserLoggedIn(userName);

        CommonFunctions.logOut(driver);
    }

    @Test(
        groups = { "ForgottenPassword_002_specialPage", "ForgottenPassword" }
    )
    public void ForgottenPassword_002_specialPage() {
        CommonFunctions.logOut(driver);
        String userName = Properties.userNameForgottenPassword2;

        WikiBasePageObject base = new WikiBasePageObject(driver, Global.DOMAIN);
        base.openWikiPage();
        SpecialUserLoginPageObject special = new SpecialUserLoginPageObject(driver);
        special.openSpecialUserLogin();
        special.remindPassword(userName);
        special.verifyMessageAboutNewPassword(userName);
        String newPassword = special.receiveMailWithNewPassowrd();
        special.login(userName, newPassword);
        newPassword = special.setNewPassword();
        special.verifyUserLoggedIn(userName);

        CommonFunctions.logOut(driver);
        special.openSpecialUserLogin();
        special.login(userName, newPassword);
        special.verifyUserLoggedIn(userName);

        CommonFunctions.logOut(driver);
    }
}
