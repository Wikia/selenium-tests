package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps.InteractiveMapPageObject;

/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author Łukasz Nowak (Dyktus)
 *
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
	@FindBy(css = "#intMapArticleSuggestions")
	private WebElement suggestionsDropdown;
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
		PageObjectLogging.log("clearAssociatedArticleField", "Associated article field was cleared", true);
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
		return new InteractiveMapPageObject(driver);
	}

	public void clickSuggestion(int opt) {
		waitForElementVisibleByElement(suggestedOption.get(opt));
		WebElement suggestionSelected = suggestedOption.get(opt);
		suggestionSelected.click();
	}

	public InteractiveMapPageObject clickDeletePin() {
		waitForElementByElement(deleteButton);
		deleteButton.click();
		driver.switchTo().defaultContent();
		return new InteractiveMapPageObject(driver);
	}

	public String getAssociatedArticleImageSrc() {
		waitForElementByElement(associatedArticleImage);
		String imageSrc = articleImageUrl.getAttribute("src");
		return imageSrc;
	}

	public void selectPinType() {
		waitForElementByElement(pinCategorySelector);
		Select pinCategorySelectorDropDown = new Select(pinCategorySelector);
		List<WebElement> pinCategoryList = pinCategorySelectorDropDown.getOptions();
		pinCategoryList.get(1).click();
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
		PageObjectLogging.log("typePinDescription", "Pin description was typed in", true);
	}

	public void typeAssociatedArticle(String associatedArticleName) {
		waitForElementByElement(associatedArticleField);
		associatedArticleField.sendKeys(associatedArticleName);
		PageObjectLogging.log("typePinName", associatedArticleName + " Associated article is typed in", true);
	}

	public void verifyPinTitleFieldIsDisplayed() {
		waitForElementByElement(pinNameField);
		PageObjectLogging.log("verifyPinTitleFieldIsDisplayed", "Pin name field is visible", true);
	}

	public void verifyAssociatedArticleFieldIsDisplayed() {
		waitForElementByElement(associatedArticleField);
		PageObjectLogging.log("verifyAssociatedArticleFieldIsDisplayed", "Associated article field is visible", true, driver);
	}

	public void verifyPinCategorySelectorIsDisplayed() {
		waitForElementByElement(pinCategorySelector);
		PageObjectLogging.log("verifyPinCategorySelector", "Pin category selector is visible", true, driver);
	}

	public void verifyDescriptionFieldIsDisplayed() {
		waitForElementByElement(descriptionField);
		PageObjectLogging.log("verifyPinCategorySelector", "Pin category selector is visible", true, driver);
	}

	public void verifyAssociatedArticleImagePlaceholderIsDisplayed() {
		waitForElementByElement(associatedArticleImage);
		PageObjectLogging.log("verifyAssociatedArticleImageIsDisplayed", "Associated article image placeholder is visible", true, driver);
	}

	public void verifyErrorIsPresented() {
		waitForElementByElement(errorField);
		Assertion.assertEquals(checkIfElementOnPage(errorField), true);
	}

	public void verifyAssociatedImageIsVisible(String placeholderImageSrc) {
		waitForElementByElement(articleImageUrl);
		Assertion.assertNotEquals(placeholderImageSrc, getAssociatedArticleImageSrc());
	}

	public void verifyImage() {
		waitForElementByElement(articleImageUrl);
		Assertion.assertStringContains(articleImageUrl.getAttribute("src"), "Robert_Pattison");
	}

	public void verifyChangedCategory(String newCategory) {
		waitForElementByElement(pinCategorySelector);
		Select pinCategorySelectorDropDown = new Select(pinCategorySelector);
		List<WebElement> pinCategorySelectorList = pinCategorySelectorDropDown.getAllSelectedOptions();
		Assertion.assertEquals(newCategory, pinCategorySelectorList.get(0));
	}

}
