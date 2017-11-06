package com.wikia.webdriver.testcases.forumtests;

import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumBoardPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumManageBoardsPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject.ForumPage;

// User has to be an admin on wiki to delete and create forum
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
    manageForum.verifyForumExists(first, wikiURL);
    manageForum.deleteForum(first, second);
    manageForum.verifyForumNotExists(first, wikiURL);
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
    manageForum.editForum(first, title, description);
    manageForum.verifyBoardCreated(title, description);
    manageForum.verifyForumExists(title, wikiURL);
  }

  @Test(groups = {"Forum_005", "Forum"})
  @Execute(asUser = User.USER_ADMIN_FORUM)
  public void adminUserCanMoveBoard() {
    ForumBoardPage forumBoard = new ForumBoardPage();
    first = forumBoard.createNew(User.USER_ADMIN_FORUM);
    second = forumBoard.createNew(User.USER_ADMIN_FORUM);

    ForumPage forumMainPage = new ForumPage();
    forumMainPage.openForumMainPage(wikiURL);
    ForumManageBoardsPageObject manageForum = forumMainPage.clickManageBoardsButton();
    int beforeMoveDown = manageForum.getBoardPosition(first);
    manageForum.clickMoveDown(first);
    Assertion.assertTrue(beforeMoveDown < manageForum.getBoardPosition(first), "");
    manageForum.clickMoveUp(first);
    Assertion.assertTrue(beforeMoveDown == manageForum.getBoardPosition(first), "");
  }

  @Test
  public void clickExplore(){
    new ArticleContent().push();

    ArticlePageObject article = new ArticlePageObject().open();
    article.getLocalNavigation().openExplore();
    article.getLocalNavigation().clickImages();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Special:Images"));
  }

  @Test
  public void clickCommunity(){
    new ArticleContent().push();

    ArticlePageObject article = new ArticlePageObject().open();
    article.getNestedNavigation().openCommunity();
    article.getNestedNavigation().openScrollTest3();
    article.getNestedNavigation().clickCat_3();
    Assertion.assertTrue(driver.getCurrentUrl().contains("Category:Videos"));


  }

  @Test
  @Execute(onWikia = "chesskynet", asUser = User.SUS_REGULAR_USER)
  public void uploadVideo(){
    new ArticleContent().push();

    ArticlePageObject article = new ArticlePageObject().open();
    article.getLocalNavigation().openExplore();
    article.getLocalNavigation().clickVidoesButton();
    article.getUploadVideo().clickAddVideo();
    article.getUploadVideo().addVideo("https://www.youtube"
                                      + ".com/watch?time_continue=3&v=KsVWdGOnHZU");





  }
}

