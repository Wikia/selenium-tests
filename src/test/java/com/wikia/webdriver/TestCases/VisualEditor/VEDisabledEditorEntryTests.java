package com.wikia.webdriver.TestCases.VisualEditor;

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
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = true, wgVisualEditorUI = false
 *
 */

public class VEDisabledEditorEntryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_VEPreferred", "VEDisabledEditorEntryTests_001"}
	)
	public void VEDisabledEditorEntryTests_001_CreatePageEntry_vePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(
			PageContent.articleNamePrefix + articleName
		);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_VEPreferred", "VEDisabledEditorEntryTests_002"}
	)
	public void VEDisabledEditorEntryTests_002_MainEditEntry_vePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_VEPreferred", "VEDisabledEditorEntryTests_003"}
	)
	public void VEDisabledEditorEntryTests_003_RedlinkEntry_vePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_VEPreferred", "VEDisabledEditorEntryTests_004"}
	)
	public void VEDisabledEditorEntryTests_004_SectionEditEntry_vePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_VEPreferred", "VEDisabledEditorEntryTests_005"}
	)
	public void VEDisabledEditorEntryTests_005_URLEntry_vePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_CKPreferred", "VEDisabledEditorEntryTests_006"}
	)
	public void VEDisabledEditorEntryTests_006_CreatePageEntry_ckPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = article.createArticleUsingDropdown(
			PageContent.articleNamePrefix + articleName
		);
		ck.verifyContentLoaded();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_CKPreferred", "VEDisabledEditorEntryTests_007"}
	)
	public void VEDisabledEditorEntryTests_007_MainEditEntry_ckPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditModePageObject ck = article.editArticleInRTEUsingDropdown();
		ck.verifyContentLoaded();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_CKPreferred", "VEDisabledEditorEntryTests_008"}
	)
	public void VEDisabledEditorEntryTests_008_RedlinkEntry_ckPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithRedLinks(0);
		ck.verifyContentLoaded();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_CKPreferred", "VEDisabledEditorEntryTests_009"}
	)
	public void VEDisabledEditorEntryTests_009_SectionEditEntry_ckPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditModePageObject ck = article.openCKModeWithSectionEditButton(0);
		ck.verifyContentLoaded();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_CKPreferred", "VEDisabledEditorEntryTests_010"}
	)
	public void VEDisabledEditorEntryTests_010_URLEntry_ckPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_sourcePreferred", "VEDisabledEditorEntryTests_011"}
	)
	public void VEDisabledEditorEntryTests_011_CreatePageEntry_sourcePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_sourcePreferred", "VEDisabledEditorEntryTests_012"}
	)
	public void VEDisabledEditorEntryTests_012_MainEditEntry_sourcePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_sourcePreferred", "VEDisabledEditorEntryTests_013"}
	)
	public void VEDisabledEditorEntryTests_013_RedlinkEntry_sourcePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_sourcePreferred", "VEDisabledEditorEntryTests_014"}
	)
	public void VEDisabledEditorEntryTests_014_SectionEditEntry_sourcePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_sourcePreferred", "VEDisabledEditorEntryTests_015"}
	)
	public void VEDisabledEditorEntryTests_015_URLEntry_sourcePreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_defaultPreferred", "VEDisabledEditorEntryTests_016"}
	)
	public void VEDisabledEditorEntryTests_016_CreatePageEntry_defaultPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_defaultPreferred", "VEDisabledEditorEntryTests_017"}
	)
	public void VEDisabledEditorEntryTests_017_MainEditEntry_defaultPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_defaultPreferred", "VEDisabledEditorEntryTests_018"}
	)
	public void VEDisabledEditorEntryTests_018_RedlinkEntry_defaultPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_defaultPreferred", "VEDisabledEditorEntryTests_019"}
	)
	public void VEDisabledEditorEntryTests_019_SectionEditEntry_defaultPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.testingPage);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEDisabledEditorEntryTests", "VEDisabledEditorEntryTests_defaultPreferred", "VEDisabledEditorEntryTests_020"}
	)
	public void VEDisabledEditorEntryTests_020_URLEntry_defaultPreferred() {
//		String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
