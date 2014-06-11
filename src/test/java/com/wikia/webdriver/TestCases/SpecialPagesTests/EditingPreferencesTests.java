package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences.EditingPreferencesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences.PreferencesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 *
 * VE-1202 Select VE from editor preference page then clicking on main article edit would launch VE
 * VE-1202 Select CK from editor preference page then clicking on main article edit would launch CK
 * VE-1202 Select Source from editor preference page then clicking on main article edit would launch source
 * VE-1202 Select Default from editor preference page then clicking on main article edit would launch VE
 */
public class EditingPreferencesTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String DEFAULT = "0";
	String SOURCE = "1";
	String VE = "2";
	String CK = "3";

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.veEnabledTestMainPage);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName5, credentials.password5, wikiURL);
	}

	@Test(groups = {"EditingPreferencesTest", "EditPreferences_001"})
	public void EditPreferences_001_selectVE() {
		EditingPreferencesPageObject editPrefPage = base.openSpecialEditingPreferencesPage(wikiURL);
		editPrefPage.selectPreferredEditor(VE);
		PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
		prefPage.verifyNotificationMessage();
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject aritclePage = prefPage.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = aritclePage.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(groups = {"EditingPreferencesTest", "EditPreferences_002"})
	public void EditPreferences_002_selectCK() {
		EditingPreferencesPageObject editPrefPage = base.openSpecialEditingPreferencesPage(wikiURL);
		editPrefPage.selectPreferredEditor(CK);
		PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
		prefPage.verifyNotificationMessage();
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject aritclePage = prefPage.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = aritclePage.editArticleInRTEUsingDropdown();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(groups = {"EditingPreferencesTest", "EditPreferences_003"})
	public void EditPreferences_003_selectSource() {
		EditingPreferencesPageObject editPrefPage = base.openSpecialEditingPreferencesPage(wikiURL);
		editPrefPage.selectPreferredEditor(SOURCE);
		PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
		prefPage.verifyNotificationMessage();
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject aritclePage = prefPage.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject src = aritclePage.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(groups = {"EditingPreferencesTest", "EditPreferences_004"})
	public void EditPreferences_004_selectDefault() {
		EditingPreferencesPageObject editPrefPage = base.openSpecialEditingPreferencesPage(wikiURL);
		editPrefPage.selectPreferredEditor(DEFAULT);
		PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
		prefPage.verifyNotificationMessage();
		String articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		ArticlePageObject aritclePage = prefPage.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = aritclePage.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}
}
