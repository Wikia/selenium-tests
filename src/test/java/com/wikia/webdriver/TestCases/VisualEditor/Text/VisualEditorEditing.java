package com.wikia.webdriver.TestCases.VisualEditor.Text;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.WikiTextContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertList;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
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

public class VisualEditorEditing extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	private String text = WikiTextContent.text;
	private ArrayList<String> wikiTexts;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
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
	}

	@Test(
			groups = {"VisualEditorEditing", "VisualEditorEditing_001"}
	)
	public void VisualEditorFormatting_001() {

		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.articleNamePrefix + base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
		for (Formatting format : Formatting.values()){
			PageObjectLogging.log("Formatting selection", format.toString() + " selected", true);
			ve.selectFormatting(format);
			ve.typeTextArea(text);
			ve.typeReturn();
		}

		for (Style style : Style.values()) {
			PageObjectLogging.log("Style selection", style.toString() + " selected", true);
			ve.selectStyle(style);
			ve.typeTextArea(text);
			ve.typeReturn();
		}
		ve.typeTextArea(text);
		ve.insertList(InsertList.BULLET_LIST);
		ve.typeReturn();
		ve.typeReturn();
		ve.typeTextArea(text);
		ve.insertList(InsertList.NUMBERED_LIST);
		ve.typeReturn();
		ve.typeReturn();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(wikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		saveDialog.savePage();
	}
}
