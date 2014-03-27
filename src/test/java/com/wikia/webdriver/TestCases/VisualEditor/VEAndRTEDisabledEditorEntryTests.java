package com.wikia.webdriver.TestCases.VisualEditor;

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
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = false, wgVisualEditorUI = false
 *
 */

public class VEAndRTEDisabledEditorEntryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_VEPreferred", "VEAndRTEDisabledEditorEntryTests_001"}
	)
	public void VEAndRTEDisabledEditorEntryTests_001_CreatePageEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
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
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_VEPreferred", "VEAndRTEDisabledEditorEntryTests_002"}
	)
	public void VEAndRTEDisabledEditorEntryTests_002_MainEditEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
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
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_VEPreferred", "VEAndRTEDisabledEditorEntryTests_003"}
	)
	public void VEAndRTEDisabledEditorEntryTests_003_RedlinkEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
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
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_VEPreferred", "VEAndRTEDisabledEditorEntryTests_004"}
	)
	public void VEAndRTEDisabledEditorEntryTests_004_SectionEditEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
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
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_VEPreferred", "VEAndRTEDisabledEditorEntryTests_005"}
	)
	public void VEAndRTEDisabledEditorEntryTests_005_URLEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_CKPreferred", "VEAndRTEDisabledEditorEntryTests_006"}
	)
	public void VEAndRTEDisabledEditorEntryTests_006_CreatePageEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_CKPreferred", "VEAndRTEDisabledEditorEntryTests_007"}
	)
	public void VEAndRTEDisabledEditorEntryTests_007_MainEditEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_CKPreferred", "VEAndRTEDisabledEditorEntryTests_008"}
	)
	public void VEAndRTEDisabledEditorEntryTests_008_RedlinkEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_CKPreferred", "VEAndRTEDisabledEditorEntryTests_009"}
	)
	public void VEAndRTEDisabledEditorEntryTests_009_SectionEditEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_CKPreferred", "VEAndRTEDisabledEditorEntryTests_010"}
	)
	public void VEAndRTEDisabledEditorEntryTests_010_URLEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_sourcePreferred", "VEAndRTEDisabledEditorEntryTests_011"}
	)
	public void VEAndRTEDisabledEditorEntryTests_011_CreatePageEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_sourcePreferred", "VEAndRTEDisabledEditorEntryTests_012"}
	)
	public void VEAndRTEDisabledEditorEntryTests_012_MainEditEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_sourcePreferred", "VEAndRTEDisabledEditorEntryTests_013"}
	)
	public void VEAndRTEDisabledEditorEntryTests_013_RedlinkEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_sourcePreferred", "VEAndRTEDisabledEditorEntryTests_014"}
	)
	public void VEAndRTEDisabledEditorEntryTests_014_SectionEditEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_sourcePreferred", "VEAndRTEDisabledEditorEntryTests_015"}
	)
	public void VEAndRTEDisabledEditorEntryTests_015_URLEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_defaultPreferred", "VEAndRTEDisabledEditorEntryTests_016"}
	)
	public void VEAndRTEDisabledEditorEntryTests_016_CreatePageEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_defaultPreferred", "VEAndRTEDisabledEditorEntryTests_017"}
	)
	public void VEAndRTEDisabledEditorEntryTests_017_MainEditEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_defaultPreferred", "VEAndRTEDisabledEditorEntryTests_018"}
	)
	public void VEAndRTEDisabledEditorEntryTests_018_RedlinkEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_defaultPreferred", "VEAndRTEDisabledEditorEntryTests_019"}
	)
	public void VEAndRTEDisabledEditorEntryTests_019_SectionEditEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"VEAndRTEDisabledEditorEntryTests", "VEAndRTEDisabledEditorEntryTests_defaultPreferred", "VEAndRTEDisabledEditorEntryTests_020"}
	)
	public void VEAndRTEDisabledEditorEntryTests_020_URLEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
