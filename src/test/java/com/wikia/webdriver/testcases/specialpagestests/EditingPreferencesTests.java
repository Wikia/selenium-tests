package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.components.notifications.Notification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.EditPreferencesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.List;

import static com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject.CONFIRM_NOTIFICATION;

@Test(groups = {"EditingPreferencesTest"})
public class EditingPreferencesTests extends NewTestTemplate {

  private static final String SOURCE = "1";
  private static final String VE = "2";
  private static final String CK = "3";
  private static final String USERNAME = Configuration.getCredentials().emailQaart2;
  private static final String PASSWORD = Configuration.getCredentials().emailPasswordQaart2;

  @Test(groups = {"EditPreferences_001"})
  @Execute(asUser = User.USER_5, onWikia = URLsContent.VE_ENABLED_WIKI)
  @RelatedIssue(issueID = "MAIN-9722", comment = "test failing randomly")
  public void EditPreferences_001_selectVE() {
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEditingSection();
    editPrefPage.selectPreferredEditor(VE);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();

    List<Notification> confirmNotifications = prefPage.getNotifications(CONFIRM_NOTIFICATION);
    Assertion.assertEquals(confirmNotifications.size(),1,
            "Number of action confirming notifications is invalid");
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
            "Banner notification message is not visible");

    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject aritclePage = new ArticlePageObject().open(articleName);
    VisualEditorPageObject ve = aritclePage.openVEModeWithMainEditButton();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
  }

  @Test(groups = {"EditPreferences_002"})
  @Execute(asUser = User.USER_5, onWikia = URLsContent.VE_ENABLED_WIKI)
  @RelatedIssue(issueID = "MAIN-9722", comment = "test failing randomly")
  public void EditPreferences_002_selectCK() {
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEditingSection();
    editPrefPage.selectPreferredEditor(CK);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();

    List<Notification> confirmNotifications = prefPage.getNotifications(CONFIRM_NOTIFICATION);
    Assertion.assertEquals(confirmNotifications.size(),1,
            "Number of action confirming notifications is invalid");
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
            "Banner notification message is not visible");

    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    ArticlePageObject aritclePage = new ArticlePageObject().open(articleName);
    VisualEditModePageObject ck = aritclePage.navigateToArticleEditPage();
    Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
    ck.clickPublishButton();
  }

  @Test(groups = {"EditPreferences_003"})
  @Execute(asUser = User.USER_5, onWikia = URLsContent.VE_ENABLED_WIKI)
  public void EditPreferences_003_selectSource() {
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEditingSection();
    editPrefPage.selectPreferredEditor(SOURCE);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();

    List<Notification> confirmNotifications = prefPage.getNotifications(CONFIRM_NOTIFICATION);
    Assertion.assertEquals(confirmNotifications.size(),1,
            "Number of action confirming notifications is invalid");
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
            "Banner notification message is not visible");

    String articleName = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();

    ArticlePageObject aritclePage = new ArticlePageObject().open(articleName);
    SourceEditModePageObject src = aritclePage.openSrcModeWithMainEditButton();
    src.verifySourceOnlyMode();
  }

  @Test(groups = {"EditPreferences_004"})
  @Execute(asUser = User.USER_5)
  public void changeEmailAddress() {
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEmailSection();

    String newEmailAddress = EmailUtils.getEmail(editPrefPage.getEmailAdress());

    EmailUtils.deleteAllEmails(USERNAME, PASSWORD);

    Assertion.assertNotEquals(newEmailAddress, editPrefPage.getEmailAdress(),
        "New email and old email SHOULD NOT be the same");

    editPrefPage.changeEmail(newEmailAddress);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();

    List<Notification> confirmNotifications = prefPage.getNotifications(CONFIRM_NOTIFICATION);
    Assertion.assertEquals(confirmNotifications.size(),1,
            "Number of action confirming notifications is invalid");
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
            "Banner notification message is not visible");

    editPrefPage.enterEmailChangeLink(USERNAME, PASSWORD);
    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), newEmailAddress,
                           "Email address doesn't equal to new email address");
  }
}
