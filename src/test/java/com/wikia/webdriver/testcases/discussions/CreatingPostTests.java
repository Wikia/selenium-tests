package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Transitions;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-creating-posts")
public class CreatingPostTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  private static final String WIKIA_URL = "http://www.wikia.com/";

  private static final String POST_SHOULD_BE_FOLLOWED_MESSAGE = "Created post should be followed.";

  private static final String OPEN_GRAPH_SHOULD_LOAD_MESSAGE = "Open graph should start loading.";

  private static final String OPEN_GRAPH_SHOULD_BE_VISIBLE_MESSAGE = "Open graph should appear at the end of post content.";

  /*
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanNotWriteNewPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotWriteNewPost() {
    userOnMobileMustBeLoggedInToUsePostCreator();
  }

  /*
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanNotWriteNewPost")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotWriteNewPost() {
    userOnDesktopMustBeLoggedInToUsePostCreator();
  }

  @Test(groups = "discussions-anonUserOnDesktopSeesStickyEditorAfterScrollDown")
  @Execute(asUser = User.ANONYMOUS)
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

  @Test(groups = "discussions-loggedInUsersMobilePosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCannotSavePostWithoutCategoryAndDescription() {
    PostsListPage page = new PostsListPage().open();
    PostsCreator postsCreator = page.getPostsCreatorMobile();
    assertThatPostWithoutSelectedCategoryAndDescriptionCannotBeAdded(postsCreator);
  }

  @Test(enabled = false, groups = "discussions-loggedInUsersMobilePosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @RelatedIssue(issueID = "SOC-3562")
  public void userOnMobileCanAddPostWithoutTitle() throws MalformedURLException {
    String description = TextGenerator.createUniqueText();

    PostsListPage page = new PostsListPage().open();
    PostsCreator postsCreator = page.getPostsCreatorMobile();

    final CategoryPill categoryPill = fillPostCategoryWith(postsCreator, description);

    description = addLinkToDescription(postsCreator, description);
    Assertion.assertTrue(postsCreator.hasOpenGraph(), OPEN_GRAPH_SHOULD_LOAD_MESSAGE);

    PostEntity postEntity = submitAndWaitForPostToAppear(postsCreator, page, description);

    assertThatPostWasAddedWith(postEntity, description, categoryPill.getName());
    Assertion.assertTrue(postEntity.findPostActions().isFollowed(), POST_SHOULD_BE_FOLLOWED_MESSAGE);
    Assertion.assertTrue(postEntity.hasOpenGraphAtContentEnd(), OPEN_GRAPH_SHOULD_BE_VISIBLE_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersMobilePosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanClickPostAndGoToPostDetailsPage() {
    assertThatUserCanClickPostAndGoToPostDetailsPage();
  }

  /*
   * LOGGED-IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedInUsersDesktopPosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanExpandPostEditor() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostsCreatorDesktop();

    postsCreator.click();

    Assertion.assertTrue(postsCreator.isExpanded());
    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  @Test(groups = "discussions-loggedInUsersDesktopPosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCannotSavePostWithoutCategoryAndDescription() {
    PostsCreator postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
    assertThatPostWithoutSelectedCategoryAndDescriptionCannotBeAdded(postsCreator);
  }

  @Test(groups = "discussions-loggedInUsersDesktopPosting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanAddPostWithoutTitle() throws MalformedURLException {
    String description = TextGenerator.createUniqueText();

    PostsListPage page = new PostsListPage().open();
    PostsCreator postsCreator = page.getPostsCreatorDesktop();

    final CategoryPill categoryPill = fillPostCategoryWith(postsCreator, description);

    description = addLinkToDescription(postsCreator, description);
    Assertion.assertTrue(postsCreator.hasOpenGraph(), OPEN_GRAPH_SHOULD_LOAD_MESSAGE);

    PostEntity postEntity = submitAndWaitForPostToAppear(postsCreator, page, description);

    assertThatPostWasAddedWith(postEntity, description, categoryPill.getName());
    Assertion.assertTrue(postEntity.findPostActions().isFollowed(), POST_SHOULD_BE_FOLLOWED_MESSAGE);
    Assertion.assertTrue(postEntity.hasOpenGraphAtContentEnd(), OPEN_GRAPH_SHOULD_BE_VISIBLE_MESSAGE);
  }

  @Test(groups = "discussions-loggedInUsersDesktopPosting")
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
        .findCategoryOnPosition(0)
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
        .findCategoryOnPosition(0);

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
}
