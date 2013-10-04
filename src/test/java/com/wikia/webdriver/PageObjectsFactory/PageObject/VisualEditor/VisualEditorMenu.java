/**
 *
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VisualEditorMenu extends WikiBasePageObject {

	public VisualEditorMenu(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="")
	private WebElement undoButton;
	@FindBy(css="")
	private WebElement redoButton;
	@FindBy(css="")
	private WebElement paragraphDropdown;
	@FindBy(css=".ve-ui-icon-bold-b")
	private WebElement boldButton;
	@FindBy(css=".ve-ui-icon-italic-i")
	private WebElement italicButton;
	@FindBy(css=".ve-ui-icon-link")
	private WebElement linkButton;
	@FindBy(css=".ve-ui-frame")
	private WebElement linkIframe;
	@FindBy(css=".ve-ui-icon-code")
	private WebElement codeButton;
	@FindBy(css=".ve-ui-icon-clear")
	private WebElement clearButton;
	@FindBy(css=".ve-ui-icon-number-list")
	private WebElement numListButton;
	@FindBy(css=".ve-ui-icon-bullet-list")
	private WebElement bulletListButton;
	@FindBy(css=".ve-ui-menuToolGroup .ve-ui-icon-down")
	private WebElement formattingDropDown;
	@FindBy(css=".ve-ui-toolbar-saveButton")
	private WebElement savePageButton;

	public void clickBoldButton() {
		boldButton.click();
		PageObjectLogging.log("clickBoldButton", "bold button clicked", true);
	}

	public void clickItalicButton() {
		italicButton.click();
		PageObjectLogging.log("clickItalicButton", "italic button clicked", true);
	}

	public void clickLinkButton() {
		linkButton.click();
		PageObjectLogging.log("clickLinkButton", "link button clicked", true);
	}

	public void clickCodeButton() {
		codeButton.click();
		PageObjectLogging.log("clickCodeButton", "code button clicked", true);
	}

	public void clickClearButton() {
		clearButton.click();
		PageObjectLogging.log("clickClearButton", "clear styles button clicked", true);
	}

	public void clickNumListButton() {
		numListButton.click();
		PageObjectLogging.log("clickNumListButton", "numered list button clicked", true);
	}

	public void clickBullListButton() {
		bulletListButton.click();
		PageObjectLogging.log("clickBullListButton", "bullet list button clicked", true);
	}

	public void selectFormatting(Formatting format) {
		formattingDropDown.click();
		List<WebElement> list = formattingDropDown
		.findElement(parentBy)
		.findElement(parentBy)
		.findElements(By.cssSelector("a.ve-ui-tool"));
		list.get(format.ordinal()).click();
	}

	public VisualEditorSaveChangesDialog savePage() {
		waitForElementNotPresent(".ve-ui-toolbar-saveButton.ve-ui-widget-disabled");
		waitForElementClickableByElement(savePageButton);
		savePageButton.click();
		return new VisualEditorSaveChangesDialog(driver);
	}
}
