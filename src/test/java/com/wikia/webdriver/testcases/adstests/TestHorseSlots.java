package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

public class TestHorseSlots extends NewTestTemplate {

  @Test(groups = {"TestHorseSlots_Interstital", "TestHorseSlots"}, invocationCount = 10)
  public void TestHorseSlots_Interstital() {
    driver.manage().window().maximize();
    driver.get("http://sandbox-adeng02.project43.wikia.com/wiki/SyntheticTests/Slots/InvisibleHighImpact/Interstitial");
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.waitForElementPresent("wikia_gpt/5441/wka.life/_project43//article/gpt/INVISIBLE_HIGH_IMPACT_2");
  }

  @Test(groups = {"TestHorseSlots_FloorAdhesion", "TestHorseSlots"}, invocationCount = 10)
  public void TestHorseSlots_FloorAdhesion() {
    driver.manage().window().maximize();
    driver.get("http://sandbox-adeng02.project43.wikia.com/wiki/SyntheticTests/Slots/InvisibleHighImpact/FloorAdhesion");
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.waitForElementPresent("wikia_gpt/5441/wka.life/_project43//article/gpt/INVISIBLE_HIGH_IMPACT_2");
  }
}
