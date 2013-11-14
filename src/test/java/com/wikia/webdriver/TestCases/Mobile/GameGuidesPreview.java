/**
 *
 */
package com.wikia.webdriver.TestCases.Mobile;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class GameGuidesPreview extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups={"sectionTest_001", "sectionsTests", "mobile"})
	public void Sections_001_chevronTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		article.appendToUrl(URLsContent.gameGuidesControllerQS);
		article.appendToUrl(URLsContent.renderFullQS);
		article.appendToUrl(URLsContent.pageName + "PMG");
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickSection(1);
		article.verifySectionInvisibility();
	}

	@Test(groups={"sectionTest_002", "sectionsTests", "mobile"})
	public void Sections_002_hideTest() {
		MobileArticlePageObject article = new MobileArticlePageObject(driver);
		article.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		driver.get(wikiURL + URLsContent.wikiaPhp);
		article.appendToUrl(URLsContent.gameGuidesControllerQS);
		article.appendToUrl(URLsContent.renderFullQS);
		article.appendToUrl(URLsContent.pageName + "PMG");
		article.clickSection(1);
		article.verifySectionVisibility();
		article.clickHideButton();
		article.verifySectionInvisibility();
	}
}
