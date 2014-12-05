package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class NewMessageWallAddLinkComponentObject extends WikiBasePageObject {

	@FindBy(css=".linkEditorDialog > div")
	private WebElement addLinkModal;

	private By targetBy = By.cssSelector(".linkEditorDialog > div tr:nth-child(2) input");
	private By textBy = By.cssSelector(".linkEditorDialog > div tr:nth-child(3) input");
	private By okButtonBy = By.cssSelector(".linkEditorDialog > div tr:nth-child(1) a");
	private By externalRadioButtonBy = By.cssSelector(".linkEditorDialog > div tr:nth-child(1) input[value = ext]");

	public NewMessageWallAddLinkComponentObject(WebDriver driver) {
		super(driver);
	}

	private void typeTargetAndText(String target, String text) {
		addLinkModal.findElement(targetBy).sendKeys(target);
		addLinkModal.findElement(textBy).clear();
		addLinkModal.findElement(textBy).sendKeys(text);
	}

	public void addInternalLink(String target, String text) {
		typeTargetAndText(target, text);
		addLinkModal.findElement(okButtonBy).click();
		PageObjectLogging.log("addInternalLink", "internal link " + target + " and text " + text + " added", true);
	}

	public void addExternalLink(String target, String text) {
		addLinkModal.findElement(externalRadioButtonBy).click();
		typeTargetAndText(target, text);
		addLinkModal.findElement(okButtonBy).click();
		PageObjectLogging.log("addExternalLink", "external link " + target + " and text " + text + " added", true);
	}
}
