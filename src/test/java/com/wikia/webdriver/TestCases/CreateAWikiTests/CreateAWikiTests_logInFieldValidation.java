package com.wikia.webdriver.TestCases.CreateAWikiTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.LoginDataProvider;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars.CustomizedToolbarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiLogInPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.NewWikiaHomePage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import org.testng.annotations.Test;

public class CreateAWikiTests_logInFieldValidation extends TestTemplate{

	private String wikiName;

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
		String timeStamp = createNewWiki1.getTimeStamp().substring(createNewWiki1.getTimeStamp().length()-3);
		wikiName = "QaTest"+timeStamp;
		createNewWiki1.typeInWikiName(wikiName);
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(userName);
		logInPage.typeInPassword(password);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory("Auto");
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectThemeByName("carbon");
		NewWikiaHomePage newWikia = createNewWiki3.submit(wikiName);
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(userNameEnc);
		CustomizedToolbarComponentObject toolbar = new CustomizedToolbarComponentObject(driver);
		toolbar.verifyUserToolBar();
	}
}
