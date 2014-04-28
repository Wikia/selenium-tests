package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CssEditorContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate_TwoDrivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCssPageObject;

public class CssChromeTests_TwoDrivers extends NewTestTemplate_TwoDrivers {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"cssChromeTwoDrivers_001", "CssChrome"})
	public void cssChromeTwoDrivers_001_verifyThatConflictAppearsWithTheLatestRevision() {
		//first user opens the special:CSS
		switchToWindow(driverOne);
		WikiBasePageObject base1 = new WikiBasePageObject(driverOne);
		base1.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialCssPageObject specialCss1 = base1.openSpecialCss(wikiURL);
		specialCss1.verifyAceEditorPresence();
		specialCss1.sendCssText(CssEditorContent.validCss);
		//second user opens the special:CSS

		switchToWindow(driverTwo);
		WikiBasePageObject base2 = new WikiBasePageObject(driverTwo);
		base2.logInCookie(credentials.userNameStaff2, credentials.passwordStaff2, wikiURL);
		SpecialCssPageObject specialCss2 = base2.openSpecialCss(wikiURL);
		specialCss2.verifyAceEditorPresence();
		specialCss2.sendCssText(CssEditorContent.validCss2);

		//first publishes his changes
		switchToWindow(driverOne);
		specialCss1.clickPublishButton();
		specialCss1.verifyAceEditorPresence(); //make sure page reloaded

		//second user publishes his changes
		switchToWindow(driverTwo);
		specialCss2.clickPublishButton();
		specialCss2.verifyConflictArea();
		specialCss2.verifyLatestRevision();
	}
}
