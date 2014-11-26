package com.wikia.webdriver.TestCases.CreateAWikiTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CreateWikiMessages;
import com.wikia.webdriver.Common.DataProvider.CreateNewWikiDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep2;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep3;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 * 1. Create wiki in different languages
 */

public class CreateWikiTests_lang extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(
		dataProviderClass=CreateNewWikiDataProvider.class,
		dataProvider="getLangs",
		groups = {"CreateNewWiki_lang_001","CNW_lang"}
	)
	public void CreateNewWiki_lang_TC001(String lang) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		CreateNewWikiPageObjectStep1 cnw1 = base.openSpecialCreateNewWikiPage(wikiCorporateURL);
		cnw1.selectLanguage(lang);
		String wikiName = cnw1.getWikiName();
		cnw1.typeInWikiName(wikiName);
		cnw1.verifySuccessIcon();
		CreateNewWikiPageObjectStep2 cnw2 = cnw1.submit();
		cnw2.selectCategory(CreateWikiMessages.WIKI_CATEGORY);
		CreateNewWikiPageObjectStep3 cnw3 = cnw2.submit();
		cnw3.selectThemeByName(CreateWikiMessages.WIKI_THEME);
		ArticlePageObject article = cnw3.submit();
		article.verifyWikiTitleOnCongratualtionsLightBox(wikiName);
		article.closeNewWikiCongratulationsLightBox();
		article.verifyWikiTitleHeader(wikiName);
		article.verifyUserLoggedIn(credentials.userName);
	}
}
