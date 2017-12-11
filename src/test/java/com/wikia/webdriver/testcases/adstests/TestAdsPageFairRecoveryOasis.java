package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsRecoveryObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestAdsPageFairRecoveryOasis extends TemplateNoFirstLoad {

  private static Dimension DESKTOP_SIZE = new Dimension(1920, 768);

  private static final String WIKIA = "arecovery";
  private static final String WIKIA_ARTICLE = "SyntheticTests/Static_image";

  private static final String INSTANT_GLOBAL_INSTART_LOGIC = "wgAdDriverInstartLogicRecoveryCountries";
  private static final String INSTANT_GLOBAL_PAGE_FAIR = "wgAdDriverPageFairRecoveryCountries";

  private static final String ADONIS_MARKER_BOTTOM_LEADERBOARD_SELECTOR =
      "div[id*=BOTTOM_LEADERBOARD][adonis-marker] iframe[id*=BOTTOM_LEADERBOARD]";

  @Test(
      groups = "AdsRecoveryPageFairOasis"
  )
  public void adsRecoveryPageFairOasis() {
    String url = urlBuilder.getUrlForPath(WIKIA, getUrlArticlePageFairRecovery());
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    // when PF recovered ad is on page, inserts span elements as a direct children of body
    adsRecoveryObject.wait.forElementPresent(AdsRecoveryObject.PF_RECOVERED_ADS_SELECTOR);

    adsRecoveryObject.triggerAdSlot(AdsContent.BOTTOM_LB)
        .wait
        .forRecoveredAds(adsRecoveryObject, AdsRecoveryObject.PF_RECOVERED_ADS_SELECTOR, 3);

    adsRecoveryObject.assertIfAllRecoveredSlotHasCorrectSizeAndBackground(
        adsRecoveryObject.getRecoveredAds(AdsRecoveryObject.PF_RECOVERED_ADS_SELECTOR)
    );
  }

  @Test(
      groups = "AdsRecoveryNoAdblockPageFairOasis"
  )
  public void adsRecoveryNoAdblockPageFairOasis() {
    String url = urlBuilder.getUrlForPath(WIKIA, getUrlArticlePageFairRecovery());
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    adsRecoveryObject.verifyNumberOfAdonisMarkedSlots(2);
    adsRecoveryObject.triggerAdSlot(AdsContent.BOTTOM_LB)
        .wait
        .forElementPresent(By.cssSelector(ADONIS_MARKER_BOTTOM_LEADERBOARD_SELECTOR));

    adsRecoveryObject.verifyNumberOfAdonisMarkedSlots(3);
  }

  @Test(
      groups = "AdsRecoveryLoggedInPageFairOasis"
  )
  @Execute(asUser = User.USER_2)
  public void adsRecoveryLoggedInPageFairOasis() {
    String url = urlBuilder.getUrlForPath(WIKIA, getUrlArticlePageFairRecovery());
    AdsRecoveryObject adsRecoveryObject = new AdsRecoveryObject(driver, url, DESKTOP_SIZE);

    adsRecoveryObject.verifyNumberOfAdonisMarkedSlots(0);
  }

  private String getUrlArticlePageFairRecovery() {
    String url = urlBuilder.globallyDisableGeoInstantGlobalOnPage(WIKIA_ARTICLE, INSTANT_GLOBAL_INSTART_LOGIC);

    return urlBuilder.globallyEnableGeoInstantGlobalOnPage(url, INSTANT_GLOBAL_PAGE_FAIR);
  }
}
