package com.wikia.webdriver.Common.DataProvider;

import com.wikia.webdriver.Common.Properties.Properties;
import org.testng.annotations.DataProvider;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class LoginDataProvider {

    @DataProvider
    public static final Object[][] getUserCredentials() {
        return new Object[][] {
            { 
                Properties.userName, Properties.password, Properties.userName
            }, {
                Properties.userName2, Properties.password2, Properties.userName2
            }, {
                Properties.userNameWithUnderScore,
                Properties.passwordWithUnderScore,
                Properties.userNameWithUnderScore
            }, {
                Properties.userNameNonLatin, Properties.passwordNonLatin,
                Properties.userNameNonLatinEncoded
            }, {
                Properties.userNameWithBackwardSlash,
                Properties.passwordWithBackwardSlash,
                Properties.userNameWithBackwardSlashEncoded
            }, {
                Properties.userNameLong, Properties.passwordLong,
                Properties.userNameLong
            }, {
                Properties.userNameStaff, Properties.passwordStaff,
                Properties.userNameStaff
            },
        };
    }
}
