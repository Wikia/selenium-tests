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
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = true, wgVisualEditorUI = false
 * User Editor Preference is set to Source Editor
 * VE-958 verify Source Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify Source Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify Source Editor is loaded when clicking the red link in the article
 * VE-958 verify Source Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 * VE-898 verify Src Editor is loaded on List namespace
 * VE-898 verify Src Editor is loaded on Category namespace
 * VE-898 verify Src Editor is loaded on Template namespace
 * VE-898 verify Src Editor is loaded when using ?action=edit in the URL
 */

public class VEDisabledEditorEntrySourcePreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_sourcePreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_DISABLED_WIKI);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_001", "createPageEntry"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_001_CreatePageEntry_sourcePreferred() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_002", "articleEditEntry"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_003", "redlinkEntry"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_004", "sectionEditEntry"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_005", "veactionURLEntry"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_006"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.LIST_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_007"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.CATEGORY_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_008"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TEMPLATE_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntrySourcePreferred", "VEDisabledEditorEntrySourcePreferredTests_009", "actionURLEntry"}
	)
	public void VEDisabledEditorEntrySourcePreferredTests_009_actionEdit() {
		SourceEditModePageObject src =
			base.navigateToArticleEditPageSrc(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		src.verifySourceOnlyMode();
	}
}
