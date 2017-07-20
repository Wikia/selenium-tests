package com.wikia.webdriver.testcases.discussions;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
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

@Execute(onWikia = MercuryWikis.DISCUSSIONS_1)
@Test(groups = "discussions-reporting-posts")
public class ReportingPostTests extends NewTestTemplate {

  private static final String DISCUSSIONS_MODERATOR_SHOULD_SEE_RE_REPORTED_POST =
      "Discussions moderator should see re reported post.";
  private static final String NO_REPORT_POST_OPTION_MESSAGE =
      "'Report Post' option in 'More Options' should not be available.";
  private static final String REPORTED_INDICATOR_ON_POST_MESSAGE =
      "Reported indicator on post should be visible.";
  private static final String NO_REPORTED_INDICATOR_ON_POST_MESSAGE =
      "Reported indicator on post should not be visible.";
  private static final String CAN_REPORT_POST_MESSAGE = "User should be able to report post.";
  private static final String REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE =
      "User should not see reported indicator on posts which were not reported by them.";
  private static final String DELETED_POST_MESSAGE = "Post should be deleted.";
  private static final String ANON_NOT_VISIBLE_DELETED_POST_MESSAGE =
      "Anonymous user should not see deleted post.";
  private static final String NOT_VISIBLE_DELETED_POST_MESSAGE =
      "User should not see deleted post.";

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostsListPage() {
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostDetailsPage() {
    final PostDetailsPage page = openDefaultPostDetailsWaitingUtilLoaded();
    assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  private PostDetailsPage openDefaultPostDetailsWaitingUtilLoaded() {
    return new PostDetailsPage().openDefaultPost();
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnUserPostsPage() {
    final UserPostsPage page = openDefaultUserPostPageWaiting();
    assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostsListPage() {
    createAndReportPostRemotelyAsFirstUser();
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    final UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeDeletedPostOnPostsListPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostsListPage postsListPage = openPostListPageAndWaitUntilLoaded();
    final PostEntity post = postsListPage.getPost().findPostById(data.getId());
    assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeDeletedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    assertTrue(page.getErrorMessages().isErrorMessagePresent(),
               ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeDeletedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final UserPostsPage open = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity post = open.getPost().findPostById(data.getId());
    assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotReportPostOnPostsListPage() {
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotReportPostOnPostDetailsPage() {
    final PostDetailsPage page = openDefaultPostDetailsWaitingUtilLoaded();
    assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }


  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotReportPostOnUserPostsPage() {
    final UserPostsPage page = openDefaultUserPostPageWaiting();
    assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  private UserPostsPage openDefaultUserPostPageWaiting() {
    return new UserPostsPage().openDefaultUserPage();
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostsListPage() {
    createAndReportPostRemotelyAsFirstUser();
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotSeeReportedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    final UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotSeeDeletedPostOnPostsListPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostEntity
        post =
        openPostListPageAndWaitUntilLoaded().getPost().findPostById(data.getId());
    assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotSeeDeletedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    assertTrue(page.getErrorMessages().isErrorMessagePresent(),
               ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotSeeDeletedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    PostEntity post = page.getPost().findPostById(data.getId());
    assertNull(post, ANON_NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  // User on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostsListPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostDetailsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnUserPostsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();
    final UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }


  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeDeletedPostOnPostsListPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostEntity
        post =
        openPostListPageAndWaitUntilLoaded().getPost().findPostById(data.getId());
    assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeDeletedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    assertTrue(page.getErrorMessages().isErrorMessagePresent(),
               NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanNotSeeDeletedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity post = page.getPost().findPostById(data.getId());
    assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnPostsListPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostEntity postEntity =
        openPostListPageAndWaitUntilLoaded().getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnPostDetailsPage() {
    userCanReportApprovedPostOnPostDetailsPage();
  }

  private void userCanReportApprovedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnUserPostsPage() {
    userCanReportApprovedPostOnUserPostsPage();
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanReportPostOnPostsListPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanReportPostOnPostDetailsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanReportPostOnUserPostsPage() {
    final PostEntity.Data data = cretePostRemotelyAsFirstUser();
    final UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotReReportPostOnPostsListPage() {
    userCannotReReportPostOnPostListPage();
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotReReportPostOnPostsListPage() {
    userCannotReReportPostOnPostListPage();
  }

  private void userCannotReReportPostOnPostListPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotReReportPostOnPostDetailsPage() {
    userCannotReReportPostOnPostDetailPage();
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotReReportPostOnPostDetailsPage() {
    userCannotReReportPostOnPostDetailPage();
  }

  private void userCannotReReportPostOnPostDetailPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotReReportPostOnUserPostsPage() {
    userCannotReReportPostOnUserPostPage();
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotReReportPostOnUserPostsPage() {
    userCannotReReportPostOnUserPostPage();
  }

  private void userCannotReReportPostOnUserPostPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);

    final UserPostsPage userPostsPage = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity postEntity = userPostsPage.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotSeeDeletedPostOnPostsListPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertNull(postEntity, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotSeeDeletedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    assertTrue(page.getErrorMessages().isErrorMessagePresent(),
               NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanNotSeeDeletedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportAndDeletePostRemotely();
    final UserPostsPage userPostsPage = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity post = userPostsPage.getPost().findPostById(data.getId());
    assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost();
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost();
  }

  private void userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    final PageWithPosts page = openPostListPageAndWaitUntilLoaded();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost();
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost();
  }

  private void userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    final PageWithPosts page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost();
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost();
  }

  private void userCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    final PageWithPosts page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Third user on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanReportApprovedPostOnPostsListPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final PostsListPage page = openPostListPageAndWaitUntilLoaded();
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanReportApprovedPostOnPostDetailsPage() {
    userCanReportApprovedPostOnPostDetailsPage();
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER_3)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanReportApprovedPostOnUserPostsPage() {
    userCanReportApprovedPostOnUserPostsPage();
  }

  private void userCanReportApprovedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void moderatorOnDesktopCanSeeReportedPostOnPostListPage() {
    moderatorCanSeeReportedPostOnPostListPage();
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanSeeReportedPostOnPostListPage() {
    moderatorCanSeeReportedPostOnPostListPage();
  }

  private void moderatorCanSeeReportedPostOnPostListPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    final PostsListPage postsListPage = openPostListPageAndWaitUntilLoaded();
    final PostEntity postEntity = postsListPage.getPost().findPostById(data.getId());
    assertTrue(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void moderatorOnDesktopCanApproveReportedPostOnPostDetailsPage() {
    moderatorCanApproveReportedPostOnPostDetailsPage();
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanApproveReportedPostOnPostDetailsPage() {
    moderatorCanApproveReportedPostOnPostDetailsPage();
  }

  private void moderatorCanApproveReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);

    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    assertTrue(clickCancelOnValidatePostModalDialog(page, postEntity),
            REPORTED_INDICATOR_ON_POST_MESSAGE);
    assertFalse(clickApproveOnValidatePostModalDialog(page, postEntity),
            NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void moderatorOnDesktopCanSeeReportedPostOnUserPostsPage() {
    moderatorCanSeeReportedPostOnUserPostsPage();
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanSeeReportedPostOnUserPostsPage() {
    moderatorCanSeeReportedPostOnUserPostsPage();
  }

  private void moderatorCanSeeReportedPostOnUserPostsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    final UserPostsPage page = openUserPostsAndWaitUntilLoaded(data.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanNotSeeApprovedPostOnReportedPostsPage() {
    moderatorCanNotSeeApprovedPostOnReportedPostsPage();
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void moderatorOnDesktopCanNotSeeApprovedPostOnReportedPostsPage() {
    moderatorCanNotSeeApprovedPostOnReportedPostsPage();
  }

  private void moderatorCanNotSeeApprovedPostOnReportedPostsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);

    final ReportedPostsAndRepliesPage page = new ReportedPostsAndRepliesPage().open();
    final PostEntity post = page.getPost().findPostById(data.getId());
    assertNull(post, NOT_VISIBLE_DELETED_POST_MESSAGE);
  }


  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanSeeReReportedPostOnReportedPostsPage() {
    moderatorCanSeeReReportedPostOnReportedPostsPage();
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void moderatorOnDesktopCanSeeReReportedPostOnReportedPostsPage() {
    moderatorCanSeeReReportedPostOnReportedPostsPage();
  }

  private void moderatorCanSeeReReportedPostOnReportedPostsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    validatePostRemotelyAsDiscussionsModerator(data);
    reportPostRemotelyAsSecondUser(data);

    ReportedPostsAndRepliesPage open = new ReportedPostsAndRepliesPage().open();
    final PostEntity post = open.getPost().findPostById(data.getId());
    assertNotNull(post, DISCUSSIONS_MODERATOR_SHOULD_SEE_RE_REPORTED_POST);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanDeleteReportedPostOnPostDetailsPage() {
    moderatorCanDeleteReportedPostOnPostDetailsPage();
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void moderatorOnDesktopCanDeleteReportedPostOnPostDetailsPage() {
    moderatorCanDeleteReportedPostOnPostDetailsPage();
  }

  private void moderatorCanDeleteReportedPostOnPostDetailsPage() {
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
    reportPostRemotelyAsSecondUser(data);
    validatePostRemotelyAsDiscussionsModerator(data);
    reportPostRemotelyAsThirdUser(data);

    final PostDetailsPage page = openPostDetailsPageAndWaitUntilLoaded(data.getId());
    final PostEntity postEntity = page.getPost().findPostById(data.getId());
    assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    assertTrue(clickCancelOnDeletePostModalDialog(page, postEntity),
            REPORTED_INDICATOR_ON_POST_MESSAGE);
    assertTrue(clickApproveOnDeletePostModalDialog(page, postEntity), DELETED_POST_MESSAGE);
  }

  private UserPostsPage openUserPostsAndWaitUntilLoaded(String authorId) {
    final UserPostsPage post = new UserPostsPage().open(authorId);
    post.waitForPageLoad();
    return post;
  }

  private PostDetailsPage openPostDetailsPageAndWaitUntilLoaded(String postId) {
    final PostDetailsPage post = new PostDetailsPage().open(postId);
    post.waitForPageLoad();
    return post;
  }

  private PostsListPage openPostListPageAndWaitUntilLoaded() {
    final PostsListPage post = new PostsListPage().open();
    post.waitForPageLoad();
    return post;
  }

  private PostEntity.Data cretePostRemotelyAsFirstUser() {
    return DiscussionsOperations.using(User.USER, driver).createPostWithUniqueData();
  }

  private PostEntity.Data createAndReportPostRemotelyAsFirstUser() {
    final DiscussionsOperations operations = DiscussionsOperations.using(User.USER, driver);
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
    final PostEntity.Data data = createAndReportPostRemotelyAsFirstUser();
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
    postEntity.clickMoreOptions().clickReportPostOption();
  }

  private boolean isReported(final PostEntity postEntity) {
    return postEntity.hasTopNote() && postEntity.isReported();
  }

  private boolean isReportPostOptionAvailableFor(final PostEntity postEntity) {
    return postEntity.clickMoreOptions().hasReportPostOption();
  }

  private boolean clickCancelOnValidatePostModalDialog(final PostDetailsPage page,
                                                       final PostEntity postEntity) {
    postEntity.findTopNote().clickValidate();
    page.getTopNoteModalDialog().clickCancel();

    return isReported(postEntity);
  }

  private boolean clickApproveOnValidatePostModalDialog(final PostDetailsPage page,
                                                        final PostEntity postEntity) {
    postEntity.findTopNote().clickValidate();
    page.getTopNoteModalDialog().clickApprove();

    return postEntity.isReported();
  }

  private boolean clickCancelOnDeletePostModalDialog(final PostDetailsPage page,
                                                     final PostEntity postEntity) {
    postEntity.findTopNote().clickDelete();
    page.getTopNoteModalDialog().clickCancel();

    return isReported(postEntity);
  }

  private boolean clickApproveOnDeletePostModalDialog(final PostDetailsPage page,
                                                      final PostEntity postEntity) {
    postEntity.findTopNote().clickDelete();
    page.getTopNoteModalDialog().clickApprove();

    return postEntity.isDeleted();
  }

}
