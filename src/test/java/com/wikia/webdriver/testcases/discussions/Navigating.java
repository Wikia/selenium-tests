package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

public class Navigating extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = {"Discussions", "PostList_004"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = {"Discussions", "PostList_008"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickBackToWiki() {
    backToWiki();
  }

  @Test(groups = {"Discussions", "PostList_009"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickAvatar() {
    clickAvatarLoadsUserPage();
  }

  @Test(groups = {"Discussions", "PostList_010"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void backToWiki() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickBackToWikiLink();
    postsList.verifyUrl(wikiURL);
  }

  public void clickAvatarLoadsUserPage() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickUserAvatar();
    Assertion.assertTrue(postsList.isUserPageHeaderVisible());
  }

  public void clickUsernameLoadsUserPage() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickUsernameLink();
    Assertion.assertTrue(postsList.isUserPageHeaderVisible());
  }
}
