package com.wikia.webdriver.testcases.visualeditor.text;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.WikiTextContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
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
  private String articleName;

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
      groups = {
          "VisualEditorEditing", "VisualEditorEditing_001", "VisualEditorEditing_002",
          "VisualEditorEditing_003", "VisualEditorDelete"
      }
  )
  @RelatedIssue(issueID = "WW-487")
  public void VisualEditorEditing_001_insertToNewArticle() {
    articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.typeTextInAllFormat(text);
    ve.typeTextInAllStyle(text);
    ve.typeTextInAllList(text);
    ve.clickPublishButton();
    ArticlePageObject article = new ArticlePageObject();
    article.verifyVEPublishComplete();
    article.verifyContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
  }

  @Test(
      groups = {"VisualEditorDelete", "VisualEditorEditing_002"},
      dependsOnGroups = "VisualEditorEditing_001"
  )
  @RelatedIssue(issueID = "WW-487")
  public void VisualEditorEditing_002_delete() {

    String removeText = "Lorem ";
    List<String> deletedWikiTexts;
    deletedWikiTexts = new ArrayList<>();
    deletedWikiTexts.add(removeText);

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.removeText(removeText);
    ve.clickPublishButton();
    VisualEditorSaveChangesDialog saveDialog = new VisualEditorSaveChangesDialog(driver);
    saveDialog.savePage();
    ArticlePageObject article = new ArticlePageObject();
    article.verifyVEPublishComplete();
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_003"},
      dependsOnGroups = "VisualEditorEditing_001"
  )
  @RelatedIssue(issueID = "WW-487")
  public void VisualEditorEditing_003_insertToExistingArticle() {
    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.typeTextInAllFormat(text);
    ve.typeTextInAllStyle(text);
    ve.typeTextInAllList(text);
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
    reviewDialog.verifyAddedDiffs(wikiTexts);
    saveDialog = reviewDialog.clickReturnToSaveFormButton();
    ArticlePageObject article = saveDialog.savePage();
    article.verifyVEPublishComplete();
  }

  @Test(
      groups = {"VisualEditorLinks", "VisualEditorEditing_004"}
  )
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
    ve.clickPublishButton();
    ArticlePageObject article = new ArticlePageObject();
    article.verifyVEPublishComplete();
    article.verifyHTMLContent(By.cssSelector("a[href*='" + PageContent.INTERNAL_LINK + "']"));
    article.verifyHTMLContent(By.cssSelector("a.new[href*='" + PageContent.REDLINK + "']"));
    article.verifyHTMLContent(By.cssSelector("a.external[href*='" + PageContent.EXTERNAL_LINK + "']"));
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_005"}
  )
  public void VisualEditorEditing_005_switchToSourceMode() {
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve = ve.typeInSourceEditor(text);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.clickPublishButton();
    ve.waitForPageLoad();
    ArticlePageObject article = new ArticlePageObject();
    article.openVEModeWithMainEditButton();
    ve.typeInSourceEditor(text);
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    saveDialog.savePage();
    article.verifyVEPublishComplete();
    article.verifyContent(text);
  }

  @Test(
      groups = {"VisualEditorEditing", "VisualEditorEditing_006"}
  )
  public void VisualEditorEditing_006_editSummary() {
    String summaryText =
        "This is an example summary text being used by test: VisualEditorEditing_006_editSummary";
    String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName2);
    ve.verifyVEToolBarPresent();
    ve.verifyEditorSurfacePresent();
    ve.typeTextArea("a");
    ve.clickPublishButton();
    ve.waitForPageLoad();
    ArticlePageObject article = new ArticlePageObject();
    article.openVEModeWithMainEditButton();
    ve.typeTextArea("a");
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    saveDialog.typeEditSummary(summaryText);
    saveDialog.savePage();
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
    ve.clickPublishButton();
    ve.waitForPageLoad();
    ArticlePageObject article = new ArticlePageObject();
    article.openVEModeWithMainEditButton();
    ve.typeTextArea("b");
    VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
    saveDialog.clickMinorEdit();
    saveDialog.savePage();
    article.verifyVEPublishComplete();
    WikiHistoryPageObject historyPage = article.openArticleHistoryPage();
    historyPage.verifyRevisionMarkedAsMinor();
  }
}
