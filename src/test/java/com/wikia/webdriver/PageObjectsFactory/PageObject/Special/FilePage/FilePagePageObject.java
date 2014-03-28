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

/**
 *
 * @author liz_lux
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong
 *
 */
public class FilePagePageObject extends WikiBasePageObject {

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
	@FindBy(css="div.fullImageLink")
	private WebElement fileEmbedded;
	@FindBy(css=".filehistory img.Wikia-video-thumb")
	private WebElement videoThumbnail;
	@FindBys(@FindBy(css="ul.tabs li"))
	private List<WebElement> tabs;
	@FindBy(css="p.video-provider a")
	private WebElement provider;
	@FindBy(css="div.fullImageLink iframe")
	private WebElement playerIframe;
	@FindBy(css="div.fullImageLink [name=flashvars]")
	private WebElement playerObject;

	String selectedTab = ".tabBody.selected[data-tab-body='%name%']";

	public void selectTab(int tab) {
		WebElement currentTab = tabList.get(tab);
		scrollAndClick(currentTab);
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
			case "realgravity":
				autoplayStr = "/ac330d90-cb46-012e-f91c-12313d18e962/";
				embedCode = playerObject.getAttribute("value");
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
}
