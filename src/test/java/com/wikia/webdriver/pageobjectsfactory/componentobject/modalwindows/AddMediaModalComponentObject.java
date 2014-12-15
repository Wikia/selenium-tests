package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class AddMediaModalComponentObject extends WikiBasePageObject {

	@FindBy(css = "#UploadPhotosWrapper")
	private WebElement addPhotoModal;
	@FindBy(css = "#UploadPhotosWrapper .close")
	private WebElement modalAddPhotoClose;
	@FindBy (css = "#VideoEmbedBackWrapper button.close")
	private WebElement modalAddVideoClose;
	@FindBy (css = "#VideoEmbedBackWrapper")
	protected WebElement addVideoModal;

	public AddMediaModalComponentObject(WebDriver driver) {
		super(driver);
	}

	public void closeAddPhotoModal() {
		waitForElementVisibleByElement(addPhotoModal);
		PageObjectLogging.log(
			"UploadPhotoModalIsPresent",
			"Upload photo modal is present",
			true, driver
		);
		scrollAndClick(modalAddPhotoClose);
		waitForElementNotVisibleByElement(addPhotoModal);
		PageObjectLogging.log(
			"UploadPhotoModalClosed",
			"Upload photo modal is closed",
			true, driver
		);
	}

	public void closeAddVideoModal() {
		waitForElementVisibleByElement(addVideoModal);
		PageObjectLogging.log(
			"UploadVideoModalIsPresent",
			"Upload video modal is present",
			true, driver
		);
		scrollAndClick(modalAddVideoClose);
		waitForElementNotVisibleByElement(addVideoModal);
		PageObjectLogging.log(
			"UploadVideoModalClosed",
			"Upload video modal is closed",
			true, driver
		);
	}
}
