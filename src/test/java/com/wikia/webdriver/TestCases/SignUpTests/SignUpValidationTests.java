package com.wikia.webdriver.TestCases.SignUpTests;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;
import org.testng.annotations.Test;

public class SignUpValidationTests extends TestTemplate {

	@Test(groups = {"SignUp_validation_1", "SignUp_validation"})
	public void SignUp_validation_1() {
		SignUpPageObject signUpPage = new SignUpPageObject(driver);
		signUpPage.openSignUpPage();
		signUpPage.typeInUserName(signUpPage.getTimeStamp());
		String userNameEmail = Properties.emailQaart4;
		signUpPage.typeInEmail(userNameEmail);
		signUpPage.typeInPassword(signUpPage.getTimeStamp());
		signUpPage.enterBirthDate(
		PageContent.wikiSignUpBirthMonth,
		PageContent.wikiSignUpBirthDay,
		PageContent.wikiSignUpBirthYear
		);
		signUpPage.enterWrongBlurryWord();
		signUpPage.clickCreateAccountButton();
		signUpPage.verifyWrongBlurryWordValidation();
	}

}