package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CssEditorContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCssPageObject;

public class CssChromeTests_TwoDrivers extends NewTestTemplate_TwoDrivers {

	Credentials credentials = config.getCredentials();

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-731 stories description
	 * https://wikia-inc.atlassian.net/browse/DAR-880 development ticket
	 */
	@Test(groups = {"cssChromeTwoDrivers_001", "cssChrome"})
	public void cssChromeTwoDrivers_001_verifyThatConflictAppearsWithTheLatestRevision() {
		//first user opens the special:CSS
		switchToWindow(driver);
		WikiBasePageObject base1 = new WikiBasePageObject(driver);
		base1.logInCookie(credentials.userNameStaff, credentials.passwordStaff);
		SpecialCssPageObject specialCss1 = base1.openSpecialCss(wikiURL);
		specialCss1.verifyAceEditorPresence();
		specialCss1.sendCssText(CssEditorContent.validCss);
		//second user opens the special:CSS
		switchToWindow(driverFF);
		WikiBasePageObject base2 = new WikiBasePageObject(driverFF);
		base2.logInCookie(credentials.userNameStaff2, credentials.passwordStaff2);
		SpecialCssPageObject specialCss2 = base2.openSpecialCss(wikiURL);
		specialCss2.verifyAceEditorPresence();
		specialCss2.sendCssText(CssEditorContent.validCss2);
		//first publishes his changes
		switchToWindow(driver);
		specialCss1.clickPublishButton();
		specialCss1.verifyAceEditorPresence(); //make sure page reloaded
		//second user publishes his changes
		switchToWindow(driverFF);
		specialCss2.clickPublishButton();
		specialCss2.verifyConflictArea();
		specialCss2.verifyLatestRevision();
	}
}
