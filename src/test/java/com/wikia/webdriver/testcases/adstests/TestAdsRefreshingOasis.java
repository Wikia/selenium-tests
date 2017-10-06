package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

@Test(groups = "AdsRefreshingOasis")
public class TestAdsRefreshingOasis extends TemplateNoFirstLoad {

  private static final String[] CREATIVE_ID_CHAIN = {
      "108480236412",
      "108480237132",
      "108480238572",
      "108480239052",
      "108480239532"
  };

  private static final String WIKI_NAME = "project43";

  private static final String PATH = "SyntheticTests/Slots/RefreshOnView";

  public void testRefreshingMedrecOasis() {
    AdsBaseObject page = new AdsBaseObject(
        driver,
        urlBuilder.getUrlForPath(WIKI_NAME, PATH),
        WindowSize.DESKTOP
    );
    page.verifyAdChainForSlot(CREATIVE_ID_CHAIN, AdsContent.MEDREC, page);
  }
}
