package com.wikia.webdriver.TestCases.VisualEditor;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VisualEditorContent;
import com.wikia.webdriver.Common.Templates.VisualEditorTestTemplate;
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

public class VisualEditorTests extends VisualEditorTestTemplate{

	@Test(groups = {"VisualEditor", "VisualEditor_001"})
	public void VisualEditor_001_writeBoldText() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickBoldButton();
		ve.write(VisualEditorContent.text);
		ve.verifyTextBold(VisualEditorContent.text);
		ve.highlightBoldText();
		ve.clickClearButton();
		ve.verifyTextNotFormatted(VisualEditorContent.text);
	}

	@Test(groups = {"VisualEditor", "VisualEditor_002"})
	public void VisualEditor_002_writeItalicText() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickItalicButton();
		ve.write(VisualEditorContent.text);
		ve.verifyTextItalic(VisualEditorContent.text);
		ve.highlightItalicText();
		ve.clickClearButton();
		ve.verifyTextNotFormatted(VisualEditorContent.text);
	}

	@Test(groups = {"VisualEditor", "VisualEditor_003"})
	public void VisualEditor_003_addLink() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.write(VisualEditorContent.text);
		ve.clickLinkButton();
		ve.typeHyperlink(PageContent.wikiaGlobalUrl);
		ve.verifyLink(VisualEditorContent.text, PageContent.wikiaGlobalUrl);
	}

	@Test(groups = {"VisualEditor", "VisualEditor_004"})
	public void VisualEditor_004_writeCodeText() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickCodeButton();
		ve.write(VisualEditorContent.text);
		ve.verifyCodeText(VisualEditorContent.text);
		ve.hightlightCodeText();
		ve.clickClearButton();
		ve.verifyTextNotFormatted(VisualEditorContent.text);
	}

	@Test(groups = {"VisualEditor", "VisualEditor_005"})
	public void VisualEditor_005_writeNumList() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickNumListButton();
		List<String> elements = new ArrayList<String>();
		elements.add("row1");
		elements.add("row2");
		elements.add("row3");
		ve.write(elements.get(0));
		ve.write(Keys.ENTER);
		ve.write(elements.get(1));
		ve.write(Keys.ENTER);
		ve.write(elements.get(2));
		ve.verifyNumList(elements);
	}

	@Test(groups = {"VisualEditor", "VisualEditor_006"})
	public void VisualEditor_006_writeBulletList() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickBullListButton();
		List<String> elements = new ArrayList<String>();
		elements.add("row1");
		elements.add("row2");
		elements.add("row3");
		ve.write(elements.get(0));
		ve.write(Keys.ENTER);
		ve.write(elements.get(1));
		ve.write(Keys.ENTER);
		ve.write(elements.get(2));
		ve.verifyBullList(elements);
	}
}
