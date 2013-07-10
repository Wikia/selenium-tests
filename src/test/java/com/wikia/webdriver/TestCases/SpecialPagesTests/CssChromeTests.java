package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CssEditorContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCssPageObject;

public class CssChromeTests extends TestTemplate {

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-285
	 */
	@Test(groups = {"cssChrome_001", "cssChrome", "AdminDashboard"})
	public void cssChrome_001_syntaxHighlightingIsViewable() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.verifyAceEditorPresence();
		specialCss.verifyHighlighting();
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-285
	 */
	@Test(groups = {"cssChrome_002", "cssChrome", "AdminDashboard"})
	public void cssChrome_002_showingErrorWhenWrongSyntax() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.verifyAceEditorPresence();
		specialCss.clearCssText();
		specialCss.sendCssText(CssEditorContent.invalidCssError);
		specialCss.verifyAceError();
	}

}
