package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.FilePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class SpecialUploadPageObject extends WikiBasePageObject {


	@FindBy(css="input[name='wpUploadFile']")
	private WebElement BrowseForFileInput;
	@FindBy(css="input[name='wpIgnoreWarning']")
	private WebElement IgnoreAnyWarnings;
	@FindBy(css="input.mw-htmlform-submit[value*='Upload']")
	private WebElement UploadFileInput;

	public SpecialUploadPageObject(WebDriver driver) {
		super(driver);

		PageFactory.initElements(driver, this);
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
		sendKeys(BrowseForFileInput, System.getProperty("user.dir")+"\\src\\test\\resources\\ImagesForUploadTests\\"+file);
		PageObjectLogging.log("TypeInFileToUploadPath", "Type file "+file+" to Special:Upload upload path", true, driver);

	}

	public void checkIgnoreAnyWarnings() {
		waitForElementByElement(IgnoreAnyWarnings);
		scrollAndClick(IgnoreAnyWarnings);
		PageObjectLogging.log("CheckIgnoreAnyWarnings", "Check 'Ignore Any Warnings' option", true, driver);

	}

	public FilePageObject clickOnUploadFile(String file) {
		waitForElementByElement(UploadFileInput);
		scrollAndClick(UploadFileInput);
		PageObjectLogging.log("ClickOnUploadFile", "Click on Upload file button. The method returns FilePageObject", true, driver);
		return new FilePageObject(driver, file);
	}




}
