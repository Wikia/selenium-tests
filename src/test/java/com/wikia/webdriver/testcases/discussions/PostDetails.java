package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostDetailsPage;

import org.testng.annotations.Test;

/**
 * @ownership Social Wikia
 */
public class PostDetails extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  /**
   * LOGGED IN USERS ON MOBILE SECTION
   */

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserOnMobileCanSeePostDetailsList() {
    postDetailsListLoads();
  }

  /**
   * LOGGED IN USERS ON DESKTOP SECTION
   */

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserOnDesktopCanSeePostDetailsList() {
    postDetailsListLoads();
  }
  /**
   * TESTING METHODS SECTION
   */

  public void postDetailsListLoads() {
    PostDetailsPage postDetails = new PostDetailsPage(driver).open();
    Assertion.assertFalse(postDetails.isPostDetailsListEmpty());
  }
}
