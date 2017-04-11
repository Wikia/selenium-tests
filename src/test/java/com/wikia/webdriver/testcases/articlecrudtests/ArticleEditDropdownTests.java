package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"ArticleEditDropdown"})
public class ArticleEditDropdownTests extends NewTestTemplate {

  @Test(groups = {"ArticleEditDropdown_001"})
  @Execute(asUser = User.STAFF)
  public void ArticleEditDropdown_001_admin() {
    new ArticleContent().push(PageContent.LOREM_IPSUM_LONG);
    ArticlePageObject article = new ArticlePageObject().open();
    article.clickArticleEditDropdown();

    Assertion.assertTrue(article.isRenameButtonVisible());
    Assertion.assertTrue(article.isDeleteButtonVisible());
    Assertion.assertTrue(article.isHistoryButtonVisible());
    Assertion.assertTrue(article.isProtectButtonVisible());
    Assertion.assertTrue(article.isVEEditButtonVisible());
    Assertion.assertEquals(article.getEditDropdownElementsSize(), 5);
  }

  @Test(groups = {"ArticleEditDropdown_002"})
  @Execute(asUser = User.USER)
  public void ArticleEditDropdown_002_user() {
    new ArticleContent().push(PageContent.LOREM_IPSUM_LONG);
    ArticlePageObject article = new ArticlePageObject().open();
    article.clickArticleEditDropdown();

    Assertion.assertTrue(article.isRenameButtonVisible());
    Assertion.assertTrue(article.isHistoryButtonVisible());
    Assertion.assertTrue(article.isVEEditButtonVisible());
    Assertion.assertEquals(article.getEditDropdownElementsSize(), 3);
  }

  @Test(groups = {"ArticleEditDropdown_003"})
  public void ArticleEditDropdown_003_anon() {
    new ArticleContent().push(PageContent.LOREM_IPSUM_LONG);
    ArticlePageObject article = new ArticlePageObject().open();
    article.clickArticleEditDropdown();

    Assertion.assertTrue(article.isHistoryButtonVisible());
    Assertion.assertTrue(article.isVEEditButtonVisible());
    Assertion.assertEquals(article.getEditDropdownElementsSize(), 2);
  }
}
