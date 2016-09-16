package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
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

  private final String POST_ID_ADDED_ON_DESKTOP_DURING_TEST = "desktop-post-id";

  private final String POST_ID_ADDED_ON_MOBILE_DURING_TEST = "mobile-post-id";

  private final String USER_ID_THAT_ADDED_POST_ON_DESKTOP_DURING_TEST = "desktop-user-id";

  private final String USER_ID_THAT_ADDED_POST_ON_MOBILE_DURING_TEST = "mobile-user-id";

  private final Map<String, String> IDS = new ConcurrentHashMap<>(8);

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    assertThatReportPostOptionIsNotAvailable(postsListPage.getPost());
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage postDetailsPage = new PostDetailsPage().openDefaultPost();
    assertThatReportPostOptionIsNotAvailable(postDetailsPage.getPost());
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnUserPostsPage() {
    UserPostsPage userPostsPage = new UserPostsPage().openDefaultUserPage();
    assertThatReportPostOptionIsNotAvailable(userPostsPage.getPost());
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostsListPage() {
    assertThatAnonymousUserDoesNotSeeReportedPostsOnPostsListPage();
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage() {
    assertThatAnonymousUserDoesNotSeeReportedPostsOnPostDetailsPage(
        IDS.get(POST_ID_ADDED_ON_MOBILE_DURING_TEST));
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnUserPostsPage() {
    assertThatAnonymousUserDoesNotSeeReportedPostsOnUserPostsPage(
        IDS.get(USER_ID_THAT_ADDED_POST_ON_MOBILE_DURING_TEST));
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    assertThatReportPostOptionIsNotAvailable(postsListPage.getPost());
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage postDetailsPage = new PostDetailsPage().openDefaultPost();
    assertThatReportPostOptionIsNotAvailable(postDetailsPage.getPost());
  }


  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnUserPostsPage() {
    UserPostsPage userPostsPage = new UserPostsPage().openDefaultUserPage();
    assertThatReportPostOptionIsNotAvailable(userPostsPage.getPost());
  }

  @Test(groups = "discussions-anonUserDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostsListPage() {
    assertThatAnonymousUserDoesNotSeeReportedPostsOnPostsListPage();
  }

  @Test(groups = "discussions-anonUserDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnDesktopCanNotSeeReportedPostOnPostDetailsPage() {
    assertThatAnonymousUserDoesNotSeeReportedPostsOnPostDetailsPage(
        IDS.get(POST_ID_ADDED_ON_DESKTOP_DURING_TEST));
  }

  @Test(groups = "discussions-anonUserDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnDesktopCanNotSeeReportedPostOnUserPostsPage() {
    assertThatAnonymousUserDoesNotSeeReportedPostsOnUserPostsPage(
        IDS.get(USER_ID_THAT_ADDED_POST_ON_DESKTOP_DURING_TEST));
  }

  // User on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    assertThatAddedPostCanBeReportedOnPostsListPage(postsListPage, postsCreator);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostDetailsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    assertThatAddedPostCanBeReportedOnPostDetailsPage(postsListPage, postsCreator);

    IDS.put(POST_ID_ADDED_ON_MOBILE_DURING_TEST, PostDetailsPage.extractPostIdFrom(driver.getCurrentUrl()));
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnUserPostsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    assertThatAddedPostCanBeReportedOnUserPostsPage(postsListPage, postsCreator);

    IDS.put(USER_ID_THAT_ADDED_POST_ON_MOBILE_DURING_TEST, UserPostsPage.extractUserIdFrom(driver.getCurrentUrl()));
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPage() {
    PageWithPosts page = new PostDetailsPage().open(
        IDS.get(POST_ID_ADDED_ON_MOBILE_DURING_TEST));
    assertThatUserCanNotSeeReportedIndicatorOnPostReportedByAnotherUser(page);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnUserPostsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPage() {
    PageWithPosts page = new UserPostsPage().open(
        IDS.get(USER_ID_THAT_ADDED_POST_ON_MOBILE_DURING_TEST));
    assertThatUserCanNotSeeReportedIndicatorOnPostReportedByAnotherUser(page);
  }

  // User on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    assertThatAddedPostCanBeReportedOnPostsListPage(postsListPage, postsCreator);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostDetailsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    assertThatAddedPostCanBeReportedOnPostDetailsPage(postsListPage, postsCreator);

    IDS.put(POST_ID_ADDED_ON_DESKTOP_DURING_TEST, PostDetailsPage.extractPostIdFrom(driver.getCurrentUrl()));
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnUserPostsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    assertThatAddedPostCanBeReportedOnUserPostsPage(postsListPage, postsCreator);

    IDS.put(USER_ID_THAT_ADDED_POST_ON_DESKTOP_DURING_TEST, UserPostsPage.extractUserIdFrom(driver.getCurrentUrl()));
  }


  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnPostDetailsPage() {
    PageWithPosts page = new PostDetailsPage().open(
        IDS.get(POST_ID_ADDED_ON_DESKTOP_DURING_TEST));
    assertThatUserCanNotSeeReportedIndicatorOnPostReportedByAnotherUser(page);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting",
      dependsOnMethods = "userOnDesktopCanReportPostOnUserPostsPage")
  @Execute(asUser = User.USER_2)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSeeReportedIndicatorOnPostsReportedByAnotherUserOnUserPostsPage() {
    PageWithPosts page = new UserPostsPage().open(
        IDS.get(USER_ID_THAT_ADDED_POST_ON_DESKTOP_DURING_TEST));
    assertThatUserCanNotSeeReportedIndicatorOnPostReportedByAnotherUser(page);
  }

  private void assertThatReportPostOptionIsNotAvailable(final Post post) {
    boolean actual = post.getTheNewestPost()
        .clickMoreOptions()
        .hasReportPostOption();

    Assertion.assertFalse(actual);
  }

  private void assertThatAddedPostCanBeReportedOnPostsListPage(
      final PostsListPage postsListPage, final PostsCreator postsCreator) {
    PostEntity postEntity = createAndGetNewPost(postsListPage, postsCreator);
    reportPost(postEntity);

    assertThatPostIsReported(postEntity);
    assertThatReportPostOptionIsNotPresent(postEntity);
  }

  private PostEntity createAndGetNewPost(final PostsListPage postsListPage, final PostsCreator postsCreator) {
    PostEntity.Data postEntityData = postsCreator.click()
        .closeGuidelinesMessage()
        .addPostWithRandomData();

    return postsListPage.getPost()
        .waitForPostToAppearWith(postEntityData.getDescription())
        .getTheNewestPost();
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

  private void assertThatReportPostOptionIsNotPresent(final PostEntity postEntity) {
    Assertion.assertFalse(postEntity.clickMoreOptions().hasReportPostOption(),
        "There should be no 'Report post' option in 'More options' on post already reported by user.");
  }

  private void assertThatAddedPostCanBeReportedOnPostDetailsPage(
      final PostsListPage postsListPage, final PostsCreator postsCreator) {
    createAndGetNewPost(postsListPage, postsCreator).click();

    new Transitions(driver).waitForPostDetailsPageTransition();

    PostEntity postEntity = new PostDetailsPage().getPost().getTheNewestPost();
    reportPost(postEntity);

    assertThatPostIsReported(postEntity);
    assertThatReportPostOptionIsNotPresent(postEntity);
  }

  private void assertThatAddedPostCanBeReportedOnUserPostsPage(
      final PostsListPage postsListPage, final PostsCreator postsCreator) {
    createAndGetNewPost(postsListPage, postsCreator)
        .clickMoreOptions()
        .clickViewAllPostsByOption();

    new Transitions(driver).waitForUserPostsPageTransition();

    PostEntity postEntity = new UserPostsPage().getPost().getTheNewestPost();
    reportPost(postEntity);

    assertThatPostIsReported(postEntity);
    assertThatReportPostOptionIsNotPresent(postEntity);
  }

  private void assertThatUserCanNotSeeReportedIndicatorOnPostReportedByAnotherUser(final PageWithPosts page) {
    Assertion.assertTrue(page.getPost().getReportedPosts().isEmpty(),
        "User should not see reported indicator on posts reported by another user.");
  }

  private void assertThatAnonymousUserDoesNotSeeReportedPostsOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();

    Assertion.assertTrue(postsListPage.getPost().getReportedPosts().isEmpty(),
        "Anonymous user should not see reported posts.");
  }

  private void assertThatAnonymousUserDoesNotSeeReportedPostsOnPostDetailsPage(final String postId) {
    PostDetailsPage postDetailsPage = new PostDetailsPage().open(postId);

    Assertion.assertTrue(postDetailsPage.getPost().getReportedPosts().isEmpty(),
        "Anonymous user should not see reported posts.");
  }

  private void assertThatAnonymousUserDoesNotSeeReportedPostsOnUserPostsPage(final String userId) {
    UserPostsPage userPostsPage = new UserPostsPage().open(userId);

    Assertion.assertTrue(userPostsPage.getPost().getReportedPosts().isEmpty(),
        "Anonymous user should not see reported posts.");
  }
}
