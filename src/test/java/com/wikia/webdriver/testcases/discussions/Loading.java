package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostDetailsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

@Test(groups = {"Discussions", "Loading"})
public class Loading extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = {"Loading_001"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = {"Loading_002"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = {"Loading_003"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanViewMorePosts() {
    userCanViewMorePosts();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = {"Loading_004"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = {"Loading_005"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = {"Loading_006"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanViewMorePosts() {
    userCanViewMorePosts();
  }
  
  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = {"Loading_007"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = {"Loading_008"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostsList() {
    postsListLoads();
  }


  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = {"Loading_009"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = {"Loading_010"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void postDetailsListLoads() {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    Assertion.assertFalse(postDetails.isPostDetailsListEmpty());
  }

  public void postsListLoads() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertFalse(postsList.isPostListEmpty());
  }

  public void userCanViewMorePosts() {
    PostsListPage postsList = new PostsListPage(driver).open();
    int startingListLength = postsList.getPostsListLength();
    postsList.scrollToBottom(driver);
    new com.wikia.webdriver.elements.mercury.Loading(driver).handleAsyncPageReload();
    Assertion.assertTrue(startingListLength < postsList.getPostsListLength());
  }

}
