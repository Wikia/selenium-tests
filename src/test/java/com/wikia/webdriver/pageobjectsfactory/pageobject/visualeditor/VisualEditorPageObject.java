package com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.VEContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.*;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.*;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VisualEditorPageObject extends VisualEditorMenu {

  @FindBy(css = "figure.ve-ce-branchNode")
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
  @FindBy(css = ".oo-ui-popupWidget-body .oo-ui-widget-enabled .oo-ui-iconElement")
  private WebElement infoboxPopup;
  @FindBy(css = ".portable-infobox")
  private WebElement infoboxInArticle;
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
  @FindBy(css = ".oo-ui-inputWidget textarea")
  private List<WebElement> parametersFieldList;
  @FindBy(css = ".oo-ui-buttonElement-button")
  private List<WebElement> buttonsList;
  @FindBy(css = ".image.video.video-thumbnail.medium")
  private List<WebElement> videoNodes;
  @FindBy(css = "figure.ve-ce-branchNode a")
  private List<WebElement> mediaNodes;
  @FindBy(css = ".media-gallery-wrapper.ve-ce-branchNode>div")
  private List<WebElement> galleryNodes;

  private By contextMenuBy = By.cssSelector(".ve-ui-contextSelectWidget");
  private By contextEditBy = By.cssSelector(".oo-ui-labelElement");
  private By blockTransclusionBy = By.cssSelector("div[typeof='mw:Transclusion']");
  private By inlineTransclusionBy = By.cssSelector("span[typeof='mw:Transclusion']");

  public VisualEditorPageObject open(){
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForPage(
            "/" + TestContext.getCurrentMethodName()), URLsContent.VEACTION_EDIT));

    return this;
  }

  public void selectMediaAndDelete() {
    wait.forElementVisible(editArea);
    editArea.click();
    wait.forElementVisible(mediaNode);
    mediaNode.click();
    deleteMediaNode();
    PageObjectLogging.log("selectMediaAndDelete", "Selected media and click delete", true, driver);
  }

  private void deleteMediaNode() {
    jsActions.execute("$(\"figure\").trigger($.Event(\"keydown\", {keyCode: 46}))");
  }

  public void typeTextArea(String text) {
    wait.forElementVisible(editArea);
    editArea.sendKeys(text);
    PageObjectLogging.log("write", "text " + text + "written", true);
  }

  public void press(Keys key) {
    editArea.sendKeys(key);
    PageObjectLogging.log("press", "key " + key.toString() + "pressed", true);
  }

  public void putCursorAtTheEnd() {
    wait.forElementVisible(editArea);
    String putCursorAtTheEndJS = "ve.init.target.getSurface().getModel().setLinearSelection(" +
            "new ve.Range(" +
            "ve.init.target.getSurface().getView().getNearestCorrectOffset(" +
            "ve.init.target.getSurface().getModel().getDocument().getInternalList().getListNode().getOuterRange().to," +
            "1" +
            ")" +
            ")" +
            ")";
    driver.executeScript(putCursorAtTheEndJS);
  }

  public void selectText(int from, int to) {
    String
        selectTextJS =
        "ve.init.target.getSurface().getModel().change(" +
        "null, new ve.dm.LinearSelection(" +
        "ve.init.target.getSurface().getModel().getDocument(),new ve.Range(" +
        from + "," + to + " )));";
    driver.executeScript(selectTextJS);
  }

  public void selectText(String text) {
    wait.forElementVisible(editArea);
    String textDump = editArea.getText();
    int
        from =
        textDump.indexOf(text) + 1; //+1 because index is counted differently in selectText() method
    int to = from + text.length();
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
    editArea.click();
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
    PageObjectLogging.log("verifyEditorSurface", "VE editor surface is displayed", true, driver);
  }

  public ArticlePageObject clickVEEditAndPublish(String content) {
    typeTextArea(content);
    publish();
    return new ArticlePageObject();
  }

  public ArticlePageObject publish() {
    verifyVEToolBarPresent();
    clickPublishButton();
    new VisualEditorSaveChangesDialog(this.driver).savePage();
    ArticlePageObject article = new ArticlePageObject();
    article.verifyVEPublishComplete();

    return article;
  }

  public void verifyMapPresent() {
    wait.forElementVisible(mapNode);
    PageObjectLogging.log("verifyMapPresent", "VE map is displayed", true);
  }

  public void verifyNoVideo() {
    if (isElementOnPage(mediaNode)) {
      throw new AssertionError("Media Node is still on the page");
    } else {
      PageObjectLogging.log("verifyNoVideo", "Verified no video is on page", true, driver);
    }
  }

  public void verifyVideos(int expected) {
    wait.forElementVisible(editArea);
    Assertion.assertNumber(videoNodes.size(), expected,
            "Checking the correct number of video nodes added");
    PageObjectLogging.log("verifyVideos", videoNodes.size() + " videos displayed", true);
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
    PageObjectLogging
        .log("verifyMediasInGallery", numOfMediasInGallery + " medias displayed", true);
  }

  public void verifyMedias(int expected) {
    wait.forElementVisible(mediaNode);
    wait.forElementVisible(mediaNode);
    Assertion.assertNumber(mediaNodes.size(), expected,
                           "Checking the correct number of media nodes added");
    PageObjectLogging.log("verifyMedias", mediaNodes.size() + " media displayed", true);
  }

  public VisualEditorMediaSettingsDialog openMediaSettings() {
    wait.forElementVisible(editArea);
    wait.forElementVisible(mediaNode);
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
    wait.forElementClickable(editArea);
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

  public void verifyPreviewVideo() {
    wait.forElementVisible(previewOverlay);
    wait.forElementVisible(previewVideoWrapper);
    PageObjectLogging.log("verifyPreviewVideo", "Preview for Video loaded", true, driver);
  }

  public void verifyPreviewImage() {
    wait.forElementVisible(previewOverlay);
    wait.forElementVisible(previewImage);
    PageObjectLogging.log("verifyPreviewImage", "Preview for Image loaded", true, driver);
  }

  public void verifyVideoCaption(String caption) {
    wait.forElementVisible(mediaNode);
    wait.forElementVisible(mediaCaption);
    Assertion.assertEquals(caption, mediaCaption.getText(), "The video caption does not match");
    PageObjectLogging.log("getVideoCaption", "Video caption matches", true, driver);
  }

  public void selectMedia() {
    wait.forElementClickable(mediaNode);
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
    PageObjectLogging.log("resizeMedia", "Before resizing", true, driver);
    selectMedia();
    wait.forElementVisible(swResizeHandle);
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
    //wait for highlight
    wait.forElementVisible(focusedHighlight);
    //TODO check if any future webdriver upgrade would resolve having to use separate logic
    if (driver.isChrome()) {
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
    PageObjectLogging
        .log("clickTransclusion", "Clicked at X: " + tempLeft + ", Y: " + tempTop, true,
             driver);
  }

  private Point getTransclusionLocation(int index, Transclusion transclusion) {
    JavascriptExecutor js = driver;
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
    wait.forElementVisible(editArea);
    wait.forElementVisible(focusedNode);
    clickContextMenu();
    return new VisualEditorEditTemplateDialog(driver);
  }

  public VisualEditorPageObject clickInsertToolButton() {
    wait.forElementClickable(insertDropdownMenuButton);
    insertDropdownMenuButton.click();
    return this;
  }

  public VisualEditorPageObject clickInsertInfoboxFromInsertToolMenu() {
    wait.forElementClickable(infoboxInDropdownMenu);
    infoboxInDropdownMenu.click();
    return this;
  }

  public VisualEditorPageObject selectInfoboxTemplate(int i) {
    WebElement template = infoboxTemplatesList.get(i);
    wait.forElementClickable(template);
    template.click();
    return this;
  }

  public VisualEditorPageObject typeInParameterField(int i, String parameter) {
    WebElement field = parametersFieldList.get(i);
    wait.forElementClickable(field);
    field.click();
    field.sendKeys(parameter);
    return this;
  }

  public VisualEditorPageObject applyChanges() {
    wait.forElementClickable(applyChangesButton);
    applyChangesButton.click();
    wait.forElementNotVisible(applyChangesButton);
    return this;
  }

  public boolean isInfoboxInsertedInEditorArea() {
    wait.forElementVisible(focusedHighlight);
    return isElementOnPage(focusedHighlight);
  }

  public VisualEditorPageObject clickInfoboxPopup() {
    wait.forElementClickable(infoboxPopup);
    infoboxPopup.click();
    return this;
  }

  public VisualEditorPageObject addVideoToContent(String videoType) {
    verifyVEToolBarPresent();
    verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = clickVideoButton();
    return mediaDialog.addMediaByURL(videoType);
  }

  public VisualEditorAddMediaDialog searchVideo(String searchInput) {
    verifyVEToolBarPresent();
    verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = clickVideoButton();
    return mediaDialog.searchMedia(searchInput);
  }

  public void resizeMedia(int resizeNumber, ImageSize size) {
    VisualEditorMediaSettingsDialog mediaSettingsDialog = openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    mediaSettingsDialog.setCustomSize(resizeNumber, size);
    mediaSettingsDialog.clickApplyChangesButton();
  }

  public void alignMedia(int mediaIndex, Alignment direction) {
    verifyEditorSurfacePresent();
    verifyVEToolBarPresent();
    selectMediaByIndex(mediaIndex);
    VisualEditorMediaSettingsDialog mediaSettingsDialog = openMediaSettings();
    mediaSettingsDialog.selectSettings(Setting.ADVANCED);
    mediaSettingsDialog.clickAlignment(direction);
    mediaSettingsDialog.clickApplyChangesButton();
  }

  public VisualEditorAddMediaDialog searchImage(String searchInput) {
    verifyVEToolBarPresent();
    verifyEditorSurfacePresent();
    VisualEditorAddMediaDialog mediaDialog = clickImageButton();
    mediaDialog = mediaDialog.searchMedia(searchInput);
    return mediaDialog;
  }

  public VisualEditorPageObject clickEditArea() {
    editArea.click();
    return this;
  }

  public VisualEditorPageObject clickInfobox() {
    infoboxInArticle.click();
    return this;
  }

}
