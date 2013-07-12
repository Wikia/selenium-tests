package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CssEditorContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
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

	/**
	 * http://wikia-inc.atlassian.net/browse/DAR-733
	 */
	@Test(groups = {"cssChrome_003", "cssChrome", "AdminDashboard"})
	public void cssChrome_003_verifyPublishButtonAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		String currentTimestamp = specialCss.getTimeStamp();
		specialCss.saveCssContent(currentTimestamp, wiki);
		wiki.openArticle(URLsContent.mediaWikiCss);
		String cssContent = wiki.getWikiaCssContent();
		Assertion.assertEquals(currentTimestamp, cssContent);
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
		String currentTimestamp = specialCss.getTimeStamp();
		specialCss.sendEditSummaryText(currentTimestamp);
		specialCss.saveCssContent(currentTimestamp, wiki);
		wiki.openArticle(URLsContent.buildUrl(URLsContent.mediaWikiCss, URLsContent.historyAction));
		String editSummary = wiki.getFirstCssRevision();
		Assertion.assertStringContains(editSummary, currentTimestamp);
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
	@Test(groups = {"cssChrome_006", "cssChrome", "AdminDashboard"})
	public void cssChrome_006_verifyMinorEditAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		String currentTimestamp = specialCss.getTimeStamp();
		specialCss.verifyMinorEditAppears();
		specialCss.clickMinorCheckbox();
		specialCss.saveCssContent(currentTimestamp, wiki);
		wiki.openArticle(URLsContent.buildUrl(URLsContent.mediaWikiCss, URLsContent.historyAction));
		wiki.verifyRevisionMarkedAsMinor();
	}

	@Test(groups = {"cssChrome_007", "cssChrome", "AdminDashboard"})
	public void cssChrome_007_verifyHistoryButtonAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.clickPublishButtonDropdown();
		specialCss.clickHistoryButton();
		specialCss.verifyUrl("action=history");
	}

	@Test(groups = {"cssChrome_008", "cssChrome", "AdminDashboard"})
	public void cssChrome_008_verifyDeleteButtonAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.verifyAceEditorPresence();
		specialCss.verifyArticleIsNotRemoved();
		specialCss.clickPublishButtonDropdown();
		specialCss.clickDeleteButton();
		specialCss.confirmDelete();
	}

	@Test(groups = {"cssChrome_009", "cssChrome", "AdminDashboard"},
			dependsOnMethods={"cssChrome_008_verifyDeleteButtonAppearsAndWorks"})
	public void cssChrome_009_verifyUndeleteButtonAppearsAndWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.verifyAceEditorPresence();
		specialCss.verifyArticleIsRemoved();
		specialCss.clickPublishButtonDropdown();
		specialCss.clickUndeleteButton();
		specialCss.confirmUndelete();
		specialCss = wiki.openSpecialCss();
		specialCss.verifyAceEditorPresence();
		specialCss.verifyArticleIsNotRemoved();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-731 story description
	 * https://wikia-inc.atlassian.net/browse/DAR-880 development ticket
	 */
	@Test(groups = {"cssChrome_010", "cssChrome", "AdminDashboard"})
	public void cssChrome_010_verifyOnLeaveMessageWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.verifyAceEditorPresence();
		specialCss.sendCssText(CssEditorContent.validCss);
		specialCss.navigateToHistoryPage();
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-999
	 */
	@Test(groups = {"cssChrome_011", "cssChrome", "AdminDashboard"})
	public void cssChrome_011_verifyTalkButtonWorks() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialCssPageObject specialCss = wiki.openSpecialCss();
		specialCss.verifyTalkBubblePresence();
		int commentsFromSpecialCss = specialCss.getNumberFromCssTalkBubble();
		specialCss.clickTalkButton();
		Assertion.assertEquals(commentsFromSpecialCss, specialCss.getNumberFromWikaiCssTalkBubble());
		
	}

}
