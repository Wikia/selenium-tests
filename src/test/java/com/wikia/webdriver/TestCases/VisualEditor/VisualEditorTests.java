/**
 *
 */
package com.wikia.webdriver.TestCases.VisualEditor;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VisualEditorContent;
import com.wikia.webdriver.Common.Templates.VeTestTemplate;
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

public class VisualEditorTests extends VeTestTemplate{

	@Test(groups = {"VE", "VE_001"})
	public void VE_001_writeBoldText() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickBoldButton();
		ve.write(VisualEditorContent.text);
		ve.verifyTextBold(VisualEditorContent.text);
		ve.selectBoldText();
		ve.clickClearButton();
		ve.verifyTextNotFormatted(VisualEditorContent.text);
	}

	@Test(groups = {"VE", "VE_002"})
	public void VE_002_writeItalicText() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickItalicButton();
		ve.write(VisualEditorContent.text);
		ve.verifyTextItalic(VisualEditorContent.text);
		ve.selectItalicText();
		ve.clickClearButton();
		ve.verifyTextNotFormatted(VisualEditorContent.text);
	}

	@Test(groups = {"VE", "VE_003"})
	public void VE_003_addLink() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.write(VisualEditorContent.text);
		ve.clickLinkButton();
		ve.typeHyperlink(PageContent.wikiaGlobalUrl);
		ve.verifyLink(VisualEditorContent.text, PageContent.wikiaGlobalUrl);
	}

	@Test(groups = {"VE", "VE_004"})
	public void VE_004_writeCodeText() {
		VisualEditorPageObject ve = new VisualEditorPageObject(driver);
		ve.clear();
		ve.clickCodeButton();
		ve.write(VisualEditorContent.text);
		ve.verifyCodeText(VisualEditorContent.text);
		ve.selectCodeText();
		ve.clickClearButton();
		ve.verifyTextNotFormatted(VisualEditorContent.text);
	}

	@Test(groups = {"VE", "VE_005"})
	public void VE_005_writeNumList() {
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

	@Test(groups = {"VE", "VE_006"})
	public void VE_006_writeBulletList() {
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
