package com.wikia.webdriver.TestCases.SignUpTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.AlmostThereMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.ConfirmationMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.SignUpMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.UserProfileMonoBookPageObject;

public class SignUpTests_account_creation_monobook extends TestTemplate
{
    private String timeStamp, userName, userNameEnc, password, tempPassword;

    @Test(groups = {"SignUp_monobook_account_creation_TC_001", "monobook", "", "Smoke"})
    public void SignUp_account_creation_TC_001_non_latin_user_name()
    {
        SignUpMonoBookPageObject signUp = new SignUpMonoBookPageObject(driver);
        timeStamp = signUp.getTimeStamp(); 
        userName = Properties.userNameWithUnderScore+timeStamp;
        password = "QAPassowrd" + timeStamp;

        signUp.openSignUpPage();
        signUp.fillLoginForm(userName, password);
        AlmostThereMonoBookPageObject almostThere = signUp.submitForm();
        almostThere.verifyAlmostTherePage();
        ConfirmationMonoBookPageObject confirmPageAlmostThere = almostThere.enterActivationLink();
        confirmPageAlmostThere.fillForm(userName, password);
        confirmPageAlmostThere.submitForm();
        UserProfileMonoBookPageObject userProfile = new UserProfileMonoBookPageObject(driver, userName);
        userProfile.verifyUserProfileWelcomePage(userName);
        userProfile.verifyWelcomeEmail();
    }
}
