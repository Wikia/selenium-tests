package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class VisualEditModePageObject extends EditMode {

  @FindBy(css = "#bodyContent")
  private WebElement contentInput;
  @FindBy(css = "div.cke_wrapper.cke_ltr div.cke_contents iframe")
  private WebElement iframe;
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
  @FindBy(css = ".video-thumbnail")
  protected WebElement videoArticle;
  @FindBy(css = ".RTEMediaOverlayEdit")
  private WebElement modifyComponentButton;
  @FindBy(css = "[style*=\"block\"] .RTEMediaOverlayDelete")
  private WebElement removeComponentButton;
  @FindBy(css = "#RTEConfirmOk > span")
  private WebElement removeConfirmationButton;
  @FindBy(css = "#wpTextbox1")
  private WebElement messageSourceModeTextArea;
  @FindBy(css = "input#CategorySelectInput")
  private WebElement categoryInput;
  @FindBy(css = "#CategorySelect .ui-autocomplete")
  private WebElement categorySuggestionsContainer;
  @FindBy(css = "li.category")
  private List<WebElement> categoryList;
  @FindBy(css = ".RTEMediaCaption")
  private WebElement caption;
  @FindBy(xpath = "//p[contains(text(), 'You do not have permission to edit this page, for the following reason:')]")
  private WebElement blockedUserMessage1;
  @FindBy(xpath = "//b[contains(text(), 'Your user name or IP address has been blocked.')]")
  private WebElement blockedUserMessage2;
  @FindBy(css = ".cke_button_tabledelete > span.cke_label")
  private WebElement deleteItem;
  @FindBy(css = ".cke_button_table")
  private WebElement propertiesItem;
  @FindBy(css = ".article-table")
  private WebElement visualModeTable;
  @FindBy(css = ".cke_contextmenu iframe")
  private WebElement contextFrame;
  @FindBy(css = ".cke_dialog_body")
  private WebElement addTableLightbox;

  private static final String IMAGE_COMPONENT_CSS = "img.image";
  @FindBy(css = IMAGE_COMPONENT_CSS)
  private WebElement image;

  private static final By IMAGE_BY = By.cssSelector(IMAGE_COMPONENT_CSS);
  private By galleryBy = By.cssSelector("img.image-gallery");

  private By slideshowBy = By.cssSelector("img.image-slideshow");
  private By sliderBy = By.cssSelector("img.image-gallery-slider");
  private By videoBy = By.cssSelector("img.video");
  private By categorySuggestionsList = By.cssSelector("li > a");

  private String categoryEditSelector = "li.category[data-name='%categoryName%'] li.editCategory";
  private String
      categoryRemoveSelector =
      "li.category[data-name='%categoryName%'] li.removeCategory";
  private String categoryRemovedSelector = "li.category[data-name='%categoryName%']";

  public enum Components {
    PHOTO, GALLERY, SLIDESHOW, SLIDER, VIDEO, VIDEO_PLACEHOLDER
  }

  public VisualEditModePageObject(WebDriver driver) {
    super(driver);
  }

  public void clearContent() {
    driver.switchTo().frame(iframe);
    contentInput.clear();
    driver.switchTo().defaultContent();
  }

  /**
   * clears article content and adds new content to the article
   */
  public void addContent(String content) {
    driver.switchTo().frame(iframe);
    contentInput.clear();
    contentInput.sendKeys(content);
    driver.switchTo().defaultContent();
    PageObjectLogging.log("addContent", "content " + content + " added to the article", true);
  }

  /**
   * adds new content to an article without clearing the existing content
   */
  public void appendContent(String content) {
    driver.switchTo().frame(iframe);
    contentInput.sendKeys(content);
    driver.switchTo().defaultContent();
    PageObjectLogging.log("appendContent", "content " + content + " added to the article", true);
  }

  private void verifyComponent(WebElement component) {
    driver.switchTo().frame(iframe);
    waitForElementByElement(component);
    driver.switchTo().defaultContent();
  }

  private void verifyComponent(By componentBy) {
    driver.switchTo().frame(iframe);
    waitForElementByElementLocatedBy(componentBy);
    driver.switchTo().defaultContent();
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

  public void verifyContentLoaded() {
    driver.switchTo().frame(iframe);
    waitForElementByElement(contentInput);
    PageObjectLogging.log("verifyContentLoaded", "RTE editor loaded", true, driver);
    driver.switchTo().defaultContent();
  }

  public void verifyVideoPosition(PositionsVideo position) {
    verifyComponent(video);
    driver.switchTo().frame(iframe);
    String positionClass = video.getAttribute("class");
    driver.switchTo().defaultContent();
    switch (position) {
      case LEFT:
        Assertion.assertStringContains("alignLeft", positionClass);
        break;
      case CENTER:
        Assertion.assertStringContains("alignCenter", positionClass);
        break;
      case RIGHT:
        Assertion.assertStringContains("alignRight", positionClass);
        break;
      default:
        throw new NoSuchElementException("Non-existing position selected");
    }
  }

  public void verifyVideoWidth(int widthDesired) {
    verifyComponent(video);
    driver.switchTo().frame(iframe);
    int widthCurrent = Integer.parseInt(video.getAttribute("width"));
    driver.switchTo().defaultContent();
    Assertion.assertNumber(
        widthDesired,
        widthCurrent,
        "width should be " + widthDesired + " but is " + widthCurrent
    );
  }

  public void verifyVideoCaption(String captionDesired) {
    mouseOverComponent(Components.VIDEO);
    Assertion.assertEquals(captionDesired, caption.getText());
  }

  private void mouseOverComponent(Components component) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    switch (component) {
      case GALLERY:
        verifyComponent(gallery);
        js.executeScript(
            "$('div.cke_contents>iframe').contents().find('img.image-gallery').mouseenter()");
        break;
      case SLIDESHOW:
        verifyComponent(slideshow);
        js.executeScript(
            "$('div.cke_contents>iframe').contents().find('img.image-slideshow').mouseenter()");
        break;
      case SLIDER:
        verifyComponent(slider);
        js.executeScript(
            "$('div.cke_contents>iframe').contents().find('img.image-gallery-slider').mouseenter()");
        break;
      case VIDEO:
        verifyComponent(video);
        js.executeScript("$('div.cke_contents>iframe').contents().find('img.video').mouseenter()");
        break;
      case PHOTO:
        verifyComponent(image);
        js.executeScript("$('div.cke_contents>iframe').contents().find('img.image').mouseenter()");
        break;
      case VIDEO_PLACEHOLDER:
        verifyComponent(videoPlaceholder);
        js.executeScript(
            "$('div.cke_contents>iframe').contents().find('img.video-placeholder').mouseenter()");
        break;
      default:
        break;
    }
  }

  public Object modifyComponent(Components component) {
    mouseOverComponent(component);
    waitForElementByElement(modifyComponentButton);
    modifyComponentButton.click();
    PageObjectLogging.log("modifyGallery", "Click on 'modify button' on gallery", true, driver);
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
    PageObjectLogging.log("removeGallery", "Click on 'remove button' on gallery", true);
  }

  public void verifyComponentRemoved(Components component) {
    driver.switchTo().frame(iframe);
    switch (component) {
      case PHOTO:
        waitForElementNotPresent(IMAGE_BY);
        break;
      case GALLERY:
        waitForElementNotPresent(galleryBy);
        break;
      case SLIDESHOW:
        waitForElementNotPresent(slideshowBy);
        break;
      case SLIDER:
        waitForElementNotPresent(sliderBy);
        break;
      case VIDEO:
        waitForElementNotPresent(videoBy);
        break;
      default:
        PageObjectLogging.log(
            "verifyComponentRemoved",
            "Invalid component: " + component.name() + " selected",
            false
        );
        break;
    }
    driver.switchTo().defaultContent();
    PageObjectLogging.log("verifyGalleryRemoved", "Click on 'remove button' on gallery", true);
  }

  /**
   * Delete unwanted video by its name. Message article page is e.g http://mediawiki119.wikia.com/wiki/MediaWiki:RelatedVideosGlobalList
   * This method destination is exactly related videos message article
   *
   * @param unwantedVideoName e.g "What is love (?) - on piano (Haddway)"
   * @author Michal Nowierski
   */
  public void deleteUnwantedVideoFromMessage(String unwantedVideoName) {
    List<String> videos = new ArrayList<String>();
    waitForElementByElement(messageSourceModeTextArea);
    String sourceText = messageSourceModeTextArea.getText();
    int index = 0;
    while (true) {
      int previousStarIndex = sourceText.indexOf("*", index);
      int nextStarIndex = sourceText.indexOf("*", previousStarIndex + 1);
      if (nextStarIndex < 0) {
        break;
      }
      String videoText = sourceText.substring(previousStarIndex, nextStarIndex);
      if (!videoText.contains(unwantedVideoName)) {
        videos.add(videoText);
      }
      index = previousStarIndex + 1;
    }
    waitForElementByElement(messageSourceModeTextArea);
    messageSourceModeTextArea.clear();
    messageSourceModeTextArea.sendKeys("WHITELIST");
    messageSourceModeTextArea.sendKeys(Keys.ENTER);
    messageSourceModeTextArea.sendKeys(Keys.ENTER);
    String builder = "";
    for (int i = 0; i < videos.size(); i++) {
      builder += videos.get(i);
      builder += "\n";
    }
    CommonUtils.setClipboardContents(builder);
    messageSourceModeTextArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    PageObjectLogging
        .log("deleteUnwantedVideoFromMessage", "Delete all source code on the article", true,
             driver);
  }

  public void typeCategoryName(String categoryName) {
    waitForElementByElement(categoryInput);
    CommonUtils.setClipboardContents(categoryName);
    categoryInput.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    PageObjectLogging.log("typeCategoryName", categoryName + " typed", true);
  }

  public void triggerCategorySuggestions() {
    int timeout = 0;
    pressDownArrow(categoryInput);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String returned = (String) js.executeScript("return $('ul.ui-autocomplete li').text()");
    while (returned.isEmpty() && timeout <= 5000) {
      try {
        Thread.sleep(500);
        timeout += 500;
      } catch (InterruptedException e) {
        PageObjectLogging
            .log("triggerCategorySuggestions", "Interrupted Exception occurred", false);
      }
      pressDownArrow(categoryInput);
      returned = (String) js.executeScript("return $('ul.ui-autocomplete li').text()");
    }
  }

  public void submitCategory() {
    pressEnter(categoryInput);
    PageObjectLogging.log("submitCategory", "category submitted", true);
  }

  public void verifyCategoryPresent(String category) {
    boolean categoryVisible = false;
    for (WebElement elem : categoryList) {
      if (elem.getText().equals(category)) {
        categoryVisible = true;
      }
    }
    Assertion.assertTrue(categoryVisible, "category " + category + " not present");
  }

  public void verifyCategoryNotPresent(String category) {
    waitForElementNotPresent(categoryRemovedSelector.replace("%categoryName%", category));
    boolean categoryVisible = true;
    for (WebElement elem : categoryList) {
      if (elem.getText().equals(category)) {
        categoryVisible = false;
      }
    }
    Assertion.assertTrue(categoryVisible, "category " + category + " present");
  }

  public String selectCategorySuggestions(int categoryNumber) {
    waitForElementByElement(categorySuggestionsContainer);
    WebElement categoryItem = categorySuggestionsContainer
        .findElements(categorySuggestionsList)
        .get(categoryNumber);
    String categoryName = categoryItem.getText();
    categoryItem.click();
    waitForElementNotVisibleByElement(categorySuggestionsContainer);
    PageObjectLogging
        .log("selectCategorySuggestions", categoryNumber + " category selected from suggestions",
             true);
    return categoryName;
  }

  public EditCategoryComponentObject editCategory(String categoryName) {
    WebElement category = driver.findElement(
        By.cssSelector(
            categoryEditSelector.replace("%categoryName%", categoryName)
        )
    );
    jQueryClick(category);
    PageObjectLogging
        .log("editCategory", "edit category button clicked on category " + categoryName, true);
    return new EditCategoryComponentObject(driver);
  }

  public void removeCategory(String categoryName) {
    WebElement category = driver.findElement(
        By.cssSelector(
            categoryRemoveSelector.replace("%categoryName%", categoryName)
        )
    );
    jQueryClick(category);
    PageObjectLogging
        .log("removeCategory", "remove category button clicked on category " + categoryName, true);
  }

  public void verifyBlockedUserMessage() {
    waitForElementByElement(blockedUserMessage1);
    waitForElementByElement(blockedUserMessage2);
    PageObjectLogging.log("verifyBlockedUserMessage",
                          "blocked user message when attempting to create article verified", true);
  }

  private void selectFromContextMenu(WebElement option) {
    waitForElementByElement(iframe);
    driver.switchTo().frame(iframe);
    Actions actions = new Actions(driver);
    actions.contextClick(visualModeTable).build().perform();
    driver.switchTo().defaultContent();
    driver.switchTo().frame(contextFrame);
    option.click();
    driver.switchTo().defaultContent();
    checkIfElementOnPage(visualModeTable);
  }

  public void clickDeleteTableButton() {
    selectFromContextMenu(deleteItem);
  }

  public void clickPropertiesTableButton() {
    selectFromContextMenu(propertiesItem);
    waitForElementByElement(addTableLightbox);
  }

  public ArticlePageObject clickPublishButton() {
    waitForElementByElement(submitButton);
    submitButton.click();
    return new ArticlePageObject(driver);
  }

}
