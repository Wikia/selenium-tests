package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestHorseSlots extends NewTestTemplate {

  @Test(groups = {"TestHorseSlots_FloorAdhesion", "TestHorseSlots"}, invocationCount = 5)
  public void TestHorseSlots_FloorAdhesion() {
    driver.manage().window().maximize();
    driver.get("http://aga.wikia.com/wiki/SyntheticTests/Slots/InvisibleHighImpact/FloorAdhesion");
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.waitForElementPresent("google_ads_iframe_/5441/wka.gaming/_aga//article/gpt/INVISIBLE_HIGH_IMPACT_2_0__container__");
    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test(groups = {"TestHorseSlots_Interstitial", "TestHorseSlots"}, invocationCount = 5)
  public void TestHorseSlots_Interstitial() {
    driver.manage().window().maximize();
    driver.get("http://aga.wikia.com/wiki/SyntheticTests/Slots/InvisibleHighImpact/Interstitial");
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.waitForElementPresent("google_ads_iframe_/5441/wka.gaming/_aga//article/gpt/INVISIBLE_HIGH_IMPACT_2_0__container__");
    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
