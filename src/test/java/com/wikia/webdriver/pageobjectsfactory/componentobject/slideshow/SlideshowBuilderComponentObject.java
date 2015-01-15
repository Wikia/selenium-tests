package com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class SlideshowBuilderComponentObject extends BasePageObject {

	@FindBy(css = "#WikiaPhotoGallerySlideshowAddImage")
	private WebElement addPhotoButton;
	@FindBy(css = "#WikiaPhotoGalleryEditorSlideshowAlign")
	private WebElement slideshowPosition;
	@FindBy(css = "#WikiaPhotoGalleryEditorSave")
	private WebElement finishButton;

	public SlideshowBuilderComponentObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void adjustWidth(int width) {
		//TODO
	}

	public void useSmartCropping() {
		//TODO
	}

	public enum Positions {
		LEFT, CENTER, RIGHT;

		private final String label;

		Positions() {
			this.label = StringUtils.capitalize(this.toString().toLowerCase());
		}

		public String getPosition() {
			return this.label;
		}
	}

	public void adjustPosition(Positions position) {
		Select pos = new Select(slideshowPosition);
		pos.selectByVisibleText(position.getPosition());
		PageObjectLogging.log("adjustPosition", "slideshow position set to " + position.getPosition(), true);
	}

	public AddPhotoComponentObject clickAddPhoto() {
		waitForElementByElement(addPhotoButton);
		addPhotoButton.click();
		return new AddPhotoComponentObject(driver);
	}

	public void clickFinish() {
		waitForElementByElement(finishButton);
		finishButton.click();
		PageObjectLogging.log("clickFinish", "finish button clicked", true);
	}

}
