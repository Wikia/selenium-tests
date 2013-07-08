package com.wikia.webdriver.TestCases.SignUpTests;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;
import org.testng.annotations.Test;

public class SignUpValidationTests extends TestTemplate {

    @Test(groups = {"SignUp_validation2", "SignUp_validation"})
    public void SignUp_validation2()
    {
	SignUpPageObject signUpPage = new SignUpPageObject(driver);
	signUpPage.openSignUpPage();
	signUpPage.typeInUserName(signUpPage.getTimeStamp());
	signUpPage.typeInEmail(Properties.emailQaart1);
	signUpPage.typeInPassword(signUpPage.getTimeStamp());
	signUpPage.selectToYoungBirthDate();
	signUpPage.waitForTooYoungErrorMsg();
    }
}
