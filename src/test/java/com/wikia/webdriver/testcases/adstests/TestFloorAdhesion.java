package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;

import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateDontLogout {
    private final String WIKI_NAME = "adtest";
    private final String ARTICLE_TITLE = "FLOOR_ADHESION";

    private enum Selectors {
        OASIS_MODAL_CSS("#blackout_ext-wikia-adEngine-template-modal"),
        OASIS_MODAL_CLOSE_CSS("#ext-wikia-adEngine-template-modal .close"),
        MERCURY_MODAL_CSS(".ads-lightbox"),
        MERCURY_MODAL_CLOSE_CSS(".lightbox-close-wrapper");
        private String selector;

        private Selectors(String selector) {
            this.selector = selector;
        }
    }

    @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
    public void testFloorAdhesion() {
        String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);
        AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);

        String floorAdhesionModalSelector =
                "CHROMEMOBILEMERCURY".equalsIgnoreCase(config.getBrowser()) ?
                        Selectors.MERCURY_MODAL_CSS.selector :
                        Selectors.OASIS_MODAL_CSS.selector;

        String floorAdhesionModalCloseSelector =
                "CHROMEMOBILEMERCURY".equalsIgnoreCase(config.getBrowser()) ?
                        Selectors.MERCURY_MODAL_CLOSE_CSS.selector :
                        Selectors.OASIS_MODAL_CLOSE_CSS.selector;

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
