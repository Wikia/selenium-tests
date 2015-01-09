package com.wikia.webdriver.pageobjectsfactory.componentobject.gallery;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

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

	public enum PositionsGallery {
		LEFT, CENTER, RIGHT;

		private final String label;

		PositionsGallery() {
			this.label = this.toString().toLowerCase();
		}

		public String getPositionGallery() {
			return this.label;
		}
	}

	public enum SpacingGallery {
		SMALL, MEDIUM, LARGE;

		private final String label;

		SpacingGallery() {
			this.label = this.toString().toLowerCase();
		}

		public String getSpacingGallery() {
			return this.label;
		}
	}

	public enum Orientation{
		NONE, SQUARE, LANDSCAPE, PORTRAIT
	}

	public GalleryBuilderComponentObject(WebDriver driver) {
		super(driver);
	}

	public void adjustPosition(PositionsGallery positionGallery){
		waitForElementByElement(position);
		Select positionDropdown = new Select(position);
		positionDropdown.selectByValue(positionGallery.getPositionGallery());
	}

	/**
	 * @param columnsNo
	 * fit to page
	 * 1 - 6
	 */
	public void adjustColumns(String columnsNo){
		waitForElementByElement(columns);
		Select columnsDropdown = new Select(columns);
		columnsDropdown.selectByVisibleText(columnsNo);
	}

	public void adjustSpacing(SpacingGallery spacingGallery){
		waitForElementByElement(spacing);
		Select spacingDropdown = new Select(spacing);
		spacingDropdown.selectByValue(spacingGallery.getSpacingGallery());
	}


	public void adjustOrientation(Orientation orientionGallery){
		waitForElementByElement(orientation);
		switch(orientionGallery){
			case NONE:
				orientation.findElement(orintationNone);
				break;
			case SQUARE:
				orientation.findElement(orintationSquare);
				break;
			case LANDSCAPE:
				orientation.findElement(orintationLandscape);
				break;
			case PORTRAIT:
				orientation.findElement(orintationPortrait);
				break;
			default:
				throw new NoSuchElementException("Non-existing orientation selected");
		}
		PageObjectLogging.log("adjustOrientation", "dropdown selected", true);

	}

	public AddPhotoComponentObject clickAddPhoto(){
		waitForElementByElement(addPhotoButton);
		scrollAndClick(addPhotoButton);
		PageObjectLogging.log("clickAddPhoto", "add photo button clicked", true);
		return new AddPhotoComponentObject(driver);
	}

	public void verifyPhotosCount(int photos){
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

