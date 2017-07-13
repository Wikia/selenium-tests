package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.apache.commons.collections.ListUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestUap extends TemplateNoFirstLoad {

  private static final String MOBILE_IN_CONTENT = ".mobile-in-content";
  private static final String MOBILE_PREFOOTER = ".mobile-prefooter";
  private static final String MOBILE_BOTTOM_LEADERBOARD = ".mobile-bottom-leaderboard";
  private static final String ARTICLE_MIDDLE_SECTION_SELECTOR = "#ArticleMidSection.mw-headline";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsUapOasis",
      groups = "AdsUapOasis"
  )
  public void adsUapOasis(Page page,
                          List<Map<String, Object>> atfSlots,
                          List<Map<String, Object>> btfSlots) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), WindowSize.DESKTOP);
    verifySlotsUnblocked(ads, atfSlots);
    verifySlotsBlocked(ads, btfSlots);
    ads.triggerComments();
    ads.scrollToPosition(ARTICLE_MIDDLE_SECTION_SELECTOR);
    verifySlotsUnblocked(ads, ListUtils.union(atfSlots, btfSlots));
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.NEXUS_5X
  )
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsUapMercury",
      groups = "AdsUapMercury"
  )
  public void adsUapMercury(Page page,
                            List<Map<String, Object>> mobileTopLeaderboard,
                            List<Map<String, Object>> mobileInContent,
                            List<Map<String, Object>> mobilePrefooter,
                            List<Map<String, Object>> mobileBottomLeaderboard) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page));
    verifySlotsUnblocked(ads, mobileTopLeaderboard);
    verifySlotsBlocked(ads, mobileInContent);
    verifySlotsBlocked(ads, mobilePrefooter);
    verifySlotsBlocked(ads, mobileBottomLeaderboard);

    ads.scrollToPosition(MOBILE_IN_CONTENT);
    Assertion.assertTrue(ads.isMobileInContentAdDisplayed(), "Mobile in content ad is not displayed");
    verifySlotsUnblocked(ads, mobileTopLeaderboard);
    verifySlotsUnblocked(ads, mobileInContent);
    verifySlotsBlocked(ads, mobilePrefooter);
    verifySlotsBlocked(ads, mobileBottomLeaderboard);

    ads.scrollToPosition(MOBILE_PREFOOTER);
    Assertion.assertTrue(ads.isMobilePrefooterAdDisplayed(), "Mobile prefooter ad is not displayed");
    ads.scrollToPosition(MOBILE_BOTTOM_LEADERBOARD);
    Assertion.assertTrue(ads.isMobileBottomLeaderboardAdDisplayed(), "Mobile bottom leaderboard ad is not dispalyed");
    verifySlotsUnblocked(ads, mobileTopLeaderboard);
    verifySlotsUnblocked(ads, mobileInContent);
    verifySlotsUnblocked(ads, mobilePrefooter);
    verifySlotsUnblocked(ads, mobileBottomLeaderboard);
  }

  private void verifySlotsBlocked(AdsBaseObject ads, List<Map<String, Object>> slotsData) {
    for (Map<String, Object> slotData : slotsData) {
      String slotName = slotData.get("slotName").toString();

      ads.verifyNoAd(AdsContent.getSlotSelector(slotName));
    }
  }

  private void verifySlotsUnblocked(AdsBaseObject ads, List<Map<String, Object>> slotsData) {
    for (Map<String, Object> slotData : slotsData) {
      String slotName = slotData.get("slotName").toString();
      String slotSelector = AdsContent.getSlotSelector(slotName);

      ads.wait.forElementPresent(By.cssSelector(slotSelector));
      ads.scrollToPosition(slotSelector);
      Dimension slotSize = (Dimension) slotData.get("slotSize");

      ads.verifyLineItemId(slotName, Integer.valueOf(slotData.get("lineItemId").toString()));
      ads.verifyIframeSize(slotName, slotData.get("src").toString(),
                           slotSize.getWidth(), slotSize.getHeight());
    }
  }
}
