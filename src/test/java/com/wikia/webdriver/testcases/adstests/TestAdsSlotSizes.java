package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.util.Map;

public class TestAdsSlotSizes extends TemplateNoFirstLoad {

  private static final String ARTICLE_MIDDLE_SECTION_SELECTOR = "#ArticleMidSection.mw-headline";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsSlotSizeOasis",
      groups = "AdsSlotSizesOasis"
  )
  public void adsSlotSizesOasis(Page page,
                                String urlParamToEnable,
                                Map<String, Object> slotInfo) {

    adsSlotSizes(page, urlParamToEnable, WindowSize.DESKTOP, slotInfo);
  }

  @Test(
      dataProviderClass = MobileAdsDataProvider.class,
      dataProvider = "adsSlotSizeMercury",
      groups = "AdsSlotSizesMercury"
  )
  public void adsSlotSizesMercury(Page page,
                                  String urlParamToEnable,
                                  Map<String, Object> slotInfo) {

    adsSlotSizes(page, urlParamToEnable, WindowSize.PHONE, slotInfo);
  }

  private void adsSlotSizes(Page page,
                            String urlParamToEnable,
                            Dimension pageSize,
                            Map<String, Object> slotInfo) {

    String slotName = slotInfo.get("slotName").toString();
    Dimension slotSize = (Dimension) slotInfo.get("slotSize");

    String url = urlBuilder.getUrlForPage(page);
    if (StringUtils.isNotEmpty(urlParamToEnable)) {
      url = urlBuilder.appendQueryStringToURL(url, urlParamToEnable);
    }

    log(slotName, slotSize);

    AdsBaseObject ads = new AdsBaseObject(driver, url, pageSize);

    if (pageSize.equals(WindowSize.DESKTOP)) {
      // on desktop comments lazy loads - on mobile you need to tap to load them
      ads.triggerComments();

      // only desktop pages have this headline
      ads.scrollToPosition(ARTICLE_MIDDLE_SECTION_SELECTOR);
    }
    ads.wait.forElementPresent(By.cssSelector(AdsContent.getSlotSelector(slotName)));
    ads.triggerAdSlot(slotName);
    ads.verifyLineItemId(slotName, Integer.valueOf(slotInfo.get("lineItemId").toString()));
    ads.verifyIframeSize(slotName, slotInfo.get("src").toString(),
                          slotSize.getWidth(), slotSize.getHeight());
  }

  private void log(String slotName, Dimension slotSize) {
    PageObjectLogging.log("adsSlotSizes", "Slot name: " + slotName, true);
    PageObjectLogging.log("adsSlotSizes", "Width: " + slotSize.getWidth(), true);
    PageObjectLogging.log("adsSlotSizes", "Height: " + slotSize.getHeight(), true);
  }
}
