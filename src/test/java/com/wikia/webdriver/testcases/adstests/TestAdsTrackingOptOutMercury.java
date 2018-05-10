package com.wikia.webdriver.testcases.adstests;

import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.templates.mobile.MobileTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

public class TestAdsTrackingOptOutMercury extends MobileTestTemplate {
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @NetworkTrafficDump(useMITM = true)
  @Test(groups = "AdsTrackingOptOutMercury")
  public void adsTrackingOptOutMercury() {
    networkTrafficInterceptor.startIntercepting();

    Boolean noKikimoraRequestFound = false;
    Boolean noKruxRequestFound = false;
    Page page = new Page(TestAdsTrackingOptOutOasis.TEST_WIKI, TestAdsTrackingOptOutOasis.TEST_PAGE);
    String url = TestAdsTrackingOptOutOasis.appendTrackingOptOutParameters(page.getUrl());
    MobileAdsBaseObject mobileAdsBaseObject = new MobileAdsBaseObject(driver, url);

    try {
      mobileAdsBaseObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, TestAdsTrackingOptOutOasis.KIKIMORA_PATTERN);
    } catch (TimeoutException exception) {
      noKikimoraRequestFound = true;
    }

    try {
      mobileAdsBaseObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, TestAdsTrackingOptOutOasis.KRUX_PATTERN);
    } catch (TimeoutException exception) {
      noKruxRequestFound = true;
    }

    assertTrue(noKikimoraRequestFound);
    assertTrue(noKruxRequestFound);
  }
}
