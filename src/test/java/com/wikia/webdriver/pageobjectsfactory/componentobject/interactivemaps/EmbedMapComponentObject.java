package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmbedMapComponentObject extends InteractiveMapPageObject {

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

  public EmbedMapComponentObject(WebDriver driver) {
    super();
  }

  public void verifyMapTitlePresented() {
    wait.forElementVisible(mapTitle);
    Assertion.assertEquals(isElementOnPage(mapTitle), true);
  }

  public void verifyCloseButtonPresented() {
    wait.forElementVisible(closeModalButton);
    Assertion.assertEquals(isElementOnPage(closeModalButton), true);
  }

  public void verifyEmbedMapModalOpened() {
    wait.forElementVisible(mapModal);
    Assertion.assertEquals(isElementOnPage(mapModal), true);
  }

  public void verifyMapElementsPresented() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(filterBox);
    Assertion.assertEquals(isElementOnPage(filterBox), true);
    wait.forElementVisible(zoomInButton);
    Assertion.assertEquals(isElementOnPage(zoomInButton), true);
    wait.forElementVisible(zoomOutButton);
    Assertion.assertEquals(isElementOnPage(zoomOutButton), true);
    PageObjectLogging.log("verifyMapElementsPresented", "Map elements was presented", true, driver);
    driver.switchTo().defaultContent();
  }

  public void verifyBrandFooterNotVisible() {
    Assertion.assertEquals(isElementOnPage(brandFooter), false);
  }

  public void verifyBranFooterVisible() {
    Assertion.assertEquals(isElementOnPage(brandFooter), true);
  }
}
