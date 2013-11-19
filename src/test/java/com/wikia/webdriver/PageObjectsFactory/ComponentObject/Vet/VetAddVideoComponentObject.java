package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class VetAddVideoComponentObject extends WikiBasePageObject{

	@FindBy(css="#VideoEmbedUrl")
	private WebElement urlField;
	@FindBy(css="#VideoEmbedUrlSubmit")
	private WebElement addUrlButton;
	@FindBy(css="figure")
	private WebElement libraryFigures;
	@FindBy(css="#VET-search-field")
	private WebElement findField;
	@FindBy(css="#VET-search-submit")
	private WebElement findButton;
	@FindBys(@FindBy(css="#VET-suggestions li"))
	private List<WebElement> videoList;
	@FindBys(@FindBy(css="#VET-suggestions .Wikia-video-play-button"))
	private List<WebElement> videoThumbnailsList;
	@FindBy(css=".Wikia-video-enabledEmbedCode")
	private WebElement videoThumbnail;
	@FindBy(css="div.Wikia-video-play-button")
	private WebElement suggestedVideo;
	@FindBy(css="a.bottom-close-button")
	private WebElement closeButton;

	private By addVideoLibraryLink = By.cssSelector("li a[href*='http']");

	public VetAddVideoComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,  this);
	}

	private String videoName;

	private void typeInUrl(String url) {
		waitForElementByElement(urlField);
		urlField.sendKeys(url);
		PageObjectLogging.log("typeInUrl", url+" typed into url field", true);
	}

	private void clickAddButtonProvider() {
		waitForElementByElement(addUrlButton);
		scrollAndClick(addUrlButton);
		PageObjectLogging.log("clickAddButton", "add url button clicked", true, driver);
	}

	private void typeInSearchQuery(String query) {
		waitForElementByElement(findField);
		findField.sendKeys(query);
		PageObjectLogging.log("typeInSearchQuery", query+" query typed into search video field", true);
	}

	private void clickFindButton() {
		waitForElementByElement(findButton);
		scrollAndClick(findButton);
		PageObjectLogging.log("clickFindButton", "find button clicked", true, driver);
	}

	private void clickAddVideoLibrary(int videoListItem) {
		WebElement listElem = videoList.get(videoListItem);
		waitForElementByElement(listElem);
		String videoName = listElem.findElement(By.cssSelector("a[data-pos]")).getAttribute("title");
		WebElement addVideoLink = listElem.findElement(addVideoLibraryLink);
		scrollAndClick(addVideoLink);
		PageObjectLogging.log("clickAddVideoLibrary", "add video button clicked", true, driver);
		this.videoName =  videoName;
	}

	private void checkIfLibraryIsPresent() {
		waitForElementByElement(libraryFigures);
		PageObjectLogging.log("checkIfLibraryIsPresent", "library carousel present", true);
	}

	public void verifyAddVideoModal() {
		waitForElementByElement(urlField);
		waitForElementByElement(addUrlButton);
		PageObjectLogging.log("verifyAddVideoModal", "add video modal is displayed", true);
	}

	public VetOptionsComponentObject addVideoByUrl(String url) {
		typeInUrl(url);
		clickAddButtonProvider();
		return new VetOptionsComponentObject(driver);
	}

	public VetOptionsComponentObject addVideoByQuery(String query, int i) {
		typeInSearchQuery(query);
		clickFindButton();
		checkIfLibraryIsPresent();
		clickVideoThumbnail(i);
		checkVideoPreviewAppearing();
		clickAddVideoLibrary(i);
		return new VetOptionsComponentObject(driver);
	}

	private void clickVideoThumbnail(int i) {
		videoThumbnailsList.get(i).click();
		PageObjectLogging.log("clickVideoThumbnail", "video thumbnails clicked", true);
	}

	private void checkVideoPreviewAppearing() {
		waitForElementByElement(videoThumbnail);
		PageObjectLogging.log("checkVideoPreviewAppearing", "video preview appeared", true);
	}

	public String getVideoName() {
		return this.videoName;
	}

	public void verifySuggestionsIsDisplayed() {
		waitForElementByElement(suggestedVideo);
		PageObjectLogging.log("verifySuggestionsIsDisplayed", "Verified suggested module appeared", true, driver);
	}

	public void clickCloseButton() {
		waitForElementByElement(closeButton);
		scrollAndClick(closeButton);
		PageObjectLogging.log("updateVideoButton", "update video button clicked",  true);
	}
}
