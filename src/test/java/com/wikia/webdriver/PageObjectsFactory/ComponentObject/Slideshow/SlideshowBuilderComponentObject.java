package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto.AddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class SlideshowBuilderComponentObject extends BasePageObject{

	@FindBy(css="#WikiaPhotoGallerySlideshowAddImage")
	private WebElement addPhotoButton;
	@FindBy(css="#WikiaPhotoGalleryEditorSlideshowAlign")
	private WebElement slideshowPosition;
	@FindBy(css="#WikiaPhotoGalleryEditorSave")
	private WebElement finishButton;

	public SlideshowBuilderComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void adjustWidth(int width){
		//TODO
	}

	public void useSmartCropping(){
		//TODO
	}

	public enum Positions{
		Left, Center, Right
	}

	public void adjustPosition(Positions position){
		Select pos = new Select(slideshowPosition);
		pos.selectByVisibleText(position.toString());
		PageObjectLogging.log("adjustPosition", "slideshow position set to "+position.toString(), true);
	}

	public AddPhotoComponentObject clickAddPhoto(){
		waitForElementByElement(addPhotoButton);
		addPhotoButton.click();
		return new AddPhotoComponentObject(driver);
	}

	public void clickFinish(){
		waitForElementByElement(finishButton);
		finishButton.click();
		PageObjectLogging.log("clickFinish", "finish button clicked", true);
	}

}
