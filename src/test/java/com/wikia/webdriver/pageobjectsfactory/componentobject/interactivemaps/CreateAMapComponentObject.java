package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author: Lukasz Jedrzejczak
 */

public class CreateAMapComponentObject extends BasePageObject {

  public CreateAMapComponentObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = ".int-map-icon-geo-tile-set-blue")
  private WebElement realMapLink;
  @FindBy(css = ".int-map-icon-custom-tile-set-blue")
  private WebElement customMapLink;
  @FindBy(css = "#userForceLoginModal")
  private WebElement loginModal;
  @FindBy(css = ".tip > a")
  private WebElement learnMoreLink;
  @FindBy(css = ".close")
  private WebElement closeButton;

  public CreateACustomMapComponentObject clickCustomMap() {
    waitForElementByElement(customMapLink);
    customMapLink.click();
    PageObjectLogging.log("clickCustomMap", "custom map link clicked", true, driver);
    return new CreateACustomMapComponentObject(driver);
  }

  public CreateRealMapComponentObject clickRealMap() {
    waitForElementByElement(realMapLink);
    realMapLink.click();
    PageObjectLogging.log("clickRealMap", "Real Map link clicked", true, driver);
    return new CreateRealMapComponentObject(driver);
  }

  public InteractiveMapsPageObject clickCloseButton() {
    waitForElementByElement(closeButton);
    closeButton.click();
    return new InteractiveMapsPageObject(driver);
  }

  public void verifyLearnMoreLinkRedirect(String link) {
    waitForElementByElement(learnMoreLink);
    Assertion.assertEquals(learnMoreLink.getAttribute("href").contains(link), true);
  }

  public void verifyLoginModal() {
    waitForElementByElement(loginModal);
    PageObjectLogging.log("verifyLoginModal", "Login modal is displayed", true);
  }

  public void verifyRealMapAndCustomMapButtons() {
    waitForElementByElement(realMapLink);
    waitForElementByElement(customMapLink);
    PageObjectLogging
        .log("verifyRealMapAndCustomMapButtons", "Real Map and Custom Map links are visible", true);
  }
}
