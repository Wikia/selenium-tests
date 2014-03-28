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
 *
 */

public class RTEDisabledEditorEntryCKPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
//	String wikiURL;

	@BeforeMethod(groups = {"RTEDisabledEditorEntryCKPreferred"})
	public void setup_CKPreferred() {
//		wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
	}

	@Test(
		groups = {"RTEDisabledEditorEntryCKPreferred", "RTEDisabledEditorEntryCKPreferredTests_001"}
	)
	public void RTEDisabledEditorEntryCKPreferredTests_001_CreatePageEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntryCKPreferred", "RTEDisabledEditorEntryCKPreferredTests_002"}
	)
	public void RTEDisabledEditorEntryCKPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
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
		ve.logOut(wikiURL);
	}
}
