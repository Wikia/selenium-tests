package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Test(groups = "AdsIncontentBoxadAE3Mobile")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class TestAdsIncontentBoxadAE3MobileWiki extends NewTestTemplate {

  private static final Page page = new Page("project43", "SyntheticTests/LongPage");

  private static final String AD_INFO_PATTERN = "https?://.*wikia-services\\.com.*kv_pos=INCONTENT_BOXAD.*kv_rv=4.*";
  private static final String AE3_INSTANT_GLOBAL = "wgAdDriverAdEngine3Countries";
  private static final String MOBILE_INCONTENT_INSTANT_GLOBAL = "wgAdDriverRepeatMobileIncontentCountries";
  private static final String INCONTENT_SELECTOR = "incontent_boxad_%s";
  private static final String ARTICLE_HEADER_SELECTOR = "Header_%s";

  private static final boolean INCONTENT_ENABLED = true;
  private static final boolean INCONTENT_DISABLED = false;

  private static final int HEADERS_NUMBER = 6;
  private static final int FOURTH_HEADER_NUMBER = 4;

  @Test()
  public void incontentBoxadDisplayedBeforeEachSection() {
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.getUrl(urlWithInstantGlobals(INCONTENT_ENABLED));
    for (int i = 1; i < HEADERS_NUMBER; i++){
      ads.scrollTo(By.id(String.format(ARTICLE_HEADER_SELECTOR, Integer.toString(i))));

      Assertion.assertTrue(isIncontenAdDisplayed(i, ads),
                           "IcontentBoxad is not displayed before section");
    }
  }

  @Test()
  @NetworkTrafficDump(useMITM = true)
  public void incontentBoxadTracking() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.getUrl(urlWithInstantGlobals(INCONTENT_ENABLED));
    ads.scrollTo(By.id(String.format(ARTICLE_HEADER_SELECTOR, Integer.toString(FOURTH_HEADER_NUMBER))));

    ads.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, AD_INFO_PATTERN);
  }

  @Test()
  public void incontentBoxadNotDisplayedBeforeEachSection() {
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.getUrl(urlWithInstantGlobals(INCONTENT_DISABLED));
    ads.scrollTo(By.id(String.format(ARTICLE_HEADER_SELECTOR, Integer.toString(FOURTH_HEADER_NUMBER))));

    Assertion.assertFalse(isIncontenAdDisplayed(FOURTH_HEADER_NUMBER, ads),
                          "IcontentBoxad is displayed before section");
  }

  private boolean isIncontenAdDisplayed(int headerNumber, AdsBaseObject ads){
    try {
      WebElement slot =
          driver.findElement(By.id(String.format(INCONTENT_SELECTOR, Integer.toString(headerNumber))));
      ads.waitForSlotExpanded(slot);
      return true;
    } catch (Exception ex){
      PageObjectLogging
          .logInfo("IcontentBoxad is not displayed", ex);
      return false;
    }
  }

  private static String urlWithInstantGlobals(boolean enabled) {
    UrlBuilder urlBuilder = new UrlBuilder();
    String url = urlBuilder.getUrlForPage(page);
    String newUrl = urlBuilder.globallyEnableGeoInstantGlobalOnPage(url, AE3_INSTANT_GLOBAL);
    if(!enabled) {
      return urlBuilder.globallyDisableGeoInstantGlobalOnPage(newUrl, MOBILE_INCONTENT_INSTANT_GLOBAL);
    } else {
      return urlBuilder.globallyEnableGeoInstantGlobalOnPage(newUrl, MOBILE_INCONTENT_INSTANT_GLOBAL);
    }
  }
}
