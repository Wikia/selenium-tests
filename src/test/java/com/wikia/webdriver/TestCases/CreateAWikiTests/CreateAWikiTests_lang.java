package com.wikia.webdriver.TestCases.CreateAWikiTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.CreateNewWikiDataProvider;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiLogInPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.NewWikiaHomePage;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import org.testng.annotations.Test;

public class CreateAWikiTests_lang extends TestTemplate {

	private String wikiName;

	@Test(
		dataProviderClass=CreateNewWikiDataProvider.class,
		dataProvider="getLangs",
		groups = {"CreateNewWiki_lang_001","CNW_lang"}
	)
	public void CreateNewWiki_lang_TC001(String lang)
	{
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		String timeStamp = createNewWiki1.getTimeStamp().substring(createNewWiki1.getTimeStamp().length()-3);
		wikiName = PageContent.wikiNamePrefix+timeStamp;
		createNewWiki1.selectLanguage(lang);
		createNewWiki1.typeInWikiName(wikiName);
		createNewWiki1.waitForSuccessIcon();
		CreateNewWikiLogInPageObject logInPage = createNewWiki1.submitToLogIn();
		logInPage.typeInUserName(Properties.userName);
		logInPage.typeInPassword(Properties.password);
		CreateNewWikiPageObjectStep2 createNewWiki2 = logInPage.submitLogin();
		createNewWiki2.describeYourTopic(PageContent.wikiTopic);
		createNewWiki2.selectCategory(PageContent.wikiCategory);
		CreateNewWikiPageObjectStep3 createNewWiki3 = createNewWiki2.submit();
		createNewWiki3.selectThemeByName("carbon");
		NewWikiaHomePage newWikia = createNewWiki3.submit(wikiName);
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(Properties.userName);
	}
}
