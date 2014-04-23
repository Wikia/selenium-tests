package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class SpecialPromotePageObject extends BasePageObject {

	@FindBy(css="#curMainImageName")
	protected WebElement thumbnailImage;
	@FindBy(css=".description-wrapper textarea")
	protected WebElement wikiaDescription;
	@FindBy(css=".headline-wrapper input")
	protected WebElement wikiaHeadline;
	@FindBy(css=".wikia-button.upload-button")
	protected WebElement addPhotoButton;
	@FindBy(css=".modify-remove.show")
	protected WebElement modifyThumbnailButton;
	@FindBy(css=".button.normal.primary")
	protected WebElement submitButton;
	@FindBy(css=".button.big")
	protected WebElement publishButton;
	@FindBy(css="input[name='wpUploadFile']")
	protected WebElement uploadFileInput;


	public SpecialPromotePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickPublishButton(){
		waitForElementByElement(publishButton);
		scrollAndClick(publishButton);
		PageObjectLogging.log("clickPublishButton", "publish button click", true);
	}

	public String getUniqueThumbnailTextSpecialPromotePage() {
		int indexComparisonStart = thumbnailImage.getAttribute("src").indexOf("px-");
		int indexComparisonFinish = thumbnailImage.getAttribute("src").indexOf("-Wikia-Visualization-Main");
		return thumbnailImage.getAttribute("src").substring(indexComparisonStart + 3, indexComparisonFinish - 1);
	}

	public void modifyThumnailImage(String file){
		waitForElementByElement(thumbnailImage);
		scrollAndClick(thumbnailImage);
		waitForElementByElement(modifyThumbnailButton);
		modifyThumbnailButton.click();
		waitForElementByElement(uploadFileInput);
		uploadFileInput.sendKeys(
			getAbsolutePathForFile(PageContent.resourcesPath + file)
			);
		PageObjectLogging.log(
			"modifyThumnailImage",
			"file " + file + " added to upload",
			true);
	}

	public void typeIntoHeadline(String text){
		waitForElementByElement(wikiaHeadline);
		wikiaHeadline.clear();
		wikiaHeadline.sendKeys(text);
		PageObjectLogging.log(
			"typeIntoHeadline",
			"text " + text + " typed into headline",
			true);
	}

	public void typeIntoDescription(String text){
		waitForElementByElement(wikiaDescription);
		wikiaDescription.clear();
		wikiaDescription.sendKeys(text);
		PageObjectLogging.log(
			"typeIntoDescription",
			"text " + text + " typed into description",
			true);
	}

	public void uploadThumbnailImage(String file){
		waitForElementByElement(addPhotoButton);
		scrollAndClick(addPhotoButton);
		waitForElementByElement(uploadFileInput);
		uploadFileInput.sendKeys(
			getAbsolutePathForFile(PageContent.resourcesPath + file)
			);
		PageObjectLogging.log(
			"uploadThumbnailImage",
			"file " + file + " added to upload",
			true);
		waitForElementByElement(submitButton);
		submitButton.click();
	}

	public void verifyCrossWikiSearchDescription(String firstDescription) {
		waitForElementByElement(wikiaDescription);
		Assertion.assertStringContains(
			wikiaDescription.getText(),
			firstDescription.substring(0,
			firstDescription.length()-3)
		);
	}

	public void verifyCrossWikiSearchImage(String firstImage) {
		waitForElementByElement(thumbnailImage);
		String secondImage = getUniqueThumbnailTextSpecialPromotePage();
		Assertion.assertEquals(firstImage, secondImage);
	}

	public void verifyUploadedImage(String fileName) {
		File expectedImageFile = new File(PageContent.resourcesPath + fileName);
		File actualImageFile = getUploadedImage();
		ImageComparison comparer = new ImageComparison();
		Boolean ifEqual = comparer.compareImagesBasedOnBytes(expectedImageFile, actualImageFile);
		actualImageFile.delete();
		Assertion.assertTrue(ifEqual);
	}

	/**
	 * This method creates a file in the process.
	 * recomended: after call, delete the physical file using file.delete()
	 *
	 * @return file uploaded as main thumbnail on special:Promote
	 */
	public File getUploadedImage() {
		waitForElementByElement(thumbnailImage);
		File uploadedImageFile = new File(PageContent.resourcesPath + "shouldBeDeleted.png");
        try {
        	URL url = new URL(thumbnailImage.getAttribute("src"));
        	BufferedImage bufImgOne = ImageIO.read(url);
        	ImageIO.write(bufImgOne, "png", uploadedImageFile);
        } catch (IOException e) {
			e.printStackTrace();
		}
        return uploadedImageFile;
	}

}
