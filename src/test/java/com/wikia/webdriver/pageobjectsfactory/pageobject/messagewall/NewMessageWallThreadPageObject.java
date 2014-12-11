/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class NewMessageWallThreadPageObject extends NewMessageWall {

	@FindBy(css = ".replyBody")
	private WebElement replyBody;
	@FindBy(css = ".replies .edited-by")
	private List<WebElement> lastReplyEditor;
	@FindBy(css = ".replies .msg-body")
	private List<WebElement> lastReplyText;

	public NewMessageWallThreadPageObject(WebDriver driver) {
		super(driver);
	}

	public MiniEditorComponentObject triggerMessageArea() {
		while (!driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy).isDisplayed()) {
			jQueryFocus(replyBody);
		}
		return new MiniEditorComponentObject(driver);
	}

	public void verifyLastReply(String userName, String message) {
		waitForElementByElement(replyBody);
		Assertion.assertEquals(userName, lastReplyEditor.get(lastReplyEditor.size() - 1).getText());
		Assertion.assertEquals(message, lastReplyText.get(lastReplyEditor.size() - 1).getText());
	}

}
