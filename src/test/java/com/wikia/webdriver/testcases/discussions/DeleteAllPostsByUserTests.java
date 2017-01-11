package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DeleteAllButton;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DeleteDialog;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.*;


import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;
import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static com.wikia.webdriver.common.core.Assertion.assertFalse;


/**
 * Tests for deleting all posts by some user
 */
@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class DeleteAllPostsByUserTests extends NewTestTemplate {

  private static final User userWithPosts = User.USER_6;
  private String siteId;

  // FIXTURES

  /**
   *
   * @param wikiName wiki on which a post by `userWithPosts` will be created
   * @return post that was created
   */
  private PostEntity.Data setUp(String wikiName) {
    String wikiUrl = new UrlBuilder().getUrlForWiki(wikiName);
    siteId = Discussions.extractSiteIdFromMediaWiki(wikiUrl + URLsContent.SPECIAL_VERSION);
    return DiscussionsOperations
      .using(userWithPosts, driver)
      .createPostWithUniqueData(siteId);
  }

  private PostEntity.Data setUp() {
    return setUp(MercuryWikis.DISCUSSIONS_AUTO);
  }

  /**
   *
   * @param post to be deleted as staff user
   */
  private void cleanUp(PostEntity.Data post) {
    DiscussionsOperations.using(User.STAFF, driver).deletePost(post, this.siteId);
  }

  // GENERAL - no specific user permissions

  @Test(groups = "discussions-deleteAllPostsByUserDesktop")
  @Execute(asUser = User.STAFF)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void clickDeleteAllDesktopDisplaysConfirmationDialog() {
    DeleteDialog confirmationDialog = getDeleteAllButton(userWithPosts.getUserId()).click();
    assertTrue(confirmationDialog.isVisible());
  }

  @Test(groups = "discussions-deleteAllPostsByUserMobile")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void clickDeleteAllMobileDisplaysConfirmationDialog() {
    DeleteDialog confirmationDialog = getDeleteAllButton(userWithPosts.getUserId()).click();
    assertTrue(confirmationDialog.isVisible());
  }

  @Test(groups = "discussions-deleteAllPostsByUserDesktop")
  @Execute(asUser = User.STAFF)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void clickCancelDeleteAllDesktopHidesConfirmationDialog() {
    assertFalse(cancelAndReturnFirst().isDeleted());
  }

  @Test(groups = "discussions-deleteAllPostsByUserMobile")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void clickCancelDeleteAllMobileHidesConfirmationDialog() {
    assertFalse(cancelAndReturnFirst().isDeleted());
  }

  // ANON

  @Test(groups = "discussions-deleteAllPostsByUserDesktop")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonDesktopDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp(MercuryWikis.DISCUSSIONS_AUTO);
    assertTrue(deleteAllOptionNotVisible(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = "discussions-deleteAllPostsByUserMobile")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonMobileDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp(MercuryWikis.DISCUSSIONS_AUTO);
    assertTrue(deleteAllOptionNotVisible(userWithPosts.getUserId()));
    cleanUp(post);
  }

  // REGULAR USER

  @Test(groups = "discussions-deleteAllPostsByUserDesktop")
  @Execute(asUser = User.USER)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void regularUserDesktopDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp(MercuryWikis.DISCUSSIONS_AUTO);
    assertTrue(deleteAllOptionNotVisible(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = "discussions-deleteAllPostsByUserMobile")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void regularUserMobileDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp(MercuryWikis.DISCUSSIONS_AUTO);
    assertTrue(deleteAllOptionNotVisible(userWithPosts.getUserId()));
    cleanUp(post);
  }

  // VSTF

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.VSTF)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void vstfUserDesktopCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.VSTF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void vstfUserMobileCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  // HELPER

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.HELPER)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void helperUserDesktopCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.HELPER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void helperUserMobileCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  // STAFF

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.STAFF)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void staffUserDesktopCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.HELPER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserMobileCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  // MODERATOR

  @Test(groups = "discussions-deleteAllPostsByUserDesktop")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR, onWikia = MercuryWikis.DISCUSSIONS_MESSAGING)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void modUserDesktopDeleteAllOptionNotVisibleOnDifferentWiki() {
    PostEntity.Data post = setUp(MercuryWikis.DISCUSSIONS_MESSAGING);
    assertTrue(deleteAllOptionNotVisible(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = "discussions-deleteAllPostsByUserMobile")
  @Execute(asUser = User.DISCUSSIONS_MODERATOR, onWikia = MercuryWikis.DISCUSSIONS_MESSAGING)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void modUserMobileDeleteAllOptionNotVisibleOnDifferentWiki() {
    PostEntity.Data post = setUp(MercuryWikis.DISCUSSIONS_MESSAGING);
    assertTrue(deleteAllOptionNotVisible(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.STAFF)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void modUserDesktopCanDeleteAllPostsOnModdedWiki() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.HELPER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void modUserMobileCanDeleteAllPostsOnModdedWiki() {
    assertTrue(deleteAllAndReturnFirst().isDeleted());
  }

  // HELPER METHODS

  private boolean deleteAllOptionNotVisible(String userId) {
    return getDeleteAllButton(userId).isNotVisible();
  }

  private DeleteAllButton getDeleteAllButton(String userId) {
    return new UserPostsPage().open(userId).getDeleteAll();
  }

  private PostEntity deleteAllAndReturnFirst() {
    String postId = setUp().getId();
    getDeleteAllButton(userWithPosts.getUserId()).click().confirmAndWait();
    return new Post().findPostById(postId);
  }

  private PostEntity cancelAndReturnFirst() {
    String postId = setUp().getId();
    getDeleteAllButton(userWithPosts.getUserId()).click().cancelAndWait();
    return new Post().findPostById(postId);
  }

}
