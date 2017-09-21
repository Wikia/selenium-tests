package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AutoplayVuap;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = "AdsVuapMercury")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TestAdsVuapMercury extends TemplateNoFirstLoad {
  private static final String AD_REDIRECT_URL = "http://fandom.wikia.com/";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsVuapClickToPlayMobile",
      groups = {"AdsVuapClickToPlayTopAreas"}
  )
  public void vuapCheckTopAreasMercury(Page page, String slot) {
    AdsBaseObject ads = new AdsBaseObject(driver, page.getUrl());
    final AutoplayVuap vuap = new AutoplayVuap(driver,
                                               '#' + slot,
                                               ads.findFirstIframeWithAd(slot),
                                               true
    );
    ads.scrollToSlot(slot);

    vuap.clickOnArea(1);
    vuap.clickOnArea(2);

    List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    Assert.assertEquals(
        tabs.size(),
        3,
        "There should be three tabs opened after two clicks."
    );
    final String actual = ads.switchToNewBrowserTab();
    Assert.assertEquals(
        actual,
        AD_REDIRECT_URL,
        "Top part of creative should point to FANDOM page but it points to " + actual
    );
  }
}
