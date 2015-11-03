package com.wikia.webdriver.testcases.specialpagestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialContactGeneralPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockListPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialUnblockPageObject;
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
    SpecialBlockPageObject block = new SpecialBlockPageObject(driver).open();
    block.deselectAllSelections();
    block.typeInUserName(credentials.userNameBlocked);
    block.selectExpiration("2 hours");
    block.clickBlockButton();

    SpecialBlockListPageObject list =
        new SpecialBlockListPageObject(driver).openSpecialBlockListPage(wikiURL);
    list.searchForUser(credentials.userNameBlocked);
    list.verifyUserBlocked(credentials.userNameBlocked);
  }

  @Test(groups = {"usersAndRights002"}, dependsOnMethods = {"staffCanBlockUser"})
  public void blockedUserShouldSeeMessageOnArticleEdit() {
    VisualEditModePageObject edit =
        new WikiBasePageObject(driver).goToArticleDefaultContentEditPage(wikiURL,
            PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis());

    edit.getVenusGlobalNav().openAccountNAvigation().logIn(User.BLOCKED_USER);
    edit.verifyUserLoggedIn(User.BLOCKED_USER);

    edit.verifyBlockedUserMessage();
  }

  @Test(groups = {"usersAndRights003"}, dependsOnMethods = {"staffCanBlockUser"})
  @RelatedIssue(issueID = "MAIN-5753", comment = "not possible to test until the issue is fixed")
  public void blockedUserShouldBeAbleToChangeEmail() {
    final String newEmailAddress = "myAwesomeEmail@email.co.uk";
    final String oldEmailAddress = Configuration.getCredentials().email;

    EditPreferencesPage editPrefPage = new EditPreferencesPage(driver).openEmailSection();
    editPrefPage.getVenusGlobalNav().openAccountNAvigation().logIn(User.BLOCKED_USER);
    editPrefPage.verifyUserLoggedIn(User.BLOCKED_USER);

    editPrefPage.openEmailSection();
    editPrefPage.changeEmail(newEmailAddress);
    PreferencesPageObject prefPage = editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();

    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), newEmailAddress);

    editPrefPage.changeEmail(oldEmailAddress);
    editPrefPage.clickSaveButton();
    prefPage.verifyNotificationMessage();

    editPrefPage.openEmailSection();
    Assertion.assertEquals(editPrefPage.getEmailAdress(), oldEmailAddress);
  }

  @Test(groups = {"usersAndRights003"}, dependsOnMethods = {"staffCanBlockUser"})
  public void blockedUserShouldBeAbleToAccessSpecialContactPage() {
    SpecialContactGeneralPage contactPage = new SpecialContactGeneralPage(driver).open();

    contactPage.getVenusGlobalNav().openAccountNAvigation().logIn(User.BLOCKED_USER);
    contactPage.verifyUserLoggedIn(User.BLOCKED_USER);

    Assertion.assertTrue(contactPage.isLoggedInUserMessageVisible(User.BLOCKED_USER));
  }

  @Test(groups = {"usersAndRights004"}, dependsOnMethods = {"staffCanBlockUser"})
  @Execute(asUser = User.STAFF)
  public void staffCanUnblockUser() {
    SpecialUnblockPageObject unblock =
        new SpecialUnblockPageObject(driver).openSpecialUnblockPage(wikiURL);
    unblock.unblockUser(credentials.userNameBlocked);
    unblock.verifyUnblockMessage(credentials.userNameBlocked);

    SpecialBlockListPageObject list = unblock.openSpecialBlockListPage(wikiURL);
    list.searchForUser(credentials.userNameBlocked);
    list.verifyUserUnblocked();
  }

  @Test(groups = {"usersAndRights005"}, dependsOnMethods = {"staffCanUnblockUser"})
  public void unblockedUserCanEditUser() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
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
