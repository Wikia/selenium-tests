package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsPageFairObject;
import org.testng.annotations.Test;

public class TestAdsDetectionPageFair extends TemplateNoFirstLoad {

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectAdBlockPageFair",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsDetectionPageFair"
  )
  public void adsDetectAdBlockPageFair(Page page) {
    networkTrafficInterceptor.startIntercepting();
    AdsPageFairObject adsBaseObject = new AdsPageFairObject(driver, page);
    adsBaseObject.assertPageFairResponse(true, networkTrafficInterceptor);
  }

  @NetworkTrafficDump
  @Test(
      groups = "AdsDetectNoAdBlockPageFair",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsDetectionPageFair"
  )
  public void adsDetectNoAdBlockPageFair(Page page) {
    networkTrafficInterceptor.startIntercepting();
    AdsPageFairObject adsBaseObject = new AdsPageFairObject(driver, page);
    adsBaseObject.assertPageFairResponse(false, networkTrafficInterceptor);
  }
}
