package com.wikia.webdriver.testcases.discussions;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_2)
@Test(groups = "discussions-creating-posts")
public class CreatingPostTests extends NewTestTemplate {

  private static final String WIKIA_URL = "http://www.wikia.com/";
  private static final String POST_SHOULD_BE_FOLLOWED_MESSAGE = "Created post should be followed.";
  private static final String OPEN_GRAPH_SHOULD_LOAD_MESSAGE = "Open graph should start loading.";
  private static final String OPEN_GRAPH_SHOULD_BE_VISIBLE_MESSAGE = "Open graph should appear at the end of post content.";
  private static final String FIRST_LINE = "# Big List of Naughty Strings\n";

  private static final String DESKTOP = "discussions-creating-posts-desktop";
  private static final String MOBILE = "discussions-creating-posts-mobile";

  private String siteId;

  private void setUp(String wikiName) {
    siteId = Utils.excractSiteIdFromWikiName(wikiName);
  }

  /*
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotWriteNewPost() {
    userOnMobileMustBeLoggedInToUsePostCreator();
  }

  /*
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotWriteNewPost() {
    userOnDesktopMustBeLoggedInToUsePostCreator();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_1)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopWhenScrollsDownThenSeesStickyEditor() {
    PostsListPage postsListPage = new PostsListPage().open();
    postsListPage.getPost().scrollToLoadMoreButton();
    PostsCreatorDesktop postsCreator = postsListPage.getPostsCreatorDesktop();

    Assertion.assertFalse(postsCreator.isExpanded());
    Assertion.assertTrue(postsCreator.isSticky());
  }

  /*
   * LOGGED-IN USERS ON MOBILE SECTION
   */

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSavePostWithoutCategoryAndDescription() {
    PostsListPage page = new PostsListPage().open();
    PostsCreator postsCreator = page.getPostsCreatorMobile();
    assertThatPostWithoutSelectedCategoryAndDescriptionCannotBeAdded(postsCreator);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void postWithWeirdCharactersIsDisplayedOnMobilePostListPage() {
    assertPostWithWeirdCharactersDisplayedOnPostsListPage(MercuryWikis.DISCUSSIONS_2);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void postWithWeirdCharactersIsDisplayedOnMobilePostDetailsPage() {
    assertPostWithWeirdCharactersDisplayedOnPostDetailsPage(MercuryWikis.DISCUSSIONS_2);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotAddPostWithoutTitle() throws MalformedURLException {
    String description = TextGenerator.createUniqueText();
    PostsListPage page = new PostsListPage().open();
    PostsCreator postsCreator = page.getPostsCreatorMobile();
    fillPostCategoryWith(postsCreator, description);

    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER, onWikia = MercuryWikis.DISCUSSIONS_2)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanClickPostAndGoToPostDetailsPage() {
    assertThatUserCanClickPostAndGoToPostDetailsPage();
  }

  /*
   * LOGGED-IN USERS ON DESKTOP SECTION
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanExpandPostEditor() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostsCreatorDesktop();

    postsCreator.click();

    Assertion.assertTrue(postsCreator.isExpanded());
    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSavePostWithoutCategoryAndDescription() {
    PostsCreator postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
    assertThatPostWithoutSelectedCategoryAndDescriptionCannotBeAdded(postsCreator);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void postWithWeirdCharactersIsDisplayedOnDesktopPostListPage() {
    assertPostWithWeirdCharactersDisplayedOnPostsListPage(MercuryWikis.DISCUSSIONS_2);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void postWithWeirdCharactersIsDisplayedOnDesktopPostDetailsPage() {
    assertPostWithWeirdCharactersDisplayedOnPostDetailsPage(MercuryWikis.DISCUSSIONS_2);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotAddPostWithoutTitle() throws MalformedURLException {
    String description = TextGenerator.createUniqueText();
    PostsListPage page = new PostsListPage().open();
    PostsCreator postsCreator = page.getPostsCreatorDesktop();
    fillPostCategoryWith(postsCreator, description);

    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanClickPostAndGoToPostDetailsPage() {
    assertThatUserCanClickPostAndGoToPostDetailsPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void userOnMobileMustBeLoggedInToUsePostCreator() {
    PostsCreatorMobile postsCreator = new PostsListPage().open().getPostsCreatorMobile();

    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());

    postsCreator.clickOkButtonInSignInDialog();

    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());

    postsCreator.clickSignInButtonInSignInDialog();

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.JOIN_PAGE));
  }

  private void userOnDesktopMustBeLoggedInToUsePostCreator() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostsCreatorDesktop();

    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());

    postsCreator.clickOkButtonInSignInDialog();

    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());

    postsCreator.clickSignInButtonInSignInDialog();

    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.REGISTER_PAGE));
  }

  /**
   * This test covers all situations when post cannot be added (submit button is disabled).
   * <p>
   * | Category | Title | Description |
   * |          |     x |             |
   * |          |     x |           x |
   * |          |       |           x |
   * |        x |       |             |
   * |        x |     x |             |
   * <p>
   * * x - means category was selected or text was added
   */
  private void assertThatPostWithoutSelectedCategoryAndDescriptionCannotBeAdded(PostsCreator postsCreator) {
    postsCreator.click()
        .closeGuidelinesMessage();
    Assertion.assertFalse(postsCreator.isPostButtonActive());

    postsCreator.addTitleWith(TextGenerator.defaultText());
    Assertion.assertFalse(postsCreator.isPostButtonActive(),
        "User should not be able to add post with only title filled.");

    postsCreator.addDescriptionWith(TextGenerator.defaultText());
    Assertion.assertFalse(postsCreator.isPostButtonActive(),
        "User should not be able to add post with title and description filled.");

    postsCreator.clearTitle();
    Assertion.assertFalse(postsCreator.isPostButtonActive(),
        "User should not be able to add post with only description filled.");
    postsCreator.clearDescription();

    postsCreator.clickAddCategoryButton()
        .findCategoryOn(0)
        .click();
    Assertion.assertFalse(postsCreator.isPostButtonActive(),
        "User should not be able to add post with only category selected.");

    postsCreator.addTitleWith(TextGenerator.defaultText());
    Assertion.assertFalse(postsCreator.isPostButtonActive(),
        "User should not be able to add post with category selected and title filled.");
  }

  private CategoryPill fillPostCategoryWith(final PostsCreator postsCreator, final String description) {
    CategoryPill categoryPill = postsCreator.click()
        .closeGuidelinesMessage()
        .addDescriptionWith(description)
        .clickAddCategoryButton()
        .findCategoryOn(0);

    categoryPill.click();

    return categoryPill;
  }

  private String addLinkToDescription(final PostsCreator postsCreator, final String description) throws MalformedURLException {
    String text = description + " " + WIKIA_URL;
    postsCreator.addDescriptionWith(new URL(WIKIA_URL));

    return text;
  }

  private PostEntity submitAndWaitForPostToAppear(final PostsCreator postsCreator, final PostsListPage page, final String description) {
    postsCreator.clickSubmitButton();

    return page.getPost()
        .waitForPostToAppearWith(description)
        .findNewestPost();
  }

  private void assertThatPostWasAddedWith(final PostEntity postEntity, final String description,
      final String categoryName) {
    if (null != postEntity) {
      Assertion.assertStringContains(postEntity.findTimestamp(), "now");
      Assertion.assertEquals(postEntity.findDescription(), description);
      Assertion.assertStringContains(postEntity.findCategory(), categoryName);
    } else {
      Assertion.fail("Post with description \"" + description + "\" was not added or not found.");
    }
  }

  private void assertThatUserCanClickPostAndGoToPostDetailsPage() {
    final PostEntity post = new PostsListPage().open().getPost().findNewestPost();

    final String postDetailsUrl = post.findLinkToPostDetails();

    post.click();

    new Transitions(driver).waitForPostDetailsPageTransition();

    final String url = driver.getCurrentUrl();
    Assertion.assertTrue(PostDetailsPage.is(url));
    Assertion.assertTrue(url.endsWith(postDetailsUrl));
  }

  private PostEntity.Data createNaughtyStringsPostRemotely(User user) {
    String description = FIRST_LINE + ContentLoader.loadWikiTextContent("blns.txt");
    String title = "Naughty strings";
    return DiscussionsOperations.using(user, driver).createCustomPost(siteId, title, description);
  }

  private PostEntity.Data createWeirdCharactersPostOnWiki(String wiki) {
    setUp(wiki);
    return createNaughtyStringsPostRemotely(User.USER);
  }

  private void cleanUp(PostEntity.Data post) {
    DiscussionsOperations.using(User.STAFF, driver).deletePost(post, siteId);
  }

  private void assertPostWithWeirdCharactersDisplayedOnPostsListPage(String wiki) {
    PostEntity.Data post = createWeirdCharactersPostOnWiki(wiki);
    String description = new PostsListPage()
      .open()
      .getPost()
      .findPostById(post.getId())
      .findDescription();
    try {
      Assertion.assertStringContains(description, FIRST_LINE);
    } finally {
      cleanUp(post);
    }
  }

  private void assertPostWithWeirdCharactersDisplayedOnPostDetailsPage(String wiki) {
    PostEntity.Data post = createWeirdCharactersPostOnWiki(wiki);
    String description = new PostDetailsPage()
      .open(post.getId())
      .getPost()
      .getPostDetailText();
    try {
      Assertion.assertStringContains(description, FIRST_LINE);
    } finally {
      cleanUp(post);
    }
  }
}
