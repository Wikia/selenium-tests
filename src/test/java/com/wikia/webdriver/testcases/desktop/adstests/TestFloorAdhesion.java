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

  private static final String
      ARTICLE_TITLE
      = "SyntheticTests/Slots/InvisibleHighImpact/FloorAdhesion";

  private static final String
      URL_TRIGGER
      = "InstantGlobals.wgAdDriverHighImpact2SlotCountries=[XX]";

  private static final String SLOT_NAME = "INVISIBLE_HIGH_IMPACT_2";
  private static final String LINE_ITEM_ID = "270609492";
  private static final String CREATIVE_ID = "94178805972";
  private UrlBuilder urlBuilder = UrlBuilder.createUrlBuilderForWiki(WIKI_NAME);

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "AdsFloorAdhesionMercury")
  public void testFloorAdhesionPresenceMercury() {
    testFloorAdhesionPresenceMercury(WindowSize.PHONE, true);
  }

  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = "AdsFloorAdhesionMercury")
  public void testFloorAdhesionCloseButtonMercury() {
    testFloorAdhesionCloseButtonMercury(WindowSize.PHONE);
  }

  @Test(groups = "AdsFloorAdhesionOasis")
  public void testFloorAdhesionPresenceOasis() {
    String browser = Configuration.getBrowser();
    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver,
                                                                 getArticleUrl(ARTICLE_TITLE,
                                                                               URL_TRIGGER
                                                                 )
    );

    wikiPage.verifyFloorAdhesionPresent(SLOT_NAME, LINE_ITEM_ID, CREATIVE_ID);
    wikiPage.verifyThereIsNoWikiaBar(browser);
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

  private void testFloorAdhesionPresenceMercury(Dimension resolution, Boolean isMobile) {
    AdsFloorAdhesionSkinContext skinContext = new AdsFloorAdhesionSkinContext(isMobile);

    AdsFloorAdhesionOldObject wikiPage = new AdsFloorAdhesionOldObject(driver,
                                                                       getArticleUrl(
                                                                           ARTICLE_TITLE,
                                                                           URL_TRIGGER
                                                                       ),
                                                                       resolution
    );

    wikiPage.verifyFloorAdhesionPresent(skinContext.getSlotName(),
                                        skinContext.getLineItemId(),
                                        skinContext.getCreativeId()
    );
    wikiPage.verifyThereIsNoWikiaBar(isMobile);
  }

  private void testFloorAdhesionCloseButtonMercury(Dimension resolution) {
    AdsFloorAdhesionOldObject wikiPage = new AdsFloorAdhesionOldObject(driver,
                                                                       getArticleUrl(
                                                                           ARTICLE_TITLE,
                                                                           URL_TRIGGER
                                                                       ),
                                                                       resolution
    );
    wikiPage.clickFloorAdhesionClose().verifyThereIsNoFloorAdhesion();
  }
}
