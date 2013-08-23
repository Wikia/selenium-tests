package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */public class VetOptionsComponentObject extends WikiBasePageObject{

		@FindBy(css=".main-header")
		private WebElement videoOptionsHeader;
		@FindBy(css="#VideoEmbedLayoutRow")
		private WebElement videoEmbedLayotRow;
		@FindBy(css="#VideoEmbedCaption")
		private WebElement captionField;
		@FindBy(css="#VideoEmbedManualWidth")
		private WebElement withInputField;
		@FindBy(css="#VET_LayoutLeftBox label")
		private WebElement positionLayoutLeft;
		@FindBy(css="#VET_LayoutCenterBox label")
		private WebElement positionLayoutCenter;
		@FindBy(css="#VET_LayoutRightBox label")
		private WebElement positionLayoutRight;
		@FindBy(css=".vet-style-label.VideoEmbedThumbOption")
		private WebElement styleWithCaption;
		@FindBy(css=".vet-style-label.VideoEmbedNoThumbOption")
		private WebElement styleWithoutCaption;
		@FindBy(css="#VideoEmbedName")
		private WebElement videoName;
		@FindBy(css=".input-group.button-group input")
		private WebElement addAvideo;
		@FindBy(css="#VideoEmbedCloseButton")
		private WebElement returnToEditing;
		@FindBy(css="input.wikia-button.v-float-right")
		private WebElement updateVideoButton;
		@FindBy(css="span#VET_LayoutLeftBox.selected")
		private WebElement positionLayoutLeftSelected;
		@FindBy(css="span#VET_LayoutRightBox.selected")
		private WebElement positionLayoutRightSelected;
		@FindBy(css="span#VET_LayoutCenterBox.selected")
		private WebElement positionLayoutCenterSelected;
		@FindBy(css="input#VideoEmbedName")
		private WebElement uneditableVideoNameField;
		@FindBy(css="input[type='text'][id='VideoEmbedName']")
		private WebElement editableVideoNameField;
		@FindBy(css="div#VideoEmbedThumb div")
		private WebElement videoThumbnail;
		@FindBy(css="div#VideoEmbedNameRow p")
		private WebElement videoNameCaption;
		@FindBy(css="#VET_LayoutLeftBox")
		private WebElement leftPositionButton;

		public VetOptionsComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,  this);
	}

	public void adjustStyle(StyleVideo style) {
		switch (style) {
		case caption:
			styleWithCaption.click();
			break;
		case nocaption:
			styleWithoutCaption.click();
			break;
		}
		PageObjectLogging.log("adjustStyle", "style selected " + style.toString(), true);
	}

	public String getVideoName(){
		return videoNameCaption.getText();
	}

	public void setCaption(String caption){
		waitForElementByElement(captionField);
		captionField.clear();
		captionField.sendKeys(caption);
		PageObjectLogging.log("setCaption", "caption was set to: "+caption, true);
	}

	public void adjustWith(int Width) {
		String width = Integer.toString(Width);
		waitForElementByElement(withInputField);
		withInputField.clear();
		withInputField.sendKeys(width);
		PageObjectLogging.log("adjustWith", "width set to: "+Width,  true, driver);
	}

	private void clickAddaVideo() {
		waitForElementByElement(addAvideo);
		scrollAndClick(addAvideo);
		PageObjectLogging.log("clickAddaVideo", "add video button clicked",  true, driver);
	}

	private void clickRetunToEditing(){
		waitForElementByElement(returnToEditing);
		scrollAndClick(returnToEditing);
		PageObjectLogging.log("clickReturnToEditing", "return to editing button clicked",  true, driver);
	}

	private void verifyVideoThumbnail(){
		waitForElementByElement(videoThumbnail);
		Dimension dim = videoThumbnail.getSize();
		int w = dim.getWidth();
		Assertion.assertEquals(w, 350);
		PageObjectLogging.log("verifyVideoThumbnail", "video thumbnail is visible",  true);
	}

	public WikiArticleEditMode submit(){
		verifyVideoThumbnail();
		clickAddaVideo();
		clickRetunToEditing();
		return new WikiArticleEditMode(driver);
	}

	public void update(){
		clickAddaVideo();
	}

	public void adjustPosition(PositionsVideo pos){
		waitForElementByElement(videoEmbedLayotRow);
		switch (pos){
		case left:
			positionLayoutLeft.click();
			break;
		case center:
			positionLayoutCenter.click();
			break;
		case right:
			positionLayoutRight.click();
			break;
		default:
			PageObjectLogging.log("adjustPosition", "invalid style selected",  false);
			break;
		}
		PageObjectLogging.log("adjustPosition", "position " + pos.toString() + " selected", true);
	}

	public void verifyVideoAlignmentSelected(PositionsVideo positions) {
		waitForElementByElement(videoEmbedLayotRow);
		String selectedPositionId = videoEmbedLayotRow
				.findElement(By.cssSelector(".selected"))
				.getAttribute("id");
		String desiredPositionId;
		switch (positions){
		case left:
			desiredPositionId = "VET_LayoutLeftBox";
			break;
		case center:
			desiredPositionId = "VET_LayoutCenterBox";
			break;
		case right:
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
		PageObjectLogging.log("updateVideoButton", "update video button clicked",  true, driver);
	}


	public void verifyVideoWidth(int widthDesired) {
		waitForElementByElement(withInputField);
		int width = Integer.parseInt(withInputField.getAttribute("value"));
		Assertion.assertNumber(
				widthDesired,
				width,
				"video width is " + width + " should be " + widthDesired);
		PageObjectLogging.log("verifyVideoWidth", "video width verified", true);
	}

	public void verifyCaption(String captionDesired) {
		String caption = captionField.getAttribute("value");
		Assertion.assertEquals(captionDesired, caption);
	}

	public void verifyNoCaption() {
		String styleClass = styleWithoutCaption.findElement(
				By.xpath("./..")
		).getAttribute("class");
		Assertion.assertEquals("selected", styleClass);
	}

	public void verifyNameNotEditable() {
		Assertion.assertTrue(!uneditableVideoNameField.isDisplayed());
		PageObjectLogging.log("verifyNameNotEditable", "video name field not editable",  true);
	}
}
