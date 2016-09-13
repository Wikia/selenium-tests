package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiArticleEditMode extends WikiEditMode {

  @FindBy(css = "a.RTEVideoButton")
  private WebElement videoButton;
  @FindBy(css = ".cke_source")
  private WebElement sourceModeTextArea;
  @FindBy(css = "#wpTextbox1")
  private WebElement messageSourceModeTextArea;
  @FindBy(css = "div.cke_wrapper.cke_ltr div.cke_contents iframe")
  private WebElement iFrame;
  @FindBy(css = "span.cke_button_ModeSource a span.cke_label")
  private WebElement sourceModeButton;
  @FindBy(css = "span.cke_button_ModeWysiwyg a")
  private WebElement visualModeButton;
  @FindBy(css = "body[id='bodyContent']")
  private WebElement bodyContent;
  @FindBy(css = "#wpSave")
  private WebElement publishButton;
  @FindBy(css = "span[id=cke_22_label]")
  private WebElement sourceButton;
  @FindBy(css = "a[data-map-title]")
  private WebElement embededMap;

  public WikiArticleEditMode(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public SpecialVideosPageObject openSpecialVideoPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_VIDEOS);
    return new SpecialVideosPageObject(driver);
  }

  public SourceEditModePageObject clickOnSourceButton() {
    wait.forElementVisible(sourceModeButton);
    wait.forElementClickable(sourceModeButton);
    scrollAndClick(sourceModeButton);
    wait.forElementVisible(sourceModeTextArea);
    PageObjectLogging.log("ClickOnSourceButton", "Click on 'Source' button", true, driver);
    return new SourceEditModePageObject(driver);
  }

  public void clickOnVisualButton() {
    wait.forElementVisible(visualModeButton);
    wait.forElementClickable(visualModeButton);
    scrollAndClick(visualModeButton);
    wait.forElementVisible(iFrame);
    PageObjectLogging.log("ClickOnVisualButton", "Click on 'Visual' button", true);
  }

  public void clickOnPublish() {
    wait.forElementClickable(publishButton);
    publishButton.click();
    PageObjectLogging.log("clickOnPublish", "publish button clicked", true, driver);
  }

  public void verifySourceEditorContentIsEmpty() {
    wait.forElementVisible(sourceModeTextArea);
    Assertion.assertEquals(sourceModeTextArea.getText().isEmpty(), true);
    PageObjectLogging.log("verifySourceEditorContentIsEmpty",
                          "Source editor content was cleaned", true);
  }

  public void clearSource() {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.clear();
    PageObjectLogging.log("deleteArticleContent", "Delete all source code on the article", true);
  }

  public void typeInContent(String content) {
    wait.forElementVisible(iFrame);
    driver.switchTo().frame(iFrame);
    wait.forElementVisible(bodyContent);
    bodyContent.sendKeys(content);
    PageObjectLogging.log("typeInContent", "content " + bodyContent.getText()
                                           + " - type into article body", true, driver);
    driver.switchTo().defaultContent();
  }

  public void clickSourceButton() {
    wait.forElementVisible(sourceButton);
    sourceButton.click();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickSourceButton", "Source button was clicked", true, driver);
  }

  public WikiArticleEditMode editArticleByName(String name, String wikiUrl) {
    String newUrl = URLsContent.ADD_ARTICLE.replace("%title%", name);
    getUrl(wikiUrl + newUrl);
    return new WikiArticleEditMode(driver);
  }

  public VetAddVideoComponentObject clickVideoButton() {
    wait.forElementVisible(videoButton);
    scrollAndClick(videoButton);
    PageObjectLogging.log("clickVideoButton", "video button clicked", true);
    return new VetAddVideoComponentObject(driver);
  }

  public void typeContentInSourceMode(String content) {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.sendKeys(content);
    PageObjectLogging.log(
        "typeInContent",
        "content type into source mode textarea",
        true,
        driver
    );
  }

  public void typeInTemplateContent(String content) {
    driver.switchTo().defaultContent();
    wait.forElementVisible(messageSourceModeTextArea);
    messageSourceModeTextArea.sendKeys(content);
    PageObjectLogging.log(
        "typeInContent",
        "content type into source mode textarea",
        true,
        driver
    );
  }

  public void verifyEmbededMap(String mapID) {
    driver.switchTo().defaultContent();
    wait.forElementVisible(embededMap);
    String embededMapID = embededMap.getAttribute("data-map-id");
    Assertion.assertEquals(embededMapID, mapID);
  }
}
