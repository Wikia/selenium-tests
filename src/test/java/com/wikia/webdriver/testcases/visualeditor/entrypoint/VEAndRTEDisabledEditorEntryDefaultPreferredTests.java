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
 *         <p/>
 *         Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = false
 *         User Editor Preference is set to Default Editor
 *         VE-958 verify Source Editor is loaded when clicking Add Page from the contribution drop down
 *         VE-958 verify Source Editor is loaded when clicking the main edit button on the top of the article
 *         VE-958 verify Source Editor is loaded when clicking the red link in the article
 *         VE-958 verify Source Editor is loaded when clicking the section edit link in the article
 *         VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 *         VE-898 verify Src Editor is loaded on List namespace
 *         VE-898 verify Src Editor is loaded on Category namespace
 *         VE-898 verify Src Editor is loaded on Template namespace
 *         VE-898 verify Src Editor is loaded when using ?action=edit in the URL
 */

public class VEAndRTEDisabledEditorEntryDefaultPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_defaultPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_AND_RTE_DISABLED_WIKI);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_001", "createPageEntry"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_001_CreatePageEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_002", "articleEditEntry"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_003", "redlinkEntry"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_004", "sectionEditEntry"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_005", "veactionURLEntry"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_006"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.LIST_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_007"}
	)
	public void RTEDisabledEditorEntryDefaultPreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.CATEGORY_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_008"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TEMPLATE_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEAndRTEDisabledEditorEntryDefaultPreferred", "VEAndRTEDisabledEditorEntryDefaultPreferredTests_009", "actionURLEntry"}
	)
	public void VEAndRTEDisabledEditorEntryDefaultPreferredTests_009_actionEdit() {
		SourceEditModePageObject src =
			base.navigateToArticleEditPageSrc(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		src.verifySourceOnlyMode();
	}
}
