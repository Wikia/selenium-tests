package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.*;


import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;


/**
 * Tests for deleting all posts by some user
 */
@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class DeleteAllPostsByUserTests extends NewTestTemplate {

  private User UserWithPosts = User.USER_6;
  private String siteId;

  /**
   *
   * @param wikiName wiki on which a post by `UserWithPosts` will be created
   * @return post that was created
   */
  @BeforeClass
  private PostEntity.Data setUp(String wikiName) {
    String wikiUrl = new UrlBuilder().getUrlForWiki(wikiName);
    siteId = Discussions.extractSiteIdFromMediaWiki(wikiUrl + URLsContent.SPECIAL_VERSION);
    return DiscussionsOperations
      .using(UserWithPosts, driver)
      .createPostWithUniqueData(siteId);
  }

  /**
   *
   * @param post to be deleted as staff user
   */
  private void cleanUp(PostEntity.Data post) {
    DiscussionsOperations.using(User.STAFF, driver).deletePost(post);
  }

  // hacky way to get rid of totally redundant loading of Special:Version
  @Override
  protected void loadFirstPage() {}

  /***
   * ANON
   */

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonDesktopDeleteAllOptionNotVisible() {
    PostEntity.Data post = setUp(MercuryWikis.DISCUSSIONS_AUTO);
    Assertion.assertTrue(deleteAllOptionNotVisible(UserWithPosts.getUserId()));
    cleanUp(post);
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonMobileDeleteAllOptionNotVisible() {
    Assertion.assertTrue(deleteAllOptionNotVisible(UserWithPosts.getUserId()));
  }

  /***
   *
   * REGULAR USER
   */

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.USER)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void regularUserDesktopDeleteAllOptionNotVisible() {
    Assertion.assertTrue(deleteAllOptionNotVisible(UserWithPosts.getUserId()));
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void regularUserMobileDeleteAllOptionNotVisible() {
    Assertion.assertTrue(deleteAllOptionNotVisible(UserWithPosts.getUserId()));
  }

  private boolean deleteAllOptionNotVisible(String userId) {
    UserPostsPage userPosts = new UserPostsPage().open(userId);
    return userPosts.getDeleteAll().isNotVisible();
  }

}
