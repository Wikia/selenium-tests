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
 *         wgEnabledRTEExt = true, wgVisualEditorUI = false, wgForceVisualEditor = false
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

public class VEDisabledEditorEntryAnonTests extends NewTestTemplateBeforeClass {

	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_DISABLED_WIKI);
		base = new WikiBasePageObject(driver);
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_001", "createPageEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_001_CreatePageEntry() {
		String articleName = base.getNameForArticle();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = article.createArticleInCKUsingDropdown(articleName);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_002", "articleEditEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, base.getNameForArticle());
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_003", "redlinkEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_004", "sectionEditEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyEditorSurfacePresent();
		ve.verifyVEToolBarPresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_005", "veactionURLEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_006", "listEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.LIST_PAGE);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_007", "categoryEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.CATEGORY_PAGE);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_008", "templateEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TEMPLATE_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntryAnonTests", "VEDisabledEditorEntryAnonTestsTests_009", "actionURLEntry"}
	)
	public void VEDisabledEditorEntryAnonTestsTests_009_actionEdit() {
		VisualEditModePageObject ck =
			base.navigateToArticleEditPageCK(wikiURL, base.getNameForArticle());
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}
}
