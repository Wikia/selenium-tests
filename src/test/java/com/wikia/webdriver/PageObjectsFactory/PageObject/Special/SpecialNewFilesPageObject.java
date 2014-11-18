package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Lightbox.LightboxComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialNewFilesPageObject extends SpecialPageObject {

	@FindBy(css = "a.upphotos[title*='Add a photo']")
	private WebElement addPhotoButton;
	@FindBy(css="input[name='wpUploadFile']")
	private WebElement browseForFileInput;
	@FindBy(css="div.step-1 input[value*='Upload']")
	private WebElement uploadFileInput;
	@FindBy(css="div.advanced a")
	private WebElement moreOrFewerOptions;
	@FindBy(css="div.toggles input[name='wpIgnoreWarning']")
	private WebElement ignoreAnyWarnings;
	@FindBy(css="section[id='UploadPhotosWrapper']")
	private WebElement uploadPhotoDialog;
	@FindBy(css="div.wikia-gallery div.wikia-gallery-item img")
	private WebElement wikiaPreviewImg;
	@FindBys(@FindBy(css="#mw-content-text img"))
	private List<WebElement> imagesNewFiles;
	@FindBy(css = ".wikia-gallery div.wikia-gallery-item a.image")
	private List<WebElement> galleryImageBox;

	public SpecialNewFilesPageObject(WebDriver driver) {
		super(driver);
	}

	public void addPhoto() {
		scrollAndClick(addPhotoButton);
		PageObjectLogging.log(
			"ClickAddPhotoButton",
			"Add photo button clicked",
			true
		);
	}

	public void clickUploadButton() {
		scrollAndClick(uploadFileInput);
		PageObjectLogging.log(
			"ClickOnUploadaPhoto",
			"Click on upload a photo button",
			true
		);
	}

	public void clickOnMoreOrFewerOptions() {
		scrollAndClick(moreOrFewerOptions);
		PageObjectLogging.log(
			"ClickOnMoreOrFewerOptions",
			"Click on More or Fewer options (depends on which of those two is currently visible)",
			true
		);
	}

	public void checkIgnoreAnyWarnings() {
		waitForElementByElement(ignoreAnyWarnings);
		scrollAndClick(ignoreAnyWarnings);
		PageObjectLogging.log(
			"CheckIgnoreAnyWarnings",
			"Check 'Ignore Any Warnings' option",
			true
		);
	}

	public void selectFileToUpload(String file) {
		browseForFileInput.sendKeys(
				getAbsolutePathForFile(PageContent.RESOURCES_PATH + file)
		);
		PageObjectLogging.log("typeInFileToUploadPath", "type file " + file + " to upload it", true);
	}

	public void verifyFileUploaded(String fileName) {
		driver.navigate().refresh();
		waitForValueToBePresentInElementsAttributeByElement(
				wikiaPreviewImg,
				"src",
				fileName);
		PageObjectLogging.log(
			"waitForFile",
			"Verify if " + fileName + " has been succesfully uploaded",
			true
		);
	}

	/**
	 * @return name of random image on Special:NewFiles page
	 */
	public String getRandomImageName() {
		List<String> hrefs = new ArrayList<String>();
		for (WebElement elem:imagesNewFiles) {
			hrefs.add(elem.getAttribute("data-image-name"));
		}
		Random r = new Random();
		return hrefs.get((r.nextInt(hrefs.size()-1))+1);
	}

	/**
	 * @return url of random image on Special:NewFiles page
	 */
	public String getRandomImageUrl() {
		List<String> hrefs = new ArrayList<String>();
		for (WebElement elem:imagesNewFiles) {
			hrefs.add(elem.findElement(parentBy).getAttribute("href"));
		}
		Random r = new Random();
		String href = hrefs.get((r.nextInt(hrefs.size()-1))+1);
		PageObjectLogging.log("getRandomImageUrl", href + " image is selected", true);
		return href;
	}

	public String getImageUrl(String imageName) {
		for (WebElement elem:imagesNewFiles) {
			String href = elem.findElement(parentBy).getAttribute("href");
			if (href.contains(imageName)){
				return href;
			}
		}
		throw new NoSuchElementException("there is no " + imageName + " on Special:NewFiles page");
	}

	public FilePagePageObject openImage(String imageName) {
		driver.get(getImageUrl(imageName));
		return new FilePagePageObject(driver);
	}

	public FilePagePageObject openRandomImage() {
		driver.get(
				getRandomImageUrl()
		);
		return new FilePagePageObject(driver);
	}

	public WatchPageObject unfollowImage(String wikiURL, String imageName) {
		String url = urlBuilder.appendQueryStringToURL(
				wikiURL +
				URLsContent.WIKI_DIR +
				URLsContent.FILE_NAMESPACE +
				imageName,
				URLsContent.ACTION_UNFOLLOW
		);
		getUrl(url);
		return new WatchPageObject(driver);
	}

	public LightboxComponentObject openLightbox(int itemNumber) {
		scrollAndClick(galleryImageBox.get(itemNumber));
		return new LightboxComponentObject(driver);
	}

	public String getFileUrl(String wikiURL, int itemNumber) {
		String fileUrl = wikiURL + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + getImageKey(itemNumber);
		PageObjectLogging.log("getFileUrl", "File url: " + fileUrl, true);
		return fileUrl;
	}

	public String getImageKey(int itemNumber) {
		String imageKey = imagesNewFiles.get(itemNumber).getAttribute("data-image-key");
		PageObjectLogging.log("getImageKey", "Image key: " + imageKey, true);
		return imageKey;
	}

}
