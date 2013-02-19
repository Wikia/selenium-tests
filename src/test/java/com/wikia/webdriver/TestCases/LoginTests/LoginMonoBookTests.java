package com.wikia.webdriver.TestCases.LoginTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginMonobookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.UserProfileMonoBookPageObject;

public class LoginMonoBookTests extends TestTemplate {

    @DataProvider
    private static final Object[][] getUserCredentials() {
        return new Object[][] {
            { Properties.userName, Properties.password, Properties.userName },
            { Properties.userName2, Properties.password2, Properties.userName2 },
            { Properties.userNameWithUnderScore, Properties.passwordWithUnderScore,
                Properties.userNameWithUnderScore },
            { Properties.userNameNonLatin, Properties.passwordNonLatin,
                Properties.userNameNonLatinEncoded },
            { Properties.userNameWithBackwardSlash,
                Properties.passwordWithBackwardSlash,
                Properties.userNameWithBackwardSlashEncoded },
            { Properties.userNameLong, Properties.passwordLong,
                Properties.userNameLong },
            { Properties.userNameStaff, Properties.passwordStaff,
                Properties.userNameStaff }
        };
    }

    @Test(dataProvider = "getUserCredentials", groups = {"monobook", "Login_monobook_001", "" })
    public void Login_monobook_001_SpecialPage(String userName, String password,
        String userNameEnc) {
            PageObjectLogging.log("Login_monobook_001_SpecialPage", userName, true);
            CommonFunctions.logOut(driver);

            SpecialUserLoginMonobookPageObject loginPage = new SpecialUserLoginMonobookPageObject(driver);
            loginPage.open();
            loginPage.fillLoginForm(userName, password, userNameEnc);
            loginPage.submitForm();
            UserProfileMonoBookPageObject userProfile = new UserProfileMonoBookPageObject(driver, userName);
            userProfile.verifyUserProfilePage(userName);

            CommonFunctions.logOut(driver);
    }
}
