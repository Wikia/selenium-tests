package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import java.util.List;

import org.openqa.selenium.By;
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
 *
 */

public class AddPinComponentObject extends BasePageObject{

	public AddPinComponentObject(WebDriver driver) {
		super(driver);
	}
	
	//UI Mapping
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
	@FindBy(css = "#intMapArticleSuggestions")
	private WebElement suggestionsDropdown;
	@FindBy(css = ".article-suggestions>li>a")
	private List<WebElement> suggestedOption;
	@FindBy(css = ".article-image-url")
	private WebElement articleImageUrl;
	@FindBy(name = "lon")
	private WebElement hiddenLonInput;

	
	public void verifyPinTitleFieldIsDisplayed() {
		waitForElementByElement(pinNameField);
		PageObjectLogging.log("verifyPinTitleFieldIsDisplayed", "Pin name field is visible", true);
	}
	
	public boolean verifyPinTitleFieldIsFilled(){
		waitForElementByElement(pinNameField);
		return !pinNameField.equals("") ? true: false;
	}
	
	public void verifyAssociatedArticleFieldIsDisplayed() {
		waitForElementByElement(associatedArticleField);
		PageObjectLogging.log("verifyAssociatedArticleFieldIsDisplayed", "Associated article field is visible",  true, driver);
	}
	
	public void verifyPinCategorySelectorIsDisplayed() {
		waitForElementByElement(pinCategorySelector);
		PageObjectLogging.log("verifyPinCategorySelector", "Pin category selector is visible",  true, driver);
	}
	
	public void verifyDescriptionFieldIsDisplayed() {
		waitForElementByElement(descriptionField);
		PageObjectLogging.log("verifyPinCategorySelector", "Pin category selector is visible",  true, driver);
	}
	
	public void verifyAssociatedArticleImagePlaceholderIsDisplayed() {
		waitForElementByElement(associatedArticleImage);
		PageObjectLogging.log("verifyAssociatedArticleImageIsDisplayed", "Associated article image placeholder is visible",  true, driver);
	}

	public InteractiveMapPageObject clickCancelButton() {
		waitForElementByElement(cancelButton);
		cancelButton.click();
		PageObjectLogging.log("clickCancelButton", "cancel button clicked",  true, driver);
		return new InteractiveMapPageObject(driver);
	}
	public InteractiveMapPageObject clickSaveButton(){
		waitForElementByElement(hiddenLonInput);
		waitForElementByElement(saveButton);
		saveButton.click();
		PageObjectLogging.log("clickSaveButton","save button clicked", true, driver);
		return new InteractiveMapPageObject(driver);
	}

	public void typePinName(String pinName) {
		waitForElementByElement(pinNameField);
		pinNameField.sendKeys(pinName);
		PageObjectLogging.log("typePinName", pinName+" title for Pin was typed in", true);
	}
	
	public void typePinDescription(String pinDescription){
		waitForElementByElement(descriptionField);
		descriptionField.sendKeys(pinDescription);
		PageObjectLogging.log("typePinDescription","Pin description was typed in", true);
	}
	
	public void typeAssociatedArticle(String associatedArticleName) {
		waitForElementByElement(associatedArticleField);
		associatedArticleField.sendKeys(associatedArticleName);
		PageObjectLogging.log("typePinName", associatedArticleName + " Associated article is typed in", true);
	}
	
	public String selectPinCategorySelector(){
		Select pinCategorySelectorDropDown = new Select(pinCategorySelector);
		List<WebElement> pinCategorySelectorList = pinCategorySelectorDropDown.getOptions();
		waitForElementByElement(pinCategorySelectorList.get(pinCategorySelectorList.size()-1));
		pinCategorySelectorList.get(pinCategorySelectorList.size()-1).click();
		PageObjectLogging.log("typePinCategorySelector", "Pin Category choosed is "+pinCategorySelectorList.get(pinCategorySelectorList.size()-1), true);
		return pinCategorySelectorList.get(pinCategorySelectorList.size()-1).getText();
		//pinCategorySelectorList.size()-1 is the last one from selector list
	}
	
	
	
	public void clickSuggestion(int opt) {
		waitForElementVisibleByElement(suggestedOption.get(opt));
		WebElement suggestionSelected = suggestedOption.get(opt);
		suggestionSelected.click();
	}
	
	public String getAssociatedArticleImageSrc() {
		waitForElementByElement(associatedArticleImage);
		String imageSrc = articleImageUrl.getAttribute("src");
		System.out.println(imageSrc);
		return imageSrc;
	}
	
	public void verifyAssociatedImageIsVisible(String placeholderImageSrc) {
		waitForElementByElement(articleImageUrl);
		Assertion.assertNotEquals(placeholderImageSrc, getAssociatedArticleImageSrc());
	}

	public void verifyImage() {
		waitForElementByElement(articleImageUrl);
		Assertion.assertStringContains(articleImageUrl.getAttribute("src"), "Robert_Pattison");
	}
	
	
	
	
	
}
