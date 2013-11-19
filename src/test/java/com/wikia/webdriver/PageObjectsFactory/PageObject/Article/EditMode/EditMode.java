package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class EditMode extends WikiBasePageObject {

	@FindBy(css="#wpSave")
	private WebElement submitButton;
	@FindBy(css="#wpPreview")
	private WebElement previewButton;
	@FindBy(css="a.RTEImageButton")
	private WebElement photoButton;
	@FindBy(css="a.RTEVideoButton")
	private WebElement videoButton;
	@FindBy(css="a.RTEGalleryButton")
	private WebElement galleryButton;
	@FindBy(css="a.RTESlideshowButton")
	private WebElement slideshowButton;
	@FindBy(css="a.RTESliderButton")
	private WebElement sliderButton;
	@FindBy(css="a.cke_button_ModeWysiwyg > span#cke_23_label")
	private WebElement visualButton;
	@FindBy(css="a.cke_button_ModeSource > span#cke_22_label")
	private WebElement sourceButton;

	private By submitButtonBy = By.cssSelector("#wpSave");

	public EditMode(WebDriver driver) {
		super(driver);
	}

	private void submit() {
		driver.switchTo().defaultContent();
		waitForElementClickableByElement(submitButton);
		scrollAndClick(submitButton);
		waitForElementNotPresent(submitButtonBy);
		PageObjectLogging.log("submit", "Page submitted", true);
	}

	public ArticlePageObject submitArticle() {
		submit();
		return new ArticlePageObject(driver);
	}

	public PreviewEditModePageObject previewArticle() {
		driver.switchTo().defaultContent();
		previewButton.click();
		PageObjectLogging.log("preview", "Page preview displayed", true);
		return new PreviewEditModePageObject(driver);
	}

	public BlogPageObject submitBlog() {
		submit();
		return new BlogPageObject(driver);
	}

	public PhotoAddComponentObject clickPhotoButton(){
		waitForElementByElement(photoButton);
		scrollAndClick(photoButton);
		PageObjectLogging.log("clickPhotoButton", "photo button clicked", true);
		return new PhotoAddComponentObject(driver);
	}

	public VetAddVideoComponentObject clickVideoButton(){
		waitForElementByElement(videoButton);
		scrollAndClick(videoButton);
		PageObjectLogging.log("clickVideoButton", "video button clicked", true);
		return new VetAddVideoComponentObject(driver);
	}

	public SliderBuilderComponentObject clickSliderButton(){
		waitForElementByElement(sliderButton);
		scrollAndClick(sliderButton);
		PageObjectLogging.log("clickSliderButton", "slider button clicked", true);
		return new SliderBuilderComponentObject(driver);
	}

	public SlideshowBuilderComponentObject clickSlideshowButton(){
		waitForElementByElement(slideshowButton);
		scrollAndClick(slideshowButton);
		PageObjectLogging.log("clickSlideshowButton", "slideshow button clicked", true);
		return new SlideshowBuilderComponentObject(driver);
	}

	public GalleryBuilderComponentObject clickGalleryButton(){
		waitForElementByElement(galleryButton);
		scrollAndClick(galleryButton);
		PageObjectLogging.log("clickGalleryButton", "gallery button clicked", true);
		return new GalleryBuilderComponentObject(driver);
	}

	public SourceEditModePageObject clickSourceButton() {
		sourceButton.click();
		PageObjectLogging.log("clickSourceButton", "source button clicked", true);
		return new SourceEditModePageObject(driver);
	}

	public VisualEditModePageObject clickVisualButton() {
		visualButton.click();
		PageObjectLogging.log("clickVisualButton", "visual button clicked", true);
		return new VisualEditModePageObject(driver);
	}
}
