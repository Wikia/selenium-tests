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
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = true
 * User Editor Preference is set to RTE Editor
 * VE-958 verify Src Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify Src Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify Src Editor is loaded when clicking the red link in the article
 * VE-958 verify Src Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 */

public class RTEDisabledEditorEntryCKPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
//	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_CKPreferred() {
//		wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
	}

	@Test(
		groups = {"RTEDisabledEditorEntryCKPreferred", "RTEDisabledEditorEntryCKPreferredTests_001"}
	)
	public void RTEDisabledEditorEntryCKPreferredTests_001_CreatePageEntry() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
				base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject src = article.createArticleInSrcUsingDropdown(articleName);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryCKPreferred", "RTEDisabledEditorEntryCKPreferredTests_002"}
	)
	public void RTEDisabledEditorEntryCKPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryCKPreferred", "RTEDisabledEditorEntryCKPreferredTests_003"}
	)
	public void RTEDisabledEditorEntryCKPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryCKPreferred", "RTEDisabledEditorEntryCKPreferredTests_004"}
	)
	public void RTEDisabledEditorEntryCKPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryCKPreferred", "RTEDisabledEditorEntryCKPreferredTests_005"}
	)
	public void RTEDisabledEditorEntryCKPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}
}
