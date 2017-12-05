package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import org.openqa.selenium.WebDriver;

public class AdsPageFairObject extends AdsBaseObject {

  private static final String PATTERN_PAGEFAIR =
      "https?://stats.pagefair.com/stats/page_view_event/[0-9A-Z]{16}/a\\.js.*";
  private static final String PAGEFAIR_URL_PARAM = "pagefairdetection=1";
  private static final String PATTERN_PAGEFAIR_ADBLOCK_DETECTED =
      PATTERN_PAGEFAIR + "is_ab=1.*";
  private static final String PATTERN_PAGEFAIR_ADBLOCK_NOT_DETECTED =
      PATTERN_PAGEFAIR + "is_ab=0.*";

  public AdsPageFairObject(WebDriver driver, Page page) {
    super(driver, AdsPageFairObject.addPageFairDetectionParam(page));
  }

  private static String addPageFairDetectionParam(Page page) {
    UrlBuilder urlBuilder = new UrlBuilder();

    String url = urlBuilder.getUrlForPage(page);
    return urlBuilder.appendQueryStringToURL(url, PAGEFAIR_URL_PARAM);
  }

  public void assertPageFairResponse(
      boolean expectedResult,
      NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
    String pattern = expectedResult ?
                     PATTERN_PAGEFAIR_ADBLOCK_DETECTED : PATTERN_PAGEFAIR_ADBLOCK_NOT_DETECTED;
    this.wait.forSuccessfulResponseByUrlPattern(networkTrafficInterceptor, pattern);
  }
}
