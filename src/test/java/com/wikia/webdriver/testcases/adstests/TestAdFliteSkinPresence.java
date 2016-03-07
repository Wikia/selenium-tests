package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TestAdFliteSkinPresence extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteSkin",
      groups = "TestSkinPresence_GeoEdgeFree"
  )
  public void TestAdFliteSkinPresence(String wikiName, String article,
                                      Dimension windowResolution) throws IOException {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    PageObjectLogging.log("Window resolution: ", String.valueOf(windowResolution.width), true);
    AdsBaseObject adsBaseObject = new AdsBaseObject(driver, testedPage, windowResolution);
    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_1.png",
                             "src/test/resources/adsResources/flite_skin_right_1.png",
                             null,
                             null);
    driver.switchTo().frame("google_ads_iframe_/5441/wka.life/_adtest//article/gpt/TOP_LEADERBOARD_0");
    driver.switchTo().frame("f_ad_dd9465c8-a687-46cd-8550-b6ee529f76ed");
    driver.switchTo().frame(driver.findElement(By.className("flite-ad")));
    WebElement fliteAdButton2 = driver.findElement(By.cssSelector("div[id*='div18']"));
    Actions action = new Actions(driver);
    action.moveToElement(fliteAdButton2).perform();
    driver.switchTo().defaultContent();

    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_2.png",
                             "src/test/resources/adsResources/flite_skin_right_2.png",
                             null,
                             null);

    driver.switchTo().frame("google_ads_iframe_/5441/wka.life/_adtest//article/gpt/TOP_LEADERBOARD_0");
    driver.switchTo().frame("f_ad_dd9465c8-a687-46cd-8550-b6ee529f76ed");
    driver.switchTo().frame(driver.findElement(By.className("flite-ad")));
    WebElement fliteAdButton3 = driver.findElement(By.cssSelector("div[id*='div16']"));
    action.moveToElement(fliteAdButton3).perform();
    driver.switchTo().defaultContent();

    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_3.png",
                             "src/test/resources/adsResources/flite_skin_right_3.png",
                             null,
                             null);

    driver.switchTo().frame("google_ads_iframe_/5441/wka.life/_adtest//article/gpt/TOP_LEADERBOARD_0");
    driver.switchTo().frame("f_ad_dd9465c8-a687-46cd-8550-b6ee529f76ed");
    driver.switchTo().frame(driver.findElement(By.className("flite-ad")));
    WebElement fliteAdButton4 = driver.findElement(By.cssSelector("div[id*='div14']"));
    action.moveToElement(fliteAdButton4).perform();
    driver.switchTo().defaultContent();

    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_4.png",
                             "src/test/resources/adsResources/flite_skin_right_4.png",
                             null,
                             null);

    driver.switchTo().frame("google_ads_iframe_/5441/wka.life/_adtest//article/gpt/TOP_LEADERBOARD_0");
    driver.switchTo().frame("f_ad_dd9465c8-a687-46cd-8550-b6ee529f76ed");
    driver.switchTo().frame(driver.findElement(By.className("flite-ad")));
    WebElement fliteAdButton1 = driver.findElement(By.cssSelector("button[id*='21']"));
    action.moveToElement(fliteAdButton1).perform();
    driver.switchTo().defaultContent();

    adsBaseObject.verifySkin("src/test/resources/adsResources/flite_skin_left_1.png",
                             "src/test/resources/adsResources/flite_skin_right_1.png",
                             null,
                             null);

  }
}
