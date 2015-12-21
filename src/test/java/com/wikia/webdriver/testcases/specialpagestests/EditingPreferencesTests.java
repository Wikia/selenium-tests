package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.ConfirmationPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.EditPreferencesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

@Test(groups = {"EditingPreferencesTest"})
public class EditingPreferencesTests extends NewTestTemplate {

  private static final String SOURCE = "1";
  private static final String VE = "2";
  private static final String CK = "3";

  @Test(groups = {"EditPreferences_001"})
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

  @Test(groups = {"EditPreferences_002"})
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

  @Test(groups = {"EditPreferences_003"})
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

  @Test(groups = {"EditPreferences_004"})
  @Execute(asUser = User.USER_5)
  @RelatedIssue(issueID = "MAIN-5753", comment = "not possible to test until the issue is fixed")
  public void changeEmailAddress() {
    final String newEmailAddress = Configuration.getCredentials().emailQaart2;
    final String oldEmailAddress = Configuration.getCredentials().email;

    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEmailSection();

    MailFunctions.deleteAllEmails(Configuration.getCredentials().emailQaart2,
                                  Configuration.getCredentials().emailPasswordQaart2);

    editPrefPage.changeEmail(newEmailAddress);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();

    ConfirmationPageObject confirmPageAlmostThere =
        new AlmostTherePageObject(driver).enterEmailChangeLink(
            Configuration.getCredentials().emailQaart2,
            Configuration.getCredentials().emailPasswordQaart2);
    confirmPageAlmostThere.typeInUserName(User.USER_5.getUserName());
    confirmPageAlmostThere.typeInPassword(User.USER_5.getPassword());
    confirmPageAlmostThere.clickSubmitButton(Configuration.getCredentials().emailQaart2,
        Configuration.getCredentials().emailPasswordQaart2);

    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), newEmailAddress);

    MailFunctions.deleteAllEmails(Configuration.getCredentials().email,
        Configuration.getCredentials().emailPassword);

    editPrefPage.changeEmail(oldEmailAddress);
    editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();

    confirmPageAlmostThere =
        new AlmostTherePageObject(driver).enterEmailChangeLink(
            Configuration.getCredentials().email, Configuration.getCredentials().emailPassword);
    confirmPageAlmostThere.typeInUserName(User.USER_5.getUserName());
    confirmPageAlmostThere.typeInPassword(User.USER_5.getPassword());
    confirmPageAlmostThere.clickSubmitButton(Configuration.getCredentials().email,
                                             Configuration.getCredentials().emailPassword);

    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), oldEmailAddress);
  }
}
