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
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = false
 * User Editor Preference is set to Default Editor
 * VE-958 verify CK Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify CK Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify CK Editor is loaded when clicking the red link in the article
 * VE-958 verify CK Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 * VE-898 verify CK Editor is loaded on List namespace
 * VE-898 verify Src Editor is loaded on Category namespace
 * VE-898 verify Src Editor is loaded on Template namespace
 * VE-898 verify CK Editor is loaded when using ?action=edit in the URL
 */

public class VEDisabledEditorEntryDefaultPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_defaultPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veDisabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_001", "createPageEntry"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_001_CreatePageEntry() {
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = article.createArticleInCKUsingDropdown(articleName);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_002", "articleEditEntry"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditModePageObject ck = article.editArticleInRTEUsingDropdown();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_003", "redlinkEntry"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithRedLinks(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_004", "sectionEditEntry"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithSectionEditButton(0);
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_005", "veactionURLEntry"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_006"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.listPage);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_007"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.categoryPage);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_008"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.templatePage);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEDisabledEditorEntryDefaultPreferred", "VEDisabledEditorEntryDefaultPreferredTests_009", "actionURLEntry"}
	)
	public void VEDisabledEditorEntryDefaultPreferredTests_009_actionEdit() {
		VisualEditModePageObject ck =
			base.navigateToArticleEditPageCK(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}
}
