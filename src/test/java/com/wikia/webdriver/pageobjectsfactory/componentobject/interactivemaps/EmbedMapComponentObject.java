package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Łukasz Nowak (Dyktus)
 */

public class EmbedMapComponentObject extends InteractiveMapPageObject {

  public EmbedMapComponentObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = ".leaflet-control-zoom-in")
  private WebElement zoomInButton;
  @FindBy(css = ".leaflet-control-zoom-out")
  private WebElement zoomOutButton;
  @FindBy(css = "#filterMenu")
  private WebElement filterBox;
  @FindBy(css = "a[title=close]")
  private WebElement closeModalButton;
  @FindBy(css = "header > h3")
  private WebElement mapTitle;
  @FindBy(css = "iframe[name=wikia-interactive-map]")
  private WebElement mapFrame;
  @FindBy(css = ".modal")
  private WebElement mapModal;
  @FindBy(css = "#attr")
  private WebElement brandFooter;

  public void verifyMapTitlePresented() {
    waitForElementVisibleByElement(mapTitle);
    Assertion.assertEquals(checkIfElementOnPage(mapTitle), true);
  }

  public void verifyCloseButtonPresented() {
    waitForElementVisibleByElement(closeModalButton);
    Assertion.assertEquals(checkIfElementOnPage(closeModalButton), true);
  }

  public void verifyEmbedMapModalOpened() {
    waitForElementVisibleByElement(mapModal);
    Assertion.assertEquals(checkIfElementOnPage(mapModal), true);
  }

  public void verifyMapElementsPresented() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(filterBox);
    Assertion.assertEquals(checkIfElementOnPage(filterBox), true);
    waitForElementVisibleByElement(zoomInButton);
    Assertion.assertEquals(checkIfElementOnPage(zoomInButton), true);
    waitForElementVisibleByElement(zoomOutButton);
    Assertion.assertEquals(checkIfElementOnPage(zoomOutButton), true);
    PageObjectLogging.log("verifyMapElementsPresented", "Map elements was presented", true, driver);
    driver.switchTo().defaultContent();
  }

  public void verifyBrandFooterNotVisible() {
    Assertion.assertEquals(checkIfElementOnPage(brandFooter), false);
  }

  public void verifyBranFooterVisible() {
    Assertion.assertEquals(checkIfElementOnPage(brandFooter), true);
  }

}
