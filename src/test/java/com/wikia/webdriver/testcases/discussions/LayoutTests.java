package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Reply;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_1)
@Test(groups = {"discussions-layout"})
public class LayoutTests extends NewTestTemplate {

  private PostEntity.Data existingPost;

  @BeforeSuite
  private void setUp() {
    String siteId = Utils.excractSiteIdFromWikiName(MercuryWikis.DISCUSSIONS_5);
    existingPost = DiscussionsClient
      .using(User.USER_4, driver)
      .createPostWithUniqueData(siteId);
  }

  /**
   * ANONS ON MOBILE SECTION
   */

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanViewMorePosts() {
    userCanViewMorePosts();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanViewMorePosts")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanViewMorePosts() {
    userCanViewMorePosts();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void loggedInUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void loggedInUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void postDetailsListLoads() {
    Reply reply = new PostDetailsPage().open(existingPost.getId()).getReply();

    Assertion.assertFalse(reply.isPostDetailsListEmpty());
  }

  private void postsListLoads() {
    Post post = new PostsListPage().open().getPost();

    Assertion.assertFalse(post.isPostListEmpty());
  }

  private void userCanViewMorePosts() {
    Post post = new PostsListPage().open().getPost();
    int startingListLength = post.getPostsListLength();
    post.clickLoadMore();
    new Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(startingListLength < post.getPostsListLength());
  }
}
