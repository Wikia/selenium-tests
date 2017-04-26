package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VisualEditorDialog extends WikiBasePageObject {

  @FindBy(css = ".oo-ui-window-ready .oo-ui-window-frame")
  private WebElement frame;
  @FindBy(css = ".oo-ui-window-ready")
  protected WebElement dialog;
  @FindBy(css = ".oo-ui-window-ready .oo-ui-icon-close")
  private WebElement closeButton;

  public VisualEditorDialog(WebDriver driver) {
    super();
    waitForDialogVisible();
  }

  @Deprecated
  public void switchToIFrame() {
    wait.forElementVisible(dialog);
    wait.forElementVisible(dialog);
    driver.switchTo().frame(frame);
  }

  public void waitForDialogVisible() {
    wait.forElementVisible(dialog);
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
    waitForDialogVisible();
    wait.forElementClickable(closeButton);
    closeButton.click();
    waitForDialogNotVisible();
    PageObjectLogging.log("closeDialog", "Dialog is closed", true);
    return new VisualEditorPageObject();
  }
}
