package com.wikia.webdriver.testcases.desktop.adstests;

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

  private static final String MOBILE_IN_CONTENT = "#incontent_boxad_1";
  private static final String RESOLVED_STATE = "resolved_state=0";
  private static final String ARTICLE_MIDDLE = "#ArticleMidSection, #Header";
  private static final String ARTICLE_FOOTER = ".article-footer";

  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adsUapOasis", groups = "AdsUapOasis")
  public void adsUapOasis(
      Page page, List<Map<String, Object>> atfSlots, List<Map<String, Object>> btfSlots
  ) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl(RESOLVED_STATE), WindowSize.DESKTOP);
    verifySlotsUnblocked(ads, atfSlots, false);
    verifySlotsBlocked(ads, btfSlots);
    verifySlotsUnblocked(ads, ListUtils.union(atfSlots, btfSlots), false);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5_DEFAULT // FIXME: use default emulator if mobile flag will be reverted
  )
  @Test(dataProviderClass = AdsDataProvider.class, dataProvider = "adsUapMercury", groups = "AdsUapMercury")
  public void adsUapMercury(
      Page page,
      List<Map<String, Object>> mobileTopLeaderboard,
      List<Map<String, Object>> mobileInContent,
      List<Map<String, Object>> mobileBottomLeaderboard
  ) {
    AdsBaseObject ads = new AdsBaseObject(page.getUrl());
    verifySlotsUnblocked(ads, mobileTopLeaderboard, true);
    if (ads.hasTopBoxad()) {
      verifySlotsBlocked(ads, AdsContent.TOP_BOXAD);
    } else {
      verifySlotsBlocked(ads, AdsContent.MOBILE_AD_IN_CONTENT);
    }
    verifySlotsBlocked(ads, AdsContent.MOBILE_BOTTOM_LB);

    ads.scrollTo(ARTICLE_MIDDLE);
    if (ads.hasTopBoxad()) {
      ads.scrollTo(AdsContent.TOP_BOXAD);
      Assertion.assertTrue(ads.checkSlotOnPageLoaded(AdsContent.TOP_BOXAD),
              "Mobile top_boxad ad is not displayed"
      );
    } else {
      ads.scrollTo(MOBILE_IN_CONTENT);
      Assertion.assertTrue(ads.isMobileInContentAdDisplayed(),
                           "Mobile in content ad is not displayed"
      );
      verifySlotsUnblocked(ads, mobileInContent, true);
    }

    ads.scrollTo(ARTICLE_FOOTER);
    verifySlotsUnblocked(ads, mobileBottomLeaderboard, true);

    ads.scrollToRecirculationPrefooter();
    ads.scrollToPosition(By.id(AdsContent.MOBILE_BOTTOM_LB));
    Assertion.assertTrue(ads.isMobileBottomLeaderboardAdDisplayed(),
                         "Mobile bottom leaderboard ad is not dispalyed"
    );
    verifySlotsUnblocked(ads, mobileTopLeaderboard, true);
    verifySlotsUnblocked(ads, mobileInContent, true);
    verifySlotsUnblocked(ads, mobileBottomLeaderboard, false);
  }

  private void verifySlotsBlocked(AdsBaseObject ads, String slotName) {
    ads.verifyNoAdWithoutTrigger(AdsContent.getSlotSelector(slotName));
  }

  private void verifySlotsUnblocked(AdsBaseObject ads, List<Map<String, Object>> slotsData, Boolean isMobile) {
    for (Map<String, Object> slotData : slotsData) {
      String slotName = slotData.get("slotName").toString();
      Dimension slotSize = (Dimension) slotData.get("slotSize");

      ads.checkSlotOnPageLoaded(slotName, isMobile);
      ads.verifyLineItemId(slotName, slotData.get("lineItemId").toString());
      ads.verifyIframeSize(slotName, slotSize.getWidth(), slotSize.getHeight());
    }
  }
}
