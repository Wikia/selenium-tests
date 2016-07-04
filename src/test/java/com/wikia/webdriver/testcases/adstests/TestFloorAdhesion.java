package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionOldObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsFloorAdhesionSkinContext;

import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateNoFirstLoad {

  private static final String WIKI_NAME = "project43";

  private static final String OLD_ARTICLE_TITLE = "SyntheticTests/FloorAdhesion";
  private static final String ARTICLE_TITLE =
      "SyntheticTests/Slots/InvisibleHighImpact/FloorAdhesion";

  private static final String OLD_URL_TRIGGER = "highimpactslot=1";
  private static final String URL_TRIGGER =
      "InstantGlobals.wgAdDriverHighImpact2SlotCountries=[XX]";

  private static final String SLOT_NAME = "INVISIBLE_HIGH_IMPACT_2";
  private static final String LINE_ITEM_ID = "270609492";
  private static final String CREATIVE_ID = "94178805972";

  @Test(
      groups = {"AdsFloorAdhesionOasis", "AdsFloorAdhesionMercury"}
  )
  public void testOldFloorAdhesionPresence() {
    String browser = Configuration.getBrowser();
    AdsFloorAdhesionSkinContext skinContext = new AdsFloorAdhesionSkinContext(browser);

    AdsFloorAdhesionOldObject wikiPage =
        new AdsFloorAdhesionOldObject(driver, getArticleUrl(OLD_ARTICLE_TITLE, OLD_URL_TRIGGER));

    wikiPage.verifyFloorAdhesionPresent(
        skinContext.getSlotName(),
        skinContext.getLineItemId(),
        skinContext.getCreativeId()
    );
    wikiPage.verifyThereIsNoWikiaBar(browser);
  }

  @Test(
      groups = {"AdsFloorAdhesionOasis", "AdsFloorAdhesionMercury"}
  )
  public void testOldFloorAdhesionModal() {
    String browser = Configuration.getBrowser();

    AdsFloorAdhesionOldObject wikiPage =
        new AdsFloorAdhesionOldObject(driver, getArticleUrl(OLD_ARTICLE_TITLE, OLD_URL_TRIGGER));
    AdsFloorAdhesionSkinContext skinContext = new AdsFloorAdhesionSkinContext(browser);

    String floorAdhesionModalSelector = skinContext.getModalSelector();
    String floorAdhesionModalCloseSelector = skinContext.getModalCloseSelector();

    wikiPage.clickFloorAdhesion().verifyModalOpened(floorAdhesionModalSelector);

    wikiPage.clickFloorAdhesionModalClose(floorAdhesionModalCloseSelector)
        .verifyThereIsNoModal(floorAdhesionModalSelector);
  }

  @Test(
      groups = {"AdsFloorAdhesionOasis", "AdsFloorAdhesionMercury"}
  )
  public void testOldFloorAdhesionCloseButton() {
    AdsFloorAdhesionOldObject wikiPage =
        new AdsFloorAdhesionOldObject(driver, getArticleUrl(OLD_ARTICLE_TITLE, OLD_URL_TRIGGER));
    wikiPage.clickFloorAdhesionClose().verifyThereIsNoFloorAdhesion();
  }

  @Test(
      groups = "AdsFloorAdhesionOasis"
  )
  public void testFloorAdhesionPresence() {
    String browser = Configuration.getBrowser();
    AdsFloorAdhesionObject wikiPage =
        new AdsFloorAdhesionObject(driver, getArticleUrl(ARTICLE_TITLE, URL_TRIGGER));

    wikiPage.verifyFloorAdhesionPresent(SLOT_NAME, LINE_ITEM_ID, CREATIVE_ID);
    wikiPage.verifyThereIsNoWikiaBar(browser);
  }

  @Test(
      groups = {"AdsFloorAdhesionOasis"}
  )
  public void testFloorAdhesionCloseButton() {
    AdsFloorAdhesionObject wikiPage =
        new AdsFloorAdhesionObject(driver, getArticleUrl(ARTICLE_TITLE, URL_TRIGGER));
    wikiPage.clickFloorAdhesionClose().verifyThereIsNoFloorAdhesion();
  }

  private String getArticleUrl(String articleTitle, String urlTrigger) {
    String url = urlBuilder.getUrlForPath(WIKI_NAME, articleTitle);
    return urlBuilder.appendQueryStringToURL(url, urlTrigger);
  }
}
