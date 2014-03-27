package com.wikia.webdriver.TestCases.VisualEditor.EntryPoint;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
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

public class VEAndRTEDisabledEditorEntryVEPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	@BeforeMethod(groups = {"VEAndRTEDisabledEditorEntryVEPreferred"})
	public void setup_VEPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veAndrteDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_001"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_001_CreatePageEntry() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(
			PageContent.articleNamePrefix + articleName
		);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_002"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_003"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_004"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_005"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
