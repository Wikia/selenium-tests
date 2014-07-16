package com.wikia.webdriver.TestCases.VisualEditor.Text;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.WikiTextContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorHyperLinkDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

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

	private String text = WikiTextContent.text;
	private ArrayList<String> wikiTexts, linkWikiTexts, firstSourceEditText, secondSourceEditText;
	private String articleName;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		base = new WikiBasePageObject(driver);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		wikiTexts = new ArrayList<>();
		wikiTexts.add(WikiTextContent.paragraphText);
		wikiTexts.add(WikiTextContent.headingText);
		wikiTexts.add(WikiTextContent.subHeading1Text);
		wikiTexts.add(WikiTextContent.subHeading2Text);
		wikiTexts.add(WikiTextContent.subHeading3Text);
		wikiTexts.add(WikiTextContent.subHeading4Text);
		wikiTexts.add(WikiTextContent.preformattedText);
		wikiTexts.add(WikiTextContent.boldText);
		wikiTexts.add(WikiTextContent.italicText);
		wikiTexts.add(WikiTextContent.striketroughText);
		wikiTexts.add(WikiTextContent.underlineText);
		wikiTexts.add(WikiTextContent.subscriptText);
		wikiTexts.add(WikiTextContent.superscriptText);
		wikiTexts.add(WikiTextContent.bulletListText);
		wikiTexts.add(WikiTextContent.numberedListText);
		linkWikiTexts = new ArrayList<>();
		linkWikiTexts.add(WikiTextContent.blueLinkText);
		linkWikiTexts.add(WikiTextContent.redLinkText);
		linkWikiTexts.add(WikiTextContent.externalLinkText);
		firstSourceEditText = new ArrayList<>();
		firstSourceEditText.add(text);
		secondSourceEditText = new ArrayList<>();
		secondSourceEditText.add(text+text);
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_001"}
	)
	public void VisualEditorEditing_001_insertToNewArticle() {
		base = new WikiBasePageObject(driver);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
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
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		ve.removeText(removeText);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyDeletedDiffs(wikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		ArticlePageObject article = saveDialog.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_003"},
		dependsOnGroups = "VisualEditorEditing_001"
	)
	public void VisualEditorEditing_003_insertToExistingArticle() {
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
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
		String articleName2 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName2, wikiURL);
		VisualEditorHyperLinkDialog veLinkDialog = ve.clickLinkButton();
		veLinkDialog.typeInLinkInput(PageContent.internalLink);
		veLinkDialog.verifyMatchingPageIsTop();
		ve = veLinkDialog.clickLinkResult();
		ve.typeReturn();
		veLinkDialog = ve.clickLinkButton();
		veLinkDialog.typeInLinkInput(PageContent.redLink);
		veLinkDialog.verifyNewPageIsTop();
		ve = veLinkDialog.clickLinkResult();
		ve.typeReturn();
		veLinkDialog = ve.clickLinkButton();
		veLinkDialog.typeInLinkInput(PageContent.externalLink);
		veLinkDialog.verifyExternalLinkIsTop();
		ve = veLinkDialog.clickLinkResult();
		ve.typeReturn();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(linkWikiTexts);
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_005"}
	)
	public void VisualEditorEditing_005_switchToSourceMode_VE_1338() {
		String articleName2 = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName2, wikiURL);
		ve = ve.typeInSourceEditor(text);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(firstSourceEditText);
		ve = reviewDialog.clickCloseButton();
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
}
