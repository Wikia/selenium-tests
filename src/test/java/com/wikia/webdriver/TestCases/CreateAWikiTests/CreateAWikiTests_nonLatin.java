package com.wikia.webdriver.TestCases.CreateAWikiTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiLogInPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.NewWikiaHomePage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class CreateAWikiTests_nonLatin extends TestTemplate
{
	/*
	 * Test Case 3.1.03 Create new wiki: log in field validation (non-Latin characters)
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_CNW#Test_Case_3.1.02_Create_new_wiki:_log_in_field_validation_.28Latin_characters.29
	 * Username field validation: username does not exist
	 * */
	@Test(groups = {"CreateNewWiki_nonLatin_TC001","CNW"})
	public void CreateNewWiki_nonLatin_TC001_user_name_does_not_exists()
	{
		SpecialUserLoginPageObject login =  new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName("查爾斯和");
		logInPage.submitLogin();
		logInPage.verifyInvalidUserNameValidation();
		logInPage.typeInUserName(Properties.userNameNonLatin);
		logInPage.typeInPassword(Properties.passwordNonLatin);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory(PageContent.wikiCategory);
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectTheme(3);
		NewWikiaHomePage newWikia = createNewWiki3.submit();
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(Properties.userNameNonLatinEncoded);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		login.logOut(driver);
	}

	/*
	 * Test Case 3.1.02 Create new wiki: log in field validation (Latin characters)
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_CNW#Test_Case_3.1.02_Create_new_wiki:_log_in_field_validation_.28Latin_characters.29
	 * Password field Validation: password is blank
	 * */
	@Test(groups = {"CreateNewWiki_nonLatin_TC002","CNW"})
	public void CreateNewWiki_nonLatin_TC002_password_is_blank()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userNameNonLatin);
		logInPage.submitLogin();
		logInPage.verifyBlankPasswordValidation();
		logInPage.typeInUserName(Properties.userNameNonLatin);
		logInPage.typeInPassword(Properties.passwordNonLatin);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory(PageContent.wikiCategory);
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectTheme(3);
		NewWikiaHomePage newWikia = createNewWiki3.submit();
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(Properties.userNameNonLatinEncoded);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		login.logOut(driver);
	}

	/*
	 * Test Case 3.1.02 Create new wiki: log in field validation (Latin characters)
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_CNW#Test_Case_3.1.02_Create_new_wiki:_log_in_field_validation_.28Latin_characters.29
	 * Password field Validation: password is incorrect
	 * */
	@Test(groups = {"CreateNewWiki_nonLatin_TC003","CNW"})
	public void CreateNewWiki_nonLatin_TC003_password_is_incorrect()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userNameNonLatin);
		logInPage.typeInPassword("Invalid password");
		logInPage.submitLogin();
		logInPage.verifyInvalidPasswordValidation();
		logInPage.typeInUserName(Properties.userNameNonLatin);
		logInPage.typeInPassword(Properties.passwordNonLatin);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory(PageContent.wikiCategory);
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectTheme(3);
		NewWikiaHomePage newWikia = createNewWiki3.submit();
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(Properties.userNameNonLatinEncoded);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		login.logOut(driver);
	}

	/*
	 * Test Case 3.1.02 Create new wiki: log in field validation (Latin characters)
	 * https://internal.wikia-inc.com/wiki/Global_Log_in_and_Sign_up/Test_Cases:_CNW#Test_Case_3.1.02_Create_new_wiki:_log_in_field_validation_.28Latin_characters.29
	 * Password field Validation: username and password are correct
	 * */
	@Test(groups = {"CreateNewWiki_nonLatin_TC004","CNW", "Smoke"})
	public void CreateNewWiki_nonLatin_TC004_user_name_and_password_are_correct()
	{
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userNameNonLatin);
		logInPage.typeInPassword(Properties.passwordNonLatin);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory(PageContent.wikiCategory);
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectTheme(3);
		NewWikiaHomePage newWikia = createNewWiki3.submit();
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
		login.logOut(driver);
	}
}
