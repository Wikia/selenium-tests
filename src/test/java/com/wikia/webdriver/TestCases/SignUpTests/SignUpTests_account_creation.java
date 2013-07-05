package com.wikia.webdriver.TestCases.SignUpTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.AlmostTherePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.ConfirmationPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.SignUpPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences.PreferencesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences.PreferencesPageObject.tabNames;

public class SignUpTests_account_creation extends TestTemplate
{	
	private String timeStamp, userName, userNameEnc, password, tempPassword, userNameEmail, passwordEmail;
	
	/*
	 * 3.30 Test Case 2.3.01 Sign up page: Account creation Non latin username
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_Sign_up  
	 * */
	@Test(groups = {"SignUp_account_creation_TC_001", "SignUp", "Smoke"})
	public void SignUp_account_creation_TC_001_non_latin_user_name()
	{
		userNameEmail = Properties.emailQaart1;
		passwordEmail = Properties.emailPasswordQaart1;
		SignUpPageObject signUp = new SignUpPageObject(driver);
		timeStamp = signUp.getTimeStamp(); 
		userName = Properties.userNameNonLatin+timeStamp;
		userNameEnc = Properties.userNameNonLatinEncoded+timeStamp;
		password = "QAPassword"+timeStamp;
		signUp.openSignUpPage();
		signUp.typeInEmail(userNameEmail);
		signUp.typeInUserName(userName);
		signUp.typeInPassword(password);
		signUp.enterBirthDate(
			PageContent.wikiSignUpBirthMonth, 
			PageContent.wikiSignUpBirthDay, 
			PageContent.wikiSignUpBirthYear
		);
		signUp.enterBlurryWord();
		AlmostTherePageObject almostTherePage = signUp.submit(userNameEmail, passwordEmail);
		almostTherePage.verifyAlmostTherePage();
		ConfirmationPageObject confirmPageAlmostThere = almostTherePage.enterActivationLink(userNameEmail, passwordEmail);
		confirmPageAlmostThere.typeInUserName(userName);
		confirmPageAlmostThere.typeInPassword(password);
		UserProfilePageObject userProfile = confirmPageAlmostThere.clickSubmitButton(userNameEmail, passwordEmail);
		userProfile.verifyUserLoggedIn(userNameEnc);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();	
		userProfile.verifyWelcomeEmail(userNameEnc, userNameEmail, passwordEmail);
		PreferencesPageObject preferences = new PreferencesPageObject(driver);
		preferences.openSpecialPreferencesPage();
		preferences.selectTab(tabNames.Email);
		preferences.verifyEmailMeSection();
	}
	

	/*
	 * 3.32 Test Case 2.3.03 Sign up page: Account creation Fifty character Username
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_Sign_up  
	 * */
	@Test(groups = {"SignUp_account_creation_TC_002", "SignUp"})
	public void SignUp_account_creation_TC_002_fifty_character_user_name()
	{
		userNameEmail = Properties.emailQaart2;
		passwordEmail = Properties.emailPasswordQaart2;
		SignUpPageObject signUp = new SignUpPageObject(driver);
		timeStamp = signUp.getTimeStamp(); 
		userName = "Qweasdzxcvqweasdzxcvqweasdzxcvqweasdz"+timeStamp;
		password = "QAPassword"+timeStamp;
		signUp.openSignUpPage();
		signUp.typeInEmail(userNameEmail);
		signUp.typeInUserName(userName);
		signUp.typeInPassword(password);
		signUp.enterBirthDate(
			PageContent.wikiSignUpBirthMonth, 
			PageContent.wikiSignUpBirthDay, 
			PageContent.wikiSignUpBirthYear
		);
		signUp.enterBlurryWord();
		AlmostTherePageObject almostTherePage = signUp.submit(userNameEmail, passwordEmail);
		almostTherePage.verifyAlmostTherePage();
		ConfirmationPageObject confirmPageAlmostThere = almostTherePage.enterActivationLink(userNameEmail, passwordEmail);
		confirmPageAlmostThere.typeInUserName(userName);
		confirmPageAlmostThere.typeInPassword(password);
		UserProfilePageObject userProfile = confirmPageAlmostThere.clickSubmitButton(userNameEmail, passwordEmail);
		userProfile.verifyUserLoggedIn(userName);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		userProfile.verifyWelcomeEmail(userName, userNameEmail, passwordEmail);
		PreferencesPageObject preferences = new PreferencesPageObject(driver);
		preferences.openSpecialPreferencesPage();
		preferences.selectTab(tabNames.Email);
		preferences.verifyEmailMeSection();
	}
	

	/*
	 * 3.33 Test Case 2.3.04 Sign up page: Account creation Username contains a backward slash
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_Sign_up  
	 * */
	@Test(groups = {"SignUp_account_creation_TC_003", "SignUp"})
	public void SignUp_account_creation_TC_003_backward_slash_user_name()
	{
		userNameEmail = Properties.emailQaart3;
		passwordEmail = Properties.emailPasswordQaart3;
		SignUpPageObject signUp = new SignUpPageObject(driver);
		timeStamp = signUp.getTimeStamp(); 
		userName = Properties.userNameWithBackwardSlash+timeStamp;
		userNameEnc = Properties.userNameWithBackwardSlashEncoded+timeStamp;
		password = "QAPassword"+timeStamp;
		signUp.openSignUpPage();
		signUp.typeInEmail(userNameEmail);
		signUp.typeInUserName(userName);
		signUp.typeInPassword(password);
		signUp.enterBirthDate(
			PageContent.wikiSignUpBirthMonth, 
			PageContent.wikiSignUpBirthDay, 
			PageContent.wikiSignUpBirthYear
		);
		signUp.enterBlurryWord();
		AlmostTherePageObject almostTherePage = signUp.submit(userNameEmail, passwordEmail);
		almostTherePage.verifyAlmostTherePage();
		ConfirmationPageObject confirmPageAlmostThere = almostTherePage.enterActivationLink(userNameEmail, passwordEmail);
		confirmPageAlmostThere.typeInUserName(userName);
		confirmPageAlmostThere.typeInPassword(password);
		UserProfilePageObject userProfile = confirmPageAlmostThere.clickSubmitButton(userNameEmail, passwordEmail);
		userProfile.verifyUserLoggedIn(userNameEnc);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		userProfile.verifyWelcomeEmail(userNameEnc, userNameEmail, passwordEmail);
		PreferencesPageObject preferences = new PreferencesPageObject(driver);
		preferences.openSpecialPreferencesPage();
		preferences.selectTab(tabNames.Email);
		preferences.verifyEmailMeSection();
	}
	
	/*
	 * 3.34 Test Case 2.3.05 Sign up page: Account creation Username contains an underscore
	 * 3.36 Test Case 2.3.07 Sign up page: Account creation Password is 1 character
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_Sign_up  
	 * */
	@Test(groups = {"SignUp_account_creation_TC_004", "SignUp"})
	public void SignUp_account_creation_TC_004_one_char_password()
	{
		userNameEmail = Properties.emailQaart4;
		passwordEmail = Properties.emailPasswordQaart4;
		SignUpPageObject signUp = new SignUpPageObject(driver);
		timeStamp = signUp.getTimeStamp(); 
		userName = Properties.userNameWithUnderScore+timeStamp;
		password = RandomStringUtils.randomAscii(1);
		signUp.openSignUpPage();
		signUp.typeInEmail(userNameEmail);
		signUp.typeInUserName(userName);
		signUp.typeInPassword(password);
		signUp.enterBirthDate(
			PageContent.wikiSignUpBirthMonth, 
			PageContent.wikiSignUpBirthDay, 
			PageContent.wikiSignUpBirthYear
		);
		signUp.enterBlurryWord();
		AlmostTherePageObject almostTherePage = signUp.submit(userNameEmail, passwordEmail);
		almostTherePage.verifyAlmostTherePage();
		ConfirmationPageObject confirmPageAlmostThere = almostTherePage.enterActivationLink(userNameEmail, passwordEmail);
		confirmPageAlmostThere.typeInUserName(userName);
		confirmPageAlmostThere.typeInPassword(password);
		UserProfilePageObject userProfile = confirmPageAlmostThere.clickSubmitButton(userNameEmail, passwordEmail);
		userProfile.verifyUserLoggedIn(userName);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		userProfile.verifyWelcomeEmail(userName, userNameEmail, passwordEmail);
		PreferencesPageObject preferences = new PreferencesPageObject(driver);
		preferences.openSpecialPreferencesPage();
		preferences.selectTab(tabNames.Email);
		preferences.verifyEmailMeSection();
	}
	

	/*
	 * 3.37 Test Case 2.3.08 Sign up page: Account creation Password is 50 characters
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_Sign_up  
	 * */
	@Test(groups = {"SignUp_account_creation__005", "SignUp", "Smoke"})
	public void SignUp_account_creation_TC_005_fifty_character_password()
	{
		userNameEmail = Properties.emailQaart1;
		passwordEmail = Properties.emailPasswordQaart1;
		SignUpPageObject signUp = new SignUpPageObject(driver);
		timeStamp = signUp.getTimeStamp(); 
		userName = Properties.userName+timeStamp;
		password = RandomStringUtils.randomAscii(50);
		signUp.openSignUpPage();
		signUp.typeInEmail(userNameEmail);
		signUp.typeInUserName(userName);
		signUp.typeInPassword(password);
		signUp.enterBirthDate(
			PageContent.wikiSignUpBirthMonth, 
			PageContent.wikiSignUpBirthDay, 
			PageContent.wikiSignUpBirthYear
		);
		signUp.enterBlurryWord();
		AlmostTherePageObject almostTherePage = signUp.submit(userNameEmail, passwordEmail);
		almostTherePage.verifyAlmostTherePage();
		ConfirmationPageObject confirmPageAlmostThere = almostTherePage.enterActivationLink(userNameEmail, passwordEmail);
		confirmPageAlmostThere.typeInUserName(userName);
		confirmPageAlmostThere.typeInPassword(password);
		UserProfilePageObject userProfile = confirmPageAlmostThere.clickSubmitButton(userNameEmail, passwordEmail);
		userProfile.verifyUserLoggedIn(userName);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();	
		userProfile.verifyWelcomeEmail(userName, userNameEmail, passwordEmail);
		PreferencesPageObject preferences = new PreferencesPageObject(driver);
		preferences.openSpecialPreferencesPage();
		preferences.selectTab(tabNames.Email);
		preferences.verifyEmailMeSection();
	}
	
	/*
	 * 3.38 Test Case 2.3.09 Sign up page: Account creation Birthdate 29-Feb and leap year
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_Sign_up  
	 * */
	@Test(groups = {"SignUp_account_creation_TC_006", "SignUp"})
	public void SignUp_account_creation_TC_006_lap_year()
	{
		userNameEmail = Properties.emailQaart2;
		passwordEmail = Properties.emailPasswordQaart2;
		SignUpPageObject signUp = new SignUpPageObject(driver);
		timeStamp = signUp.getTimeStamp(); 
		userName = Properties.userName+timeStamp;
		password = Properties.password+timeStamp;
		signUp.openSignUpPage();
		signUp.typeInEmail(userNameEmail);
		signUp.typeInUserName(userName);
		signUp.typeInPassword(password);
		signUp.enterBirthDate("2", "29", "1988");
		signUp.enterBlurryWord();
		AlmostTherePageObject almostTherePage = signUp.submit(userNameEmail, passwordEmail);
		almostTherePage.verifyAlmostTherePage();
		ConfirmationPageObject confirmPageAlmostThere = almostTherePage.enterActivationLink(userNameEmail, passwordEmail);
		confirmPageAlmostThere.typeInUserName(userName);
		confirmPageAlmostThere.typeInPassword(password);
		UserProfilePageObject userProfile = confirmPageAlmostThere.clickSubmitButton(userNameEmail, passwordEmail);
		userProfile.verifyUserLoggedIn(userName);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		userProfile.verifyWelcomeEmail(userName, userNameEmail, passwordEmail);
		PreferencesPageObject preferences = new PreferencesPageObject(driver);
		preferences.openSpecialPreferencesPage();
		preferences.selectTab(tabNames.Email);
		preferences.verifyEmailMeSection();
	}
}
