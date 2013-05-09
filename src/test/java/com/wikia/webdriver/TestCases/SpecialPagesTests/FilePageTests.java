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
	
	/**
	 * Verify functionality of tabs on file pages in Oasis.  When a tab
	 * is clicked, the corresponding content should be displayed. 
	 * 
	 * @author "Liz Lee"
	 */
	@Test(groups = {"FilePage", "filePage001_tabs"})
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
	
	/**
	 * Verify that file page tabs will save their state for logged in users
	 * when they navigate away from the page and back to it. 
	 * 
	 * @author "Liz Lee"
	 */
	@Test(groups = {"FilePage", "filePage002_tabsLoggedIn"})
	public void filePage002_tabsLoggedIn() {
		CommonFunctions.logOut(driver);

		CommonFunctions.logInCookie(Properties.userName, Properties.password);

		FilePagePageObject filePage = new FilePagePageObject(driver);
		filePage.openFilePage(URLsContent.fileName);
		
		filePage.refreshAndVerifyTabs(0);
		filePage.refreshAndVerifyTabs(1);
		filePage.refreshAndVerifyTabs(2);
	}

	/**
	 * Verify if a diff table is present on a diff page.  
	 * Note that not all diff pages have diff tables but the one specified does. 
	 * 
	 * @author "Liz Lee"
	 */
	@Test(groups = {"FilePage", "filePage003_diffPage"})
	public void filePage003_diffPage() {
		
		HistoryPagePageObject historyPage = new HistoryPagePageObject(driver);

		historyPage.openHistoryPage(Global.DOMAIN + URLsContent.wikiDir + URLsContent.fileNS + URLsContent.fileName);
		historyPage.goToDiffPageFromHistoryPage();
		
		DiffPagePageObject diffPage = new DiffPagePageObject(driver);
		
		diffPage.verifyDiffTablePresent();
	}

    /**
     * Testing "Appears on these pages"
     * 
     * @author Garth Webb
     */
    @Test(groups = {"FilePage", "filePage004_appearsOn"})
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
