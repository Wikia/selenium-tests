package com.wikia.webdriver.TestCases.VisualEditor.Text;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.WikiTextContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'rochan' Chan
 * @ownership Contribution
 *
 */

public class VisualEditorEditingTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	private String text = WikiTextContent.text;
	private ArrayList<String> wikiTexts;
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
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_001"}
	)
	public void VisualEditorEditing_001_insertToNewArticle() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.typeTextInAllFormat(text);
		ve.typeTextInAllStyle(text);
		ve.typeTextInAllList(text);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(wikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		article = saveDialog.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_002"},
		dependsOnGroups = "VisualEditorEditing_001"
	)
	public void VisualEditorEditing_002_delete() {
		String removeText = "Lorem";
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.removeText(removeText);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyDeletedDiffs(wikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		article = saveDialog.savePage();
		article.verifyVEPublishComplete();
	}

	@Test(
		groups = {"VisualEditorEditing", "VisualEditorEditing_003"},
		dependsOnGroups = "VisualEditorEditing_001"
	)
	public void VisualEditorEditing_003_insertToExistingArticle() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		ve.typeTextInAllFormat(text);
		ve.typeTextInAllStyle(text);
		ve.typeTextInAllList(text);
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(wikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		article = saveDialog.savePage();
		article.verifyVEPublishComplete();
	}
}
