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
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = true
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

public class RTEDisabledEditorEntrySourcePreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_sourcePreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_001", "createPageEntry"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_001_CreatePageEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_002", "articleEditEntry"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_003", "redlinkEntry"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_004", "sectionEditEntry"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_005", "veactionURLEntry"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_006"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.listPage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_007"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.categoryPage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_008"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.templatePage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"RTEDisabledEditorEntrySourcePreferred", "RTEDisabledEditorEntrySourcePreferredTests_009", "actionURLEntry"}
	)
	public void RTEDisabledEditorEntrySourcePreferredTests_009_actionEdit() {
		SourceEditModePageObject src =
			base.navigateToArticleEditPageSrc(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		src.verifySourceOnlyMode();
	}
}
