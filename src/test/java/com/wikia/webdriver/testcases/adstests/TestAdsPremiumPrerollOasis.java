package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestAdsPremiumPrerollOasis extends TemplateNoFirstLoad {

  private static final Page PAGE_WITH_FV_PREROLL = new Page("project43", "SyntheticTests/Premium/FeaturedVideo/WithSound");
  private static final Duration AD_LENGTH = Duration.ofSeconds(30);

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsPremiumPrerollOasis"},
      dataProvider = "adsPremiumPreroll"
  )
  public void adsPremiumPrerollOasis(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    testedPage = urlBuilder.appendQueryStringToURL(
        testedPage,
        "InstantGlobals.wgAdDriverMegaAdUnitBuilderForFVCountries=[ZZ]"
    );
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.waitForAdStartsPlaying();
    wikiPage.verifyPlayerOnPage();
    wikiPage.verifyArticleAd();
    wikiPage.verifyArticleVideo();
  }

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdsPremiumPrerollOasis"},
      dataProvider = "adsPremiumPreroll"
  )
  public void adsPremiumPrerollOasisNoAds(String wikiName, String article) {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article + "?noads=1");
    testedPage = urlBuilder.appendQueryStringToURL(
        testedPage,
        "InstantGlobals.wgAdDriverMegaAdUnitBuilderForFVCountries=[ZZ]"
    );
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyPlayerOnPage();
    wikiPage.verifyArticleVideo();
  }

  @Test(
          groups = {"AdsPremiumPrerollOasis", "AdsPremiumPrerollOasisHasSound"}
  )
  public void adsPremiumPrerollOasisWithSound() {
    String testedPage = PAGE_WITH_FV_PREROLL.getUrl();
    testedPage = urlBuilder.appendQueryStringToURL(
        testedPage,
        "InstantGlobals.wgAdDriverMegaAdUnitBuilderForFVCountries=[ZZ]"
    );
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.waitForAdStartsPlaying();
    wikiPage.clickVolumeButton();
    wikiPage.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    Assertion.assertTrue(wikiPage.wasSoundHeard());
  }

  @Test(
          groups = {"AdsPremiumPrerollOasis", "AdsPremiumPrerollOasisHasNoSound"}
  )
  public void adsPremiumPrerollOasisWithoutSound() {
    String testedPage = PAGE_WITH_FV_PREROLL.getUrl();
    testedPage = urlBuilder.appendQueryStringToURL(
        testedPage,
        "InstantGlobals.wgAdDriverMegaAdUnitBuilderForFVCountries=[ZZ]"
    );
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.waitForAdStartsPlaying();
    wikiPage.allowToPlayVideoForSomeTime(Duration.ofSeconds(3));
    wikiPage.waitForAdFinish(AD_LENGTH);
    Assertion.assertFalse(wikiPage.wasSoundHeard());
  }
}
