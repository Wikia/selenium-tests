/**
 *
 */
package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorMenu.Formatting;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 * https://wikia-inc.atlassian.net/browse/QAART-241
 * Verify paragraph formatting
 * Verify heading formatting
 * Verify sub-heading 1 formatting
 * Verify sub-heading 2 formatting
 * Verify sub-heading 3 formatting
 * Verify sub-heading 4 formatting
 * Verify preformatted formatting
 * Verify page title formatting
 */

public class VisualEditorFormatting extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	private String text = "text";

	@Test
	public void VisualEditorFormatting_001_paragraph() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject visual = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
		visual.selectFormatting(Formatting.PARAGRAPH);
		visual.write(text);
		visual.verifyFormatting(Formatting.PARAGRAPH, text);
	}

	@Test
	public void VisualEditorFormatting_002_heading() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditorPageObject visual = base.gotoArticleEditModeVisual(wikiURL, base.getTimeStamp());
		visual.selectFormatting(Formatting.HEADING);
		visual.write(text);
		visual.verifyFormatting(Formatting.HEADING, text);
	}
}
