package com.wikia.webdriver.TestCases.AdminDashboardTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialAdminDashboardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginMonobookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.WikiArticleMonoBookPageObject;

/**
 * tests are prepared to test the following feature: https://wikia-inc.atlassian.net/browse/DAR-136
 *
 * @author wikia
 */
public class editingLocalCssTests extends TestTemplate {

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-293
	 */
	@Test(groups = {"editingLocalCss_001", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_001_UserWithAdminRightsTriesToEditWikiaCss() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		wiki.openArticle(URLsContent.mediaWikiCss);
		wiki.clickEditButton();
		wiki.verifyUrl(URLsContent.specialCSS);
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-294
	 */
	@Test(groups = {"editingLocalCss_002", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_002_UserWithoudAdminRightsHasNoEditOption() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wiki.openArticle(URLsContent.mediaWikiCss);
		wiki.verifyEditButtonNotPresent();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-295
	 */
	@Test(groups = {"editingLocalCss_003", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_003_UserWithoudAdminRightsTriesToAccessWikiaCssUsingParameter() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wiki.openArticle(URLsContent.mediaWikiCss);
		wiki.appendToUrl(URLsContent.actionEditParameter);
		wiki.verifyPermissionsErrorsPresent();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-296
	 */
	@Test(groups = {"editingLocalCss_004", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_004_AnonHasNoEditOptionOnMediawikiWikiaCss() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		wiki.openArticle(URLsContent.mediaWikiCss);
		wiki.verifyEditButtonNotPresent();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-297
	 */
	@Test(groups = {"editingLocalCss_005", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_005_AnonTriesToAccessWikiaCssUsingParameter() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		wiki.openArticle(URLsContent.mediaWikiCss);
		wiki.appendToUrl(URLsContent.actionEditParameter);
		wiki.verifyPermissionsErrorsPresent();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-298
	 */
	@Test(groups = {"editingLocalCss_006", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_006_UserWithAdminRightsTriesToEditWikiaCssUsingParameter() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		wiki.openArticle(URLsContent.mediaWikiCss);
		wiki.appendToUrl(URLsContent.actionEditParameter);
		wiki.verifyUrl(URLsContent.specialCSS);
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-299
	 */
	@Test(groups = {"editingLocalCss_007", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_007_MonobookUserWithAdminRightsEditsWikiaCss() {
		SpecialUserLoginMonobookPageObject loginMonobook = new SpecialUserLoginMonobookPageObject(driver);
		loginMonobook.open();
		loginMonobook.fillLoginForm(Properties.userNameMonobook, Properties.passwordMonobook, "");
		loginMonobook.submitForm();
		loginMonobook.verifyLogin();
		WikiArticleMonoBookPageObject monobookArticle = new WikiArticleMonoBookPageObject(driver);
		monobookArticle.openArticle(URLsContent.mediaWikiCss);
		monobookArticle.clickEdit();
		monobookArticle.verifyEditionArea();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-300
	 */
	@Test(groups = {"editingLocalCss_008", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_008_MonobookUserWithAdminRightsOpensSpecialCss() {
		SpecialUserLoginMonobookPageObject loginMonobook = new SpecialUserLoginMonobookPageObject(driver);
		loginMonobook.open();
		loginMonobook.fillLoginForm(Properties.userNameMonobook, Properties.passwordMonobook, "");
		loginMonobook.submitForm();
		loginMonobook.verifyLogin();
		WikiArticleMonoBookPageObject monobookArticle = new WikiArticleMonoBookPageObject(driver);
		monobookArticle.openArticle(URLsContent.specialCSS);
		monobookArticle.verifyOasisOnly();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-302
	 */
	@Test(groups = {"editingLocalCss_009", "editingLocalCss", "AdminDashboard"})
	public void editingLocalCss_009_UserWithAdminRightsTriesToAccesSpecialCssFromAdminDashboard() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		SpecialAdminDashboardPageObject adminDashboard = wiki.openSpecialAdminDashboard();
		adminDashboard.clickCssTool();
		wiki.verifyUrl(URLsContent.specialCSS);
	}
}
