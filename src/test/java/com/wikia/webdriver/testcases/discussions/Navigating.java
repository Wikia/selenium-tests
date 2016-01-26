package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

/**
 * @ownership Social Wikia
 */
@Test(groups = {"Discussions", "Navigating"})
public class Navigating extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = {"Navigating_001"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = MOBILE_RESOLUTION)
  public void anonUserOnMobileCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = {"Navigating_002"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickBackToWiki() {
    backToWiki();
  }

  @Test(groups = {"Navigating_003"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickAvatar() {
    clickAvatarLoadsUserPage();
  }

  @Test(groups = {"Navigating_004"})
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickUsername() {
    clickUsernameLoadsUserPage();
  }

  @Test(groups = {"Navigating_005"})
  @Execute(onWikia = "fallout")
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeAppPromotion() {
    discussionsAppPromotionUnitPresentOnPage();
  }

  @Test(groups = {"Navigating_006"})
  @Execute(onWikia = "fallout")
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickAppleLinkAppPromotion() {
    appleLinkRedirectsProperly();
  }

  @Test(groups = {"Navigating_007"})
  @Execute(onWikia = "fallout")
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickGooglePlayLinkAppPromotion() {
    googlePlayLinkRedirectsProperly();
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

  public void discussionsAppPromotionUnitPresentOnPage() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertTrue(postsList.isAppleLinkDisplayed());
    Assertion.assertTrue(postsList.isGooglePlayLinkDisplayed());
    Assertion.assertEquals(postsList.isPromotionAppTextDisplayed(), "Stay up to date on the go. Get the app now!");
  }

  public void appleLinkRedirectsProperly() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickAppleLinkInAppPromotion();
    String newWindow = driver.getWindowHandles().toArray()[1].toString();
    driver.switchTo().window(newWindow);
    Assertion.assertTrue(driver.getTitle().contains("Wikia Fan App for: Fallout"));
  }

  public void googlePlayLinkRedirectsProperly() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickGooglePlayLinkInAppPromotion();
    String newWindow = driver.getWindowHandles().toArray()[1].toString();
    driver.switchTo().window(newWindow);
    Assertion.assertTrue(driver.getTitle().contains("Wikia: Fallout"));
  }

}
