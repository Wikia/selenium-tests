package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;


public class VetOptionsComponentObject extends BasePageObject{

	//generic
		@FindBy(css=".main-header")
		private WebElement videoOptionsHeader;
		@FindBy(css="#VideoEmbedLayoutRow")
		private WebElement videoEmbedLayotRow;
		@FindBy(css=".vet-style-label.VideoEmbedThumbOption")
		private WebElement styleWithCation;
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
		@FindBy(css="label.VideoEmbedNoThumbOption")
		private WebElement styleWithoutCaption;
		@FindBy(css="#VideoEmbedName")
		private WebElement videoName;
		@FindBy(css="div.VideoEmbedNoBorder input")
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
		@FindBy(css="input[type='hidden'][id='VideoEmbedName']")
		private WebElement uneditableVideoNameField;
		@FindBy(css="input[type='text'][id='VideoEmbedName']")
		private WebElement editableVideoNameField;
		@FindBy(css="div#VideoEmbedThumb div")
		private WebElement videoThumbnail;
		@FindBy(css="div#VideoEmbedNameRow p")
		private WebElement videoNameCaption;
	
		public VetOptionsComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,  this);
	}

	/**
	 * for provider
	 */
	/**
	 * @param i
	 * i = 1; with caption
	 * i = 2; without caption 
	 */
	public void adjustStyle(int i){
		waitForTextToBePresentInElementByElement(videoOptionsHeader, "Video display options");
		switch (i){
		case 1: styleWithCation.click();
				PageObjectLogging.log("adjustStyle", "first style selected",  true);
				break;
		case 2: styleWithoutCaption.click();
				PageObjectLogging.log("adjustStyle", "second style selected",  true);
				break;
		default: PageObjectLogging.log("adjustStyle", "invalid style selected",  false);
		}
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
	
	/**
	 * author: Michal Nowierski
	 */
	public void adjustWith(int Width) {
		String width = Integer.toString(Width);
		waitForElementByElement(withInputField);
		withInputField.clear();
		withInputField.sendKeys(width);
		PageObjectLogging.log("adjustWith", "width set to: "+Width,  true, driver);
	}
	
	/**
	 * author: Michal Nowierski
	 */
	private void clickAddaVideo() {
		waitForElementByElement(addAvideo);
		clickAndWait(addAvideo);
		PageObjectLogging.log("clickAddaVideo", "add video button clicked",  true, driver);
	}
	
	private void clickRetunToEditing(){
		waitForElementByElement(returnToEditing);
		clickAndWait(returnToEditing);
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
	
	/**
	 * for provider
	 */
	/**
	 * @param i
	 * i = 1; left position
	 * i = 2; center position
	 * i = 3; right position 
	 */
	public void adjustPosition(int i){
		waitForElementByElement(videoEmbedLayotRow);
		switch (i){
		case 1: positionLayoutLeft.click();
				PageObjectLogging.log("adjustPosition", "left position selected",  true);
				break;
		case 2: positionLayoutCenter.click();
				PageObjectLogging.log("adjustPosition", "center position selected",  true);
				break;
		case 3: positionLayoutRight.click();
				PageObjectLogging.log("adjustPosition", "right position selected",  true);
				break;
		default: PageObjectLogging.log("adjustPosition", "invalid style selected",  false);
		}
	}
	
	
	/**
	 * @param i
	 * i = 1; left position
	 * i = 2; center position
	 * i = 3; right position
	 * 
	 * @author Rodrigo 'RodriGomez' Molinero
	 *  
	 */
	
	public void verifyAlignmentOptionIsSelected(int i){
		waitForElementByElement(videoEmbedLayotRow);
		switch (i){
		case 1: positionLayoutLeftSelected.click();
				PageObjectLogging.log("verifyAlignmentOptionIsSelected", "left position selected",  true);
				break;
		case 2: positionLayoutCenterSelected.click();
				PageObjectLogging.log("verifyAlignmentOptionIsSelected", "center position selected",  true);
				break;
		case 3: positionLayoutRightSelected.click();
				PageObjectLogging.log("verifyAlignmentOptionIsSelected", "right position selected",  true);
				break;
		default: PageObjectLogging.log("verifyAlignmentOptionIsSelected", "invalid alignment selected",  false);
		}
	}
	
	public void clickUpdateVideo() {
		waitForElementByElement(updateVideoButton);
		clickAndWait(updateVideoButton);
		PageObjectLogging.log("updateVideoButton", "update video button clicked",  true, driver);
	}
	
	
	public void verifyVideoWidthInVETOptionsModal() {
		waitForElementByElement(withInputField);
		Assertion.assertEquals("250", withInputField.getAttribute("value"));
		PageObjectLogging.log("verifyVideoWidthInVETOptionsModal", "Video width has the correct value set previously", true);
		
	}
	
	
	public void verifyCaptionInVETModal(String caption) {
		Assertion.assertStringContains("QAWebdriverCaption1", caption);
		PageObjectLogging.log("verifyCaptionInVETModal", "Verify that the caption of the video set previously appears in the VET modal", true, driver);
	}
	
	
	public void verifyNoCaptionInVETModal() {
		if (styleWithoutCaption.isSelected()){
			PageObjectLogging.log("verifyNoCaptionInVETModal", "Video with no caption is selected in VET modal", true);
			}
		else{
			PageObjectLogging.log("verifyNoCaptionInVETModal", "Video with caption is selected in VET modal", false);
		}
	}
	
	public void verifyVideoNameFieldIsNotEditable(){
		waitForElementNotVisibleByElement(uneditableVideoNameField);
		PageObjectLogging.log("verifyVideoNameFieldIsNotEditable", "Verified that Video Name Field is not editable",  true, driver);
	}
	
	public void verifyVideoNameFieldIsEditable(){
		waitForElementByElement(editableVideoNameField);
		PageObjectLogging.log("verifyVideoNameFieldIsEditable", "Verified that Video Name Field is editable",  true, driver);
	}
}
