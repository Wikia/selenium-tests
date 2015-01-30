package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Łukasz Nowak (Dyktus)
 */

public class AddPinComponentObject extends BasePageObject {

  public AddPinComponentObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "input[name=name]")
  private WebElement pinNameField;
  @FindBy(css = "#intMapArticleTitle")
  private WebElement associatedArticleField;
  @FindBy(css = "select[name=poi_category_id]")
  private WebElement pinCategorySelector;
  @FindBy(css = "textarea[name=description]")
  private WebElement descriptionField;
  @FindBy(css = ".article-image-wrapper")
  private WebElement associatedArticleImage;
  @FindBy(css = "button[data-event=save]")
  private WebElement saveButton;
  @FindBy(css = "button[data-event=close]")
  private WebElement cancelButton;
  @FindBy(css = "button[data-event=deletePOI]")
  private WebElement deleteButton;
  @FindBy(css = ".article-suggestions>li>a")
  private List<WebElement> suggestedOption;
  @FindBy(css = ".article-image-url")
  private WebElement articleImageUrl;
  @FindBy(css = ".error")
  WebElement errorField;

  public void clearPinName() {
    waitForElementByElement(pinNameField);
    pinNameField.clear();
    PageObjectLogging.log("clearPinName", "Pin name input was cleared", true);
  }

  public void clearAssociatedArticleField() {
    waitForElementByElement(associatedArticleField);
    associatedArticleField.clear();
    PageObjectLogging.log("clearAssociatedArticleField",
                          "Associated article field was cleared", true);
  }

  public InteractiveMapPageObject clickCancelButton() {
    waitForElementByElement(cancelButton);
    cancelButton.click();
    PageObjectLogging.log("clickCancelButton", "cancel button clicked", true, driver);
    driver.switchTo().defaultContent();
    return new InteractiveMapPageObject(driver);
  }

  public void clearPinDescription() {
    waitForElementByElement(descriptionField);
    descriptionField.clear();
    PageObjectLogging.log("clearPinName", "Description input was cleared", true);
  }

  public InteractiveMapPageObject clickSaveButton() {
    waitForElementByElement(saveButton);
    saveButton.click();
    PageObjectLogging.log("clickSaveButton", "Save button clicked", true, driver);
    driver.switchTo().defaultContent();
    return new InteractiveMapPageObject(driver);
  }

  public void clickSuggestion(int opt) {
    waitForElementVisibleByElement(suggestedOption.get(opt));
    WebElement suggestionSelected = suggestedOption.get(opt);
    suggestionSelected.click();
  }

  public InteractiveMapPageObject clickDeletePin() {
    waitForElementVisibleByElement(deleteButton);
    deleteButton.click();
    driver.switchTo().defaultContent();
    return new InteractiveMapPageObject(driver);
  }

  public String getAssociatedArticleImageSrc() {
    waitForElementByElement(associatedArticleImage);
    return articleImageUrl.getAttribute("src");
  }

  public void selectPinType() {
    waitForElementByElement(pinCategorySelector);
    Select pinCategorySelectorDropDown = new Select(pinCategorySelector);
    pinCategorySelectorDropDown.selectByIndex(1);
    PageObjectLogging.log("selectPinType", "Pin type was choosed", true, driver);
  }

  public void typePinName(String pinName) {
    waitForElementByElement(pinNameField);
    pinNameField.sendKeys(pinName);
    PageObjectLogging.log("typePinName", pinName + " title for Pin was typed in", true);
  }

  public void typePinDescription(String pinDescription) {
    waitForElementByElement(descriptionField);
    descriptionField.sendKeys(pinDescription);
    PageObjectLogging.log("typePinDescription",
                          pinDescription + "Pin description was typed in", true);
  }

  public void typeAssociatedArticle(String associatedArticleName) {
    waitForElementByElement(associatedArticleField);
    associatedArticleField.sendKeys(associatedArticleName);
    PageObjectLogging.log("typePinName",
                          associatedArticleName + " Associated article is typed in",
                          true);
  }

  public void verifyPinTitleFieldIsDisplayed() {
    waitForElementByElement(pinNameField);
    PageObjectLogging.log("verifyPinTitleFieldIsDisplayed", "Pin name field is visible", true);
  }

  public void verifyAssociatedArticleFieldIsDisplayed() {
    waitForElementByElement(associatedArticleField);
    PageObjectLogging.log("verifyAssociatedArticleFieldIsDisplayed",
                          "Associated article field is visible", true, driver);
  }

  public void verifyPinCategorySelectorIsDisplayed() {
    waitForElementByElement(pinCategorySelector);
    PageObjectLogging.log("verifyPinCategorySelector",
                          "Pin category selector is visible", true, driver);
  }

  public void verifyDescriptionFieldIsDisplayed() {
    waitForElementByElement(descriptionField);
    PageObjectLogging.log("verifyDescriptionFieldIsDisplayed",
                          "Description field is visible", true, driver);
  }

  public void verifyAssociatedArticleImagePlaceholderIsDisplayed() {
    waitForElementByElement(associatedArticleImage);
    PageObjectLogging.log("verifyAssociatedArticleImageIsDisplayed",
                          "Associated article image placeholder is visible", true, driver);
  }

  public void verifyErrorExists() {
    waitForElementByElement(errorField);
    Assertion.assertEquals(checkIfElementOnPage(errorField), true);
    PageObjectLogging.log("verifyErrorIsPresented", "Error message is visible", true, driver);
  }

  public void verifyErrorContent(String errorMessage) {
    waitForElementByElement(errorField);
    Assertion.assertEquals(errorMessage, errorField.getText());
  }

  public void verifyAssociatedImageIsVisible(String placeholderImageSrc) {
    waitForElementByElement(articleImageUrl);
    Assertion.assertNotEquals(placeholderImageSrc, getAssociatedArticleImageSrc());
  }

  public void verifyAssociatedArticlePlaceholder() {
    waitForElementByElement(associatedArticleField);
    Assertion.assertEquals(
        InteractiveMapsContent.ASSOCIATED_ARTICLE_PLACEHOLDER,
        associatedArticleField.getAttribute("placeholder"),
        "Associated article place holder is not correct"
    );
  }
}
