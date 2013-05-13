package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class NotificationsComponentObject extends BasePageObject{
	
	public NotificationsComponentObject(WebDriver driver) {
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
}