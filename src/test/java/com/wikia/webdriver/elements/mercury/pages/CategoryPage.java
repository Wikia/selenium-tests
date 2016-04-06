package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoryPage extends WikiBasePageObject {

  private By article = By.cssSelector(".article-content");
  private By categorySections = By.cssSelector(".category-sections");
  private By noMembersMessage = By.cssSelector(".category-page-no-members");

  private Navigate navigate;

  public CategoryPage() {
    super();

    navigate = new Navigate(driver);
  }

  public CategoryPage navigateToPageWithArticleAndWithMembersFromUrl() {
    navigate.toPage(MercurySubpages.CATEGORY_WITH_ARTICLE_AND_WITH_MEMBERS);

    articleContainerIsVisible();
    categorySectionsContainerIsVisible();

    return this;
  }

  public CategoryPage navigateToPageWithArticleAndWithoutMembersFromUrl() {
    navigate.toPage(MercurySubpages.CATEGORY_WITH_ARTICLE_AND_WITHOUT_MEMBERS);

    articleContainerIsVisible();
    noMembersMessageIsVisible();

    return this;
  }

  public CategoryPage navigateToPageWithoutArticleAndWithMembersFromUrl() {
    navigate.toPage(MercurySubpages.CATEGORY_WITHOUT_ARTICLE_AND_WITH_MEMBERS);

    articleContainerIsNotPresent();
    categorySectionsContainerIsVisible();

    return this;
  }

  public CategoryPage articleContainerIsVisible() {
    wait.forElementVisible(article);
    PageObjectLogging.logInfo("Article container is visible.");

    return this;
  }

  public CategoryPage articleContainerIsNotPresent() {
    wait.forElementNotPresent(article);
    PageObjectLogging.logInfo("Article container is not present in DOM, which is nice.");

    return this;
  }

  public CategoryPage categorySectionsContainerIsVisible() {
    wait.forElementVisible(categorySections);
    PageObjectLogging.logInfo("Category sections container is visible.");

    return this;
  }

  public CategoryPage noMembersMessageIsVisible() {
    wait.forElementVisible(noMembersMessage);
    PageObjectLogging.logInfo("Info message about no pages in category is visible.");

    return this;
  }

  public CategoryPage loadMoreCategoriesForSection(String name) {
    WebElement section = driver.findElement(By.cssSelector(".category-section#" + name.toUpperCase()));
    wait.forElementVisible(section);
    PageObjectLogging.logInfo("Category section: " + name.toUpperCase() + ", is visible");

    WebElement loadMoreButton = section.findElement(By.cssSelector(".next__button"));
    wait.forElementClickable(loadMoreButton);
    loadMoreButton.click();

    return this;
  }
}
