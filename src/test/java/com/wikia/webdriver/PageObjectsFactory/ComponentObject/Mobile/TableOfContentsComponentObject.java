package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile.MobileBasePageObject;

public class TableOfContentsComponentObject extends MobileBasePageObject{

	public TableOfContentsComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private String wikiTOC = "wiki/TOC#";

	@FindBy(css="#wkTOC.active")
	private WebElement tocSideMenu;
	@FindBy(css=".toc-list.level")
	private WebElement tocList;
	@FindBy(css="#wkTOCHandle")
	private WebElement closeTOCbutton;

	@FindBys(@FindBy(css=".toc-list:first-child > li > a"))
	private List<WebElement> tocLevel1Sections;
	@FindBys(@FindBy(css=".toclevel-2 > a"))
	private List<WebElement> tocLevel2Sections;
	@FindBys(@FindBy(css=".toclevel-3 > a"))
	private List<WebElement> tocLevel3Sections;
	@FindBys(@FindBy(css=".toclevel-4 > a"))
	private List<WebElement> tocLevel4Sections;

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

	public String clickOnLevel1Section(int number, String wikiURL) {
		WebElement tocElement = tocLevel1Sections.get(number);
		String href = tocElement.getAttribute("href");
		tocElement.click();
		PageObjectLogging.log("clickOnLevel1Section", "toc level 1 clicked", true);
		return href.replace(wikiURL + wikiTOC, "");
	}

//	public void clickButtonToCloseToc() {
//		waitForElementByElement(tocOpened);
//		tocOpened.click();
//		PageObjectLogging.log("clickChevronToChangeTocState", "toc state changed", true);
//	}
}
