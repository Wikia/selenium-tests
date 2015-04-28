package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Alignment;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.ImageSize;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Setting;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorMediaSettingsDialog extends VisualEditorDialog {

  @FindBy(css = ".oo-ui-outlineOptionWidget")
  private List<WebElement> outlineMenuItems;
  @FindBy(css = ".oo-ui-window-ready .ve-ce-documentNode")
  private WebElement captionEditArea;
  @FindBy(css = ".oo-ui-window-foot .oo-ui-labelElement-label")
  private WebElement applyChangesButton;
  @FindBy(css = ".ve-ui-dimensionsWidget input")
  private List<WebElement> customSizeInputs;
  @FindBy(css = ".oo-ui-buttonSelectWidget a")
  private List<WebElement> positionButtons;

  private By labelElementBy = By.cssSelector(".oo-ui-labelElement-label");

  public VisualEditorMediaSettingsDialog(WebDriver driver) {
    super(driver);
  }

  public void selectSettings(Setting setting) {
    waitForDialogVisible();
    WebElement
        generalSetting =
        outlineMenuItems.get(setting.ordinal()).findElement(labelElementBy);
    waitForElementClickableByElement(generalSetting);
    generalSetting.click();
    PageObjectLogging.log("selectSettings", setting.toString() + " setting is selected", true);
    driver.switchTo().defaultContent();
  }

  public void typeCaption(String text) {
    waitForDialogVisible();
    waitForElementVisibleByElement(captionEditArea);
    captionEditArea.sendKeys(text);
    PageObjectLogging.log("typeCaption", "Typed " + text + " in caption area", true);
    driver.switchTo().defaultContent();
  }

  public VisualEditorPageObject clickApplyChangesButton() {
    waitForDialogVisible();
    waitForElementVisibleByElement(applyChangesButton);
    waitForElementClickableByElement(applyChangesButton);
    applyChangesButton.click();
    waitForDialogNotVisible();
    return new VisualEditorPageObject(driver);
  }

  private void typeCustomSize(int size, ImageSize side) {
    WebElement customSizeInput = customSizeInput = customSizeInputs.get(side.ordinal());
    customSizeInput.clear();
    customSizeInput.sendKeys(Integer.toString(size));
    PageObjectLogging.log("typeCustomSize", "Typed " + size + " in the field", true, driver);
    driver.switchTo().defaultContent();
  }

  public void setCustomSize(int size, ImageSize side) {
    waitForDialogVisible();
    typeCustomSize(size, side);
    driver.switchTo().defaultContent();
  }

  public void clickAlignment(Alignment align) {
    waitForDialogVisible();
    WebElement button = positionButtons.get(align.ordinal()).findElement(labelElementBy);
    waitForElementClickableByElement(button);
    button.click();
    PageObjectLogging.log("clickAlignment", align.toString() + " align is selected", true);
    driver.switchTo().defaultContent();
  }
}
