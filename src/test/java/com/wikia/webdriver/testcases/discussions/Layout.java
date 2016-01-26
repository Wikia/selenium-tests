package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.old.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostDetailsPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

/**
 * @ownership Social Wikia
 */
@Execute(onWikia = MercuryWikis.MEDIAWIKI_119)
public class Layout extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-Layout-1")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = "discussions-Layout-2")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = "discussions-Layout-3")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanViewMorePosts() {
    userCanViewMorePosts();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-Layout-4")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = "discussions-Layout-5")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = "discussions-Layout-6")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanViewMorePosts() {
    userCanViewMorePosts();
  }
  
  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-Layout-7")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = "discussions-Layout-8")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostsList() {
    postsListLoads();
  }


  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-Layout-9")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = "discussions-Layout-10")
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
    new BasePageObject(driver).waitForLoadingOverlayToDisappear();
    Assertion.assertTrue(startingListLength < postsList.getPostsListLength());
  }

}
