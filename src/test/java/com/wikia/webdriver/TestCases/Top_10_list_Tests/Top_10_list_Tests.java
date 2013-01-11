package com.wikia.webdriver.TestCases.Top_10_list_Tests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.Top_10_list;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.Top_10_list_EditMode;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiHistoryPageObject;

// https://internal.wikia-inc.com/wiki/Top_10_List/QA#Tests_on_Development_environment  - TOP 10 list QA specification
public class Top_10_list_Tests extends TestTemplate {
	
	@Test(groups = { "Top_10_list_Tests_001", "Top_10_list_Tests" })
	public void Top_10_list_Tests_001_createTop10list() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addDescription(PageContent.top10Description);
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyArticleText(PageContent.top10Description);
	}
		
	@Test(groups = { "Top_10_list_Tests_002", "Top_10_list_Tests" })
	public void Top_10_list_Tests_002_createTop10listWithItems() {
		
		if (Global.BROWSER.equals("CHROME") || Global.BROWSER.equals("IE")) {
			PageObjectLogging.log("ACTIVE BUG 35690", "on CHROME and IE: verifyItemPresent steps are likely to fail. NOTE: the defect SOMETIMES does not occur", false);
		}		
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addItem(1, "Item1");
		top10listCreation.addItem(2, "Item2");
		top10listCreation.addItem(3, "Item3");
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyItemPresent(1, "Item1");
		top10list.verifyItemPresent(2, "Item2");
		top10list.verifyItemPresent(3, "Item3");
	}
	
	@Test(groups = { "Top_10_list_Tests_003", "Top_10_list_Tests" })
	public void Top_10_list_Tests_003_anonymousEditingPermissions() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull);
		top10list.verifyTop10listPageTitle(PageContent.top10TestListQAshort);
		top10list.clickEditAsAnon();
		top10list.verifyModalLoginAppeared();
	}
	
//  uncomment when 94877 is fixed, then the test case can be finished
//	@Test(groups = { "Top_10_list_Tests_004", "Top_10_list_Tests" })
	public void Top_10_list_Tests_004_createTop10listPageWithUploadedPhoto() {
		// this test creation can be completed after 94877 defect is fixed
		// photoUpload on top10list doesn't allow overwriting uploaded photos.
		// solution: delete Image001.jpg file from wiki before each execution of this test. - not possible to do due to 94877 bug. 
		
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addAPhoto("Image001.jpg");
		top10listCreation.verifyPhotoAppeared("Image001.jpg");
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyPhotoOnTop10page("Image001.jpg");
	}
	
	@Test(groups = { "Top_10_list_Tests_005", "Top_10_list_Tests" }) //story 94880
	public void Top_10_list_Tests_005_createTop10listPageWithSelectedPhoto() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		String relatedPageName = "PageToCheckTop10ListFetching";
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.typeRelatedPageName(relatedPageName);
		top10listCreation.clickAddAPhoto();
		top10listCreation.checkFetchedPhotoAppears();
		top10listCreation.addTheFetchedPhoto();
		top10listCreation.verifyPhotoAppeared();
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyRelatedPhotoOnTop10page(relatedPageName);
	}
	
	@Test(groups = { "Top_10_list_Tests_006", "Top_10_list_Tests" }) //story 93947
	public void Top_10_list_Tests_006_deleteItemFromTop10listPage() {
		if (Global.BROWSER.equals("CHROME") || Global.BROWSER.equals("IE")) {
			PageObjectLogging.log("ACTIVE BUG 35690", "after the bug is fixed, remove the line 'refreshpage(). It is added only to work around the bug", false);
		}		
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addItem(1, "Item1");
		top10listCreation.addItem(2, "Item2");
		top10listCreation.addItem(3, "Item3");
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		//after 35690 is fixed, remove the below line top10list.refreshPage();
		top10list.refreshPage();
		top10list.verifyItemPresent(1, "Item1");
		top10list.verifyItemPresent(2, "Item2");
		top10list.verifyItemPresent(3, "Item3");
		Top_10_list_EditMode top10listEdit = top10list.clickEditAsLoggedIn();
		top10listEdit.removeItem();
		top10listEdit.removeItem();
		top10listEdit.removeItem();
		top10list = top10listEdit.clickSaveList();
		top10list.verifyItemsNotPresent();
	}
	
	@Test(groups = { "Top_10_list_Tests_007", "Top_10_list_Tests" })
	public void Top_10_list_Tests_007_createTop10listAnonymous() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		wiki.openWikiPage();
		SpecialCreateTopListPageObject top10listCreation = wiki.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyPermissionsErrorsPresent();
	}	
	
	@Test(groups = { "Top_10_list_Tests_008", "Top_10_list_Tests" })
	public void Top_10_list_Tests_008_deleteTop10listAnonymous() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull);
		top10list.clickOnDeleteButton();
		top10list.verifyPermissionsErrorsPresent();
	}
	
	@Test(groups = { "Top_10_list_Tests_009", "Top_10_list_Tests" })
	public void Top_10_list_Tests_009_historyTop10listAnonymous() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull);
		WikiHistoryPageObject top10history = top10list.openHistoryPage();
		top10history.verifyImportandPageElements();
	}
	
	@Test(groups = { "Top_10_list_Tests_010", "Top_10_list_Tests" })
	public void Top_10_list_Tests_010_historyTop10listLoggedIn() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull);
		WikiHistoryPageObject top10history = top10list.openHistoryPage();
		top10history.verifyImportandPageElements();
	}
	
	@Test(groups = { "Top_10_list_Tests_011", "Top_10_list_Tests" })
	public void Top_10_list_Tests_011_voteTop10listLoggedIn() {		
		PageObjectLogging.log("ACTIVE BUG 35690", "verifyItemPresent steps are likely to fail, which will make it impossible to vote for an item. NOTE: the defect SOMETIMES does not occur", false);	
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		wiki.openWikiPage();		
		WikiArticlePageObject article = new WikiArticlePageObject(driver,	Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addItem(1, "Item1");
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyItemPresent(1, "Item1");
		int voteCount = top10list.getVoteCountOfItem(1);
		top10list.voteForItem(1);
		top10list.verifyVoteCountOfItem(voteCount+1, 1);
	}
	
	@Test(groups = { "Top_10_list_Tests_012", "Top_10_list_Tests" })
	public void Top_10_list_Tests_012_voteTop10listAnonymous() {				
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		CommonFunctions.logOut(driver);
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull);	
		top10list.verifyThereAreVoteButtons();
	}
	
	@Test(groups = { "Top_10_list_Tests_013", "Top_10_list_Tests" })
	public void Top_10_list_Tests_013_deleteTop10listLoggedInNonStaff() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull);
		top10list.clickOnDeleteButton();
		top10list.verifyPermissionsErrorsPresent();
	}
	
	@Test(groups = { "Top_10_list_Tests_014", "Top_10_list_Tests" })
	public void Top_10_list_Tests_014_deleteTop10listSTAFF() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();		
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addDescription(PageContent.top10Description);
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyArticleText(PageContent.top10Description);
		top10list.deleteTop10List(top_10_list_Name);
	}
	
	@Test(groups = { "Top_10_list_Tests_015", "Top_10_list_Tests" })
	public void Top_10_list_Tests_015_editTop10list() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String Description = "DescriptionForList"+ wiki.getTimeStamp();
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull);
		top10list.verifyTop10listPageTitle(PageContent.top10TestListQAshort);
		Top_10_list_EditMode top10listEdit = top10list.clickEditAsLoggedIn();
		top10listEdit.addNewDescription(Description);
		top10list = top10listEdit.clickSaveList();
		top10list.verifyArticleText(Description);
	}

	//		The below logging is in case of writing test that will check if user can vote twice for the same item
	//		PageObjectLogging.log("ACTIVE BUG 33902", "one user car repeatedly vote on the same list. Remove if 33902 bug is fixed ", false);			
	
}
