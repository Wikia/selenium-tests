package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;
import org.testng.annotations.Test;

@InBrowser(
  browser = Browser.CHROME,
  emulator = Emulator.GOOGLE_NEXUS_5
)
@Test(groups = "AdsSlotsMercury")
public class TestAdsSlotsMercury extends MobileTestTemplate {
  private static final String CREATIVE_IMAGE_URL = "googlesyndication.com/simgad/8216620376696319112";
  private static final String PORTABLE_INFOBOX = ".portable-infobox";
  private static final String ARTICLE_HEADER = ".wiki-page-header";
  private static final String ARTICLE_BODY = ".article-body";
  private static final String ARTICLE_FOOTER = ".article-footer";

  @Test
  public void adsAllSlotsOnPage() {
    Page page = new Page("project43", "SyntheticTests/Mercury/Slots/AllSlots");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.verifySlotExpanded(AdsContent.MOBILE_TOP_LB);
    ads.verifySlotExpanded(AdsContent.MOBILE_AD_IN_CONTENT);
    ads.scrollToPosition(ARTICLE_FOOTER);
    ads.verifyImgAdLoadedInSlot(AdsContent.MOBILE_TOP_LB, CREATIVE_IMAGE_URL);
    ads.verifyImgAdLoadedInSlot(AdsContent.MOBILE_AD_IN_CONTENT, CREATIVE_IMAGE_URL);
    ads.verifyImgAdLoadedInSlot(AdsContent.MOBILE_BOTTOM_LB, CREATIVE_IMAGE_URL);
  }

  @Test
  public void adsLeaderboardOnPageWithInfobox() {
    Page page = new Page("project43", "SyntheticTests/Mercury/Slots/Leaderboard_below_infobox");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    PortableInfobox infobox = new PortableInfobox();

    ads.waitForPageLoadedWithGpt();
    ads.verifySlotExpanded(AdsContent.MOBILE_TOP_LB);

    int adPosition = ads.getElementTopPositionByCssSelector(AdsContent.getSlotSelector(AdsContent.MOBILE_TOP_LB));
    int pageElementPosition = infobox.getElementBottomPositionByCssSelector(PORTABLE_INFOBOX);

    PageObjectLogging.log("Ad top position", String.valueOf(adPosition), true);
    PageObjectLogging.log("Infobox bottom position", String.valueOf(pageElementPosition), true);

    Assertion.assertTrue(
        adPosition >= pageElementPosition,
        "Verify if ad top position is >= infobox bottom position (ad below infobox)"
    );
  }

  @Test
  public void adsLeaderboardOnPageWithoutInfobox() {
    Page page = new Page("project43", "SyntheticTests/Mercury/Slots/Leaderboard_below_page_header");
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());

    ads.waitForPageLoadedWithGpt();
    ads.verifySlotExpanded(AdsContent.MOBILE_TOP_LB);

    int adPosition = ads.getElementTopPositionByCssSelector(AdsContent.getSlotSelector(AdsContent.MOBILE_TOP_LB));
    int pageElementPosition = ads.getElementBottomPositionByCssSelector(ARTICLE_HEADER);

    PageObjectLogging.log("Ad top position", String.valueOf(adPosition), true);
    PageObjectLogging.log("Page header bottom position", String.valueOf(pageElementPosition), true);

    Assertion.assertTrue(
        adPosition >= pageElementPosition,
        "Ad top position >= page header position (ad below page header)"
    );
  }

  @Test
  public void leaderboardsOnConsecutivePageViews() {
    Page page = new Page("project43", "SyntheticTests/Mercury/Slots/ConsecutivePageViews/1");
    String secondArticle = "SyntheticTests/Mercury/Slots/ConsecutivePageViews/2";
    String thirdArticle = "SyntheticTests/Mercury/Slots/ConsecutivePageViews/3";

    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, page.getUrl());
    ads.scrollToFooter();
    ads.verifySlotExpanded(AdsContent.MOBILE_TOP_LB);
    ads.verifySlotExpanded(AdsContent.MOBILE_BOTTOM_LB);

    ads.scrollToPosition(ARTICLE_BODY);
    ads.mercuryNavigateToAnArticle(secondArticle);
    ads.waitTitleChangesTo(secondArticle);
    ads.scrollToFooter();
    ads.verifySlotExpanded(AdsContent.MOBILE_TOP_LB);
    ads.verifySlotExpanded(AdsContent.MOBILE_BOTTOM_LB);

    ads.scrollToPosition(ARTICLE_BODY);
    ads.mercuryNavigateToAnArticle(thirdArticle);
    ads.waitTitleChangesTo(thirdArticle);
    ads.scrollToFooter();
    ads.verifySlotExpanded(AdsContent.MOBILE_TOP_LB);
    ads.verifySlotExpanded(AdsContent.MOBILE_BOTTOM_LB);
  }
}
