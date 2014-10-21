package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;

public class SpecialUploadPageObject extends WikiBasePageObject {


	@FindBy(css="#wpUploadFile")
	private WebElement browseForFileInput;
	@FindBy(css="#wpIgnoreWarning")
	private WebElement ignoreAnyWarnings;
	@FindBy(css="input.mw-htmlform-submit")
	private WebElement uploadFileInput;
	@FindBy(css="#wpDestFile")
	private WebElement uploadFileName;

	public SpecialUploadPageObject(WebDriver driver) {
		super(driver);
	}

	/**
	 * Selects given file in upload browser.
	 *
	 *
	 * @author Michal Nowierski
	 * ** @param file file to Be uploaded
	 * <p> Look at folder acceptancesrc/src/test/resources/ImagesForUploadTests - this is where those files are stored
	 *  */

	public void selectFileToUpload(String file){
		browseForFileInput.sendKeys(
				getAbsolutePathForFile(PageContent.resourcesPath + file)
		);
		PageObjectLogging.log(
				"typeInFileToUploadPath",
				"file " + file + " added to upload",
				true
		);

	}

	public void checkIgnoreAnyWarnings() {
		waitForElementClickableByElement(ignoreAnyWarnings);
		scrollAndClick(ignoreAnyWarnings);
		PageObjectLogging.log(
				"checkIgnoreAnyWarnings",
				"ignore warnings checkbox selected",
				true,
				driver
		);

	}

	public FilePagePageObject clickUploadButton() {
		scrollAndClick(uploadFileInput);
		PageObjectLogging.log("clickOnUploadFile", "upload file button clicked.", true);
		return new FilePagePageObject(driver);
	}

	public void typeFileName(String fileName) {
		uploadFileName.clear();
		uploadFileName.sendKeys(fileName);
		PageObjectLogging.log("typeFileName", fileName + " typed into file name field", true);
	}
}
