package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * VE-881 Verify Visual Editor is loaded for signed in user by clicking on Edit on article on VE enabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit in the URL on VE enabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit in the URL on VE disabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit via redlink on VE enabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit via redlink on VE disabled wiki
 * VE-884 Verify Visual Editor is loaded for signed in user by clicking on Edit on section on VE enabled wiki
 */

public class VisualEditorEntryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(
			groups = {"VisualEditorEntry", "VisualEditorEntryTest_001"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getVEWikis"
	)
	public void VisualEditorEntryTest_001_editLoggedIn_veEnabled(String wikiName) {
		String wikiURL = urlBuilder.getUrlForWiki(wikiName);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VisualEditorEntry", "VisualEditorEntryTest_002"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getVEWikis"
	)
	public void VisualEditorEntryTest_002_urlLoggedIn_veEnabled(String wikiName) {
		String wikiURL = urlBuilder.getUrlForWiki(wikiName);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VisualEditorEntry", "VisualEditorEntryTest_003"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getNonVEWikis"
	)
	public void VisualEditorEntryTest_003_urlLoggedIn_veDisabled(String wikiName) {
		String wikiURL = urlBuilder.getUrlForWiki(wikiName);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VisualEditorEntry", "VisualEditorEntryTest_004"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getVEWikis"
	)
	public void VisualEditorEntryTest_004_urlLoggedIn_veEnabled_redLink(String wikiName) {
		String wikiURL = urlBuilder.getUrlForWiki(wikiName);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject ve =
			base.openNewArticleEditModeVisualWithRedlink(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VisualEditorEntry", "VisualEditorEntryTest_005"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getNonVEWikis"
	)
	public void VisualEditorEntryTest_005_urlLoggedIn_veDisabled_redLink(String wikiName) {
		String wikiURL = urlBuilder.getUrlForWiki(wikiName);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject ve =
			base.openNewArticleEditModeVisualWithRedlink(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VisualEditorEntry", "VisualEditorEntryTest_006"},
			dataProviderClass = VisualEditorDataProvider.class,
			dataProvider = "getVEWikis"
	)
	public void VisualEditorEntryTest_006_editSectionLoggedIn_veEnabled(String wikiName) {
		String wikiURL = urlBuilder.getUrlForWiki(wikiName);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.VE_ENABLED_WIKI);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
