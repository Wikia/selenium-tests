package com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

public class VisualEditorOptionsDialog extends VisualEditorDialog {

	private final int PAGESETTINGS = 0;
	private final int CATEGORIES = 1;
	private final int LANGUAGES = 2;

	@FindBy(css=".oo-ui-outlineWidget li")
	private List<WebElement> outlineMenuItems;
	@FindBy(css=".oo-ui-window-foot .oo-ui-labeledElement-label")
	private WebElement applyChangesButton;
	@FindBy(css=".ve-ui-mwCategoriesPage-defaultsort input")
	private WebElement optionsInput;
	@FindBy(css=".ve-ui-mwCategoryWidget input")
	private WebElement categoriesInput;
	@FindBy(css=".ve-ui-desktopContext")
	private WebElement desktopContext;
	@FindBy(css=".ve-ui-mwCategoryInputWidget-menu .oo-ui-optionWidget-selected")
	private WebElement selectedResult;

	private By labeledElementBy = By.cssSelector(".oo-ui-labeledElement-label");
	private By matchingResultBy = By.cssSelector(".oo-ui-optionWidget-selected span");

	public VisualEditorOptionsDialog(WebDriver driver) {
		super(driver);
	}

	public void selectPageSettings() {
		switchToIFrame();
		selectFromOutlineMenu(PAGESETTINGS);
		PageObjectLogging.log("selectPageSettings", "Page settings is selected", true);
		driver.switchTo().defaultContent();
	}

	public void selectCategories() {
		switchToIFrame();
		selectFromOutlineMenu(CATEGORIES);
		PageObjectLogging.log("selectCategories", "Categories is selected", true);
		driver.switchTo().defaultContent();
	}

	public void selectLanguages() {
		switchToIFrame();
		selectFromOutlineMenu(LANGUAGES);
		PageObjectLogging.log("selectLanguages", "Languages is selected", true);
		driver.switchTo().defaultContent();
	}

	private void selectFromOutlineMenu(int index) {
		WebElement menuItem = outlineMenuItems.get(index).findElement(labeledElementBy);
		waitForElementClickableByElement(menuItem);
		menuItem.click();
	}

	public VisualEditorPageObject clickApplyChangesButton() {
		switchToIFrame();
		waitForElementVisibleByElement(applyChangesButton);
		waitForElementClickableByElement(applyChangesButton);
		applyChangesButton.click();
		PageObjectLogging.log("clickApplyChangesButton", "Apply changes is clicked", true);
		driver.switchTo().defaultContent();
		return new VisualEditorPageObject(driver);
	}

	public void addCategory(String cat) {
		switchToIFrame();
		typeCategory(cat);
		clickLinkResult();
		driver.switchTo().defaultContent();
	}

	private void typeCategory(String cat) {
		waitForElementVisibleByElement(categoriesInput);
		categoriesInput.clear();
		categoriesInput.sendKeys(cat);
		PageObjectLogging.log("typeCategory", "Typed " + cat + " in the field", true, driver);
	}

	public void clickLinkResult() {
		waitForElementByElement(selectedResult);
		WebElement matchingResult = selectedResult.findElement(labeledElementBy);
		waitForElementByElement(matchingResult);
		waitForElementClickableByElement(matchingResult);
		matchingResult.click();
	}
}
