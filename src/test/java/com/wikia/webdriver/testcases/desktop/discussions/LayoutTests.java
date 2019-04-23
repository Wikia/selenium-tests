package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.Post;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.Reply;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.PostsListPage;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@Test(groups = {"discussions-layout"})
@Execute(onWikia = "qadiscussions", language = "de")
public class LayoutTests extends NewTestTemplate {

  private PostEntity.Data existingPost;

  @BeforeSuite
  private void setUp() {
    String siteId = Utils.extractSiteIdFromWikiName("qadiscussions", "de");
    existingPost = DiscussionsClient
      .using(User.USER_4, driver)
      .createPostWithUniqueData(siteId);
  }

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanViewMorePosts() {
    userCanViewMorePosts();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test
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

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void loggedInUserOnMobileCanSeePostsList() {
    postsListLoads();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void loggedInUserOnDesktopCanSeePostsList() {
    postsListLoads();
  }

  @Test
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
    if(startingListLength < 20) {
      throw new SkipException("Skipping test because the condition of minimum 20 posts not met");
    }
    post.clickLoadMore().waitForLoadingSpinner();

    Assertion.assertTrue(startingListLength < post.getPostsListLength());
  }
}