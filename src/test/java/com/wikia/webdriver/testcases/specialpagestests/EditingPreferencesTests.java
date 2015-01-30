package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.EditingPreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Robert 'rochan' Chan
 * @ownership Contribution <p/> VE-1202 Select VE from editor preference page then clicking on main
 * article edit would launch VE VE-1202 Select CK from editor preference page then clicking on main
 * article edit would launch CK VE-1202 Select Source from editor preference page then clicking on
 * main article edit would launch source
 */
public class EditingPreferencesTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();
  WikiBasePageObject base;
  private static final String SOURCE = "1";
  private static final String VE = "2";
  private static final String CK = "3";

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
    base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName5, credentials.password5, wikiURL);
  }

  @Test(groups = {"EditingPreferencesTest", "EditPreferences_001"})
  public void EditPreferences_001_selectVE() {
    EditingPreferencesPageObject editPrefPage = base.openSpecialEditingPreferencesPage(wikiURL);
    editPrefPage.selectPreferredEditor(VE);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
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
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
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
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    ArticlePageObject aritclePage = prefPage.openArticleByName(wikiURL, articleName);
    SourceEditModePageObject src = aritclePage.openSrcModeWithMainEditButton();
    src.verifySourceOnlyMode();
  }
}

