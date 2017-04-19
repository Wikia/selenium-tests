package com.wikia.webdriver.testcases.discussions;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
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

import java.util.function.Function;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_1)
@Test(groups = "discussions-following-post")
public class FollowingPostTests extends NewTestTemplate {

  private static final String SIGN_IN_MODAL_SHOULD_APPEAR = "Sign in/Following modal dialog should appear.";

  private static final String MODAL_SHOULD_NOT_BE_VISIBLE = "Sign in/Following modal dialog should not be visible after clicking ok button.";

  private static final String SHOULD_FOLLOW_POST = "User should be able follow post.";

  private static final String SHOULD_UNFOLLOW_POST = "User should be able unfollow post.";

  // Anonymous user on mobile

  @Test(groups = "discussions-anonymousUserMobileFollowingPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnPostsListPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostsListPage().open());
  }

  @Test(groups = "discussions-anonymousUserMobileFollowingPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnPostDetailsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = "discussions-anonymousUserMobileFollowingPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonymousUserOnMobileCanNotFollowPostOnUserPostsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonymousUserDesktopFollowingPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotFollowPostOnPostsListPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostsListPage().open());
  }

  @Test(groups = "discussions-anonymousUserDesktopFollowingPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotFollowPostOnPostDetailsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = "discussions-anonymousUserDesktopFollowingPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonymousUserOnDesktopCanNotFollowPostOnUserPostsPage() {
    assertThatAnonymousUserCannotFollowPostOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  // User on mobile

  @Test(groups = "discussions-userMobileFollowingPost")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnPostsListPage() {
    assertThatPostCanBeFollowedOn(data -> new PostsListPage().open());
  }

  @Test(groups = "discussions-userMobileFollowingPost")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnPostDetailsPage() {
    assertThatPostCanBeFollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = "discussions-userMobileFollowingPost")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanFollowPostOnUserPostsPage() {
    assertThatPostCanBeFollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userMobileFollowingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnPostsListPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostsListPage().open());
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userMobileFollowingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnPostDetailsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userMobileFollowingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanUnfollowPostOnUserPostsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * By default all posts on "Followed" tab are followed.
   */
  @Test(groups = "discussions-userMobileFollowingPost", enabled = false)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "SOC-3674", comment = "Introducing pagination")
  public void userOnMobileCanFollowAndUnfollowPostOnFollowedPostsPage() {
    createPostAsUserRemotely();
    final FollowPage page = FollowPage.open();

    final PostActionsRow postActions = clickUnfollowOn(page);
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_POST);

    clickFollowOn(page);
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  // User on desktop

  @Test(groups = "discussions-userDesktopFollowingPost")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanFollowPostOnPostsListPage() {
    assertThatPostCanBeFollowedOn(data -> new PostsListPage().open());
  }

  @Test(groups = "discussions-userDesktopFollowingPost")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanFollowPostOnPostDetailsPage() {
    assertThatPostCanBeFollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  @Test(groups = "discussions-userDesktopFollowingPost")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanFollowPostOnUserPostsPage() {
    assertThatPostCanBeFollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userDesktopFollowingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanUnfollowPostOnPostsListPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostsListPage().open());
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userDesktopFollowingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanUnfollowPostOnPostDetailsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new PostDetailsPage().open(data.getId()));
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-userDesktopFollowingPost")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanUnfollowPostOnUserPostsPage() {
    assertThatPostCanBeUnfollowedOn(data -> new UserPostsPage().open(data.getAuthorId()));
  }

  /**
   * By default all posts on "Followed" tab are followed.
   */
  @Test(groups = "discussions-userDesktopFollowingPost", enabled = false)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "SOC-3674", comment = "Introducing pagination")
  public void userOnDesktopCanFollowAndUnfollowPostOnFollowedPostsPage() {
    createPostAsUserRemotely();
    final FollowPage page = FollowPage.open();

    final PostActionsRow postActions = clickUnfollowOn(page);
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_POST);

    clickFollowOn(page);
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  // Discussions Administrator on mobile

  @Test(groups = "discussions-discussionsAdministratorMobileFollowingPost")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanFollowPostOnReportedPostsPage() {
    createAndReportPostAsUserRemotely();

    final PostActionsRow postActions = clickFollowOn(new ReportedPostsAndRepliesPage().open());
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-discussionsAdministratorMobileFollowingPost")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void discussionsAdministratorOnMobileCanUnfollowPostOnReportedListPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).createPostWithUniqueData();
    DiscussionsOperations.using(User.USER, driver).reportPost(data);

    final PostActionsRow postActions = clickUnfollowOn(new ReportedPostsAndRepliesPage().open());
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_POST);
  }

  // Discussions Administrator on desktop

  @Test(groups = "discussions-discussionsAdministratorDesktopFollowingPost")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanFollowPostOnReportedPostsPage() {
    createAndReportPostAsUserRemotely();

    final PostActionsRow postActions = clickFollowOn(new ReportedPostsAndRepliesPage().open());
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  /**
   * Post created by user is automatically followed.
   */
  @Test(groups = "discussions-discussionsAdministratorDesktopFollowingPost")
  @Execute(asUser = User.DISCUSSIONS_ADMINISTRATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void discussionsAdministratorOnDesktopCanUnfollowPostOnReportedListPage() {
    final PostEntity.Data data = DiscussionsOperations.using(User.DISCUSSIONS_ADMINISTRATOR, driver).createPostWithUniqueData();
    DiscussionsOperations.using(User.USER, driver).reportPost(data);

    final PostActionsRow postActions = clickUnfollowOn(new ReportedPostsAndRepliesPage().open());
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
    return DiscussionsOperations.using(User.USER, driver).createPostWithUniqueData();
  }

  private PostActionsRow clickUnfollowOn(PageWithPosts page) {
    return clickFollowOn(page);
  }

  private PostActionsRow clickFollowOn(PageWithPosts page) {
    return page.getPost().findNewestPost().findPostActions()
        .clickFollow();
  }

  private void assertThatPostCanBeFollowedOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    final PostActionsRow postActions = clickFollowOn(navigator.apply(data));
    Assertion.assertTrue(postActions.isFollowed(), SHOULD_FOLLOW_POST);
  }

  private void assertThatPostCanBeUnfollowedOn(Function<PostEntity.Data, PageWithPosts> navigator) {
    final PostEntity.Data data = createPostAsUserRemotely();

    final PostActionsRow postActions = clickUnfollowOn(navigator.apply(data));
    Assertion.assertFalse(postActions.isFollowed(), SHOULD_UNFOLLOW_POST);
  }

  private void createAndReportPostAsUserRemotely() {
    final PostEntity.Data data = createPostAsUserRemotely();
    DiscussionsOperations.using(User.USER, driver).reportPost(data);
  }
}
