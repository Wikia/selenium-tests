package com.wikia.webdriver.testcases.categoriestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

public class CategoriesArticleTests extends NewTestTemplate {

  @Test(groups = {"CategoriesTestsArticle_001", "CategoriesTestsArticle", "Smoke2"})
  public void CategoriesTestsArticle_001_anon() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
    article.addCategory(categoryName);
    article.submitCategory();
    article.verifyCategoryPresent(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticle_002", "CategoriesTestsArticle"})
  @RelatedIssue(issueID = "WW-514")
  public void CategoriesTestsArticle_002_anonSuggestions() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String desiredCategory = article.addCategorySuggestions(PageContent.CATEGORY_NAME_PREFIX, 2);
    article.submitCategory();
    article.verifyCategoryPresent(desiredCategory);
  }

  @Test(groups = {"CategoriesTestsArticle_003", "CategoriesTestsArticle"})
  @Execute(asUser = User.USER)
  public void CategoriesTestsArticle_003_user() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + article.getTimeStamp();
    article.addCategory(categoryName);
    article.submitCategory();
    article.verifyCategoryPresent(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticle_004", "CategoriesTestsArticle"})
  @RelatedIssue(issueID = "WW-514")
  @Execute(asUser = User.USER)
  public void CategoriesTestsArticle_004_userSuggestions() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String desiredCategory = article.addCategorySuggestions(PageContent.CATEGORY_NAME_PREFIX, 2);
    article.submitCategory();
    article.verifyCategoryPresent(desiredCategory);
  }

  @Test(groups = {"CategoriesTestsArticle_005", "CategoriesTestsArticle"})
  public void CategoriesTestsArticle_005_anonEdit() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + DateTime.now().getMillis();
    article.addCategory(categoryName);
    EditCategoryComponentObject editCategory = article.editCategory(categoryName);
    categoryName = PageContent.CATEGORY_NAME_PREFIX + DateTime.now().getMillis();
    editCategory.editCategoryName(categoryName);
    article.submitCategory();
    article.verifyCategoryPresent(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticle_006", "CategoriesTestsArticle"})
  public void CategoriesTestsArticle_006_anonDelete() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    ArticlePageObject article = new ArticlePageObject().open();
    article.addCategory("DeleteMe");
    article.verifySubmitCategoryEnabled();
    article.removeCategory("DeleteMe");
    article.verifySubmitCategoryDisabled();
  }
}
