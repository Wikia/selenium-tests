/**
 *
 */
package com.wikia.webdriver.testcases.categoriestests;

import static org.assertj.core.api.Assertions.assertThat;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.rte.CategoryModule;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;

import org.testng.annotations.Test;

public class CategoriesTestsEditMode extends NewTestTemplate {

  @Test(groups = {"CategoriesTestsArticleEdit_001", "CategoriesTestsArticleEditMode"})
  public void CategoriesTestsArticleEdit_001_anonEdit() {
    WikiBasePageObject base = new WikiBasePageObject();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + BasePageObject.getTimeStamp();
    VisualEditModePageObject visual = base.navigateToArticleEditPage(wikiURL, articleName);
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + BasePageObject.getTimeStamp();

    CategoryModule categoryModule = visual.getCategoryModule();

    categoryModule.typeCategoryName(categoryName);
    categoryModule.saveCategory();

    assertThat(categoryModule.getCategoryList()).contains(categoryName);

    EditCategoryComponentObject editCategory = categoryModule.editExistingCategory(categoryName);
    categoryName = PageContent.CATEGORY_NAME_PREFIX + BasePageObject.getTimeStamp();
    editCategory.editCategoryName(categoryName);

    assertThat(categoryModule.getCategoryList()).doesNotContain(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_002", "CategoriesTestsArticleEditMode"})
  public void CategoriesTestsArticleEdit_002_anonDelete() {
    new ArticleContent().clear();

    VisualEditModePageObject visual = new VisualEditModePageObject().open();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + BasePageObject.getTimeStamp();

    CategoryModule categoryModule = visual.getCategoryModule();

    categoryModule.typeCategoryName(categoryName);
    categoryModule.saveCategory();

    assertThat(categoryModule.getCategoryList()).contains(categoryName);

    categoryModule.removeExistingCategory(categoryName);

    assertThat(categoryModule.getCategoryList()).doesNotContain(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_003", "CategoriesTestsArticleEditMode"})
  public void CategoriesTestsArticleEdit_003_anonSuggestions() {
    WikiBasePageObject base = new WikiBasePageObject();
    String articleName = PageContent.ARTICLE_NAME_PREFIX + BasePageObject.getTimeStamp();
    VisualEditModePageObject visual = base.navigateToArticleEditPage(wikiURL, articleName);

    CategoryModule categoryModule = visual.getCategoryModule();

    categoryModule.typeCategoryName(PageContent.CATEGORY_NAME_PREFIX);
    String categoryName = categoryModule.selectFirstSuggestedCategory();

    assertThat(categoryModule.getCategoryList()).contains(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_004", "CategoriesTestsArticleEditMode"})
  @Execute(asUser = User.USER)
  public void CategoriesTestsArticleEdit_004_user() {
    new ArticleContent().push(PageContent.ARTICLE_TEXT);

    VisualEditModePageObject visual = new VisualEditModePageObject().open();
    String categoryName = PageContent.CATEGORY_NAME_PREFIX + BasePageObject.getTimeStamp();
    CategoryModule categoryModule = visual.getCategoryModule();

    categoryModule.typeCategoryName(categoryName);
    categoryModule.saveCategory();

    assertThat(categoryModule.getCategoryList()).contains(categoryName);

    categoryModule.removeExistingCategory(categoryName);

    assertThat(categoryModule.getCategoryList()).doesNotContain(categoryName);
  }

  @Test(groups = {"CategoriesTestsArticleEdit_005", "CategoriesTestsArticleEditMode"})
  @Execute(asUser = User.USER)
  public void CategoriesTestsArticleEdit_005_userSuggestions() {
    new ArticleContent().push();

    VisualEditModePageObject visual = new VisualEditModePageObject().open();
    CategoryModule categoryModule = visual.getCategoryModule();

    categoryModule.typeCategoryName(PageContent.CATEGORY_NAME_PREFIX);
    String categoryName = categoryModule.selectFirstSuggestedCategory();

    assertThat(categoryModule.getCategoryList()).contains(categoryName);
  }
}
