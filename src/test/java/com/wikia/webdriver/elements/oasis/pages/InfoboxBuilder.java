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

/**
 * @ownshership: Content West-Wing
 */
public class InfoboxBuilder extends SpecialPageObject {

  @FindBy(css = ".InfoboxBuilder")
  private WebElement builderIFrame;
  @FindBy(css = ".sub-head .sub-head--done")
  private WebElement saveButton;
  @FindBy(css = ".edit-header--delete")
  private WebElement deleteButton;
  @FindBy(css = ".checkbox-title .ember-checkbox")
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

  public InfoboxBuilder() {
    super();
  }

  public InfoboxBuilder open(String templateName) {
    String url = urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_INFOBOX_BUILDER + templateName;
    getUrl(url);

    return this;
  }

  public InfoboxBuilder switchToIFrame() {
    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public String getBackgroundColor() {
    return component.get(0).getCssValue("background-color");
  }

  public InfoboxBuilder selectTitleWithIndex(int index) {
    wait.forElementVisible(titles.get(index));
    titles.get(index).click();

    return this;
  }

  public InfoboxBuilder selectImageWithIndex(int index) {
    wait.forElementVisible(images.get(index));
    images.get(index).click();

    return this;
  }

  public InfoboxBuilder selectRowWithIndex(int index) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();

    return this;
  }

  public InfoboxBuilder addRowComponent() {
    wait.forElementVisible(componentsButtons.get(0));
    componentsButtons.get(0).click();

    return this;
  }

  public InfoboxBuilder addTitleComponent() {
    wait.forElementVisible(componentsButtons.get(1));
    componentsButtons.get(1).click();

    return this;
  }

  public InfoboxBuilder addImageComponent() {
    wait.forElementVisible(componentsButtons.get(2));
    componentsButtons.get(2).click();

    return this;
  }

  public InfoboxBuilder deleteTitleComponentWithIndex(List<WebElement> componentsList, int index) {
    wait.forElementVisible(componentsList.get(index));
    componentsList.get(index).click();
    wait.forElementVisible(deleteButton);
    deleteButton.click();

    return this;
  }

  public InfoboxBuilder verifyScrollbarIsVisible() {
    Assertion.assertEquals(previewArea.getCssValue("overflow"), "auto");

    return this;
  }

  public InfoboxBuilder verifyInfoboxPreviewBackgroundColor(String invocationBgcolor) {
    String previewBackgroundColor = getBackgroundColor();
    Assertion.assertEquals(invocationBgcolor, previewBackgroundColor);

    return this;
  }

  public InfoboxBuilder verifyBackArrowFunctionality() {
    wait.forElementVisible(backArrowButton);
    backArrowButton.click();
    Assertion.assertTrue(componentsButtons.get(0).isDisplayed());

    return this;
  }

  public InfoboxBuilder verifyHelpDialog() {
    wait.forElementVisible(questionMarkButton);
    questionMarkButton.click();
    Assertion.assertTrue(helpDialog.isDisplayed());

    return this;
  }

  public InfoboxBuilder verifyTooltipOnHover() {
    wait.forElementVisible(component.get(0));
    builder.moveToElement(component.get(0)).perform();
    Assertion.assertTrue(tooltip.isDisplayed());

    return this;
  }

  /* Verifies default rendered template structure, which should contain:
     1 title component, 1 image component and 2 row components
  */
  public InfoboxBuilder verifyDefaultTemplateStructure() {
    Assertion.assertEquals(this.titles.size(), 1);
    Assertion.assertEquals(this.images.size(), 1);
    Assertion.assertEquals(this.rows.size(), 2);

    return this;
  }

  public InfoboxBuilder verifyRowAdded() {
    int dataLabelsCount = rows.size();
    Assertion.assertEquals(dataLabelsCount + 1, this.addRowComponent().rows.size());

    return this;
  }

  public InfoboxBuilder verifyTitleAdded() {
    int titlesCount = titles.size();
    Assertion.assertEquals(titlesCount + 1, this.addTitleComponent().titles.size());

    return this;
  }

  public InfoboxBuilder verifyImageAdded() {
    int imageCount = images.size();
    Assertion.assertEquals(imageCount + 1, this.addImageComponent().images.size());

    return this;
  }

  public InfoboxBuilder verifyDeletingTitleWithIndex(int index) {
    int titlesCount = titles.size();
    this.deleteTitleComponentWithIndex(titles, index);
    Assertion.assertEquals(titlesCount, titles.size() + 1);

    return this;
  }

  public InfoboxBuilder verifyDeletingRowWithIndex(int index) {
    int rowsCount = rows.size();
    this.deleteTitleComponentWithIndex(rows, index);
    Assertion.assertEquals(rowsCount, rows.size() + 1);

    return this;
  }

  public InfoboxBuilder verifyDeletingImageWithIndex(int index) {
    int imagesCount = images.size();
    this.deleteTitleComponentWithIndex(images, index);
    Assertion.assertEquals(imagesCount, images.size() + 1);

    return this;
  }

  public InfoboxBuilder verifyCreatedTemplateName(
      String builderTemplate, TemplatePage createdTemplatePage) {
    Assertion.assertEquals(builderTemplate.toLowerCase(),
                           createdTemplatePage.getHeaderText().toLowerCase());

    return this;
  }

  public InfoboxBuilder verifySelectedComponentBorderStyle(int index) {
    wait.forElementVisible(component.get(index));
    component.get(index).click();
    String script = "return window.getComputedStyle("
                    + "document.querySelector('.active'),':after').getPropertyValue('Border')";
    JavascriptExecutor js = (JavascriptExecutor)driver;
    String borderValues = (String) js.executeScript(script);
    Assertion.assertEquals(borderValues, "1px solid rgb(26, 94, 184)");

    return this;
  }

  public InfoboxBuilder setTitleToUseArticleName(int index) {
    titles.get(index).click();
    wait.forElementVisible(titleCheckbox);
    if (!titleCheckbox.isSelected()) {
      titleCheckbox.click();
    }

    return this;
  }

  public InfoboxBuilder setAndVerifyRowLabelWithIndex(int index, String labelName) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();
    wait.forElementVisible(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getText(), labelName);

    return this;
  }

  public InfoboxBuilder setLongLabelNameAndVerifyBreakLine(
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

  public InfoboxBuilder scrollAndSelectLastComponent() {
    scrollAndClick(component, component.size() - 1);

    return this;
  }

  public InfoboxBuilder dragAndDropToTheTop(int index) {
    String componentToBeMovedText = component.get(index).getText();
    Point location = component.get(component.size() - 1).getLocation();
    Dimension size = component.get(component.size() - 1).getSize();
    Integer targetY = location.getY() + size.getHeight();
    new Actions(driver)
        .clickAndHold(component.get(index))
        .moveByOffset(0, targetY)
        .release(component.get(index))
        .build()
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
