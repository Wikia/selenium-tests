package com.wikia.webdriver.TestCases.VisualEditor.EntryPoint;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = false
 * User Editor Preference is set to VE Editor
 * VE-958 verify VE Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify VE Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify VE Editor is loaded when clicking the red link in the article
 * VE-958 verify VE Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 */

public class VEAndRTEDisabledEditorEntryVEPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
//	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
//		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veAndrteDisabledTestMainPage);
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
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_005"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_006"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.listPage);
		VisualEditorPageObject ve = article.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();;
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_007"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.categoryPage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_008"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.templatePage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_009"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_009_MediawikiNamespace() {
		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.mediaWiki);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryVEPreferred", "VEAndRTEDisabledEditorEntryVEPreferredTests_010"}
	)
	public void VEAndRTEDisabledEditorEntryVEPreferredTests_010_actionEdit() {
		SourceEditModePageObject src =
			base.navigateToArticleEditPageSrc(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		src.verifySourceOnlyMode();
	}
}
