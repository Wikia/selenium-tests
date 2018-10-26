package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.DeleteAllButton;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.DeleteDialog;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.Post;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.UserPostsPage;
import org.testng.annotations.*;


import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static com.wikia.webdriver.common.core.Assertion.assertFalse;


/**
 * Tests for deleting all posts by some user
 */
@Execute(onWikia = MobileWikis.DISCUSSIONS_4)
public class DeleteAllPostsByUserTests extends NewTestTemplate {

  private static final User userWithPosts = User.USER_12;
  private static final String GENERAL = "discussions-deleteAllPostsByUser";
  private static final String DESKTOP = "discussions-deleteAllPostsByUserDesktop";
  private static final String MOBILE = "discussions-deleteAllPostsByUserMobile";
  private String siteId;

  // FIXTURES

  /**
   *
   * @param wikiName wiki on which a post by `userWithPosts` will be created
   * @return post that was created
   */
  private PostEntity.Data setUp(String wikiName) {
    siteId = Utils.excractSiteIdFromWikiName(wikiName);
    return DiscussionsClient
      .using(userWithPosts, driver)
      .createPostWithUniqueData(siteId);
  }

  private PostEntity.Data setUp() {
    return setUp(MobileWikis.DISCUSSIONS_4);
  }

  /**
   *
   * @param post to be deleted as staff user
   */
  private void cleanUp(PostEntity.Data post) {
    DiscussionsClient.using(User.STAFF, driver).deletePost(post, this.siteId);
  }

  // GENERAL - no specific user permissions

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void clickDeleteAllDesktopDisplaysConfirmationDialog() {
    DeleteDialog confirmationDialog = getDeleteAllButtonDesktop(userWithPosts.getUserId()).click();
    assertTrue(confirmationDialog.isVisible());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void clickDeleteAllMobileDisplaysConfirmationDialog() {
    DeleteDialog confirmationDialog = getDeleteAllButtonMobile(userWithPosts.getUserId()).click();
    assertTrue(confirmationDialog.isVisible());
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void clickCancelDeleteAllDesktopHidesConfirmationDialog() {
    String postId = setUp().getFirstPostId();
    getDeleteAllButtonDesktop(userWithPosts.getUserId()).click().cancelAndWait();
    assertFalse(new Post().findPostById(postId).isDeleted());
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void clickCancelDeleteAllMobileHidesConfirmationDialog() {
    String postId = setUp().getFirstPostId();
    getDeleteAllButtonMobile(userWithPosts.getUserId()).click().cancelAndWait();
    assertFalse(new Post().findPostById(postId).isDeleted());
  }

  // ANON

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonDesktopDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp();
    assertTrue(deleteAllOptionNotVisibleDesktop(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonMobileDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp();
    assertTrue(deleteAllOptionNotVisibleMobile(userWithPosts.getUserId()));
    cleanUp(post);
  }

  // REGULAR USER

  @Test(groups = DESKTOP)
  @Execute(asUser = User.USER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void regularUserDesktopDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp();
    assertTrue(deleteAllOptionNotVisibleDesktop(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void regularUserMobileDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp();
    assertTrue(deleteAllOptionNotVisibleMobile(userWithPosts.getUserId()));
    cleanUp(post);
  }

  // VSTF

  @Test(groups = GENERAL)
  @Execute(asUser = User.VSTF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void vstfUserDesktopCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirstDesktop().isDeleted());
  }

  @Test(groups = GENERAL)
  @Execute(asUser = User.VSTF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void vstfUserMobileCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirstMobile().isDeleted());
  }

  // HELPER

  @Test(groups = GENERAL)
  @Execute(asUser = User.HELPER)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void helperUserDesktopCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirstDesktop().isDeleted());
  }

  @Test(groups = GENERAL)
  @Execute(asUser = User.HELPER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void helperUserMobileCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirstMobile().isDeleted());
  }

  // STAFF

  @Test(groups = GENERAL)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserDesktopCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirstDesktop().isDeleted());
  }

  @Test(groups = GENERAL)
  @Execute(asUser = User.HELPER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserMobileCanDeleteAllPosts() {
    assertTrue(deleteAllAndReturnFirstMobile().isDeleted());
  }

  // MODERATOR

  @Test(groups = DESKTOP)
  @Execute(asUser = User.DISCUSSIONS_MODERATOR, onWikia = MobileWikis.DISCUSSIONS_MESSAGING)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void modUserDesktopDeleteAllOptionNotVisibleOnDifferentWiki() {
    PostEntity.Data post = setUp(MobileWikis.DISCUSSIONS_MESSAGING);
    assertTrue(deleteAllOptionNotVisibleDesktop(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.DISCUSSIONS_MODERATOR, onWikia = MobileWikis.DISCUSSIONS_MESSAGING)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void modUserMobileDeleteAllOptionNotVisibleOnDifferentWiki() {
    PostEntity.Data post = setUp(MobileWikis.DISCUSSIONS_MESSAGING);
    assertTrue(deleteAllOptionNotVisibleMobile(userWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = GENERAL)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void modUserDesktopCanDeleteAllPostsOnModdedWiki() {
    assertTrue(deleteAllAndReturnFirstDesktop().isDeleted());
  }

  @Test(groups = GENERAL)
  @Execute(asUser = User.HELPER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void modUserMobileCanDeleteAllPostsOnModdedWiki() {
    assertTrue(deleteAllAndReturnFirstMobile().isDeleted());
  }

  // HELPER METHODS

  private boolean deleteAllOptionNotVisibleMobile(String userId) {
    return getDeleteAllButtonMobile(userId).isNotVisible();
  }

  private boolean deleteAllOptionNotVisibleDesktop(String userId) {
    return getDeleteAllButtonDesktop(userId).isNotVisible();
  }

  private DeleteAllButton getDeleteAllButtonDesktop(String userId) {
    return new UserPostsPage().open(userId).getDeleteAll();
  }

  private DeleteAllButton getDeleteAllButtonMobile(String userId) {
    return new UserPostsPage().open(userId).expandModeration().getDeleteAll();
  }

  private PostEntity deleteAllAndReturnFirstDesktop() {
    String postId = setUp().getFirstPostId();
    getDeleteAllButtonDesktop(userWithPosts.getUserId()).click().confirmAndWait();
    return new Post().findPostById(postId);
  }

  private PostEntity deleteAllAndReturnFirstMobile() {
    String postId = setUp().getFirstPostId();
    getDeleteAllButtonMobile(userWithPosts.getUserId()).click().confirmAndWait();
    return new Post().findPostById(postId);
  }

}
