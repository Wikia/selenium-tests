package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;

/**
 *
 * @author liz_lux
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong
 *
 */

public class FilePagePageObject extends WikiBasePageObject {

	public static final int ABOUT_TAB = 0;
	public static final int HISTORY_TAB = 1;
	public static final int METADATA_TAB = 2;

	public FilePagePageObject(WebDriver driver) {
		super(driver);
	}

	public FilePagePageObject(WebDriver driver, String fileName) {
		super(driver);
	}

	@FindBys(@FindBy(css="ul.tabs li a"))
	private List<WebElement> tabList;
	@FindBy(css="section[data-listing-type='local'] h3.page-listing-title a")
	private WebElement appearsListing;
	@FindBy(css="section[data-listing-type='local'] div.page-list-pagination img.right")
	private WebElement localPageNext;
	@FindBy(css="section[data-listing-type='local'] div.page-list-pagination img.left")
	private WebElement localPagePrev;
	@FindBy(css=".fullImageLink")
	private WebElement fileEmbedded;
	@FindBy(css=".filehistory .video-thumb")
	private WebElement videoThumbnail;
	@FindBys(@FindBy(css=".tabs li"))
	private List<WebElement> tabs;
	@FindBy(css=".video-provider a")
	private WebElement provider;
	@FindBy(css=".fullImageLink iframe")
	private WebElement playerIframe;
	@FindBy(css=".fullImageLink [name=flashvars]")
	private WebElement playerObject;
	@FindBy(css="div#mw-imagepage-nofile")
	private WebElement noFileText;
	@FindBy(css="li#mw-imagepage-reupload-link a")
	private WebElement reuploadLink;
	@FindBy(css="#wpWikiaVideoAddUrl")
	private WebElement uploadFileURL;
	@FindBy(css="div.submits input")
	private WebElement addButton;
	@FindBys(@FindBy(css="table.filehistory tr td:nth-child(1)>a"))
	private List<WebElement> historyDeleteLinks;

	String selectedTab = ".tabBody.selected[data-tab-body='%name%']";

	public void clickTab(int tab) {
		WebElement currentTab = tabList.get(tab);
		scrollAndClick(currentTab);
		PageObjectLogging.log(
				"clickTab",
				tab + " selected",
				true
		);
	}

	public void selectAboutTab() {
		clickTab(ABOUT_TAB);
	}
	public void selectHistoryTab() {
		clickTab(HISTORY_TAB);
	}
	public void selectMetadataTab() {
		clickTab(METADATA_TAB);
	}

	public void verifySelectedTab(String tabName) {
		driver.findElement(By.cssSelector(selectedTab.replace("%name%", tabName)));
		PageObjectLogging.log(
				"verified selected tab",
				tabName + " selected",
				true
		);
	}

	public void refreshAndVerifyTabs(int tab) {

		String tabName;

		if (tab == ABOUT_TAB) {
			tabName = "about";
		} else if (tab == HISTORY_TAB) {
			tabName = "history";
		} else {
			tabName = "metadata";
		}

		clickTab(tab);
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

	public void verifyEmptyFilePage() {
		waitForElementByElement(noFileText);
		PageObjectLogging.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible", true);
	}

	public void verifyThumbnailIsPresent() {
		waitForElementByElement(videoThumbnail);
		PageObjectLogging.log("verifythumbnailIsPresent", "Verified thumbnail is visible", true);
	}

	public String getImageUrl() {
		return fileEmbedded.findElement(By.cssSelector("a")).getAttribute("href");
	}
	public String getImageThumbnailUrl() {
		return fileEmbedded.findElement(By.cssSelector("img")).getAttribute("src");
	}

	public void verifyTabsExistVideo() {
		String[] expectedTabs = { "about", "history", "metadata" };
		Assertion.assertEquals(expectedTabs.length, tabs.size());
		verifyTabsExist(expectedTabs);
	}

	public void verifyTabsExistImage() {
		String[] expectedTabs = { "about", "history" };
		Assertion.assertTrue(expectedTabs.length <= tabs.size());
		verifyTabsExist(expectedTabs);
	}

	public void verifyTabsExist(String[] expectedTabs) {
		for (int i=0; i<expectedTabs.length; i++) {
			String tab = tabs.get(i).getAttribute("data-tab");
			Assertion.assertEquals(expectedTabs[i], tab);
		}
	}

	public void verifyVideoAutoplay(boolean status) {
		String providerName = provider.getText().toLowerCase();
		PageObjectLogging.log("verifyVideoAutoplay", "Provider: "+providerName, true);

		String autoplayStr = "";
		String embedCode = "";
		switch (providerName) {
			case "screenplay":
				autoplayStr = "autostart=" + status;
				embedCode = playerObject.getAttribute("value");
				break;
			case "ign":
				autoplayStr = "&autoplay=" + status;
				embedCode = playerIframe.getAttribute("src");
				break;
			case "anyclip":
				autoplayStr = "&autoPlay=" + status;
				embedCode = playerObject.getAttribute("value");
				break;
			case "youtube":
				autoplayStr = "&autoplay=" + ((status) ? 1 : 0);
				embedCode = playerIframe.getAttribute("src");
				break;
			case "vimeo":
				autoplayStr = "?autoplay=" + ((status) ? 1 : 0);
				embedCode = playerIframe.getAttribute("src");
				break;
			case "gamestar":
			case "hulu":
			case "dailymotion":
			case "myvideo":
			case "snappytv":
			case "ustream":
			case "fivemin":
			case "metacafe":
			case "movieclips":
			case "sevenload":
			case "gametrailers":
			case "viddler":
			case "bliptv":
			case "twitchtv":
			case "youku":
				break;
			// for ooyala videos
			default:
				autoplayStr = "&autoplay=" + ((status) ? 1 : 0);
				embedCode = playerObject.getAttribute("value");
				break;
		}

		Assertion.assertStringContains(embedCode, autoplayStr);
	}

	public void replaceVideo(String url) {
		waitForElementByElement(reuploadLink);
		scrollAndClick(reuploadLink);

		uploadFileURL.sendKeys(url);
		PageObjectLogging.log("replaceVideo", url + " typed into url field", true);

		waitForElementByElement(addButton);
		scrollAndClick(addButton);
		PageObjectLogging.log("replaceVideo", "add url button clicked", true, driver);
	}

	public void verifyVersionCountAtLeast( int count ) {
		Assertion.assertTrue( historyDeleteLinks.size() >= count, "Version count is at least " + count );
	}

	public DeletePageObject deleteVersion( int num ) {
		scrollAndClick(historyDeleteLinks.get(num - 1));

		PageObjectLogging.log("deletePage", "delete page opened", true);

		return new DeletePageObject(driver);
	}
}
