package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet;

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
		@FindBy(css="#VideoEmbedHeader")
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
		private WebElement PositionLayoutLeft;
		@FindBy(css="#VET_LayoutCenterBox label")
		private WebElement PositionLayoutCenter;
		@FindBy(css="#VET_LayoutRightBox label")
		private WebElement PositionLayoutRight;
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
		private WebElement PositionLayoutLeftSelected;
		@FindBy(css="span#VET_LayoutRightBox.selected")
		private WebElement PositionLayoutRightSelected;
		@FindBy(css="span#VET_LayoutCenterBox.selected")
		private WebElement PositionLayoutCenterSelected;
		
		@FindBy(css="input[type='hidden'][id='VideoEmbedName']")
		private WebElement uneditableVideoNameField;
		@FindBy(css="input[type='text'][id='VideoEmbedName']")
		private WebElement editableVideoNameField;
		
		
		
		
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
	public void setTitle(String title) {
		waitForElementByElement(videoName);
		videoName.clear();
		videoName.sendKeys(title);
		PageObjectLogging.log("setTitle", "set title of the viedo to: "+title,  true, driver);
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
	
	public WikiArticleEditMode submit(){
		clickAddaVideo();
		clickRetunToEditing();
		return new WikiArticleEditMode(driver, Domain, Domain);
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
		case 1: PositionLayoutLeft.click();
				PageObjectLogging.log("adjustPosition", "left position selected",  true);
				break;
		case 2: PositionLayoutCenter.click();
				PageObjectLogging.log("adjustPosition", "center position selected",  true);
				break;
		case 3: PositionLayoutRight.click();
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
		case 1: PositionLayoutLeftSelected.click();
				PageObjectLogging.log("verifyAlignmentOptionIsSelected", "left position selected",  true);
				break;
		case 2: PositionLayoutCenterSelected.click();
				PageObjectLogging.log("verifyAlignmentOptionIsSelected", "center position selected",  true);
				break;
		case 3: PositionLayoutRightSelected.click();
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
		if (styleWithoutCaption.isSelected())
		{
			PageObjectLogging.log("verifyNoCaptionInVETModal", "Video with no caption is selected in VET modal", true);
			}
		else
			{
			PageObjectLogging.log("verifyNoCaptionInVETModal", "Video with caption is selected in VET modal", false);
		}	
				
	}
	
	public void verifyVideoNameFieldIsNotEditable(){
		waitForElementByElement(uneditableVideoNameField);
		PageObjectLogging.log("verifyVideoNameFieldIsNotEditable", "Verified that Video Name Field is not editable",  true, driver);
	}
	
	public void verifyVideoNameFieldIsEditable(){
		waitForElementByElement(editableVideoNameField);
		PageObjectLogging.log("verifyVideoNameFieldIsEditable", "Verified that Video Name Field is editable",  true, driver);
	}
}
