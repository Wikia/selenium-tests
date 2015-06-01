package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateDontLogout;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestFloorAdhesion extends TemplateDontLogout {
    private final String FLOOR_ADHESION_SELECTOR = "#ext-wikia-adEngine-template-footer";
    private final String FLOOR_ADHESION_AD_SELECTOR = "#ext-wikia-adEngine-template-footer .ad";
    private final String
            FLOOR_ADHESION_MODAL_SELECTOR = "#blackout_ext-wikia-adEngine-template-modal";
    private final String
            FLOOR_ADHESION_MODAL_CLOSE_SELECTOR = "#ext-wikia-adEngine-template-modal .close";
    private final String
            FLOOR_ADHESION_CLOSE_SELECTOR = "#ext-wikia-adEngine-template-footer .close";
    private final String WIKIA_BAR_SELECTOR = "#WikiaBar";

    @Test(
            dataProvider = "flooradhesion",
            dataProviderClass = AdsDataProvider.class,
            groups = {"TestFloorAdhesion"}
    )
    public void testFloorAdhesionOnOasis(String wikiName, String article) {
        String testPage = urlBuilder.getUrlForPath(wikiName, article);
        AdsBaseObject wikiPage = new AdsBaseObject(driver, testPage);
        WebElement wikiaBar = driver.findElement(By.cssSelector(WIKIA_BAR_SELECTOR));

        // verify the unit is visible
        wikiPage.waitForElementByCss(FLOOR_ADHESION_SELECTOR);
        PageObjectLogging.log(
                "testFloorAdhesionOnOasis",
                "Floor Adhesion unit visible",
                true
        );

        // verify there is no Wikia Bar
        wikiPage.waitForElementNotVisibleByElement(wikiaBar);
        PageObjectLogging.log(
                "testFloorAdhesionOnOasis",
                "WikiaBar not visible",
                true
        );

        // verify clicking it opens lightbox
        driver.findElement(By.cssSelector(FLOOR_ADHESION_AD_SELECTOR)).click();
        wikiPage.waitForElementByCss(FLOOR_ADHESION_MODAL_SELECTOR);

        // verify closing modal and closing Floor Adhesion unit works
        driver.findElement(By.cssSelector(FLOOR_ADHESION_MODAL_CLOSE_SELECTOR)).click();
        driver.findElement(By.cssSelector(FLOOR_ADHESION_CLOSE_SELECTOR)).click();

        // verify there is no Wikia Bar
        wikiPage.waitForElementNotVisibleByElement(wikiaBar);
    }
}
