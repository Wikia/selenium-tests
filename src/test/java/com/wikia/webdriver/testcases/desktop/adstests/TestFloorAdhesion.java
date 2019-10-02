package com.wikia.webdriver.testcases.desktop.adstests;

import com.wikia.webdriver.common.WindowSize;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionOldObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsFloorAdhesionSkinContext;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateNoFirstLoad {

  private static final String WIKI_NAME = "project43";

  private static final String OLD_ARTICLE_TITLE = "SyntheticTests/FloorAdhesion";
  private static final String
      ARTICLE_TITLE
      = "SyntheticTests/Slots/InvisibleHighImpact/FloorAdhesion";

  private static final String OLD_URL_TRIGGER = "highimpactslot=1";
  private static final String
      URL_TRIGGER
      = "InstantGlobals.wgAdDriverHighImpact2SlotCountries=[XX]";

  private static final String SLOT_NAME = "invisible_high_impact_2";
  private static final String LINE_ITEM_ID = "270609492";
  private static final String CREATIVE_ID = "94178805972";
  private UrlBuilder urlBuilder = UrlBuilder.createUrlBuilderForWiki(WIKI_NAME);

  @Test(groups = "AdsFloorAdhesionOasis")
  public void testFloorAdhesionPresenceOasis() {
    String browser = Configuration.getBrowser();
    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver,
                                                                 getArticleUrl(ARTICLE_TITLE,
                                                                               URL_TRIGGER
                                                                 )
    );

    wikiPage.verifyFloorAdhesionPresent(SLOT_NAME, LINE_ITEM_ID, CREATIVE_ID);
  }

  @Test(groups = "AdsFloorAdhesionOasis")
  public void testFloorAdhesionCloseButtonOasis() {
    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver,
                                                                 getArticleUrl(ARTICLE_TITLE,
                                                                               URL_TRIGGER
                                                                 )
    );
    wikiPage.clickFloorAdhesionClose().verifyThereIsNoFloorAdhesion();
  }

  private String getArticleUrl(String articleTitle, String urlTrigger) {
    String url = urlBuilder.getUrlForPath(articleTitle);
    return urlBuilder.appendQueryStringToURL(url, urlTrigger);
  }
}
