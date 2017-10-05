package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditMode extends WikiBasePageObject {

  @FindBy(css = "#wpSave")
  private WebElement submitButton;

  @FindBy(css = "#wpPreview")
  private WebElement desktopPreviewButton;

  @FindBy(css = "a.RTEImageButton")
  private WebElement photoButton;

  @FindBy(css = "a.RTEVideoButton")
  private WebElement videoButton;

  @FindBy(css = "a.RTEGalleryButton")
  private WebElement galleryButton;

  @FindBy(css = "a.RTESlideshowButton")
  private WebElement slideshowButton;

  @FindBy(css = "a.RTESliderButton")
  private WebElement sliderButton;

  @FindBy(css = "a.cke_button_ModeWysiwyg > span#cke_23_label")
  private WebElement visualButton;

  @FindBy(css = "a.cke_button_ModeSource > span.cke_label")
  private WebElement sourceButton;

  @FindBy(css = "a.cke_off.cke_button_table")
  private WebElement addTableButton;

  @FindBy(css = ".editpage-notices")
  private WebElement notificationForAnon;

  private By submitButtonBy = By.cssSelector("#wpSave");

  public EditMode() {
    super();
  }

  private void submit() {
    driver.switchTo().defaultContent();
    wait.forElementClickable(submitButton);
    scrollAndClick(submitButton);

    PageObjectLogging.logInfo("Submit");
  }

  public ArticlePageObject submitArticle() {
    submit();
    wait.forElementNotPresent(submitButtonBy);
    PageObjectLogging.logInfo("Page submitted");

    return new ArticlePageObject();
  }

  /**
   * Submitting an edit is expecting a notification
   */
  public EditMode submitExpectingNotification() {
    submit();
    wait.forElementVisible(notificationForAnon);
    PageObjectLogging.logInfo("Notification is visible");

    return this;
  }

  public PreviewEditModePageObject previewArticle() {
    driver.switchTo().defaultContent();
    wait.forElementClickable(desktopPreviewButton);
    desktopPreviewButton.click();
    PageObjectLogging.log("preview", "Page preview displayed", true);
    return new PreviewEditModePageObject(driver);
  }

  public BlogPage submitBlog() {
    submit();
    wait.forElementNotPresent(submitButtonBy);

    return new BlogPage();
  }

  public PhotoAddComponentObject clickPhotoButton() {
    wait.forElementVisible(photoButton);
    scrollAndClick(photoButton);
    PageObjectLogging.log("clickPhotoButton", "photo button clicked", true);
    return new PhotoAddComponentObject(driver);
  }

  public VetAddVideoComponentObject clickVideoButton() {
    wait.forElementVisible(videoButton);
    scrollAndClick(videoButton);
    PageObjectLogging.log("clickVideoButton", "video button clicked", true);
    return new VetAddVideoComponentObject(driver);
  }

  public TableBuilderComponentObject clickAddTableButton() {
    addTableButton.click();
    PageObjectLogging.log("addTable", "add table button clicked", true);
    return new TableBuilderComponentObject(driver);
  }

  public SliderBuilderComponentObject clickSliderButton() {
    wait.forElementVisible(sliderButton);
    scrollAndClick(sliderButton);
    PageObjectLogging.log("clickSliderButton", "slider button clicked", true);
    return new SliderBuilderComponentObject(driver);
  }

  public SlideshowBuilderComponentObject clickSlideshowButton() {
    wait.forElementVisible(slideshowButton);
    scrollAndClick(slideshowButton);
    PageObjectLogging.log("clickSlideshowButton", "slideshow button clicked", true);
    return new SlideshowBuilderComponentObject(driver);
  }

  public GalleryBuilderComponentObject clickGalleryButton() {
    wait.forElementVisible(galleryButton);
    scrollAndClick(galleryButton);
    PageObjectLogging.log("clickGalleryButton", "gallery button clicked", true);
    return new GalleryBuilderComponentObject(driver);
  }

  public SourceEditModePageObject clickSourceButton() {
    sourceButton.click();
    PageObjectLogging.log("clickSourceButton", "source button clicked", true);
    return new SourceEditModePageObject();
  }
}
