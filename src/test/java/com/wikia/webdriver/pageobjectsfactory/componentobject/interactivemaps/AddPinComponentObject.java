package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
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
  private WebElement errorField;

  public AddPinComponentObject(WebDriver driver) {
    super(driver);
  }

  public void clearPinName() {
    wait.forElementVisible(pinNameField);
    pinNameField.clear();
    LOG.log("clearPinName", "Pin name input was cleared", LOG.Type.SUCCESS);
  }

  public void clearAssociatedArticleField() {
    wait.forElementVisible(associatedArticleField);
    associatedArticleField.clear();
    LOG.logResult("clearAssociatedArticleField",
                  "Associated article field was cleared",
                  true);
  }

  public InteractiveMapPageObject clickCancelButton() {
    wait.forElementVisible(cancelButton);
    cancelButton.click();
    LOG.log("clickCancelButton", "cancel button clicked", true, driver);
    driver.switchTo().defaultContent();
    return new InteractiveMapPageObject(driver);
  }

  public void clearPinDescription() {
    wait.forElementVisible(descriptionField);
    descriptionField.clear();
    LOG.log("clearPinName", "Description input was cleared", LOG.Type.SUCCESS);
  }

  public InteractiveMapPageObject clickSaveButton() {
    wait.forElementVisible(saveButton);
    saveButton.click();
    LOG.log("clickSaveButton", "Save button clicked", true, driver);
    driver.switchTo().defaultContent();
    return new InteractiveMapPageObject(driver);
  }

  public void clickSuggestion(int opt) {
    wait.forElementVisible(suggestedOption.get(opt));
    WebElement suggestionSelected = suggestedOption.get(opt);
    suggestionSelected.click();
  }

  public InteractiveMapPageObject clickDeletePin() {
    wait.forElementVisible(deleteButton);
    deleteButton.click();
    driver.switchTo().defaultContent();
    return new InteractiveMapPageObject(driver);
  }

  public String getAssociatedArticleImageSrc() {
    wait.forElementVisible(associatedArticleImage);
    return articleImageUrl.getAttribute("src");
  }

  public void selectPinType() {
    wait.forElementVisible(pinCategorySelector);
    Select pinCategorySelectorDropDown = new Select(pinCategorySelector);
    pinCategorySelectorDropDown.selectByIndex(1);
    LOG.log("selectPinType", "Pin type was choosed", true, driver);
  }

  public void typePinName(String pinName) {
    wait.forElementVisible(pinNameField);
    pinNameField.sendKeys(pinName);
    LOG.log("typePinName", pinName + " title for Pin was typed in", LOG.Type.SUCCESS);
  }

  public void typePinDescription(String pinDescription) {
    wait.forElementVisible(descriptionField);
    descriptionField.sendKeys(pinDescription);
    LOG.logResult("typePinDescription",
                  pinDescription + "Pin description was typed in",
                  true);
  }

  public void typeAssociatedArticle(String associatedArticleName) {
    wait.forElementVisible(associatedArticleField);
    associatedArticleField.sendKeys(associatedArticleName);
    LOG.logResult("typePinName",
                  associatedArticleName + " Associated article is typed in",
                  true);
  }

  public void verifyPinTitleFieldIsDisplayed() {
    wait.forElementVisible(pinNameField);
    LOG.log("verifyPinTitleFieldIsDisplayed", "Pin name field is visible", LOG.Type.SUCCESS);
  }

  public void verifyAssociatedArticleFieldIsDisplayed() {
    wait.forElementVisible(associatedArticleField);
    LOG.log("verifyAssociatedArticleFieldIsDisplayed",
            "Associated article field is visible", true, driver);
  }

  public void verifyPinCategorySelectorIsDisplayed() {
    wait.forElementVisible(pinCategorySelector);
    LOG.log("verifyPinCategorySelector", "Pin category selector is visible", true,
            driver);
  }

  public void verifyDescriptionFieldIsDisplayed() {
    wait.forElementVisible(descriptionField);
    LOG.log("verifyDescriptionFieldIsDisplayed", "Description field is visible",
            true, driver);
  }

  public void verifyAssociatedArticleImagePlaceholderIsDisplayed() {
    wait.forElementVisible(associatedArticleImage);
    LOG.log("verifyAssociatedArticleImageIsDisplayed",
            "Associated article image placeholder is visible", true, driver);
  }

  public void verifyErrorExists() {
    wait.forElementVisible(errorField);
    Assertion.assertEquals(isElementOnPage(errorField), true);
    LOG.log("verifyErrorIsPresented", "Error message is visible", true, driver);
  }

  public void verifyErrorContent(String errorMessage) {
    wait.forElementVisible(errorField);
    Assertion.assertEquals(errorField.getText(), errorMessage);
  }

  public void verifyAssociatedImageIsVisible(String placeholderImageSrc) {
    wait.forElementVisible(articleImageUrl);
    Assertion.assertNotEquals(getAssociatedArticleImageSrc(), placeholderImageSrc);
  }

  public void verifyAssociatedArticlePlaceholder() {
    wait.forElementVisible(associatedArticleField);
    Assertion.assertEquals(InteractiveMapsContent.ASSOCIATED_ARTICLE_PLACEHOLDER,
                           associatedArticleField.getAttribute("placeholder"),
                           "Associated article place holder is not correct");
  }
}
