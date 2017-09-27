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
import com.wikia.webdriver.elements.mercury.pages.discussions.*;
import org.testng.annotations.Test;

import java.util.function.Function;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_4)
@Test(groups = "discussions-following-post")
public class FollowingPostTests extends NewTestTemplate {

  private static final String SIGN_IN_MODAL_SHOULD_APPEAR = "Sign in/Following modal dialog should appear.";
  private static final String SHOULD_FOLLOW_POST = "User should be able follow post.";
  private static final String SHOULD_UNFOLLOW_POST = "User should be able unfollow post.";
  private static final String DESKTOP = "discussions-following-post-desktop";
  private static final String MOBILE = "discussions-following-post-mobile";

  /**
   * Anonymous user on mobile
   */

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

  /**
   * Anonymous user on desktop
   */

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

  /**
   * User on mobile
   */

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowAndUnfollowPostOnPostsListPage() {
    assertThatPostCanBeFollowedAndUnfollowedOn(data -> new PostsListPage().open());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowAndUnfollowPostOnPostDetailsPage() {
    assertThatPostCanBeFollowedAndUnfollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowAndUnfollowPostOnUserPostsPage() {
    assertThatPostCanBeFollowedAndUnfollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  @Test(groups = MOBILE, enabled = false)
  @RelatedIssue(issueID = "SOC-3674", comment = "Introducing pagination")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowAndFollowPostOnFollowedPostsPage() {
    assertThatPostCanBeUnfollowedAndFollowedOn(new FollowPage());
  }

  /**
   * User on desktop
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanFollowAndUnfollowPostOnPostsListPage() {
    assertThatPostCanBeFollowedAndUnfollowedOn(data -> new PostsListPage().open());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanFollowAndUnfollowPostOnPostDetailsPage() {
    assertThatPostCanBeFollowedAndUnfollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanFollowAndUnfollowPostOnUserPostsPage() {
    assertThatPostCanBeFollowedAndUnfollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  @Test(groups = DESKTOP, enabled = false)
  @RelatedIssue(issueID = "SOC-3674", comment = "Introducing pagination")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnDesktopCanFollowAndUnfollowPostOnFollowedPostsPage() {
    assertThatPostCanBeUnfollowedAndFollowedOn(new FollowPage());
  }

  /**
   *   Discussions Administrator on mobile
   */

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanFollowAndUnfollowPostOnReportedPostsPage() {
    PostEntity.Data data = createAndReportPostAsUserRemotely();
    ReportedPostsAndRepliesPage page = new ReportedPostsAndRepliesPage().open();
    followPostOnPageAndCheckIfFollowedAfterPageRefresh(page, data);
    followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(page, data);
  }

  /**
   * Discussions Administrator on desktop
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void discussionsAdministratorOnDesktopCanFollowAndUnfollowPostOnReportedPostsPage() {
    PostEntity.Data data = createAndReportPostAsUserRemotely();
    ReportedPostsAndRepliesPage page = new ReportedPostsAndRepliesPage().open();
    followPostOnPageAndCheckIfFollowedAfterPageRefresh(page, data);
    followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(page, data);
  }

  /**
   * helper methods
   */

  private void assertThatAnonymousUserCannotFollowPostOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();
    final PageWithPosts page = navigator.apply(data);
    clickFollowOn(page, data);

    Assertion.assertEquals(page.getSignInToFollowModalDialog().getText(),
      SignInToFollowModalDialog.FOLLOW_DISCUSSION_TEXT, SIGN_IN_MODAL_SHOULD_APPEAR);
    Assertion.assertFalse(new PostDetailsPage().open(data.getId()).isPostFollowed(), SHOULD_UNFOLLOW_POST);
  }

  private void clickFollowOn(PageWithPosts page, PostEntity.Data data) {
    page.getPostById(data.getId()).ifPresent(PostEntity::clickFollow);
  }

  private void assertThatPostCanBeFollowedAndUnfollowedOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();
    followPostOnPageAndCheckIfFollowedAfterPageRefresh(navigator.apply(data), data);
    followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(navigator.apply(data), data);
  }

  private void assertThatPostCanBeUnfollowedAndFollowedOn(PageWithPosts page) {
    final PostEntity.Data data = createPostAsUserRemotely();
    followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(page.open(), data);
    followPostOnPageAndCheckIfFollowedAfterPageRefresh(page.open(), data);
  }

  private void followPostOnPageAndCheckIfFollowedAfterPageRefresh(PageWithPosts page, PostEntity.Data data) {
    clickFollowOn(page, data);
    Assertion.assertTrue(new PostDetailsPage().open(data.getId()).isPostFollowed(), SHOULD_FOLLOW_POST);
  }

  private void followPostOnPageAndCheckIfNotFollowedAfterPageRefresh(PageWithPosts page, PostEntity.Data data) {
    clickFollowOn(page, data);
    Assertion.assertFalse(new PostDetailsPage().open(data.getId()).isPostFollowed(), SHOULD_UNFOLLOW_POST);
  }

  private PostEntity.Data createPostAsUserRemotely() {
    return DiscussionsClient.using(User.USER, driver).createPostWithUniqueData();
  }

  private PostEntity.Data createAndReportPostAsUserRemotely() {
    final PostEntity.Data data = createPostAsUserRemotely();
    DiscussionsClient.using(User.USER, driver).reportPost(data);
    return data;
  }
}
