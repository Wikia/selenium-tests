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
	private By resultTemplatesBy = By.cssSelector(".oo-ui-searchWidget-results:not(.ve-ui-wikiaTemplateSearchWidget-suggestions) ul li");
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
		waitForElementVisibleByElement(suggestedWidget);
		WebElement selected = suggestedTemplates.get(index).findElement(labelBy);
		String templateName = selected.getText();
		selected.click();
		switchOutOfIFrame();
		PageObjectLogging.log("selectSuggestedTemplate", "Suggested template selected: " + templateName, true);
		return new VisualEditorEditTemplateDialog(driver);
	}

	public VisualEditorEditTemplateDialog selectResultTemplate(String searchString, int index) {
		typeInSearchInput(searchString);
		switchToIFrame();
		waitForElementVisibleByElement(resultWidget);
		WebElement selected = resultTemplates.get(index).findElement(labelBy);
		String templateName = selected.getText();
		selected.click();
		switchOutOfIFrame();
		PageObjectLogging.log("selectResultTemplate", "Search result template selected: " + templateName, true);
		return new VisualEditorEditTemplateDialog(driver);
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
