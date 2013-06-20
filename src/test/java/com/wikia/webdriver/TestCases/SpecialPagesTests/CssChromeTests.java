package com.wikia.webdriver.TestCases.SpecialPagesTests;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
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
		/*  I'm not sure does it work properly
			specialCss.sendCssText(CssEditorContent.invalidCssError);
		 */
		specialCss.sendAceCssText(CssEditorContent.invalidCssError);
		specialCss.verifyAceError();
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"cssChrome_003", "cssChrome", "AdminDashboard"})
	public void cssChrome_003_verifyPublishButtonAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		String randomText = specialCss.generateRandomString();
		specialCss.saveCssContent(randomText, wiki);
		wiki.openArticle(URLsContent.mediaWikiCss);
		String cssContent = wiki.getWikiaCssContent();
		Assertion.assertEquals(randomText, cssContent);
	}
	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"cssChrome_004", "cssChrome", "AdminDashboard"})
	public void cssChrome_004_verifyEditSummaryAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		String randomText = specialCss.generateRandomString();
		specialCss.sendEditSummaryText(randomText);
		specialCss.saveCssContent(randomText, wiki);
		wiki.openArticle(URLsContent.buildUrl(URLsContent.mediaWikiCss, URLsContent.historyAction));
		String editSummary = wiki.getFirstCssRevision();
		randomText = "(" + randomText + ")";
		Assertion.assertEquals(randomText, editSummary);
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"cssChrome_005", "cssChrome", "AdminDashboard"})
	public void cssChrome_005_verifyChangesAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.verifyPublishButtonAppears();
		specialCss.clickPublishButtonDropdown();
		specialCss.clickShowChanges();
		specialCss.showModalChanges();
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"cssChrome_006", "cssChrome", "AdminDashboard"})
	public void cssChrome_006_verifyMinorEditAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		String randomText = specialCss.generateRandomString();
		specialCss.verifyMinorEditAppears();
		specialCss.clickMinorCheckbox();
		specialCss.saveCssContent(randomText, wiki);
		wiki.openArticle(URLsContent.buildUrl(URLsContent.mediaWikiCss, URLsContent.historyAction));
		wiki.checkMinorEdit();
	}
}
