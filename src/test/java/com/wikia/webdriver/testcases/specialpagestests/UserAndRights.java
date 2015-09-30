package com.wikia.webdriver.testcases.specialpagestests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockListPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialBlockPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.block.SpecialUnblockPageObject;

public class UserAndRights extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"usersAndRights001", "UsersAndRights"})
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

  @Test(groups = {"usersAndRights002", "UsersAndRights"}, dependsOnMethods = {"staffCanBlockUser"})
  public void blockedUserShouldSeeMessageOnArticleEdit() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameBlocked, credentials.passwordBlocked, wikiURL);
    VisualEditModePageObject edit =
        base.goToArticleDefaultContentEditPage(wikiURL,
            PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
    edit.verifyBlockedUserMessage();
  }


  @Test(groups = {"usersAndRights004", "UsersAndRights"}, dependsOnMethods = {"staffCanBlockUser"})
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

  @Test(groups = {"usersAndRights005", "UsersAndRights"},
      dependsOnMethods = {"staffCanUnblockUser"})
  public void unblockedUserCanEditUser() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameBlocked, credentials.passwordBlocked, wikiURL);
    String title = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject edit =
        base.navigateToArticleEditPageCK(wikiURL,
            PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
    edit.clearContent();
    edit.addContent(String.valueOf(DateTime.now().getMillis()));
    edit.submitArticle().verifyArticleTitle(title);
  }
}
