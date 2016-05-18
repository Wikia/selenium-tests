package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

/**
 * @ownership Social Wikia
 */

@Test(groups = "discussions")
@Execute(onWikia = "fallout")
public class Promoting extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  @Test(groups = "discussions-anonUserOnDesktopCanSeeAppPromotion")
  @Execute(onWikia = "fallout")
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeAppPromotion() {
    discussionsAppPromotionUnitPresentOnPage();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanClickAppleLinkAppPromotion")
  @Execute(onWikia = "fallout")
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickAppleLinkAppPromotion() {
    appleLinkRedirectsProperly();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanClickGooglePlayLinkAppPromotion")
  @Execute(onWikia = "fallout")
  @InBrowser(browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickGooglePlayLinkAppPromotion() {
    googlePlayLinkRedirectsProperly();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void discussionsAppPromotionUnitPresentOnPage() {
    PostsListPage postsList = new PostsListPage(driver).open("3035");
    Assertion.assertTrue(postsList.isAppleLinkDisplayed());
    Assertion.assertTrue(postsList.isGooglePlayLinkDisplayed());
    Assertion.assertEquals(postsList.isPromotionAppTextDisplayed(), "Stay up to date on the go. Get the app now!");
  }

  private void appleLinkRedirectsProperly() {
    PostsListPage postsList = new PostsListPage(driver).open("3035");
    postsList.clickAppleLinkInAppPromotion();
    String newWindow = driver.getWindowHandles().toArray()[1].toString();
    driver.switchTo().window(newWindow);
    Assertion.assertTrue(driver.getTitle().contains("Wikia Fan App for: Fallout"));
  }

  private void googlePlayLinkRedirectsProperly() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickGooglePlayLinkInAppPromotion();
    String newWindow = driver.getWindowHandles().toArray()[1].toString();
    driver.switchTo().window(newWindow);
    Assertion.assertTrue(driver.getTitle().contains("Wikia: Fallout"));
  }



}
