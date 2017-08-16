package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class TestAdsPageFairRecoveryOasis extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1920, 768);

  private static final String WIKIA = "arecovery";
  private static final String WIKIA_ARTICLE = "SyntheticTests/Static_image";
  private static final String INSTANT_GLOBAL_INSTART_LOGIC_SWITCHED_OFF = "InstantGlobals.wgAdDriverInstartLogicRecoveryCountries=[ZZ]";
  private static final String INSTANT_GLOBAL_PREMIUM_AD_LAYOUT_SWITCHED_OFF = "InstantGlobals.wgAdDriverPremiumAdLayoutCountries=[ZZ]";
  private static final String INSTANT_GLOBAL_PAGE_FAIR_SWITCHED_ON = "InstantGlobals.wgAdDriverPageFairRecoveryCountries=[XX]";

  @Test(
      groups = "AdsRecoveryPageFairOasis"
  )
  public void adsRecoveryPageFairOasis() {
    String url = urlBuilder.getUrlForPath(WIKIA, articlePageFairRecoveryUrl());
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    // when PF recovered ad is on page, inserts span elements as a direct children of body
    adsRecoveryObject.wait.forElementPresent(AdsRecoveryObject.PF_RECOVERED_ADS_SELECTOR);

    // verify that adblock is turned on on that page
    adsRecoveryObject.verifyNoAdsOnPage();

    List<WebElement> recoveredAds = adsRecoveryObject.getRecoveredAds(AdsRecoveryObject.PF_RECOVERED_ADS_SELECTOR);

    Assert.assertEquals(recoveredAds.size(), 4);
    adsRecoveryObject.assertIfAllRecoveredSlotHasCorrectSizeAndBackground(recoveredAds);
  }

  @Test(
      groups = "AdsRecoveryNoAdblockPageFairOasis"
  )
  public void adsRecoveryNoAdblockPageFairOasis() {
    String url = urlBuilder.getUrlForPath(WIKIA, articlePageFairRecoveryUrl());
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    adsRecoveryObject.verifyNumberOfPageFairRecoveredSlots(4);
  }

  @Test(
      groups = "AdsRecoveryLoggedInPageFairOasis"
  )
  @Execute(asUser = User.USER_2)
  public void adsRecoveryLoggedInPageFairOasis() {
    String url = urlBuilder.getUrlForPath(WIKIA, articlePageFairRecoveryUrl());
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    adsRecoveryObject.verifyNumberOfPageFairRecoveredSlots(0);
  }

  private String articlePageFairRecoveryUrl() {
    String instartLogicSwitchedOffUrl = urlBuilder.appendQueryStringToURL(WIKIA_ARTICLE, INSTANT_GLOBAL_INSTART_LOGIC_SWITCHED_OFF);
    String pageFairSwitchedOnUrl = urlBuilder.appendQueryStringToURL(instartLogicSwitchedOffUrl, INSTANT_GLOBAL_PAGE_FAIR_SWITCHED_ON);

    return urlBuilder.appendQueryStringToURL(pageFairSwitchedOnUrl, INSTANT_GLOBAL_PREMIUM_AD_LAYOUT_SWITCHED_OFF);
  }
}
