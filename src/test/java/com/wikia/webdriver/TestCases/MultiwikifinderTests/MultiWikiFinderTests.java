package com.wikia.webdriver.TestCases.MultiwikifinderTests;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Multiwikifinder.SpecialMultiWikiFinderPageObject;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;
import com.wikia.webdriver.Common.DataProvider.SearchDataProvider;
import java.util.List;

import org.testng.annotations.Test;

/**
 *
 * @author łukasz
 */
public class MultiWikiFinderTests extends TestTemplate{

	private String pageName = "Wiki";

	@Test(groups = { "MultiWikiFinder_001" ,"MultiWikiFinder" })
	public void multiWikiFinderTests_001_notExistingPagename() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
		multiWikiFinder.openSpecialMultiWikiFinderPage();
		multiWikiFinder.findPageName(multiWikiFinder.getTimeStamp());
		multiWikiFinder.compareResultsCount(0);
	}

	@Test(groups = { "MultiWikiFinder_002" , "MultiWikiFinder"})
	public void multiWikiFinderTests_002_maxAmoutOfLinksOnPage() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
		multiWikiFinder.openSpecialMultiWikiFinderPage();
		multiWikiFinder.findPageName(pageName);
		for(int element : SearchDataProvider.getSearchLimits()){
			multiWikiFinder.limitResults(pageName, element);
			multiWikiFinder.compareResultsCount(element);
		}
	}

	@Test(groups = { "MultiWikiFinder_003" , "MultiWikiFinder"})
	public void multiwikiFinderTests_003_checkPagination() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
		multiWikiFinder.openSpecialMultiWikiFinderPage();
		multiWikiFinder.findPageName(pageName);
		multiWikiFinder.verifyPagination();
	}

	@Test(
		dataProviderClass = ArticleDataProvider.class,
		dataProvider = "getPageNames",
		groups = { "MultiWikiFinder_004" , "MultiWikiFinder"}
	)
	public void multiWikifinderTests_004_pagenameInPath(String popularPagename) {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
		multiWikiFinder.openSpecialMultiWikiFinderPage();
		multiWikiFinder.findPageName(popularPagename);
		multiWikiFinder.verifyAllLinksHavePagenameInPath(popularPagename);
	}

	@Test(groups = { "MultiWikiFinder_005" ,"MultiWikiFinder" })
	public void multiWikiFinderTests_005_emptyPagename() {
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiWikiFinderPageObject multiWikiFinder = new SpecialMultiWikiFinderPageObject(driver);
		multiWikiFinder.openSpecialMultiWikiFinderPage();
		multiWikiFinder.findPageName("");
		multiWikiFinder.verifyEmptyPagename();
	}
}