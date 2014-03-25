package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = false
 *
 */

public class VEAndRTEDisabledEditorEntryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_001"}
	)
	public void VisualEditorEntryTest_001_editLoggedIn_veEnabled() {
		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veAndrteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
