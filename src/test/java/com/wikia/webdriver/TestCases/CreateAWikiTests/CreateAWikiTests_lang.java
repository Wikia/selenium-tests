package com.wikia.webdriver.TestCases.CreateAWikiTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SpecialFactoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiLogInPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.NewWikiaHomePage;

public class CreateAWikiTests_lang extends TestTemplate{

	private String wikiName;
	
	@DataProvider
	private static final Object[][] getArticleName()
	{
		return new Object[][]
				{
					{"de"},
					{"es"},
					{"fr"},
					{"it"},
					{"ja"},
					{"nl"},
					{"no"},
					{"pl"},
					{"pt"},
					{"pt-br"},
					{"ru"},
					{"zh"},
				};
	}	
	
	
	
	@Test(dataProvider="getArticleName", groups = {"CreateNewWiki_lang_001","CNW_lang"})
	public void CreateNewWiki_lang_TC001(String lang)
	{
		CommonFunctions.logOut(driver);
		HomePageObject home = new HomePageObject(driver);
		home.openHomePage();
		CreateNewWikiPageObjectStep1 createNewWiki1 = home.startAWiki();
		String timeStamp = createNewWiki1.getTimeStamp();
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
		createNewWiki3.selectTheme(3);
		NewWikiaHomePage newWikia = createNewWiki3.submit(wikiName);
		newWikia.VerifyCongratulationsLightBox();
		newWikia.closeCongratulationsLightBox();
		newWikia.verifyUserLoggedIn(Properties.userName);
		CommonFunctions.logOut(driver);
	}	

}
