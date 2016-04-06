package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Loading;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoryPage extends WikiBasePageObject {

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

  public CategoryPage navigateToCategoryMemberPage(String name) {
    WebElement member = driver.findElement(By.xpath("//a[contains(text(), \"" + name + "\")]"));
    wait.forElementClickable(member);
    member.click();

    System.out.println(member);

    new Loading(driver).handleAsyncPageReload();

    String expectedUrl = "/wiki/" + name.replaceAll(" ", "_");
    String currentUrl = driver.getCurrentUrl();
    Assertion.assertTrue(
        currentUrl.contains(expectedUrl),
        "Expected part \"" + expectedUrl + "\" was not found in \"" + currentUrl + "\"."
    );
    PageObjectLogging.logInfo("You were redirected to page: " + name);

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

    WebElement sectionBatchFirstItem = section.findElement(By.cssSelector("li"));
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
