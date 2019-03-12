package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.WikiaGlobalVariables;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts.WikiTextShortCutsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SourceEditModePageObject extends EditMode {

  @FindBy(css = "#wpSave")
  private WebElement submitButton;
  @FindBy(css = "#mw-editbutton-bold")
  private WebElement bold;
  @FindBy(css = "#mw-editbutton-italic")
  private WebElement italic;
  @FindBy(css = "#mw-editbutton-link")
  private WebElement internalLink;
  @FindBy(css = "#mw-editbutton-extlink")
  private WebElement externalLink;
  @FindBy(css = "#mw-editbutton-headline")
  private WebElement lvl2headLine;
  @FindBy(css = "#mw-editbutton-image")
  private WebElement embedFile;
  @FindBy(css = "#mw-editbutton-media")
  private WebElement embedMedia;
  @FindBy(css = "#mw-editbutton-math")
  private WebElement math;
  @FindBy(css = "#mw-editbutton-nowiki")
  private WebElement nowiki;
  @FindBy(css = "#mw-editbutton-signature")
  private WebElement signature;
  @FindBy(css = "#mw-editbutton-hr")
  private WebElement hline;
  @FindBy(css = "#mw-editbutton-wmu")
  private WebElement photo;
  @FindBy(css = "#mw-editbutton-wpg")
  private WebElement gallery;
  @FindBy(css = "#mw-editbutton-vet")
  private WebElement video;
  @FindBy(css = ".cke_toolbar_source .cke_toolbar_expand")
  private WebElement more;
  @FindBy(css = ".close.wikia-chiclet-button")
  private WebElement moreClose;
  @FindBy(css = "section#WikiaPhotoGalleryEditor")
  private WebElement componentSelector;
  @FindBy(css = "a.wikia-button[type='2']")
  private WebElement createSlideshow;
  @FindBy(css = "a.wikia-button[type='1']")
  private WebElement createGallery;
  @FindBy(css = "a.wikia-button[type='3']")
  private WebElement createSlider;
  @FindBy(css = ".loading-indicator")
  private WebElement sourceModeLoadingIndicator;
  @FindBy(css = ".editpage-editarea")
  private WebElement sourceOnlyModeTextArea;
  @FindBy(css = ".modalWrapper")
  private WebElement editorModal;
  @FindBy(css = ".blackout")
  private WebElement focusedMode;
  @FindBy(css = "#editarea .ace_scroller")
  private WebElement textArea;
  @FindBy(css = ".cke_source")
  private WebElement sourceModeTextArea;
  @FindBy(css = ".close.wikia-chiclet-button")
  private WebElement draftNotificationClose;
  @FindBy(css = ".modalContent")
  private WebElement modalText;
  @FindBy(css = "#wpDiff")
  private WebElement showChanges;
  @FindBy(css = ".diff-deletedline")
  private WebElement diffDeletedLine;
  @FindBy(css = ".wds-banner-notification__text")
  private WebElement draftBanner;
  @FindBy(css = "#discard")
  private WebElement discard;
  @FindBy(css = "#keep")
  private WebElement keep;
  @FindBy(css = ".diff-deletedline")
  private WebElement deletedLine;


  public SourceEditModePageObject openArticle(String articleTitle) {
    String url = urlBuilder.getUrl() + URLsContent.WIKI_DIR + articleTitle;
    url = urlBuilder.appendQueryStringToURL(url, URLsContent.ACTION_EDIT);
    url = urlBuilder.appendQueryStringToURL(url, URLsContent.SOURCE_MODE);
    getUrl(url);

    return this;
  }

  public SourceEditModePageObject focusTextArea() {
    jsActions.focus(".cke_source");
    return this;
  }

  public String getSourceContent() {
    wait.forElementVisible(sourceModeTextArea);

    return (String) jsActions.execute("$('.cke_source').attr('value')");
  }

  public void checkSourceContent(String desiredContent) {
    wait.forElementClickable(sourceModeTextArea);
    Assertion.assertEquals(getSourceContent(), desiredContent);
  }

  public void checkSourceVideoContent(String desiredContent) {
    Assertion.assertEquals(getSourceContent().replaceAll("(-\\d+)\\|", "|"), desiredContent);
  }

  public void clickBold() {
    focusTextArea();
    bold.click();
    Log.log("clickBold", "bold button was clicked", true, driver);
  }

  public void clickItalic() {
    focusTextArea();
    italic.click();
    Log.log("clickItalic", "italic button was clicked", true, driver);
  }

  public void clickInternalLink() {
    focusTextArea();
    internalLink.click();
    Log.log("clickInternalLink", "internal link button was clicked", true, driver);
  }

  public void clickExternalLink() {
    focusTextArea();
    externalLink.click();
    Log.log("clickExternalLink", "external link button was clicked", true, driver);
  }

  public void clickLvl2Headline() {
    focusTextArea();
    lvl2headLine.click();
    Log.log("clickExternalLink", "external link button was clicked", true, driver);
  }

  public void clickEmbedFile() {
    focusTextArea();
    embedFile.click();
    Log.log("clickEmbedFile", "embed file button was clicked", true, driver);
  }

  public void clickEmbedMedia() {
    focusTextArea();
    embedMedia.click();
    Log.log("clickEmbedMedia", "embed media button was clicked", true, driver);
  }

  public void clickMath() {
    focusTextArea();
    math.click();
    Log.log("clickMath", "math button was clicked", true, driver);
  }

  public void clickNowiki() {
    focusTextArea();
    nowiki.click();
    Log.log("clickNoWiki", "nowwiki button was clicked", true, driver);
  }

  public void clickShowChanges() {
    showChanges.click();
    Log.log("clickShowChanges", "Show changes button was clicked", true, driver);
  }

  public void clickSignature() {
    focusTextArea();
    signature.click();
    Log.log("clickSignature", "signature button was clicked", true, driver);
  }

  public void clickHorizontalLine() {
    focusTextArea();
    hline.click();
    Log.log("clickHorizontalLine", "horizontal line button was clicked", true, driver);
  }

  public PhotoAddComponentObject clickAddPhoto() {
    focusTextArea();
    photo.click();
    Log.log("clickAddPhot", "add photo button was clicked", true, driver);
    return new PhotoAddComponentObject(driver);
  }

  public void clickAddGallery() {
    focusTextArea();
    gallery.click();
    Log.log("clickAddGallery", "add gallery button was clicked", true, driver);
  }

  public VetAddVideoComponentObject clickAddVideo() {
    focusTextArea();
    video.click();
    Log.log("clickAddVideo", "add video button was clicked", true, driver);
    return new VetAddVideoComponentObject(driver);
  }

  public WikiTextShortCutsComponentObject clickMore() {
    focusTextArea();
    more.click();
    Log.log("clickMore", "more button was clicked", true, driver);
    return new WikiTextShortCutsComponentObject(driver);
  }

  public void clearSource() {
    sourceModeTextArea.clear();
    Log.log("clearSource", "source area erased", true, driver);
  }

  public SourceEditModePageObject addContentInSourceMode(String content) {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.sendKeys(content);
    Log.log("addContent", "the following content was added: " + content, true);
    return this;
  }

  public void verifyComponentSelector() {
    wait.forElementVisible(componentSelector);
    Log.log("verifyComponentSelector", "component selector is visible", true, driver);
  }

  public Object addComponent(String componentName) {
    if ("slideshow".equals(componentName)) {
      wait.forElementVisible(createSlideshow);
      createSlideshow.click();
      Log.log("addComponent", "selected " + componentName + " component", true);
      return new SlideshowBuilderComponentObject(driver);
    } else if ("gallery".equals(componentName)) {
      wait.forElementVisible(createGallery);
      createGallery.click();
      Log.log("addComponent", "selected " + componentName + " component", true);
      return new GalleryBuilderComponentObject(driver);
    } else if ("slider".equals(componentName)) {
      wait.forElementVisible(createSlider);
      createSlider.click();
      Log.log("addComponent", "selected " + componentName + " component", true);
      return new SliderBuilderComponentObject(driver);
    } else {
      Log.log("addComponent", "not supported component name: " + componentName, false);
      return null;
    }
  }

  public void checkMainTools() {
    // edit tools 'Insert' section in MediaWiki:Edittools
    int wikitextShortcutsInsertSection = 17;
    for (int i = 1; i < wikitextShortcutsInsertSection; i++) {
      clearSource();
      clickMore();
      String content = driver.findElement(By.xpath(
          "//section[@class='modalContent']//span[@id='edittools_main']/a[" + i + "]")).getText();
      driver.findElement(By.xpath(
          "//section[@class='modalContent']//span[@id='edittools_main']/a[" + i + "]")).click();
      waitForElementNotVisibleByElement(editorModal);
      waitForElementNotVisibleByElement(focusedMode);
      checkSourceContent(content);
    }
  }

  public void checkWikiMarkupTools() {
    // edit tools 'Wiki markup' section in MediaWiki:Edittools
    int wikitextShortcutsWikiMarkupSection = 21;
    for (int i = 1; i < wikitextShortcutsWikiMarkupSection; i++) {
      clearSource();
      clickMore();
      String content = (String) jsActions.execute(
          "$('.modalContent #edittools_wikimarkup a:nth-child(" + (i + 1) + ")').text()");
      driver.findElement(By.xpath(
          "//section[@class='modalContent']//span[@id='edittools_wikimarkup']/a[" + i + "]"))
          .click();
      waitForElementNotVisibleByElement(editorModal);
      waitForElementNotVisibleByElement(focusedMode);
      checkSourceContent(content);
    }
  }

  public void checkSymbolsTools() {
    // edit tools 'Symbols' section in MediaWiki:Edittools
    int wikitextShortcutsSymbolsSection = 65;
    for (int i = 1; i < wikitextShortcutsSymbolsSection; i++) {
      clearSource();
      clickMore();
      String content = driver.findElement(By.xpath(
          "//section[@class='modalContent']//span[@id='edittools_symbols']/a[" + i + "]"))
          .getText();
      driver.findElement(By.xpath(
          "//section[@class='modalContent']//span[@id='edittools_symbols']/a[" + i + "]")).click();
      waitForElementNotVisibleByElement(editorModal);
      waitForElementNotVisibleByElement(focusedMode);
      checkSourceContent(content);
    }
  }

  public String getContent() {
    wait.forElementVisible(sourceModeTextArea);

    return sourceModeTextArea.getAttribute("value").trim();
  }

  public void verifyVideoAlignment(PositionsVideo position) {
    Assertion.assertStringContains(getContent(), position.toString().toLowerCase());
  }

  public int getVideoWidth() {
    String content = getContent();

    return Integer.parseInt(content.substring(content.indexOf("px") - 4,
                                              content.indexOf("px") - 1
    ));
  }

  /**
   * adds source code that will create Table of Contents on the article
   */
  public void addTOC() {
    clearContent();
    appendContent(PageContent.ARTICLE_WITH_TOC_LINES);
  }

  private void appendContent(String content) {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.sendKeys(content);
    Log.log("appendContent", "text: '" + content + "', added to the source mode", true);
  }

  public void clearContent() {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.clear();
    Log.log("clearContent", "source mode cleared", true);
  }

  public void closeDraftNotification() {
    draftNotificationClose.click();
  }

  public String getModalText() {
    return modalText.getText();
  }

  public String getDeletedText() {
    return deletedLine.getText();
  }

  public String getBannerText() {
    return draftBanner.getText();
  }

  public void setDiscardChanges() { discard.click(); }
  public void setKeepChanges() { keep.click(); }

  public void verifySourceModeEnabled() {
    wait.forElementVisible(sourceModeTextArea);
    waitForElementNotVisibleByElement(sourceModeLoadingIndicator);
    Log.log("verifySourceModeEnabled", "source mode enabled", true);
  }

  public void verifySourceOnlyMode() {
    wait.forElementVisible(sourceOnlyModeTextArea);
    if (!(Boolean) jsActions.execute(WikiaGlobalVariables.WG_IS_ARTICLE)) {
      wait.forElementVisible(srcOnlyMode);
      Log.log("verifySourceOnlyMode", "source only mode enabled", true, driver);
    } else {
      throw new NoSuchElementException("Can not detect the page to be in Edit mode");
    }
  }

  public ArticlePageObject clickPublishButton() {
    wait.forElementVisible(submitButton);
    submitButton.click();
    return new ArticlePageObject();
  }

  public void waitForDraftToBeSaved() {
    try {
      Thread.sleep(5001);
    } catch (InterruptedException e) {
      Log.log("ERROR","waiting for draft save interrupted", false);
    }
  }
}
