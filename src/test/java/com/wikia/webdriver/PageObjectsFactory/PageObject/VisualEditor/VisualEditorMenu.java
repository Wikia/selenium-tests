package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
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

	@FindBy(css=".oo-ui-icon-bold-b")
	private WebElement boldButton;
	@FindBy(css=".oo-ui-icon-italic-i")
	private WebElement italicButton;
	@FindBy(css=".oo-ui-icon-link")
	private WebElement linkButton;
	@FindBy(css=".oo-ui-frame")
	protected WebElement linkIframe;
	@FindBy(css=".oo-ui-icon-code")
	private WebElement codeButton;
	@FindBy(css=".oo-ui-icon-clear")
	private WebElement clearButton;
	@FindBy(css=".oo-ui-icon-number-list")
	private WebElement numListButton;
	@FindBy(css=".oo-ui-icon-bullet-list")
	private WebElement bulletListButton;
	@FindBy(css=".oo-ui-menuToolGroup")
	private WebElement formattingDropDown;
	@FindBy(css=".ve-ui-toolbar-saveButton")
	private WebElement savePageButton;
	@FindBy(css=".oo-ui-listToolGroup")
	private WebElement moreOptionsWrapper;
	@FindBy(css=".oo-ui-menuToolGroup>div>span")
	private List<WebElement> formattingDropDownItem;
	@FindBy(css=".oo-ui-listToolGroup>.oo-ui-clippableElement-clippable>span")
	private List<WebElement> moreOptionsDrownDownItems;

	private By genericDropDownBy = By.cssSelector(".oo-ui-icon-down");
	private By strikeStyleBy = By.cssSelector(".oo-ui-tool-name-strikethrough");
	private By underlineStyleBy = By.cssSelector(".oo-ui-tool-name-underline");
	private By subscriptStyleBy = By.cssSelector(".oo-ui-tool-name-subscript");
	private By superscriptStyleBy = By.cssSelector(".oo-ui-tool-name-superscript");
	private By toolWrapper = By.cssSelector("a.oo-ui-tool");
	private By saveButtonDisabled = By.cssSelector(".oo-ui-toolbar-saveButton.ve-ui-widget-disabled");

	private void clickStyleFromMoreDropDown(By styleBy) {
		Actions actions = new Actions(driver);
		actions
			.click(moreOptionsWrapper.findElement(genericDropDownBy))
			.click(moreOptionsWrapper.findElement(styleBy))
			.build()
			.perform();
	}

	public void selectStyle(Style style) {

		switch (style) {
		case BOLD:
			boldButton.click();
			break;
		case ITALIC:
			italicButton.click();
			break;
		case STRIKETHROUGH:
			clickStyleFromMoreDropDown(strikeStyleBy);
			break;
		case SUBSCRIPT:
			clickStyleFromMoreDropDown(subscriptStyleBy);
			break;
		case SUPERSCRIPT:
			clickStyleFromMoreDropDown(superscriptStyleBy);
			break;
		case UNDERLINE:
			clickStyleFromMoreDropDown(underlineStyleBy);
			break;
		}
		PageObjectLogging.log("selectStyle", style.toString() + " selected", true);
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
		formattingDropDownItem.get(format.ordinal()).click();
	}

	public VisualEditorSaveChangesDialog clickPublishButton() {
		waitForElementNotPresent(saveButtonDisabled);
		waitForElementClickableByElement(savePageButton);
		savePageButton.click();
		return new VisualEditorSaveChangesDialog(driver);
	}
}
