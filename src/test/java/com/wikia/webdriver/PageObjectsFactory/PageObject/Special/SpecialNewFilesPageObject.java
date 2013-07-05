package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialNewFilesPageObject extends SpecialPageObject {

    @FindBy(css = "a.upphotos[title*='Add a photo']")
    private WebElement addPhotoButton;
    @FindBy(css="input[name='wpUploadFile']")
    private WebElement BrowseForFileInput;
    @FindBy(css="div.step-1 input[value*='Upload']")
    private WebElement UploadFileInput;
    @FindBy(css="div.advanced a")
    private WebElement MoreOrFewerOptions;
    @FindBy(css="div.toggles input[name='wpIgnoreWarning']")
    private WebElement IgnoreAnyWarnings;
    @FindBy(css="section[id='UploadPhotosWrapper']")
    private WebElement UploadPhotoDialog;
    @FindBys(@FindBy(css=".wikia-gallery .image"))
    private List<WebElement> imagesNewFiles;

    //Selectors
    private String addPhotoModalSelector = "#UploadPhotosWrapper";
    private String WikiaPreviewImgCssSelector = "div.wikia-gallery span.wikia-gallery-item img";

    public SpecialNewFilesPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addPhoto() {
        waitForElementByElement(addPhotoButton);
        clickAndWait(addPhotoButton);
        PageObjectLogging.log(
            "ClickAddPhotoButton",
            "Add photo button clicked",
            true
        );
    }

    public void clickOnUploadaPhoto() {
        waitForElementByElement(UploadFileInput);
        clickAndWait(UploadFileInput);
        PageObjectLogging.log(
            "ClickOnUploadaPhoto",
            "Click on upload a photo button",
            true, driver
        );
    }

    public void clickOnMoreOrFewerOptions() {
        waitForElementByElement(MoreOrFewerOptions);
        clickAndWait(MoreOrFewerOptions);
        PageObjectLogging.log(
            "ClickOnMoreOrFewerOptions",
            "Click on More or Fewer options (depends on which of those two is currently visible)",
            true, driver
        );
    }

    public void checkIgnoreAnyWarnings() {
        waitForElementByElement(IgnoreAnyWarnings);
        clickAndWait(IgnoreAnyWarnings);
        PageObjectLogging.log(
            "CheckIgnoreAnyWarnings",
            "Check 'Ignore Any Warnings' option",
            true, driver
        );
    }

    /**
     * Selects given image in upload browser.
     *
     * @author Michal Nowierski
     * @param file file to Be uploaded
     * <p> Look at folder acceptancesrc/src/test/resources/ImagesForUploadTests
     * - this is where those files are stored
     */
    public void typeInFileToUploadPath(String file){    
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
	sendKeys(
            BrowseForFileInput,fileCheck.getAbsoluteFile().toString()
        );
	PageObjectLogging.log("typeInFileToUploadPath", "type file "+file+" to upload it", true, driver);
    }

    public void waitForFile(String FileName) {
        driver.navigate().refresh();
        waitForValueToBePresentInElementsAttributeByCss(
            WikiaPreviewImgCssSelector, "src", FileName
        );
        PageObjectLogging.log(
            "waitForFile",
            "Verify if "+FileName+" has been succesfully uploaded",
            true, driver
        );
    }

    public String followRandomImage(){
        List<String> hrefs = new ArrayList<String>();
        for (WebElement elem:imagesNewFiles) {
            hrefs.add(elem.getAttribute("href"));
        }
        Random r = new Random();
        String imageName = hrefs.get((r.nextInt(hrefs.size()-1))+1);
		unfollowImage(imageName);
        getUrl(imageName+"?action=watch");
        clickAndWait(followSubmit);
        waitForElementByElement(followedButton);
        PageObjectLogging.log("followRandomImage", "folow image named "+imageName, true);
        return imageName;
    }

    public void unfollowImage(String imageName){
        getUrl(imageName+"?action=unwatch");
        clickAndWait(followSubmit);
        waitForElementByElement(unfollowedButton);
    }
}
