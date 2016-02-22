package com.wikia.webdriver.pageobjectsfactory.pageobject.special.infoboxbuilder;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.elements.oasis.pages.TemplatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfoboxPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import com.wikia.webdriver.testcases.infoboxbuilder.InfoboxBuilderTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownshership: Content West-Wing
 */
public class SpecialInfoboxBuilderPageObject extends SpecialPageObject {

  @FindBy(css = ".InfoboxBuilder")
  private WebElement builderIFrame;
  @FindBy(css = ".portable-infobox")
  private WebElement infoboxLayout;
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
  @FindBy(css = ".portable-infobox .pi-item")
  private List<WebElement> component;

  public SpecialInfoboxBuilderPageObject(WebDriver driver) {
    super(driver);
  }

  public SpecialInfoboxBuilderPageObject open(String templateName) {
    String url = urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_INFOBOX_BUILDER + templateName;
    getUrl(url);
    return this;
  }

  public SpecialInfoboxBuilderPageObject switchToIFrame() {
    driver.switchTo().frame(builderIFrame);
    return this;
  }

  public String getBackgroundColor() {
    return infoboxLayout.getCssValue("background-color");
  }

  public SpecialInfoboxBuilderPageObject selectTitleWithIndex(int index) {
    wait.forElementVisible(titles.get(index));
    titles.get(index).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject selectImageWithIndex(int index) {
    wait.forElementVisible(images.get(index));
    images.get(index).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject selectRowWithIndex(int index) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject addRowComponent() {
    wait.forElementVisible(componentsButtons.get(0));
    componentsButtons.get(0).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject addTitleComponent() {
    wait.forElementVisible(componentsButtons.get(1));
    componentsButtons.get(1).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject addImageComponent() {
    wait.forElementVisible(componentsButtons.get(2));
    componentsButtons.get(2).click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject deleteTitleComponentWithIndex(List<WebElement> componentsList, int index) {
    wait.forElementVisible(componentsList.get(index));
    componentsList.get(index).click();
    wait.forElementVisible(deleteButton);
    deleteButton.click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyScrollbarIsVisible() {
    Assertion.assertEquals(previewArea.getCssValue("overflow"), "auto");
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyInfoboxPreviewBackgroundColor(String invocationBgcolor) {
    String previewBackgroundColor = getBackgroundColor();
    Assertion.assertEquals(invocationBgcolor, previewBackgroundColor);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyBackArrowFunctionality() {
    wait.forElementVisible(backArrowButton);
    backArrowButton.click();
    Assertion.assertTrue(componentsButtons.get(0).isDisplayed());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyHelpDialog() {
    wait.forElementVisible(questionMarkButton);
    questionMarkButton.click();
    Assertion.assertTrue(helpDialog.isDisplayed());
    return this;
  }

  /* Verifies default rendered template structure, which should contain:
     1 title component, 1 image component and 2 row components
  */
  public SpecialInfoboxBuilderPageObject verifyDefaultTemplateStructure() {
    Assertion.assertEquals(this.titles.size(), 1);
    Assertion.assertEquals(this.images.size(), 1);
    Assertion.assertEquals(this.rows.size(), 2);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyRowAdded() {
    int dataLabelsCount = rows.size();
    Assertion.assertEquals(dataLabelsCount + 1, this.addRowComponent().rows.size());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyTitleAdded() {
    int titlesCount = titles.size();
    Assertion.assertEquals(titlesCount + 1, this.addTitleComponent().titles.size());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyImageAdded() {
    int imageCount = images.size();
    Assertion.assertEquals(imageCount + 1, this.addImageComponent().images.size());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyDeletingTitleWithIndex(int index) {
    int titlesCount = titles.size();
    this.deleteTitleComponentWithIndex(titles, index);
    Assertion.assertEquals(titlesCount, titles.size() + 1);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyDeletingRowWithIndex(int index) {
    int rowsCount = rows.size();
    this.deleteTitleComponentWithIndex(rows, index);
    Assertion.assertEquals(rowsCount, rows.size() + 1);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyDeletingImageWithIndex(int index) {
    int imagesCount = images.size();
    this.deleteTitleComponentWithIndex(images, index);
    Assertion.assertEquals(imagesCount, images.size() + 1);
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyCreatedTemplateName(
      String builderTemplate, TemplatePage createdTemplatePage) {
    Assertion.assertEquals(builderTemplate.toLowerCase(),
                           createdTemplatePage.getHeaderText().toLowerCase());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifyTitleUsingArticleName(
      PortableInfoboxPageObject infobox, String templateName) {
    Assertion.assertEquals(infobox.getTitleTextWithIndex(0).toLowerCase(),
                           templateName.toLowerCase());
    return this;
  }

  public SpecialInfoboxBuilderPageObject verifySelectedComponentBorderStyle(int index) {
    wait.forElementVisible(component.get(index));
    component.get(index).click();
    String script = "return window.getComputedStyle("
                    + "document.querySelector('.active'),':after').getPropertyValue('Border')";
    JavascriptExecutor js = (JavascriptExecutor)driver;
    String borderValues = (String) js.executeScript(script);
    Assertion.assertEquals(borderValues, "2px solid rgb(26, 94, 184)");
    return this;
  }

  public SpecialInfoboxBuilderPageObject setTitleToUseArticleName(int index) {
    titles.get(index).click();
    wait.forElementVisible(titleCheckbox);
    titleCheckbox.click();
    return this;
  }

  public SpecialInfoboxBuilderPageObject setAndVerifyRowLabelWithIndex(int index, String labelName) {
    wait.forElementVisible(rows.get(index));
    rows.get(index).click();
    wait.forElementVisible(rowLabelInputField);
    rowLabelInputField.click();
    rowLabelInputField.clear();
    rowLabelInputField.sendKeys(labelName);
    Assertion.assertEquals(rowLabels.get(index).getText(), labelName);
    return this;
  }

  public SpecialInfoboxBuilderPageObject scrollAndSelectLastComponent() {
    scrollAndClick(component, component.size() - 1);
    return this;
  }

  public TemplatePage save() {
    wait.forElementClickable(saveButton);
    saveButton.click();
    //wait until template page is loaded
    wait.forElementVisible(driver.findElement(By.className("header-title")));
    return new TemplatePage(driver);
  }

}
