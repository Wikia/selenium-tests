package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestHorseSlots extends NewTestTemplate {

//  @Test(groups = {"TestHorseSlots_FloorAdhesion", "TestHorseSlots"}, invocationCount = 5)
//  public void TestHorseSlots_FloorAdhesion() {
//    driver.manage().window().maximize();
//    driver.get("http://project43.wikia.com/wiki/SyntheticTests/Slots/InvisibleHighImpact/FloorAdhesion");
//    AdsBaseObject ads = new AdsBaseObject(driver);
//    ads.waitForElementPresent("google_ads_iframe_/5441/wka.gaming/_aga//article/gpt/INVISIBLE_HIGH_IMPACT_2_0__container__");
//    try {
//      Thread.sleep(10000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//  }
//
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = {"TestHorseSlots_Interstitial", "TestHorseSlots"}, invocationCount = 5)
  public void TestHorseSlots_Interstitial() {
    driver.manage().window().maximize();
    driver.get("http://sandbox-adeng04.project43.wikia.com/wiki/A");
    AdsBaseObject ads = new AdsBaseObject(driver);
    ads.waitForElementPresent("wikia_gpt/5441/wka.life/_project43//article/mobile/MOBILE_TOP_LEADERBOARD");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    WebElement link = driver.findElement(By.cssSelector("a[title='B']"));
    link.click();
    ads.waitForElementPresent("wikia_gpt/5441/wka.life/_project43//article/mobile/MOBILE_TOP_LEADERBOARD");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
