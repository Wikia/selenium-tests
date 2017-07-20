package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_5)
@Test(groups = {"discussions-navigation"})
public class NavigatingTests extends NewTestTemplate {

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanClickUsername")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(enabled = false, groups = "discussions-anonUserOnDesktopCanClickAvatar")
  @RelatedIssue(issueID = "SOC-2301")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanClickAvatar() {
    clickAvatarLoadsUserPage();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanClickUsername")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void clickAvatarLoadsUserPage() {
    PostsListPage page = new PostsListPage().open();
    page.getPost().clickUserAvatar();

    Assertion.assertTrue(
            driver.getCurrentUrl().contains(
                    URLsContent.USER_PROFILE.replace("%userName%", "")));
  }

  private void clickUsernameLoadsUserPage() {
    PostsListPage page = new PostsListPage().open();
    page.getPost().clickUsernameLink();

    Assertion.assertTrue(
            driver.getCurrentUrl().contains(
                    URLsContent.USER_PROFILE.replace("%userName%", "")));
  }
 }
