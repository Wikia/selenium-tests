package com.wikia.webdriver.testcases.visualeditor.text;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.WikiTextContent;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorHyperLinkDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiHistoryPageObject;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VisualEditorEditingTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();
  WikiBasePageObject base;

  private String text = WikiTextContent.TEXT;
  private List<String> wikiTexts, firstSourceEditText, secondSourceEditText;

  private String startingWikiText = ContentLoader.loadWikiTextContent("Visual_Editor_Existing_Article");

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    base = new WikiBasePageObject();
    wikiTexts = new ArrayList<>();
    wikiTexts.add(WikiTextContent.PARAGRAPH_TEXT);
    wikiTexts.add(WikiTextContent.HEADING_TEXT);
    wikiTexts.add(WikiTextContent.SUBHEADING1_TEXT);
    wikiTexts.add(WikiTextContent.SUBHEADING2_TEXT);
    wikiTexts.add(WikiTextContent.SUBHEADING3_TEXT);
    wikiTexts.add(WikiTextContent.SUBHEADING4_TEXT);
    wikiTexts.add(WikiTextContent.PREFORMATTED_TEXT);
    wikiTexts.add(WikiTextContent.BOLD_TEXT);
    wikiTexts.add(WikiTextContent.ITALIC_TEXT);
    wikiTexts.add(WikiTextContent.STRIKETROUGH_TEXT);
    wikiTexts.add(WikiTextContent.UNDERLINE_TEXT);
    wikiTexts.add(WikiTextContent.SUBSCRIPT_TEXT);
    wikiTexts.add(WikiTextContent.SUPERSCRIPT_TEXT);
    wikiTexts.add(WikiTextContent.BULLET_LIST_TEXT);
    wikiTexts.add(WikiTextContent.NUMBERED_LIST_TEXT);

    firstSourceEditText = new ArrayList<>();
    firstSourceEditText.add(text);
    secondSourceEditText = new ArrayList<>();
    secondSourceEditText.add(text + "\n" + text);
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_002"})
  public void VisualEditorEditing_002_delete() {
    new ArticleContent().push(startingWikiText);

    String removeText = "Lorem ";
    List<String> deletedWikiTexts;
    deletedWikiTexts = new ArrayList<>();
    deletedWikiTexts.add(removeText);

    VisualEditorPageObject ve = new VisualEditorPageObject().open();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.removeText(removeText);
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyDeletedDiffs(deletedWikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_003"})
  public void VisualEditorEditing_003_insertToExistingArticle() {
    new ArticleContent().push(startingWikiText);

    VisualEditorPageObject ve = new VisualEditorPageObject().open();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.putCursorAtTheEnd();
    ve.typeReturn();
    ve.typeReturn();
    ve.typeTextInAllFormat(text);
    ve.typeTextInAllStyle(text);
    ve.typeTextInAllList(text);
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(wikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
    article.verifyContent(text);
  }

  @Test(
      groups = {"VisualEditorLinks", "VisualEditorEditing_004"})
  public void VisualEditorEditing_004_insertLinks() {
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    ArrayList<String> linkWikiTexts = new ArrayList<>();
    linkWikiTexts.add(WikiTextContent.BLUELINK_TEXT);
    linkWikiTexts.add(WikiTextContent.REDLINK_TEXT);
    linkWikiTexts.add(WikiTextContent.EXTERNAL_LINK_TEXT);

    base.loginAs(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    VisualEditorHyperLinkDialog veLinkDialog = ve.clickLinkButton();
    veLinkDialog.typeInLinkInput(PageContent.INTERNAL_LINK);
    veLinkDialog.verifyMatchingPageIsTop();
    veLinkDialog.clickLinkResult();
    ve = veLinkDialog.clickDoneButton();
    ve.typeReturn();
    veLinkDialog = ve.clickLinkButton();
    veLinkDialog.typeInLinkInput(PageContent.REDLINK);
    veLinkDialog.verifyNewPageIsTop();
    veLinkDialog.clickLinkResult();
    ve = veLinkDialog.clickDoneButton();
    ve.typeReturn();
    veLinkDialog = ve.clickLinkButton();
    veLinkDialog.typeInLinkInput(PageContent.EXTERNAL_LINK);
    veLinkDialog.verifyExternalLinkIsTop();
    veLinkDialog.clickLinkResult();
    ve = veLinkDialog.clickDoneButton();
    ve.typeReturn();
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(linkWikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
    article.verifyElementInContent(By.cssSelector("a[href*='" + PageContent.INTERNAL_LINK + "']"));
    article.verifyElementInContent(By.cssSelector("a.new[href*='" + PageContent.REDLINK + "']"));
    article.verifyElementInContent(
        By.cssSelector("a.external[href*='" + PageContent.EXTERNAL_LINK + "']"));
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_005"})
  public void VisualEditorEditing_005_switchToSourceMode() {
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve = ve.typeInSourceEditor(text);
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(firstSourceEditText);
    ve = reviewDialog.closeDialog();
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve = ve.typeInSourceEditor(text);
    saveDialog = ve.clickPublishButton();
    reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(secondSourceEditText);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
    article.verifyContent(text);
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_006"})
  public void VisualEditorEditing_006_editSummary() {
    String summaryText =
        "This is an example summary text being used by test: VisualEditorEditing_006_editSummary";
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.typeTextArea("a");
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    saveDialog.typeEditSummary(summaryText);
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
    WikiHistoryPageObject historyPage = article.openArticleHistoryPage();
    historyPage.verifyLatestEditSummary(summaryText);
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_007"}
  )
  public void VisualEditorEditing_007_minorEdit() {
    base.loginAs(credentials.userName7, credentials.password7, wikiURL);
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.typeTextArea("b");
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    saveDialog.clickMinorEdit();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
    WikiHistoryPageObject historyPage = article.openArticleHistoryPage();
    historyPage.verifyRevisionMarkedAsMinor();
  }
}
