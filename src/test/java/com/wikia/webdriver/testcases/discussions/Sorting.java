package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

@Test(groups="discussions")
@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
public class Sorting extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileCanSortPostsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanSortPostList")
  @RelatedIssue(issueID = "XW-1047")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSortPostList() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanSortPostsList")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSortPostsList() {
    userCanSwitchBetweenLatestAndTrendingInDropdown();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-loggedUserOnDesktopCanSwitchBetweenLatestAndTrendingTab", enabled = false)
  @RelatedIssue(issueID = "XW-1047")
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void loggedUserOnDesktopCanSwitchBetweenLatestAndTrendingTab() {
    userCanSwitchBetweenLatestAndTrendingTab();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void userCanSwitchBetweenLatestAndTrendingInDropdown() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertTrue(postsList.clickSortButtonOnMobile().isSortListVisibleMobile());
    postsList.clickLatestLinkOnMobile();
    postsList.clickApplyButton();
    new Loading(driver).handleAsyncPageReload();

    postsList.clickSortButtonOnMobile();
    postsList.clickTrendingOptionInSortMenu();
    postsList.clickApplyButton();
    new Loading(driver).handleAsyncPageReload();
  }

  private void userCanSwitchBetweenLatestAndTrendingTab() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickLatestTabOnDesktop();
    new Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("latest"));

    postsList.clickTrendingTabOnDesktop();
    new Loading(driver).handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains("trending"));
  }
}
