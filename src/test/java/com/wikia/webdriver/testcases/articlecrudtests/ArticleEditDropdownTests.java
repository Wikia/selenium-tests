package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"ArticleEditDropdown"})
public class ArticleEditDropdownTests extends NewTestTemplate {

  private static final String articleName = "DropdownStatus";

  @Test(groups = {"ArticleEditDropdown_001"})
  @Execute(asUser = User.STAFF)
  public void ArticleEditDropdown_001_admin() {
    ArticlePageObject article = new ArticlePageObject(driver).open(articleName);
    article.verifyDropdownForAdmin();
  }

  @Test(groups = {"ArticleEditDropdown_002"})
  @Execute(asUser = User.USER)
  public void ArticleEditDropdown_002_user() {
    ArticlePageObject article = new ArticlePageObject(driver).open(articleName);
    article.verifyDropdownForUser();
  }

  @Test(groups = {"ArticleEditDropdown_003"})
  public void ArticleEditDropdown_003_anon() {
    ArticlePageObject article = new ArticlePageObject(driver).open(articleName);
    article.verifyDropdownForAnon();
  }
}
