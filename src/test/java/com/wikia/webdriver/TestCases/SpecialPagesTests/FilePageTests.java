package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.DiffPage.DiffPagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HistoryPage.HistoryPagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;

public class FilePageTests extends TestTemplate {
	
	@Test(groups = {"FilePage", "FilePage001"})
	public void filePage001_tabs() {
		FilePagePageObject filePage = new FilePagePageObject(driver);
		filePage.openFilePage(URLsContent.fileName);

		filePage.verifySelectedTab("about");
		
		filePage.selectTab(1);
		filePage.verifySelectedTab("history");

		filePage.selectTab(0);
		filePage.verifySelectedTab("about");

		filePage.selectTab(2);
		filePage.verifySelectedTab("metadata");
	}
	
	@Test(groups = {"FilePage", "FilePage002"})
	public void filePage002_tabsLoggedIn() {
		CommonFunctions.logOut(driver);

		CommonFunctions.logInCookie(Properties.userName, Properties.password);

		FilePagePageObject filePage = new FilePagePageObject(driver);
		filePage.openFilePage(URLsContent.fileName);
		
		filePage.refreshAndVerifyTabs(0);
		filePage.refreshAndVerifyTabs(1);
		filePage.refreshAndVerifyTabs(2);
	}

	// Diff Page
	@Test(groups = {"FilePage", "FilePage003"})
	public void filePage003_diffPage() {
		
		HistoryPagePageObject historyPage = new HistoryPagePageObject(driver);

		historyPage.openHistoryPage(Global.DOMAIN + URLsContent.wikiDir + URLsContent.fileNS + URLsContent.fileName);
		historyPage.goToDiffPageFromHistoryPage();
		
		DiffPagePageObject diffPage = new DiffPagePageObject(driver);
		
		diffPage.verifyDiffTablePresent();
	}

    // Testing "Appears on these pages"
    @Test(groups = {"FilePage", "FilePage004"})
    public void filePage004_appearsOn() {
        FilePagePageObject filePage = new FilePagePageObject(driver);
        filePage.openFilePage(URLsContent.fileName002);

        // This should be the first article in the list
        filePage.verifyAppearsOn(URLsContent.articleName001);

        // After paging, article #4 should be at the top of the list
        filePage.localAppearsPageNext();
        filePage.verifyAppearsOn(URLsContent.articleName003);
    }
}
