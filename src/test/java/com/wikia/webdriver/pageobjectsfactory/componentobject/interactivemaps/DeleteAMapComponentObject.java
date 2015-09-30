package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapsPageObject;

/**
 * @author Robert 'Rochan' Chan
 */

public class DeleteAMapComponentObject extends BasePageObject {

  @FindBy(css = "#intMapsDeleteMapModal .button.primary")
  private WebElement deleteMapButton;
  @FindBy(css = "#intMapsDeleteMapModal")
  private WebElement deleteMapModal;
  @FindBy(css = "#intMapError")
  private WebElement deleteMapError;

  public DeleteAMapComponentObject(WebDriver driver) {
    super(driver);
  }

  public InteractiveMapsPageObject deleteMap() {
    clickDeleteMap();
    waitForElementNotVisibleByElement(deleteMapModal);
    return new InteractiveMapsPageObject(driver);
  }

  public void clickDeleteMap() {
    wait.forElementVisible(deleteMapModal);
    wait.forElementClickable(deleteMapButton);
    deleteMapButton.click();
  }

  public String getDeleteMapError() {
    wait.forElementVisible(deleteMapError);
    return deleteMapError.getText();
  }
}
