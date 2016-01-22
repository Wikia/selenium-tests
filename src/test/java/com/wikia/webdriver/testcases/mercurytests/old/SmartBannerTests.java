package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.old.SmartBannerComponentObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
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

    Colors(String hex) {
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

  @Test(groups = "MercurySmartBannerTest_001")
  public void MercurySmartBannerTest_001_ButtonName_FixPosition_Close() {
    SmartBannerComponentObject banner = new SmartBannerComponentObject(driver);
    wikiURL = urlBuilder.getUrlForWiki(WIKIS[0][0]);
    banner.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, WIKIS[0][1]);
    JavascriptActions jsActions = new JavascriptActions(driver);

    Assertion.assertTrue(
        banner.isSmartBannerVisible(),
        "Smart banner is closed"
    );

    Assertion.assertTrue(banner.isButtonVisible(), "Button is not visible");

    int lastSmartBannerPosition = banner.getSmartBannerPosition();
    jsActions.scrollBy(0, 100);

    boolean result = lastSmartBannerPosition == banner.getSmartBannerPosition();
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
