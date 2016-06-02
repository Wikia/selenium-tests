package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Promoting;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;

import org.testng.annotations.Test;

@Execute(onWikia = "fallout")
public class PromotingTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanSeeAppPromotion")
  @Execute(onWikia = "fallout")
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeAppPromotion() {
    discussionsAppPromotionUnitPresentOnPage();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanClickAppleLinkAppPromotion")
  @Execute(onWikia = "fallout")
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickAppleLinkAppPromotion() {
    appleLinkRedirectsProperly();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanClickGooglePlayLinkAppPromotion")
  @Execute(onWikia = "fallout")
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickGooglePlayLinkAppPromotion() {
    googlePlayLinkRedirectsProperly();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void discussionsAppPromotionUnitPresentOnPage() {
    Promoting promoting = new PostsListPage().open("3035").getPromoting();

    Assertion.assertTrue(promoting.isAppleLinkDisplayed());
    Assertion.assertTrue(promoting.isGooglePlayLinkDisplayed());
    Assertion.assertEquals(promoting.isPromotionAppTextDisplayed(), "Stay up to date on the go. Get the app now!");
  }

  private void appleLinkRedirectsProperly() {
    Promoting postsList = new PostsListPage().open("3035").getPromoting();
    postsList.clickAppleLinkInAppPromotion();
    String newWindow = driver.getWindowHandles().toArray()[1].toString();
    driver.switchTo().window(newWindow);

    Assertion.assertTrue(driver.getTitle().contains("Wikia Fan App for: Fallout"));
  }

  private void googlePlayLinkRedirectsProperly() {
    Promoting postsList = new PostsListPage().open("3035").getPromoting();
    postsList.clickGooglePlayLinkInAppPromotion();
    String newWindow = driver.getWindowHandles().toArray()[1].toString();
    driver.switchTo().window(newWindow);

    Assertion.assertTrue(driver.getTitle().contains("Wikia: Fallout"));
  }
}
