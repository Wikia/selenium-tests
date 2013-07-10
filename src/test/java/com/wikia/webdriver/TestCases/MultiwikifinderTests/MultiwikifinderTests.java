package com.wikia.webdriver.TestCases.MultiwikifinderTests;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Multiwikifinder.SpecialMultiwikifinderPageObject;
import com.wikia.webdriver.Common.DataProvider.ArticleDataProvider;

import org.testng.annotations.Test;

/**
 *
 * @author łukasz
 */
public class MultiwikifinderTests extends TestTemplate{

    @Test(groups = { "Multiwikifinder_001" ,"Multiwikifinder" })
    public void multiwikifinderTests_001_notExistingPagename() {
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiwikifinderPageObject multiwikifinder = new SpecialMultiwikifinderPageObject(driver);
		multiwikifinder.openSpecialMultiwikifinderPage();
		multiwikifinder.findPagename(multiwikifinder.getTimeStamp());
		multiwikifinder.verifyFoundNotExistingPagename();
    }

    @Test(groups = { "Multiwikifinder_002" , "Multiwikifinder"})
    public void multiwikifinderTests_002_maxAmoutOfLinksOnPage() {
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiwikifinderPageObject multiwikifinder = new SpecialMultiwikifinderPageObject(driver);
		multiwikifinder.openSpecialMultiwikifinderPage();
		multiwikifinder.findPagename("A");
		multiwikifinder.clickAndVerifyMaxAmountOfLinksOnPage();
    }

    @Test(groups = { "Multiwikifinder_003" , "Multiwikifinder"})
    public void multiwikifinderTests_003_checkPagination() {
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiwikifinderPageObject multiwikifinder = new SpecialMultiwikifinderPageObject(driver);
		multiwikifinder.openSpecialMultiwikifinderPage();
		multiwikifinder.findPagename("A");
		multiwikifinder.verifyPagination();
    }

    @Test(
		dataProviderClass = ArticleDataProvider.class,
		dataProvider = "getPopularPagenames",
		groups = { "Multiwikifinder_004" , "Multiwikifinder"}
    )
    public void multiwikifinderTests_004_pagenameInPath(String popularPagename) {
		CommonFunctions.logOut(driver);
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.openWikiPage();
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);
		SpecialMultiwikifinderPageObject multiwikifinder = new SpecialMultiwikifinderPageObject(driver);
		multiwikifinder.openSpecialMultiwikifinderPage();
		multiwikifinder.findPagename(popularPagename);
		multiwikifinder.verifyAllLinksHavePagenameInPath(popularPagename);
    }
}