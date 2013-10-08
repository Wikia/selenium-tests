package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 * Test cases:
 * 1. Write bold text and clear formatting
 * 2. Write italic text and clear formatting
 * 3. Add link
 * 4. Write code text and clear formatting
 * 5. Write numbered list
 * 6. Write bullet list
 */

public class VisualEditorTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	private String articleText = PageContent.articleText;
	private String linkText = PageContent.textLink;

	@Test(groups = {"VisualEditor", "VisualEditor_001"})
	public void VisualEditor_001_writeBoldText() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject ve = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
		ve.clickBoldButton();
		ve.write(articleText);
		ve.verifyTextBold(articleText);
		ve.selectText(0, articleText.length()+1);
		ve.clickClearButton();
		ve.verifyTextNotFormatted(articleText);
	}

	@Test(groups = {"VisualEditor", "VisualEditor_002"})
	public void VisualEditor_002_writeItalicText() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject ve = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
		ve.clickItalicButton();
		ve.write(articleText);
		ve.verifyTextItalic(articleText);
		ve.selectText(0, articleText.length()+1);
		ve.clickClearButton();
		ve.verifyTextNotFormatted(articleText);
	}

//	@Test(groups = {"VisualEditor", "VisualEditor_003"})
//	public void VisualEditor_003_addLink() {
//		WikiBasePageObject base = new WikiBasePageObject(driver);
//		base.logInCookie(credentials.userName, credentials.password, wikiURL);
//		VisualEditorPageObject ve = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
//		ve.write(linkText);
//		ve.clickLinkButton();
//		ve.typeHyperlink("http://wikia.com/");
//		ve.verifyLink(linkText, "http://wikia.com/");
//	}
//
////	@Test(groups = {"VisualEditor", "VisualEditor_004"})
//	public void VisualEditor_004_writeCodeText() {
//		WikiBasePageObject base = new WikiBasePageObject(driver);
//		base.logInCookie(credentials.userName, credentials.password, wikiURL);
//		VisualEditorPageObject ve = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
//		ve.clickCodeButton();
//		ve.write(articleText);
//		ve.verifyCodeText(articleText);
//		ve.selectText(0, articleText.length()+1);
//		ve.clickClearButton();
//		ve.verifyTextNotFormatted(articleText);
//	}
//
//	@Test(groups = {"VisualEditor", "VisualEditor_005"})
//	public void VisualEditor_005_writeNumList() {
//		WikiBasePageObject base = new WikiBasePageObject(driver);
//		base.logInCookie(credentials.userName, credentials.password, wikiURL);
//		VisualEditorPageObject ve = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
//		ve.clickNumListButton();
//		List<String> elements = new ArrayList<String>();
//		elements.add("row1");
//		elements.add("row2");
//		elements.add("row3");
//		ve.write(elements.get(0));
//		ve.write(Keys.ENTER);
//		ve.write(elements.get(1));
//		ve.write(Keys.ENTER);
//		ve.write(elements.get(2));
//		ve.verifyNumList(elements);
//	}
//
//	@Test(groups = {"VisualEditor", "VisualEditor_006"})
//	public void VisualEditor_006_writeBulletList() {
//		WikiBasePageObject base = new WikiBasePageObject(driver);
//		base.logInCookie(credentials.userName, credentials.password, wikiURL);
//		VisualEditorPageObject ve = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
//		ve.clickBullListButton();
//		List<String> elements = new ArrayList<String>();
//		elements.add("row1");
//		elements.add("row2");
//		elements.add("row3");
//		ve.write(elements.get(0));
//		ve.write(Keys.ENTER);
//		ve.write(elements.get(1));
//		ve.write(Keys.ENTER);
//		ve.write(elements.get(2));
//		ve.verifyBullList(elements);
//	}
}
