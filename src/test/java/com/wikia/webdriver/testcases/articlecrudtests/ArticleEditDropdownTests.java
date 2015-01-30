package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class ArticleEditDropdownTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(
      groups = {"ArticleEditDropdown_001", "ArticleEditDropdown"}
  )
  public void ArticleEditDropdown_001_admin() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    article.verifyDropdownForAdmin();
  }

  @Test(
      groups = {"ArticleEditDropdown_002", "ArticleEditDropdown"}
  )
  public void ArticleEditDropdown_002_user() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    article.verifyDropdownForUser();
  }

  @Test(
      groups = {"ArticleEditDropdown_003", "ArticleEditDropdown"}
  )
  public void ArticleEditDropdown_003_anon() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    ArticlePageObject article = base.openRandomArticle(wikiURL);
    article.verifyDropdownForAnon();
  }
}
