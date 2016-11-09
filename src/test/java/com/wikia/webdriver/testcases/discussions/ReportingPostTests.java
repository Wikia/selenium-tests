package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

  private static final String POST_ON_DESKTOP_ON_POSTS_LIST = "post-desktop-posts-list";

  private static final String POST_ON_DESKTOP_ON_POST_DETAILS = "post-desktop-post-details";

  private static final String POST_ON_DESKTOP_ON_USER_PAGE = "post-desktop-user-page";

  private static final String POST_ON_MOBILE_ON_POSTS_LIST = "post-mobile-posts-list";

  private static final String POST_ON_MOBILE_ON_POST_DETAILS = "post-mobile-post-details";

  private static final String POST_ON_MOBILE_ON_USER_PAGE = "post-mobile-user-page";

  private final Map<String, PostEntity.Data> testsPostData = new ConcurrentHashMap<>(8);

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostsListPage() {
    PostsListPage page = new PostsListPage().open();

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
    createAndReportPostRemotely();

    PostsListPage page = new PostsListPage().open();
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage() {
    PostEntity.Data data = createAndReportPostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotely();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = {
          "userOnMobileCanReportPostOnPostDetailsPage",
          "anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage",
          "userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnMobileCanApproveReportedPostOnPostDetailsPage",
          "userOnMobileCannotReReportPostOnPostDetailsPage",
          "userOnMobileCanReportApprovedPostOnPostDetailsPage",
          "moderatorOnMobileCanDeleteReportedPostOnPostDetailsPage"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeDeletedPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);

    Assertion.assertTrue(page.getErrorMessages().isErrorMessagePresent(), "Anonymous user should not see deleted post.");
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostsListPage() {
    PostsListPage page = new PostsListPage().open();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage page = new PostDetailsPage().openDefaultPost();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }


  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnUserPostsPage() {
    UserPostsPage page = new UserPostsPage().openDefaultUserPage();

    Assertion.assertFalse(isReportPostOptionAvailableOn(page), NO_REPORT_POST_OPTION_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostsListPage() {
    createAndReportPostRemotely();

    PostsListPage page = new PostsListPage().open();
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage() throws InterruptedException {
    PostEntity.Data data = createAndReportPostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotSeeReportedPostOnUserPostsPage() {
    PostEntity.Data data = createAndReportPostRemotely();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    Assertion.assertFalse(postHasReportedIndicator(page), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  // User on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostsListPage() {
    final PostEntity.Data data = cretePostRemotely();

    PostsListPage page = new PostsListPage().open();
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    testsPostData.put(POST_ON_MOBILE_ON_POSTS_LIST, data);

    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostDetailsPage() {
    final PostEntity.Data data = cretePostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    testsPostData.put(POST_ON_MOBILE_ON_POST_DETAILS, data);

    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnUserPostsPage() {
    final PostEntity.Data data = cretePostRemotely();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    testsPostData.put(POST_ON_MOBILE_ON_USER_PAGE, data);

    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = {
          "userOnMobileCanReportPostOnPostDetailsPage",
          "anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage",
          "userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnMobileCanApproveReportedPostOnPostDetailsPage"})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotReReportPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  // Second user on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostsListPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    final String postId = testsPostData.get(POST_ON_MOBILE_ON_POSTS_LIST).getId();
    PageWithPosts page = new PostsListPage().open();
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    final String postId = testsPostData.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();
    PageWithPosts page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnUserPostsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    final PostEntity.Data postData = this.testsPostData.get(POST_ON_MOBILE_ON_USER_PAGE);
    PageWithPosts page = new UserPostsPage().open(postData.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(postData.getId());

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Third user on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = {
          "userOnMobileCanReportPostOnPostDetailsPage",
          "anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage",
          "userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnMobileCanApproveReportedPostOnPostDetailsPage"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Discussions moderator on mobile

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting",
      dependsOnMethods = {
          "userOnMobileCanReportPostOnPostDetailsPage",
          "anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage",
          "userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanApproveReportedPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnValidatePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(clickApproveOnValidatePostModalDialog(page, postEntity), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorMobileReporting",
      dependsOnMethods = {
          "userOnMobileCanReportPostOnPostDetailsPage",
          "anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage",
          "userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnMobileCanApproveReportedPostOnPostDetailsPage",
          "userOnMobileCannotReReportPostOnPostDetailsPage",
          "userOnMobileCanReportApprovedPostOnPostDetailsPage"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void moderatorOnMobileCanDeleteReportedPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnDeletePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickApproveOnDeletePostModalDialog(page, postEntity), DELETED_POST_MESSAGE);
  }

  // User on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostsListPage() {
    final PostEntity.Data data = cretePostRemotely();

    PostsListPage page = new PostsListPage().open();
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostDetailsPage() {
    final PostEntity.Data data = cretePostRemotely();

    PostDetailsPage page = new PostDetailsPage().open(data.getId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnUserPostsPage() {
    final PostEntity.Data data = cretePostRemotely();

    UserPostsPage page = new UserPostsPage().open(data.getAuthorId());
    PostEntity postEntity = page.getPost().findPostById(data.getId());
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = {
          "userOnDesktopCanReportPostOnPostDetailsPage",
          "anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage",
          "userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnDesktopCanApproveReportedPostOnPostDetailsPage"})
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotReReportPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(isReportPostOptionAvailableFor(postEntity), NO_REPORT_POST_OPTION_MESSAGE);
  }

  // Second user on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostsListPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    final String postId = testsPostData.get(POST_ON_DESKTOP_ON_POSTS_LIST).getId();
    PageWithPosts page = new PostsListPage().open();
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    final String postId = testsPostData.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();
    PageWithPosts page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnUserPostsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    final PostEntity.Data postData = this.testsPostData.get(POST_ON_DESKTOP_ON_USER_PAGE);
    PageWithPosts page = new UserPostsPage().open(postData.getAuthorId());
    final PostEntity postEntity = page.getPost().findPostById(postData.getId());

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Third user on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = {
          "userOnDesktopCanReportPostOnPostDetailsPage",
          "anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage",
          "userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnDesktopCanApproveReportedPostOnPostDetailsPage"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportApprovedPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(), REPORTED_INDICATOR_NOT_VISIBLE_FOR_USER_MESSAGE);
    Assertion.assertTrue(postCanBeReported(postEntity), CAN_REPORT_POST_MESSAGE);
  }

  // Discussions moderator on desktop

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting",
      dependsOnMethods = {
          "userOnDesktopCanReportPostOnPostDetailsPage",
          "anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage",
          "userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanApproveReportedPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnValidatePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertFalse(clickApproveOnValidatePostModalDialog(page, postEntity), NO_REPORTED_INDICATOR_ON_POST_MESSAGE);
  }

  @Test(groups = "discussions-loggedInDiscussionsModeratorDesktopReporting",
      dependsOnMethods = {
          "userOnDesktopCanReportPostOnPostDetailsPage",
          "anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage",
          "userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnDesktopCanApproveReportedPostOnPostDetailsPage",
          "userOnDesktopCannotReReportPostOnPostDetailsPage",
          "userOnDesktopCanReportApprovedPostOnPostDetailsPage"})
  @Execute(asUser = User.DISCUSSIONS_MODERATOR)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void moderatorOnDesktopCanDeleteReportedPostOnPostDetailsPage() {
    final String postId = testsPostData.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();
    PostDetailsPage page = new PostDetailsPage().open(postId);
    final PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertTrue(isReported(postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickCancelOnDeletePostModalDialog(page, postEntity), REPORTED_INDICATOR_ON_POST_MESSAGE);
    Assertion.assertTrue(clickApproveOnDeletePostModalDialog(page, postEntity), DELETED_POST_MESSAGE);
  }

  private PostEntity.Data cretePostRemotely() {
    return DiscussionsOperations.using(User.USER).cratePostWithUniqueData();
  }

  private PostEntity.Data createAndReportPostRemotely() {
    DiscussionsOperations operations = DiscussionsOperations.using(User.USER);
    final PostEntity.Data data = operations.cratePostWithUniqueData();
    operations.reportPost(data);
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
