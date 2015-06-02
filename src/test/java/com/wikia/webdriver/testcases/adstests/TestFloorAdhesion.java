package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.dataprovider.mobile.MobileAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFloorAdhesionObject;

import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateDontLogout {
    @Test(
            dataProvider = "floorAdhesionOnOasis",
            dataProviderClass = AdsDataProvider.class,
            groups = {"TestFloorAdhesionOasis"}
    )
    public void testFloorAdhesionOnOasis(
            String wikiName,
            String article,
            String floorAdhesionModalSelector,
            String floorAdhesionModalCloseSelector
    ) {
        String testPage = urlBuilder.getUrlForPath(wikiName, article);
        AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);

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

    @Test(
            dataProvider = "floorAdhesionOnMercury",
            dataProviderClass = MobileAdsDataProvider.class,
            groups = {"TestFloorAdhesionMercury", "MercuryAds"}
    )
    public void floorAdhesionOnMercury(
            String wikiName,
            String article,
            String floorAdhesionModalSelector,
            String floorAdhesionModalCloseSelector
    ) {
        String testPage = urlBuilder.getUrlForPath(wikiName, article);
        AdsFloorAdhesionObject wikiPage = new AdsFloorAdhesionObject(driver, testPage);

        PageObjectLogging.log(
                "Check visibility",
                "Floor Adhesion should be displayed after opening the page",
                wikiPage.isFloorAdhesionPresent()
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
