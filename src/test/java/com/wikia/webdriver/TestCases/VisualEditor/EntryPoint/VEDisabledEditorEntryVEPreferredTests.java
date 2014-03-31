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
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = true, wgVisualEditorUI = false
 * User Editor Preference is set to VE Editor
 * VE-958 verify VE Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify VE Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify VE Editor is loaded when clicking the red link in the article
 * VE-958 verify VE Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 */

public class VEDisabledEditorEntryVEPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
//	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
//		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEDisabledEditorEntryVEPreferred", "VEDisabledEditorEntryVEPreferredTests_001"}
	)
	public void VEDisabledEditorEntryVEPreferredTests_001_CreatePageEntry() {
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
		groups = {"VEDisabledEditorEntryVEPreferred", "VEDisabledEditorEntryVEPreferredTests_002"}
	)
	public void VEDisabledEditorEntryVEPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
		groups = {"VEDisabledEditorEntryVEPreferred", "VEDisabledEditorEntryVEPreferredTests_003"}
	)
	public void VEDisabledEditorEntryVEPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
		groups = {"VEDisabledEditorEntryVEPreferred", "VEDisabledEditorEntryVEPreferredTests_004"}
	)
	public void VEDisabledEditorEntryVEPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
		groups = {"VEDisabledEditorEntryVEPreferred", "VEDisabledEditorEntryVEPreferredTests_005"}
	)
	public void VEDisabledEditorEntryVEPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
