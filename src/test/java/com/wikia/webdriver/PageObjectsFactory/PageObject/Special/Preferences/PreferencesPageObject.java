package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class PreferencesPageObject extends WikiBasePageObject{

	public PreferencesPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="#fbConnectDisconnect")
	private WebElement facebookDisconnect;
	@FindBy(css="#fbDisconnectDone")
	private WebElement disconnectDone;
	@FindBy(css="#preftoc li")
	private List<WebElement> tabs;
	@FindBy(css="#mw-htmlform-email-me-v2 td.mw-input")
	private List<WebElement> emailMeSectionRows;
	@FindBy(css=".mw-htmlform-submit")
	private WebElement saveButton;
	@FindBy(css=".mw-prefs-buttons a")
	private WebElement restoreDefaultLink;
	@FindBy(css=".global-notification.confirm")
	private WebElement saveNotfication;

	public enum tabNames{
		Info, Email, Editing, Under, Facebook
	}

	public PreferencesPageObject selectTab(tabNames tab){
		int tabNum = -1;
		switch(tab){
		case Info:
			tabNum = 0;
			tabs.get(tabNum).findElement(By.cssSelector("a")).click();
			break;
		case Email:
			tabNum = 1;
			tabs.get(tabNum).findElement(By.cssSelector("a")).click();
			break;
		case Editing:
			tabNum = 2;
			tabs.get(tabNum).findElement(By.cssSelector("a")).click();
			break;
		case Under:
			tabNum = 3;
			tabs.get(tabNum).findElement(By.cssSelector("a")).click();
			break;
		case Facebook:
			tabNum = 4;
			tabs.get(tabNum).findElement(By.cssSelector("a")).click();
			break;
		}
		waitForValueToBePresentInElementsAttributeByElement(tabs.get(tabNum), "class", "selected");
		PageObjectLogging.log("selectTab", "tab "+tab.toString()+" selected", true);
		return this;
	}

	public void verifyEmailMeSection(){
		for (WebElement elem:emailMeSectionRows){
			PageObjectLogging.log("verifyEmailSection", "verifying "+elem.getText(), true);
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
		saveButton.click();
		return new PreferencesPageObject(driver);
	}

	public void clickRestoreLink() {
		waitForElementClickableByElement(restoreDefaultLink);
		restoreDefaultLink.click();
	}
}
