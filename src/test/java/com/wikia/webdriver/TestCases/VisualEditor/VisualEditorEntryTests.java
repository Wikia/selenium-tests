package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 * https://wikia-inc.atlassian.net/browse/VE-881
 * Verify Visual Editor is loaded for signed in user by clicking on Edit on article on VE enabled wiki
 */

public class VisualEditorEntryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(
			groups = {"VisualEditorEntry", "VisualEditorEntryTest_001"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getVEWikis"
	)
	public void VisualEditorEntryTest_001_loggedIn(String wiki) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(
				urlBuilder.getUrlForWiki(wiki),
				base.getTimeStamp()
			);
		VisualEditorPageObject ve = article.clickVEEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}
}
