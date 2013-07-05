/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.TestCases.SignUpTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;
import org.testng.annotations.Test;

public class SignUpValidationTests extends TestTemplate {

	@Test(groups = {"SignUp_validation_2"})
	public void SignUp_validation_2()
	{
		SignUpPageObject signUpPage = new SignUpPageObject(driver);
                signUpPage.openSignUpPage();
                String userNameNotExisting = signUpPage.getTimeStamp();
                signUpPage.typeInUserName(userNameNotExisting);
                String userNameEmail = signUpPage.getTimeStamp();
                signUpPage.typeInEmail(userNameEmail);
                String passwordNotExisting = signUpPage.getTimeStamp();
                signUpPage.typeInPassword(passwordNotExisting);
                signUpPage.selectToYoungBirthDate();
                signUpPage.clickCreateAccountButton();
        }
}
