package com.wikia.webdriver.TestCases.Top10ListTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiHistoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Top10.Top_10_list;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Top10.Top_10_list_EditMode;

// https://internal.wikia-inc.com/wiki/Top_10_List/QA#Tests_on_Development_environment  - TOP 10 list QA specification
public class Top10ListTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	private String Description;
	private String top_10_list_Name;
	private String relatedPageName;

	@Test(groups = { "Top10List_001", "Top10List" })
	public void Top10List_001_createTop10list() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		this.top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addDescription(PageContent.top10Description);
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyArticleText(PageContent.top10Description);
	}

	@Test(groups = { "Top10List_002", "Top10List" })
	public void Top10List_002_createTop10listWithItems() {
		PageObjectLogging.log(
				"ACTIVE BUG 35690",
				"on CHROME, FF and IE: verifyItemPresent steps are likely to fail. "
				+ "NOTE: the defect SOMETIMES does not occur",
				false
		);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		this.top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
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

	@Test(groups = { "Top10List_003", "Top10List" })
	public void Top10List_003_anonymousEditingPermissions() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		Top_10_list top10list = wiki.openTop10List(PageContent.top10TestListQAfull, wikiURL);
		top10list.verifyTop10listPageTitle(PageContent.top10TestListQAshort);
		top10list.clickEditAsAnon();
		top10list.verifyModalLoginAppeared();
	}

/**
 * enable when 94877 is fixed, then the test case can be finished
 * this test creation can be completed after 94877 defect is fixed
 * photoUpload on top10list doesn't allow overwriting uploaded photos.
 * solution: delete Image001.jpg file from wiki before each execution of this test.
 * not possible to do due to 94877 bug.
 */
	@Test(enabled = false, groups = { "Top10List_004", "Top10List" })
	public void Top10List_004_createTop10listPageWithUploadedPhoto() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		String top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addAPhoto("Image001.jpg");
		top10listCreation.verifyPhotoAppeared("Image001.jpg");
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyPhotoOnTop10page("Image001.jpg");
	}

	@Test(enabled = false, groups = { "Top10List_005", "Top10List" })
	public void Top10List_005_createTop10listPageWithSelectedPhoto() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		this.top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();
		this.relatedPageName = PageContent.relatedPageName;
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.typeRelatedPageName(relatedPageName);
		top10listCreation.clickAddAPhoto();
		top10listCreation.checkFetchedPhotoAppears();
		top10listCreation.addTheFetchedPhoto();
		top10listCreation.verifyPhotoAppeared();
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyRelatedPhotoOnTop10page(relatedPageName);
	}

	@Test(enabled = false, groups = { "Top10List_006", "Top10List" }) //story 93947
	public void Top10List_006_deleteItemFromTop10listPage() {
		PageObjectLogging.log(
				"ACTIVE BUG 35690",
				"after the bug is fixed, remove the line 'refreshpage(). "
				+ "It is added only to work around the bug",
				false
		);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(Properties.userName, Properties.password, wikiURL);
		this.top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
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

	@Test(groups = { "Top10List_007", "Top10List" })
	public void Top10List_007_createTop10listAnonymous() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		this.top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
		top10listCreation.verifyPermissionsErrorsPresent();
	}

	@Test(groups = { "Top10List_008", "Top10List" })
	public void Top10List_008_deleteTop10listAnonymous() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		Top_10_list top10list = base.openTop10List(PageContent.top10TestListQAfull, wikiURL);
		top10list.deletePage();
		top10list.verifyPermissionsErrorsPresent();
	}

	@Test(groups = { "Top10List_009", "Top10List" })
	public void Top10List_009_historyTop10listAnonymous() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		Top_10_list top10list = base.openTop10List(PageContent.top10TestListQAfull, wikiURL);
		WikiHistoryPageObject top10history = top10list.openHistoryForCurrentPage();
		top10history.verifyImportandPageElements();
	}

	@Test(groups = { "Top10List_010", "Top10List" })
	public void Top10List_010_historyTop10listLoggedIn() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		Top_10_list top10list = base.openTop10List(PageContent.top10TestListQAfull, wikiURL);
		WikiHistoryPageObject top10history = top10list.openHistoryForCurrentPage();
		top10history.verifyImportandPageElements();
	}

	@Test(enabled = false, groups = { "Top10List_011", "Top10List" })
	public void Top10List_011_voteTop10listLoggedIn() {
		PageObjectLogging.log(
				"ACTIVE BUG 35690",
				"verifyItemPresent steps are likely to fail, which will make it impossible to vote for an item. "
				+ "NOTE: the defect SOMETIMES does not occur",
				false
		);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		this.top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addItem(1, "Item1");
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyItemPresent(1, "Item1");
		int voteCount = top10list.getVoteCountOfItem(1);
		top10list.voteForItem(1);
		top10list.verifyVoteCountOfItem(voteCount+1, 1);
	}

	@Test(groups = { "Top10List_012", "Top10List" })
	public void Top10List_012_voteTop10listAnonymous() {
		// bug: 95218 'Vote up' button is not displayed for anonymous user
		WikiBasePageObject base = new WikiBasePageObject(driver);
		Top_10_list top10list = base.openTop10List(PageContent.top10TestListQAfull, wikiURL);
		top10list.verifyThereAreVoteButtons();
	}

	@Test(groups = { "Top10List_013", "Top10List" })
	public void Top10List_013_deleteTop10listLoggedInNonStaff() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		Top_10_list top10list = base.openTop10List(PageContent.top10TestListQAfull, wikiURL);
		top10list.deletePage();
		top10list.verifyPermissionsErrorsPresent();
	}

	@Test(groups = { "Top10List_014", "Top10List" })
	public void Top10List_014_deleteTop10listSTAFF() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		this.top_10_list_Name = PageContent.top10listNamePrefix + base.getTimeStamp();;
		SpecialCreateTopListPageObject top10listCreation = base.createNewTop10list(top_10_list_Name, wikiURL);
		top10listCreation.verifyListName(top_10_list_Name);
		top10listCreation.addDescription(PageContent.top10Description);
		Top_10_list top10list = top10listCreation.clickCreateList();
		top10list.verifyTop10listPageTitle(top_10_list_Name);
		top10list.verifyArticleText(PageContent.top10Description);
		top10list.deleteTop10List(top_10_list_Name);
	}

	@Test(groups = { "Top10List_015", "Top10List" })
	public void Top10List_015_editTop10list() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		this.Description = PageContent.top10Description + base.getTimeStamp();
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		Top_10_list top10list = base.openTop10List(PageContent.top10TestListQAfull, wikiURL);
		top10list.verifyTop10listPageTitle(PageContent.top10TestListQAshort);
		Top_10_list_EditMode top10listEdit = top10list.clickEditAsLoggedIn();
		top10listEdit.addNewDescription(Description);
		top10list = top10listEdit.clickSaveList();
		top10list.verifyArticleText(Description);
	}
}
