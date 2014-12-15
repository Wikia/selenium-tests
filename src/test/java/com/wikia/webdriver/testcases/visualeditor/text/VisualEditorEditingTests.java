package com.wikia.webdriver.testcases.visualeditor.text;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.WikiTextContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorHyperLinkDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.WikiHistoryPageObject;

/**
 * @author Robert 'rochan' Chan
 * @ownership Contribution
 *
 * 1. VE-1228 Adding all style text, heading text and list to a new article
 * 2. VE-1228 Removing a piece of text from the article
 * 3. VE-1228 Adding all style text, heading text and list to an existing article
 * 4. VE-1271 Adding blue link, red link and external link to a new article
 * 5. VE-1332 Editing in VE's source mode, review change, then edit once more in source more then publish
 */

public class VisualEditorEditingTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	private String text = WikiTextContent.TEXT;
	private ArrayList<String> wikiTexts, linkWikiTexts, firstSourceEditText, secondSourceEditText;
	private String articleName;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		base = new WikiBasePageObject(driver);
		articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
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
		linkWikiTexts = new ArrayList<>();
		linkWikiTexts.add(WikiTextContent.BLUELINK_TEXT);
		linkWikiTexts.add(WikiTextContent.REDLINK_TEXT);
		linkWikiTexts.add(WikiTextContent.EXTERNAL_LINK_TEXT);
		firstSourceEditText = new ArrayList<>();
		firstSourceEditText.add(text);
		secondSourceEditText = new ArrayList<>();
		secondSourceEditText.add(text+text);

	}

	@Test(
		groups = {
			"VisualEditorEditing", "VisualEditorEditing_001", "VisualEditorEditing_002", "VisualEditorEditing_003"
		}
	)
	public void VisualEditorEditing_001_insertToNewArticle() {
		base = new WikiBasePageObject(driver);
		articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
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
		groups = {"VisualEditorEditing", "VisualEditorEditing_002"},
		dependsOnGroups = "VisualEditorEditing_001"
	)
	public void VisualEditorEditing_002_delete() {
		String removeText = "Lorem";
		ArrayList<String> deletedWikiTexts;
		deletedWikiTexts = new ArrayList<>();
		deletedWikiTexts.add(removeText);

		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.removeText(removeText);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyDeletedDiffs(deletedWikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_003"},
		dependsOnGroups = "VisualEditorEditing_001"
	)
	public void VisualEditorEditing_003_insertToExistingArticle() {
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
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
		groups = {"VisualEditorEditing", "VisualEditorEditing_004"}
	)
	public void VisualEditorEditing_004_insertLinks() {
		String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		VisualEditorHyperLinkDialog veLinkDialog = ve.clickLinkButton();
		veLinkDialog.typeInLinkInput(PageContent.INTERNAL_LINK);
		veLinkDialog.verifyMatchingPageIsTop();
		ve = veLinkDialog.clickLinkResult();
		ve.typeReturn();
		veLinkDialog = ve.clickLinkButton();
		veLinkDialog.typeInLinkInput(PageContent.REDLINK);
		veLinkDialog.verifyNewPageIsTop();
		ve = veLinkDialog.clickLinkResult();
		ve.typeReturn();
		veLinkDialog = ve.clickLinkButton();
		veLinkDialog.typeInLinkInput(PageContent.EXTERNAL_LINK);
		veLinkDialog.verifyExternalLinkIsTop();
		ve = veLinkDialog.clickLinkResult();
		ve.typeReturn();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(linkWikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		article.logOut(wikiURL);
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_005"}
	)
	public void VisualEditorEditing_005_switchToSourceMode() {
		String articleName2 = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
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
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_006"}
	)
	public void VisualEditorEditing_006_editSummary() {
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.typeTextArea("a");
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		saveDialog.typeEditSummary(text);
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		WikiHistoryPageObject historyPage = article.openArticleHistoryPage();
		historyPage.verifyLatestEditSummary(text);
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_007"}
	)
	public void VisualEditorEditing_007_minorEdit() {
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		VisualEditorPageObject ve = base.openVEOnArticle(wikiURL, articleName);
		ve.typeTextArea("b");
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		saveDialog.clickMinorEdit();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
		WikiHistoryPageObject historyPage = article.openArticleHistoryPage();
		historyPage.verifyRevisionMarkedAsMinor();
	}
}
