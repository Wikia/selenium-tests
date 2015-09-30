package com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps.InteractiveMapPageObject;

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
    LOG.success("clearPinName", "Pin name input was cleared");
  }

  public void clearAssociatedArticleField() {
    wait.forElementVisible(associatedArticleField);
    associatedArticleField.clear();
    LOG.result("clearAssociatedArticleField", "Associated article field was cleared", true);
  }

  public InteractiveMapPageObject clickCancelButton() {
    wait.forElementVisible(cancelButton);
    cancelButton.click();
    LOG.success("clickCancelButton", "cancel button clicked", true);
    driver.switchTo().defaultContent();
    return new InteractiveMapPageObject(driver);
  }

  public void clearPinDescription() {
    wait.forElementVisible(descriptionField);
    descriptionField.clear();
    LOG.success("clearPinName", "Description input was cleared");
  }

  public InteractiveMapPageObject clickSaveButton() {
    wait.forElementVisible(saveButton);
    saveButton.click();
    LOG.success("clickSaveButton", "Save button clicked", true);
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
    LOG.success("selectPinType", "Pin type was choosed", true);
  }

  public void typePinName(String pinName) {
    wait.forElementVisible(pinNameField);
    pinNameField.sendKeys(pinName);
    LOG.success("typePinName", pinName + " title for Pin was typed in");
  }

  public void typePinDescription(String pinDescription) {
    wait.forElementVisible(descriptionField);
    descriptionField.sendKeys(pinDescription);
    LOG.result("typePinDescription", pinDescription + "Pin description was typed in", true);
  }

  public void typeAssociatedArticle(String associatedArticleName) {
    wait.forElementVisible(associatedArticleField);
    associatedArticleField.sendKeys(associatedArticleName);
    LOG.result("typePinName", associatedArticleName + " Associated article is typed in", true);
  }

  public void verifyPinTitleFieldIsDisplayed() {
    wait.forElementVisible(pinNameField);
    LOG.success("verifyPinTitleFieldIsDisplayed", "Pin name field is visible");
  }

  public void verifyAssociatedArticleFieldIsDisplayed() {
    wait.forElementVisible(associatedArticleField);
    LOG.success("verifyAssociatedArticleFieldIsDisplayed", "Associated article field is visible",
                true);
  }

  public void verifyPinCategorySelectorIsDisplayed() {
    wait.forElementVisible(pinCategorySelector);
    LOG.success("verifyPinCategorySelector", "Pin category selector is visible",true);
  }

  public void verifyDescriptionFieldIsDisplayed() {
    wait.forElementVisible(descriptionField);
    LOG.success("verifyDescriptionFieldIsDisplayed", "Description field is visible",true);
  }

  public void verifyAssociatedArticleImagePlaceholderIsDisplayed() {
    wait.forElementVisible(associatedArticleImage);
    LOG.success("verifyAssociatedArticleImageIsDisplayed",
                "Associated article image placeholder is visible", true);
  }

  public void verifyErrorExists() {
    wait.forElementVisible(errorField);
    Assertion.assertEquals(isElementOnPage(errorField), true);
    LOG.success("verifyErrorIsPresented", "Error message is visible", true);
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
