package com.wikia.webdriver.pageobjectsfactory.componentobject.slider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

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
		HORIZONTAL, VERTICAL
	}

	public void selectMenuPosition(MenuPositions pos){
		waitForElementByElement(hPosition);
		waitForElementByElement(vPosition);
		switch(pos){
		case HORIZONTAL:
			hPosition.click();
			break;
		case VERTICAL:
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
