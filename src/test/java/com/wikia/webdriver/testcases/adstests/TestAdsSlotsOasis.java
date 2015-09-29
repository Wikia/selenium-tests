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
  private AdsBaseObject ads;

  @Test(
      groups = "TestSlotsOasis"
  )
  public void adsSmokeTestSlotsOasis() {
    String testedPage = urlBuilder.getUrlForPath("adtest", "SyntheticTests/OasisSlots");
    ads = new AdsBaseObject(driver, testedPage);
    ads.waitForPageLoaded();

    for(String slotName : AdsDataProvider.OASIS_SLOTS_TO_SMOKE_TEST) {
      smokeTestSlot(slotName);
    }
  }

  private void smokeTestSlot(String slotName) {
    verifySlotIsLoaded(slotName);
    ads.verifyGptIframe("wka.life/_adtest//article", slotName, "gpt");
  }

  private void verifySlotIsLoaded(String slotName) {
    Assertion.assertTrue(ads.checkSlotOnPageLoaded(slotName));
  }

}
