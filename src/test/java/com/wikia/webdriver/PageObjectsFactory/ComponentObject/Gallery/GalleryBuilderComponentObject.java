package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

/**
 * 
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class GalleryBuilderComponentObject extends BasePageObject{

	@FindBy(css="#WikiaPhotoGalleryAddImage")
	private WebElement addPhotoButton;
	@FindBy(css="#WikiaPhotoGalleryEditorSave")
	private WebElement finishButton;
	@FindBys(@FindBy(css="#WikiaPhotoGalleryEditorPreview a.image"))
	private List<WebElement> galleryPreviewPhotos;
	@FindBy(css="select#WikiaPhotoGalleryEditorGalleryPosition")
	private WebElement position;
	@FindBy(css="select#WikiaPhotoGalleryEditorGalleryColumns")
	private WebElement columns;
	@FindBy(css="select#WikiaPhotoGalleryEditorGalleryImageSpacing")
	private WebElement spacing;
	@FindBy(css="ul#WikiaPhotoGalleryOrientation")
	private WebElement orientation;
	
	private By orintationNone = By.cssSelector("[id*='none']");
	private By orintationSquare = By.cssSelector("[id*='square']");
	private By orintationLandscape = By.cssSelector("[id*='landscape']");
	private By orintationPortrait = By.cssSelector("[id*='portrait']");

	
	public GalleryBuilderComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void adjustSize(int size){
		//TODO
	}
	
	/**
	 * @param positionNo
 	 * Left
	 * Center
	 * Right
	 */
	public void adjustPosition(String positionNo){
		waitForElementByElement(position);
		Select positionDropdown = new Select(position);
		positionDropdown.selectByVisibleText(positionNo);
	}
	
	/**
	 * @param columnsNo
	 * fit to page
	 * 1
	 * 2
	 * 3
	 * 4
	 * 5
	 * 6
	 */
	public void adjustColumns(String columnsNo){
		waitForElementByElement(columns);
		Select columnsDropdown = new Select(columns);
		columnsDropdown.selectByVisibleText(columnsNo);
	}
	
	/**
	 * 0 - small
	 * 1 - medium
	 * 2 - large
	 * @param spacingNo
	 */
	public void adjustSpacing(String spacingNo){
		waitForElementByElement(spacing);
		Select spacingDropdown = new Select(spacing);
		spacingDropdown.selectByVisibleText(spacingNo);
	}
	
	public enum Orientation{
		none, square, landscape, portrait
	}
	
	/**
	 * 0 - none
	 * 1 - square
	 * 2 - landscape
	 * 3 - portrait
	 * @param orientationNo
	 */
	public void adjustOrientation(Orientation orient){
		waitForElementByElement(orientation);
		switch(orient){
		case none: 
			orientation.findElement(orintationNone);
			break;
		case square:
			orientation.findElement(orintationSquare);
			break;
		case landscape:
			orientation.findElement(orintationLandscape);
			break;
		case portrait:
			orientation.findElement(orintationPortrait);
			break;
		}
		PageObjectLogging.log("adjustOrientation", "dropdown selected", true);
		
	}
	
	public GalleryAddPhotoComponentObject clickAddPhoto(){
		waitForElementByElement(addPhotoButton);
		clickAndWait(addPhotoButton);
		PageObjectLogging.log("clickAddPhoto", "add photo button clicked", true);
		return new GalleryAddPhotoComponentObject(driver);
	}
	
	public void verifyPhotosVisible(int photos){
		for (int i=0; i<photos; i++){
			waitForElementByElement(galleryPreviewPhotos.get(i));
			PageObjectLogging.log("verifyPhotosVisible", "photo no. "+i+1+"/photos is visible", true);
		}
	}
	
	public void clickFinish(){
		waitForElementByElement(finishButton);
		finishButton.click();
		PageObjectLogging.log("clickFinish", "finish button clicked", true);
	}
}

