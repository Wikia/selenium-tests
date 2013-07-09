package com.wikia.webdriver.TestCases.SignUpTests;

import com.ibm.icu.util.Calendar;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;
import java.text.SimpleDateFormat;
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
		Calendar currentDate = Calendar.getInstance();
		signUpPage.enterBirthDate(
			Integer.toString(currentDate.get(Calendar.MONTH) + 1),// +1 because months are numerated from 0
			Integer.toString(currentDate.get(Calendar.DAY_OF_MONTH)),
			Integer.toString(currentDate.get(Calendar.YEAR)- PageContent.MIN_AGE)
		);
		signUpPage.waitForTooYoungErrorMsg();
	}
}
