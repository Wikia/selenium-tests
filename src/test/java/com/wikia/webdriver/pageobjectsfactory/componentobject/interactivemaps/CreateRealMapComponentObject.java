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

public class CreateRealMapComponentObject extends BasePageObject {

  @FindBy(css = "input[name=map-title]")
  private WebElement mapTitleField;
  @FindBy(css = "#intMapPreviewImage")
  private WebElement realMapImagePreview;
  @FindBy(css = "#intMapBack")
  private WebElement backButton;
  @FindBy(css = "#intMapNext")
  private WebElement nextButton;
  @FindBy(css = ".close")
  private WebElement closeButton;
  @FindBy(css = "#intMapError")
  private WebElement mapErrorField;

  public CreateRealMapComponentObject(WebDriver driver) {
    super(driver);
  }

  public CreateAMapComponentObject clickBack() {
    wait.forElementVisible(backButton);
    backButton.click();
    LOG.success("clickCustomMap", "custom map link clicked", true);
    return new CreateAMapComponentObject(driver);
  }

  public CreatePinTypesComponentObject clickNext() {
    wait.forElementVisible(nextButton);
    nextButton.click();
    LOG.success("clickCustomMap", "custom map link clicked", true);
    return new CreatePinTypesComponentObject(driver);
  }

  public InteractiveMapsPageObject clickClose() {
    wait.forElementVisible(closeButton);
    closeButton.click();
    return new InteractiveMapsPageObject(driver);
  }

  public void typeMapName(String mapName) {
    wait.forElementVisible(mapTitleField);
    mapTitleField.sendKeys(mapName);
    LOG.success("typeMapName", mapName + " title for map is typed in");
  }

  public void verifyRealMapPreviewImage() {
    wait.forElementVisible(realMapImagePreview);
    Assertion.assertFalse(realMapImagePreview.getAttribute("src").isEmpty());
    LOG.success("verifyRealMapPreviewImage", "Real map preview image is visible");
  }

  public void verifyErrorExists() {
    wait.forElementVisible(mapErrorField);
    Assertion.assertFalse(mapErrorField.getText().isEmpty());
  }
}
