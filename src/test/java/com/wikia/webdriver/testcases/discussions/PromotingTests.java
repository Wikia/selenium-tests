package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.Promoting;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import org.testng.annotations.Test;

import static com.wikia.webdriver.common.core.Assertion.assertStringContains;
import static com.wikia.webdriver.common.core.Assertion.assertTrue;


@Execute(onWikia = MobileWikis.FALLOUT)
@Test(groups = {"discussions-promoting"})
public class PromotingTests extends NewTestTemplate {

  private static final String MOBILE_PROMOTION_TEXT = "Wikia: Fallout 4 Fan App";
  private static final String ANDROID_APP_TITLE = "FANDOM for: Fallout 4";


  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanSeeAppPromotion() {
    Promoting promoting = new PostsListPage().open().getPromoting();
    assertTrue(promoting.isMobileBannerDisplayed());
    assertStringContains(promoting.getPromotionAppMobileText(), MOBILE_PROMOTION_TEXT);
  }

  // this test has to use a browser that is supported by Google Play website
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.NEXUS_5X)
  public void anonUserOnMobileCanClickGooglePlayLink() {
    new PostsListPage().open().getPromoting().clickInstallOnMobileBanner();
    assertTrue(driver.getTitle().contains(ANDROID_APP_TITLE));
  }

}
