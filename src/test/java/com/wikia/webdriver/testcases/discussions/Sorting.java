package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

public class Sorting extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = {"Discussions", "PostList_002"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanSortPostsList() {
    userCanSortPostsList();
  }

  /**
   * LOGGED IN USER ON MOBILE SECTION
   */

  @Test(groups = {"Discussions", "PostList_011"})
  @Execute(asUser = User.USER_3)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void loggedInUserOnMobileCanSortPostsList() {
    userCanSortPostsList();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void userCanSortPostsList() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertTrue(postsList.clickSortButtonOnMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickTrendingLinkOnMobile().getSortButtonLabel(), "Trending");
    postsList.waitForLoadingOverlayToDisappear();
    Assertion.assertTrue(postsList.clickSortButtonOnMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickLatestLinkOnMobile().getSortButtonLabel(), "Latest");
  }
}
