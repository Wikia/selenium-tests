package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateDontLogout;

import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.ModalSelectorsHelper;

import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateDontLogout {
    private final String WIKI_NAME = "adtest";
    private final String ARTICLE_TITLE = "FLOOR_ADHESION";

    @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
    public void testFloorAdhesion() {
        String browser = config.getBrowser();
        String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);

        AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);
        ModalSelectorsHelper modalSelectors = new ModalSelectorsHelper(browser);

        String floorAdhesionModalSelector = modalSelectors.getModalSelector();
        String floorAdhesionModalCloseSelector = modalSelectors.getModalCloseSelector();

        PageObjectLogging.log(
                "Check visibility",
                "Floor Adhesion should be displayed after opening the page",
                wikiPage.isFloorAdhesionPresent()
        );


        PageObjectLogging.log(
                "Check visibility",
                "There should be no Wikia Bar when Floor Adhesion is visible",
                wikiPage.thereIsNoWikiaBar()
        );

        PageObjectLogging.log(
                "Check visibility",
                "Clicking Floor Adhesion opens light-box",
                wikiPage.clickFloorAdhesion().verifyModalOpened(floorAdhesionModalSelector)
        );

        PageObjectLogging.log(
                "Check visibility",
                "Clicking light-box close button hides light-box",
                wikiPage
                        .clickFloorAdhesionModalClose(floorAdhesionModalCloseSelector)
                        .verifyThereIsNoModal(floorAdhesionModalSelector)
        );

        PageObjectLogging.log(
                "Check visibility",
                "Floor Adhesion should be displayed after closing light-box",
                wikiPage.isFloorAdhesionPresent()
        );

        PageObjectLogging.log(
                "Check visibility",
                "Clicking Floor Adhesion close button hides ad unit",
                wikiPage.clickFloorAdhesionClose().thereIsNoFloorAdhesion()
        );
    }

}
