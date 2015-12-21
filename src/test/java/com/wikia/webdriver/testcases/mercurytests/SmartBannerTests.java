package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Device;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.SmartBannerComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.PerformTouchAction;

import org.testng.annotations.Test;

@Execute(
    onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING,
    onDevice = Device.GOOGLE_NEXUS_5,
    browser = Browser.CHROME
)
public class SmartBannerTests extends NewTestTemplate {

  /**
   * HUB color definition
   */
  private enum Colors {
    LIGHT_GREEN("#94d11f"),
    LIGHT_ORANGE("#ff7f26"),
    DARK_ORANGE("#ff5400"),
    LIGHT_BLUE("#00b7e0"),
    CYAN("#09d3bf"),
    YELLOW("#ffd000"),
    MAGENTA("#c819ad");
    private String hex;

    private Colors(String hex) {
      this.hex = hex;
    }
  }

  /**
   * Wiki name, Main page, HUB color
   */
  private static final String[][] WIKIS = {
      {"destiny", "Destiny_Wiki", Colors.LIGHT_GREEN.hex},
      {"starwars", "Main_Page", Colors.CYAN.hex},
      {"thehungergames", "The_Hunger_Games_Wiki", Colors.LIGHT_ORANGE.hex},
      {"dc", "Main_Page", Colors.DARK_ORANGE.hex},
      {"tardis", "Doctor_Who_Wiki", Colors.LIGHT_BLUE.hex},
      {"lego", "LEGO_Wiki", Colors.YELLOW.hex},
      {"ladygaga", "Gagapedia", Colors.MAGENTA.hex}
  };
  private static final String BUTTON_NAME_FOR_ANDROID = "Install";
  private static final String BUTTON_NAME_FOR_IOS = "GET";

  @Test(groups = "MercurySmartBannerTest_001")
  @RelatedIssue(issueID = "XW-656")
  @Execute(browser = Browser.CHROME_ANDROID)
  public void MercurySmartBannerTest_001_ButtonName_FixPosition_Close() {
    SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(WIKIS[0][0]);
    banner.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, WIKIS[0][1]);
    PerformTouchAction touchAction = new PerformTouchAction(driver);

    String buttonName;

    Assertion.assertTrue(
        banner.isSmartBannerVisible(),
        "Smart banner is closed"
    );

    if ("ANDROID".equals(Configuration.getPlatform())) {
      buttonName = BUTTON_NAME_FOR_ANDROID;
    } else {
      buttonName = BUTTON_NAME_FOR_IOS;
    }

    boolean result = banner.getButtonName().equals(buttonName);
    PageObjectLogging.log(
        "Button name",
        "is correct",
        "is incorrect",
        result
    );

    int lastSmartBannerPosition = banner.getSmartBannerPosition();
    touchAction.swipeFromPointToPoint(50, 90, 50, 40, 500, 3000);

    result = lastSmartBannerPosition == banner.getSmartBannerPosition();
    PageObjectLogging.log(
        "Position",
        "is fixed",
        "is floated",
        result
    );

    banner.scrollToTopAndWaitForShareBarToBeHidden();
    banner.clickCloseButton();

    result = !banner.isSmartBannerVisible();
    PageObjectLogging.log(
        "Smart banner",
        "is closed",
        "is visible",
        result
    );
  }

  @Test(groups = "MercurySmartBannerTest_002")
  public void MercurySmartBannerTest_002_ThemeColorOnDifferentHubs() {
    SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
    boolean result;

    for (String[] WIKI : WIKIS) {
      wikiURL = urlBuilder.getUrlForWiki(WIKI[0]);
      banner.openMercuryArticleByName(wikiURL, WIKI[1]);

      result = banner.isSmartBannerColorCorrect(WIKI[2]);
      PageObjectLogging.log(
          "Smart banner color",
          "is correct",
          "is wrong",
          result
      );

      result = banner.isSmartBannerButtonColorCorrect(WIKI[2]);
      PageObjectLogging.log(
          "Smart banner button color",
          "is correct",
          "is wrong",
          result
      );
    }
  }
}
