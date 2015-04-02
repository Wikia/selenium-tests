package com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor;

import com.wikia.webdriver.common.contentpatterns.VEContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Indentation;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertList;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Transclusion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorEditTemplateDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorMediaSettingsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSourceEditorDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorPageObject extends VisualEditorMenu {

  public VisualEditorPageObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = ".ve-ce-documentNode")
  private WebElement editArea;
  @FindBy(css = "ol.ve-ce-branchNode > li")
  private List<WebElement> numList;
  @FindBy(css = "ul.ve-ce-branchNode > li")
  private List<WebElement> bullList;
  @FindBy(css = ".ve-init-mw-viewPageTarget-surface")
  private WebElement veEditorSurface;
  @FindBy(css = ".image.video.video-thumbnail.medium")
  private List<WebElement> videoNodes;
  @FindBy(css = "figure.ve-ce-branchNode a")
  private WebElement mediaNode;
  @FindBy(css = "figure.ve-ce-branchNode a")
  private List<WebElement> mediaNodes;
  @FindBy(css = "figure.wikia-interactive-map-thumbnail")
  private WebElement mapNode;
  @FindBy(css = ".ve-ui-wikiaMediaPreviewWidget-overlay")
  private WebElement previewOverlay;
  @FindBy(css = ".ve-ui-wikiaMediaPreviewWidget-overlay img")
  private WebElement previewImage;
  @FindBy(css = ".ve-ui-wikiaMediaPreviewWidget-videoWrapper")
  private WebElement previewVideoWrapper;
  @FindBy(css = "figure figcaption .caption")
  private WebElement mediaCaption;
  @FindBy(css = ".ve-ce-resizableNode-swHandle")
  private WebElement swResizeHandle;
  @FindBy(css = ".ve-ui-desktopContext .oo-ui-popupWidget-popup")
  private WebElement contextMenu;
  @FindBy(css = ".ve-ui-wikiaFocusWidget")
  private WebElement focusedNode;
  @FindBy(css = ".mw-body-content")
  private WebElement mainContent;
  @FindBy(css = ".media-gallery-wrapper.ve-ce-branchNode")
  private WebElement galleryNode;
  @FindBy(css = ".media-gallery-wrapper.ve-ce-branchNode>div")
  private List<WebElement> galleryNodes;
  @FindBy(css = ".media-gallery-wrapper.ve-ce-branchNode .toggler")
  private WebElement toggler;
  @FindBy(css = ".ve-ce-surface-highlights-focused .ve-ce-focusableNode-highlight")
  private WebElement focusedHighlight;

  private By contextMenuBy = By.cssSelector(".ve-ui-contextSelectWidget");
  private By contextEditBy = By.cssSelector(".oo-ui-labelElement");
  private By blockTransclusionBy = By.cssSelector("div[typeof='mw:Transclusion']");
  private By inlineTransclusionBy = By.cssSelector("span[typeof='mw:Transclusion']");

  public void selectMediaAndDelete() {
    waitForElementVisibleByElement(editArea);
    editArea.click();
    waitForElementVisibleByElement(mediaNode);
    mediaNode.click();
    Actions actions2 = new Actions(driver);
    actions2.sendKeys(Keys.DELETE).build().perform();
    PageObjectLogging.log("selectMediaAndDelete", "Selected media and click delete", true, driver);
  }

  public void typeTextArea(String text) {
    waitForElementVisibleByElement(editArea);
    editArea.sendKeys(text);
    PageObjectLogging.log("write", "text " + text + "written", true);
  }

  public void press(Keys key) {
    editArea.sendKeys(key);
    PageObjectLogging.log("press", "key " + key.toString() + "pressed", true);
  }

  public void selectText(int from, int to) {
    String
        showSelectiontJS =
        "ve.init.target.getSurface().getModel().change(" +
        "null, new ve.dm.LinearSelection(" +
        "ve.init.target.getSurface().getModel().getDocument(),new ve.Range(" +
        from + "," + to + " )));";
    ((JavascriptExecutor) driver).executeScript(showSelectiontJS);
  }

  public void selectText(String text) {
    waitForElementVisibleByElement(editArea);
    String textDump = editArea.getText();
    int
        from =
        textDump.indexOf(text) + 1; //+1 because index is counted differently in selectText() method
    int to = from + text.length() + 1;
    selectText(from, to);
  }

  public int[] getTextIndex(String text) {
    String textDump = editArea.getText();
    int[] indexes = new int[2];
    //+1 because index is counted differently in selectText() method
    indexes[0] = textDump.indexOf(text) + 1;
    indexes[1] = indexes[0] + text.length();
    if (indexes[0] == 0) {
      throw new NoSuchElementException("String: " + text + " is not found");
    }
    return indexes;
  }

  public void removeText(String text) {
    selectText(text);
    editArea.sendKeys(Keys.DELETE);
  }

  public void verifyNumList(List<String> elements) {
    for (int i = 0; i < elements.size(); i++) {
      Assertion.assertEquals(elements.get(i), numList.get(i).getText());
    }
  }

  public void verifyBullList(List<String> elements) {
    for (int i = 0; i < elements.size(); i++) {
      Assertion.assertEquals(elements.get(i), bullList.get(i).getText());
    }
  }

  public void verifyFormatting(Formatting format, String text) {
    Assertion.assertEquals(text, editArea.findElement(format.getTag()).getText());
  }

  public void verifyStyle(Style style, String text) {
    Assertion.assertEquals(text, editArea.findElement(style.getTag()).getText());
  }

  public void verifyEditorSurfacePresent() {
    waitForElementVisibleByElement(veMode);
    waitForElementVisibleByElement(veEditorSurface);
    PageObjectLogging.log("verifyEditorSurface", "VE editor surface is displayed", true, driver);
  }

  public ArticlePageObject clickVEEditAndPublish(String content) {
    typeTextArea(content);
    VisualEditorSaveChangesDialog save = clickPublishButton();
    save.savePage();
    return new ArticlePageObject(driver);
  }

  public void verifyMapPresent() {
    waitForElementVisibleByElement(mapNode);
    PageObjectLogging.log("verifyMapPresent", "VE map is displayed", true);
  }

  public void verifyNoVideo() {
    if (checkIfElementOnPage(mediaNode)) {
      throw new AssertionError("Media Node is still on the page");
    } else {
      PageObjectLogging.log("verifyNoVideo", "Verified no video is on page", true, driver);
    }
  }

  public void verifyVideos(int expected) {
    waitForElementVisibleByElement(mediaNode);
    Assertion.assertNumber(expected, videoNodes.size(),
                           "Checking the correct number of video nodes added");
    PageObjectLogging.log("verifyVideos", videoNodes.size() + " videos displayed", true);
  }

  public void verifyGalleries(int expected) {
    if (expected > 0) {
      waitForElementVisibleByElement(galleryNode);
    }
    Assertion.assertNumber(
        expected,
        getNumOfElementOnPage(By.cssSelector(".media-gallery-wrapper.ve-ce-branchNode")),
        "Checking the correct number of gallery nodes");
  }

  public void verifyMediasInGallery(int expected) {
    waitForElementByElement(galleryNode);
    String className = galleryNode.getAttribute("class");
    String count = className.substring(className.indexOf("count-"));
    int numOfMediasInGallery = Integer.parseInt(count.substring(count.indexOf('-') + 1));
    Assertion.assertNumber(expected, numOfMediasInGallery,
                           "Checking the correct number of media in gallery");
    PageObjectLogging
        .log("verifyMediasInGallery", numOfMediasInGallery + " medias displayed", true);
  }

  public void verifyMedias(int expected) {
    waitForElementByElement(mediaNode);
    waitForElementVisibleByElement(mediaNode);
    Assertion.assertNumber(expected, mediaNodes.size(),
                           "Checking the correct number of media nodes added");
    PageObjectLogging.log("verifyMedias", mediaNodes.size() + " media displayed", true);
  }

  public VisualEditorMediaSettingsDialog openMediaSettings() {
    waitForElementByElement(editArea);
    waitForElementVisibleByElement(mediaNode);
    waitForElementByElement(focusedNode);
    clickContextMenu();
    return new VisualEditorMediaSettingsDialog(driver);
  }

  private void clickContextMenu() {
    waitForElementVisibleByElement(contextMenu);
    WebElement contextEdit;
    try {
      contextEdit = contextMenu.findElement(contextMenuBy).findElement(contextEditBy);
      contextEdit.click();
    } catch (StaleElementReferenceException e) {
      contextEdit = contextMenu.findElement(contextMenuBy).findElement(contextEditBy);
      contextEdit.click();
    }
  }

  public void typeReturn() {
    waitForElementVisibleByElement(editArea);
    editArea.sendKeys(Keys.RETURN);
  }

  public void typeTextInAllFormat(String text) {
    for (Formatting format : Formatting.values()) {
      PageObjectLogging.log("Formatting selection", format.toString() + " selected", true);
      selectFormatting(format);
      typeTextArea(text);
      typeReturn();
      if ("PREFORMATTED".equals(format.name())) {
        selectFormatting(Formatting.PARAGRAPH);
      }
    }
  }

  public void typeTextInAllStyle(String text) {
    for (Style style : Style.values()) {
      PageObjectLogging.log("Style selection", style.toString() + " selected", true);
      selectStyle(style);
      typeTextArea(text);
      typeReturn();
    }
  }

  public void typeTextInAllList(String text) {
    typeReturn();
    typeTextArea(text);
    insertList(InsertList.BULLET_LIST);
    typeReturn();
    selectIndentation(Indentation.DECREASE);
    typeTextArea(text);
    insertList(InsertList.NUMBERED_LIST);
    typeReturn();
    selectIndentation(Indentation.DECREASE);
  }

  public void copyAndPaste() {
    waitForElementClickableByElement(editArea);
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "c"));
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    PageObjectLogging.log("copyAndPaste", editArea.getText(), true, driver);
  }

  public VisualEditorPageObject typeInSourceEditor(String text) {
    VisualEditorSourceEditorDialog veSrcDialog =
        (VisualEditorSourceEditorDialog) openDialogFromMenu(InsertDialog.SOURCE_EDITOR);
    veSrcDialog.typeInEditArea(text);
    return veSrcDialog.clickApplyChangesButton();
  }

  public void verifyPreviewVideoPlay(String providerName) {
    waitForElementVisibleByElement(previewOverlay);
    waitForElementVisibleByElement(previewVideoWrapper);
    VideoComponentObject video = new VideoComponentObject(driver, previewVideoWrapper);
    video.verifyVideoAutoplay(providerName, true);
    PageObjectLogging.log("verifyPreviewVideoPlay", "Preview for Video loaded", true, driver);
  }

  public void verifyPreviewImage() {
    waitForElementVisibleByElement(previewOverlay);
    waitForElementVisibleByElement(previewImage);
    PageObjectLogging.log("verifyPreviewImage", "Preview for Image loaded", true, driver);
  }

  public void verifyVideoCaption(String caption) {
    waitForElementVisibleByElement(mediaNode);
    waitForElementByElement(mediaCaption);
    Assertion.assertEquals(caption, mediaCaption.getText(), "The video caption does not match");
    PageObjectLogging.log("verifyVideoCaption", "Video caption matches", true, driver);
  }

  public void selectMedia() {
    waitForElementByElement(mediaNode);
    mediaNode.click();
  }

  public void selectMediaByIndex(int index) {
    WebElement selectedMedia = mediaNodes.get(index);
    waitForElementVisibleByElement(selectedMedia);
    selectedMedia.click();
  }

  public void selectMediaByTitle(String title) {
    WebElement selectedMedia = getElementByValue(mediaNodes, "href", title);
    selectedMedia.click();
  }

  public void randomResizeOnMedia() {
    int randomX = (int) (Math.random() * 100);
    int randomY = (int) (-Math.random() * 100);
    resizeMedia(randomX, randomY);
  }

  private void resizeMedia(int xOffSet, int yOffset) {
    PageObjectLogging.log("resizeMedia", "Before resizing", true, driver);
    selectMedia();
    waitForElementVisibleByElement(swResizeHandle);
    Actions actions = new Actions(driver);
    actions
        .dragAndDropBy(swResizeHandle, xOffSet, yOffset)
        .build()
        .perform();
    PageObjectLogging.log("resizeMedia", "After resizing", true, driver);
  }

  public void verifyVideoSWHandleMoved(Point source) {
    verifyElementMoved(source, swResizeHandle);
  }

  public Point getVideoSWHandle() {
    return swResizeHandle.getLocation();
  }

  public void verifyVideoResized(Dimension source) {
    verifyElementResized(source, mediaNode);
  }

  public Dimension getVideoDimension() {
    return mediaNode.getSize();
  }

  public int getNumberOfBlockTransclusion() {
    return getNumOfElementOnPage(blockTransclusionBy);
  }

  public int getNumberOfInlineTransclusion() {
    return getNumOfElementOnPage(inlineTransclusionBy);
  }

  public void verifyNumberOfBlockTransclusion(int expected) {
    Assertion.assertNumber(expected, getNumOfElementOnPage(blockTransclusionBy),
                           "The number of blocked transclusion node is not equal");
  }

  public void verifyNumberOfInlineTransclusion(int expected) {
    Assertion.assertNumber(expected, getNumOfElementOnPage(inlineTransclusionBy),
                           "The number of inline transclusion node is not equal");
  }


  public void selectGallery(int index) {
    WebElement selectedGallery = galleryNodes.get(index);
    waitForElementClickableByElement(selectedGallery);
    selectedGallery.click();

  }

  public void deleteGallery(int index) {
    selectGallery(index);
    //wait for highlight
    waitForElementByElement(focusedHighlight);
    //TODO check if any future webdriver upgrade would resolve having to use separate logic
    if("Chrome".equalsIgnoreCase(getBrowser())) {
      Actions actions2 = new Actions(driver);
      actions2.sendKeys(Keys.DELETE).build().perform();
    } else {
      editArea.sendKeys(Keys.DELETE);
    }
  }

  public void deleteTransclusion(int index, Transclusion transclusion) {
    clickTransclusion(index, transclusion);
    Actions actions2 = new Actions(driver);
    actions2.sendKeys(Keys.DELETE).build().perform();
  }

  public void clickTransclusion(int index, Transclusion transclusion) {
    Point tempLocation = getTransclusionLocation(index, transclusion);
    int xOffset = 10;
    int yOffset = 10;
    int tempLeft = tempLocation.x + xOffset;
    int tempTop = tempLocation.y + yOffset;
    editArea.click();
    Actions actions = new Actions(driver);
    actions.moveToElement(mainContent, tempLeft, tempTop).clickAndHold().release().build()
        .perform();
    WebElement contextEdit = contextMenu.findElement(contextMenuBy).findElement(contextEditBy);
    waitForElementVisibleByElement(contextEdit);
    PageObjectLogging
        .log("clickTransclusion", "Clicked at X: " + tempLeft + ", Y: " + tempTop, true,
             driver);
  }

  private Point getTransclusionLocation(int index, Transclusion transclusion) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Object
        templateBounding =
        js.executeScript(VEContent.BOUNDING_SCRIPT, transclusion.getCssSelector(), index);
    Map<String, String> mapBounding = (Map) templateBounding;
    int tempLeft = getMapValueAsInt(String.valueOf(mapBounding.get("left")));
    int tempTop = getMapValueAsInt(String.valueOf(mapBounding.get("top")));
    return new Point(tempLeft, tempTop);
  }

  private int getMapValueAsInt(String input) {
    return new BigDecimal(input).intValue();
  }

  public VisualEditorEditTemplateDialog openEditTemplateDialog() {
    waitForElementVisibleByElement(editArea);
    waitForElementVisibleByElement(focusedNode);
    clickContextMenu();
    return new VisualEditorEditTemplateDialog(driver);
  }
}
