package com.wikia.webdriver.elements.oasis.pages;

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

  @FindBy(css = ".sub-head--done")
  private WebElement saveButton;

  @FindBy(css = ".infobox-builder-sidebar-header .infobox-builder-sidebar-header-icon-delete")
  private WebElement deleteButton;

  @FindBy(css = ".pop-over-container .infobox-builder-sidebar-header-icon-delete")
  private WebElement deletePopUp;

  @FindBy(css = "#useArticleName")
  private WebElement titleCheckbox;

  @FindBy(css = "#isCollapsible")
  private WebElement collapsibilityCheckbox;

  @FindBy(css = "#infoboxRowLabel")
  private WebElement rowLabelInputField;

  @FindBy(css = "#infoboxSectionHeader")
  private WebElement sectionHeaderInputField;

  @FindBy(css = ".infobox-builder-sidebar-header-icon-back")
  private WebElement backArrowButton;

  @FindBy(css = ".infobox-builder-sidebar .modal-dialog")
  private WebElement helpDialog;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement previewArea;

  @FindBy(css = ".on-hover-tooltip")
  private WebElement tooltip;

  @FindBy(css = ".infobox-builder-spinner")
  private WebElement savingSpinner;

  @FindBy(css = ".success-icon")
  private WebElement successIcon;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement builderBackground;

  @FindBy(css = "#go-to-source")
  private WebElement goToSourceButton;

  @FindBy(css = ".infobox-builder-go-to-source-modal")
  private WebElement goToSourceModal;

  @FindBy(css = ".modal-dialog div.modal-bottom-row > button:nth-child(1)")
  private WebElement saveChangesButton;

  @FindBy(css = ".modal-dialog div.modal-bottom-row > button:nth-child(2)")
  private WebElement dropChangesButton;

  @FindBy(css = ".pop-over .orient-below")
  private WebElement sectionTooltipOrientedBelow;

  @FindBy(css = ".pop-over .orient-above")
  private WebElement sectionTooltipOrientedAbove;

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

  @FindBy(css = ".portable-infobox .pi-header")
  private List<WebElement> headers;

  @FindBy(css = ".portable-infobox .sortable-item")
  private List<WebElement> component;

  @FindBy(css = ".infobox-builder-chevron-area")
  private List<WebElement> sectionHeadersChevron;

  public InfoboxBuilderPage() {
    super();
  }

  public InfoboxBuilderPage openNew(String templateName) {
    new TemplateEditPage().open(templateName)
        .getTemplateClassification()
        .selectTemplateType()
        .clickAddButton();

    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public InfoboxBuilderPage openExisting(String templateName) {
    new TemplateEditPage().open(templateName)
        .openCurrectArticleSourceMode();
    driver.switchTo().frame(builderIFrame);
    return this;
  }

  public boolean isInfoboxBuilderDisplayed() {
    return builderIFrame.isDisplayed();
  }

  public InfoboxBuilderPage clickBuilderBackground() {
    wait.forElementClickable(builderBackground);
    builderBackground.click();

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

  public int countHeaders() {
    return headers.size();
  }

  public String getBackgroundColor() {
    wait.forElementVisible(component.get(0));

    return component.get(0).getCssValue("background-color");
  }

  public String getBorderStyle() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement selectedComponent = component.get(0);
    wait.forElementClickable(selectedComponent);
    selectedComponent.click();

    String script = "return window.getComputedStyle("
                    + "document.querySelector('.active'),':before').getPropertyValue('Border')";

    return js.executeScript(script).toString();
  }

  public InfoboxBuilderPage selectTitleWithIndex(int index) {
    WebElement selectedTitle = titles.get(index);
    wait.forElementClickable(selectedTitle);
    selectedTitle.click();

    return this;
  }

  public InfoboxBuilderPage selectImageWithIndex(int index) {
    WebElement selectedImage = images.get(index);
    wait.forElementClickable(selectedImage);
    selectedImage.click();

    return this;
  }

  public InfoboxBuilderPage selectRowWithIndex(int index) {
    WebElement selectedRow = rows.get(index);
    wait.forElementClickable(selectedRow);
    selectedRow.click();

    return this;
  }

  public InfoboxBuilderPage selectHeaderWithIndex(int index) {
    WebElement selectedHeader = headers.get(index);
    wait.forElementClickable(selectedHeader);
    selectedHeader.click();

    return this;
  }

  public InfoboxBuilderPage addRowComponent() {
    WebElement rowComponentButton = componentsButtons.get(2);
    wait.forElementClickable(rowComponentButton);
    rowComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addImageComponent() {
    WebElement imageComponentButton = componentsButtons.get(1);
    wait.forElementClickable(imageComponentButton);
    imageComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addTitleComponent() {
    WebElement titleComponentButton = componentsButtons.get(0);
    wait.forElementClickable(titleComponentButton);
    titleComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage addHeaderComponent() {
    WebElement headerComponentButton = componentsButtons.get(3);
    wait.forElementClickable(headerComponentButton);
    headerComponentButton.click();

    return this;
  }

  public InfoboxBuilderPage deleteTitleUsingButton(int index) {
    deleteItem(titles.get(index), deleteButton);
    return this;
  }

  public InfoboxBuilderPage deleteRowUsingButton(int index) {
    deleteItem(rows.get(index), deleteButton);
    return this;
  }

  public InfoboxBuilderPage deleteImageUsingButton(int index) {
    deleteItem(images.get(index), deleteButton);
    return this;
  }

  public InfoboxBuilderPage deleteHeaderUsingButton(int index) {
    deleteItem(headers.get(index), deleteButton);
    return this;
  }

  public InfoboxBuilderPage deleteTitleUsingPopUp(int index) {
    deleteItem(titles.get(index), deletePopUp);
    return this;
  }

  public InfoboxBuilderPage deleteRowUsingPopUp(int index) {
    deleteItem(rows.get(index), deletePopUp);
    return this;
  }

  public InfoboxBuilderPage deleteImageUsingPopUp(int index) {
    deleteItem(images.get(index), deletePopUp);
    return this;
  }

  public InfoboxBuilderPage deleteHeaderUsingPopUp(int index) {
    deleteItem(headers.get(index), deletePopUp);
    return this;
  }

  private void deleteItem(WebElement item, WebElement deleteMethod) {
    wait.forElementClickable(item);
    item.click();

    wait.forElementClickable(deleteMethod);
    deleteMethod.click();
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
    wait.forElementClickable(backArrowButton);
    backArrowButton.click();
    Assertion.assertTrue(componentsButtons.get(0).isDisplayed());

    return this;
  }

  public InfoboxBuilderPage verifyTooltipOnHover() {
    wait.forElementVisible(component.get(0));
    builder.moveToElement(component.get(0)).perform();
    Assertion.assertTrue(tooltip.isDisplayed());

    return this;
  }

  public InfoboxBuilderPage changeHeaderCollapsibilityState(int index) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    headers.get(index).click();

    wait.forElementClickable(collapsibilityCheckbox);
    collapsibilityCheckbox.click();

    String script = "return window.getComputedStyle(document"
                    + ".querySelector('.pi-header'),':after').content";
    String chevronContent = js.executeScript(script).toString();

    if (collapsibilityCheckbox.isSelected()) {
      Assertion.assertFalse(chevronContent.isEmpty());
    } else {
      Assertion.assertTrue(chevronContent.isEmpty());
    }

    return this;
  }

  public boolean isTitleUsingArticleName(int titleIndex) {
    this.selectTitleWithIndex(titleIndex);
    wait.forElementClickable(titleCheckbox);
    return titleCheckbox.isSelected();
  }

  public InfoboxBuilderPage setTitleToUseArticleName(int index) {
    WebElement selectedTitle = titles.get(index);
    wait.forElementClickable(selectedTitle);
    selectedTitle.click();
    wait.forElementClickable(titleCheckbox);

    if (!titleCheckbox.isSelected()) {
      titleCheckbox.click();
    }

    return this;
  }

  public InfoboxBuilderPage setAndVerifyRowLabel(int index, String labelName) {
    WebElement selectedRow = rows.get(index);
    wait.forElementClickable(selectedRow);
    selectedRow.click();

    wait.forElementClickable(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilderPage setLongLabelNameAndVerifyBreakLine(int index, String labelName) {
    WebElement selectedRow = rows.get(index);
    wait.forElementClickable(selectedRow);
    selectedRow.click();

    wait.forElementClickable(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getCssValue("word-wrap"), "break-word");

    return this;
  }

  public InfoboxBuilderPage setAndVerifyHeaderName(int index, String labelName) {
    WebElement selectedHeader = headers.get(index);
    wait.forElementClickable(selectedHeader);
    selectedHeader.click();

    wait.forElementClickable(sectionHeaderInputField);
    sectionHeaderInputField.click();
    sectionHeaderInputField.clear();
    sectionHeaderInputField.sendKeys(labelName);
    Assertion.assertEquals(headers.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilderPage moveToLastComponent() {
    WebElement lastComponent = component.get(component.size() - 1);
    wait.forElementVisible(lastComponent);
    builder.moveToElement(lastComponent).perform();

    return this;
  }

  public InfoboxBuilderPage dragAndDropToTheTop(int index) {
    this.wait.forElementClickable(component.get(index));
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

  public InfoboxBuilderPage clickGoToSourceButton() {
    wait.forElementClickable(goToSourceButton);
    goToSourceButton.click();
    return this;
  }

  public boolean isGoToSourceDialogPresent() {
    wait.forElementVisible(goToSourceModal);
    return goToSourceModal.isDisplayed();
  }

  public InfoboxBuilderPage clickGoToSourceModalBackground() {
    wait.forElementClickable(goToSourceModal);
    goToSourceModal.click();
    return this;
  }

  public TemplateEditPage clickDropChangesButton() {
    wait.forElementClickable(dropChangesButton);
    dropChangesButton.click();
    return new TemplateEditPage();
  }

  public TemplateEditPage clickSaveChangesButton() {
    wait.forElementClickable(saveChangesButton);
    saveChangesButton.click();
    return new TemplateEditPage();
  }

  public boolean isInfoboxBuilderOpened() {
    wait.forElementClickable(builderBackground);
    return builderBackground.isDisplayed();
  }

  public boolean isLabelInputFocused() {
    return rowLabelInputField.equals(driver.switchTo().activeElement());
  }

  public boolean isHeaderInputFocused() {
    return sectionHeaderInputField.equals(driver.switchTo().activeElement());
  }

  public int getInfoboxWidth() {
    return titles.get(0).getSize().getWidth();
  }

  public void hoverOverSectionChevron(int index) {
    wait.forElementVisible(sectionHeadersChevron.get(index));
    builder.moveToElement(sectionHeadersChevron.get(index)).perform();
  }

  public boolean isSectionTooltipDisplayedAbove(int index) {
    hoverOverSectionChevron(index);
    wait.forElementVisible(sectionTooltipOrientedAbove);

    return sectionTooltipOrientedAbove.isDisplayed();
  }

  public boolean isSectionTooltipDisplayedBelow(int index) {
    hoverOverSectionChevron(index);
    wait.forElementVisible(sectionTooltipOrientedBelow);

    return sectionTooltipOrientedBelow.isDisplayed();
  }

  public TemplatePage save() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    wait.forElementVisible(savingSpinner);
    Assertion.assertTrue(savingSpinner.isDisplayed());

    //wait until template page is loaded
    wait.forElementVisible(driver.findElement(By.className("header-title")));

    return new TemplatePage();
  }
}
