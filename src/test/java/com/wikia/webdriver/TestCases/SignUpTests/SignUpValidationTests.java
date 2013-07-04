/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.TestCases.SignUpTests;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;
import org.testng.annotations.Test;

public class SignUpValidationTests extends TestTemplate {

	@Test(groups = {"SignUp_test"})
	public void SignUp_field_validation_TC002()
	{
		String userNameEmail = Properties.emailQaart2;
		String passwordEmail = Properties.emailPasswordQaart2;
		SignUpPageObject signUpPage = new SignUpPageObject(driver);
		signUpPage.openSignUpPage();
	}
}
