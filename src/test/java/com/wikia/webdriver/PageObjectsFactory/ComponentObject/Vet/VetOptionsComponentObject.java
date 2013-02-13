package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bsh.Parser;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

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
		@FindBy(css=".vet-style-label.VideoEmbedNoThumbOption")
		private WebElement styleWithoutCaption;
		@FindBy(css="#VideoEmbedName")
		private WebElement videoName;
		@FindBy(css="div.addVideoDetailsFormControls input")
		private WebElement addAvideo;
		@FindBy(css="#VideoEmbedCloseButton")
		private WebElement returnToEditing;
	
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
	
	public void setCaption(String caption){
		waitForElementByElement(captionField);
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
	
	public void submit(){
		clickAddaVideo();
		clickRetunToEditing();
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
}
