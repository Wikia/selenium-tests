package com.wikia.webdriver.TestCases.AdminDashboardTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBaseMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.MessageWallPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.WikiArticleMonoBookPageObject;

/**
 * tests are prepared to test the following feature: https://wikia-inc.atlassian.net/browse/DAR-136
 * 
 * @author wikia
 *
 */
public class editingLocalCssTests extends TestTemplate{
	
	private String mediaWikiCss = "MediaWiki:Wikia.css";
	private String specialCSS = "Special:CSS";
	private String actionEditParameter = "?action=edit";
	
	/**
	 *	https://wikia-inc.atlassian.net/browse/DAR-293 
	 */
	@Test(groups = { "editingLocalCss_001", "editingLocalCss", "AdminDashboard" })
	public void editingLocalCss_001_UserWithAdminRightsTriesToEditWikiaCss() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		wiki.openArticle(this.mediaWikiCss);
		wiki.clickEditButton("");
		wiki.waitForStringInURL(this.specialCSS);
	}
	
	/**
	 *	https://wikia-inc.atlassian.net/browse/DAR-294
	 */
	@Test(groups = { "editingLocalCss_002", "editingLocalCss", "AdminDashboard" })
	public void editingLocalCss_002_UserWithoudAdminRightsHasNoEditOption() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wiki.openArticle(this.mediaWikiCss);
		wiki.verifyEditButtonNotPresent();
	}
	
	/**
	 *	https://wikia-inc.atlassian.net/browse/DAR-295
	 */
	@Test(groups = { "editingLocalCss_003", "editingLocalCss", "AdminDashboard" })
	public void editingLocalCss_003_UserWithoudAdminRightsTriesToAccessWikiaCssUsingParameter() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
		wiki.openArticle(this.mediaWikiCss);
		wiki.appendToUrl(this.actionEditParameter);
		wiki.verifyPermissionsErrorsPresent();
	}
	
	/**
	 *	https://wikia-inc.atlassian.net/browse/DAR-296
	 */
	@Test(groups = { "editingLocalCss_004", "editingLocalCss", "AdminDashboard" })
	public void editingLocalCss_004_AnonHasNoEditOptionOnMediawikiWikiaCss() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		wiki.openArticle(this.mediaWikiCss);
		wiki.verifyEditButtonNotPresent();
	}
	
	/**
	 *	https://wikia-inc.atlassian.net/browse/DAR-297
	 */
	@Test(groups = { "editingLocalCss_005", "editingLocalCss", "AdminDashboard" })
	public void editingLocalCss_005_AnonTriesToAccessWikiaCssUsingParameter() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		wiki.openArticle(this.mediaWikiCss);
		wiki.appendToUrl(this.actionEditParameter );
		wiki.verifyPermissionsErrorsPresent();
	}
	
	/**
	 *	https://wikia-inc.atlassian.net/browse/DAR-298
	 */
	@Test(groups = { "editingLocalCss_006", "editingLocalCss", "AdminDashboard" })
	public void editingLocalCss_006_UserWithAdminRightsTriesToEditWikiaCssUsingParameter() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		wiki.openArticle(this.mediaWikiCss);
		wiki.appendToUrl(this.actionEditParameter);
		wiki.waitForStringInURL(this.specialCSS);
	}
	
	/**
	 *	https://wikia-inc.atlassian.net/browse/DAR-299
	 */
	@Test(groups = { "editingLocalCss_007", "editingLocalCss", "AdminDashboard" })
	public void editingLocalCss_007_UserWithAdminRightsTriesToEditWikiaCssUsingParameter() {
		CommonFunctions.logOut(driver);
        WikiBaseMonoBookPageObject monobookWiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        monobookWiki.openWikiWithMonobook();
        CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
        WikiArticleMonoBookPageObject monobookArticle = monobookWiki.openArticle(this.mediaWikiCss);
        monobookArticle.clickEdit();
        monobookArticle.verifyEditionArea();
	}
}
