package com.wikia.webdriver.testcases.desktop.categoriestests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.components.categories.CategoryLayoutSelector;
import com.wikia.webdriver.elements.communities.desktop.components.categories.PaginationControls;
import com.wikia.webdriver.elements.communities.desktop.pages.categories.DynamicCategoryPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

@Test(groups = {"DynamicCategoryTests"}, invocationCount = 5)
@Execute(onWikia = "category")
public class DynamicCategoryTests extends NewTestTemplate {


  public void dynamicCategoryAnonNotAbleToChangeLayout() {
    new ArticlePageObject().open("Category:Wikia_tests");

    DynamicCategoryPage categoryPage = new DynamicCategoryPage();
    CategoryLayoutSelector layoutSelector = categoryPage.getLayoutSelector();
    Assertion.assertFalse(layoutSelector.isVisible(), "Layout selectors are displayed for anon");
  }

  @Execute(asUser = User.USER)
  public void categoryExhibitionSetInPreferencesAsDefaultView() {
    new ArticlePageObject().open("Category:Wikia_tests");

    DynamicCategoryPage categoryPage = new DynamicCategoryPage();
    CategoryLayoutSelector layoutSelector = categoryPage.getLayoutSelector();
    Assertion.assertTrue(layoutSelector.isCategoryExhibitionActive());

  }

  @Execute(asUser = User.USER)
  public void dynamicCategoryLoggedInUserCanChangeLayout() {
    new ArticlePageObject().open("Category:Wikia_tests");

    DynamicCategoryPage categoryPage = new DynamicCategoryPage();
    CategoryLayoutSelector layoutSelector = categoryPage.getLayoutSelector();
    Assertion.assertTrue(layoutSelector.isVisible(), "Layout selectors are displayed for anon");
    String pageUrl = categoryPage.getCurrentUrl();
    layoutSelector.switchToDynamicCategoriesView();
    Assertion.assertStringContains(categoryPage.getCurrentUrl(), pageUrl);
  }

  @Execute(asUser = User.USER_2)
  public void pagination200elementsNoNextButton() {
    new ArticlePageObject().open("Category:200elements");

    DynamicCategoryPage categoryPage = new DynamicCategoryPage();
    CategoryLayoutSelector layoutSelector = categoryPage.getLayoutSelector();
    Assertion.assertTrue(layoutSelector.isDynamicViewActive());
    PaginationControls paginationControls = categoryPage.getPaginationControls();
    Assertion.assertFalse(paginationControls.isNextButtonVisible());
  }

  @Execute(asUser = User.USER_2)
  public void pagination201elementsNextButtonVisible() {
    new ArticlePageObject().open("Category:201elements");

    DynamicCategoryPage categoryPage = new DynamicCategoryPage();
    CategoryLayoutSelector layoutSelector = categoryPage.getLayoutSelector();
    Assertion.assertEquals(layoutSelector.isDynamicViewActive(), true, "Dynamic view is not active");
    PaginationControls paginationControls = categoryPage.getPaginationControls();
    paginationControls.clickNextButton();
    Assertion.assertEquals(categoryPage.getMembers().size(), 1, "Invalid number of members on category page");
  }

  @Execute(asUser = User.USER_2)
  public void pagination401elementsNextButtonVisible() {
    new ArticlePageObject().open("Category:401elements");

    DynamicCategoryPage categoryPage = new DynamicCategoryPage();
    CategoryLayoutSelector layoutSelector = categoryPage.getLayoutSelector();
    Assertion.assertEquals(layoutSelector.isDynamicViewActive(), true, "Dynamic view is not active");
    PaginationControls paginationControls = categoryPage.getPaginationControls();
    Assertion.assertTrue(paginationControls.isNextButtonVisible(), "Next button is not visible");
    Assertion.assertTrue(paginationControls.isLastButtonVisible(), "Last button is not visible");
    paginationControls.clickLastButton();

    List<WebElement> memberList = categoryPage.getMembers();

    Assertion.assertEquals(categoryPage.getMembers().size(), 1);
    Assertion.assertTrue(memberList.stream().anyMatch(e->e.getText().equals("Article99")));
    paginationControls.clickFirstButton();

    memberList = categoryPage.getMembers();
    Assertion.assertTrue(memberList.stream().anyMatch(e->e.getText().equals("Article1")));
  }

  @Execute(asUser = User.USER_2)
  public void overridingLayoutSetInPreferences() {
    new ArticlePageObject().open("Category:201elements");

    DynamicCategoryPage categoryPage = new DynamicCategoryPage();
    CategoryLayoutSelector layoutSelector = categoryPage.getLayoutSelector();
    Assertion.assertTrue(layoutSelector.isVisible(), "Layout selectors are displayed for anon");
    Assertion.assertTrue(layoutSelector.isDynamicViewActive());
    layoutSelector.switchToCategoryExhibitionView();
    categoryPage.refreshPage();
    Assertion.assertTrue(layoutSelector.isCategoryExhibitionActive());
  }
}
