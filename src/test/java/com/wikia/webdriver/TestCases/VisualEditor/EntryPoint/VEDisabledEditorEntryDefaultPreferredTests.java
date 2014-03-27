package com.wikia.webdriver.TestCases.VisualEditor.EntryPoint;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = true, wgVisualEditorUI = false
 *
 */

public class VEDisabledEditorEntryDefaultPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(groups = {"VEDisabledEditorEntryDefaultPreferred"})
	public void setup_defaultPreferred() {
//		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_001"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_001_CreatePageEntry() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = article.createArticleUsingDropdown(articleName);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_002"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditModePageObject ck = article.editArticleInRTEUsingDropdown();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_003"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithRedLinks(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_004"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithSectionEditButton(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_005"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
