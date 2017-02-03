package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.AceEditor;
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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class VisualEditModePageObject extends EditMode {

  private static final String IMAGE_COMPONENT_CSS = "img.image";
  private static final By IMAGE_BY = By.cssSelector(IMAGE_COMPONENT_CSS);
  @FindBy(css = "#wpSave")
  private WebElement submitButton;
  @FindBy(css = ".video-thumbnail")
  protected WebElement videoArticle;
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
  @FindBy(css = "li.category>span")
  private List<WebElement> categoryList;
  @FindBy(css = ".RTEMediaCaption")
  private WebElement caption;
  @FindBy(
      xpath = "//p[contains(text(), 'You do not have permission to edit this page, for the following reason:')]")
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
  @FindBy(css = IMAGE_COMPONENT_CSS)
  private WebElement image;
  @FindBy(css = "#wpApprove")
  private WebElement autoApproveCheckbox;
  @FindBy(css = ".module_categories")
  private WebElement categoriesModuleBar;


  @FindBy(css = ".rail-auto-height")
  private WebElement modalElement;


  private By galleryBy = By.cssSelector("img.image-gallery");
  private By slideshowBy = By.cssSelector("img.image-slideshow");
  private By sliderBy = By.cssSelector("img.image-gallery-slider");
  private By videoBy = By.cssSelector("img.video");
  private By categorySuggestionsList = By.cssSelector("li > a");

  private String categoryEditSelector = "li.category[data-name='%categoryName%'] li.editCategory";
  private String categoryRemoveSelector =
      "li.category[data-name='%categoryName%'] li.removeCategory";
  private String categoryRemovedSelector = "li.category[data-name='%categoryName%']";
  private AceEditor aceEditor;

  public VisualEditModePageObject() {
    super();
  }

  public VisualEditModePageObject open() {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForWiki(Configuration.getWikiName())
        + URLsContent.WIKI_DIR + TestContext.getCurrentMethodName(), URLsContent.ACTION_EDIT));
    return this;
  }

  public VisualEditModePageObject open(String articleName) {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForWiki(Configuration.getWikiName())
        + URLsContent.WIKI_DIR + articleName, URLsContent.ACTION_EDIT));
    return this;
  }

  public AceEditor getAceEditor() {
    if (aceEditor == null) {
      aceEditor = new AceEditor();
    }

    return aceEditor;
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

  private void verifyComponent(WebElement component) {
    driver.switchTo().frame(iframe);
    wait.forElementVisible(component);
    driver.switchTo().defaultContent();
  }

  private void verifyComponent(By componentBy) {
    driver.switchTo().frame(iframe);
    wait.forElementVisible(componentBy);
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

  public boolean isContentLoaded() {
    wait.forElementVisible(iframe);
    driver.switchTo().frame(iframe);
    try {
      wait.forElementVisible(contentInput);
      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.log("isContentLoaded", "RTE editor loaded", false, driver);
      return false;
    } finally {
      driver.switchTo().defaultContent();
    }
  }

  public void verifyVideoPosition(PositionsVideo position) {
    verifyComponent(video);
    driver.switchTo().frame(iframe);
    String positionClass = video.getAttribute("class");
    driver.switchTo().defaultContent();
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
    verifyComponent(video);
    driver.switchTo().frame(iframe);
    int widthCurrent = Integer.parseInt(video.getAttribute("width"));
    driver.switchTo().defaultContent();

    return widthCurrent;
  }

  public String getVideoCaption() {
    mouseOverComponent(Components.VIDEO);
    wait.forElementVisible(caption);

    return caption.getText();
  }

  private void mouseOverComponent(Components component) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    switch (component) {
      case GALLERY:
        verifyComponent(gallery);
        js.executeScript("$('div.cke_contents>iframe').contents().find('img.image-gallery').mouseenter()");
        break;
      case SLIDESHOW:
        verifyComponent(slideshow);
        js.executeScript("$('div.cke_contents>iframe').contents().find('img.image-slideshow').mouseenter()");
        break;
      case SLIDER:
        verifyComponent(slider);
        js.executeScript("$('div.cke_contents>iframe').contents().find('img.image-gallery-slider').mouseenter()");
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
        js.executeScript("$('div.cke_contents>iframe').contents().find('img.video-placeholder').mouseenter()");
        break;
      default:
        break;
    }
  }

  public Object modifyComponent(Components component) {
    mouseOverComponent(component);
    wait.forElementVisible(modifyComponentButton);
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
        PageObjectLogging.log("verifyComponentRemoved", "Invalid component: " + component.name()
            + " selected", false);
        break;
    }
    driver.switchTo().defaultContent();
    PageObjectLogging.log("verifyGalleryRemoved", "Click on 'remove button' on gallery", true);
  }

  public void typeCategoryName(String categoryName) {
    jsActions.scrollToElementInModal(categoryInput,modalElement);
    wait.forElementVisible(categoryInput);
    categoryInput.sendKeys(categoryName);
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
    new Actions(driver).sendKeys(categoryInput, Keys.ARROW_DOWN)
        .sendKeys(categoryInput, Keys.ARROW_DOWN).sendKeys(categoryInput, Keys.ENTER).perform();
    PageObjectLogging.log("submitCategory", "category submitted", true);
  }

  public void verifyCategoryPresent(String category) {
    boolean categoryVisible = false;
//    Assertion.assertFalse(categoryList.isEmpty(),"Category list is empty");
    for (WebElement elem : categoryList) {
      if (elem.getText().equals(category)) {
        categoryVisible = true;
      }
    }
    Assertion.assertTrue(categoryVisible, "category " + category + " not present. Available ones: " +
                                          categoryList.stream().map(d->d.getText()).collect(Collectors.joining(", ")) + ".");
  }

  public void verifyCategoryNotPresent(String category) {
    this.scrollTo(categoriesModuleBar);
    wait.forElementNotPresent(By.cssSelector(categoryRemovedSelector.replace("%categoryName%",
            category)));
    boolean categoryVisible = true;
    for (WebElement elem : categoryList) {
      if (elem.getText().equals(category)) {
        categoryVisible = false;
      }
    }
    Assertion.assertTrue(categoryVisible, "category " + category + " present");
  }

  public String selectCategorySuggestions(int categoryNumber) {
    wait.forElementVisible(categorySuggestionsContainer);
    WebElement categoryItem =
        categorySuggestionsContainer.findElements(categorySuggestionsList).get(categoryNumber);
    String categoryName = categoryItem.getText();
    categoryItem.click();
    waitForElementNotVisibleByElement(categorySuggestionsContainer);
    PageObjectLogging.log("selectCategorySuggestions", categoryNumber
        + " category selected from suggestions", true);
    return categoryName;
  }

  public EditCategoryComponentObject editCategory(String categoryName) {
    WebElement editCategory =
        driver.findElement(By.cssSelector(categoryEditSelector.replace("%categoryName%",
            categoryName)));
    WebElement category =
        driver.findElement(By.cssSelector(".category[data-name='" + categoryName + "']"));
    new Actions(driver).moveToElement(category).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
        .perform();
    scrollAndClick(editCategory);
    PageObjectLogging.log("editCategory", "edit category button clicked on category "
        + categoryName, true);
    return new EditCategoryComponentObject(driver);
  }

  public void removeCategory(String categoryName) {
    WebElement removeCategory =
        driver.findElement(By.cssSelector(categoryRemoveSelector.replace("%categoryName%",
            categoryName)));
    WebElement category =
        driver.findElement(By.cssSelector(".category[data-name='" + categoryName + "']"));
    new Actions(driver).moveToElement(category).perform();
    scrollAndClick(removeCategory);
    PageObjectLogging.log("removeCategory", "remove category button clicked on category "
        + categoryName, true);
  }

  public void verifyBlockedUserMessage() {
    wait.forElementVisible(blockedUserMessage1);
    wait.forElementVisible(blockedUserMessage2);
    PageObjectLogging.log("verifyBlockedUserMessage",
            "blocked user message when attempting to create article verified", true);
  }

  private void selectFromContextMenu(WebElement option) {
    wait.forElementVisible(iframe);
    driver.switchTo().frame(iframe);
    Actions actions = new Actions(driver);
    actions.contextClick(visualModeTable).build().perform();
    driver.switchTo().defaultContent();
    driver.switchTo().frame(contextFrame);
    option.click();
    driver.switchTo().defaultContent();
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

  public enum Components {
    PHOTO, GALLERY, SLIDESHOW, SLIDER, VIDEO, VIDEO_PLACEHOLDER
  }

}
