package com.wikia.webdriver.testcases.articlecrudtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

@Test(groups = {"ArticleEditDropdown"})
public class ArticleEditDropdownTests extends NewTestTemplate {

  private static final String articleName = "DropdownStatus";

  @Test(groups = {"ArticleEditDropdown_001"})
  @Execute(asUser = User.STAFF)
  public void ArticleEditDropdown_001_admin() {
    new ArticleContent(User.USER_6).push("Dummy", articleName);

    ArticlePageObject article = new ArticlePageObject().open(articleName);
    article.verifyDropdownForAdmin();
  }

  @Test(groups = {"ArticleEditDropdown_002"})
  @Execute(asUser = User.USER)
  public void ArticleEditDropdown_002_user() {
    new ArticleContent(User.USER_6).push("Dummy", articleName);

    ArticlePageObject article = new ArticlePageObject().open(articleName);
    article.verifyDropdownForUser();
  }

  @Test(groups = {"ArticleEditDropdown_003"})
  public void ArticleEditDropdown_003_anon() {
    new ArticleContent(User.USER_6).push("Dummy", articleName);

    ArticlePageObject article = new ArticlePageObject().open(articleName);
    article.verifyDropdownForAnon();
  }
}
