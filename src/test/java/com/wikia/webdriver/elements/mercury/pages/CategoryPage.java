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

  @FindBy(css = ".category-sections #C.category-section li")
  private WebElement sectionBatchFirstItem;

  private By article = By.cssSelector(".article-content");
  private By categorySections = By.cssSelector(".category-sections");
  private By noMembersMessage = By.cssSelector(".category-page-no-members");
  private By loadMoreButton = By.cssSelector(".next-button");
  private By loadPreviousButton = By.cssSelector(".previous-button");

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

  public CategoryPage loadMoreMembersForSection(String name) {
    navigateThroughSection(name, loadMoreButton);

    return this;
  }

  public CategoryPage loadPreviousMembersForSection(String name) {
    navigateThroughSection(name, loadPreviousButton);

    return this;
  }

  private CategoryPage articleContainerIsVisible() {
    wait.forElementVisible(article);
    PageObjectLogging.logInfo("Article container is visible.");

    return this;
  }

  private CategoryPage articleContainerIsNotPresent() {
    wait.forElementNotPresent(article);
    PageObjectLogging.logInfo("Article container is not present in DOM, which is nice.");

    return this;
  }

  private CategoryPage categorySectionsContainerIsVisible() {
    wait.forElementVisible(categorySections);
    PageObjectLogging.logInfo("Category sections container is visible.");

    return this;
  }

  private CategoryPage noMembersMessageIsVisible() {
    wait.forElementVisible(noMembersMessage);
    PageObjectLogging.logInfo("Info message about no pages in category is visible.");

    return this;
  }

  private CategoryPage navigateThroughSection(String name, By buttonSelector) {
    WebElement section = driver.findElement(
        By.cssSelector("#" + name.toUpperCase() + ".category-section")
    );
    wait.forElementVisible(section);
    PageObjectLogging.logInfo("Category section \"" + name.toUpperCase() + "\" is visible.");

    wait.forElementVisible(sectionBatchFirstItem);
    String firstItemTextInFirstBatch = sectionBatchFirstItem.getText();

    WebElement buttonElement = section.findElement(buttonSelector);
    wait.forElementClickable(buttonElement);
    buttonElement.click();

    wait.forTextNotInElement(
        sectionBatchFirstItem,
        firstItemTextInFirstBatch
    );
    String firstItemTextInSecondBatch = sectionBatchFirstItem.getText();

    Assertion.assertNotEquals(
        firstItemTextInFirstBatch,
        firstItemTextInSecondBatch,
        "First element of the newly loaded section is the same as the previous one."
    );
    PageObjectLogging.logInfo("New section was loaded correctly.");

    return this;
  }
}
