package com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WikiArticleEditMode extends WikiEditMode {

  @FindBy(css = "a.RTEVideoButton")
  private WebElement videoButton;
  @FindBy(css = ".cke_source")
  private WebElement sourceModeTextArea;
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

  public SpecialVideosPageObject openSpecialVideoPage(String wikiURL) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + URLsContent.SPECIAL_VIDEOS);
    return new SpecialVideosPageObject(driver);
  }

  public SourceEditModePageObject clickOnSourceButton() {
    wait.forElementVisible(sourceModeButton);
    wait.forElementClickable(sourceModeButton);
    scrollAndClick(sourceModeButton);
    wait.forElementVisible(sourceModeTextArea);
    Log.log("ClickOnSourceButton", "Click on 'Source' button", true, driver);
    return new SourceEditModePageObject();
  }

  public void clickOnVisualButton() {
    wait.forElementVisible(visualModeButton);
    wait.forElementClickable(visualModeButton);
    scrollAndClick(visualModeButton);
    wait.forElementVisible(iFrame);
    Log.log("ClickOnVisualButton", "Click on 'Visual' button", true);
  }

  public void clickOnPublish() {
    wait.forElementClickable(publishButton);
    publishButton.click();
    Log.log("clickOnPublish", "publish button clicked", true, driver);
  }

  public void verifySourceEditorContentIsEmpty() {
    wait.forElementVisible(sourceModeTextArea);
    Assertion.assertEquals(sourceModeTextArea.getText().isEmpty(), true);
    Log.log("verifySourceEditorContentIsEmpty", "Source editor content was cleaned", true);
  }

  public void clearSource() {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.clear();
    Log.log("deleteArticleContent", "Delete all source code on the article", true);
  }

  public void typeInContent(String content) {
    wait.forElementVisible(iFrame);
    driver.switchTo().frame(iFrame);
    wait.forElementVisible(bodyContent);
    bodyContent.sendKeys(content);
    Log.log("typeInContent",
            "content " + bodyContent.getText() + " - type into article body",
            true,
            driver
    );
    driver.switchTo().defaultContent();
  }

  public void clickSourceButton() {
    wait.forElementVisible(sourceButton);
    sourceButton.click();
    driver.switchTo().defaultContent();
    Log.log("clickSourceButton", "Source button was clicked", true, driver);
  }

  public WikiArticleEditMode editArticleByName(String name, String wikiUrl) {
    String newUrl = URLsContent.ADD_ARTICLE.replace("%title%", name);
    getUrl(wikiUrl + newUrl);
    return new WikiArticleEditMode();
  }

  public void typeContentInSourceMode(String content) {
    wait.forElementVisible(sourceModeTextArea);
    sourceModeTextArea.sendKeys(content);
    Log.log("typeInContent", "content type into source mode textarea", true, driver);
  }
}
