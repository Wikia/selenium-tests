package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

public class AdsFloorAdhesionObject extends AdsBaseObject {
    private final String FLOOR_ADHESION_CSS = "#ext-wikia-adEngine-template-footer";
    private final String FLOOR_ADHESION_AD_CSS = "#ext-wikia-adEngine-template-footer .ad";
    private final String FLOOR_ADHESION_CLOSE_CSS = "#ext-wikia-adEngine-template-footer .close";
    private final String WIKIA_BAR_CSS = "#WikiaBar";

    public AdsFloorAdhesionObject(WebDriver driver, String testedPage) {
        super(driver, testedPage);
    }

    private Boolean isCssElementPresent(String cssSelector) {
        try {
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            if( element.isDisplayed() ) {
                return true;
            } else {
                return false;
            }
        } catch( Exception e ) {
            return false;
        }
    }

    private Boolean isCssElementNotVisible(String cssSelector) {
        try {
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            waitForElementNotVisibleByElement(element);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private Boolean isCssElementNotPresent(String cssSelector) {
        try {
            driver.findElement(By.cssSelector(cssSelector));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public Boolean isFloorAdhesionPresent() {
        waitForElementByCss(FLOOR_ADHESION_CSS);
        return isCssElementPresent(FLOOR_ADHESION_CSS);
    }

    public Boolean thereIsNoFloorAdhesion() {
        return isCssElementNotVisible(FLOOR_ADHESION_CSS);
    }

    public AdsFloorAdhesionObject clickFloorAdhesion() {
        driver.findElement(By.cssSelector(FLOOR_ADHESION_AD_CSS)).click();
        return this;
    }

    public AdsFloorAdhesionObject clickFloorAdhesionModalClose(
            String floorAdhesionModalCloseSelector
    ) {
        driver.findElement(By.cssSelector(floorAdhesionModalCloseSelector)).click();
        return this;
    }

    public AdsFloorAdhesionObject clickFloorAdhesionClose() {
        driver.findElement(By.cssSelector(FLOOR_ADHESION_CLOSE_CSS)).click();
        return this;
    }

    public Boolean verifyModalOpened(String floorAdhesionModalSelector) {
        waitForElementByCss(floorAdhesionModalSelector);
        return isCssElementPresent(floorAdhesionModalSelector);
    }

    public Boolean verifyThereIsNoModal(String floorAdhesionModalSelector) {
        return isCssElementNotPresent(floorAdhesionModalSelector);
    }

    public Boolean thereIsNoWikiaBar() {
        return isCssElementNotVisible(WIKIA_BAR_CSS);
    }
}
