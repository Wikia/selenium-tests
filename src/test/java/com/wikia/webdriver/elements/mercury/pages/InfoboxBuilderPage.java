package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.oasis.pages.TemplateEditPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InfoboxBuilderPage extends SpecialPageObject {

  @FindBy(css = ".InfoboxBuilder")
  private WebElement builderIFrame;

  @FindBy(css = ".pop-over-container .infobox-builder-sidebar-header-icon-delete")
  private WebElement deletePopUp;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement previewArea;

  @FindBy(css = ".on-hover-tooltip")
  private WebElement tooltip;

  @FindBy(css = ".infobox-builder-preview")
  private WebElement builderBackground;

  @FindBy(css = ".infobox-builder-go-to-source-modal")
  private WebElement goToSourceModalBackground;

  @FindBy(css = ".pop-over .orient-below")
  private WebElement sectionTooltipOrientedBelow;

  @FindBy(css = ".pop-over .orient-above")
  private WebElement sectionTooltipOrientedAbove;

  @FindBy(css = ".infobox-builder-go-to-source-modal > div")
  private WebElement modalGoToSource;

  @FindBy(css = ".infobox-builder-go-to-source-modal .modal-dialog div.modal-bottom-row > button:nth-child(1)")
  private WebElement saveChangesButton;

  @FindBy(css = ".infobox-builder-go-to-source-modal .modal-dialog div.modal-bottom-row > button:nth-child(2)")
  private WebElement dropChangesButton;

  @FindBy(css = ".text-field-error-message")
  private WebElement errorMessage;

  @FindBy(css = ".infobox-builder-edit-title-modal > div")
  private WebElement modalEditTitle;

  @FindBy(css = ".infobox-builder-edit-title-modal .modal-dialog div.modal-bottom-row > button:nth-child(1)")
  private WebElement publishEditedTitleButton;

  @FindBy(css = "#editTemplateTitle")
  private WebElement editTemplateTitleInput;

  @FindBy(css = ".portable-infobox .pi-data-label")
  private List<WebElement> rowLabels;

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

  public InfoboxBuilderPage openNew(String templateName) {
    new TemplateEditPage().open(templateName)
        .getTemplateClassification()
        .selectInfoboxTemplate()
        .clickAddButton();

    driver.switchTo().frame(builderIFrame);
    wait.forElementVisible(previewArea);

    return this;
  }

  public InfoboxBuilderPage openExisting(String templateName) {
    new TemplateEditPage().open(templateName)
        .openCurrectArticleSourceMode();
    driver.switchTo().frame(builderIFrame);

    return this;
  }

  public InfoboxBuilderPage open() {
    new TemplateEditPage().open("temp_template");
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(), URLsContent.SPECIAL_INFOBOX_BUILDER));
    driver.switchTo().frame(builderIFrame);
    wait.forElementVisible(previewArea);

    return this;
  }

  public boolean isGoToSourceModalPresent() {
    wait.forElementVisible(modalGoToSource);

    return modalGoToSource.isDisplayed();
  }

  public boolean isModalEditTitlePresent() {
    wait.forElementVisible(modalEditTitle);

    return modalEditTitle.isDisplayed();
  }

  public boolean isEditTemplateTitleInputPresent() {
    wait.forElementVisible(editTemplateTitleInput);

    return editTemplateTitleInput.isDisplayed();
  }

  public InfoboxBuilderPage insertTemplateTitle(String title) {
    wait.forElementClickable(editTemplateTitleInput);
    editTemplateTitleInput.sendKeys(title);

    return this;
  }

  public boolean isErrorMessagePresent() {
    wait.forElementVisible(errorMessage);

    return errorMessage.isDisplayed();
  }

  public boolean isInfoboxBuilderOpened() {
    wait.forElementClickable(builderBackground);

    return builderBackground.isDisplayed();
  }

  public boolean isInfoboxBuilderPresent() {
    return builderIFrame.isDisplayed();
  }

  public boolean isSectionTooltipPresentAbove(int index) {
    hoverOverSectionChevron(index);
    wait.forElementVisible(sectionTooltipOrientedAbove);

    return sectionTooltipOrientedAbove.isDisplayed();
  }

  public boolean isSectionTooltipPresentBelow(int index) {
    hoverOverSectionChevron(index);
    wait.forElementVisible(sectionTooltipOrientedBelow);

    return sectionTooltipOrientedBelow.isDisplayed();
  }

  public void clickDropChangesButton() {
    wait.forElementClickable(dropChangesButton);
    dropChangesButton.click();
  }

  public void clickSaveChangesButton() {
    wait.forElementClickable(saveChangesButton);
    saveChangesButton.click();
  }

  public void clickPublishEditedTitleButton() {
    wait.forElementClickable(publishEditedTitleButton);
    publishEditedTitleButton.click();
  }

  public InfoboxBuilderPage clickBuilderBackground() {
    wait.forElementClickable(builderBackground);
    builderBackground.click();

    return this;
  }

  public InfoboxBuilderPage clickGoToSourceModalBackground() {
    wait.forElementClickable(goToSourceModalBackground);
    goToSourceModalBackground.click();

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

  public String getLabelText(int index) {
    wait.forElementVisible(rowLabels.get(index));

    return rowLabels.get(index).getText();
  }

  public String getLabelCssValue(int index, String attribute) {
    wait.forElementVisible(rowLabels.get(index));

    return rowLabels.get(index).getCssValue(attribute);
  }

  public String getComponentBackgroundColor(int index) {
    wait.forElementVisible(component.get(index));

    return component.get(index).getCssValue("background-color");
  }

  public String getPreviewBackgroundColor() {
    wait.forElementVisible(previewArea);

    return previewArea.getCssValue("background-color");
  }

  public String getBorderStyle() {
    WebElement selectedComponent = component.get(0);
    wait.forElementClickable(selectedComponent);
    selectedComponent.click();

    String script = "return window.getComputedStyle("
                    + "document.querySelector('.active'),':before').getPropertyValue('Border')";

    return driver.executeScript(script).toString();
  }

  public int getInfoboxWidth() {
    return titles.get(0).getSize().getWidth();
  }

  public InfoboxBuilderPage selectTitleWithIndex(int index) {
    WebElement selectedTitle = titles.get(index);
    wait.forElementClickable(selectedTitle);
    scrollAndClick(selectedTitle);

    return this;
  }

  public InfoboxBuilderPage selectImageWithIndex(int index) {
    WebElement selectedImage = images.get(index);
    wait.forElementClickable(selectedImage);
    scrollAndClick(selectedImage);

    return this;
  }

  public InfoboxBuilderPage selectRowWithIndex(int index) {
    WebElement selectedRow = rows.get(index);
    wait.forElementClickable(selectedRow);
    scrollAndClick(selectedRow);

    return this;
  }

  public InfoboxBuilderPage selectHeaderWithIndex(int index) {
    WebElement selectedHeader = headers.get(index);
    wait.forElementClickable(selectedHeader);
    scrollAndClick(selectedHeader);

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

  public InfoboxBuilderPage hoverMouseOverComponent(int index){
    wait.forElementVisible(component.get(0));
    builder.moveToElement(component.get(0)).pause(500).perform();

    return this;
  }

  public boolean isTooltipVisible() {
    try{
      wait.forElementVisible(tooltip);
      return true;
    }catch (TimeoutException e){
      PageObjectLogging.logInfo("Tooltip not visible");
      return false;
    }
  }

  public InfoboxBuilderPage moveToLastComponent() {
    WebElement lastComponent = component.get(component.size() - 1);
    wait.forElementVisible(lastComponent);
    builder.moveToElement(lastComponent).perform();

    return this;
  }

  /**
   _   _                     __     _
   | | | |,^.        ,'.      | `.  | |
   | | |  ,. `.    ,'   `.    | . `.| |
   | | | .--`,'  ,'  ,^.  `.  | |`.   |
   | | | |`. `. `. ,'___`. ,' | |  `| |
   |_| |_|  `. `. `._____,'   |_|   | |
   `/                    `.|
   `
   __               _   _   __         ____  __     _
   | `. ,^.       ,' | | | |  `.      |  __| | `.  | |
   | . ' , |  /`,' . | | | | |`.`.    | |__  | . `.| |
   | |`.'| |  `. ,'| | | | | |  `.`.  |  __| | |`.   |
   | |   | | ,' . `. | | | | |____` / | |__  | |  `| |
   |_|   | | \,' `.__| |_| |_______/   `.__| |_|   | |
   `.|                                       `.|
   */
  public WebElement dragAndDropToTheTop(WebElement draggedElement) {
    this.wait.forElementClickable(draggedElement);

    Point location = driver.findElement(By.cssSelector(".portable-infobox")).getLocation();
    Integer targetY = draggedElement.getLocation().getY() - location.getY() + 10;

    new Actions(driver)
        .clickAndHold(draggedElement)
        .moveByOffset(0,targetY)
        .pause(500)
        .release(draggedElement)
        .pause(500)
        .perform();

    wait.forValueToBeNotPresentInElementsAttribute(draggedElement, "class", "is-dragging");
    wait.forValueToBeNotPresentInElementsAttribute(draggedElement, "class", "is-dropping");

    return component.get(0);
  }

  public WebElement getInfoboxComponent(int index) {
    return component.get(index);
  }

  public void hoverOverSectionChevron(int index) {
    wait.forElementVisible(sectionHeadersChevron.get(index));
    builder.moveToElement(sectionHeadersChevron.get(index)).perform();
  }

  public InfoboxBuilderPage waitUntilEditTitleModalIsClosed() {
    wait.forElementNotVisible(By.cssSelector(".infobox-builder-edit-title-modal > div"));

    return this;
  }
}
