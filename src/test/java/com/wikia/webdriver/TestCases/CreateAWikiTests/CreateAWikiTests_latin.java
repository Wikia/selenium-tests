package com.wikia.webdriver.TestCases.CreateAWikiTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiLogInPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.NewWikiaHomePage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialFactoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class CreateAWikiTests_latin extends TestTemplate {

	@Test(groups = { "CreateNewWiki_latin_002", "CNW" })
	public void CreateNewWiki_latin_TC002_user_name_is_blank() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.submitLogin();
		logInPage.verifyEmptyUserNameValidation();
	}

	@Test(groups = { "CreateNewWiki_latin_003", "CNW" })
	public void CreateNewWiki_latin_TC003_user_name_does_not_exists() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName("invalidUserName");
		logInPage.submitLogin();
		logInPage.verifyInvalidUserNameValidation();
	}

	@Test(groups = { "CreateNewWiki_latin_004", "CNW" })
	public void CreateNewWiki_latin_TC004_password_is_blank() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userName);
		logInPage.submitLogin();
		logInPage.verifyBlankPasswordValidation();
	}

	@Test(groups = { "CreateNewWiki_latin_005", "CNW" })
	public void CreateNewWiki_latin_TC005_password_is_incorrect() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userName);
		logInPage.typeInPassword("Invalid password");
		logInPage.submitLogin();
		logInPage.verifyInvalidPasswordValidation();
	}

	@Test(groups = { "CreateNewWiki_latin_006", "CNW", "Smoke" })
	public void CreateNewWiki_latin_TC006_user_name_and_password_are_correct() {
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userName);
		logInPage.typeInPassword(Properties.password);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory(PageContent.wikiCategory);
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectThemeByName("carbon");
		NewWikiaHomePage newWikia = createNewWiki3.submit();
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(Properties.userName);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(
				driver);
		toolbar.verifyUserToolBar();
	}

	//https://wikia.fogbugz.com/default.asp?99340
	@Test(groups = { "CreateNewWiki_latin_007", "CNW", "Smoke" })
	public void CreateNewWiki_latin_TC007_deleteWiki(){
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userName);
		logInPage.typeInPassword(Properties.password);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory(PageContent.wikiCategory);
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectThemeByName("carbon");
		NewWikiaHomePage newWikia = createNewWiki3.submit();
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(Properties.userName);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(
				driver);
		toolbar.verifyUserToolBar();
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(
				driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialFactoryPageObject factory = new SpecialFactoryPageObject(driver);
		factory.openWikiFactoryPage();
		factory.deleteWiki(createNewWiki1.getWikiName());
	}
}
