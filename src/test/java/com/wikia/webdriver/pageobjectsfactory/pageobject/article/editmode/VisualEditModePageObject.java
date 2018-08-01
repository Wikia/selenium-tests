package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.common.Frame;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AceEditor;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.rte.CategoryModule;
import com.wikia.webdriver.pageobjectsfactory.componentobject.rte.FeaturesModule;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class VisualEditModePageObject extends EditMode {

  public static final int ELEMENT_SHOW_UP_TIMEOUT = 3;
  private static final String IMAGE_COMPONENT_CSS = "img.image";
  private static final By IMAGE_BY = By.cssSelector(IMAGE_COMPONENT_CSS);
  private final By portableInfoboxBy = By.cssSelector(
      ".placeholder-double-brackets .portable-infobox");
  @FindBy(css = ".video-thumbnail")
  protected WebElement videoArticle;
  @FindBy(css = "#wpSave")
  private WebElement submitButton;
  @FindBy(css = "a.cke_button_bold")
  private WebElement boldButton;
  @FindBy(css = "#bodyContent")
  private WebElement contentInput;
  @FindBy(css = ".cke_wysiwyg_frame")
  private WebElement iframe;
  private final Frame editorFrame = new Frame(iframe);
  @FindBy(css = "img.image-gallery")
  private WebElement gallery;
  @FindBy(css = "img.image-slideshow")
  private WebElement slideshow;
  @FindBy(css = "img.image-gallery-slider")
  private WebElement slider;
  @FindBy(css = "img.video")
  private WebElement video;
  @FindBy(css = "img.video-placeholder")
  private WebElement videoPlaceholder;
  @FindBy(css = ".RTEMediaOverlayEdit")
  private WebElement modifyComponentButton;
  @FindBy(css = "[style*=\"block\"] .RTEMediaOverlayDelete")
  private WebElement removeComponentButton;
  @FindBy(css = "#RTEConfirmOk > span")
  private WebElement removeConfirmationButton;
  @FindBy(css = ".RTEMediaCaption")
  private WebElement caption;
  @FindBy(css = ".placeholder-double-brackets .portable-infobox")
  private WebElement portableInfoboxTransclusion;
  @FindBy(css = "[style*=\"block\"] .RTEPlaceholderPreviewToolsDelete")
  private WebElement removeInfoboxButton;
  @FindBy(xpath = "//p[contains(text(), 'You do not have permission to edit this page, for the following reason:')]")
  private WebElement blockedUserMessage1;
  @FindBy(xpath = "//b[contains(text(), 'Your user name or IP address has been blocked.')]")
  private WebElement blockedUserMessage2;
  @FindBy(css = ".cke_menubutton.cke_menubutton__tabledelete")
  private WebElement deleteItem;
  @FindBy(css = ".cke_button_table")
  private WebElement propertiesItem;
  @FindBy(css = ".article-table.article-table-selected")
  private WebElement visualModeTable;
  @FindBy(css = ".cke_menu_panel iframe")
  private WebElement contextFrame;
  private final Frame contextFrameWrapper = new Frame(contextFrame);
  @FindBy(css = ".cke_dialog_body")
  private WebElement addTableLightbox;
  @FindBy(css = IMAGE_COMPONENT_CSS)
  private WebElement image;
  @FindBy(css = "#wpApprove")
  private WebElement autoApproveCheckbox;
  @FindBy(css = ".rail-auto-height")
  private WebElement modalElement;
  private By galleryBy = By.cssSelector("img.image-gallery");
  private By slideshowBy = By.cssSelector("img.image-slideshow");
  private By sliderBy = By.cssSelector("img.image-gallery-slider");
  private By videoBy = By.cssSelector("img.video");
  private AceEditor aceEditor;
  private FeaturesModule featuresModule;
  private CategoryModule categoryModule;

  public VisualEditModePageObject() {
    super();
  }

  public VisualEditModePageObject clickBoldButton() {
    boldButton.click();
    return this;
  }

  public VisualEditModePageObject open() {
    getUrl(urlBuilder.appendQueryStringToURL(
        urlBuilder.getUrl() + URLsContent.WIKI_DIR + TestContext.getCurrentMethodName(),
        URLsContent.ACTION_EDIT
    ));
    return this;
  }

  public VisualEditModePageObject open(String articleName) {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrl() + URLsContent.WIKI_DIR + articleName,
                                             URLsContent.ACTION_EDIT
    ));
    return this;
  }

  public AceEditor getAceEditor() {
    if (aceEditor == null) {
      aceEditor = new AceEditor();
    }

    return aceEditor;
  }

  public VisualEditModePageObject clearContent() {
    editorFrame.frameScope(contentInput::clear);
    return this;
  }

  /**
   * clears article content and adds new content to the article
   */
  public void addContent(String content) {
    editorFrame.frameScope(() -> contentInput.sendKeys(content));
    Log.log("addContent", "content " + content + " added to the article", true);
  }

  public boolean checkPortableInfoboxVisible() {
    return editorFrame.frameScope(portableInfoboxTransclusion::isDisplayed);
  }

  public void addContentWithoutClear(String content) {
    wait.forElementVisible(iframe);
    driver.switchTo().frame(iframe);
    contentInput.sendKeys(content);
    driver.switchTo().defaultContent();
    Log.log("addContent", "content " + content + " added to the article", true);
  }

  private void verifyComponent(WebElement component) {
    editorFrame.frameScope(() -> wait.forElementVisible(component));
  }

  private void verifyComponent(By componentBy) {
    editorFrame.frameScope(() -> wait.forElementVisible(componentBy));
  }

  public void verifyPhoto() {
    verifyComponent(IMAGE_BY);
  }

  public void verifyGallery() {
    verifyComponent(gallery);
  }

  public void verifySlideshow() {
    verifyComponent(slideshow);
  }

  public void verifySlider() {
    verifyComponent(slider);
  }

  public void verifyVideo() {
    verifyComponent(video);
  }

  public boolean isContentLoaded() {
    return editorFrame.frameScope(() -> {
      try {
        wait.forElementVisible(contentInput);
        return true;
      } catch (TimeoutException e) {
        Log.log("isContentLoaded", "RTE editor loaded", false, driver);
        return false;
      }
    });
  }

  public void verifyVideoPosition(PositionsVideo position) {
    String positionClass = editorFrame.frameScope(() -> video.getAttribute("class"));
    switch (position) {
      case LEFT:
        Assertion.assertStringContains(positionClass, "alignLeft");
        break;
      case CENTER:
        Assertion.assertStringContains(positionClass, "alignCenter");
        break;
      case RIGHT:
        Assertion.assertStringContains(positionClass, "alignRight");
        break;
      default:
        throw new NoSuchElementException("Non-existing position selected");
    }
  }

  public int getVideoWidth() {
    return editorFrame.frameScope(() -> Integer.parseInt(video.getAttribute("width")));
  }

  public String getVideoCaption() {
    mouseOverComponent(Components.VIDEO);
    wait.forElementVisible(caption);

    return caption.getText();
  }

  private void mouseOverComponent(Components component) {
    switch (component) {
      case GALLERY:
        verifyComponent(gallery);
        driver.executeScript(
            "$('.cke_wysiwyg_frame').contents().find('img.image-gallery').mouseenter()");
        break;
      case SLIDESHOW:
        verifyComponent(slideshow);
        driver.executeScript(
            "$('.cke_wysiwyg_frame').contents().find('img.image-slideshow').mouseenter()");
        break;
      case SLIDER:
        verifyComponent(slider);
        driver.executeScript(
            "$('.cke_wysiwyg_frame').contents().find('img.image-gallery-slider').mouseenter()");
        break;
      case VIDEO:
        verifyComponent(video);
        driver.executeScript("$('.cke_wysiwyg_frame').contents().find('img.video').mouseenter()");
        break;
      case PHOTO:
        verifyComponent(image);
        driver.executeScript("$('.cke_wysiwyg_frame').contents().find('img.image').mouseenter()");
        break;
      case VIDEO_PLACEHOLDER:
        verifyComponent(videoPlaceholder);
        driver.executeScript(
            "$('.cke_wysiwyg_frame').contents().find('img.video-placeholder').mouseenter()");
        break;
      default:
        break;
    }
  }

  public Object modifyComponent(Components component) {
    mouseOverComponent(component);
    wait.forElementVisible(modifyComponentButton);
    modifyComponentButton.click();
    Log.log("modifyGallery", "Click on 'modify button' on gallery", true, driver);
    switch (component) {
      case GALLERY:
        return new GalleryBuilderComponentObject(driver);
      case PHOTO:
        return new PhotoAddComponentObject(driver);
      case SLIDER:
        return new SliderBuilderComponentObject(driver);
      case SLIDESHOW:
        return new SlideshowBuilderComponentObject(driver);
      case VIDEO:
        return new VetOptionsComponentObject(driver);
      case VIDEO_PLACEHOLDER:
        return new VetAddVideoComponentObject(driver);
      default:
        return null;
    }
  }

  public void removeComponent(Components component) {
    mouseOverComponent(component);
    removeComponentButton.click();
    removeConfirmationButton.click();
    Log.log("removeComponent", "Click on 'remove button' on component", true);
  }

  public void verifyComponentRemoved(Components component) {
    editorFrame.frameScope(() -> {
      switch (component) {
        case PHOTO:
          wait.forElementNotPresent(IMAGE_BY);
          break;
        case GALLERY:
          wait.forElementNotPresent(galleryBy);
          break;
        case SLIDESHOW:
          wait.forElementNotPresent(slideshowBy);
          break;
        case SLIDER:
          wait.forElementNotPresent(sliderBy);
          break;
        case VIDEO:
          wait.forElementNotPresent(videoBy);
          break;
        default:
          Log.log("verifyComponentRemoved",
                  "Invalid component: " + component.name() + " selected",
                  false
          );
          break;
      }
    });

    Log.log("verifyGalleryRemoved", "Click on 'remove button' on gallery", true);
  }

  public void removePortableInfobox() {
    verifyComponent(portableInfoboxTransclusion);
    driver.executeScript(
        "$('.cke_wysiwyg_frame').contents().find('.placeholder-double-brackets .portable-infobox').mouseenter()");

    removeInfoboxButton.click();
    removeConfirmationButton.click();
  }

  public boolean checkPortableInfoboxIsNotPresent() {
    return editorFrame.frameScope(() -> {
      try {
        wait.forElementNotPresent(portableInfoboxBy);
        return true;
      } catch (TimeoutException e) {
        return false;
      }
    });
  }

  public void verifyBlockedUserMessage() {
    wait.forElementVisible(blockedUserMessage1);
    wait.forElementVisible(blockedUserMessage2);
    Log.log(
        "verifyBlockedUserMessage",
        "blocked user message when attempting to create article verified",
        true
    );
  }

  private void selectFromContextMenu(WebElement option) {
    editorFrame.frameScope(() -> {
      Actions actions = new Actions(driver);
      actions.contextClick(visualModeTable).build().perform();
    });

    contextFrameWrapper.frameScope(option::click);

    isElementOnPage(visualModeTable);
  }

  public void clickDeleteTableButton() {
    selectFromContextMenu(deleteItem);
  }

  public void clickPropertiesTableButton() {
    selectFromContextMenu(propertiesItem);
    wait.forElementVisible(addTableLightbox);
  }

  public ArticlePageObject clickPublishButton() {
    wait.forElementVisible(submitButton);
    submitButton.click();
    return new ArticlePageObject();
  }

  public VisualEditModePageObject clickAutoApproveCheckbox() {
    wait.forElementVisible(autoApproveCheckbox);
    autoApproveCheckbox.click();
    return this;
  }

  public FeaturesModule getFeaturesModule() {
    if (featuresModule == null) {
      featuresModule = new FeaturesModule(driver);
    }

    return featuresModule;
  }

  public CategoryModule getCategoryModule() {
    if (categoryModule == null) {
      categoryModule = new CategoryModule(driver);
    }
    return categoryModule;
  }

  public enum Components {
    PHOTO, GALLERY, SLIDESHOW, SLIDER, VIDEO, VIDEO_PLACEHOLDER
  }
}
