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
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostsCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostsCreatorMobile;
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
    PostsCreatorMobile postsCreator = new PostsListPage().open().getPostsCreatorMobile();
    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());
    postsCreator.clickOkButtonInSignInDialog();
    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());
    postsCreator.clickSignInButtonInSignInDialog();
    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.JOIN_PAGE));
  }

  /*
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanNotWriteNewPost() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());
    postsCreator.clickOkButtonInSignInDialog();
    Assertion.assertTrue(postsCreator.click().isSignInDialogVisible());
    postsCreator.clickSignInButtonInSignInDialog();
    Assertion.assertTrue(driver.getCurrentUrl().contains(MercurySubpages.REGISTER_PAGE));
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_1)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
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
  public void userOnMobileCannotSavePostWithoutCategoryOrAnyContent() {
    PostsListPage page = new PostsListPage().open();
    BasePostsCreator postsCreator = page.getPostsCreatorMobile();
    assertThatPostWithoutCategoryOrAnyContentCannotBeAdded(postsCreator);
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
  public void userOnMobileCannotAddPostWithoutTitle() {
    PostsCreator postsCreator = new PostsListPage().open().getPostsCreatorMobile();
    fillPostCategoryWith(postsCreator, TextGenerator.createUniqueText());

    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  /*
   * LOGGED-IN USERS ON DESKTOP SECTION
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCanExpandPostEditor() {
    PostsCreatorDesktop postsCreator = new PostsListPage().open().getPostsCreatorDesktop();

    postsCreator.click();

    Assertion.assertTrue(postsCreator.isExpanded());
    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopCannotSavePostWithoutCategoryOrAnyContent() {
    BasePostsCreator postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
    assertThatPostWithoutCategoryOrAnyContentCannotBeAdded(postsCreator);
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
  public void userOnDesktopCannotAddPostWithoutTitle() {
    PostsCreator postsCreator = new PostsListPage().open().getPostsCreatorDesktop();
    fillPostCategoryWith(postsCreator, TextGenerator.createUniqueText());

    Assertion.assertFalse(postsCreator.isPostButtonActive());
  }


  /**
   * TESTING METHODS SECTION
   *
   * This test covers all situations when post cannot be added (submit button is disabled).
   * <p>
   * | Category | Title | Description | Open Graph | Content Image |
   * |          |       |             |            |               |
   * |          |     x |             |            |               |
   * |          |       |           x |            |               |
   * |          |       |             |          x |               |
   * |          |       |             |            |             x |
   * |          |     x |           x |            |               |
   * |          |     x |             |          x |               |
   * |          |     x |             |            |             x |
   * |        x |       |             |            |               |
   * |        x |     x |             |            |               |
   * |        x |       |           x |            |               |
   * |        x |       |             |          x |               |
   * |        x |       |             |            |             x |
   *
   *
   * <p>
   * * x - means category was selected or text was added
   */
  private void assertThatPostWithoutCategoryOrAnyContentCannotBeAdded(BasePostsCreator postsCreator) {
    postsCreator.click().closeGuidelinesMessage();
    Assertion.assertFalse(postsCreator.isPostButtonActive());

    postsCreator.addTitleWith(TextGenerator.defaultText());
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only title."
    );
    postsCreator.clearTitle();

    postsCreator.addDescriptionWith(TextGenerator.defaultText());
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only description."
    );
    postsCreator.clearDescription();

    postsCreator.addDescriptionWithLink("http://fandom.wikia.com");
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only open graph."
    );
    postsCreator.clearDescription();
    postsCreator.clearOpenGraph();

    postsCreator.uploadImage();
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only content image uploaded."
    );
    postsCreator.removeImage();

    postsCreator.addTitleWith(TextGenerator.defaultText());
    postsCreator.addDescriptionWith(TextGenerator.defaultText());
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only title and description."
    );
    postsCreator.clearDescription();
    postsCreator.clearTitle();

    postsCreator.addTitleWith(TextGenerator.defaultText());
    postsCreator.addDescriptionWithLink("http://fandom.wikia.com");
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only title and open graph."
    );
    postsCreator.clearDescription();
    postsCreator.clearOpenGraph();
    postsCreator.clearTitle();

    postsCreator.addTitleWith(TextGenerator.defaultText());
    postsCreator.uploadImage();
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only title and content image."
    );
    postsCreator.removeImage();
    postsCreator.clearTitle();

    // Add category at the end because there is no way to clear it
    postsCreator.clickAddCategoryButton()
        .findCategoryOn(0)
        .click();
    Assertion.assertFalse(postsCreator.isPostButtonActive(),
        "User should not be able to add post with only category.");

    postsCreator.addTitleWith(TextGenerator.defaultText());
    Assertion.assertFalse(postsCreator.isPostButtonActive(),
        "User should not be able to add post with only category and title.");
    postsCreator.clearTitle();

    postsCreator.addDescriptionWith(TextGenerator.defaultText());
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only category and description."
    );
    postsCreator.clearDescription();

    postsCreator.addDescriptionWithLink("http://fandom.wikia.com");
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only category and open graph."
    );
    postsCreator.clearDescription();
    postsCreator.clearOpenGraph();

    postsCreator.uploadImage();
    Assertion.assertFalse(
        postsCreator.isPostButtonActive(),
        "User should not be able to add post with only category and content image."
    );
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
