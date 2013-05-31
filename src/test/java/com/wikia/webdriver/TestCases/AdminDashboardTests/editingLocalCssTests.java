package com.wikia.webdriver.TestCases.AdminDashboardTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.MessageWallPageObject;

/**
 * those tests are prepared to test the following feature: on https://wikia-inc.atlassian.net/browse/DAR-136
 * 
 * @author wikia
 *
 */
public class editingLocalCssTests extends TestTemplate{
	
	private String mediaWikiCss = "MediaWiki:Wikia.css";
	private String specialCSS = "Special:CSS";
	
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
		wiki.verifyURL(Global.DOMAIN+"wiki/"+this.specialCSS);
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
		wiki.appendToUrl("?action=edit");
		wiki.verifyPermissionsErrorsPresent();
	}
}
