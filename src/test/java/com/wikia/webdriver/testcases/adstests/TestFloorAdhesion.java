package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsFloorAdhesionSkinContext;

import org.testng.annotations.Test;
/**
 * @ownership AdEngineering Wikia
 */
public class TestFloorAdhesion extends TemplateNoFirstLoad {

  private static final String WIKI_NAME = "adtest";
  private static final String ARTICLE_TITLE = "FLOOR_ADHESION";

  @Test(
      groups = {"TestFloorAdhesion", "MercuryAds"}
  )
  public void testFloorAdhesionPresence() {
    String browser = Configuration.getBrowser();
    AdsFloorAdhesionSkinContext skinContext = new AdsFloorAdhesionSkinContext(browser);

    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, getArticleUrl());

    wikiPage.verifyFloorAdhesionPresent(
        skinContext.getSlotName(),
        skinContext.getLineItemId(),
        skinContext.getCreativeId()
    );
    wikiPage.verifyThereIsNoWikiaBar(browser);
  }

  @Test(
      groups = {"TestFloorAdhesion", "MercuryAds"}
  )
  public void testFloorAdhesionModal() {
    String browser = Configuration.getBrowser();

    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, getArticleUrl());
    AdsFloorAdhesionSkinContext skinContext = new AdsFloorAdhesionSkinContext(browser);

    String floorAdhesionModalSelector = skinContext.getModalSelector();
    String floorAdhesionModalCloseSelector = skinContext.getModalCloseSelector();

    wikiPage.clickFloorAdhesion().verifyModalOpened(floorAdhesionModalSelector);

    wikiPage.clickFloorAdhesionModalClose(floorAdhesionModalCloseSelector)
        .verifyThereIsNoModal(floorAdhesionModalSelector);
  }

  @Test(
      groups = {"TestFloorAdhesion", "MercuryAds"}
  )
  public void testFloorAdhesionCloseButton() {
    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, getArticleUrl());
    wikiPage.clickFloorAdhesionClose().verifyThereIsNoFloorAdhesion();
  }

  private String getArticleUrl() {
    String url = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);
    return urlBuilder.appendQueryStringToURL(url, "highimpactslot=1");
  }
}
