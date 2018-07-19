package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.dataprovider.ads.AdTypeDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.mobile.MobileAdsBaseObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

@Test(groups = "AdsAdTypeOasis")
public class TestAdType extends TemplateNoFirstLoad {

  private static final String
      DFP_IMAGE_URL
      = "googlesyndication.com/pagead/imgad?id=CICAgKCNj62dEhCsAhj6ASgBMgjBw3U0lR5Thg";

  @Test(dataProviderClass = AdTypeDataProvider.class, dataProvider = "collapse")
  public void adsAdTypeCollapse(String wikiName, String article, String adUnit, String[] slots) {
    String testedPage = UrlBuilder.createUrlBuilderForWiki(wikiName).getUrlForPath(article);
    AdsBaseObject ads = new AdsBaseObject(testedPage);

    for (String slotName : slots) {
      ads.verifyGptIframe(adUnit, slotName, "gpt");
      ads.verifyIframeSize(slotName, 0, 0);
    }
  }

  @Test
  public void adsAdTypeInspectIframe() {
    Page page = new Page("project43", "SyntheticTests/AdType/InspectIframe");
    final By slotSelector = By.id(AdsContent.MEDREC);
    MobileAdsBaseObject ads = new MobileAdsBaseObject(page.getUrl());
    ads.waitForSlotExpanded(slotSelector);
    ads.scrollToPosition(slotSelector);
    ads.verifyImgAdLoadedInSlot(AdsContent.MEDREC, DFP_IMAGE_URL);
  }
}
