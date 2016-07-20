package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestHorseSlots extends NewTestTemplate {

  @Test(groups = {"TestHorseSlots_Interstital", "TestHorseSlots"}, invocationCount = 5)
  public void TestHorseSlots_Interstital() {
    driver.manage().window().maximize();
    driver.get("http://sandbox-s6.project43.wikia.com/wiki/SyntheticTests/Slots/InvisibleHighImpact/Interstitial");
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.waitForElementPresent("wikia_gpt/5441/wka.life/_project43//article/gpt/INVISIBLE_HIGH_IMPACT_2");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test(groups = {"TestHorseSlots_FloorAdhesion", "TestHorseSlots"}, invocationCount = 5)
  public void TestHorseSlots_FloorAdhesion() {
    driver.manage().window().maximize();
    driver.get("http://sandbox-s6.project43.wikia.com/wiki/SyntheticTests/Slots/InvisibleHighImpact/FloorAdhesion");
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.waitForElementPresent("wikia_gpt/5441/wka.life/_project43//article/gpt/INVISIBLE_HIGH_IMPACT_2");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
