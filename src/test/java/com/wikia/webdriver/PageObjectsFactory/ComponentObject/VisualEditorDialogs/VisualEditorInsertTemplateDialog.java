package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class VisualEditorInsertTemplateDialog extends VisualEditorDialog {

	@FindBy(css = ".oo-ui-searchWidget-query input")
	private WebElement searchInput;
	@FindBy(css = ".oo-ui-searchWidget-query .oo-ui-clearableTextInputWidget-clearButton")
	private WebElement searchClearButton;
	@FindBy(css = ".ve-ui-wikiaTemplateSearchWidget-suggestions")
	private WebElement suggestedWidget;
	@FindBy(css = ".ve-ui-wikiaTemplateSearchWidget-suggestions ul li")
	private List<WebElement> suggestedTemplates;
	@FindBy(css = ".oo-ui-searchWidget-results:not(.ve-ui-wikiaTemplateSearchWidget-suggestions)")
	private WebElement resultWidget;
	@FindBy(css = ".oo-ui-searchWidget-results:not(.ve-ui-wikiaTemplateSearchWidget-suggestions) ul li")
	private List<WebElement> resultTemplates;

	private By labelBy = By.cssSelector(".oo-ui-labeledElement-label");
	private By suggestedTemplatesBy = By.cssSelector(".ve-ui-wikiaTemplateSearchWidget-suggestions ul li");

	public VisualEditorInsertTemplateDialog(WebDriver driver) {
		super(driver);
	}

	public void typeInSearchInput(String searchString) {
		switchToIFrame();
		waitForElementByElement(searchInput);
		searchInput.sendKeys(searchString);
		waitForValueToBePresentInElementsAttributeByElement(searchInput, "value", searchString);
		PageObjectLogging.log(
			"typeInSearchInput",
			"Typed '" + searchString + "' into the template search textfield",
			true
		);
		switchOutOfIFrame();
	}

	public void clearSearchInput() {
		switchToIFrame();
		waitForElementVisibleByElement(searchClearButton);
		searchClearButton.click();
		PageObjectLogging.log("clearSearchInput", "Cleared the template search input field", true);
		switchOutOfIFrame();
	}

	public VisualEditorEditTemplateDialog selectSuggestedTemplate(int index) {
		switchToIFrame();
		try {
			waitForElementVisibleByElement(suggestedWidget);
			WebElement selected = suggestedTemplates.get(index).findElement(labelBy);
			selected.click();
			PageObjectLogging.log(
				"selectSuggestedTemplate",
				"Suggested template selected: " + selected.getText(),
				true
			);
			return new VisualEditorEditTemplateDialog(driver);
		} finally {
			switchOutOfIFrame();
		}
	}

	public VisualEditorEditTemplateDialog selectResultTemplate(String searchString, int index) {
		typeInSearchInput(searchString);
		switchToIFrame();
		try {
			waitForElementVisibleByElement(resultWidget);
			WebElement selected = resultTemplates.get(index).findElement(labelBy);
			selected.click();
			PageObjectLogging.log(
				"selectResultTemplate",
				"Search result template selected: " + selected.getText(),
				true
			);
			return new VisualEditorEditTemplateDialog(driver);
		} finally {
			switchOutOfIFrame();
		}
	}

	public int getNumberOfResultTemplates() {
		switchToIFrame();
		try {
			if (resultTemplates.isEmpty()) {
				return 0;
			} else {
				return resultTemplates.size();
			}
		} finally {
			PageObjectLogging.log(
				"getNumberOfResultTemplates",
				"Number of result templates found: " + resultTemplates.size(),
				true
			);
			switchOutOfIFrame();
		}
	}

	public void verifyIsResultTemplate() {
		Assertion.assertTrue(getNumberOfResultTemplates() > 0, "No result template shown.");
		PageObjectLogging.log("verifyIsResultTemplate", "Result templates found", true);
	}

	public void verifyNoResultTemplate() {
		Assertion.assertTrue(getNumberOfResultTemplates() == 0, "There is result template shown.");
		PageObjectLogging.log("verifyNoResultTemplate", "No result templates found", true);
	}

	public void verifyIsSuggestedTemplate() {
		switchToIFrame();
		Assertion.assertTrue(checkIfElementOnPage(suggestedTemplatesBy), "No suggested template shown.");
		PageObjectLogging.log("verifyIsSuggestedTemplate", "Suggested templates found", true);
		switchOutOfIFrame();
	}
}
