package com.wikia.webdriver.testcases.adstests;

import static org.testng.Assert.assertTrue;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

public class TestAdsTrackingOptOutFandom extends AdsFandomTestTemplate {
  @NetworkTrafficDump(useMITM = true)
  @Test(groups = "AdsTrackingOptOutFandomDesktop")
  public void adsTrackingOptOutFandomDesktop() {
    adsTrackingOptOutFandom();
  }

  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5
  )
  @NetworkTrafficDump(useMITM = true)
  @Test(groups = "AdsTrackingOptOutFandomMobile")
  public void adsTrackingOptOutFandomMobile() {
    adsTrackingOptOutFandom();
  }

  public void adsTrackingOptOutFandom() {
    networkTrafficInterceptor.startIntercepting();

    Boolean noKikimoraRequestFound = false;
    Boolean noKruxRequestFound = false;
    String url = TestAdsTrackingOptOutOasis.appendTrackingOptOutParameters(urlBuilder.getFandomUrl());
    AdsFandomObject adsFandomObject = new AdsFandomObject(driver, url);

    try {
      adsFandomObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, TestAdsTrackingOptOutOasis.KIKIMORA_PATTERN);
    } catch (TimeoutException exception) {
      noKikimoraRequestFound = true;
    }

    try {
      adsFandomObject.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, TestAdsTrackingOptOutOasis.KRUX_PATTERN);
    } catch (TimeoutException exception) {
      noKruxRequestFound = true;
    }

    assertTrue(noKikimoraRequestFound);
    assertTrue(noKruxRequestFound);
  }
}
