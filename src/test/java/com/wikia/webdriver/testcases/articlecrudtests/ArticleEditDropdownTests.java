package com.wikia.webdriver.testcases.articlecrudtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/**
 * @author: Bogna 'bognix' Knychała
 */
@Test(groups = {"ArticleEditDropdown"})
public class ArticleEditDropdownTests extends NewTestTemplate {

  @Test(groups = {"ArticleEditDropdown_001"})
  @ExecuteAs(user = User.STAFF)
  public void ArticleEditDropdown_001_admin() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    article.verifyDropdownForAdmin();
  }

  @Test(groups = {"ArticleEditDropdown_002"})
  @ExecuteAs(user = User.USER)
  public void ArticleEditDropdown_002_user() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    article.verifyDropdownForUser();
  }

  @Test(groups = {"ArticleEditDropdown_003"})
  public void ArticleEditDropdown_003_anon() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
    article.verifyDropdownForAnon();
  }
}
