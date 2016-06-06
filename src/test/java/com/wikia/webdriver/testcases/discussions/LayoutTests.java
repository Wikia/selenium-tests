package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Reply;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class LayoutTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = "discussions-anonUserOnMobileCanSeePostsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = "discussions-anonUserOnMobileCanViewMorePosts")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanViewMorePosts() {
    userCanViewMorePosts();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanSeePostDetailsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanSeePostsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanViewMorePosts")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanViewMorePosts() {
    userCanViewMorePosts();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanSeePostsList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUserOnDesktopCanSeePostsList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = "discussions-loggedInUserOnDesktopCanSeePostDetailsList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void loggedInUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void postDetailsListLoads() {
    Reply reply = new PostDetailsPage().open().getReply();

    Assertion.assertFalse(reply.isPostDetailsListEmpty());
  }

  private void postsListLoads() {
    Post post = new PostsListPage().open().getPost();

    Assertion.assertFalse(post.isPostListEmpty());
  }

  private void userCanViewMorePosts() {
    Post post = new PostsListPage().open().getPost();
    int startingListLength = post.getPostsListLength();
    post.scrollToBottom(driver);
    new Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(startingListLength < post.getPostsListLength());
  }
}
