package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsFloorAdhesionSkinContext;

import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateDontLogout {

  private final String WIKI_NAME = "adtest";
  private final String ARTICLE_TITLE = "FLOOR_ADHESION";

  @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
  public void testFloorAdhesionPresence() {
    String browser = Configuration.getBrowser();
    String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);
    AdsFloorAdhesionSkinContext skinContext = new AdsFloorAdhesionSkinContext(browser);

    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);

    wikiPage.verifyFloorAdhesionPresent(
        skinContext.getSlotName(),
        skinContext.getLineItemId(),
        skinContext.getCreativeId()
    );
    wikiPage.verifyThereIsNoWikiaBar(browser);
  }

  @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
  public void testFloorAdhesionModal() {
    String browser = Configuration.getBrowser();
    String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);

    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);
    AdsFloorAdhesionSkinContext skinContext = new AdsFloorAdhesionSkinContext(browser);

    String floorAdhesionModalSelector = skinContext.getModalSelector();
    String floorAdhesionModalCloseSelector = skinContext.getModalCloseSelector();

    wikiPage.clickFloorAdhesion().verifyModalOpened(floorAdhesionModalSelector);

    wikiPage.clickFloorAdhesionModalClose(floorAdhesionModalCloseSelector)
        .verifyThereIsNoModal(floorAdhesionModalSelector);
  }

  @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
  public void testFloorAdhesionCloseButton() {
    String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);
    AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);
    wikiPage.clickFloorAdhesionClose().verifyThereIsNoFloorAdhesion();
  }

}
