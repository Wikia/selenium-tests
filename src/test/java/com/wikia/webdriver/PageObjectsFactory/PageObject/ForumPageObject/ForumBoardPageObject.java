package com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class ForumBoardPageObject extends BasePageObject{

	@FindBy(css="textarea.title:nth-child(2)")
	private WebElement discussionTitleArea;
	@FindBy(css="button.submit")
	private WebElement postButton;
	@FindBy(css="div.msg-title a")
	private WebElement discussionTitle;
	@FindBys(@FindBy(css="div.msg-body p"))
	private List<WebElement> discussionBody;
	@FindBys(@FindBy(css="li.SpeechBubble img.thumbimage"))
	private List<WebElement> postedImageList;
	@FindBys(@FindBy(css="li.thread div.thread-left h4 a"))
	private List<WebElement> threadTitlesList;
	@FindBy(css=".notify-everyone")
	private WebElement highlight;
    @FindBy(css="#Forum .board-description")
    private WebElement boardDescription;
	private String discussionTextarea = "textarea.title:nth-child(2)";
	private String wikiaEditorTextarea = "#WikiaEditor-0";

	MiniEditorComponentObject miniEditor;

	public ForumBoardPageObject(WebDriver driver) {
		super(driver);
		miniEditor = new MiniEditorComponentObject(driver);
		PageFactory.initElements(driver, this);
	}

	private void checkHighlightCheckbox(boolean isHighLighted) {
		if (isHighLighted) {
			highlight.click();
			PageObjectLogging.log("checkHighlightCheckbox",
					"highlight checkbox clicked", true, driver);
		}

	}

	public ForumThreadPageObject startDiscussion(String title, String message,
			boolean highlight) {
		jQueryFocus(discussionTextarea);
		discussionTitleArea.sendKeys(title);
		jQueryFocus(wikiaEditorTextarea);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();
		checkHighlightCheckbox(highlight);
		clickPostButton();
		PageObjectLogging.log("startDiscussion", "discussion with message: "
				+ message + ", with title " + title + " posted", true, driver);
		return new ForumThreadPageObject(driver);
	}

	public ForumThreadPageObject openDiscussion(String title) {
		for (WebElement elem : threadTitlesList) {
			if (elem.getText().contains(title)) {
				scrollAndClick(elem);
				PageObjectLogging.log("openDiscussion",
						"discussion with title: " + title + ", opened", true,
						driver);
				return new ForumThreadPageObject(driver);
			}
		}
		PageObjectLogging.log("openDiscussion", "discussion with title: "
				+ title + ", not found", false, driver);
		return null;
	}

	public ForumThreadPageObject startDiscussionWithoutTitle(String message) {
		jQueryFocus(discussionTextarea);
		jQueryFocus(wikiaEditorTextarea);
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(message);
		driver.switchTo().defaultContent();
		clickPostNotitleButton();
		PageObjectLogging.log("startDiscussionWithoutTitle",
				"discussion with message: " + message
						+ " without title, posted", true, driver);
		return new ForumThreadPageObject(driver);
	}

	public void clickPostButton() {
		waitForElementClickableByElement(postButton);
		scrollAndClick(postButton);
		PageObjectLogging.log("clickPostButton", "post button clicked", true,
				driver);
	}

	public void verifyDiscussionTitleAndMessage(String title, String message)
	{
		waitForTextToBePresentInElementByElement(discussionTitle, title);
		waitForTextToBePresentInElementByElement(discussionBody.get(0), message);
		PageObjectLogging.log("verifyDiscussionWithTitle", "discussion with title and message verified", true);
	}

    public void verifyBoardDescription( String description ) {
        waitForTextToBePresentInElementByElement( boardDescription, description );
        PageObjectLogging.log("verifyBoardDescription", "board description verified", true);
    }

	public void clickPostNotitleButton()
	{
		waitForElementClickableByElement(postButton);
		scrollAndClick(postButton);
		scrollAndClick(postButton);
	}

	public void startDiscussionWithImgae(String title) {
		jQueryFocus(discussionTextarea);
		discussionTitleArea.sendKeys(title);
		jQueryFocus(wikiaEditorTextarea);
		PhotoAddComponentObject photoAdd = miniEditor.clickAddImage();
		PhotoOptionsComponentObject photoOptions = photoAdd.addPhotoFromWiki(
				"image", 1);
		photoOptions.clickAddPhoto();
		PageObjectLogging.log("startDiscussionWithImgae",
				"discussion with image started" + title, true, driver);
	}

	public void verifyDiscussionWithImage() {
		waitForElementByElement(postedImageList.get(0));
		PageObjectLogging.log("verifyPostedMessageWithImage",
				"discussion with image started", true);
	}

	public void startDiscussionWithLink(String internalLink,
			String externalLink, String title) {
		jQueryFocus(discussionTextarea);
		discussionTitleArea.sendKeys(title);
		jQueryFocus(wikiaEditorTextarea);
		// add internal wikia link
		miniEditor.addInternalLink(internalLink);
		// add external link
		driver.switchTo().frame(miniEditor.miniEditorIframe);
		miniEditor.writeMiniEditor(Keys.END);
		miniEditor.writeMiniEditor(Keys.ENTER);
		driver.switchTo().defaultContent();
		miniEditor.addExternalLink(externalLink);
		PageObjectLogging.log("startDiscussionWithLink",
				"internal and external links: " + internalLink + " and"
						+ externalLink + "added", true, driver);

	}

	public void verifyStartedDiscussionWithLinks(String internalLink,
			String externalLink) {
		waitForTextToBePresentInElementByElement(discussionBody.get(0),
				internalLink);
		waitForTextToBePresentInElementByElement(discussionBody.get(1),
				externalLink);
		PageObjectLogging.log("verifyStartedDiscussionWithLinks",
				"internal and external links: " + internalLink + " and"
						+ externalLink + "verified", true);
	}

	public void startDiscussionWithVideo(String url, String title) {
		jQueryFocus(discussionTextarea);
		discussionTitleArea.sendKeys(title);
		jQueryFocus(wikiaEditorTextarea);
		miniEditor.addVideoMiniEditor(url);
		PageObjectLogging.log("startDiscussionWithVideo",
				"discussion with video started" + title, true, driver);
	}

	public void unfollowIfDiscussionIsFollowed(int threadNumber) {
		WebElement followButton = driver.findElement(By
				.cssSelector(".thread:nth-child(" + threadNumber
						+ ") li.follow"));
		waitForElementByElement(followButton);
		if (followButton.getText().contains("Following")) {
			PageObjectLogging
					.log("unfollowIfDiscussionIsFollowed",
							"discussion is followed. Preparing to click \"unfollowed\"",
							true);
			waitForElementClickableByElement(followButton);
			scrollAndClick(followButton);
			PageObjectLogging.log("unfollowIfDiscussionIsFollowed",
					"discussion unfollowed", true, driver);
		} else {
			PageObjectLogging.log("unfollowIfDiscussionIsFollowed",
					"discussion was unfollowed already", true);
		}
	}

	public void verifyTextOnFollowButton(int threadNumber, String followStatus) {
		WebElement followButton = driver.findElement(By
				.cssSelector(".thread:nth-child(" + threadNumber
						+ ") li.follow"));
		waitForTextToBePresentInElementByElement(followButton, followStatus);
		PageObjectLogging.log("verifyTextOnFollowButton",
				"verify that thread number " + threadNumber
						+ " has the status: " + followStatus, true);
	}

	public void clickOnFollowButton(int threadNumber) {
		WebElement followButton = driver.findElement(By
				.cssSelector(".thread:nth-child(" + threadNumber
						+ ") li.follow"));
		waitForElementByElement(followButton);
		waitForElementClickableByElement(followButton);
		scrollAndClick(followButton);
		PageObjectLogging.log("clickOnFollowButton",
				"click on follow button of thread number " + threadNumber,
				true, driver);
	}

	public String getTitle() {
		String Url = getCurrentUrl();
		return Url.substring(Url.indexOf("Board:")+6);
	}
}
