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
  private By noMembersMessage = By.cssSelector("[data-category-page-no-members]");
  private By loadMoreButton = By.cssSelector(".category-navigation__button[data-next]");
  private By loadPreviousButton = By.cssSelector(".category-navigation__button[data-previous]");

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
    WebElement member = driver.findElement(By.xpath(
        String.format("//a[contains(text(), \"%s\")]", name))
    );
    wait.forElementClickable(member);
    member.click();

    new Loading(driver).handleAsyncPageReload();

    String expectedUrl = "/wiki/" + name.replaceAll(" ", "_");
    String currentUrl = driver.getCurrentUrl();
    Assertion.assertTrue(
        currentUrl.contains(expectedUrl),
        String.format("Expected part \"%s\" was not found in \"%s\".", expectedUrl, currentUrl)
    );
    PageObjectLogging.logInfo(String.format("You were redirected to page: \"%s\".", name));

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
        By.cssSelector(String.format("#%s.category-section", name))
    );
    wait.forElementVisible(section);
    PageObjectLogging.logInfo(String.format("Category section \"%s\" is visible.", name));

    WebElement sectionBatchFirstItem = section.findElement(By.cssSelector("li"));
    wait.forElementVisible(sectionBatchFirstItem);
    String firstItemTextInFirstBatch = sectionBatchFirstItem.getText();

    WebElement buttonElement = section.findElement(buttonSelector);
    wait.forElementClickable(buttonElement);
    buttonElement.click();
    waitForBatchToBeLoaded(section);

    sectionBatchFirstItem = section.findElement(By.cssSelector("li"));
    wait.forElementVisible(sectionBatchFirstItem);
    String firstItemTextInSecondBatch = sectionBatchFirstItem.getText();

    Assertion.assertNotEquals(
        firstItemTextInFirstBatch,
        firstItemTextInSecondBatch,
        "First element of the newly loaded section is the same as the previous one."
    );
    PageObjectLogging.logInfo("New section was loaded correctly.");

    return this;
  }

  private void waitForBatchToBeLoaded(WebElement section) {
    int attemptLimit = 5;

    PageObjectLogging.logInfo("Waiting for loading-batch to be present");

    for (int i = 1; i <= attemptLimit; ++i) {
      if (section.getAttribute("class").contains("loading-batch")) {
        PageObjectLogging.logInfo("loading-batch class was present in attempt: " + i + " of " + attemptLimit);

        break;
      }

      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        PageObjectLogging.logWarning("Couldn't sleep thread", e);
      }
    }

    PageObjectLogging.logInfo("Waiting for loading batch to be not present");

    for (int i = 1; i <= attemptLimit; ++i) {
      if (!section.getAttribute("class").contains("loading-batch")) {
        PageObjectLogging.logInfo("loading-batch class was not present in attempt: " + i + " of " + attemptLimit);

        break;
      }

      try {
        Thread.sleep(600);
      } catch (InterruptedException e) {
        PageObjectLogging.logWarning("Couldn't sleep thread", e);
      }
    }
  }
}
