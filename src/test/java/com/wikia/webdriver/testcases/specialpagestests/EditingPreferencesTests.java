package com.wikia.webdriver.testcases.specialpagestests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.EditPreferencesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 * @ownership Contribution
 *            <p/>
 *            VE-1202 Select VE from editor preference page then clicking on main article edit would
 *            launch VE VE-1202 Select CK from editor preference page then clicking on main article
 *            edit would launch CK VE-1202 Select Source from editor preference page then clicking
 *            on main article edit would launch source
 */
public class EditingPreferencesTests extends NewTestTemplate {

  private static final String SOURCE = "1";
  private static final String VE = "2";
  private static final String CK = "3";

  @Test(groups = {"EditingPreferencesTest", "EditPreferences_001"})
  @Execute(asUser = User.USER_5, onWikia = URLsContent.VE_ENABLED_WIKI)
  public void EditPreferences_001_selectVE() {
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEditingSection();
    editPrefPage.selectPreferredEditor(VE);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject aritclePage = new ArticlePageObject(driver).open(articleName);
    VisualEditorPageObject ve = aritclePage.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(groups = {"EditingPreferencesTest", "EditPreferences_002"})
  @Execute(asUser = User.USER_5, onWikia = URLsContent.VE_ENABLED_WIKI)
  public void EditPreferences_002_selectCK() {
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEditingSection();
    editPrefPage.selectPreferredEditor(CK);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject aritclePage = new ArticlePageObject(driver).open(articleName);
    VisualEditModePageObject ck = aritclePage.navigateToArticleEditPage();
    ck.verifyContentLoaded();
    ck.clickPublishButton();
  }

  @Test(groups = {"EditingPreferencesTest", "EditPreferences_003"})
  @Execute(asUser = User.USER_5, onWikia = URLsContent.VE_ENABLED_WIKI)
  public void EditPreferences_003_selectSource() {
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEditingSection();
    editPrefPage.selectPreferredEditor(SOURCE);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject aritclePage = new ArticlePageObject(driver).open(articleName);
    SourceEditModePageObject src = aritclePage.openSrcModeWithMainEditButton();
    src.verifySourceOnlyMode();
  }

  @Test(groups = {"EditingPreferencesTest", "EditPreferences_003"})
  @Execute(asUser = User.USER_5)
  public void changeEmailAddress() {
    final String newEmailAddress = "myAwesomeEmail@email.co.uk";

    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEmailSection();

    editPrefPage.changeEmail(newEmailAddress);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();

    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), newEmailAddress);
    editPrefPage.changeEmail(Configuration.getCredentials().email);
    editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();

    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), Configuration.getCredentials().email);
  }
}
