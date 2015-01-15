package com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor;

import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.*;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorMenu extends WikiBasePageObject {

	private static final int styleList = 0;
	private static final int insertList = 1;
	private static final int hamburgerList = 2;

	@FindBy(css = ".oo-ui-icon-bold-b")
	private WebElement boldButton;
	@FindBy(css = ".oo-ui-icon-italic-i")
	private WebElement italicButton;
	@FindBy(css = ".oo-ui-icon-link")
	private WebElement linkButton;
	@FindBy(css = ".oo-ui-frame")
	protected WebElement linkIframe;
	@FindBy(css = ".oo-ui-icon-code")
	private WebElement codeButton;
	@FindBy(css = ".oo-ui-icon-clear")
	private WebElement clearButton;
	@FindBy(css = ".oo-ui-icon-number-list")
	private WebElement numListButton;
	@FindBy(css = ".oo-ui-icon-bullet-list")
	private WebElement bulletListButton;
	@FindBy(css = ".oo-ui-menuToolGroup .oo-ui-indicator-down")
	private WebElement formattingDropDown;
	@FindBy(css = ".oo-ui-menuToolGroup")
	private WebElement formattingDropDownItems;
	@FindBy(css = ".oo-ui-listToolGroup .oo-ui-indicator-down")
	private List<WebElement> toolListDropDowns;
	@FindBy(css = ".oo-ui-listToolGroup")
	private List<WebElement> toolListItems;
	@FindBy(css = ".ve-ui-toolbar-saveButton.oo-ui-widget-enabled")
	private WebElement enabledPublishButton;

	private By strikeStyleBy = By.cssSelector(".oo-ui-icon-strikethrough-s");
	private By underlineStyleBy = By.cssSelector(".oo-ui-icon-underline-u");
	private By subscriptStyleBy = By.cssSelector(".oo-ui-icon-subscript");
	private By superscriptStyleBy = By.cssSelector(".oo-ui-icon-superscript");
	private By indentBy = By.cssSelector(".oo-ui-icon-indent-list");
	private By outdentBy = By.cssSelector(".oo-ui-icon-outdent-list");
	private By publishButtonDisabled = By.cssSelector(".oo-ui-toolbar-saveButton.ve-ui-widget-disabled");
	private By mapBy = By.cssSelector(".oo-ui-tool-name-wikiaMapInsert .oo-ui-tool-title");
	private By mediaBy = By.cssSelector(".oo-ui-tool-name-wikiaMediaInsert .oo-ui-tool-title");
	private By numberbedListBy = By.cssSelector(".oo-ui-icon-number-list");
	private By bulletListBy = By.cssSelector(".oo-ui-icon-bullet-list");
	private By templateBy = By.cssSelector(".oo-ui-icon-template");
	private By referenceBy = By.cssSelector(".oo-ui-icon-reference");
	private By referenceListBy = By.cssSelector(".oo-ui-icon-references");
	private By galleryBy = By.cssSelector(".oo-ui-icon-gallery");
	private By paragraphBy = By.cssSelector(".oo-ui-tool-name-paragraph");
	private By headingBy = By.cssSelector(".oo-ui-tool-name-heading2");
	private By subHeading1By = By.cssSelector(".oo-ui-tool-name-heading3");
	private By subHeading2By = By.cssSelector(".oo-ui-tool-name-heading4");
	private By subHeading3By = By.cssSelector(".oo-ui-tool-name-heading5");
	private By subHeading4By = By.cssSelector(".oo-ui-tool-name-heading6");
	private By preformatedBy = By.cssSelector(".oo-ui-tool-name-preformatted");
	private By menuItemBy = By.cssSelector(".oo-ui-tool-title");
	private By pageSettingsBy = By.cssSelector(".oo-ui-icon-settings");
	private By categoriesBy = By.cssSelector(".oo-ui-icon-tag");
	private By keyboardShortcutsBy = By.cssSelector(".oo-ui-icon-keyboard");
	private By sourceEditorBy = By.cssSelector(".oo-ui-icon-source");
	private By labelBy = By.cssSelector(".oo-ui-labeledElement-label");

	public VisualEditorMenu(WebDriver driver) {
		super(driver);
	}

	private void clickStyleItemFromDropDown(By styleBy) {
		WebElement styleList = toolListDropDowns.get(this.styleList);
		WebElement styleItems = toolListItems.get(this.styleList);
		waitForElementByElement(styleList);
		Actions actions = new Actions(driver);
		actions
			.click(styleList)
			.click(styleItems.findElement(styleBy))
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
				clickStyleItemFromDropDown(strikeStyleBy);
				break;
			case SUBSCRIPT:
				clickStyleItemFromDropDown(subscriptStyleBy);
				break;
			case SUPERSCRIPT:
				clickStyleItemFromDropDown(superscriptStyleBy);
				break;
			case UNDERLINE:
				clickStyleItemFromDropDown(underlineStyleBy);
				break;
			default:
				throw new NoSuchElementException("Non-existing style selected");
		}
		PageObjectLogging.log("selectStyle", style.toString() + " selected", true);
	}

	public void clickFormatting(By formatBy) {
		waitForElementClickableByElement(formattingDropDown);
		Actions actions = new Actions(driver);
		actions
			.click(formattingDropDown)
			.click(formattingDropDownItems.findElement(formatBy).findElement(menuItemBy))
			.build()
			.perform();
	}

	public void selectFormatting(Formatting format) {

		switch (format) {
			case PARAGRAPH:
				clickFormatting(paragraphBy);
				break;
			case HEADING:
				clickFormatting(headingBy);
				break;
			case SUBHEADING1:
				clickFormatting(subHeading1By);
				break;
			case SUBHEADING2:
				clickFormatting(subHeading2By);
				break;
			case SUBHEADING3:
				clickFormatting(subHeading3By);
				break;
			case SUBHEADING4:
				clickFormatting(subHeading4By);
				break;
			case PREFORMATTED:
				clickFormatting(preformatedBy);
				break;
			default:
				throw new NoSuchElementException("Non-existing format selected");
		}
	}

	public void selectIndentation(Indentation indent) {
		switch (indent) {
			case INCREASE:
				clickStyleItemFromDropDown(indentBy);
				break;
			case DECREASE:
				clickStyleItemFromDropDown(outdentBy);
				break;
			default:
				throw new NoSuchElementException("Non-existing indentation selected");
		}
	}

	public VisualEditorDialog openDialogFromMenu(InsertDialog insert) {
		switch (insert) {
			case MAP:
				clickInsertItemFromDropDown(mapBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorAddMapDialog(driver);
			case MEDIA:
				clickInsertItemFromDropDown(mediaBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorAddMediaDialog(driver);
			case REFERENCE:
				clickInsertItemFromDropDown(referenceBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorReferenceDialog(driver);
			case REFERENCE_LIST:
				clickInsertItemFromDropDown(referenceListBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorReferenceListDialog(driver);
			case TEMPLATE:
				clickInsertItemFromDropDown(templateBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorInsertTemplateDialog(driver);
			case PAGE_SETTINGS:
				clickHamburgerItemFromDropDown(pageSettingsBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorOptionsDialog(driver);
			case CATEGORIES:
				clickHamburgerItemFromDropDown(categoriesBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorOptionsDialog(driver);
			case KEYBOARD_SHORTCUTS:
				clickHamburgerItemFromDropDown(keyboardShortcutsBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorKeyboardShortcutsDialog(driver);
			case SOURCE_EDITOR:
				clickHamburgerItemFromDropDown(sourceEditorBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorSourceEditorDialog(driver);
			case GALLERY:
				clickInsertItemFromDropDown(galleryBy);
				PageObjectLogging.log("selectInsertToOpenDialog", insert.toString() + " selected", true);
				return new VisualEditorInsertGalleryDialog(driver);
			default:
				throw new NoSuchElementException("Non-existing dialog selected");
		}
	}

	public void insertList(InsertList insert) {
		switch (insert) {
			case BULLET_LIST:
				clickInsertItemFromDropDown(bulletListBy);
				break;
			case NUMBERED_LIST:
				clickInsertItemFromDropDown(numberbedListBy);
				break;
		}
		PageObjectLogging.log("selectInsertToInsertList", insert.toString() + " selected", true);
	}

	private void clickInsertItemFromDropDown(By insertBy) {
		WebElement insertList = toolListDropDowns.get(this.insertList);
		WebElement insertItems = toolListItems.get(this.insertList);
		waitForElementVisibleByElement(insertList);
		waitForElementClickableByElement(insertList);
		Actions actions = new Actions(driver);
		actions
			.click(insertList)
			.click(insertItems.findElement(insertBy))
			.build()
			.perform();
	}

	private void clickHamburgerItemFromDropDown(By insertBy) {
		WebElement hamburgerList = toolListDropDowns.get(this.hamburgerList);
		WebElement hamburgerItems = toolListItems.get(this.hamburgerList);
		waitForElementVisibleByElement(hamburgerList);
		waitForElementClickableByElement(hamburgerList);
		Actions actions = new Actions(driver);
		actions
			.click(hamburgerList)
			.click(hamburgerItems.findElement(insertBy))
			.build()
			.perform();
	}

	public VisualEditorHyperLinkDialog clickLinkButton() {
		linkButton.click();
		PageObjectLogging.log("clickLinkButton", "link button clicked", true);
		return new VisualEditorHyperLinkDialog(driver);
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

	public VisualEditorSaveChangesDialog clickPublishButton() {
		driver.switchTo().defaultContent();
		waitForElementNotPresent(publishButtonDisabled);
		waitForElementVisibleByElement(enabledPublishButton);
		WebElement publishButton = enabledPublishButton.findElement(labelBy);
		waitForElementClickableByElement(publishButton);
		publishButton.click();
		PageObjectLogging.log("clickPublishButton", "Publish button on the VE toolbar is clicked", true);
		return new VisualEditorSaveChangesDialog(driver);
	}

	public void verifyVEToolBarPresent() {
		waitForElementVisibleByElement(veMode);
		waitForElementVisibleByElement(veToolMenu);
		PageObjectLogging.log("verifyVEToolBar", "VE toolbar is displayed", true);
	}

	public void verifyVEToolBarNotPresent() {
		waitForElementNotVisibleByElement(veMode);
		waitForElementNotVisibleByElement(veToolMenu);
		PageObjectLogging.log("verifyVEToolBarNotPresent", "VE toolbar is not visible", true);
	}
}
