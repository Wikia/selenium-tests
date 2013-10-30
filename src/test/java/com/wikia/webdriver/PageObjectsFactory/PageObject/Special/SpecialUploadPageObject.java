package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;

public class SpecialUploadPageObject extends WikiBasePageObject {


	@FindBy(css="input[name='wpUploadFile']")
	private WebElement browseForFileInput;
	@FindBy(css="input[name='wpIgnoreWarning']")
	private WebElement ignoreAnyWarnings;
	@FindBy(css="input.mw-htmlform-submit[value*='Upload']")
	private WebElement uploadFileInput;

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

	public void typeInFileToUploadPath(String file){
		sendKeys(browseForFileInput, getAbsolutePath(PageContent.resourcesPath + file));
		PageObjectLogging.log("typeInFileToUploadPath", "Type file "+file+" to Special:Upload upload path", true);

	}

	public void checkIgnoreAnyWarnings() {
		scrollAndClick(ignoreAnyWarnings);
		PageObjectLogging.log("checkIgnoreAnyWarnings", "Check 'Ignore Any Warnings' option", true, driver);

	}

	public FilePagePageObject clickOnUploadFile() {
		scrollAndClick(uploadFileInput);
		PageObjectLogging.log("clickOnUploadFile", "Click on Upload file button. The method returns FilePageObject", true, driver);
		return new FilePagePageObject(driver);
	}




}
