package com.wikia.webdriver.pageobjectsfactory.componentobject.toolbars;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;


/**
 * @author Michal 'justnpT' Nowierski
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class CustomizedToolbarComponentObject extends WikiBasePageObject{

	public CustomizedToolbarComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="div[class*='wikia-bar'] a.tools-customize[data-name='customize']")
	private WebElement customizeButton;
	@FindBy(css="div.msg")
	private WebElement pageWatchlistStatusMessage;
	@FindBy(css="div.search-box input.search")
	private WebElement findAToolField;
	@FindBy(css="#MyToolsRenameItem input.input-box")
	private WebElement renameItemDialogInput;
	@FindBy(css="#MyToolsRenameItem button.primary")
	private WebElement saveItemDialogInput;
	@FindBy(css="#MyToolsConfigurationWrapper footer button.primary")
	private WebElement saveButton;
	@FindBy(css="span.reset-defaults img")
	private WebElement resetDefaultsButton;
	@FindBy(css=".overflow-menu > .tools-menu li > a[href*=Special]")
	private List<WebElement> myToolsList;

	private By toolsList = By.cssSelector("ul.tools li");

	private String searchSuggestionToolCss = "div.autocomplete div[title=\"%s\"]";
	private String toolbarToolCss = "li.overflow a[data-name=\"%s\"]";
	private String toolsListToolCss = "ul.options-list li[data-caption=\"%s\"]";
	private String toolsListToolDeleteCss = " img.trash";
	private String toolsListToolEditCss = " img.edit-pencil";
	private String addedToolsPath = "//ul[@class='tools']//a[text() = '%s']";

	/**
	 * Verifies that user toolbar buttons are visible
	 */
	public void verifyUserToolBar() {
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
		jQueryClick(customizeButton);
		PageObjectLogging.log("clickCustomize", "customize button clicked", true);
	}


	/**
	 * Clicks on "ResetDefaults" button.
	 *
	 * @author Michal Nowierski
	 */
	public void clickResetDefaults() {
		waitForElementByElement(resetDefaultsButton);
		jQueryClick(resetDefaultsButton);
		PageObjectLogging.log("clickResetDefaults", "reset defaults button clicked", true);
	}

	/**
	 * Types GivenString to Find A Tool field
	 *
	 * @param toolName String to be typed into search field
	 * @author Michal Nowierski
	 */
	public void searchTool(String toolName) {
		waitForElementByElement(findAToolField);
		findAToolField.clear();
		findAToolField.sendKeys(toolName);
		PageObjectLogging.log("searchTool", toolName + " typed into search field", true);

	}


	/**
	 * Types GivenString to Find A Tool field
	 *
	 * @param toolNewName new name for the Tool
	 * @author Michal Nowierski
	 */
	public void typeNewName(String toolNewName) {
		renameItemDialogInput.clear();
		renameItemDialogInput.sendKeys(toolNewName);
		PageObjectLogging.log("typeNewName", toolNewName + " typed into edit name field", true);
	}

	/**
	 * Clicks on "save" button on Rename Item dialog.
	 *
	 * @author Michal Nowierski
	 */
	public void clickSaveNewName() {
		scrollAndClick(saveItemDialogInput);
		PageObjectLogging.log("clickSaveNewName", "save name button clicked", true);

	}

	/**
	 * Click on a Tool after searching for it
	 *
	 * @param toolName toolname appearing on the list of found tools
	 * @author Michal Nowierski
	 */
	public void clickSearchSuggestion(String toolName) {
		scrollAndClick(
				driver.findElement(
						By.cssSelector(String.format(searchSuggestionToolCss, toolName))
				)
		);
		PageObjectLogging.log("clickSearchSuggestion", toolName + " selected from search suggestions", true);
	}

	/**
	 * Click on a toolbar tool.
	 *
	 * @param data-name data-name of the toolbar tool. <br> You should check the data-name of the tool you want to click.
	 * @author Michal Nowierski
	 */
	public void clickOnTool(String toolName) {
		jQueryClick(
				waitForElementByCss(String.format(toolbarToolCss, toolName))
		);
		PageObjectLogging.log("clickOnTool", toolName + " clicked on customized toolbar", true);
	}

	/**
	 * Click on a toolbar tool.
	 *
	 * @param data-name data-name of the toolbar tool. <br> You should check the data-name of the tool you want to click.
	 * @author Michal Nowierski
	 */
	public void verifyFollowMessage() {
		waitForElementByElement(pageWatchlistStatusMessage);
		PageObjectLogging.log("verifyFollowMessage", "follow message verified", true);

	}

	/**
	 * Verify that page is followed
	 * The method should be used only after clicking on "follow" button. Before that, "follow" button does not have 'title' attribute which is necessary in the method
	 *
	 * @author Michal Nowierski
	 */
	public void verifyFollowedToolbar() {
		waitForValueToBePresentInElementsAttributeByCss(String.format(toolbarToolCss, "follow"), "title", "Unfollow");
		PageObjectLogging.log("verifyFollowedToolbar", "follow button verified", true);

	}

	/**
	 * Verify that page is unfollowed
	 * The method should be used only after clicking on "Unfollow" button. Before that, "follow" button does not have 'title' attribute which is necessary in the method
	 *
	 * @author Michal Nowierski
	 */
	public void verifyUnfollowed() {
		waitForElementByElement(pageWatchlistStatusMessage);
		waitForValueToBePresentInElementsAttributeByCss(String.format(toolbarToolCss, "follow"), "title", "Follow");
		PageObjectLogging.log("verifyUnfollowed", "unfollow button verified", true);

	}

	/**
	 * Look up if Tool appears on Toolbar List
	 *
	 * @param toolName {Follow, Edit, History, (...)}
	 * @author Michal Nowierski
	 */
	public void verifyToolOnList(String toolName) {
		waitForElementByCss(String.format(toolsListToolCss, toolName));
		PageObjectLogging.log("verifyToolOnList", toolName + " visible on the list", true);

	}

	public void verifyToolNotOnList(String toolName) {
		waitForElementNotPresent(String.format(toolsListToolCss, toolName));
		PageObjectLogging.log("verifyToolNotOnList", toolName + " not visible on the list", true);

	}

	/**
	 * Remove a wanted Tool by its data-caption
	 *
	 * @param toolName ID of tool to be removed. {Follow, Edit, History, (...)}
	 * @author Michal Nowierski
	 */
	public void clickRemove(String toolName) {
		waitForElementByCss(String.format(toolsListToolCss, toolName));
		jQueryClick(String.format(toolsListToolCss, toolName) + toolsListToolDeleteCss);
		PageObjectLogging.log("clickRemove", "remove button for " + toolName + " clicked", true);
	}

	/**
	 * Rename the wanted Tool
	 *
	 * @param toolName ID of tool to be removed. {PageAction:Follow, PageAction:Edit, PageAction:History, (...)}
	 * @author Michal Nowierski
	 */
	public void clickRename(String toolName) {
		waitForElementByCss(String.format(toolsListToolCss, toolName));
		jQueryClick(String.format(toolsListToolCss, toolName) + toolsListToolEditCss);
		PageObjectLogging.log("clickRename", "rename button for "+toolName+" clicked", true);
	}

	/**
	 * Click on save button on customize toolbar
	 *
	 * @author Michal Nowierski
	 */
	public void clickSave() {
		waitForElementByElement(saveButton);
		scrollAndClick(saveButton);
		PageObjectLogging.log("clickSave", "save button clicked", true);

	}

	public void verifyToolOnToolbar(String toolName) {
		waitForElementByXPath(String.format(addedToolsPath, toolName));
		PageObjectLogging.log("verifyToolOnToolbar","tool " + toolName + " visible on toolbar", true);
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
			if ("Following".equals(List.get(i).getText())) {
				clickOnTool("follow");
				verifyFollowMessage();
				waitForTextToBePresentInElementByBy(toolsList, "Follow");
				PageObjectLogging.log("unfollowIfFollowed",
						"page was followed, unfollow button clicked", true);
				break;
			}
		}
		PageObjectLogging.log("unfollowIfFollowed",
				"page was unfollowed", true);
	}

	public void verifyToolRemoved(String toolName) {
		waitForElementNotPresent(
				By.xpath(String.format(addedToolsPath, toolName))
		);
		PageObjectLogging.log("verifyToolRemoved",toolName + " removed from toolbar", true);
	}

	public void addManyItems(String name, Integer count){
		for (int i=0; i<count; i++){
			searchTool(name.substring(0, 2));
			clickSearchSuggestion(name);
			verifyToolOnList(name);
		}
	}

	public void openMoreMenu() {
		executeScript("$('.overflow-menu').mouseover();");
		waitForElementByCss(".overflow-menu > .tools-menu");
		PageObjectLogging.log("openMoreMenu","more menu opened", true);
	}

	public void verifyToolInMoreTool(String toolName) {
		for (WebElement elem : myToolsList) {
			Assertion.assertEquals(toolName.toLowerCase(), elem.getAttribute("data-name").toLowerCase());
		}
		PageObjectLogging.log("verifyToolInMoreTool",toolName + " appears in ToolbarMoreTool.", true);
	}
}