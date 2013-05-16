package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Preferences;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class PreferencesPageObject extends BasePageObject{

	public PreferencesPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="#preftoc li")
	private List<WebElement> tabs;
	@FindBy(css="#mw-htmlform-email-me-v2 td.mw-input")
	private List<WebElement> emailMeSectionRows;
	
	public enum tabNames{
		Info, Email, Editing, Under, Facebook
	}
	
	public void openSpecialPreferencesPage(){
		getUrl(Global.DOMAIN+URLsContent.specialPreferences);
		waitForElementByElement(tabs.get(0));
		PageObjectLogging.log("openSpecialPreferencesPage", "Special:Prefereces page opened", true);
	}
	
	public PreferencesPageObject selectTab(tabNames tab){
		int tabNum = -1;
		switch(tab){
		case Info:
			tabNum = 0;
			tabs.get(tabNum).click();
			break;
		case Email:
			tabNum = 1;
			tabs.get(tabNum).click();
			break;
		case Editing:
			tabNum = 2;
			tabs.get(tabNum).click();
			break;
		case Under:
			tabNum = 3;
			tabs.get(tabNum).click();
			break;
		case Facebook:
			tabNum = 4;
			tabs.get(tabNum).click();
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
}
