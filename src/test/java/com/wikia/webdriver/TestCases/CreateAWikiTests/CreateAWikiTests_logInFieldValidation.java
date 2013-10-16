package com.wikia.webdriver.TestCases.CreateAWikiTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.LoginDataProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiLogInSignUpPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.NewWikiaHomePage;

public class CreateAWikiTests_logInFieldValidation extends TestTemplate{

	@Test(
		dataProviderClass=LoginDataProvider.class,
		dataProvider="getUserCredentialsForCNW",
		groups={"CreateNewWiki_LogInFieldValidation","CNW"}
	)
	public void CreateNewWiki_LogInFieldValidation(String userName, String userNameEnc, String password)
	{
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		createNewWiki1.typeInWikiName(createNewWiki1.getWikiName());
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInSignUpPageObject logInPage = createNewWiki1.submitToLogInSignUp();
		logInPage.typeInUserName(userName);
		logInPage.typeInPassword(password);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory("Auto");
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectThemeByName("carbon");
		NewWikiaHomePage newWikia = createNewWiki3.submit();
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(userNameEnc);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
	}
}
