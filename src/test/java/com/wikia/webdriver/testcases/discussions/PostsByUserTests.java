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
import com.wikia.webdriver.common.remote.operations.DiscussionsOperations;
import com.wikia.webdriver.common.remote.operations.GetSiteId;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.DeleteAllButton;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.PageWithPosts;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.*;

import java.util.List;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

public class PostsByUserTests extends NewTestTemplate {

  private User UserWithPosts = User.USER_6;
  private String siteId;


  @BeforeSuite
  private void setUp() {
    String wikiUrl = new UrlBuilder().getUrlForWiki(MercuryWikis.DISCUSSIONS_AUTO);
    GetSiteId site = new GetSiteId(wikiUrl + URLsContent.SPECIAL_VERSION);
    System.out.println(site.getSiteId() + "dupa");
    //DiscussionsOperations
    //  .using(UserWithPosts, driver)
    //  .createPostWithUniqueData(site.getSiteId());
  }

  @AfterSuite
  private void cleanUp() {
  }

  // hacky way to get rid of totally redundant loading of Special:Version
  @Override
  protected void loadFirstPage() {}

  /***
   * ANON
   */

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_AUTO)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void verifyDupa() {
    Assertion.assertFalse(true);
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_AUTO)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonDesktopDeleteAllOptionNotVisible() {
    Assertion.assertFalse(deleteAllOptionVisible(UserWithPosts.getUserId()));
  }

  @Test(groups = "discussions-deleteAllPostsByUser")
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.DISCUSSIONS_AUTO)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonMobileDeleteAllOptionNotVisible() {
    Assertion.assertFalse(deleteAllOptionVisible(UserWithPosts.getUserId()));
  }


  private boolean deleteAllOptionVisible(String userId) {
    UserPostsPage userPosts = new UserPostsPage().open(userId);
    return getDeleteAllButton(userPosts).isVisible();
  }

  private DeleteAllButton getDeleteAllButton(final UserPostsPage page) {
    return page.getDeleteAll();
  }

}
