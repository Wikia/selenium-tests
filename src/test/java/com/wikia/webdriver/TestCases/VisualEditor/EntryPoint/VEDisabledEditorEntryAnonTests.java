package com.wikia.webdriver.TestCases.VisualEditor.EntryPoint;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has
 * wgEnabledRTEExt = true, wgVisualEditorUI = false, wgForceVisualEditor = false
 * User Editor Preference is set to Default for Anon
 * VE-983 verify VE Editor is loaded when clicking Add Page from the contribution drop down
 * VE-983 verify VE Editor is loaded when clicking the main edit button on the top of the article
 * VE-983 verify VE Editor is loaded when clicking the red link in the article
 * VE-983 verify VE Editor is loaded when clicking the section edit link in the article
 * VE-983 verify VE Editor is loaded when using ?veaction=edit in the URL
 * VE-983 verify VE Editor is loaded on List namespace
 * VE-983 verify CK Editor is loaded on Category namespace
 * VE-983 verify Src Editor is loaded on Template namespace
 * VE-983 verify CK Editor is loaded when using ?action=edit in the URL
 */

public class VEDisabledEditorEntryAnonTests extends NewTestTemplateBeforeClass {

	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_001"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_001_CreatePageEntry() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = article.createArticleInCKUsingDropdown(articleName);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_002"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditModePageObject ck = article.editArticleInRTEUsingDropdown();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_003"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_003_RedlinkEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithRedLinks(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_004"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_004_SectionEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithSectionEditButton(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_005"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_006"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.listPage);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_007"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.categoryPage);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_008"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.templatePage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_009"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_009_actionEdit() {
		VisualEditModePageObject ck =
			base.navigateToArticleEditPageCK(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}
}
