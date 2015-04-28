package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VisualEditorSourceEditorDialog extends VisualEditorDialog {

  @FindBy(css = ".oo-ui-window-foot .oo-ui-buttonElement-button")
  private WebElement applyChangesButton;
  @FindBy(css = ".wikiaThrobber")
  private WebElement loadingIndicator;

  private By editAreaBy = By.cssSelector(".oo-ui-widget-enabled.oo-ui-textInputWidget textarea");

  public VisualEditorSourceEditorDialog(WebDriver driver) {
    super(driver);
  }

  public VisualEditorPageObject clickApplyChangesButton() {
    waitForDialogVisible();
    waitForElementClickableByElement(applyChangesButton);
    applyChangesButton.click();
    waitForElementNotVisibleByElement(loadingIndicator);
    waitForDialogNotVisible();
    return new VisualEditorPageObject(driver);
  }

  public void typeInEditArea(String text) {
    waitForDialogVisible();
    waitForElementNotVisibleByElement(loadingIndicator);
    WebElement editArea = dialog.findElement(editAreaBy);
    waitForElementClickableByElement(editArea);
    editArea.sendKeys(text);
    waitForValueToBePresentInElementsAttributeByElement(editArea, "value", text);
    PageObjectLogging.log("typeInEditArea", "Typed " + text, true, driver);
  }
}
