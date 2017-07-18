package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.PremiumAdLayout;
import org.junit.Assert;
import org.testng.annotations.Test;

public class TestPremiumAdLayout extends TemplateNoFirstLoad {

  @Test(
      groups = {"PremiumAdLayout", "PremiumAdLayoutHasCorrectSlots"},
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "premiumLayoutPages"
  )
  public void thereIsNoSkyScrapperOrPreFooterOnAdMixLayout(Page page) {
    AdsBaseObject ads = new AdsBaseObject(driver, PremiumAdLayout.addTurnOnParams(urlBuilder, page));

    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_2));
    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.LEFT_SKYSCRAPPER_3));
    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.PREFOOTER_LEFT));
    Assert.assertFalse(ads.checkSlotOnPageLoaded(AdsContent.PREFOOTER_RIGHT));
  }
}
