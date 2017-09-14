package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.SignInToFollowModalDialog;
import com.wikia.webdriver.elements.mercury.pages.discussions.AvailablePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.FollowPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.ReportedPostsAndRepliesPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.Test;

import java.util.function.Function;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_1)
@Test(groups = "discussions-following-post")
public class FollowingPostTests extends NewTestTemplate {

  private static final String SIGN_IN_MODAL_SHOULD_APPEAR = "Sign in/Following modal dialog should appear.";
  private static final String SHOULD_FOLLOW_POST = "User should be able follow post.";
  private static final String SHOULD_UNFOLLOW_POST = "User should be able unfollow post.";
  private static final String DESKTOP = "discussions-following-post-desktop";
  private static final String MOBILE = "discussions-following-post-mobile";

  // Anonymous user on mobile

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnPostsListPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostsListPage().open());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnPostDetailsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnUserPostsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  // Anonymous user on desktop

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonymousUserOnDesktopCanNotFollowPostOnPostsListPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostsListPage().open());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonymousUserOnDesktopCanNotFollowPostOnPostDetailsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonymousUserOnDesktopCanNotFollowPostOnUserPostsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  // User on mobile

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnPostsListPage() {
    assertThatPostCanBeFollowedOn(data -> new PostsListPage().open());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnPostDetailsPage() {
    assertThatPostCanBeFollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnUserPostsPage() {
    assertThatPostCanBeFollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnPostsListPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostsListPage().open());
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnPostDetailsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnUserPostsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * By default all posts on "Followed" tab are followed.
   */
  @Test(groups = MOBILE, enabled = false)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "SOC-3674", comment = "Introducing pagination")
  public void userOnMobileCanFollowAndUnfollowPostOnFollowedPostsPage() {
    createPostAsUserRemotely();
    final FollowPage page = FollowPage.open();

    followPostOnPageAndCheckIfFollowedAfterPageRefresh(page);
  }

  // User on desktop

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanFollowPostOnPostsListPage() {
    assertThatPostCanBeFollowedOn(data -> new PostsListPage().open());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanFollowPostOnPostDetailsPage() {
    assertThatPostCanBeFollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanFollowPostOnUserPostsPage() {
    assertThatPostCanBeFollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanUnfollowPostOnPostsListPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostsListPage().open());
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanUnfollowPostOnPostDetailsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanUnfollowPostOnUserPostsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * By default all posts on "Followed" tab are followed.
   */
  @Test(groups = DESKTOP, enabled = false)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "SOC-3674", comment = "Introducing pagination")
  public void userOnDesktopCanFollowAndUnfollowPostOnFollowedPostsPage() {
    createPostAsUserRemotely();
    final FollowPage page = FollowPage.open();

    followPostOnPageAndCheckIfFollowedAfterPageRefresh(page);
  }

  // Discussions Administrator on mobile

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanFollowPostOnReportedPostsPage() {
    createAndReportPostAsUserRemotely();
    ReportedPostsAndRepliesPage page = new ReportedPostsAndRepliesPage().open();
    followPostOnPageAndCheckIfFollowedAfterPageRefresh(page);
  }

  private void followPostOnPageAndCheckIfFollowedAfterPageRefresh(PageWithPosts page) {
    clickFollowOn(page);
    String postId = page.getPost().findNewestPost().findId();
    Assertion.assertTrue(new PostDetailsPage().open(postId).isPostFollowed(), SHOULD_UNFOLLOW_POST);
  }

  private void followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(PageWithPosts page) {
    clickFollowOn(page);
    String postId = page.getPost().findNewestPost().findId();
    Assertion.assertFalse(new PostDetailsPage().open(postId).isPostFollowed(), SHOULD_UNFOLLOW_POST);
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanUnfollowPostOnReportedListPage() {
    final PostEntity.Data data = DiscussionsClient.using(User.DISCUSSIONS_ADMINISTRATOR, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.USER, driver).reportPost(data);
    ReportedPostsAndRepliesPage page = new ReportedPostsAndRepliesPage().open();
    followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(page);
  }

  // Discussions Administrator on desktop

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsAdministratorOnDesktopCanFollowPostOnReportedPostsPage() {
    createAndReportPostAsUserRemotely();
    ReportedPostsAndRepliesPage page = new ReportedPostsAndRepliesPage().open();
    clickFollowOn(page);
    String postId = page.getPost().findNewestPost().findId();
    Assertion.assertTrue(new PostDetailsPage().open(postId).isPostFollowed(), SHOULD_UNFOLLOW_POST);
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsAdministratorOnDesktopCanUnfollowPostOnReportedListPage() {
    final PostEntity.Data data = DiscussionsClient.using(User.DISCUSSIONS_ADMINISTRATOR, driver).createPostWithUniqueData();
    DiscussionsClient.using(User.USER, driver).reportPost(data);
    ReportedPostsAndRepliesPage page = new ReportedPostsAndRepliesPage().open();
    followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(page);
  }

  // Test methods

  private void assertThatAnonymousUserCannotFollowPostOn(Function<PostEntity.Data, AvailablePage> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    final AvailablePage page = navigator.apply(data);
    clickFollowOn(page);

    Assertion.assertEquals(page.getSignInToFollowModalDialog().getText(),
      SignInToFollowModalDialog.FOLLOW_DISCUSSION_TEXT, SIGN_IN_MODAL_SHOULD_APPEAR);
    Assertion.assertFalse(new PostDetailsPage().open(data.getId()).isPostFollowed());
  }

  private PostEntity.Data createPostAsUserRemotely() {
    return DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
  }

  private void clickFollowOn(PageWithPosts page) {
    page.getPost().clickFollowFirstPost();
  }

  private void assertThatPostCanBeFollowedOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    clickFollowOn(navigator.apply(data));
    Assertion.assertTrue(new PostDetailsPage().open(data.getId()).isPostFollowed(), SHOULD_FOLLOW_POST);
  }

  private void assertThatPostCanBeUnfollowedOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    clickFollowOn(navigator.apply(data));
    Assertion.assertFalse(new PostDetailsPage().open(data.getId()).isPostFollowed(), SHOULD_UNFOLLOW_POST);
  }

  private void createAndReportPostAsUserRemotely() {
    final PostEntity.Data data = createPostAsUserRemotely();
    DiscussionsClient.using(User.USER, driver).reportPost(data);
  }
}
