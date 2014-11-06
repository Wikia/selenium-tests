package com.wikia.webdriver.TestCases.VisualEditor.EntryPoint;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Editor;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.EditorPref;
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
 * VE-881 Verify Visual Editor is loaded for signed in user by clicking on Edit on article on VE enabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit in the URL on VE enabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit in the URL on VE disabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit via redlink on VE enabled wiki
 * VE-884 Verify VE is loaded for signed in user by using ?veaction=edit via redlink on VE disabled wiki
 * VE-884 Verify Visual Editor is loaded for signed in user by clicking on Edit on section on VE enabled wiki
 */

public class VisualEditorEntryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();


	ArticlePageObject article;
	VisualEditModePageObject ck;
	SourceEditModePageObject src;
	VisualEditorPageObject ve;
	String articleName;

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_001", "categoryEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "categoryEntryPoints"
	)
	public void VisualEditorEntryTest_001_Category(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.categoryPage);
		verifyMainEditEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_002", "createAPageEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "createAPageEntryPoints"
	)
	public void VisualEditorEntryTest_002_CreateAPage(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, articleName);
		verifyCreateAPageEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_003", "listEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "listEntryPoints"
	)
	public void VisualEditorEntryTest_003_List(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.listPage);
		verifyMainEditEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_004", "articleEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "mainEditEntryPoints"
	)
	public void VisualEditorEntryTest_004_Article(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.testingPage);
		verifyMainEditEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_005", "redLinkEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "redLinkEntryPoints"
	)
	public void VisualEditorEntryTest_005_redLink(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.testingPage);
		verifyRedLinkEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_006", "sectionEditEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "sectionEditEntryPoints"
	)
	public void VisualEditorEntryTest_006_sectionEdit(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.testingPage);
		verifySectionEditEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_007", "templateEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "templateEntryPoints"
	)
	public void VisualEditorEntryTest_007_template(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.templatePage);
		verifyMainEditEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_008", "urlActionEditEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "urlActionEditEntryPoints"
	)
	public void VisualEditorEntryTest_008_urlActionEdit(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.testingPage);
		verifyURLActionEditEditor(expectedEditor);
	}

	@Test(
		groups = {"VisualEditorEntry", "VisualEditorEntryTest_009", "urlVEActionEditEntry"},
		dataProviderClass = VisualEditorDataProvider.class,
		dataProvider = "urlVEActionEditEntryPoints"
	)
	public void VisualEditorEntryTest_009_urlVEActionEdit(boolean isRTEext, boolean isVEext, EditorPref editorPref, Editor expectedEditor ) {
		wikiURL = urlBuilder.getUrlForWiki(VisualEditorDataProvider.getTestWiki(isRTEext, isVEext));
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.getUserBaseOnEditorPref(editorPref), credentials.getPassBaseOnEditorPref(editorPref), wikiURL);
		article = base.openArticleByName(wikiURL, URLsContent.testingPage);
		verifyURLVEActionEditEditor(expectedEditor);
	}

	private void verifyMainEditEditor(Editor expectedEditor) {
		switch(expectedEditor) {
			case VE:
				ve = article.openVEModeWithMainEditButton();
				ve.verifyVEToolBarPresent();
				ve.verifyEditorSurfacePresent();
				break;
			case CK:
				ck = article.openCKModeWithMainEditButton();
				ck.verifyContentLoaded();
				ck.clickPublishButton();
				break;
			case SRC:
				src = article.openSrcModeWithMainEditButton();
				src.verifySourceOnlyMode();
				break;
			}
	}

	private void verifyCreateAPageEditor(Editor expectedEditor) {
		switch(expectedEditor) {
			case VE:
				ve = article.createArticleInVEUsingDropdown(articleName);
				ve.verifyVEToolBarPresent();
				ve.verifyEditorSurfacePresent();
				break;
			case CK:
				ck = article.createArticleInCKUsingDropdown(articleName);
				ck.verifyContentLoaded();
				ck.clickPublishButton();
				break;
			case SRC:
				src = article.createArticleInSrcUsingDropdown(articleName);
				src.verifySourceOnlyMode();
				break;
		}
	}

	private void verifySectionEditEditor(Editor expectedEditor) {
		switch(expectedEditor) {
			case VE:
				ve = article.openVEModeWithSectionEditButton(0);
				ve.verifyVEToolBarPresent();
				ve.verifyEditorSurfacePresent();
				break;
			case CK:
				ck = article.openCKModeWithSectionEditButton(0);
				ck.verifyContentLoaded();
				ck.clickPublishButton();
				break;
			case SRC:
				src = article.openSrcModeWithSectionEditButton(0);
				src.verifySourceOnlyMode();
				break;
		}
	}

	private void verifyRedLinkEditor(Editor expectedEditor) {
		switch(expectedEditor) {
			case VE:
				ve = article.openVEModeWithRedLinks(0);
				ve.verifyVEToolBarPresent();
				ve.verifyEditorSurfacePresent();
				break;
			case CK:
				ck = article.openCKModeWithRedLinks(0);
				ck.verifyContentLoaded();
				ck.clickPublishButton();
				break;
			case SRC:
				src = article.openSrcModeWithRedLinks(0);
				src.verifySourceOnlyMode();
				break;
		}
	}

	private void verifyURLActionEditEditor(Editor expectedEditor) {
		switch(expectedEditor) {
			case CK:
				ck = article.navigateToArticleEditPageCK(wikiURL, articleName);
				ck.verifyContentLoaded();
				ck.clickPublishButton();
				break;
			case SRC:
				src = article.navigateToArticleEditPageSrc(wikiURL, articleName);
				src.verifySourceOnlyMode();
				break;
			default:
				throw new NoSuchElementException("Invalid expected editor chosen: " + expectedEditor.name());
		}
	}

	private void verifyURLVEActionEditEditor(Editor expectedEditor) {
		switch(expectedEditor) {
			case VE:
				ve = article.openNewArticleEditModeVisual(wikiURL);
				ve.verifyVEToolBarPresent();
				ve.verifyEditorSurfacePresent();
				break;
			default:
				throw new NoSuchElementException("Invalid expected editor chosen: " + expectedEditor.name());
		}
	}
}
