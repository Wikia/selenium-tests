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
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.ReportedPostsAndRepliesPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-reporting-posts")
public class ReportingPostTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  private static final String NO_REPORT_POST_OPTION_MESSAGE = "'Report Post' option in 'More Options' should not be available.";

  private static final String REPORTED_INDICATOR_ON_POST_MESSAGE = "Reported indicator on post should be visible.";

  private static final String NO_REPORTED_INDICATOR_ON_POST_MESSAGE = "Reported indicator on post should not be visible.";

  private static final String CAN_REPORT_POST_MESSAGE = "User should be able to report post.";

  private static final String REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE = "User should not see reported indicator on posts which were not reported by them.";

  private static final String DELETED_POST_MESSAGE = "Post should be deleted.";

  private static final String ANON_NOT_VISIBLE_DELETED_POST_MESSAGE = "Anonymous user should not see deleted post.";

  private static final String NOT_VISIBLE_DELETED_POST_MESSAGE = "User should not see deleted post.";

  public static final String DISCUSSIONS_MODERATOR_SHOULD_SEE_RE_REPORTED_POST = "Discussions moderator should see re reported post.";

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostsListPage() {
    PostsListPage page = openPostsListPage();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage page = new PostDetailsPage().openDefaultPost();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnUserPostsPage() {
    UserPostsPage page = new UserPostsPage().openDefaultUserPage();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostsListPage() {
    createAndReportPostRemotelyAsFirstUser();

    PostsListPage page = openPostsListPage();
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeDeletedPostOnPostsListPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeDeletedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertTrue(page.getErrorMessages().isErrorMessagePresent(), ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeDeletedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostsListPage() {
    PostsListPage page = openPostsListPage();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage page = new PostDetailsPage().openDefaultPost();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }


  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnUserPostsPage() {
    UserPostsPage page = new UserPostsPage().openDefaultUserPage();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostsListPage() {
    createAndReportPostRemotelyAsFirstUser();

    PostsListPage page = openPostsListPage();
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeReportedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeDeletedPostOnPostsListPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeDeletedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertTrue(page.getErrorMessages().isErrorMessagePresent(), ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeDeletedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  // User on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostsListPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();

    PostsListPage page = openPostsListPage();
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostDetailsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnUserPostsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotReReportPostOnPostsListPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    PostEntity postEntity = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotReReportPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotReReportPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    PostEntity postEntity = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeDeletedPostOnPostsListPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeDeletedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertTrue(page.getErrorMessages().isErrorMessagePresent(), NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeDeletedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  // Second user on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PageWithPosts page = openPostsListPage();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PageWithPosts page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PageWithPosts page = new UserPostsPage().open(data.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Third user on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnPostsListPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostEntity postEntity = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostEntity postEntity = new PostDetailsPage().open(data.getId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostEntity postEntity = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Discussions moderator on mobile

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanSeeReportedPostOnPostListPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    final PostEntity postEntity = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertTrue(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanApproveReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnValidatePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(clickApproveOnValidatePostModalDialog(page, postEntity), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanSeeReportedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    final PostEntity postEntity = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertTrue(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanNotSeeApprovedPostOnReportedPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    PostEntity post = new ReportedPostsAndRepliesPage().open().getPost().findPostById(data.getId());
    Assertion.assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanSeeReReportedPostOnReportedPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);
    reportPostRemotelyAsSecondUser(data);

    PostEntity post = new ReportedPostsAndRepliesPage().open().getPost().findPostById(data.getId());
    Assertion.assertNotNull(post, DISCUSSIONS_MODERATOR_SHOULD_SEE_RE_REPORTED_POST);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanDeleteReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);
    reportPostRemotelyAsThirdUser(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnDeletePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickApproveOnDeletePostModalDialog(page, postEntity), DELETED_POST_MESSAGE);
  }

  // User on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostsListPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();

    PostsListPage page = openPostsListPage();
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostDetailsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnUserPostsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotReReportPostOnPostsListPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    PostEntity postEntity = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotReReportPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotReReportPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    PostEntity postEntity = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotSeeDeletedPostOnPostsListPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotSeeDeletedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertTrue(page.getErrorMessages().isErrorMessagePresent(), NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanNotSeeDeletedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportAndDeletePostRemotely();

    PostEntity post = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  // Second user on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PageWithPosts page = openPostsListPage();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PageWithPosts page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();

    PageWithPosts page = new UserPostsPage().open(data.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Third user on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportApprovedPostOnPostsListPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostEntity postEntity = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportApprovedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostEntity postEntity = new PostDetailsPage().open(data.getId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportApprovedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostEntity postEntity = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Discussions moderator on desktop

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanSeeReportedPostOnPostListPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    final PostEntity postEntity = openPostsListPage().getPost().findPostById(data.getId());
    Assertion.assertTrue(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanApproveReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnValidatePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(clickApproveOnValidatePostModalDialog(page, postEntity), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanSeeReportedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    final PostEntity postEntity = new UserPostsPage().open(data.getAuthorId()).getPost().findPostById(data.getId());
    Assertion.assertTrue(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanNotSeeApprovedPostOnReportedPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    PostEntity post = new ReportedPostsAndRepliesPage().open().getPost().findPostById(data.getId());
    Assertion.assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanSeeReReportedPostOnReportedPostsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);
    reportPostRemotelyAsSecondUser(data);

    PostEntity post = new ReportedPostsAndRepliesPage().open().getPost().findPostById(data.getId());
    Assertion.assertNotNull(post, DISCUSSIONS_MODERATOR_SHOULD_SEE_RE_REPORTED_POST);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanDeleteReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);
    reportPostRemotelyAsThirdUser(data);

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnDeletePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickApproveOnDeletePostModalDialog(page, postEntity), DELETED_POST_MESSAGE);
  }

  private PostsListPage openPostsListPage() {
    return new PostsListPage().open();
  }

  private PostEntity.Data cretePostRemotelyAsFirstUser() {
    return DiscussionsOperations.using(User.USER, driver).createPostWithUniqueData();
  }
  private PostEntity.Data createAndReportPostRemotelyAsFirstUser() {
    DiscussionsOperations operations = DiscussionsOperations.using(User.USER, driver);
    final PostEntity.Data data = operations.createPostWithUniqueData();
    operations.reportPost(data);
    return data;
  }

  private void reportPostRemotelyAsSecondUser(PostEntity.Data data) {
    DiscussionsOperations.using(User.USER_2, driver).reportPost(data);
  }

  private void validatePostRemotelyAsDiscussionsModerator(PostEntity.Data data) {
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).validatePost(data);
  }

  private void reportPostRemotelyAsThirdUser(PostEntity.Data data) {
    DiscussionsOperations.using(User.USER_3, driver).reportPost(data);
  }

  private void deletePostRemotelyAsDiscussionsModerator(PostEntity.Data data) {
    DiscussionsOperations.using(User.DISCUSSIONS_MODERATOR, driver).deletePost(data);
  }

  private PostEntity.Data createAndReportAndDeletePostRemotely() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);
    reportPostRemotelyAsThirdUser(data);
    deletePostRemotelyAsDiscussionsModerator(data);
    return data;
  }

  private boolean isReportPostOptionAvailableOn(final PageWithPosts page) {
    return isReportPostOptionAvailableFor(page.getPost().findNewestPost());
  }

  private boolean postHasReportedIndicator(final PageWithPosts pageWithPosts) {
    return !pageWithPosts.getPost().getReportedPosts().isEmpty();
  }

  private boolean postCanBeReported(final PostEntity postEntity) {
    reportPost(postEntity);

    return isReported(postEntity) && !isReportPostOptionAvailableFor(postEntity);
  }

  private void reportPost(final PostEntity postEntity) {
    postEntity.clickMoreOptions()
        .clickReportPostOption();
  }

  private boolean isReported(final PostEntity postEntity) {
    return postEntity.hasTopNote() && postEntity.isReported();
  }

  private boolean isReportPostOptionAvailableFor(final PostEntity postEntity) {
    return postEntity.clickMoreOptions().hasReportPostOption();
  }

  private boolean clickCancelOnValidatePostModalDialog(final PostDetailsPage page, final PostEntity postEntity) {
    postEntity.findTopNote()
        .clickValidate();

    page.getTopNoteModalDialog()
        .clickCancel();

    return isReported(postEntity);
  }

  private boolean clickApproveOnValidatePostModalDialog(final PostDetailsPage page, final PostEntity postEntity) {
    postEntity.findTopNote()
        .clickValidate();

    page.getTopNoteModalDialog()
        .clickApprove();

    return postEntity.isReported();
  }

  private boolean clickCancelOnDeletePostModalDialog(final PostDetailsPage page, final PostEntity postEntity) {
    postEntity.findTopNote()
        .clickDelete();

    page.getTopNoteModalDialog()
        .clickCancel();

    return isReported(postEntity);
  }

  private boolean clickApproveOnDeletePostModalDialog(final PostDetailsPage page, final PostEntity postEntity) {
    postEntity.findTopNote()
        .clickDelete();

    page.getTopNoteModalDialog()
        .clickApprove();

    return postEntity.isDeleted();
  }
}
