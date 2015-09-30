package com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile;

import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

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
    File fileCheck = new File("." + File.separator + "src" + File.separator
                              + "test" + File.separator + "resources" + File.separator
                              + "ImagesForUploadTests"
                              + File.separator + file);
    if (!fileCheck.isFile()) {
      LOG.log("uploadAvatar", "the file doesn't exist", LOG.Type.ERROR);
    }
    uploadInput.sendKeys(fileCheck.getAbsoluteFile().toString());
    LOG
        .log("typeInFileToUploadPath", "type file " + file + " to upload it", true, driver);
  }

  public void saveProfile() {
    wait.forElementClickable(saveButton);
    scrollAndClick(saveButton);
    LOG.log("save", "save profile button clicked", LOG.Type.SUCCESS);
  }

}
