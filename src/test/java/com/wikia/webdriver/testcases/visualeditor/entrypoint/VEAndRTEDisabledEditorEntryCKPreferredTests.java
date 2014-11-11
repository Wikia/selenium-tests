package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = false
 * User Editor Preference is set to RTE Editor
 * VE-958 verify Src Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify Src Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify Src Editor is loaded when clicking the red link in the article
 * VE-958 verify Src Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 * VE-898 verify Src Editor is loaded on List namespace
 * VE-898 verify Src Editor is loaded on Category namespace
 * VE-898 verify Src Editor is loaded on Template namespace
 * VE-898 verify Src Editor is loaded when using ?action=edit in the URL
 */

public class VEAndRTEDisabledEditorEntryCKPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_CKPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veAndrteDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_001", "createPageEntry"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_001_CreatePageEntry() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
				base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject src = article.createArticleInSrcUsingDropdown(articleName);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_002", "articleEditEntry"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_003", "redlinkEntry"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_004", "sectionEditEntry"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_005", "veactionURLEntry"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_006"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.listPage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_007"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.categoryPage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_008"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.templatePage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryCKPreferred", "VEAndRTEDisabledEditorEntryCKPreferredTests_009", "actionURLEntry"}
	)
	public void VEAndRTEDisabledEditorEntryCKPreferredTests_009_actionEdit() {
		SourceEditModePageObject src =
			base.navigateToArticleEditPageSrc(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		src.verifySourceOnlyMode();
	}
}
