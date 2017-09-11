package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.EmailUtils;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.components.notifications.Notification;
import com.wikia.webdriver.elements.oasis.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialUnblockPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.EditPreferencesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;
import org.joda.time.DateTime;
import org.testng.annotations.Test;
import java.util.List;


@Test(groups = {"UsersAndRights"})
public class UserAndRights extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"usersAndRights001"})
  @Execute(asUser = User.STAFF)
  public void staffCanBlockUser() {
    SpecialBlockPage block = new SpecialBlockPage(driver).open();
    block.deselectAllSelections();
    block.typeInUserName(credentials.userNameBlocked);
    block.selectExpiration("2 hours");
    block.clickBlockButton();

    SpecialBlockListPage list =
        new SpecialBlockListPage().open();
    list.searchForUser(credentials.userNameBlocked);
    list.verifyUserBlocked(credentials.userNameBlocked);
  }

  @Test(groups = {"usersAndRights002"}, dependsOnMethods = {"staffCanBlockUser"})
  @Execute(asUser = User.BLOCKED_USER)
  public void blockedUserShouldSeeMessageOnArticleEdit() {
    VisualEditModePageObject edit =
        new WikiBasePageObject().goToArticleDefaultContentEditPage(wikiURL,
            PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis());

    edit.verifyUserLoggedIn(User.BLOCKED_USER);

    edit.verifyBlockedUserMessage();
  }

  @Test(groups = {"usersAndRights003"}, dependsOnMethods = {"staffCanBlockUser"})
  @Execute(asUser = User.BLOCKED_USER)
  public void blockedUserShouldBeAbleToChangeEmail() {
    String username = Configuration.getCredentials().emailQaart2;
    String password = Configuration.getCredentials().emailPasswordQaart2;
    EditPreferencesPage editPrefPage = new EditPreferencesPage().openEmailSection();
    editPrefPage.verifyUserLoggedIn(User.BLOCKED_USER);

    editPrefPage.openEmailSection();
    EmailUtils.deleteAllEmails(username, password);

    String newEmailAddress = EmailUtils.getEmail(editPrefPage.getEmailAdress());

    editPrefPage.changeEmail(newEmailAddress);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();

    List<Notification> confirmNotifications = prefPage.getNotifications(NotificationType.CONFIRM);
    Assertion.assertEquals(confirmNotifications.size(),1,
            PreferencesPageObject.AssertionMessages.INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS);
    Assertion.assertTrue(confirmNotifications.stream().findFirst().get().isVisible(),
            PreferencesPageObject.AssertionMessages.BANNER_NOTIFICATION_NOT_VISIBLE);

    prefPage.enterEmailChangeLink(username, password);

    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), newEmailAddress);
  }

  @Test(groups = {"usersAndRights004"}, dependsOnMethods = {"staffCanBlockUser"})
  @Execute(asUser = User.STAFF)
  public void staffCanUnblockUser() {
    SpecialUnblockPage unblock =
        new SpecialUnblockPage().open();
    unblock.unblockUser(credentials.userNameBlocked);
    unblock.verifyUnblockMessage(credentials.userNameBlocked);

    SpecialBlockListPage list = new SpecialBlockListPage().open();
    list.searchForUser(credentials.userNameBlocked);
    list.verifyUserUnblocked();
  }

  @Test(groups = {"usersAndRights005"}, dependsOnMethods = {"staffCanUnblockUser"})
  public void unblockedUserCanEditUser() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userNameBlocked, credentials.passwordBlocked, wikiURL);
    String title = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject edit =
        base.navigateToArticleEditPage(wikiURL,
            PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
    edit.clearContent();
    edit.addContent(String.valueOf(DateTime.now().getMillis()));
    edit.submitArticle().verifyArticleTitle(title);
  }
}
