package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SmartBannerComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.testng.annotations.Test;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 * @ownership: Content - Mercury mobile
 */
public class SmartBannerTests extends NewTestTemplate {

  private static final String[] DIFFERENT_HUBS_WIKIS = {"destiny", "cocktails", "thehungergames",
                                                        "marvel", "tardis", "starwars", "lego"};
  private static final String[] DIFFERENT_HUBS_COLORS = {"#94d11f", "#8ca038", "#ff7f26",
                                                         "#ff5400", "#00b7e0", "#09d3bf",
                                                         "#ffd000"};

  private static final String BUTTON_NAME_FOR_ANDROID = "Install";
  private static final String BUTTON_NAME_FOR_IOS = "GET";

  // SBT01
  @Test(groups = {"MercurySmartBannerTest_001", "MercurySmartBannerTests", "Mercury"})
  public void MercurySmartBannerTest_001_UserCanCloseSmartBanner() {
    BasePageObject.turnOnMercurySkin(driver, "http://" + DIFFERENT_HUBS_WIKIS[0] + ".wikia.com/");
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryWiki(DIFFERENT_HUBS_WIKIS[0]);
    SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
    banner.clickCloseButton();
    Assertion.assertFalse(banner.isSmartBannerVisible(), "Smart banner is visible");
  }

  // SBT02
  @Test(groups = {"MercurySmartBannerTest_002", "MercurySmartBannerTests", "Mercury"})
  public void MercurySmartBannerTest_002_FixPositionOfSmartBanner() {
    BasePageObject.turnOnMercurySkin(driver, "http://" + DIFFERENT_HUBS_WIKIS[0] + ".wikia.com/");
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryWiki(DIFFERENT_HUBS_WIKIS[0]);
    SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
    PerformTouchAction touchAction = new PerformTouchAction(driver);
    int lastSmartBannerPosition = banner.getSmartBannerPosition();
    touchAction.swipeFromPointToPoint(50, 90, 50, 40, 500, 3000);
    Assertion.assertTrue(lastSmartBannerPosition == banner.getSmartBannerPosition(),
                         "Smart banner is floating");
  }

  // SBT03
  @Test(groups = {"MercurySmartBannerTest_003", "MercurySmartBannerTests", "Mercury"})
  public void MercurySmartBannerTest_003_AndroidUserSeeInstallButton() {
    BasePageObject.turnOnMercurySkin(driver, "http://" + DIFFERENT_HUBS_WIKIS[0] + ".wikia.com/");
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryWiki(DIFFERENT_HUBS_WIKIS[0]);
    SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
    Assertion.assertTrue(
        banner.getButtonName().equals(BUTTON_NAME_FOR_ANDROID),
        "Smart banner has different name");
  }

  // SBT04
  @Test(groups = {"MercurySmartBannerTest_004", "MercurySmartBannerTests", "Mercury"}, enabled = false)
  public void MercurySmartBannerTest_004_IOSUserSeeGETButton() {
    BasePageObject.turnOnMercurySkin(driver, "http://" + DIFFERENT_HUBS_WIKIS[0] + ".wikia.com/");
    BasePageObject base = new BasePageObject(driver);
    base.openMercuryWiki(DIFFERENT_HUBS_WIKIS[0]);
    SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
    Assertion
        .assertTrue(banner.getButtonName().equals(BUTTON_NAME_FOR_IOS),
                    "Smart banner has different name");
  }

  // SBT05
  @Test(groups = {"MercurySmartBannerTest_005", "MercurySmartBannerTests", "Mercury"})
  public void MercurySmartBannerTest_005_ThemeColorOnDifferentHubs() {
    SmartBannerComponentObject banner;
    for (int i = 0; i < DIFFERENT_HUBS_WIKIS.length; ++i) {
      driver.get("http://" + DIFFERENT_HUBS_WIKIS[i] + ".wikia.com/wiki/?useskin=mercury");
      banner = new SmartBannerComponentObject(driver);
      Assertion.assertTrue(banner.isSmartBannerColorCorrect(DIFFERENT_HUBS_COLORS[i]),
                           "Smart banner color is wrong");
      Assertion.assertTrue(banner.isSmartBannerButtonColorCorrect(DIFFERENT_HUBS_COLORS[i]),
                           "Smart banner button color is wrong");
    }
  }
}
