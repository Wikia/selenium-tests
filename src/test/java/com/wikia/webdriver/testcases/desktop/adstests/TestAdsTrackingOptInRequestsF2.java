package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.dataprovider.ads.FandomAdsDataProvider;
import com.wikia.webdriver.common.templates.fandom.AdsFandomTestTemplate;
import com.wikia.webdriver.elements.common.TrackingOptInModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFandomObject;

import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestAdsTrackingOptInRequestsF2 extends AdsFandomTestTemplate {

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(groups = "AdsOptInModalF2")
  public void testModalVisible() {
    AdsFandomObject page = loadPageWithGeo();
    TrackingOptInModal modal = new TrackingOptInModal(page);

    Assertion.assertTrue(modal.isVisible());
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(groups = "AdsOptInModalF2")
  public void testNoRequestForAdBeforeModal() throws InterruptedException {
    AdsFandomObject page = loadPageWithGeo();

    TrackingOptInModal modal = new TrackingOptInModal(page);
    page.waitForPageLoad();

    TimeUnit.SECONDS.sleep(10);
    Assertion.assertFalse(page.wasRequestForAdSend(networkTrafficInterceptor));
    modal.clickAcceptButton();

    TimeUnit.SECONDS.sleep(5);
    Assertion.assertTrue(page.wasRequestForAdSend(networkTrafficInterceptor));
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = true)
  @Test(dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "f2TrackingURLsForOptIn", groups = "AdsOptInAcceptF2")
  public void testRequestsForOptIn(String pattern, boolean isExpected) {
    testRequests(pattern, isExpected);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false, trackingOptOut = true)
  @Test(dataProviderClass = TrackingOptInDataProvider.class, dataProvider = "f2TrackingURLsForOptOut", groups = "AdsOptInRejectF2")
  public void testRequestsForOptOut(String pattern, boolean isExpected) {
    testRequests(pattern, isExpected);
  }

  private void testRequests(String pattern, boolean isExpected) {
    AdsFandomObject page = loadPageWithGeo();

    if (isExpected) {
      Assertion.assertTrue(isRequestFound(pattern, page));
    } else {
      Assertion.assertFalse(isRequestFound(pattern, page));
    }
  }

  private AdsFandomObject loadPageWithGeo() {
    AdsFandomObject page = loadPage(FandomAdsDataProvider.PAGE_HIVI_UAP_ARTICLE);
    TrackingOptInPage.setGeoCookie(driver, "EU", "PL");
    networkTrafficInterceptor.startIntercepting();
    page.refreshPage();
    return page;
  }

  private boolean isRequestFound(String pattern, AdsFandomObject page) {
    try {
      page.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pattern);
    } catch (TimeoutException error) {
      return false;
    }

    return true;
  }
}
