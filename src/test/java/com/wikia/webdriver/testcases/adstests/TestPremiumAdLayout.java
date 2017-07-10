package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.junit.Assert;
import org.testng.annotations.Test;

public class TestPremiumAdLayout extends TemplateNoFirstLoad {
  private static final String TURN_ON_PREMIUM_LAYOUT = "InstantGlobals.wgAdDriverPremiumAdLayoutCountries=[XX]";

  private String turnOnPremiumLayoutOnPage(Page page) {
    return urlBuilder.appendQueryStringToURL(page.getUrl(), TURN_ON_PREMIUM_LAYOUT);
  }

  @Test(
      groups = {"PremiumAdLayout"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "premiumLayoutPages"
  )
  public void thereIsNoSkyScrapperOrPreFooterOnAdMixLayout(Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, turnOnPremiumLayoutOnPage(page));

    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2));
    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_3));
    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
  }
}
