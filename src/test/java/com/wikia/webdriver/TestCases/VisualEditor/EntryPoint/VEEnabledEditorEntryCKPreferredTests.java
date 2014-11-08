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
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = true, wgVisualEditorUI = true
 * User Editor Preference is set to RTE Editor
 * VE-958 verify CK Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify CK Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify CK Editor is loaded when clicking the red link in the article
 * VE-958 verify CK Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 * VE-898 verify CK Editor is loaded on List namespace
 * VE-898 verify CK Editor is loaded on Category namespace
 * VE-898 verify Src Editor is loaded on Template namespace
 * VE-898 verify CK Editor is loaded when using ?action=edit in the URL
 */

public class VEEnabledEditorEntryCKPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_CKPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_001", "createPageEntry"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_001_CreatePageEntry() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = article.createArticleInCKUsingDropdown(articleName);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_002", "articleEditEntry"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditModePageObject ck = article.editArticleInRTEUsingDropdown();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_003", "redlinkEntry"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditModePageObject ck = article.openCKModeWithRedLinks(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_004", "sectionEditEntry"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditModePageObject ck = article.openCKModeWithSectionEditButton(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_005", "veactionURLEntry"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_006"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.LIST_PAGE);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_007"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.CATEGORY_PAGE);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_008"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TEMPLATE_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEEnabledEditorEntryCKPreferred", "VEEnabledEditorEntryCKPreferredTests_009", "actionURLEntry"}
	)
	public void VEEnabledEditorEntryCKPreferredTests_009_actionEdit() {
		VisualEditModePageObject ck =
			base.navigateToArticleEditPageCK(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}
}
