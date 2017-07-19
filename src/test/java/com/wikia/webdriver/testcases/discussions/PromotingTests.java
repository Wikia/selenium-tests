package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Promoting;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import static com.wikia.webdriver.elements.mercury.components.discussions.common.DiscussionsConstants.DESKTOP_RESOLUTION;
import static com.wikia.webdriver.common.core.Assertion.assertTrue;
import static com.wikia.webdriver.common.core.Assertion.assertEquals;
import static com.wikia.webdriver.common.core.Assertion.assertStringContains;


@Execute(onWikia = MercuryWikis.FALLOUT)
@Test(groups = {"discussions-promoting"})
public class PromotingTests extends NewTestTemplate {

  private static final String MOBILE_PROMOTION_TEXT = "Wikia: Fallout 4 Fan App";
  private static final String DESKTOP_PROMOTION_TEXT =
    "Take your fandom with you, download the app today!";
  private static final String IOS_APP_TITLE = "Fandom Community for: Fallout";
  private static final String ANDROID_APP_TITLE = "Fandom: Fallout 4";

  /**
   * ANON ON DESKTOP SECTION
   */

  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanSeeAppPromotion() {
    Promoting promoting = findPromoting();
    assertTrue(promoting.isAppleLinkDisplayed());
    assertTrue(promoting.isGooglePlayLinkDisplayed());
    assertEquals(promoting.getPromotionAppText(), DESKTOP_PROMOTION_TEXT);
  }

  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickAppleLink() {
    findPromoting().clickAppleLinkInAppPromotion();
    assertAppPageOpened(IOS_APP_TITLE);
  }
  
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanClickGooglePlayLink() {
    findPromoting().clickGooglePlayLinkInAppPromotion();
    assertAppPageOpened(ANDROID_APP_TITLE);
  }

  /**
   * ANON ON MOBILE SECTION
   */

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void anonUserOnMobileCanSeeAppPromotion() {
    Promoting promoting = findPromoting();
    assertTrue(promoting.isMobileBannerDisplayed());
    assertStringContains(promoting.getPromotionAppMobileText(), MOBILE_PROMOTION_TEXT);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void anonUserOnMobileCanClickGooglePlayLink() {
    findPromoting().clickInstallOnMobileBanner();
    assertAppPageOpened(ANDROID_APP_TITLE);
  }

  /**
   * TESTING METHODS SECTION
   */

  private Promoting findPromoting() {
    String siteId = Discussions.excractSiteIdFromWikiName(MercuryWikis.FALLOUT);
    PostsListPage page = new PostsListPage().open(siteId);
    return page.getPromoting();
  }

  private void assertAppPageOpened(String appTitle) {
    assertTrue(driver.getTitle().contains(appTitle));
  }
}
