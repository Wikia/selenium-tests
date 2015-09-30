package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 */

public class CreateAMapComponentObject extends BasePageObject {

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

  public CreateAMapComponentObject(WebDriver driver) {
    super(driver);
  }

  public CreateACustomMapComponentObject clickCustomMap() {
    wait.forElementVisible(customMapLink);
    customMapLink.click();
    LOG.success("clickCustomMap", "custom map link clicked", true);
    return new CreateACustomMapComponentObject(driver);
  }

  public CreateRealMapComponentObject clickRealMap() {
    wait.forElementVisible(realMapLink);
    realMapLink.click();
    LOG.success("clickRealMap", "Real Map link clicked", true);
    return new CreateRealMapComponentObject(driver);
  }

  public InteractiveMapsPageObject clickCloseButton() {
    wait.forElementVisible(closeButton);
    closeButton.click();
    return new InteractiveMapsPageObject(driver);
  }

  public void verifyLearnMoreLinkRedirect(String link) {
    wait.forElementVisible(learnMoreLink);
    Assertion.assertEquals(learnMoreLink.getAttribute("href").contains(link), true);
  }

  public void verifyLoginModal() {
    wait.forElementVisible(loginModal);
    LOG.success("verifyLoginModal", "Login modal is displayed");
  }

  public void verifyRealMapAndCustomMapButtons() {
    wait.forElementVisible(realMapLink);
    wait.forElementVisible(customMapLink);
    LOG.success("verifyRealMapAndCustomMapButtons", "Real Map and Custom Map links are visible");
  }
}
