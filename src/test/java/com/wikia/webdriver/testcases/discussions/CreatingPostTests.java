package com.wikia.webdriver.testcases.discussions;

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
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostEditorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostEditorMobile;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_2)
@Test(groups = "discussions-creating-posts")
public class CreatingPostTests extends NewTestTemplate {

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
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotWriteNewPost() {
    userOnDesktopMustBeLoggedInToUsePostCreator();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_1)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopWhenScrollsDownThenSeesStickyEditor() {
    PostsListPage postsListPage = new PostsListPage().open();
    postsListPage.getPost().scrollToLoadMoreButton();
    PostEditorDesktop postsCreator = postsListPage.getPostsCreatorDesktop();

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
    ContributionEditor postsCreator = page.getPostsCreatorMobile();
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
    ContributionEditor postsCreator = page.getPostsCreatorMobile();
    fillPostCategoryWith(postsCreator, description);

    Assertion.assertFalse(postsCreator.isSubmitButtonActive());
  }

  /*
   * LOGGED-IN USERS ON DESKTOP SECTION
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanExpandPostEditor() {
    PostEditorDesktop postsCreator = new PostsListPage().open().getPostsCreatorDesktop();

    postsCreator.click();

    Assertion.assertTrue(postsCreator.isExpanded());
    Assertion.assertFalse(postsCreator.isSubmitButtonActive());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotSavePostWithoutCategoryAndDescription() {
    ContributionEditor postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
    assertThatPostWithoutSelectedCategoryAndDescriptionCannotBeAdded(postsCreator);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void postWithWeirdCharactersIsDisplayedOnDesktopPostListPage() {
    assertPostWithWeirdCharactersDisplayedOnPostsListPage(MercuryWikis.DISCUSSIONS_2);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void postWithWeirdCharactersIsDisplayedOnDesktopPostDetailsPage() {
    assertPostWithWeirdCharactersDisplayedOnPostDetailsPage(MercuryWikis.DISCUSSIONS_2);
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotAddPostWithoutTitle() throws MalformedURLException {
    String description = TextGenerator.createUniqueText();
    PostsListPage page = new PostsListPage().open();
    ContributionEditor postsCreator = page.getPostsCreatorDesktop();
    fillPostCategoryWith(postsCreator, description);

    Assertion.assertFalse(postsCreator.isSubmitButtonActive());
  }


  /**
   * TESTING METHODS SECTION
   */

  private void userOnMobileMustBeLoggedInToUsePostCreator() {
    PostEditorMobile postsCreator = new PostsListPage().open().getPostsCreatorMobile();
    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());
    postsCreator.clickOkButtonInSignInDialog();
    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());
    postsCreator.clickSignInButtonInSignInDialog();
    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.JOIN_PAGE));
  }

  private void userOnDesktopMustBeLoggedInToUsePostCreator() {
    PostEditorDesktop postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
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
  private void assertThatPostWithoutSelectedCategoryAndDescriptionCannotBeAdded(ContributionEditor postsCreator) {
    postsCreator.click()
        .closeGuidelinesMessage();
    Assertion.assertFalse(postsCreator.isSubmitButtonActive());

    postsCreator.addTitleWith(TextGenerator.defaultText());
    Assertion.assertFalse(postsCreator.isSubmitButtonActive(),
        "User should not be able to add post with only title filled.");

    postsCreator.addTextWith(TextGenerator.defaultText());
    Assertion.assertFalse(postsCreator.isSubmitButtonActive(),
        "User should not be able to add post with title and description filled.");

    postsCreator.clearTitle();
    Assertion.assertFalse(postsCreator.isSubmitButtonActive(),
        "User should not be able to add post with only description filled.");
    postsCreator.clearText();

    postsCreator.clickAddCategoryButton()
        .findCategoryOn(0)
        .click();
    Assertion.assertFalse(postsCreator.isSubmitButtonActive(),
        "User should not be able to add post with only category selected.");

    postsCreator.addTitleWith(TextGenerator.defaultText());
    Assertion.assertFalse(postsCreator.isSubmitButtonActive(),
        "User should not be able to add post with category selected and title filled.");
  }

  private CategoryPill fillPostCategoryWith(final ContributionEditor postsCreator, final String description) {
    CategoryPill categoryPill = postsCreator.click()
        .closeGuidelinesMessage()
        .addDescriptionWith(description)
        .clickAddCategoryButton()
        .findCategoryOn(0);

    categoryPill.click();

    return categoryPill;
  }

  private PostEntity.Data createNaughtyStringsPostRemotely(User user) {
    String description = FIRST_LINE + ContentLoader.loadWikiTextContent("blns.txt");
    String title = "Naughty strings";
    return DiscussionsClient.using(user, driver).createCustomPost(siteId, title, description);
  }

  private PostEntity.Data createWeirdCharactersPostOnWiki(String wiki) {
    setUp(wiki);
    return createNaughtyStringsPostRemotely(User.USER);
  }

  private void cleanUp(PostEntity.Data post) {
    DiscussionsClient.using(User.STAFF, driver).deletePost(post, siteId);
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
