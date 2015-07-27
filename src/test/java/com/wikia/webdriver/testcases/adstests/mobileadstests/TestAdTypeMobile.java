package com.wikia.webdriver.testcases.adstests.mobileadstests;

import com.wikia.webdriver.common.dataprovider.ads.AdTypeDataProvider;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 * @author Sergey Naumov <p/> These are synthetic tests, the related order in DFP is:
 *         https://www.google.com/dfp/5441#delivery/OrderDetail/orderId=245575332
 * @ownership AdEngineering
 */
public class TestAdTypeMobile extends MobileTestTemplate {

  @Test(
      groups = {"MobileAds", "TestAdTypeAsync_001", "TestAdType", "MobileAds"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncSuccessWithAd"
  )
  public void TestAdTypeAsync_001_imageAd(String wikiName, String article, String adUnit,
                                          String slotName, String imgUrl) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.waitForSlot(slotName);
    ads.verifyGptIframe(adUnit, slotName, "mobile");
    ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
  }

  @Test(
      groups = {"MobileAds", "TestAdTypeAsync_002", "TestAdType"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncHopNoAd"
  )
  public void TestAdTypeAsync_002_noAd(String wikiName, String article, String adUnit,
                                       String slotName, String slotName2) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.wait.forElementPresent(By.id(slotName2));
    ads.verifyGptIframe(adUnit, slotName, "mobile");
    ads.verifyGptIframe(adUnit, slotName2, "mobile");
    ads.verifyNoAdInSlot(slotName);
    ads.verifyNoAdInSlot(slotName2);
  }

  @Test(
      groups = {"MobileAds", "TestAdTypeAsync_003", "TestAdType"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncSuccessNoAd"
  )
  public void TestAdTypeAsync_003_noAdSuccess(String wikiName, String article, String adUnit,
                                              String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, "mobile");
    ads.verifySlotExpanded(slotName);
  }

  @Test(
      groups = {"MobileAds", "TestAdTypeAsync_004", "TestAdType"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncHopWithAd"
  )
  public void TestAdTypeAsync_004_imgAdHop(String wikiName, String article, String adUnit,
                                           String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, "mobile");
    ads.verifyNoAdInSlot(slotName);
  }

  @Test(
      groups = {"MobileAds", "TestAdTypeAsync_005", "TestAdType"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncHopWithSpecialProvider"
  )
  public void TestAdTypeAsync_005_hopSpecialProvider(String wikiName, String article, String adUnit,
                                                     String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, "mobile");
    ads.verifyNoAdInSlot(slotName);
  }

  @Test(
      groups = {"MobileAds", "TestAdTypeAsync_006", "TestAdType"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "asyncHopAndAsyncSuccess"
  )
  public void TestAdTypeAsync_006_asyncHopAndAsyncSuccess(
      String wikiName, String article, String adUnit, String slotNameWithAd, String imgUrl,
      String slotNameWithoutAd
  ) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotNameWithAd));
    ads.wait.forElementPresent(By.id(slotNameWithoutAd));
    ads.verifyGptIframe(adUnit, slotNameWithAd, "mobile");
    ads.verifyGptIframe(adUnit, slotNameWithoutAd, "mobile");
    ads.verifyImgAdLoadedInSlot(slotNameWithAd, imgUrl);
    ads.verifyNoAdInSlot(slotNameWithoutAd);
  }

  @Test(
      groups = {"MobileAds", "TestAdTypeForcedSuccess_001", "TestAdType"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "forcedSuccessNoAd"
  )
  public void TestAdTypeForcedSuccess_001_noAd(String wikiName, String article, String adUnit,
                                               String slotName) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.waitForSlot(slotName);
    ads.verifyGptIframe(adUnit, slotName, "mobile");
    ads.verifySlotExpanded(slotName);
  }

  @Test(
      groups = {"MobileAds", "TestAdTypeInspectIframe_001", "TestAdType"},
      dataProviderClass = AdTypeDataProvider.class,
      dataProvider = "inspectIframeImg"
  )
  public void TestAdTypeInspectIframe_001_withAd(String wikiName, String article, String adUnit,
                                                 String slotName, String imgUrl) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(driver, testedPage);
    ads.wait.forElementPresent(By.id(slotName));
    ads.verifyGptIframe(adUnit, slotName, "mobile");
    ads.verifyImgAdLoadedInSlot(slotName, imgUrl);
  }
}
