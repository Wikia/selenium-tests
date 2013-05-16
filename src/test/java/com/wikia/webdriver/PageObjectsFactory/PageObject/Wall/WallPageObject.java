package com.wikia.webdriver.PageObjectsFactory.PageObject.Wall;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class WallPageObject extends BasePageObject {

	@FindBy(css="textarea#WallMessageTitle")
	private WebElement wallMessageTitle;
	@FindBy(css="button#WallMessageSubmit")
	private WebElement postButton;

	private String wallMessageTitleSelector = "textarea#WallMessageTitle";
	private String wallMessageBodySelector = "textarea#WallMessageBody";

	MiniEditorComponentObject miniEditor;

	public WallPageObject(WebDriver driver) {
		super(driver);
		miniEditor = new MiniEditorComponentObject(driver, Domain);
	}

	/**
	 * Opens the message wall for a user.
	 *
	 * @param userName
	 */
	public void openWallPage(String userName) {
		getUrl(Global.DOMAIN+"wiki/Message_Wall:"+userName);
		waitForElementByElement(wallMessageTitle);
		PageObjectLogging.log("openWallPage", "message wall page opened", true);
	}

	/**
	 * Start a discussion on a message wall.
	 *
	 * @param title
	 * @param message
	 */
	public void startDiscussion(String title, String message) {
		CommonFunctions.scrollToElement(wallMessageTitle);
		jQueryFocus(wallMessageTitleSelector);
		wallMessageTitle.sendKeys(title);
		jQueryFocus(wallMessageBodySelector);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();
		clickPostButton();
		PageObjectLogging.log("startDiscussion", "discussion with message: "+message+", with title "+title+" posted", true);
	}

	/**
	 * Click the post button to post a message.
	 */
	public void clickPostButton() {
		waitForElementClickableByElement(postButton);
		clickAndWait(postButton);
		PageObjectLogging.log("clickPostButton", "post button clicked", true);
	}

}
