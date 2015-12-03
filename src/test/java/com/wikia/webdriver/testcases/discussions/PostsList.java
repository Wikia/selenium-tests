package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

/**
 * @ownership Social Wikia
 */
@Test(groups = "Discussions")
public class PostsList extends NewTestTemplate {

  //anons on mobile section

  @Test(groups = "anon")
  @Execute(browserSize = "600x800", asUser = User.ANONYMOUS)
  public void anonSeePostsListLoadedOnMobile() {
    postsListLoads();
  }

  @Test(groups = "anon")
  @Execute(browserSize = "600x800", asUser = User.ANONYMOUS)
  public void anonCanSortPostsListOnMobile() {
    userCanSortPostsList();
  }

  //anons on desktop section

  @Test(groups = "anon")
  @Execute(browserSize = "1366x768", asUser = User.ANONYMOUS)
  public void anonSeePostsListLoadedOnDesktop() {
    postsListLoads();
  }

  //logged in user on mobile section

  @Test(groups = "loggedin")
  @Execute(browserSize = "600x800", asUser = User.USER_3)
  public void loggedInUserCanSortPostsListOnMobile() {
    userCanSortPostsList();
  }

  @Test(groups = "loggedin")
  @Execute(browserSize = "600x800", asUser = User.USER_3)
  public void loggedInUserSeePostsListLoadedOnMobile() {
    postsListLoads();
  }

  //logged in user on desktop section

  @Test(groups = "loggedin")
  @Execute(browserSize = "1366x768", asUser = User.USER_3)
  public void loggedInUserSeePostsListLoadedOnDesktop() {
    postsListLoads();
  }

  public void postsListLoads() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertFalse(postsList.isPostListEmpty());
  }

  public void userCanSortPostsList() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertTrue(postsList.clickOnSortButtonMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickOnLatestLinkMobile().getSortButtonLabel(), "Latest");
    Assertion.assertTrue(postsList.clickOnSortButtonMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickOnTrendingLinkMobile().getSortButtonLabel(), "Trending");
  }
}
