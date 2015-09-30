package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.SourceModeContent;
import com.wikia.webdriver.common.contentpatterns.WikiaGlobalVariables;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject.Alignment;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts.WikiTextShortCutsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.template.TemplatePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SourceEditModePageObject extends EditMode {


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

  public SourceEditModePageObject(WebDriver driver) {
    super(driver);
  }

  public SourceEditModePageObject focusTextArea() {
    jsActions.focus(".cke_source");
    return this;
  }

  public String getSourceContent() {
    return (String) jsActions.execute("$('.cke_source').attr('value')");
  }

  public void checkSourceContent(String desiredContent) {
    wait.forElementClickable(sourceModeTextArea);
    Assertion.assertEquals(getSourceContent(), desiredContent);
  }

  public void checkSourceVideoContent(String desiredContent) {
    Assertion.assertEquals(getSourceContent().substring(1, 38) + getSourceContent().substring(48), desiredContent.substring(1, 38) + desiredContent.substring(48)
    );
  }

  public void clickBold() {
    focusTextArea();
    bold.click();
    LOG.log("clickBold", "bold button was clicked", true, driver);
  }

  public void clickItalic() {
    focusTextArea();
    italic.click();
    LOG.log("clickItalic", "italic button was clicked", true, driver);
  }

  public void clickInternalLink() {
    focusTextArea();
    internalLink.click();
    LOG.log("clickInternalLink", "internal link button was clicked", true, driver);
  }

  public void clickExternalLink() {
    focusTextArea();
    externalLink.click();
    LOG.log("clickExternalLink", "external link button was clicked", true, driver);
  }

  public void clickLvl2Headline() {
    focusTextArea();
    lvl2headLine.click();
    LOG.log("clickExternalLink", "external link button was clicked", true, driver);
  }

  public void clickEmbedFile() {
    focusTextArea();
    embedFile.click();
    LOG.log("clickEmbedFile", "embed file button was clicked", true, driver);
  }

  public void clickEmbedMedia() {
    focusTextArea();
    embedMedia.click();
    LOG.log("clickEmbedMedia", "embed media button was clicked", true, driver);
  }

  public void clickMath() {
    focusTextArea();
    math.click();
    LOG.log("clickMath", "math button was clicked", true, driver);
  }

  public void clickNowiki() {
    focusTextArea();
    nowiki.click();
    LOG.log("clickNoWiki", "nowwiki button was clicked", true, driver);
  }

  public void clickSignature() {
    focusTextArea();
    signature.click();
    LOG.log("clickSignature", "signature button was clicked", true, driver);
  }

  public void clickHorizontalLine() {
    focusTextArea();
    hline.click();
    LOG
        .log("clickHorizontalLine", "horizontal line button was clicked", true, driver);
  }

  public PhotoAddComponentObject clickAddPhoto() {
    focusTextArea();
    photo.click();
    LOG.log("clickAddPhot", "add photo button was clicked", true, driver);
    return new PhotoAddComponentObject(driver);
  }

  public void clickAddGallery() {
    focusTextArea();
    gallery.click();
    LOG.log("clickAddGallery", "add gallery button was clicked", true, driver);
  }

  public VetAddVideoComponentObject clickAddVideo() {
    focusTextArea();
    video.click();
    LOG.log("clickAddVideo", "add video button was clicked", true, driver);
    return new VetAddVideoComponentObject(driver);
  }

  public WikiTextShortCutsComponentObject clickMore() {
    focusTextArea();
    more.click();
    LOG.log("clickMore", "more button was clicked", true, driver);
    return new WikiTextShortCutsComponentObject(driver);
  }

  public void clearSource() {
    sourceModeTextArea.clear();
    LOG.log("clearSource", "source area erased", true, driver);
  }

  public void closeMore() {
    moreClose.click();
  }

  public void addContent(String content) {
    wait.forElementVisible(textArea);
    textArea.sendKeys(content);
    LOG.log("addContent", "content was added", LOG.Type.SUCCESS);
  }

  public SourceEditModePageObject addContentInSourceMode(String content) {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.sendKeys(content);
    LOG.log("addContent", "content was added", LOG.Type.SUCCESS);
    return this;
  }

  public String copyContent() {
    wait.forElementVisible(textArea);
    return textArea.getText();
  }

  public String buildTablePropertiesContent(
      int border, int width, int height, int cellspacing, int cellpadding, Alignment alignment
  ) {
    String tablePropertiesContent = SourceModeContent.TABLE
        .replace("%border%", Integer.toString(border))
        .replace("%cellpadding%", Integer.toString(cellpadding))
        .replace("%cellspacing%", Integer.toString(cellspacing))
        .replace("%float%", alignment.toString())
        .replace("%height%", Integer.toString(height))
        .replace("%width%", Integer.toString(width));
    return tablePropertiesContent;
  }

  public void verifyComponentSelector() {
    wait.forElementVisible(componentSelector);
    LOG.log("verifyComponentSelector", "component selector is visible", true, driver);
  }

  public Object addComponent(String componentName) {
    if ("slideshow".equals(componentName)) {
      wait.forElementVisible(createSlideshow);
      createSlideshow.click();
      LOG.log("addComponent", "selected " + componentName + " component", LOG.Type.SUCCESS);
      return new SlideshowBuilderComponentObject(driver);
    } else if ("gallery".equals(componentName)) {
      wait.forElementVisible(createGallery);
      createGallery.click();
      LOG.log("addComponent", "selected " + componentName + " component", LOG.Type.SUCCESS);
      return new GalleryBuilderComponentObject(driver);
    } else if ("slider".equals(componentName)) {
      wait.forElementVisible(createSlider);
      createSlider.click();
      LOG.log("addComponent", "selected " + componentName + " component", LOG.Type.SUCCESS);
      return new SliderBuilderComponentObject(driver);
    } else {
      LOG
          .logResult("addComponent", "not supported component name: " + componentName, false);
      return null;
    }
  }

  public void checkMainTools() {
    for (int i = 1; i < 17; i++) {
      clearSource();
      clickMore();
      String
          content =
          driver.findElement(
              By.xpath("//section[@class='modalContent']//span[@id='edittools_main']/a[" + i + "]"))
              .getText();
      driver.findElement(
          By.xpath("//section[@class='modalContent']//span[@id='edittools_main']/a[" + i + "]"))
          .click();
      waitForElementNotVisibleByElement(editorModal);
      waitForElementNotVisibleByElement(focusedMode);
      checkSourceContent(content);
    }
  }

  public void checkWikiMarkupTools() {
    for (int i = 1, j = i + 1; i < 21; i++) {
      clearSource();
      clickMore();
      String content =
          (String) jsActions.execute(
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
    for (int i = 1; i < 65; i++) {
      clearSource();
      clickMore();
      String
          content =
          driver.findElement(By.xpath(
              "//section[@class='modalContent']//span[@id='edittools_symbols']/a[" + i + "]"))
              .getText();
      driver.findElement(
          By.xpath("//section[@class='modalContent']//span[@id='edittools_symbols']/a[" + i + "]"))
          .click();
      waitForElementNotVisibleByElement(editorModal);
      waitForElementNotVisibleByElement(focusedMode);
      checkSourceContent(content);
    }
  }

  private String getContent() {
    wait.forElementVisible(sourceModeTextArea);
    return sourceModeTextArea.getAttribute("value");
  }

  public void verifyVideoAlignment(PositionsVideo position) {
    Assertion.assertStringContains(getContent(), position.toString().toLowerCase()
    );
  }

  public void verifyVideoWidth(int widthDesired) {
    String content = getContent();
    int width = Integer.parseInt(
        content.substring(content.indexOf("px") - 4, content.indexOf("px") - 1)
    );
    Assertion.assertNumber(
            widthDesired, width,
            "width is " + width + " should be " + widthDesired
    );
  }

  public void verifyVideoCaption(String desiredCaption) {
    Assertion.assertStringContains(getContent(), desiredCaption);
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
    LOG
            .logResult("appendContent", "text: '" + content + "', added to the source mode", true);
  }

  private void appendNewLine(String content) {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.sendKeys(Keys.ENTER);
    sourceModeTextArea.sendKeys(content);
    LOG
            .logResult("appendNewLine", "text " + content + " added to the source mode in new line",
                       true);
  }

  public void clearContent() {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.clear();
    LOG.log("clearContent", "source mode cleared", LOG.Type.SUCCESS);
  }

  public void verifySourceModeEnabled() {
    wait.forElementVisible(sourceModeTextArea);
    waitForElementNotVisibleByElement(sourceModeLoadingIndicator);
    LOG.log("verifySourceModeEnabled", "source mode enabled", LOG.Type.SUCCESS);
  }

  public void verifySourceOnlyMode() {
    wait.forElementVisible(sourceOnlyModeTextArea);
    if (!(Boolean) jsActions.execute(WikiaGlobalVariables.WG_IS_ARTICLE)) {
      wait.forElementVisible(srcOnlyMode);
      LOG.log("verifySourceOnlyMode", "source only mode enabled", true, driver);
    } else {
      throw new NoSuchElementException("Can not detect the page to be in Edit mode");
    }
  }

  public ArticlePageObject clickPublishButton() {
    wait.forElementVisible(submitButton);
    submitButton.click();
    return new ArticlePageObject(driver);
  }

  public void addCategoryToSourceCode(String catName) {
    sourceModeTextArea.sendKeys(catName);
  }

  public TemplatePageObject clickPublishButtonInTemplateNamespace() {
    wait.forElementVisible(submitButton);
    submitButton.click();
    return new TemplatePageObject(driver);
  }
}
