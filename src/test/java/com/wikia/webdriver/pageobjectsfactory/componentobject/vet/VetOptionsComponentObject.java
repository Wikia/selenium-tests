package com.wikia.webdriver.pageobjectsfactory.componentobject.vet;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetOptionsComponentObject extends AddMediaModalComponentObject {

	@FindBy(css = "#VideoEmbedLayoutRow")
	private WebElement videoEmbedLayotRow;
	@FindBy(css = "#VideoEmbedCaption")
	private WebElement captionField;
	@FindBy(css = "#VideoEmbedManualWidth")
	private WebElement widthInputField;
	@FindBy(css = "#VET_LayoutLeftBox label")
	private WebElement positionLayoutLeft;
	@FindBy(css = "#VET_LayoutCenterBox label")
	private WebElement positionLayoutCenter;
	@FindBy(css = "#VET_LayoutRightBox label")
	private WebElement positionLayoutRight;
	@FindBy(css = ".input-group.button-group input")
	private WebElement addAvideo;
	@FindBy(css = "#VideoEmbedCloseButton")
	private WebElement returnToEditing;
	@FindBy(css = "input.wikia-button.v-float-right")
	private WebElement updateVideoButton;
	@FindBy(css = "input#VideoEmbedName")
	private WebElement uneditableVideoNameField;
	@FindBy(css = "#VideoEmbedThumb .video-embed")
	private WebElement videoThumbnail;
	@FindBy(css = "div#VideoEmbedNameRow p")
	private WebElement videoNameCaption;

	public VetOptionsComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public String getVideoName() {
		return videoNameCaption.getText();
	}

	public void setCaption(String caption) {
		waitForElementByElement(captionField);
		captionField.clear();
		captionField.sendKeys(caption);
		PageObjectLogging.log("setCaption", "caption was set to: " + caption, true);
	}

	public void adjustWith(int width) {
		String widthString = Integer.toString(width);
		waitForElementByElement(widthInputField);
		widthInputField.clear();
		widthInputField.sendKeys(widthString);
		PageObjectLogging.log("adjustWith", "width set to: " + width, true, driver);
	}

	private void clickAddaVideo() {
		waitForElementByElement(addAvideo);
		waitForElementClickableByElement(addAvideo);
		scrollAndClick(addAvideo);
		PageObjectLogging.log("clickAddaVideo", "add video button clicked", true, driver);
	}

	private void clickRetunToEditing() {
		waitForElementByElement(returnToEditing);
		scrollAndClick(returnToEditing);
		PageObjectLogging.log("clickReturnToEditing", "return to editing button clicked", true, driver);
	}

	private void verifyVideoThumbnail() {
		waitForElementByElement(videoThumbnail);
		Dimension dim = videoThumbnail.getSize();
		int w = dim.getWidth();
		Assertion.assertEquals(w, 350);
		PageObjectLogging.log("verifyVideoThumbnail", "video thumbnail is visible", true);
	}

	private void verifyVideoModalNotVisible() {
		waitForElementNotVisibleByElement(addVideoModal);
	}

	public WikiArticleEditMode submit() {
		verifyVideoThumbnail();
		clickAddaVideo();
		clickRetunToEditing();
		verifyVideoModalNotVisible();
		return new WikiArticleEditMode(driver);
	}

	public void update() {
		clickAddaVideo();
		verifyVideoModalNotVisible();
	}

	public void adjustPosition(PositionsVideo position) {
		waitForElementByElement(videoEmbedLayotRow);
		switch (position) {
			case LEFT:
				positionLayoutLeft.click();
				break;
			case CENTER:
				positionLayoutCenter.click();
				break;
			case RIGHT:
				positionLayoutRight.click();
				break;
			default:
				throw new NoSuchElementException("Non-existing position selected");
		}
		PageObjectLogging.log("adjustPosition", "position " + position.toString() + " selected", true);
	}

	public void verifyVideoAlignmentSelected(PositionsVideo positions) {
		waitForElementByElement(videoEmbedLayotRow);
		String selectedPositionId = videoEmbedLayotRow
			.findElement(By.cssSelector(".selected"))
			.getAttribute("id");
		String desiredPositionId;
		switch (positions) {
			case LEFT:
				desiredPositionId = "VET_LayoutLeftBox";
				break;
			case CENTER:
				desiredPositionId = "VET_LayoutCenterBox";
				break;
			case RIGHT:
				desiredPositionId = "VET_LayoutRightBox";
				break;
			default:
				desiredPositionId = "desired position not provided";
		}
		Assertion.assertEquals(desiredPositionId, selectedPositionId);
	}

	public void clickUpdateVideo() {
		waitForElementByElement(updateVideoButton);
		scrollAndClick(updateVideoButton);
		PageObjectLogging.log("updateVideoButton", "update video button clicked", true, driver);
	}


	public void verifyVideoWidth(int widthDesired) {
		waitForElementByElement(widthInputField);
		int width = Integer.parseInt(widthInputField.getAttribute("value"));
		Assertion.assertEquals(
			widthDesired,
			width);
		PageObjectLogging.log("verifyVideoWidth", "video width verified", true);
	}

	public void verifyCaption(String captionDesired) {
		String caption = captionField.getAttribute("value");
		Assertion.assertEquals(captionDesired, caption);
	}

	public void verifyNameNotEditable() {
		Assertion.assertTrue(!uneditableVideoNameField.isDisplayed());
		PageObjectLogging.log("verifyNameNotEditable", "video name field not editable", true);
	}
}
