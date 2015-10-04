package com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

import com.wikia.webdriver.common.contentpatterns.VEContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Indentation;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.InsertList;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Transclusion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorEditTemplateDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorMediaSettingsDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSourceEditorDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Robert 'rochan' Chan
 */
public class VisualEditorPageObject extends VisualEditorMenu {

  private WebElement mediaNode;
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
  @FindBy(css = ".media-gallery-wrapper.ve-ce-branchNode .toggler")
  private WebElement toggler;
  @FindBy(css = ".ve-ce-surface-highlights-focused .ve-ce-focusableNode-highlight")
  private WebElement focusedHighlight;
  @FindBy(css = ".ve-init-mw-viewPageTarget-surface")
  private WebElement veSurface;
  @FindBy(css = ".oo-ui-popupWidget-body .oo-ui-widget-enabled")
  private WebElement infoboxPopup;
  @FindBy(css = ".oo-ui-processDialog-actions-primary .oo-ui-buttonElement")
  private WebElement applyChangesButton;
  @FindBy(css = ".oo-ui-labelElement.oo-ui-popupToolGroup.oo-ui-listToolGroup")
  private WebElement insertDropdownMenuButton;
  @FindBy(css = ".oo-ui-toolGroup-tools .oo-ui-icon-infobox")
  private WebElement infoboxInDropdownMenu;
  @FindBy(css = ".ve-ce-documentNode")
  private WebElement editArea;
  @FindBy(css = "ol.ve-ce-branchNode > li")
  private List<WebElement> numList;
  @FindBy(css = "ul.ve-ce-branchNode > li")
  private List<WebElement> bullList;
  @FindBy(css = ".oo-ui-popupToolGroup-handle")
  private List<WebElement> toolsList;
  @FindBy(css = ".oo-ui-tool-title")
  private List<WebElement> insertMenuTools;
  @FindBy(css = ".oo-ui-labelElement.oo-ui-optionWidget")
  private List<WebElement> infoboxTemplatesList;
  @FindBy(css = ".ve-ui-mwParameterPage-field .oo-ui-inputWidget textarea")
  private List<WebElement> parametersFieldList;
  @FindBy(css = ".oo-ui-buttonElement-button")
  private List<WebElement> buttonsList;
  @FindBy(css = ".image.video.video-thumbnail.medium")
  private List<WebElement> videoNodes;
  @FindBy(css = "figure.ve-ce-branchNode a")
  private List<WebElement> mediaNodes;
  @FindBy(css = ".media-gallery-wrapper.ve-ce-branchNode>div")
  private List<WebElement> galleryNodes;
  @FindBy(css = "figure.ve-ce-branchNode")
  private By contextMenuBy = By.cssSelector(".ve-ui-contextSelectWidget");
  private By contextEditBy = By.cssSelector(".oo-ui-labelElement");
  private By blockTransclusionBy = By.cssSelector("div[typeof='mw:Transclusion']");
  private By inlineTransclusionBy = By.cssSelector("span[typeof='mw:Transclusion']");

  public VisualEditorPageObject(WebDriver driver) {
    super(driver);
  }

  public void selectMediaAndDelete() {
    wait.forElementVisible(editArea);
    editArea.click();
    wait.forElementVisible(mediaNode);
    mediaNode.click();
    deleteMediaNode();
    LOG.success("selectMediaAndDelete", "Selected media and click delete", true);
  }

  private void deleteMediaNode() {
    jsActions.execute("$(\"figure\").trigger($.Event(\"keydown\", {keyCode: 46}))");
  }

  public void typeTextArea(String text) {
    wait.forElementVisible(editArea);
    editArea.sendKeys(text);
    LOG.success("write", "text " + text + "written");
  }

  public void press(Keys key) {
    editArea.sendKeys(key);
    LOG.success("press", "key " + key.toString() + "pressed");
  }

  public void selectText(int from, int to) {
    String showSelectiontJS =
        "ve.init.target.getSurface().getModel().change(" + "null, new ve.dm.LinearSelection("
            + "ve.init.target.getSurface().getModel().getDocument(),new ve.Range(" + from + ","
            + to + " )));";
    ((JavascriptExecutor) driver).executeScript(showSelectiontJS);
  }

  public void selectText(String text) {
    wait.forElementVisible(editArea);
    String textDump = editArea.getText();
    int from = textDump.indexOf(text) + 1; // +1 because index is counted differently in
                                           // selectText() method
    int to = from + text.length() + 1;
    selectText(from, to);
  }

  public int[] getTextIndex(String text) {
    String textDump = editArea.getText();
    int[] indexes = new int[2];
    // +1 because index is counted differently in selectText() method
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
      Assertion.assertEquals(numList.get(i).getText(), elements.get(i));
    }
  }

  public void verifyBullList(List<String> elements) {
    for (int i = 0; i < elements.size(); i++) {
      Assertion.assertEquals(bullList.get(i).getText(), elements.get(i));
    }
  }

  public void verifyFormatting(Formatting format, String text) {
    Assertion.assertEquals(editArea.findElement(format.getTag()).getText(), text);
  }

  public void verifyStyle(Style style, String text) {
    Assertion.assertEquals(editArea.findElement(style.getTag()).getText(), text);
  }

  public void verifyEditorSurfacePresent() {
    wait.forElementVisible(veMode);
    wait.forElementVisible(veSurface);
    LOG.success("verifyEditorSurface", "VE editor surface is displayed", true);
  }

  public ArticlePageObject clickVEEditAndPublish(String content) {
    typeTextArea(content);
    VisualEditorSaveChangesDialog save = clickPublishButton();
    save.savePage();
    return new ArticlePageObject(driver);
  }

  public void verifyMapPresent() {
    wait.forElementVisible(mapNode);
    LOG.success("verifyMapPresent", "VE map is displayed");
  }

  public void verifyNoVideo() {
    if (isElementOnPage(mediaNode)) {
      throw new AssertionError("Media Node is still on the page");
    } else {
      LOG.success("verifyNoVideo", "Verified no video is on page", true);
    }
  }

  public void verifyVideos(int expected) {
    wait.forElementVisible(editArea);
    Assertion.assertNumber(videoNodes.size(), expected,
        "Checking the correct number of video nodes added");
    LOG.success("verifyVideos", videoNodes.size() + " videos displayed");
  }

  public void verifyGalleries(int expected) {
    if (expected > 0) {
      wait.forElementVisible(galleryNode);
    }
    Assertion.assertNumber(
        getNumOfElementOnPage(By.cssSelector(".media-gallery-wrapper.ve-ce-branchNode")), expected,
        "Checking the correct number of gallery nodes");
  }

  public void verifyMediasInGallery(int expected) {
    wait.forElementVisible(galleryNode);
    String className = galleryNode.getAttribute("class");
    String count = className.substring(className.indexOf("count-"));
    int numOfMediasInGallery = Integer.parseInt(count.substring(count.indexOf('-') + 1));
    Assertion.assertNumber(numOfMediasInGallery, expected,
        "Checking the correct number of media in gallery");
    LOG.result("verifyMediasInGallery", numOfMediasInGallery + " medias displayed", true);
  }

  public void verifyMedias(int expected) {
    wait.forElementVisible(mediaNode);
    wait.forElementVisible(mediaNode);
    Assertion.assertNumber(mediaNodes.size(), expected,
        "Checking the correct number of media nodes added");
    LOG.success("verifyMedias", mediaNodes.size() + " media displayed");
  }

  public VisualEditorMediaSettingsDialog openMediaSettings() {
    wait.forElementVisible(editArea);
    wait.forElementVisible(mediaNode);
    wait.forElementVisible(focusedNode);
    clickContextMenu();
    return new VisualEditorMediaSettingsDialog(driver);
  }

  private void clickContextMenu() {
    wait.forElementVisible(contextMenu);
    WebElement contextEdit;
    try {
      contextEdit = contextMenu.findElement(contextMenuBy).findElement(contextEditBy);
      contextEdit.click();
    } catch (StaleElementReferenceException e) {
      LOG.info("Stale Element Reference", e);
      contextEdit = contextMenu.findElement(contextMenuBy).findElement(contextEditBy);
      contextEdit.click();
    }
  }

  public void typeReturn() {
    wait.forElementVisible(editArea);
    editArea.sendKeys(Keys.RETURN);
  }

  public void typeTextInAllFormat(String text) {
    for (Formatting format : Formatting.values()) {
      LOG.success("Formatting selection", format.toString() + " selected");
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
      LOG.success("Style selection", style.toString() + " selected");
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
    wait.forElementClickable(editArea);
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "c"));
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    editArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
    LOG.success("copyAndPaste", editArea.getText(), true);
  }

  public VisualEditorPageObject typeInSourceEditor(String text) {
    VisualEditorSourceEditorDialog veSrcDialog =
        (VisualEditorSourceEditorDialog) openDialogFromMenu(InsertDialog.SOURCE_EDITOR);
    veSrcDialog.typeInEditArea(text);
    return veSrcDialog.clickApplyChangesButton();
  }

  public void verifyPreviewVideoPlay(String providerName) {
    wait.forElementVisible(previewOverlay);
    wait.forElementVisible(previewVideoWrapper);
    VideoComponentObject video = new VideoComponentObject(driver, previewVideoWrapper);
    video.verifyVideoAutoplay(providerName, true);
    LOG.success("verifyPreviewVideoPlay", "Preview for Video loaded", true);
  }

  public void verifyPreviewImage() {
    wait.forElementVisible(previewOverlay);
    wait.forElementVisible(previewImage);
    LOG.success("verifyPreviewImage", "Preview for Image loaded", true);
  }

  public void verifyVideoCaption(String caption) {
    wait.forElementVisible(mediaNode);
    wait.forElementVisible(mediaCaption);
    Assertion.assertEquals(caption, mediaCaption.getText(), "The video caption does not match");
    LOG.success("verifyVideoCaption", "Video caption matches", true);
  }

  public void selectMedia() {
    wait.forElementVisible(mediaNode);
    mediaNode.click();
  }

  public void selectMediaByIndex(int index) {
    WebElement selectedMedia = mediaNodes.get(index);
    wait.forElementVisible(selectedMedia);
    scrollAndClick(selectedMedia, 80);
  }

  public void randomResizeOnMedia() {
    int randomX = (int) (Math.random() * 100);
    int randomY = (int) (-Math.random() * 100);
    resizeMedia(randomX, randomY);
  }

  private void resizeMedia(int xOffSet, int yOffset) {
    LOG.success("resizeMedia", "Before resizing", true);
    selectMedia();
    wait.forElementVisible(swResizeHandle);
    Actions actions = new Actions(driver);
    actions.dragAndDropBy(swResizeHandle, xOffSet, yOffset).build().perform();
    LOG.success("resizeMedia", "After resizing", true);
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
    Assertion.assertNumber(getNumOfElementOnPage(blockTransclusionBy), expected,
        "The number of blocked transclusion node is not equal");
  }

  public void verifyNumberOfInlineTransclusion(int expected) {
    Assertion.assertNumber(getNumOfElementOnPage(inlineTransclusionBy), expected,
        "The number of inline transclusion node is not equal");
  }


  public void selectGallery(int index) {
    WebElement selectedGallery = galleryNodes.get(index);
    wait.forElementClickable(selectedGallery);
    selectedGallery.click();

  }

  public void deleteGallery(int index) {
    selectGallery(index);
    // wait for highlight
    wait.forElementVisible(focusedHighlight);
    // TODO check if any future webdriver upgrade would resolve having to use separate logic
    if ("Chrome".equalsIgnoreCase(Configuration.getBrowser())) {
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
    wait.forElementVisible(contextEdit);
    LOG.success("clickTransclusion", "Clicked at X: " + tempLeft + ", Y: " + tempTop, true);
  }

  private Point getTransclusionLocation(int index, Transclusion transclusion) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    Object templateBounding =
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
    wait.forElementVisible(editArea);
    wait.forElementVisible(focusedNode);
    clickContextMenu();
    return new VisualEditorEditTemplateDialog(driver);
  }

  public VisualEditorPageObject clickInsertToolButton() {
    insertDropdownMenuButton.click();
    return this;
  }

  public VisualEditorPageObject clickInsertInfoboxFromInsertToolMenu() {
    wait.forElementVisible(infoboxInDropdownMenu);
    infoboxInDropdownMenu.click();
    return this;
  }

  public VisualEditorPageObject selectInfoboxTemplate(int i) {
    infoboxTemplatesList.get(i).click();
    return this;
  }

  public VisualEditorPageObject typeInParameterField(int i, String parameter) {
    parametersFieldList.get(i).click();
    parametersFieldList.get(i).sendKeys(parameter);
    return this;
  }

  public VisualEditorPageObject clickApplyChanges() {
    wait.forElementVisible(applyChangesButton);
    applyChangesButton.click();
    return this;
  }

  public VisualEditorPageObject isInfoboxInsertedInEditorArea() {
    wait.forElementVisible(focusedHighlight);
    Assertion.assertEquals(isElementOnPage(focusedHighlight), true);
    return this;
  }

  public VisualEditorPageObject clickInfoboxPopup() {
    wait.forElementVisible(infoboxPopup);
    infoboxPopup.click();
    return this;
  }

}
