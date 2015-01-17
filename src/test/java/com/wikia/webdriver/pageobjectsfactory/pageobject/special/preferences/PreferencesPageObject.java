package com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PreferencesPageObject extends WikiBasePageObject {

	@FindBy(css = "#fbConnectDisconnect")
	private WebElement facebookDisconnect;
	@FindBy(css = "#fbDisconnectDone")
	private WebElement disconnectDone;
	@FindBy(css = "#preftoc li")
	private List<WebElement> tabs;
	@FindBy(css = "#mw-htmlform-email-me-v2 td.mw-input")
	private List<WebElement> emailMeSectionRows;
	@FindBy(css = ".mw-htmlform-submit")
	private WebElement saveButton;
	@FindBy(css = ".mw-prefs-buttons a")
	private WebElement restoreDefaultLink;
	@FindBy(css = ".global-notification.confirm")
	private WebElement saveNotfication;

	public enum tabNames {
		INFO, EMAIL, EDITING, UNDER, FACEBOOK
	}

	public PreferencesPageObject(WebDriver driver) {
		super(driver);
	}

	public PreferencesPageObject selectTab(tabNames tab) {
		int tabNum = -1;
		switch (tab) {
			case INFO:
				tabNum = 0;
				tabs.get(tabNum).findElement(By.cssSelector("a")).click();
				break;
			case EMAIL:
				tabNum = 1;
				tabs.get(tabNum).findElement(By.cssSelector("a")).click();
				break;
			case EDITING:
				tabNum = 2;
				tabs.get(tabNum).findElement(By.cssSelector("a")).click();
				break;
			case UNDER:
				tabNum = 3;
				tabs.get(tabNum).findElement(By.cssSelector("a")).click();
				break;
			case FACEBOOK:
				tabNum = 4;
				tabs.get(tabNum).findElement(By.cssSelector("a")).click();
				break;
			default:
				throw new NoSuchElementException("Non-existing tab selected");
		}
		waitForValueToBePresentInElementsAttributeByElement(tabs.get(tabNum), "class", "selected");
		PageObjectLogging.log("selectTab", "tab " + tab.toString() + " selected", true);
		return this;
	}

	public void verifyEmailMeSection() {
		for (WebElement elem : emailMeSectionRows) {
			PageObjectLogging.log("verifyEmailSection", "verifying " + elem.getText(), true);
			Assertion.assertEquals("true", elem.findElement(By.cssSelector("input")).getAttribute("checked"));
		}
	}

	public void disconnectFromFacebook() {
		waitForElementByElement(facebookDisconnect);
		scrollAndClick(facebookDisconnect);
		waitForElementByElement(disconnectDone);
		PageObjectLogging.log("disconnectFromFacebook", "account has been disconnected from Facebook", true);
	}

	public PreferencesPageObject clickSaveButton() {
		waitForElementClickableByElement(saveButton);
		scrollAndClick(saveButton);
		PageObjectLogging.log("clickSaveButton", "Save button clicked", true);
		return new PreferencesPageObject(driver);
	}

	public void clickRestoreLink() {
		waitForElementClickableByElement(restoreDefaultLink);
		restoreDefaultLink.click();
		PageObjectLogging.log("clickRestoreLink", "Restore Deault Link clicked", true);
	}

	public void verifySaveNotification() {
		waitForElementVisibleByElement(saveNotfication);
		PageObjectLogging.log("verifySaveNotification", "Restore Deault Link clicked", true);
	}

}
