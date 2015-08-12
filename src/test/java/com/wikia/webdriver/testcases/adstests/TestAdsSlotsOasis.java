package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

/**
 * @ownership AdEng
 */
public class TestAdsSlotsOasis extends TemplateNoFirstLoad {

  @Test(
      groups = "TestSlotsOasis",
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsSlotsOasis"
  )
  public void adsSlotOasis(String slotName) {
    String testedPage = urlBuilder.getUrlForPath("adtest", "SyntheticTests/OasisSlots");
    AdsBaseObject ads = new AdsBaseObject(driver, testedPage);
    ads.waitForPageLoaded();

    Assertion.assertTrue(ads.isSlotOnPageLoaded(slotName));
    ads.verifyGptIframe("wka.ent/_adtest//article", slotName, "gpt");
  }

}
