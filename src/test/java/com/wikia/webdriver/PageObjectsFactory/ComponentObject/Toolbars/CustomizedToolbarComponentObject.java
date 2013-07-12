package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Toolbars;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class CustomizedToolbarComponentObject extends BasePageObject{

	public CustomizedToolbarComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css="div[class*='wikia-bar'] a.tools-customize[data-name='customize']")
	protected WebElement customizeButton;
	@FindBy(css="div.msg")
	protected WebElement pageWatchlistStatusMessage;
	@FindBy(css="div.search-box input.search")
	protected WebElement findAToolField;
	@FindBy(css="div.MyToolsRenameItem input.input-box")
	protected WebElement renameItemDialogInput;
	@FindBy(css="div.MyToolsRenameItem input.save-button")
	protected WebElement saveItemDialogInput;
	@FindBy(css="input.save-button")
	protected WebElement saveButton;
	@FindBy(css="span.reset-defaults a")
	protected WebElement resetDefaultsButton;
	@FindBy(css="li.mytools.menu")
	protected WebElement myToolsMenuButton;
	@FindBy(css="ul[id='my-tools-menu']")
	protected WebElement myToolsMenu;


	private By toolsList = By.cssSelector("ul.tools li");

	/**
	 * Verifies that user toolbar buttons are visible
	 */
	public void verifyUserToolBar()
	{
		waitForElementByCss("div.toolbar ul.tools li.overflow");
		waitForElementByCss("div.toolbar ul.tools li.mytools");
		waitForElementByCss("div.toolbar ul.tools li a.tools-customize");
		PageObjectLogging.log("verifyUserToolBar", "user toolbar verified", true);
	}
	/**
	 * Clicks on "Customize" button. User must be logged in.
	 *
	 * @author Michal Nowierski
	 */
	public void clickCustomize() {
		waitForElementByElement(customizeButton);
		waitForElementClickableByElement(customizeButton);
		clickAndWait(customizeButton);
		PageObjectLogging.log("customizeToolbar_ClickCustomize", "Clicks on 'Customize' button.", true, driver);

	}


	/**
	 * Clicks on "ResetDefaults" button.
	 *
	 * @author Michal Nowierski
	 */
	public void clickResetDefaults() {
		waitForElementByElement(resetDefaultsButton);
		waitForElementClickableByElement(resetDefaultsButton);
		clickAndWait(resetDefaultsButton);
		PageObjectLogging.log("customizeToolbar_ClickOnResetDefaults", "Click on 'ResetDefaults' button.", true, driver);

	}

	/**
	 * Types GivenString to Find A Tool field
	 *
	 * @param GivenString String to be typed into search field
	 * @author Michal Nowierski
	 */
	public void searchTool(String GivenString) {
		waitForElementByElement(findAToolField);
		waitForElementClickableByElement(findAToolField);
		findAToolField.clear();
		findAToolField.sendKeys(GivenString);
		PageObjectLogging.log("customizeToolbar_TypeIntoFindATool", "Type "+GivenString+" into Find A Tool field", true, driver);

	}


	/**
	 * Types GivenString to Find A Tool field
	 *
	 * @param GivenString new name for the Tool
	 * @author Michal Nowierski
	 */
	public void typeNewName(String GivenString) {
		waitForElementByElement(renameItemDialogInput);
		waitForElementClickableByElement(renameItemDialogInput);
		renameItemDialogInput.clear();
		renameItemDialogInput.sendKeys(GivenString);
		PageObjectLogging.log("customizeToolbar_TypeIntoRenameItemDialog", "Type "+GivenString+" into rename item input", true, driver);
	}

	/**
	 * Clicks on "save" button on Rename Item dialog.
	 *
	 * @author Michal Nowierski
	 */
	public void clickSaveNewName() {
		waitForElementByElement(saveItemDialogInput);
		waitForElementClickableByElement(saveItemDialogInput);
		clickAndWait(saveItemDialogInput);
		PageObjectLogging.log("customizeToolbar_saveInRenameItemDialog", "Click on 'save' button on Rename Item dialog.", true, driver);

	}

	/**
	 * Click on a Tool after searching for it
	 *
	 * @param Tool toolname appearing on the list of found tools
	 * @author Michal Nowierski
	 */
	public void clickSearchSuggestion(String Tool) {
		waitForElementByCss("div.autocomplete div[title='"+Tool+"']");
		waitForElementClickableByCss("div.autocomplete div[title='"+Tool+"']");
		clickAndWait(driver.findElement(By.cssSelector("div.autocomplete div[title='"+Tool+"']")));
		PageObjectLogging.log("customizeToolbar_ClickOnFoundTool", "Click on "+Tool, true, driver);

	}

	/**
	 * Click on a toolbar tool.
	 *
	 * @param data-name data-name of the toolbar tool. <br> You should check the data-name of the tool you want to click.
	 * @author Michal Nowierski
	 */
	public void clickOnTool(String Tool_dataname) {
		waitForElementByCss("li.overflow a[data-name='"+Tool_dataname+"']");
		WebElement element = driver.findElement(By.cssSelector("li.overflow a[data-name='"+Tool_dataname+"']"));
		if (Global.BROWSER.equals("IE")) {
			// clicking on parent element of the above 'a' element, because IE couldn't click on the above 'a' element
			// Unfortunately Firefox can't click on this parent element, so the code must be browser-dependent
			WebElement parent = element.findElement(By.xpath(".."));
			waitForElementClickableByElement(parent);
			clickAndWait(parent);
		}
		else {
			waitForElementClickableByElement(element);
			clickAndWait(element);
		}
		PageObjectLogging.log("customizeToolbar_ClickOnFoundTool", "Click on "+Tool_dataname, true, driver);
	}

	/**
	 * Click on a toolbar tool.
	 *
	 * @param data-name data-name of the toolbar tool. <br> You should check the data-name of the tool you want to click.
	 * @author Michal Nowierski
	 */
	public void verifyFollowMessage() {
		waitForElementByElement(pageWatchlistStatusMessage);
		PageObjectLogging.log("customizeToolbar_VerifyPageWatchlistStatusMessage", "Verify that the page watchlist status message appeared ", true, driver);

	}

	/**
	 * Verify that page is followed
	 * The method should be used only after clicking on "follow" button. Before that, "follow" button does not have 'title' attribute which is necessary in the method
	 *
	 * @author Michal Nowierski
	 */
	public void verifyFollowedToolbar() {
		waitForElementByCss("a[data-name='follow']");
		waitForValueToBePresentInElementsAttributeByCss("a[data-name='follow']", "title", "Unfollow");
		PageObjectLogging.log("customizeToolbar_VerifyPageFollowed", "Verify that page is followed", true, driver);

	}

	/**
	 * Verify that page is unfollowed
	 * The method should be used only after clicking on "Unfollow" button. Before that, "follow" button does not have 'title' attribute which is necessary in the method
	 *
	 * @author Michal Nowierski
	 */
	public void verifyUnfollowed() {
		waitForElementByElement(pageWatchlistStatusMessage);
		waitForValueToBePresentInElementsAttributeByCss("a[data-name='follow']", "title", "Follow");
		PageObjectLogging.log("customizeToolbar_VerifyPageUnfollowed", "Verify that page is unfollowed", true, driver);

	}

	/**
	 * Look up if Tool appears on Toolbar List
	 *
	 * @param Tool {Follow, Edit, History, (...)}
	 * @author Michal Nowierski
	 */
	public void verifyToolOnList(String Tool) {
		waitForElementByCss("ul.options-list li[data-caption='"+Tool+"']");
		PageObjectLogging.log("customizeToolbar_VerifyToolOnToolbarList", "Check if "+Tool+" appears on list", true);

	}

	/**
	 * Remove a wanted Tool by its data-caption
	 *
	 * @param Tool ID of tool to be removed. {Follow, Edit, History, (...)}
	 * @author Michal Nowierski
	 */
	public void clickRemove(String Tool) {
		jQueryClick("ul.options-list li[data-caption=\""+Tool+"\"] img.trash");
		PageObjectLogging.log("customizeToolbar_ClickOnToolRemoveButton", "Remove Tool with id "+Tool+" from Toolbar List", true, driver);
	}

	/**
	 * Rename the wanted Tool
	 *
	 * @param ToolID ID of tool to be removed. {PageAction:Follow, PageAction:Edit, PageAction:History, (...)}
	 * @author Michal Nowierski
	 */
	public void clickRename(String ToolID) {
		By By1 = By.cssSelector("ul.options-list li[data-caption='"+ToolID+"']");
		waitForElementByBy(By1);
		jQueryClick("ul.options-list li[data-caption=\""+ToolID+"\"] img.edit-pencil");
		PageObjectLogging.log("customizeToolbar_ClickOnToolRenameButton", "Rename the "+ToolID+" Tool", true, driver);
	}

	/**
	 * Click on save button on customize toolbar
	 *
	 * @author Michal Nowierski
	 */
	public void clickSave() {
		waitForElementByElement(saveButton);
		waitForElementClickableByElement(saveButton);
		clickAndWait(saveButton);
		PageObjectLogging.log("customizeToolbar_ClickOnSaveButton", "Click on 'save' button.", true, driver);

	}

	public void verifyToolOnToolbar(String ToolName)
	{
		waitForElementByXPath("//ul[@class='tools']//a[contains(text(), '"+ToolName+"')]");
		PageObjectLogging.log("customizeToolbar_VerifyToolOnToolbar","Verify that "+ToolName+" appears in Toolbar.", true);
	}

	/**
	 * <p> Verify that wanted Tool appears in Toolbar. <br>
	 * The method finds all of Tools appearing in Toolbar (by their name), and checks if there is at least one name which fits the given param (ToolName)
	 *
	 * @param ToolName Tool to be verified (name that should appear on toolbar)
	 * @author Michal Nowierski
	 */
	public void unfollowIfFollowed() {
		List<WebElement> List = driver.findElements(toolsList);
		for (int i = 0; i < List.size(); i++) {
			if (List.get(i).getText().equals("Following")) {
				clickOnTool("follow");
				verifyFollowMessage();
				wait.until(ExpectedConditions.textToBePresentInElement(toolsList, "Follow"));

			}
		}
		PageObjectLogging.log("customizeToolbar_UnfollowIfPageIsFollowed",
				"If the page is Followed, unfollow it (preconditions assurance)", true, driver);

	}

	public void verifyToolRemoved(String ToolName)
	{
		By tool = By.xpath("//ul[@class='tools']//a[contains(text(), '"+ToolName+"')]");
		waitForElementNotVisibleByBy(tool);
		PageObjectLogging.log("customizeToolbar_VerifyToolNotOnToolbar","Verify that "+ToolName+" tool does not appear in Toolbar.", true);
	}
}