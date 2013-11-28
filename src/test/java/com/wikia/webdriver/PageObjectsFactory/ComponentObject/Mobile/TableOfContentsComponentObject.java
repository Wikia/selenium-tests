package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class TableOfContentsComponentObject extends MobileBasePageObject{

	public TableOfContentsComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#wkTOC.active")
	private WebElement tocSideMenu;
	@FindBy(css=".toc-list.level")
	private WebElement tocList;
	@FindBy(css="#wkTOCHandle")
	private WebElement closeTOCbutton;

	public void verifyTocElements() {
		waitForElementByElement(tocSideMenu);
		waitForElementByElement(tocList);
		PageObjectLogging.log("verifyTocElements", "toc elements verified", true);
	}

	public void closeToc() {
		waitForElementByElement(closeTOCbutton);
		closeTOCbutton.click();
		PageObjectLogging.log("closeToc", "toc closed", true);
	}

//	public void clickButtonToCloseToc() {
//		waitForElementByElement(tocOpened);
//		tocOpened.click();
//		PageObjectLogging.log("clickChevronToChangeTocState", "toc state changed", true);
//	}
}
