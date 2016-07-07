package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.elements.mercury.old.PortableInfoboxObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.testng.annotations.Test;

public class TestAdsSlotsMercury extends MobileTestTemplate {
  private static final String CREATIVE_IMAGE_URL = "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg";
  private static final String MOBILE_TOP_LEADERBOARD = "MOBILE_TOP_LEADERBOARD";
  private static final String MOBILE_IN_CONTENT = "MOBILE_IN_CONTENT";
  private static final String MOBILE_PREFOOTER = "MOBILE_PREFOOTER";
  private static final String SRC = "mobile";

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

    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);

    Point leaderboardLocation = driver.findElement(By.id(MOBILE_TOP_LEADERBOARD)).getLocation();
    Point infoboxLocation = driver.findElement(By.className("portable-infobox")).getLocation();

    Assertion.assertTrue(leaderboardLocation.getY() > infoboxLocation.getY());
  }

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

    Point leaderboardLocation = driver.findElement(By.id(MOBILE_TOP_LEADERBOARD)).getLocation();
    Point pageHeader = driver.findElement(By.className("wiki-page-header")).getLocation();

    Assertion.assertTrue(leaderboardLocation.getY() > pageHeader.getY());
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

    ads.mercuryNavigateToAnArticle(secondArticle);
    ads.waitTitleChangesTo(secondArticle);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, SRC);

    ads.mercuryNavigateToAnArticle(thirdArticle);
    ads.waitTitleChangesTo(thirdArticle);
    ads.verifyGptIframe(adUnit, MOBILE_TOP_LEADERBOARD, SRC);
    ads.verifyGptIframe(adUnit, MOBILE_PREFOOTER, SRC);
  }
}
