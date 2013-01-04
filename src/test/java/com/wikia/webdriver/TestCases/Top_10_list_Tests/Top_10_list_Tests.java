package com.wikia.webdriver.TestCases.Top_10_list_Tests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateBlogPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.Top_10_list;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.Top_10_list_EditMode;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;

// https://internal.wikia-inc.com/wiki/Top_10_List/QA#Tests_on_Development_environment  - TOP 10 list QA specification
public class Top_10_list_Tests extends TestTemplate {
	
	@Test(groups = { "Top_10_list_Tests_001", "Top_10_list_Tests" })
	public void Top_10_list_Tests_001_createTop10list() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		String Description = "DescriptionForList";
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addDescription(Description);
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyArticleText(Description);
	}
		
	@Test(groups = { "Top_10_list_Tests_002", "Top_10_list_Tests" })
	public void Top_10_list_Tests_002_createTop10listWithItems() {
		
		if (Global.BROWSER.equals("CHROME") || Global.BROWSER.equals("IE")) {
			PageObjectLogging.log("ACTIVE BUG 35690", "on CHROME and IE: verifyItemPresent steps are likely to fail. NOTE: the defect SOMETIMES does not occur", false, driver);
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
		String top_10_list_Name = "Top10list" + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = article.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyListName(top_10_list_Name);
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		CommonFunctions.logOut(driver);
		top10list.navigateBack();
		top10list.refreshPage();
		top10list.clickEditAsAnon();
		top10list.verifyModalLoginAppeared();
	}
	
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
			PageObjectLogging.log("ACTIVE BUG 35690", "after the bug is fixed, remove the line 'refreshpage(). It is added only to work around the bug", false, driver);
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
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialCreateTopListPageObject top10listCreation = wiki.createNewTop_10_list(top_10_list_Name);
		top10listCreation.verifyPermissionsErrorsPresent();
	}
	
}
