package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.AlmostTherePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.ConfirmationPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialUnblockPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.EditPreferencesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences.PreferencesPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

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
    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEmailSection();
    editPrefPage.verifyUserLoggedIn(User.BLOCKED_USER);

    editPrefPage.openEmailSection();
    MailFunctions.deleteAllEmails(Configuration.getCredentials().emailQaart2,
        Configuration.getCredentials().emailPasswordQaart2);

    String newEmailAddress = MailFunctions.getEmail(editPrefPage.getEmailAdress());

    editPrefPage.changeEmail(newEmailAddress);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    
    Assertion.assertTrue(prefPage.getBannerNotifications().isNotificationMessageVisible(),
                         "Notification message is not visible");

    ConfirmationPageObject confirmPageAlmostThere =
        new AlmostTherePageObject(driver).enterEmailChangeLink(
            Configuration.getCredentials().emailQaart2,
            Configuration.getCredentials().emailPasswordQaart2);
    confirmPageAlmostThere.typeInUserName(User.BLOCKED_USER.getUserName());
    confirmPageAlmostThere.typeInPassword(User.BLOCKED_USER.getPassword());
    confirmPageAlmostThere.clickSubmitButton(Configuration.getCredentials().emailQaart2,
        Configuration.getCredentials().emailPasswordQaart2);

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
