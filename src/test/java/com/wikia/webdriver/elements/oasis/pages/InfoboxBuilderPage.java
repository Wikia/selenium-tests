package com.wikia.webdriver.elements.oasis.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InfoboxBuilderPage extends SpecialPageObject {

  @FindBy(css = ".InfoboxBuilder")
  private WebElement builderIFrame;
  @FindBy(css = ".sub-head .sub-head--done")
  private WebElement saveButton;
  @FindBy(css = ".edit-header--delete")
  private WebElement deleteButton;
  @FindBy(css = ".checkbox-builder")
  private WebElement titleCheckbox;
  @FindBy(css = "#infoboxRowLabel")
  private WebElement rowLabelInputField;
  @FindBy(css = ".back-arrow")
  private WebElement backArrowButton;
  @FindBy(css = ".infobox-builder-questionmark")
  private WebElement questionMarkButton;
  @FindBy(css = ".infobox-builder-sidebar .modal-dialog")
  private WebElement helpDialog;
  @FindBy(css = ".infobox-builder-preview")
  private WebElement previewArea;
  @FindBy(css = ".on-hover-tooltip")
  private WebElement tooltip;
  @FindBy(css = ".portable-infobox .pi-data-label")
  private List<WebElement> rowLabels;
  @FindBy(css = ".infobox-builder-button")
  private List<WebElement> componentsButtons;
  @FindBy(css = ".portable-infobox .pi-data")
  private List<WebElement> rows;
  @FindBy(css = ".portable-infobox .pi-title")
  private List<WebElement> titles;
  @FindBy(css = ".portable-infobox .pi-image")
  private List<WebElement> images;
  @FindBy(css = ".portable-infobox .sortable-item")
  private List<WebElement> component;

  public InfoboxBuilderPage() {
    super();
  }

  public InfoboxBuilderPage open(String templateName) {
    getUrl(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_INFOBOX_BUILDER + templateName);

    return this;
  }

  public InfoboxBuilderPage switchToIFrame() {
    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public int countRows() {
    return rows.size();
  }

  public int countTitles() {
    return titles.size();
  }

  public int countImages() {
    return images.size();
  }

  public String getBackgroundColor() {
    return component.get(0).getCssValue("background-color");
  }

  public InfoboxBuilderPage selectTitleWithIndex(int index) {
    wait.forElementVisible(titles.get(index));
    titles.get(index).click();

    return this;
  }

  public InfoboxBuilderPage selectImageWithIndex(int index) {
    wait.forElementVisible(images.get(index));
    images.get(index).click();

    return this;
  }

  public InfoboxBuilderPage selectRowWithIndex(int index) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();

    return this;
  }

  public InfoboxBuilderPage addRowComponent() {
    wait.forElementVisible(componentsButtons.get(0));
    componentsButtons.get(0).click();

    return this;
  }

  public InfoboxBuilderPage addTitleComponent() {
    wait.forElementVisible(componentsButtons.get(1));
    componentsButtons.get(1).click();

    return this;
  }

  public InfoboxBuilderPage addImageComponent() {
    wait.forElementVisible(componentsButtons.get(2));
    componentsButtons.get(2).click();

    return this;
  }

  public InfoboxBuilderPage deleteTitleComponentWithIndex(int index) {
    wait.forElementVisible(titles.get(index));
    titles.get(index).click();
    wait.forElementVisible(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteRowComponentWithIndex(int index) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();
    wait.forElementVisible(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteImageComponentWithIndex(int index) {
    wait.forElementVisible(images.get(index));
    images.get(index).click();
    wait.forElementVisible(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilderPage verifyScrollbarIsVisible() {
    Assertion.assertEquals(previewArea.getCssValue("overflow"), "auto");

    return this;
  }

  public InfoboxBuilderPage verifyInfoboxPreviewBackgroundColor(String invocationBgColor) {
    String previewBackgroundColor = getBackgroundColor();
    Assertion.assertEquals(invocationBgColor, previewBackgroundColor);

    return this;
  }

  public InfoboxBuilderPage verifyBackArrowFunctionality() {
    wait.forElementVisible(backArrowButton);
    backArrowButton.click();
    Assertion.assertTrue(componentsButtons.get(0).isDisplayed());

    return this;
  }

  public InfoboxBuilderPage verifyHelpDialog() {
    wait.forElementVisible(questionMarkButton);
    questionMarkButton.click();
    Assertion.assertTrue(helpDialog.isDisplayed());

    return this;
  }

  public InfoboxBuilderPage verifyTooltipOnHover() {
    wait.forElementVisible(component.get(0));
    builder.moveToElement(component.get(0)).perform();
    Assertion.assertTrue(tooltip.isDisplayed());

    return this;
  }

  /* Verifies default rendered template structure, which should contain:
     1 title component, 1 image component and 2 row components
  */
  public InfoboxBuilderPage verifyDefaultTemplateStructure() {
    Assertion.assertEquals(this.titles.size(), 1);
    Assertion.assertEquals(this.images.size(), 1);
    Assertion.assertEquals(this.rows.size(), 2);

    return this;
  }

  public InfoboxBuilderPage verifySelectedComponentBorderStyle(int index) {
    wait.forElementVisible(component.get(index));
    component.get(index).click();
    String script = "return window.getComputedStyle("
                    + "document.querySelector('.active'),':before').getPropertyValue('Border')";
    JavascriptExecutor js = (JavascriptExecutor)driver;
    String borderValues = (String) js.executeScript(script);
    Assertion.assertEquals(borderValues, "1px solid rgb(26, 94, 184)");

    return this;
  }

  public InfoboxBuilderPage setTitleToUseArticleName(int index) {
    titles.get(index).click();
    wait.forElementVisible(titleCheckbox);
    if (!titleCheckbox.isSelected()) {
      titleCheckbox.click();
    }

    return this;
  }

  public InfoboxBuilderPage setAndVerifyRowLabelWithIndex(int index, String labelName) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();
    wait.forElementVisible(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilderPage setLongLabelNameAndVerifyBreakLine(
      int index, String labelName) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();
    wait.forElementVisible(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getCssValue("word-wrap"), "break-word");

    return this;
  }

  public InfoboxBuilderPage scrollAndSelectLastComponent() {
    scrollAndClick(component, component.size() - 1);

    return this;
  }

  public InfoboxBuilderPage dragAndDropToTheTop(int index) {
    String componentToBeMovedText = component.get(index).getText();
    Point location = component.get(component.size() - 1).getLocation();
    Dimension size = component.get(component.size() - 1).getSize();
    Integer targetY = location.getY() + size.getHeight();
    new Actions(driver)
        .clickAndHold(component.get(index))
        .moveByOffset(0, targetY)
        .release(component.get(index))
        .perform();
    wait.forElementClickable(component.get(component.size() - 1));
    component.get(component.size() - 1).click();
    Assertion.assertEquals(componentToBeMovedText, component.get(0).getText());

    return this;
  }

  public TemplatePage save() {
    wait.forElementClickable(saveButton);
    saveButton.click();
    //wait until template page is loaded
    wait.forElementVisible(driver.findElement(By.className("header-title")));

    return new TemplatePage();
  }

}
