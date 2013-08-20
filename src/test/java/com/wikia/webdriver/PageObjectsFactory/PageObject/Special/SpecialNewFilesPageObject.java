package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

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
	@FindBys(@FindBy(css="#mw-content-text img"))
	private List<WebElement> imagesNewFiles;

	private String WikiaPreviewImgCssSelector = "div.wikia-gallery div.wikia-gallery-item img";

	public SpecialNewFilesPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void addPhoto() {
		waitForElementByElement(addPhotoButton);
		scrollAndClick(addPhotoButton);
		PageObjectLogging.log(
			"ClickAddPhotoButton",
			"Add photo button clicked",
			true
		);
	}

	public void clickOnUploadaPhoto() {
		waitForElementByElement(UploadFileInput);
		scrollAndClick(UploadFileInput);
		PageObjectLogging.log(
			"ClickOnUploadaPhoto",
			"Click on upload a photo button",
			true,
			driver
		);
	}

	public void clickOnMoreOrFewerOptions() {
		waitForElementByElement(MoreOrFewerOptions);
		scrollAndClick(MoreOrFewerOptions);
		PageObjectLogging.log(
			"ClickOnMoreOrFewerOptions",
			"Click on More or Fewer options (depends on which of those two is currently visible)",
			true,
			driver
		);
	}

	public void checkIgnoreAnyWarnings() {
		waitForElementByElement(IgnoreAnyWarnings);
		scrollAndClick(IgnoreAnyWarnings);
		PageObjectLogging.log(
			"CheckIgnoreAnyWarnings",
			"Check 'Ignore Any Warnings' option",
			true,
			driver
		);
	}

	public void typeInFileToUploadPath(String file) {
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
			WikiaPreviewImgCssSelector,
			"src",
			FileName
		);
		PageObjectLogging.log(
			"waitForFile",
			"Verify if "+FileName+" has been succesfully uploaded",
			true,
			driver
		);
	}

	/**
	 * @return name of random image on Special:NewFiles page
	 */
	public String getRandomImage() {
		List<String> hrefs = new ArrayList<String>();
		for (WebElement elem:imagesNewFiles) {
			hrefs.add(elem.getAttribute("data-image-name"));
		}
		Random r = new Random();
		return hrefs.get((r.nextInt(hrefs.size()-1))+1);
	}

	public WatchPageObject unfollowImage(String wikiURL, String imageName) {
		String url = URLsContent.buildUrl(
				wikiURL +
				URLsContent.wikiDir +
				URLsContent.fileNameSpace +
				imageName,
				URLsContent.unfollowParameter
		);
		getUrl(url);
		return new WatchPageObject(driver);
	}
}
