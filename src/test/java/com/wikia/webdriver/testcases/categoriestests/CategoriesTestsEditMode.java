/**
 *
 */
package com.wikia.webdriver.testcases.categoriestests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.testng.annotations.Test;

public class CategoriesTestsEditMode extends NewTestTemplate {

  @Test(groups = {"CategoriesTestsArticleEdit_001", "CategoriesTestsArticleEditMode"})
  public void CategoriesTestsArticleEdit_001_anonEdit() {
    WikiBasePageObject base = new WikiBasePageObject();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject visual = base.navigateToArticleEditPage(wikiURL, articleName);
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
    visual.typeCategoryName(categoryName);
    visual.submitCategory();
    visual.verifyCategoryPresent(categoryName);
    EditCategoryComponentObject editCategory = visual.editCategory(categoryName);
    categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
    editCategory.editCategoryName(categoryName);
    visual.verifyCategoryPresent(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_002", "CategoriesTestsArticleEditMode"})
  public void CategoriesTestsArticleEdit_002_anonDelete() {
    new ArticleContent().clear();

    VisualEditModePageObject visual = new VisualEditModePageObject().open();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
    visual.typeCategoryName(categoryName);
    visual.submitCategory();
    visual.verifyCategoryPresent(categoryName);
    visual.removeCategory(categoryName);
    visual.verifyCategoryNotPresent(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_003", "CategoriesTestsArticleEditMode"})
  public void CategoriesTestsArticleEdit_003_anonSuggestions() {
    WikiBasePageObject base = new WikiBasePageObject();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject visual = base.navigateToArticleEditPage(wikiURL, articleName);
    visual.typeCategoryName(PageContent.CATEGORY_NAME_PREFIX);
    visual.triggerCategorySuggestions();
    String categoryName = visual.selectCategorySuggestions(0);
    visual.verifyCategoryPresent(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_004", "CategoriesTestsArticleEditMode"})
  @Execute(asUser = User.USER)
  public void CategoriesTestsArticleEdit_004_user() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    VisualEditModePageObject visual = new VisualEditModePageObject().open();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + visual.getTimeStamp();
    visual.typeCategoryName(categoryName);
    visual.submitCategory();
    visual.verifyCategoryPresent(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_005", "CategoriesTestsArticleEditMode"})
  @Execute(asUser = User.USER)
  public void CategoriesTestsArticleEdit_005_userSuggestions() {
    new ArticleContent().push();

    VisualEditModePageObject visual = new VisualEditModePageObject().open();
    visual.typeCategoryName(PageContent.CATEGORY_NAME_PREFIX);
    visual.triggerCategorySuggestions();
    String categoryName = visual.selectCategorySuggestions(0);
    visual.verifyCategoryPresent(categoryName);
  }
}
