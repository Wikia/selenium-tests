package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.ModalSelectorsHelper;

import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateDontLogout {
    private final String WIKI_NAME = "adtest";
    private final String ARTICLE_TITLE = "FLOOR_ADHESION";

    @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
    public void testFloorAdhesionPresence() {
        String browser = config.getBrowser();
        String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);

        AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);

        wikiPage.verifyFloorAdhesionPresent();

        wikiPage.verifyThereIsNoWikiaBar(browser);
    }

    @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
    public void testFloorAdhesionModal() {
        String browser = config.getBrowser();
        String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);

        AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);
        ModalSelectorsHelper modalSelectors = new ModalSelectorsHelper(browser);

        String floorAdhesionModalSelector = modalSelectors.getModalSelector();
        String floorAdhesionModalCloseSelector = modalSelectors.getModalCloseSelector();

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
