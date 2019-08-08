package com.wikia.webdriver.testcases.desktop.forumtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumManageBoardsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// User has to be an admin on wiki to delete and create forum
@Test(groups = {"Forum", "ForumEditMode"})
public class ForumEditModeTests extends NewTestTemplate {

  private String title, description, first, second;

  @DataProvider
  private static final Object[][] getForumName() {
    return new Object[][]{{PageContent.FORUM_TITLE_NON_LATIN_PREFIX},
                          {PageContent.FORUM_TITLE_PREFIX},
                          {PageContent.FORUM_TITLE_40_CHAR_PREFIX},
                          {PageContent.FORUM_TITLE_SLASH_PREFIX},
                          {PageContent.FORUM_TITLE_UNDER_SCORE_PREFIX}};
  }

  @Test(groups = {"ForumEditModeTests_001"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanOpenFrequentlyAskedQuestionsModalOnForum() {
    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.createNew(User.USER_ADMIN_FORUM);

    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    forumMainPage.verifyFaqLightBox();
  }

  @Test(dataProvider = "getForumName", groups = {"ForumEditModeTests_002"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanCreateNewBoard(String name) {
    ForumBoardPage forumBoard = new ForumBoardPage();
    forumBoard.createNew(User.USER_ADMIN_FORUM);

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
    ForumBoardPage forumBoard = new ForumBoardPage();
    first = forumBoard.createNew(User.USER_ADMIN_FORUM);
    second = forumBoard.createNew(User.USER_ADMIN_FORUM);

    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    System.out.println(first+ "    "+second);
    description = PageContent.FORUM_DESCRIPTION_PREFIX + manageForum.getTimeStamp();
    manageForum.createNewBoard(first+second, description);
    manageForum.verifyBoardCreated(first+second, description);
    manageForum.verifyForumExists(first+second, wikiURL);

    manageForum.createNewBoard(second+first, description);
    manageForum.verifyBoardCreated(second+first, description);
    manageForum.verifyForumExists(second+first, wikiURL);

    manageForum.deleteForum(second+first, first+second);
    manageForum.verifyForumNotExists(second+first, wikiURL);
  }

  @Test(groups = {"ForumEditModeTests_004"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanEditForum() {
    ForumBoardPage forumBoard = new ForumBoardPage();
    first = forumBoard.createNew(User.USER_ADMIN_FORUM);

    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);

    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    title = PageContent.FORUM_TITLE_EDIT_PREFIX + manageForum.getTimeStamp();
    description = PageContent.FORUM_DESCRIPTION_EDIT_PREFIX + manageForum.getTimeStamp();
    manageForum.createNewBoard(first+second, description);
    manageForum.verifyBoardCreated(first+second, description);
    manageForum.verifyForumExists(first+second, wikiURL);
    manageForum.editForum(first+second, title, title + description);
    manageForum.verifyBoardCreated(title, title + description);
    manageForum.verifyForumExists(title, wikiURL);
  }

  @Test(groups = {"Forum_005", "Forum"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanMoveBoard() {
    ForumBoardPage forumBoard = new ForumBoardPage();
    first = forumBoard.createNew(User.USER_ADMIN_FORUM);

    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    description = PageContent.FORUM_DESCRIPTION_EDIT_PREFIX + manageForum.getTimeStamp();
    manageForum.createNewBoard(first+second, description);
    manageForum.verifyBoardCreated(first+second, description);
    manageForum.verifyForumExists(first+second, wikiURL);

    int beforeMoveDown = manageForum.getBoardPosition(first+second);
    manageForum.clickMoveDown(first+second);
    Assertion.assertTrue(beforeMoveDown < manageForum.getBoardPosition(first+second), "");
    manageForum.clickMoveUp(first+second);
    Assertion.assertTrue(beforeMoveDown == manageForum.getBoardPosition(first+second), "");
  }
}