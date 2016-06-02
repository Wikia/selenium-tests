package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.BackButtons;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class NavigatingTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanClickUsername")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanClickBackToWiki", enabled = false)
  @RelatedIssue(issueID = "SOC-2567")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickBackToWiki() {
    backToWiki();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanClickAvatar", enabled = false)
  @RelatedIssue(issueID = "SOC-2567")
  @Execute(asUser = User.ANONYMOUS, onWikia = MercuryWikis.MEDIAWIKI_119)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickAvatar() {
    clickAvatarLoadsUserPage();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanClickUsername")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void backToWiki() {
    BackButtons backButtons = new PostsListPage().open().getBackButtons();
    backButtons.clickBackToWikiLink();

    Assertion.assertEquals(driver.getCurrentUrl(), wikiURL);
  }

  private void clickAvatarLoadsUserPage() {
    Post post = new PostsListPage().open().getPost();
    post.clickUserAvatar();

    Assertion.assertTrue(
            driver.getCurrentUrl().contains(
                    URLsContent.USER_PROFILE.replace("%userName%", "")));
  }

  private void clickUsernameLoadsUserPage() {
    Post post = new PostsListPage().open().getPost();
    post.clickUsernameLink();

    Assertion.assertTrue(
            driver.getCurrentUrl().contains(
                    URLsContent.USER_PROFILE.replace("%userName%", "")));
  }
 }
