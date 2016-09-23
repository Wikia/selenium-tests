package com.wikia.webdriver.testcases.adstests;

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
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestUap extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1920, 1080);

  private static final String MOBILE_HEADER = "#Header";
  private static final String MOBILE_ARTICLE_FOOTER = ".article-footer";
  private static final String MOBILE_BOTTOM_LEADERBOARD = ".ember-view.ad-slot-wrapper.mobile-bottom-leaderboard";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsUapOasis",
      groups = "AdsUapOasis"
  )
  public void adsUapOasis(Page page,
                          List<Map<String, Object>> atfSlots,
                          List<Map<String, Object>> btfSlots) {
    AdsBaseObject ads = new AdsBaseObject(driver, urlBuilder.getUrlForPage(page), DESKTOP_SIZE);
    verifySlotsUnblocked(ads, atfSlots);
    verifySlotsBlocked(ads, btfSlots);
    ads.triggerComments();
    verifySlotsUnblocked(ads, ListUtils.union(atfSlots, btfSlots));
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
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

    ads.scrollToPosition(MOBILE_HEADER);
    Assertion.assertTrue(ads.isMobileInContentAdDisplayed(), "Mobile in content ad is not dispalyed");
    verifySlotsUnblocked(ads, mobileTopLeaderboard);
    verifySlotsUnblocked(ads, mobileInContent);
    verifySlotsBlocked(ads, mobilePrefooter);
    verifySlotsBlocked(ads, mobileBottomLeaderboard);

    ads.scrollToPosition(MOBILE_ARTICLE_FOOTER);
    Assertion.assertTrue(ads.isMobilePrefooterAdDisplayed(), "Mobile prefooter ad is not dispalyed");
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
      Dimension slotSize = (Dimension) slotData.get("slotSize");

      ads.verifyLineItemId(slotName, Integer.valueOf(slotData.get("lineItemId").toString()));
      ads.verifyIframeSize(slotName, slotData.get("src").toString(),
                           slotSize.getWidth(), slotSize.getHeight());
    }
  }
}
