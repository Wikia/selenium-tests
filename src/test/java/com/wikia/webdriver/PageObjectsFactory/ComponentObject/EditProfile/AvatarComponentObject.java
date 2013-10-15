package com.wikia.webdriver.PageObjectsFactory.ComponentObject.EditProfile;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class AvatarComponentObject extends EditProfileComponentObject {

	@FindBy(css="#UPPLightboxAvatar")
	private WebElement uploadInput;
	@FindBy(css=".modalToolbar > .save")
	private WebElement saveButton;

	public AvatarComponentObject(WebDriver driver) {
		super(driver);
	}

	public void uploadAvatar(String file) {
		File fileCheck = new File("." + File.separator + "src" + File.separator
				+ "test" + File.separator + "resources" + File.separator + "ImagesForUploadTests"
				+ File.separator + file);
		if (!fileCheck.isFile()) {
			try {
				throw new Exception("the file doesn't exist");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		uploadInput.sendKeys(fileCheck.getAbsoluteFile().toString());
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true, driver);
	}

	public void saveProfile() {
		saveButton.click();
		PageObjectLogging.log("save", "save profile button clicked", true);
	}

}
