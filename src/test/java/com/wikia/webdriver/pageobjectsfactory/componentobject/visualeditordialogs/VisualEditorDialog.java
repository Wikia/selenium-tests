package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Robert 'rochan' Chan
 */

public class VisualEditorDialog extends WikiBasePageObject {

  @FindBy(css = ".oo-ui-window-ready .oo-ui-window-frame")
  private WebElement frame;
  @FindBy(css = ".oo-ui-window-ready")
  protected WebElement dialog;
  @FindBy(css = ".oo-ui-window-ready .oo-ui-icon-close")
  private WebElement closeButton;

  public VisualEditorDialog(WebDriver driver) {
    super(driver);
    waitForDialogVisible();
  }

  @Deprecated
  public void switchToIFrame() {
    waitForElementByElement(dialog);
    waitForElementVisibleByElement(dialog);
    driver.switchTo().frame(frame);
  }

  public void waitForDialogVisible() {
    waitForElementVisibleByElement(dialog);
  }

  @Deprecated
  public void switchOutOfIFrame() {
    waitForElementNotVisibleByElement(dialog);
    driver.switchTo().defaultContent();
  }

  public void waitForDialogNotVisible() {
    waitForElementNotVisibleByElement(dialog);
  }

  @Deprecated
  public void switchOutOfAllIFrame() {
    driver.switchTo().defaultContent();
    waitForElementNotVisibleByElement(dialog);
  }

  public VisualEditorPageObject closeDialog() {
    waitForElementClickableByElement(closeButton);
    closeButton.click();
    waitForDialogNotVisible();
    PageObjectLogging.log("closeDialog", "Dialog is closed", true);
    return new VisualEditorPageObject(driver);
  }
}
