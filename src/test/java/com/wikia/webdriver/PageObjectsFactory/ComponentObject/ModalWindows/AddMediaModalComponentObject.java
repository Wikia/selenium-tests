package com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    private WebElement addVideoModal;

    public AddMediaModalComponentObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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
