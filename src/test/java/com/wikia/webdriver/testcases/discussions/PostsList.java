package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.testng.annotations.Test;

@Test(groups = "Discussions")
public class PostsList extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnMobileCanSortPostsList() {
    userCanSortPostsList();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnMobileCanViewMorePosts() {
    userCanViewMorePosts();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnMobileCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonCanSwitchBetweenLatestAndTrendingTabOnDesktop() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnDesktopCanViewMorePosts() {
    userCanViewMorePosts();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnDesktopCanClickBackToWiki() {
    backToWiki();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnDesktopCanClickAvatar() {
    clickAvatarLoadsUserPage();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnDesktopCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * LOGGED IN USER ON MOBILE SECTION
   */
  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserOnMobileCanSortPostsList() {
    userCanSortPostsList();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  /**
   * LOGGED IN USER ON DESKTOP SECTION
   */

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.USER_3)
  public void loggedUserCanSwitchBetweenLatestAndTrendingTabOnDesktop() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void postsListLoads() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertFalse(postsList.isPostListEmpty());
  }

  public void userCanSortPostsList() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertTrue(postsList.clickOnSortButtonMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickOnTrendingLinkMobile().getSortButtonLabel(), "Trending");
    postsList.waitForLoadingOverlayToDisappear();
    Assertion.assertTrue(postsList.clickOnSortButtonMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickOnLatestLinkMobile().getSortButtonLabel(), "Latest");
  }

  public void userCanSwitchBetweenLatestAndTrendingTab () {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickOnLatestTabDesktop();
    postsList.waitForLoadingOverlayToDisappear();

    Assertion.assertTrue(driver.getCurrentUrl().contains("latest"));

    postsList.clickOnTrendingTabDesktop();
    postsList.waitForLoadingOverlayToDisappear();

    Assertion.assertTrue(driver.getCurrentUrl().contains("trending"));
  }

  public void userCanViewMorePosts() {
    PostsListPage postsList = new PostsListPage(driver).open();
    int startingListLength = postsList.getPostsListLength();
    postsList.scrollToBottom(driver);
    new BasePageObject(driver).waitForLoadingOverlayToDisappear();
    Assertion.assertTrue(startingListLength < postsList.getPostsListLength());
  }

  public void backToWiki() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickBackToWikiLink();
    postsList.verifyUrl(wikiURL);
  }

  public void clickAvatarLoadsUserPage() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickUserAvatar();
    Assertion.assertTrue(postsList.isUserPageHeaderVisible());
  }

  public void clickUsernameLoadsUserPage() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickUsernameLink();
    Assertion.assertTrue(postsList.isUserPageHeaderVisible());
  }
}
