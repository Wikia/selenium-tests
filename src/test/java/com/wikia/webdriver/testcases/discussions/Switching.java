package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

@Test(groups = {"Discussions", "PostList"})
public class Switching extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = {"Switching_001"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSwitchBetweenLatestAndTrendingTab() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  /**
   * LOGGED IN USER ON DESKTOP SECTION
   */

  @Test(groups = {"Switching_002"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedUserOnDesktopCanSwitchBetweenLatestAndTrendingTab() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void userCanSwitchBetweenLatestAndTrendingTab() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickLatestTabOnDesktop();
    postsList.waitForLoadingOverlayToDisappear();

    Assertion.assertTrue(driver.getCurrentUrl().contains("latest"));

    postsList.clickTrendingTabOnDesktop();
    postsList.waitForLoadingOverlayToDisappear();

    Assertion.assertTrue(driver.getCurrentUrl().contains("trending"));
  }

}
