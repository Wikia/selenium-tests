package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class SpecialCssPageObject extends SpecialPageObject {

  @FindBy(css = ".ace_editor")
  private WebElement aceEditor;
  @FindBy(css = "textarea.ace_text-input")
  private WebElement aceInputTextArea;
  @FindBy(css = ".ace_text-layer > .ace_line")
  private WebElement aceLayerTextArea;
  @FindBys(@FindBy(css = ".ace_content div[class*='ace']"))
  private List<WebElement> aceElementsList;
  @FindBy(css = ".ace_error")
  private WebElement aceError;
  @FindBy(css = ".css-publish-button")
  private WebElement cssPublishButton;
  @FindBy(css = ".css-edit-box .wikia-menu-button .drop")
  private WebElement cssPublishButtonDropdown;
  @FindBy(css = "#editSummary")
  private WebElement editSummaryField;
  @FindBy(css = "#minorEdit")
  private WebElement minorEdit;
  @FindBy(css = "#showChanges")
  private WebElement showChanges;
  @FindBy(css = "#wikiDiff")
  private WebElement changesModal;
  @FindBy(css = "#wikiDiff .diff-addedline > div")
  private WebElement changesModalAddedLine;
  @FindBy(css = ".banner-notification.confirm")
  private WebElement notificationConfirm;
  @FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"action=history\"]")
  private WebElement historyButton;
  @FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"action=delete\"]")
  private WebElement deleteButton;
  @FindBy(css = "td.diff-otitle")
  private WebElement oRevisionTitle;
  @FindBy(css = ".css-editor .mw-warning-with-logexcerpt")
  private WebElement removedWarning;
  @FindBy(css = ".css-side-bar .wikia-menu-button .WikiaMenuElement a[href*=\"Special:Undelete\"]")
  private WebElement undeleteButton;
  private By removedWarningBy = By.cssSelector(".css-editor .mw-warning-with-logexcerpt");
  @FindBy(css = ".css-edit-box a.talk .commentsbubble")
  private WebElement talkBubble;
  @FindBy(css = ".css-edit-box a.talk")
  private WebElement talkLink;
  @FindBy(css = "a.talk .commentsbubble")
  private WebElement mwTalkBubble;

  public SpecialCssPageObject(WebDriver driver) {
    super(driver);
  }

  public SpecialCssPageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
        + URLsContent.MEDIAWIKI_CSS);
    return this;
  }

  public SpecialCssPageObject  openEditor() {
    getUrl(urlBuilder.appendQueryStringToURL(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
        + URLsContent.MEDIAWIKI_CSS, "action=edit"));
    return this;
  }


  public void verifyAceEditorPresence() {
    wait.forElementVisible(aceEditor);
    LOG.log("verifyAceEditorPresence", "Ace Editor is present.", LOG.Type.SUCCESS);
  }

  public void verifyHighlighting() {
    Assertion.assertNotEquals(aceElementsList.size(), 0);
    LOG.logResult("verifyHighlighting",
                  "There are elements highlighted by ace library",
                  true);
  }

  public void saveCssContent(String randomText) {
    verifyPublishButtonAppears();
    clearCssText();
    sendCssText(randomText);
    clickPublishButton();
    verifySaveComplete();
    verifyUrl(URLsContent.SPECIAL_CSS);
  }

  public void clearCssText() {
    wait.forElementVisible(aceLayerTextArea);
    jsActions.execute("ace.edit('cssEditorContainer').setValue('');");
    LOG.log("clearCssText", "ace editor was cleared", true, driver);
  }

  public void insertCssText(String cssText) {
    wait.forElementVisible(aceLayerTextArea);
    jsActions.execute("ace.edit('cssEditorContainer').navigateFileEnd();");
    sendCssText(cssText);
    LOG.log("sendAceCssText",
            "the following text was send to ace editor: " + cssText, LOG.Type.SUCCESS);
  }

  public void sendEditSummaryText(String summaryText) {
    wait.forElementVisible(editSummaryField);
    editSummaryField.sendKeys(summaryText);
    LOG.log("editSummaryField", "the following text was send to ace editor: "
                                + summaryText, LOG.Type.SUCCESS);
  }

  public void sendCssText(String cssText) {
    wait.forElementVisible(aceLayerTextArea);
    aceInputTextArea.sendKeys(cssText);
    LOG.logResult("sendCssText",
                  "the following text was send to ace editor: " + cssText,
                  true);
  }

  public void verifyAceError() {
    wait.forElementVisible(aceError);
    LOG.log("verifyAceError", "verify that highlightet ace shows an error", LOG.Type.SUCCESS);
  }

  public void verifyPublishButtonAppears() {
    wait.forElementVisible(cssPublishButton);
    LOG.log("cssPublishButton", "verify that publish button appears", LOG.Type.SUCCESS);
  }

  public void verifyMinorEditAppears() {
    wait.forElementVisible(minorEdit);
    LOG.log("minorEdit", "verify that minor edit checkbox appears", LOG.Type.SUCCESS);
  }

  public void clickPublishButton() {
    scrollAndClick(cssPublishButton);
  }

  public void clickPublishButtonDropdown() {
    scrollAndClick(cssPublishButtonDropdown);
    jsActions.mouseOver(cssPublishButtonDropdown);
    LOG.log("clickCssPublishButton", "click on publish button dropdown", LOG.Type.SUCCESS);
  }

  public void clickMinorCheckbox() {
    scrollAndClick(minorEdit);
    LOG.log("minorEdit", "click on minor edit checkbox dropdown", LOG.Type.SUCCESS);
  }

  public void clickShowChanges() {
    scrollAndClick(showChanges);
    LOG.log("showChanges", "click on show changes from dropdown", LOG.Type.SUCCESS);
  }

  public void showChangesModal() {
    scrollAndClick(changesModal);
    LOG.log("changesModal", "modal with changes is displayed", LOG.Type.SUCCESS);
  }

  public void verifySaveComplete() {
    wait.forElementVisible(notificationConfirm);
    LOG.log("notificationConfirm", "css content saved", LOG.Type.SUCCESS);
  }

  public String getAddedLineText() {
    wait.forElementVisible(changesModalAddedLine);
    String addedLine = changesModalAddedLine.getText();
    LOG.log("changesModalAddedLine", "get added line content", LOG.Type.SUCCESS);
    return addedLine;
  }

  public void clickHistoryButton() {
    scrollAndClick(historyButton);
    LOG.log("historyButton", "click on history button", LOG.Type.SUCCESS);
  }

  public void clickDeleteButton() {
    deleteButton.click();
    verifyUrl("action=delete");
    LOG.log("deleteButton", "click on delete button", LOG.Type.SUCCESS);
  }

  public void confirmDelete() {
    clickArticleDeleteConfirmationButton();
  }

  public void verifyArticleIsRemoved() {
    wait.forElementPresent(removedWarningBy);
    LOG.log("verifyArticleIsRemoved", "Article is removed.", LOG.Type.SUCCESS);
  }

  public void undeleteArticle(String article) {
    clickPublishButtonDropdown();
    clickUndeleteButton();
    confirmUndelete();
    getUrl(article);
  }

  public void verifyArticleIsNotRemoved(String page) {
    if (isElementOnPage(removedWarning)) {
      undeleteArticle(page);
      LOG.logResult("articleIsRemoved", "Article is removed, needs to be restored",
                    true);
    } else {
      LOG.log("verifyArticleIsNotRemoved", "Article is not removed.", LOG.Type.SUCCESS);
    }
  }

  private void clickUndeleteButton() {
    undeleteButton.click();
    try {
      verifyUrl("Special:Undelete?target=" + URLEncoder.encode(URLsContent.MEDIAWIKI_CSS, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      // this should never happen
      LOG.log("undeleteButton", "dont work", LOG.Type.SUCCESS);
    }
    LOG.log("undeleteButton", "click on undelete button", LOG.Type.SUCCESS);
  }

  private void confirmUndelete() {
    clickRestoreArticleButton();
  }

  public void verifyConflictArea() {
    wait.forElementVisible(cssPublishButton);
    LOG.log("verifyConflictArea", "verify that conflict area is present", LOG.Type.SUCCESS);
  }

  /**
   * Above the editor area, there should be a box with comparison of yours and the latest changes
   */
  public void verifyLatestRevision() {
    wait.forElementVisible(oRevisionTitle);
    wait.forTextInElement(oRevisionTitle, "Latest revision");
    LOG.log("verifyLatestRevision", "verify that latest revision is shown", LOG.Type.SUCCESS);
  }

  public void verifyTalkBubblePresence() {
    wait.forElementVisible(talkBubble);
    LOG.log("verifyTalkBubblePresence", "Talk bubble is present.", LOG.Type.SUCCESS);
  }

  /**
   * Return the number of comments from talk button bubble
   */
  public int getNumberFromCssTalkBubble() {
    return Integer.parseInt(talkBubble.getText());
  }

  public void clickTalkButton() {
    scrollAndClick(talkLink);
    verifyUrl("/MediaWiki_talk:Wikia.css");
  }

  /**
   * go to mediawiki:wikia.css and return the number of comments from talk button bubble
   */
  public int getNumberFromWikaiCssTalkBubble() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "wiki/MediaWiki:Wikia.css");
    wait.forElementVisible(mwTalkBubble);
    return Integer.parseInt(mwTalkBubble.getText());
  }

  public void verifyDeleteButtonPresence() {
    wait.forElementVisible(deleteButton);
    LOG.log("verifyDeleteButtonPresence", "Delete Button is present.", LOG.Type.SUCCESS);
  }

}
