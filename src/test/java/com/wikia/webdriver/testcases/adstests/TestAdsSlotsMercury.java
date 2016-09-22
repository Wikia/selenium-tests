package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;
import org.testng.annotations.Test;

public class TestAdsSlotsMercury extends MobileTestTemplate {
  private static final String CREATIVE_IMAGE_URL = "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg";
  private static final String MOBILE_TOP_LEADERBOARD = "MOBILE_TOP_LEADERBOARD";
  private static final String MOBILE_IN_CONTENT = "MOBILE_IN_CONTENT";
  private static final String MOBILE_PREFOOTER = "MOBILE_PREFOOTER";
  private static final String SRC = "mobile";
  private static final String PORTABLE_INFOBOX = ".portable-infobox";
  private static final String ARTICLE_HEADER = ".wiki-page-header";
  private static final String ARTICLE_BODY = ".article-body";

  @Test(
      groups = "AdsSlotsMercury",
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "allSlots"
  )
  public void adsAllSlotsOnPage(String wikiName,
                                String article,
                                String adUnit) {

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_IN_CONTENT, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, SRC);
    ads.verifyImgAdLoadedInSlot(MOBILE_TOP_LEADERBOARD, CREATIVE_IMAGE_URL);
    ads.verifyImgAdLoadedInSlot(MOBILE_IN_CONTENT, CREATIVE_IMAGE_URL);
    ads.verifyImgAdLoadedInSlot(MOBILE_PREFOOTER, CREATIVE_IMAGE_URL);

    Assertion.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.MOBILE_BOTTOM_LB));
  }

  @Test(
      groups = "AdsSlotsMercury",
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "leaderboardAndPrefooterSlots"
  )
  public void adsLeaderboardAndPrefooterOnPage(String wikiName,
                                                String article,
                                                String adUnit) {

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, SRC);
    ads.verifyImgAdLoadedInSlot(MOBILE_TOP_LEADERBOARD, CREATIVE_IMAGE_URL);
    ads.verifyImgAdLoadedInSlot(MOBILE_PREFOOTER, CREATIVE_IMAGE_URL);
    ads.verifyNoSlotPresent(MOBILE_IN_CONTENT);
  }

  @Test(
      groups = "AdsSlotsMercury",
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "leaderboardAndInContentSlots"
  )
  public void adsLeaderboardAndInContentOnPage(String wikiName,
                                                String article,
                                                String adUnit) {

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_IN_CONTENT, SRC);
    ads.verifyImgAdLoadedInSlot(MOBILE_TOP_LEADERBOARD, CREATIVE_IMAGE_URL);
    ads.verifyImgAdLoadedInSlot(MOBILE_IN_CONTENT, CREATIVE_IMAGE_URL);
    ads.verifyNoSlotPresent(MOBILE_PREFOOTER);
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = "AdsSlotsMercury",
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "leaderboardSlotOnPageWithInfobox"
  )
  public void adsLeaderboardOnPageWithInfobox(String wikiName,
                                              String article,
                                              String adUnit) {

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    PortableInfobox infobox = new PortableInfobox();

    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);

    Assertion.assertTrue(
        ads.getElementTopPositionByCssSelector(AdsContent.getSlotSelector(AdsContent.MOBILE_TOP_LB))
        >= infobox.getElementBottomPositionByCssSelector(PORTABLE_INFOBOX)
    );
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @Test(
      groups = "AdsSlotsMercury",
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "leaderboardSlotOnPageWithoutInfobox"
  )
  public void adsLeaderboardOnPageWithoutInfobox(String wikiName,
                                                 String article,
                                                 String adUnit) {

    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);

    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);

    Assertion.assertTrue(
        ads.getElementTopPositionByCssSelector(AdsContent.getSlotSelector(AdsContent.MOBILE_TOP_LB))
        >= ads.getElementBottomPositionByCssSelector(ARTICLE_HEADER)
    );
  }

  @Test(
      groups = "AdsSlotsMercury",
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "mercuryConsecutivePageViews"
  )
  public void adsLeaderboardAndPrefooterOnConsecutivePageViews(String wikiName,
                                                                String firstArticle,
                                                                String secondArticle,
                                                                String thirdArticle,
                                                                String adUnit) {

    String testedPage = urlBuilder.getUrlForPath(wikiName, firstArticle);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, SRC);

    ads.scrollToPosition(ARTICLE_BODY);
    ads.mercuryNavigateToAnArticle(secondArticle);
    ads.waitTitleChangesTo(secondArticle);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);

    ads.scrollToPosition(ARTICLE_BODY);
    ads.mercuryNavigateToAnArticle(thirdArticle);
    ads.waitTitleChangesTo(thirdArticle);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, SRC);
  }
}
