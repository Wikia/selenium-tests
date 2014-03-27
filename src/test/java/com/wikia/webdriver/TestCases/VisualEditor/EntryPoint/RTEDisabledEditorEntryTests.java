package com.wikia.webdriver.TestCases.VisualEditor.EntryPoint;

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

public class RTEDisabledEditorEntryTests extends NewTestTemplateBeforeClass {


	Credentials credentials = config.getCredentials();

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_VEPreferred", "RTEDisabledEditorEntryTests_001"}
	)
	public void RTEDisabledEditorEntryTests_001_CreatePageEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_VEPreferred", "RTEDisabledEditorEntryTests_002"}
	)
	public void RTEDisabledEditorEntryTests_002_MainEditEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_VEPreferred", "RTEDisabledEditorEntryTests_003"}
	)
	public void RTEDisabledEditorEntryTests_003_RedlinkEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_VEPreferred", "RTEDisabledEditorEntryTests_004"}
	)
	public void RTEDisabledEditorEntryTests_004_SectionEditEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_VEPreferred", "RTEDisabledEditorEntryTests_005"}
	)
	public void RTEDisabledEditorEntryTests_005_URLEntry_vePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_CKPreferred", "RTEDisabledEditorEntryTests_006"}
	)
	public void RTEDisabledEditorEntryTests_006_CreatePageEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_CKPreferred", "RTEDisabledEditorEntryTests_007"}
	)
	public void RTEDisabledEditorEntryTests_007_MainEditEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_CKPreferred", "RTEDisabledEditorEntryTests_008"}
	)
	public void RTEDisabledEditorEntryTests_008_RedlinkEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_CKPreferred", "RTEDisabledEditorEntryTests_009"}
	)
	public void RTEDisabledEditorEntryTests_009_SectionEditEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_CKPreferred", "RTEDisabledEditorEntryTests_010"}
	)
	public void RTEDisabledEditorEntryTests_010_URLEntry_ckPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameCKPreferred, credentials.passwordCKPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_sourcePreferred", "RTEDisabledEditorEntryTests_011"}
	)
	public void RTEDisabledEditorEntryTests_011_CreatePageEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.editArticleInSrcUsingDropdown();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_sourcePreferred", "RTEDisabledEditorEntryTests_012"}
	)
	public void RTEDisabledEditorEntryTests_012_MainEditEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_sourcePreferred", "RTEDisabledEditorEntryTests_013"}
	)
	public void RTEDisabledEditorEntryTests_013_RedlinkEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithRedLinks(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_sourcePreferred", "RTEDisabledEditorEntryTests_014"}
	)
	public void RTEDisabledEditorEntryTests_014_SectionEditEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		ArticlePageObject article =
				base.openArticleByName(wikiURL, URLsContent.testingPage);
		SourceEditModePageObject src = article.openSrcModeWithSectionEditButton(0);
		src.verifySourceOnlyMode();
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_sourcePreferred", "RTEDisabledEditorEntryTests_015"}
	)
	public void RTEDisabledEditorEntryTests_015_URLEntry_sourcePreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameSourcePreferred, credentials.passwordSourcePreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}

	@Test(
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_defaultPreferred", "RTEDisabledEditorEntryTests_016"}
	)
	public void RTEDisabledEditorEntryTests_016_CreatePageEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_defaultPreferred", "RTEDisabledEditorEntryTests_017"}
	)
	public void RTEDisabledEditorEntryTests_017_MainEditEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_defaultPreferred", "RTEDisabledEditorEntryTests_018"}
	)
	public void RTEDisabledEditorEntryTests_018_RedlinkEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_defaultPreferred", "RTEDisabledEditorEntryTests_019"}
	)
	public void RTEDisabledEditorEntryTests_019_SectionEditEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
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
			groups = {"RTEDisabledEditorEntryTests", "RTEDisabledEditorEntryTests_defaultPreferred", "RTEDisabledEditorEntryTests_020"}
	)
	public void RTEDisabledEditorEntryTests_020_URLEntry_defaultPreferred() {
//			String wikiURL = urlBuilder.getUrlForWiki(URLsContent.rteDisabledTestMainPage);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameDefaultPreferred, credentials.passwordDefaultPreferred, wikiURL);
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.logOut(wikiURL);
	}
}
