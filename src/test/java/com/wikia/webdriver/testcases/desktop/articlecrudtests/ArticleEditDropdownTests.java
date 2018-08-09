package com.wikia.webdriver.testcases.desktop.articlecrudtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.classifiers.*;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {
    Team.SUS,
    Feature.EDIT_DROPDOWN,
    View.DESKTOP
})
public class ArticleEditDropdownTests extends NewTestTemplate {

  private static final String articleName = "DropdownStatus";

  @Test(groups = {EndUser.STAFF})
  @Execute(asUser = User.STAFF)
  public void ArticleEditDropdown_001_admin() {
    new ArticleContent(User.USER_6).push("Dummy", articleName);

    ArticlePageObject article = new ArticlePageObject().open(articleName);
    article.verifyDropdownForAdmin();
  }

  @Test(groups = {EndUser.LOGGED_IN})
  @Execute(asUser = User.USER)
  public void ArticleEditDropdown_002_user() {
    new ArticleContent(User.USER_6).push("Dummy", articleName);

    ArticlePageObject article = new ArticlePageObject().open(articleName);
    article.verifyDropdownForUser();
  }

  @Test(groups = {EndUser.LOGGED_IN})
  public void ArticleEditDropdown_003_anon() {
    new ArticleContent(User.USER_6).push("Dummy", articleName);

    ArticlePageObject article = new ArticlePageObject().open(articleName);
    article.verifyDropdownForAnon();
  }
}
