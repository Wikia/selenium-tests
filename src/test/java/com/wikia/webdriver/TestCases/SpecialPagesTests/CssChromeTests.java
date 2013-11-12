package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CssEditorContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCssPageObject;

public class CssChromeTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	SpecialCssPageObject specialCss;

	@BeforeMethod(alwaysRun = true)
	public void CssChrome_loginAndOpenSpecialCSS() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		specialCss = wiki.openSpecialCss(wikiURL);
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-285
	 */
	@Test(groups = {"CssChrome_001", "CssChrome", "AdminDashboard"})
	public void CssChrome_001_showingErrorWhenWrongSyntax() {
		specialCss.verifyAceEditorPresence();
		specialCss.verifyHighlighting();
		specialCss.clearCssText();
		specialCss.sendCssText(CssEditorContent.invalidCssError);
		specialCss.verifyAceError();
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"CssChrome_002", "CssChrome", "AdminDashboard"})
	public void CssChrome_002_verifyPublishButtonAppearsAndWorks() {
		String currentTimestamp = specialCss.getTimeStamp();
		specialCss.saveCssContent(currentTimestamp);
		specialCss.openArticleByName(wikiURL, URLsContent.mediaWikiCss);
		String cssContent = specialCss.getWikiaCssContent();
		Assertion.assertEquals(currentTimestamp, cssContent);
	}
	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"CssChrome_003", "CssChrome", "AdminDashboard"})
	public void CssChrome_003_verifyEditSummaryAppearsAndWorks() {
		String currentTimestamp = specialCss.getTimeStamp();
		specialCss.sendEditSummaryText(currentTimestamp);
		specialCss.saveCssContent(currentTimestamp);
		specialCss.openArticleByName(wikiURL, URLsContent.mediaWikiCss);
		specialCss.appendToUrl(URLsContent.historyAction);
		String editSummary = specialCss.getFirstCssRevision();
		Assertion.assertStringContains(editSummary, currentTimestamp);
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"CssChrome_004", "CssChrome", "AdminDashboard"})
	public void CssChrome_004_verifyChangesAppearsAndWorks() {
		String currentTimestamp = specialCss.getTimeStamp();
		specialCss.insertCssText("\n" + currentTimestamp);
		specialCss.clickPublishButtonDropdown();
		specialCss.clickShowChanges();
		specialCss.showChangesModal();
		String addedLine = specialCss.getAddedLineText();
		Assertion.assertEquals(currentTimestamp, addedLine);
	}

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"CssChrome_005", "CssChrome", "AdminDashboard"})
	public void CssChrome_005_verifyMinorEditAppearsAndWorks() {
		String currentTimestamp = specialCss.getTimeStamp();
		specialCss.verifyMinorEditAppears();
		specialCss.clickMinorCheckbox();
		specialCss.saveCssContent(currentTimestamp);
		specialCss.openArticleByName(wikiURL, URLsContent.mediaWikiCss);
		specialCss.appendToUrl(URLsContent.historyAction);
		specialCss.verifyRevisionMarkedAsMinor();
	}

	@Test(groups = {"CssChrome_006", "CssChrome", "AdminDashboard"})
	public void CssChrome_006_verifyHistoryButtonAppearsAndWorks() {
		specialCss.clickPublishButtonDropdown();
		specialCss.clickHistoryButton();
		specialCss.verifyUrl("action=history");
	}

	@Test(groups = {"CssChrome_007", "CssChrome", "AdminDashboard"})
	public void CssChrome_007_verifyDeleteButtonAppearsAndWorks() {
		specialCss.verifyAceEditorPresence();
		specialCss.verifyArticleIsNotRemoved();
		specialCss.clickPublishButtonDropdown();
		specialCss.clickDeleteButton();
		specialCss.confirmDelete();

		specialCss.openSpecialCss(wikiURL);
		specialCss.verifyAceEditorPresence();
		specialCss.verifyArticleIsRemoved();
		specialCss.clickPublishButtonDropdown();
		specialCss.clickUndeleteButton();
		specialCss.confirmUndelete();

		specialCss.openSpecialCss(wikiURL);
		specialCss.verifyAceEditorPresence();
		specialCss.verifyArticleIsNotRemoved();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-731 story description
	 * https://wikia-inc.atlassian.net/browse/DAR-880 development ticket
	 */
	@Test(groups = {"CssChrome_008", "CssChrome", "AdminDashboard"})
	public void CssChrome_008_verifyOnLeaveMessageWorks() {
		specialCss.verifyAceEditorPresence();
		specialCss.sendCssText(CssEditorContent.validCss);
		driver.get(wikiURL);
		specialCss.waitForAlertAndAccept();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-999
	 */
	@Test(groups = {"CssChrome_009", "CssChrome", "AdminDashboard"})
	public void CssChrome_009_verifyTalkButtonWorks() {
		specialCss.verifyTalkBubblePresence();
		int commentsFromSpecialCss = specialCss.getNumberFromCssTalkBubble();
		specialCss.clickTalkButton();
		Assertion.assertEquals(commentsFromSpecialCss, specialCss.getNumberFromWikaiCssTalkBubble());
	}
}
