package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.CssEditorContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate_Two_Drivers;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCssPageObject;

public class CssChromeTests_TwoDrivers extends TestTemplate_Two_Drivers {

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-731 stories description
	 * https://wikia-inc.atlassian.net/browse/DAR-880 development ticket
	 */
	@Test(groups = {"cssChromeTwoDrivers_001", "cssChrome"})
	public void cssChromeTwoDrivers_001_verifyThatConflictAppearsWithTheLatestRevision()
	{
		//first user opens the special:CSS
		switchToWindow(driver);
		WikiBasePageObject wiki1 = new WikiBasePageObject(driver);
		wiki1.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);
		SpecialCssPageObject specialCss1 = wiki1.openSpecialCss();
		specialCss1.verifyAceEditorPresence();
		specialCss1.sendCssText(CssEditorContent.validCss);
		//second user opens the special:CSS
		switchToWindow(driver2);
		WikiBasePageObject wiki2 = new WikiBasePageObject(driver2);
		wiki2.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff2, Properties.passwordStaff2, driver2);
		SpecialCssPageObject specialCss2 = wiki2.openSpecialCss();
		specialCss2.verifyAceEditorPresence();
		specialCss2.sendCssText(CssEditorContent.validCss2);
		//first publishes his changes
		switchToWindow(driver);
		specialCss1.clickPublishButton();
		specialCss1.verifyAceEditorPresence(); //make sure page reloaded
		//second user publishes his changes
		switchToWindow(driver2);
		specialCss2.clickPublishButton();
		specialCss2.verifyConflictArea();
		specialCss2.verifyLatestRevision();
	}
}
