package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto.AddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class SliderBuilderComponentObject extends BasePageObject{

	@FindBy(css="#WikiaPhotoGallerySliderType_bottom")
	private WebElement hPosition;
	@FindBy(css="#WikiaPhotoGallerySliderType_right")
	private WebElement vPosition;
	@FindBy(css="#WikiaPhotoGallerySliderAddImage")
	private WebElement addPhotoButton;
	@FindBy(css="#WikiaPhotoGalleryEditorSave")
	private WebElement finishButton;
	
	public SliderBuilderComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public enum MenuPositions{
		Horizontal, Vertical
	}
	
	public void selectMenuPosition(MenuPositions pos){
		waitForElementByElement(hPosition);
		waitForElementByElement(vPosition);
		switch(pos){
		case Horizontal:
			hPosition.click();
			break;
		case Vertical:
			vPosition.click();
			break;
		}
		PageObjectLogging.log("selectMenuPosition", pos.toString()+" position selected", true, driver);
	}
	
	public AddPhotoComponentObject clickAddPhoto(){
		waitForElementByElement(addPhotoButton);
		addPhotoButton.click();
		PageObjectLogging.log("addPhoto", "add photo button clicked", true);
		return new AddPhotoComponentObject(driver);
	}
	
	public void clickFinish(){
		waitForElementByElement(finishButton);
		finishButton.click();
		PageObjectLogging.log("clickFinish", "finish button clicked", true);
	}

}
