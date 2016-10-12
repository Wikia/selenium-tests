package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Transitions;
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

  private final String POST_ON_DESKTOP_ON_POSTS_LIST = "post-desktop-posts-list";

  private final String POST_ON_DESKTOP_ON_POST_DETAILS = "post-desktop-post-details";

  private final String POST_ON_DESKTOP_ON_USER_PAGE = "post-desktop-user-page";

  private final String POST_ON_MOBILE_ON_POSTS_LIST = "post-mobile-posts-list";

  private final String POST_ON_MOBILE_ON_POST_DETAILS = "post-mobile-post-details";

  private final String POST_ON_MOBILE_ON_USER_PAGE = "post-mobile-user-page";

  private final String POST_ID_ADDED_ON_DESKTOP_DURING_TEST = "desktop-post-id";

  private final String POST_ID_ADDED_ON_MOBILE_DURING_TEST = "mobile-post-id";

  private final String USER_ID_THAT_ADDED_POST_ON_DESKTOP_DURING_TEST = "desktop-user-id";

  private final String USER_ID_THAT_ADDED_POST_ON_MOBILE_DURING_TEST = "mobile-user-id";

  private final Map<String, PostEntity.Data> POST_DATA = new ConcurrentHashMap<>(8);

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    assertThatReportPostOptionIsNotAvailableOn(postsListPage);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage postDetailsPage = new PostDetailsPage().openDefaultPost();
    assertThatReportPostOptionIsNotAvailableOn(postDetailsPage);
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnUserPostsPage() {
    UserPostsPage userPostsPage = new UserPostsPage().openDefaultUserPage();
    assertThatReportPostOptionIsNotAvailableOn(userPostsPage);
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostsListPage() {
    PostsListPage page = new PostsListPage().open();

    assertThatNoPostHasReportedIndicator(page);
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage() {
    PostDetailsPage page = new PostDetailsPage().open(
        POST_DATA.get(POST_ON_MOBILE_ON_POST_DETAILS).getId());

    assertThatNoPostHasReportedIndicator(page);
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnUserPostsPage() {
    UserPostsPage page = new UserPostsPage().open(
        POST_DATA.get(POST_ON_MOBILE_ON_USER_PAGE).getAuthroId());

    assertThatNoPostHasReportedIndicator(page);
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    assertThatReportPostOptionIsNotAvailableOn(postsListPage);
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage postDetailsPage = new PostDetailsPage().openDefaultPost();
    assertThatReportPostOptionIsNotAvailableOn(postDetailsPage);
  }


  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnUserPostsPage() {
    UserPostsPage userPostsPage = new UserPostsPage().openDefaultUserPage();
    assertThatReportPostOptionIsNotAvailableOn(userPostsPage);
  }

  @Test(groups = "discussions-anonUserDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostsListPage() {
    PostsListPage page = new PostsListPage().open();

    assertThatNoPostHasReportedIndicator(page);
  }

  @Test(groups = "discussions-anonUserDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage() {
    PostDetailsPage page = new PostDetailsPage().open(
        POST_DATA.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId());

    assertThatNoPostHasReportedIndicator(page);
  }

  @Test(groups = "discussions-anonUserDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnDesktopCanNotSeeReportedPostOnUserPostsPage() {
    UserPostsPage page = new UserPostsPage().open(
        POST_DATA.get(POST_ON_DESKTOP_ON_USER_PAGE).getAuthroId());

    assertThatNoPostHasReportedIndicator(page);
  }

  // User on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    PostEntity postEntity = createAndGetNewPost(postsListPage, postsCreator);
    POST_DATA.put(POST_ON_MOBILE_ON_POSTS_LIST, postEntity.toData());

    assertThatPostCanBeReported(postEntity);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostDetailsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    PostEntity postEntity = addPostAndGoToPostDetailsPage(postsListPage, postsCreator);
    POST_DATA.put(POST_ON_MOBILE_ON_POST_DETAILS, postEntity.toData());

    assertThatPostCanBeReported(postEntity);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnUserPostsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    PostEntity postEntity = addPostAndGoToUserPostsPage(postsListPage, postsCreator);
    POST_DATA.put(POST_ON_MOBILE_ON_USER_PAGE, postEntity.toData());

    assertThatPostCanBeReported(postEntity);
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
    final String postId = POST_DATA.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();

    PostDetailsPage page = new PostDetailsPage().open(postId);

    PostEntity postEntity = page.getPost().findPostById(postId);

    assertThatPostIsNotReported(postEntity);
    assertThatReportPostOptionIsNotAvailableFor(postEntity);
  }

  // Second user on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostsListPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    final String postId = POST_DATA.get(POST_ON_MOBILE_ON_POSTS_LIST).getId();

    PageWithPosts page = new PostsListPage().open();

    assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(page, postId);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    final String postId = POST_DATA.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();

    PageWithPosts page = new PostDetailsPage().open(postId);

    assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(page, postId);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnUserPostsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    final PostEntity.Data postData = POST_DATA.get(POST_ON_MOBILE_ON_USER_PAGE);

    PageWithPosts page = new UserPostsPage().open(postData.getAuthroId());

    assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(page, postData.getId());
  }

  // Third user on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = {
          "userOnMobileCanReportPostOnPostDetailsPage",
          "anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage",
          "userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost",
          "moderatorOnMobileCanApproveReportedPostOnPostDetailsPage",
          "userOnMobileCannotReReportPostOnPostDetailsPage"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportApprovedPostOnPostDetailsPage() {
    final String postId = POST_DATA.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();

    PostDetailsPage page = new PostDetailsPage().open(postId);

    assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(page, postId);
  }

  // User on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    PostEntity postEntity = createAndGetNewPost(postsListPage, postsCreator);
    POST_DATA.put(POST_ON_DESKTOP_ON_POSTS_LIST, postEntity.toData());

    assertThatPostCanBeReported(postEntity);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostDetailsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    PostEntity postEntity = addPostAndGoToPostDetailsPage(postsListPage, postsCreator);
    POST_DATA.put(POST_ON_DESKTOP_ON_POST_DETAILS, postEntity.toData());

    assertThatPostCanBeReported(postEntity);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnUserPostsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    PostEntity postEntity = addPostAndGoToUserPostsPage(postsListPage, postsCreator);
    POST_DATA.put(POST_ON_DESKTOP_ON_USER_PAGE, postEntity.toData());

    assertThatPostCanBeReported(postEntity);
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
    final String postId = POST_DATA.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();

    PostDetailsPage page = new PostDetailsPage().open(postId);

    PostEntity postEntity = page.getPost().findPostById(postId);

    assertThatPostIsNotReported(postEntity);
    assertThatReportPostOptionIsNotAvailableFor(postEntity);
  }

  // Second user on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostsListPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostsListPageAndCanReportThatPost() {
    final String postId = POST_DATA.get(POST_ON_DESKTOP_ON_POSTS_LIST).getId();

    PageWithPosts page = new PostsListPage().open();

    assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(page, postId);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPageAndCanReportThatPost() {
    final String postId = POST_DATA.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();

    PageWithPosts page = new PostDetailsPage().open(postId);

    assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(page, postId);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnUserPostsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPageAndCanReportThatPost() {
    final PostEntity.Data postData = POST_DATA.get(POST_ON_DESKTOP_ON_USER_PAGE);

    PageWithPosts page = new UserPostsPage().open(postData.getAuthroId());

    assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(page, postData.getId());
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
    final String postId = POST_DATA.get(POST_ON_MOBILE_ON_POST_DETAILS).getId();

    PostDetailsPage page = new PostDetailsPage().open(postId);

    assertThatDiscussionsModeratorCanSeeAndApproveReportedPost(page, postId);
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
    final String postId = POST_DATA.get(POST_ON_DESKTOP_ON_POST_DETAILS).getId();

    PostDetailsPage page = new PostDetailsPage().open(postId);

    assertThatDiscussionsModeratorCanSeeAndApproveReportedPost(page, postId);
  }

  private void assertThatReportPostOptionIsNotAvailableOn(final PageWithPosts page) {
    assertThatReportPostOptionIsNotAvailableFor(page.getPost().findNewestPost());
  }

  private void assertThatNoPostHasReportedIndicator(final PageWithPosts pageWithPosts) {
    Assertion.assertTrue(pageWithPosts.getPost().getReportedPosts().isEmpty(),
        "Anonymous user should not see reported indicator.");
  }

  private PostEntity createAndGetNewPost(final PostsListPage postsListPage, final PostsCreator postsCreator) {
    PostEntity.Data postData = postsCreator.click()
        .closeGuidelinesMessage()
        .addPostWithRandomData();

    return postsListPage.getPost()
        .waitForPostToAppearWith(postData.getDescription())
        .findNewestPost();
  }

  private PostEntity addPostAndGoToPostDetailsPage(final PostsListPage postsListPage, final PostsCreator postsCreator) {
    createAndGetNewPost(postsListPage, postsCreator).click();

    new Transitions(driver).waitForPostDetailsPageTransition();

    return new PostDetailsPage().getPost().findNewestPost();
  }

  private void assertThatPostCanBeReported(final PostEntity postEntity) {
    reportPost(postEntity);

    assertThatPostIsReported(postEntity);
    assertThatReportPostOptionIsNotAvailableFor(postEntity);
  }

  private void reportPost(final PostEntity postEntity) {
    postEntity.clickMoreOptions()
        .clickReportPostOption();
  }

  private void assertThatPostIsReported(final PostEntity postEntity) {
    Assertion.assertTrue(postEntity.hasTopNote(),
        "Post should have top note. (Note with reporter should appear at the top of post.)");
    Assertion.assertTrue(postEntity.isReported(),
        "Post should be reported.");
  }

  private void assertThatPostIsNotReported(final PostEntity postEntity) {
    Assertion.assertFalse(postEntity.isReported(),
        "Post should not be reported.");
  }

  private void assertThatReportPostOptionIsNotAvailableFor(final PostEntity postEntity) {
    Assertion.assertFalse(postEntity.clickMoreOptions().hasReportPostOption(),
        "There should be no 'Report post' option in 'More options' on post already reported by user.");
  }

  private PostEntity addPostAndGoToUserPostsPage(final PostsListPage postsListPage, final PostsCreator postsCreator) {
    createAndGetNewPost(postsListPage, postsCreator)
        .clickMoreOptions()
        .clickViewAllPostsByOption();

    new Transitions(driver).waitForUserPostsPageTransition();

    return new UserPostsPage().getPost().findNewestPost();
  }

  private void assertThatPostReportedByOtherUserDoesNotHaveReportedIndicatorAndCanBeReportedByCurrentUser(
      final PageWithPosts page, final String postId) {
    PostEntity postEntity = page.getPost().findPostById(postId);

    Assertion.assertFalse(postEntity.isReported(),
        "User should not see reported indicator on posts which were not reported by them.");
    assertThatPostCanBeReported(postEntity);
  }

  private void assertThatDiscussionsModeratorCanSeeAndApproveReportedPost(
      final PostDetailsPage page, final String postId) {
    final PostEntity postEntity = page.getPost().findPostById(postId);

    assertThatPostIsReported(postEntity);
    assertThatDiscussionsModeratorCanClickCancelOnValidateModalDialog(page, postEntity);
    assertThatDiscussionsModeratorCanApproveReportedPost(page, postEntity);
  }

  private void assertThatDiscussionsModeratorCanClickCancelOnValidateModalDialog(
      final PostDetailsPage page, final PostEntity postEntity) {
    postEntity.findTopNote()
        .clickValidate();

    page.getTopNoteModalDialog()
        .clickCancel();

    assertThatPostIsReported(postEntity);
  }

  private void assertThatDiscussionsModeratorCanApproveReportedPost(
      final PostDetailsPage page, final PostEntity postEntity) {
    postEntity.findTopNote()
        .clickValidate();

    page.getTopNoteModalDialog()
        .clickApprove();

    Assertion.assertFalse(postEntity.isReported(),
        "Discussion moderator should not see reported indicator on approved posts.");
  }
}
