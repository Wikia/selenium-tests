package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;

import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateDontLogout {
    private final String WIKI_NAME = "adtest";
    private final String ARTICLE_TITLE = "FLOOR_ADHESION";

    private class ModalSelectors {
        private final String OASIS_MODAL_CSS = "#blackout_ext-wikia-adEngine-template-modal";
        private final String OASIS_MODAL_CLOSE_CSS = "#ext-wikia-adEngine-template-modal .close";

        private final String MERCURY_MODAL_CSS = ".ads-lightbox";
        private final String MERCURY_MODAL_CLOSE_CSS = ".lightbox-close-wrapper";

        private String modalSelector;
        private String modalCloseSelector;

        public ModalSelectors(String skin) {
            modalSelector = OASIS_MODAL_CSS;
            modalCloseSelector = OASIS_MODAL_CLOSE_CSS;

            if("mercury".equalsIgnoreCase(skin)) {
                modalSelector = MERCURY_MODAL_CSS;
                modalCloseSelector = MERCURY_MODAL_CLOSE_CSS;
            }
        }

        public String getModalSelector() {
            return modalSelector;
        }

        public String getModalCloseSelector() {
            return modalCloseSelector;
        }
    }

    @Test(groups = {"TestFloorAdhesion", "MercuryAds"})
    public void testFloorAdhesion() {
        String browser = config.getBrowser();
        String skin = "CHROMEMOBILEMERCURY".equalsIgnoreCase(browser) ? "mercury" : "oasis";
        String testPage = urlBuilder.getUrlForPath(WIKI_NAME, ARTICLE_TITLE);

        AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);
        ModalSelectors modalSelectors = new ModalSelectors(skin);

        String floorAdhesionModalSelector = modalSelectors.getModalSelector();
        String floorAdhesionModalCloseSelector = modalSelectors.getModalCloseSelector();

        PageObjectLogging.log(
                "Check visibility",
                "Floor Adhesion should be displayed after opening the page",
                wikiPage.isFloorAdhesionPresent()
        );

        if(!"mercury".equalsIgnoreCase(skin)) {
            PageObjectLogging.log(
                    "Check visibility",
                    "There should be no Wikia Bar when Floor Adhesion is visible",
                    wikiPage.thereIsNoWikiaBar()
            );
        }

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
