package com.wikia.webdriver.testcases.admindashboardtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialAdminDashboardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipagemonobook.WikiArticleMonoBookPageObject;


/**
 * tests are prepared to test the following feature: https://wikia-inc.atlassian.net/browse/DAR-136
 *
 * @author wikia
 */
public class EditingLocalCssTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-293 https://wikia-inc.atlassian.net/browse/DAR-298
   */
  @Test(groups = {"EditingLocalCss_001", "EditingLocalCss", "AdminDashboard"})
  public void EditingLocalCss_001_UserWithAdminRightsTriesToEditWikiaCss() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    ArticlePageObject article = new ArticlePageObject(driver).open(URLsContent.MEDIAWIKI_CSS);
    VisualEditModePageObject edit = article.goToCurrentArticleEditPage();
    edit.verifyUrl(URLsContent.SPECIAL_CSS);
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-294 https://wikia-inc.atlassian.net/browse/DAR-295
   * https://wikia-inc.atlassian.net/browse/DAR-296 https://wikia-inc.atlassian.net/browse/DAR-297
   */
  @Test(groups = {"EditingLocalCss_002", "EditingLocalCss", "AdminDashboard"})
  public void EditingLocalCss_002_UserWithoutAdminRightsHasNoEditOption() {
    ArticlePageObject article = new ArticlePageObject(driver).open(URLsContent.MEDIAWIKI_CSS);
    article.verifyEditButtonNotPresent();
    article.goToCurrentArticleEditPage();
    article.verifyPermissionsErrorsPresent();
    article.loginAs(credentials.userName, credentials.password, wikiURL);
    article.open(URLsContent.MEDIAWIKI_CSS);
    article.verifyEditButtonNotPresent();
    article.goToCurrentArticleEditPage();
    article.verifyPermissionsErrorsPresent();
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-299
   */
  @Test(groups = {"EditingLocalCss_003", "EditingLocalCss", "AdminDashboard"})
  public void EditingLocalCss_003_MonobookUserWithAdminRightsEditsWikiaCss() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameMonobook, credentials.passwordMonobook, wikiURL);
    WikiArticleMonoBookPageObject monobookArticle = new WikiArticleMonoBookPageObject(driver);
    monobookArticle.open(URLsContent.MEDIAWIKI_CSS);
    monobookArticle.clickEdit();
    monobookArticle.verifyEditionArea();
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-300
   */
  @Test(groups = {"EditingLocalCss_004", "EditingLocalCss", "AdminDashboard"})
  public void EditingLocalCss_004_MonobookUserWithAdminRightsOpensSpecialCss() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameMonobook, credentials.passwordMonobook, wikiURL);
    WikiArticleMonoBookPageObject monobookArticle = new WikiArticleMonoBookPageObject(driver);
    monobookArticle.openSpecialCss(wikiURL);
    monobookArticle.verifyOasisOnly();
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-302
   */
  @Test(groups = {"EditingLocalCss_005", "EditingLocalCss", "AdminDashboard"})
  public void EditingLocalCss_005_UserWithAdminRightsTriesToAccesSpecialCssFromAdminDashboard() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    SpecialAdminDashboardPageObject adminDashboard = base.openSpecialAdminDashboard(wikiURL);
    adminDashboard.clickCssTool();
    adminDashboard.verifyUrl(URLsContent.SPECIAL_CSS);
  }
}
