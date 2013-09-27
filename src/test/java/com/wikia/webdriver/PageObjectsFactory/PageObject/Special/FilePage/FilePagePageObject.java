package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author liz_lux
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class FilePagePageObject extends WikiBasePageObject {

	private String fileName;

	public FilePagePageObject(WebDriver driver) {
		super(driver);
	}

	public FilePagePageObject(WebDriver driver, String fileName) {
		super(driver);
		this.fileName = fileName;
	}

	@FindBys(@FindBy(css="ul.tabs li a"))
	private List<WebElement> tabList;
	@FindBy(css="section[data-listing-type='local'] h3.page-listing-title a")
	private WebElement appearsListing;
	@FindBy(css="section[data-listing-type='local'] div.page-list-pagination img.right")
	private WebElement localPageNext;
	@FindBy(css="section[data-listing-type='local'] div.page-list-pagination img.left")
	private WebElement localPagePrev;
	@FindBy(css="div.fullImageLink")
	private WebElement fileEmbedded;
	@FindBy(css=".filehistory img.Wikia-video-thumb")
	private WebElement videoThumbnail;

	String selectedTab = ".tabBody.selected[data-tab-body='%name%']";

	public void selectTab(int tab) {
		WebElement currentTab = tabList.get(tab);
		currentTab.click();
		PageObjectLogging.log(
				"selectTab",
				tab + " selected",
				true
		);
	}

	public void verifySelectedTab(String tabName) {
		driver.findElement(By.cssSelector(selectedTab.replace("%name%", tabName)));
		PageObjectLogging.log(
				"verified selected tab",
				tabName + " selected",
				true
		);
	}

	public void openFilePage(String fileName) {
		getUrl(URLsContent.filePage + fileName);
		waitForElementByElement(tabList.get(0));
		PageObjectLogging.log("Open file page", "file page opened", true);
	}

	public void refreshAndVerifyTabs(int tab) {

		String tabName;

		if(tab == 0) {
			tabName = "about";
		} else if(tab == 1) {
			tabName = "history";
		} else {
			tabName = "metadata";
		}

		selectTab(tab);
		verifySelectedTab(tabName);
		refreshPage();
		verifySelectedTab(tabName);
	}

	// Page forward in the local "appears on" section
	public void clickLocalAppearsPageNext() {
		localPageNext.click();
		PageObjectLogging.log("clickLocalAppearsPageNext", "local appears page next button clicked", true);
	}

	// Page backward in the local "appears on" section
	public void clickLocalAppearsPagePrev() {
		localPagePrev.click();
		PageObjectLogging.log("clickLocalAppearsPagePrev", "local appears page preview button clicked", true);
	}

	// Verify that a specific video title is in the "Appears on these pages" list
	public void verifyAppearsOn(String articleName) {
		Assertion.assertTrue(appearsListing.getText().equals(articleName));
	}

	public void verifyEmbeddedVideoIsPresent() {
		waitForElementByElement(fileEmbedded);
		PageObjectLogging.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible", true);
	}

	public void verifyThumbnailIsPresent() {
		waitForElementByElement(videoThumbnail);
		PageObjectLogging.log("verifythumbnailIsPresent", "Verified thumbnail is visible", true);
	}

	public void verifyHeader(String fileName) {
		waitForElementByElement(wikiFirstHeader);
		Assertion.assertStringContains(wikiFirstHeader.getText(), fileName);
	}

	public void verifyCorrectFilePage() {
		waitForStringInURL("File:"+fileName);
		PageObjectLogging.log("VerifyCorrectFilePage", "Verify that the page represents "+fileName+" file", true, driver);
	}

	public String getImageUrl() {
		return fileEmbedded.findElement(By.cssSelector("a")).getAttribute("href");
	}
	public String getImageThumbnailUrl() {
		return fileEmbedded.findElement(By.cssSelector("img")).getAttribute("src");
	}
}
