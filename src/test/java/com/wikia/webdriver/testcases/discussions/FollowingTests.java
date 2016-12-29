package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostActionsRow;
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

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-following")
public class FollowingTests extends NewTestTemplate {

  private static final String SIGN_IN_MODAL_SHOULD_APPEAR = "Sign in/Following modal dialog should appear.";

  private static final String MODAL_SHOULD_NOT_BE_VISIBLE = "Sign in/Following modal dialog should not be visible after clicking ok button.";

  private static final String SHOULD_FOLLOW_POST = "User should be able follow post.";


  private static final String SHOULD_UNFOLLOW_POST = "User should be able unfollow post.";

  // Anonymous user on mobile

  @Test(groups = "discussions-anonymousUserMobileFollowing")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnPostsListPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostsListPage().open());
  }

  @Test(groups = "discussions-anonymousUserMobileFollowing")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnPostDetailsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = "discussions-anonymousUserMobileFollowing")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnUserPostsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonymousUserDesktopFollowing")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotFollowPostOnPostsListPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostsListPage().open());
  }

  @Test(groups = "discussions-anonymousUserDesktopFollowing")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotFollowPostOnPostDetailsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = "discussions-anonymousUserDesktopFollowing")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DiscussionsConstants.DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotFollowPostOnUserPostsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  // User on mobile

  @Test(groups = "discussions-userMobileFollowing")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnPostsListPage() {
    assertThatPostCanBeFollowedOn(data -> new PostsListPage().open());
  }

  @Test(groups = "discussions-userMobileFollowing")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnPostDetailsPage() {
    assertThatPostCanBeFollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = "discussions-userMobileFollowing")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnUserPostsPage() {
    assertThatPostCanBeFollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userMobileFollowing")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnPostsListPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostsListPage().open());
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userMobileFollowing")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnPostDetailsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userMobileFollowing")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnUserPostsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * By default all posts on "Followed" tab are followed.
   */
  @Test(groups = "discussions-userMobileFollowing")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowAndUnfollowPostOnFollowedPostsPage() {
    createPostAsUserRemotely();
    final FollowPage page = FollowPage.open();

    final PostActionsRow postActions = clickUnfollowOn(page);
    sleepForOneSecond();
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_POST);

    clickFollowOn(page);
    sleepForOneSecond();
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  // Discussions Administrator on mobile

  @Test(groups = "discussions-discussionsAdministratorMobileFollowing")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanFollowPostOnReportedPostsPage() {
    final PostEntity.Data data = createPostAsUserRemotely();
    DiscussionsOperations.using(User.USER, driver).reportPost(data);

    final PostActionsRow postActions = clickFollowOn(new ReportedPostsAndRepliesPage().open());
    sleepForOneSecond();
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-discussionsAdministratorMobileFollowing")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanUnfollowPostOnReportedListPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).cratePostWithUniqueData();
    DiscussionsOperations.using(User.USER, driver).reportPost(data);

    final PostActionsRow postActions = clickUnfollowOn(new ReportedPostsAndRepliesPage().open());
    sleepForOneSecond();
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_POST);
  }

  // Test methods

  private void assertThatAnonymousUserCannotFollowPostOn(Function<PostEntity.Data, AvailablePage> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    final AvailablePage page = navigator.apply(data);
    page.getPost().findNewestPost().findPostActions().clickFollow();

    final SignInToFollowModalDialog modalDialog = page.getSignInToFollowModalDialog();
    Assertion.assertEquals(modalDialog.getText(), SignInToFollowModalDialog.FOLLOW_DISCUSSION_TEXT, SIGN_IN_MODAL_SHOULD_APPEAR);
    modalDialog.clickOkButton();
    Assertion.assertFalse(modalDialog.isVisible(), MODAL_SHOULD_NOT_BE_VISIBLE);
  }

  private PostEntity.Data createPostAsUserRemotely() {
    return DiscussionsOperations.using(User.USER, driver).cratePostWithUniqueData();
  }

  private PostActionsRow clickUnfollowOn(PageWithPosts page) {
    return page.getPost().findNewestPost().findPostActions()
        .clickFollow();
  }

  private PostActionsRow clickFollowOn(PageWithPosts page) {
    return page.getPost().findNewestPost().findPostActions()
        .clickFollow();
  }

  private void assertThatPostCanBeFollowedOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    final PostActionsRow postActions = clickFollowOn(navigator.apply(data));
    sleepForOneSecond();
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  private void assertThatPostCanBeUnfollowedOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    final PostActionsRow postActions = clickUnfollowOn(navigator.apply(data));
    sleepForOneSecond();
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_POST);
  }

  /**
   * Because follow may not succeed this test should wait at least 1 second to check if "followed" flag on post
   * did not change to "not followed" state. 1 second is sufficient for happy path scenario.
   */
  private void sleepForOneSecond() {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException x) {
      // ignore this exception
    }
  }
}
