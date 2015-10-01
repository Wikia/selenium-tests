package com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class AvatarComponentObject extends EditProfileComponentObject {

  @FindBy(css = "#UPPLightboxAvatar")
  private WebElement uploadInput;
  @FindBy(css = "#UPPLightboxWrapper [data-event=save]")
  private WebElement saveButton;

  public AvatarComponentObject(WebDriver driver) {
    super(driver);
  }

  public void uploadAvatar(String file) {
    File fileCheck =
        new File("." + File.separator + "src" + File.separator + "test" + File.separator
            + "resources" + File.separator + "ImagesForUploadTests" + File.separator + file);
    if (!fileCheck.isFile()) {
      LOG.error("uploadAvatar", "the file doesn't exist");
    }
    uploadInput.sendKeys(fileCheck.getAbsoluteFile().toString());
    LOG.success("typeInFileToUploadPath", "type file " + file + " to upload it", true);
  }

  public void saveProfile() {
    wait.forElementClickable(saveButton);
    scrollAndClick(saveButton);
    LOG.success("save", "save profile button clicked");
  }

}
