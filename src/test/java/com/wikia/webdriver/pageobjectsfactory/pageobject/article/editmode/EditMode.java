package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class EditMode extends WikiBasePageObject {

  @FindBy(css = "#wpSave")
  protected WebElement submitButton;
  @FindBy(css = "#wpPreview")
  private WebElement previewButton;
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
  @FindBy(css = "a.cke_button_ModeSource > span#cke_22_label")
  private WebElement sourceButton;
  @FindBy(css = "a.cke_off.cke_button_table")
  private WebElement addTableButton;

  private By submitButtonBy = By.cssSelector("#wpSave");

  public EditMode(WebDriver driver) {
    super(driver);
  }

  private void submit() {
    driver.switchTo().defaultContent();
    wait.forElementClickable(submitButton);
    scrollAndClick(submitButton);
    wait.forElementNotPresent(submitButtonBy);
    LOG.success("submit", "Page submitted");
  }

  public ArticlePageObject submitArticle() {
    submit();
    return new ArticlePageObject(driver);
  }

  public PreviewEditModePageObject previewArticle() {
    driver.switchTo().defaultContent();
    previewButton.click();
    LOG.success("preview", "Page preview displayed");
    return new PreviewEditModePageObject(driver);
  }

  public BlogPageObject submitBlog() {
    submit();
    return new BlogPageObject(driver);
  }

  public PhotoAddComponentObject clickPhotoButton() {
    wait.forElementVisible(photoButton);
    scrollAndClick(photoButton);
    LOG.success("clickPhotoButton", "photo button clicked");
    return new PhotoAddComponentObject(driver);
  }

  public VetAddVideoComponentObject clickVideoButton() {
    wait.forElementVisible(videoButton);
    scrollAndClick(videoButton);
    LOG.success("clickVideoButton", "video button clicked");
    return new VetAddVideoComponentObject(driver);
  }

  public TableBuilderComponentObject clickAddTableButton() {
    addTableButton.click();
    LOG.success("addTable", "add table button clicked");
    return new TableBuilderComponentObject(driver);
  }

  public SliderBuilderComponentObject clickSliderButton() {
    wait.forElementVisible(sliderButton);
    scrollAndClick(sliderButton);
    LOG.success("clickSliderButton", "slider button clicked");
    return new SliderBuilderComponentObject(driver);
  }

  public SlideshowBuilderComponentObject clickSlideshowButton() {
    wait.forElementVisible(slideshowButton);
    scrollAndClick(slideshowButton);
    LOG.success("clickSlideshowButton", "slideshow button clicked");
    return new SlideshowBuilderComponentObject(driver);
  }

  public GalleryBuilderComponentObject clickGalleryButton() {
    wait.forElementVisible(galleryButton);
    scrollAndClick(galleryButton);
    LOG.success("clickGalleryButton", "gallery button clicked");
    return new GalleryBuilderComponentObject(driver);
  }

  public SourceEditModePageObject clickSourceButton() {
    sourceButton.click();
    LOG.success("clickSourceButton", "source button clicked");
    return new SourceEditModePageObject(driver);
  }

  public VisualEditModePageObject clickVisualButton() {
    visualButton.click();
    LOG.success("clickVisualButton", "visual button clicked");
    return new VisualEditModePageObject(driver);
  }
}
