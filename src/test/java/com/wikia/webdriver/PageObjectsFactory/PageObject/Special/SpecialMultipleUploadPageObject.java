package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
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
	@FindBy(css="div[id='mw-content-text'] ul li a")
	private List<WebElement> uploadedFileslist;
	@FindBy(css="div[id='mw-content-text']")
	private WebElement uploadedFilesListContener;

	public SpecialMultipleUploadPageObject(WebDriver driver) {
		super(driver);
	}

	/**
	 * Selects given files in upload browser.
	 *
	 *
	 * @author Michal Nowierski
	 * @author Karol 'kkarolk' Kujawiak
	 * ** @param FilesNamesList List of files to be uploaded
	 * <p> Look at folder PageContent.resourcesPath
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
	 *  @author Karol 'kkarolk' Kujawiak
	 ** @param filesNamesList list of expected names of files
	 */
	public void verifySuccessfulUpload(String[] filesNamesList) {
		waitForElementByElement(uploadedFilesListContener);
		for (int i = 0; i < filesNamesList.length; i++) {
			Assertion.assertStringContains(uploadedFileslist.get(i).getText(), filesNamesList[i]);
		}
	}
}
