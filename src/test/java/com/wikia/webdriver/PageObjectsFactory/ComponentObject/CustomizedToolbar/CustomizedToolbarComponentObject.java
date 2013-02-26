package com.wikia.webdriver.PageObjectsFactory.ComponentObject.CustomizedToolbar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class CustomizedToolbarComponentObject extends BasePageObject{
	
	public CustomizedToolbarComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css="div[class*='wikia-bar'] a.tools-customize[data-name='customize']")
	protected WebElement customizeToolbar_CustomizeButton;
	@FindBy(css="div.msg")
	protected WebElement customizeToolbar_PageWatchlistStatusMessage;
	@FindBy(css="div.search-box input.search")
	protected WebElement customizeToolbar_FindAToolField;
	@FindBy(css="div.MyToolsRenameItem input.input-box")
	protected WebElement customizeToolbar_RenameItemDialogInput;
	@FindBy(css="div.MyToolsRenameItem input.save-button")
	protected WebElement customizeToolbar_SaveItemDialogInput;
	@FindBy(css="input.save-button")
	protected WebElement customizeToolbar_SaveButton;
	@FindBy(css="span.reset-defaults a")
	protected WebElement customizeToolbar_ResetDefaultsButton;
	@FindBy(css="li.mytools.menu")
	protected WebElement customizeToolbar_MyToolsMenuButton;
	@FindBy(css="ul[id='my-tools-menu']")
	protected WebElement customizeToolbar_MyToolsMenu;
	
	
	private By customizeToolbar_ToolsList = By.cssSelector("ul.tools li");
	private By customizeToolbar_MyToolsList = By.cssSelector("ul[id='my-tools-menu'] a");
	
	public void showToolbar()
	{
		executeScript("$('div#WikiaBarWrapper').attr('class', 'WikiaBarWrapper')");
		executeScript("$('div#WikiaBarWrapper').attr('class', 'WikiaBarWrapper')");
	}
	
	/**
	 * Verifies that user toolbar buttons are visible
	 */
	public void verifyUserToolBar()
	{
		waitForElementByCss("div.toolbar ul.tools li.overflow");
		waitForElementByCss("div.toolbar ul.tools li.mytools");
		waitForElementByCss("div.toolbar ul.tools li a.tools-customize");
		PageObjectLogging.log("verifyUserToolBar", "user toolbar verified", true, driver);
	}
	/**
	 * Clicks on "Customize" button. User must be logged in.
	 * 
	 * @author Michal Nowierski
	 */
	public void clickCustomize() {
		waitForElementByElement(customizeToolbar_CustomizeButton);
		waitForElementClickableByElement(customizeToolbar_CustomizeButton);
		click(customizeToolbar_CustomizeButton);
		PageObjectLogging.log("customizeToolbar_ClickCustomize", "Clicks on 'Customize' button.", true, driver);
		
	}
	
	
	/**
	 * Clicks on "ResetDefaults" button.
	 * 	 
	 * @author Michal Nowierski
	 */
	public void clickResetDefaults() {
		waitForElementByElement(customizeToolbar_ResetDefaultsButton);
		waitForElementClickableByElement(customizeToolbar_ResetDefaultsButton);
		clickAndWait(customizeToolbar_ResetDefaultsButton);
		PageObjectLogging.log("customizeToolbar_ClickOnResetDefaults", "Click on 'ResetDefaults' button.", true, driver);
		
	}
	
	/**
	 * Types GivenString to Find A Tool field
	 * 
	 * @param GivenString String to be typed into search field 
	 * @author Michal Nowierski
	 */
	public void searchTool(String GivenString) {
		waitForElementByElement(customizeToolbar_FindAToolField);
		waitForElementClickableByElement(customizeToolbar_FindAToolField);
		customizeToolbar_FindAToolField.clear();
		customizeToolbar_FindAToolField.sendKeys(GivenString);
		PageObjectLogging.log("customizeToolbar_TypeIntoFindATool", "Type "+GivenString+" into Find A Tool field", true, driver);
		
	}
	
	
	/**
	 * Types GivenString to Find A Tool field
	 * 
	 * @param GivenString new name for the Tool
	 * @author Michal Nowierski
	 */
	public void typeNewName(String GivenString) {
		waitForElementByElement(customizeToolbar_RenameItemDialogInput);
		waitForElementClickableByElement(customizeToolbar_RenameItemDialogInput);
		customizeToolbar_RenameItemDialogInput.clear();
		customizeToolbar_RenameItemDialogInput.sendKeys(GivenString);
		PageObjectLogging.log("customizeToolbar_TypeIntoRenameItemDialog", "Type "+GivenString+" into rename item input", true, driver);
	}
	
	/**
	 * Clicks on "save" button on Rename Item dialog.
	 * 	 
	 * @author Michal Nowierski
	 */
	public void clickSaveNewName() {
		waitForElementByElement(customizeToolbar_SaveItemDialogInput);
		waitForElementClickableByElement(customizeToolbar_SaveItemDialogInput);
		clickAndWait(customizeToolbar_SaveItemDialogInput);
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
		waitForElementByElement(customizeToolbar_PageWatchlistStatusMessage);
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
		waitForElementByElement(customizeToolbar_PageWatchlistStatusMessage);
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
	 * Look up if Tool does not appear on Toolbar List
	 * 
	 * @param Tool {Follow, Edit, History, (...)} 
	 * @author Michal Nowierski
	 */
	public void customizeToolbar_VerifyToolNotOnToolbarList(String Tool) {
		waitForElementByCss("ul.options-list li");
		waitForElementNotVisibleByCss("ul.options-list li[data-caption='"+Tool+"']");
		PageObjectLogging.log("customizeToolbar_VerifyToolNotOnToolbarList", "Check if "+Tool+" does not appear on Toolbar list", true, driver);
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
	 * Drag the wanted Tool
	 * 
	 * @param ToolID ID of tool to be dragged. {PageAction:Follow, PageAction:Edit, PageAction:History, (...)}
	 * @param DragDirection The direction of dragging. e.g -1 is 'drop the tool one item below'
	 * @author Michal Nowierski
	 */
	public void customizeToolbar_DragElemAndDrop(String ToolID, int DragDirection) {
		By By1 = By.cssSelector("ul.options-list li[data-caption='"+ToolID+"']");
		By By2 = By.cssSelector("ul.options-list li[data-caption='"+ToolID+"'] img.drag");
		Point Elem1_location = driver.findElement(By1).getLocation();
		CommonFunctions.MoveCursorToElement(Elem1_location);
		waitForElementByBy(By2);
		waitForElementClickableByBy(By2);
		Point Elem2_location = driver.findElement(By2).getLocation();
		CommonFunctions.MoveCursorToElement(Elem2_location);
		if (Global.BROWSER.equals("FF")) {
			// Firefox is unable to drag and drop customize toolbar elements using actions class. Able to do it with robot class
			CommonFunctions.DragFromCurrentCursorPositionAndDrop(0, 25*DragDirection+8);
		}
		else {		
			// Chrome is unable to drag and drop customize toolbar elements using robot class. Able to do it with actions class
			WebElement draggable = driver.findElement(By2); 
			new Actions(driver).dragAndDropBy(draggable, 0, 25*DragDirection+8).perform();  
			
		}
		PageObjectLogging.log("customizeToolbar_DragElemAndDrop", "Drag element "+ToolID+", by "+DragDirection, true, driver);
	}
	
	/**
	 * Check the order of two first tools on My tools list
	 * 
	 * @param tool1 The first tool to appear on My Tools list. {History, What links here, (...)} 
	 * @param tool2 The second tool to appear on My Tools list. {History, What links here, (...)} 
	 * @author Michal Nowierski
	 */
	public void customizeToolbar_VerifyMyToolsOrder(String tool1, String tool2) {
		CommonFunctions.MoveCursorTo(0, 100);		
		CommonFunctions.MoveCursorTo(0, 0);		
		waitForElementByElement(customizeToolbar_MyToolsMenuButton);
		Point location = customizeToolbar_MyToolsMenuButton.getLocation();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		CommonFunctions.MoveCursorToElement(location);
		waitForElementByElement(customizeToolbar_MyToolsMenu);
		List<WebElement> MyToolsList = driver.findElements(customizeToolbar_MyToolsList);
		String ActualTool1=MyToolsList.get(0).getText();
		String ActualTool2=MyToolsList.get(1).getText();
		if (!tool1.equals(ActualTool1)) {
			PageObjectLogging.log("customizeToolbar_VerifyMyToolsOrder", ActualTool1+" where "+tool1+" should be. Drag & drop action (from previous step) must hadn't been succesful", false, driver);
		}
		if (!tool2.equals(ActualTool2)) {
			PageObjectLogging.log("customizeToolbar_VerifyMyToolsOrder", ActualTool2+" where "+tool2+" should be. Drag & drop action (from previous step) must hadn't been succesful", false, driver);
		}
		PageObjectLogging.log("customizeToolbar_VerifyMyToolsOrder", "Verify that My Tools list has"+tool2+" appearing after "+tool1, true, driver);
	}

	/**
	 * Click on save button on customize toolbar
	 * 
	 * @author Michal Nowierski
	 */
	public void clickSave() {
		waitForElementByElement(customizeToolbar_SaveButton);
		waitForElementClickableByElement(customizeToolbar_SaveButton);
		clickAndWait(customizeToolbar_SaveButton);
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
		List<WebElement> List = driver.findElements(customizeToolbar_ToolsList);
		for (int i = 0; i < List.size(); i++) {
			if (List.get(i).getText().equals("Following")) {
				clickOnTool("follow");
				verifyFollowMessage();
				wait.until(ExpectedConditions.textToBePresentInElement(customizeToolbar_ToolsList, "Follow"));
			
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