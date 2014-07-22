package com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	
	public void verifyPinTitleFieldIsDisplayed() {
		waitForElementByElement(pinNameField);
		PageObjectLogging.log("verifyPinTitleFieldIsDisplayed", "Pin name field is visible", true);
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

	public void typePinName(String pinName) {
		waitForElementByElement(pinNameField);
		pinNameField.sendKeys(pinName);
		PageObjectLogging.log("typePinName", pinName+" title for Pin is typed in", true);
	}
	
	public void typeAssociatedArticle(String associatedArticleName) {
		waitForElementByElement(associatedArticleField);
		associatedArticleField.sendKeys(associatedArticleName);
		PageObjectLogging.log("typePinName", associatedArticleName + " Associated article is typed in", true);
	}
	
	public void clickSugggestion(int opt) {
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
