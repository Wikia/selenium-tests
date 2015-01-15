package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'Rochan' Chan
 *         <p/>
 *         Editor Entry Point Test on wiki that has
 *         wgEnabledRTEExt = true, wgVisualEditorUI = true, wgForceVisualEditor = false
 *         User Editor Preference is set to Default for Anon
 *         VE-983 verify VE Editor is loaded when clicking Add Page from the contribution drop down
 *         VE-983 verify VE Editor is loaded when clicking the main edit button on the top of the article
 *         VE-983 verify VE Editor is loaded when clicking the red link in the article
 *         VE-983 verify VE Editor is loaded when clicking the section edit link in the article
 *         VE-983 verify VE Editor is loaded when using ?veaction=edit in the URL
 *         VE-983 verify VE Editor is loaded on List namespace
 *         VE-983 verify CK Editor is loaded on Category namespace
 *         VE-983 verify Src Editor is loaded on Template namespace
 *         VE-983 verify CK Editor is loaded when using ?action=edit in the URL
 */

public class VEEnabledEditorEntryAnonTests extends NewTestTemplateBeforeClass {

	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
		base = new WikiBasePageObject(driver);
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_001", "createPageEntry"}
	)
	public void VEEnabledEditorEntryAnonTests_001_CreatePageEntry() {
		String articleName = base.getNameForArticle();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_002", "articleEditEntry"}
	)
	public void VEEnabledEditorEntryAnonTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, base.getNameForArticle());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_003", "redlinkEntry"}
	)
	public void VEEnabledEditorEntryAnonTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_004", "sectionEditEntry"}
	)
	public void VEEnabledEditorEntryAnonTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_005", "veactionURLEntry"}
	)
	public void VEEnabledEditorEntryAnonTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_006"}
	)
	public void VEEnabledEditorEntryAnonTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.LIST_PAGE);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_007"}
	)
	public void VEEnabledEditorEntryAnonTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.CATEGORY_PAGE);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_008"}
	)
	public void VEEnabledEditorEntryAnonTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TEMPLATE_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEEnabledEditorEntryAnonTests", "VEEnabledEditorEntryAnonTests_009", "actionURLEntry"}
	)
	public void VEEnabledEditorEntryAnonTests_009_actionEdit() {
		VisualEditModePageObject ck =
			base.navigateToArticleEditPageCK(wikiURL, base.getNameForArticle());
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}
}
