package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class SpecialMultipleUploadPageObject extends WikiBasePageObject {

	@FindBy(css="input[name='wpIgnoreWarning']")
	private WebElement ignoreAnyWarnings;
	@FindBy(css="input[name='wpUpload']")
	private WebElement uploadFileButton;
	@FindBy(css="form[id='mw-upload-form']")
	private WebElement multipleUploadForm;
	@FindBy(css="#wpUploadDescription")
	private WebElement multipleUploadSummaryField;
	@FindBy(css="tr.mw-htmlform-field-UploadSourceField td.mw-input input")
	private List<WebElement> fileInputs;

	private By uploadedFilesListContener = By.cssSelector("div[id='mw-content-text']");
	private By uploadedFilesList = By.cssSelector("div[id='mw-content-text'] ul li a");

	public SpecialMultipleUploadPageObject(WebDriver driver) {
		super(driver);
	}

	/**
	 * Selects given files in upload browser.
	 *
	 *
	 * @author Michal Nowierski
	 * ** @param FilesNamesList List of files to be uploaded
	 * <p> Look at folder acceptancesrc/src/test/resources/ImagesForUploadTests - this is where those files are stored
	 *  */
	public void typeInFilesToUpload(String[] filesNamesList) {
		waitForElementByElement(multipleUploadForm);
		for (int i = 0; i < filesNamesList.length; i++) {
			scrollToElement(fileInputs.get(i));
			fileInputs.get(i).sendKeys(getAbsolutePath(PageContent.resourcesPath + filesNamesList[i]));
		}
		PageObjectLogging.log("typeInFilesToUpload", "Upload " + filesNamesList.length + " files, specified in FilesNamesList", true);
	}


	public void typeInMultiUploadSummary(String summary){
		multipleUploadSummaryField.sendKeys(summary);
		PageObjectLogging.log("typeInMultiUploadSummary", "summary: "+summary+" added to multiupload", true, driver);
	}

	public void checkIgnoreAnyWarnings() {
		scrollAndClick(ignoreAnyWarnings);
		PageObjectLogging.log("CheckIgnoreAnyWarnings", "Check 'Ignore Any Warnings' option", true, driver);

	}

	public void clickOnUploadFile() {
		scrollAndClick(uploadFileButton);
		PageObjectLogging.log("ClickOnUploadFile", "Click on Upload File button", true, driver);

	}

	/**
	 * Checks if the upload have been succesful.
	 * <p> The method checks if the uploaded files correspond to those in FilesNamesList. FFilesNamesList is a parameter of the method
	 *
	 *  @author Michal Nowierski
	 ** @param filesNamesList list of expected names of files
	 */
	public void verifySuccessfulUpload(String[] filesNamesList) {
		waitForElementByBy(uploadedFilesListContener);

		List<WebElement> UploadedFileslist = driver.findElements(uploadedFilesList);
		for (int i = 0; i < filesNamesList.length; i++) {
			if (!UploadedFileslist.get(i).getText().contains(filesNamesList[i])) {
				PageObjectLogging.log("VerifySuccessfulUpload", "the uploaded list element number "+(i+1)+"does not contain expected string: "+filesNamesList[i]+"<br> The element Text is: "+UploadedFileslist.get(i).getText(), false, driver);
							}
		}
		PageObjectLogging.log("VerifySuccessfulUpload", "Verify that the upload was succesful by checking the list of uploaded files", true, driver);

	}


}
