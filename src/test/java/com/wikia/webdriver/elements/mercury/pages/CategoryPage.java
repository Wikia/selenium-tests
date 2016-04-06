package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends WikiBasePageObject {

  @FindBy(
      css = "article a[href=\"" + MercurySubpages.CATEGORY_WITH_ARTICLE_AND_WITHOUT_MEMBERS + "\"]"
  )
  private WebElement linkToCategoryWithArticleAndNoMembers;

  @FindBy(css = ".category-sections #C.category-section .category-navigation__button")
  private WebElement categoryNavigationButton;

  @FindBy(css = ".category-sections #C.category-section li")
  private WebElement sectionBatchFirstItem;

  private By article = By.cssSelector(".article-content");
  private By categorySections = By.cssSelector(".category-sections");
  private By noMembersMessage = By.cssSelector(".category-page-no-members");

  public CategoryPage() {
    super();
  }

  public CategoryPage navigateToPageWithArticleAndWithMembersFromUrl() {
    new Navigate(driver).toPage(MercurySubpages.CATEGORY_WITH_ARTICLE_AND_WITH_MEMBERS);

    articleContainerIsVisible();
    categorySectionsContainerIsVisible();

    return this;
  }

  public CategoryPage navigateToPageWithArticleAndWithoutMembersFromUrl() {
    new Navigate(driver).toPage(MercurySubpages.CATEGORY_WITH_ARTICLE_AND_WITHOUT_MEMBERS);

    articleContainerIsVisible();
    noMembersMessageIsVisible();

    return this;
  }

  public CategoryPage navigateToPageWithoutArticleAndWithMembersFromUrl() {
    new Navigate(driver).toPage(MercurySubpages.CATEGORY_WITHOUT_ARTICLE_AND_WITH_MEMBERS);

    articleContainerIsNotPresent();
    categorySectionsContainerIsVisible();

    return this;
  }

  public CategoryPage navigateToPageWithArticleAndNoMembersFromLinkInArticle() {
    new Navigate(driver)
        .toPage(MercurySubpages.ARTICLE_WITH_LINK_CATEGORY_WITH_ARTICLE_AND_WITHOUT_MEMBERS);

    wait.forElementClickable(linkToCategoryWithArticleAndNoMembers);
    linkToCategoryWithArticleAndNoMembers.click();

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
    wait.forElementNotPresent(noMembersMessage);
    PageObjectLogging.logInfo("Info message about no pages in category is visible.");

    return this;
  }

  public CategoryPage clickLoadMoreButton() {
    WebElement originalSectionBatchFirstItem = sectionBatchFirstItem;
    String originalSectionBatchFirstItemText = originalSectionBatchFirstItem.getText();

    wait.forElementClickable(categoryNavigationButton);
    categoryNavigationButton.click();

    wait.forTextNotInElement(
        originalSectionBatchFirstItem,
        originalSectionBatchFirstItemText
    );

    WebElement newSectionBatchFirstItem = sectionBatchFirstItem;
    String newSectionBatchFirstItemText = newSectionBatchFirstItem.getText();

    Assertion.assertNotEquals(originalSectionBatchFirstItemText, newSectionBatchFirstItemText,
                              "Newly loaded section is actually the same as the original.");
    PageObjectLogging.logInfo("New section was loaded correctly.");

    return this;
  }

  public CategoryPage clickLoadPreviousButton() {
    return this;
  }
}
