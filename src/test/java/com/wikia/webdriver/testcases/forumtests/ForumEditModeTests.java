package com.wikia.webdriver.testcases.forumtests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumManageBoardsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPage;

//User has to be an admin on wiki to delete and create forum
@Test(groups = {"Forum", "ForumEditMode"})
public class ForumEditModeTests extends NewTestTemplate {

  private String title, description, first, second;

  @DataProvider
  private static final Object[][] getForumName() {
    return new Object[][] {{PageContent.FORUM_TITLE_NON_LATIN_PREFIX},
        {PageContent.FORUM_TITLE_PREFIX}, {PageContent.FORUM_TITLE_40_CHAR_PREFIX},
        {PageContent.FORUM_TITLE_SLASH_PREFIX}, {PageContent.FORUM_TITLE_UNDER_SCORE_PREFIX}};
  }

  @Test(groups = {"ForumEditModeTests_001"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanOpenFrequentlyAskedQuestionsModalOnForum() {
    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    forumMainPage.verifyFaqLightBox();
  }

  @Test(dataProvider = "getForumName", groups = {"ForumEditModeTests_002"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanCreateNewBoard(String name) {
    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    title = name + manageForum.getTimeStamp();
    description = PageContent.FORUM_DESCRIPTION_PREFIX + manageForum.getTimeStamp();
    manageForum.createNewBoard(title, description);
    manageForum.verifyBoardCreated(title, description);
    manageForum.verifyForumExists(title, wikiURL);
  }

  @Test(groups = {"ForumEditModeTests_003"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanDeleteBoard() {
    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    first = manageForum.getFirstForumName();
    second = manageForum.getSecondForumName();
    manageForum.verifyForumExists(first, wikiURL);
    manageForum.deleteForum(first, second);
    manageForum.verifyForumNotExists(first, wikiURL);
  }

  @Test(groups = {"ForumEditModeTests_004"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanEditForum() {
    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    first = manageForum.getFirstForumName();
    title = PageContent.FORUM_TITLE_EDIT_PREFIX + manageForum.getTimeStamp();
    description = PageContent.FORUM_DESCRIPTION_EDIT_PREFIX + manageForum.getTimeStamp();
    manageForum.editForum(first, title, description);
    manageForum.verifyBoardCreated(title, description);
    manageForum.verifyForumExists(title, wikiURL);
  }

  @Test(groups = {"Forum_005", "Forum"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanMoveBoard() {
    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    first = manageForum.getFirstForumName();
    manageForum.clickMoveDown(first);
    second = manageForum.getSecondForumName();
    manageForum.clickMoveUp(second);
  }
}
