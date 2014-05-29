package com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertList;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorNewTemplateDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorReferenceDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorReferenceListDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
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
	@FindBy(css=".ve-ui-toolbar-saveButton .oo-ui-labeledElement-label")
	private WebElement publishPageButton;
	@FindBy(css=".oo-ui-listToolGroup")
	private WebElement moreOptionsWrapper;
	@FindBy(css=".oo-ui-menuToolGroup>div>span")
	private List<WebElement> formattingDropDownItem;
	@FindBy(css=".ve-init-mw-viewPageTarget-toolbar")
	private WebElement veToolMenu;
	@FindBy(css=".oo-ui-listToolGroup")
	private List<WebElement> toolListDropDowns;
	@FindBy(css=".ve-ui-toolbar-saveButton.oo-ui-widget-enabled")
	private WebElement enabledPublishButton;

	private final int STYLELIST = 0;
	private final int INSERTLIST = 1;
	private final int HAMBURGERLIST = 2;
	private WebElement styleList = toolListDropDowns.get(STYLELIST);
	private WebElement insertList = toolListDropDowns.get(INSERTLIST);
	private WebElement hamburgerList = toolListDropDowns.get(HAMBURGERLIST);


	private By genericDropDownBy = By.cssSelector(".oo-ui-indicator-down");
	private By strikeStyleBy = By.cssSelector(".oo-ui-icon-strikethrough-s");
	private By underlineStyleBy = By.cssSelector(".oo-ui-icon-underline-u");
	private By subscriptStyleBy = By.cssSelector(".oo-ui-icon-subscript");
	private By superscriptStyleBy = By.cssSelector(".oo-ui-icon-superscript");
	private By toolWrapper = By.cssSelector("a.oo-ui-tool");
	private By publishButtonDisabled = By.cssSelector(".oo-ui-toolbar-saveButton.ve-ui-widget-disabled");
	private By mediaBy = By.cssSelector(".oo-ui-tool-name-wikiaMediaInsert");
	private By numberbedListBy = By.cssSelector(".oo-ui-icon-number-list");
	private By bulletListBy = By.cssSelector(".oo-ui-icon-bullet-list");
	private By templateBy = By.cssSelector(".oo-ui-icon-template");
	private By referenceBy = By.cssSelector(".oo-ui-icon-reference");
	private By referenceListBy = By.cssSelector(".oo-ui-icon-references");

	private void clickStyleFromStyleDropDown(By styleBy) {
		Actions actions = new Actions(driver);
		actions
			.click(styleList)
			.click(styleList.findElement(styleBy))
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
			clickStyleFromStyleDropDown(strikeStyleBy);
			break;
		case SUBSCRIPT:
			clickStyleFromStyleDropDown(subscriptStyleBy);
			break;
		case SUPERSCRIPT:
			clickStyleFromStyleDropDown(superscriptStyleBy);
			break;
		case UNDERLINE:
			clickStyleFromStyleDropDown(underlineStyleBy);
			break;
		}
		PageObjectLogging.log("selectStyle", style.toString() + " selected", true);
	}

	public VisualEditorDialog selectInsertToOpenDialog(InsertDialog insert) {
		switch (insert) {
		case MEDIA:
			clickInsertFromInsertDropDown(mediaBy);
			PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
			return new VisualEditorAddMediaDialog(driver);
		case REFERENCE:
			clickInsertFromInsertDropDown(referenceBy);
			PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
			return new VisualEditorReferenceDialog(driver);
		case REFERENCE_LIST:
			clickInsertFromInsertDropDown(referenceListBy);
			PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
			return new VisualEditorReferenceListDialog(driver);
		case TEMPLATE:
			clickInsertFromInsertDropDown(templateBy);
			PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
			return new VisualEditorNewTemplateDialog(driver);
		default:
			return null;
		}
	}

	public void insertList(InsertList insert) {
		switch (insert) {
		case BULLET_LIST:
			clickInsertFromInsertDropDown(bulletListBy);
			break;
		case NUMBERED_LIST:
			clickInsertFromInsertDropDown(bulletListBy);
			break;
		}
		PageObjectLogging.log("selectInsertToInsertList", insert.toString() + " selected", true);
	}

	private void clickInsertFromInsertDropDown(By insertBy) {
		Actions actions = new Actions(driver);
		actions
		.click(insertList)
		.click(insertList.findElement(insertBy))
		.build()
		.perform();
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
		waitForElementClickableByElement(formattingDropDown);
		formattingDropDown.click();
		formattingDropDownItem.get(format.ordinal()).click();
	}

	public VisualEditorSaveChangesDialog clickPublishButton() {
		waitForElementNotPresent(publishButtonDisabled);
		waitForElementClickableByElement(enabledPublishButton);
		publishPageButton.click();
		PageObjectLogging.log("clickPublishButton", "Publish button on the VE toolbar is clicked", true);
		return new VisualEditorSaveChangesDialog(driver);
	}

	public void verifyVEToolBarPresent() {
		waitForElementVisibleByElement(veMode);
		waitForElementVisibleByElement(veToolMenu);
		PageObjectLogging.log("verifyVEToolBar", "VE toolbar is displayed", true);
	}
}
