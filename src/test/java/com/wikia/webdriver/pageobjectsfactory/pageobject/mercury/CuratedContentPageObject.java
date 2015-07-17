package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership: Content X-Wing
 */
public class CuratedContentPageObject extends BasePageObject {

  @FindBy(css = ".article-title")
  private WebElement sectionTitle;
  @FindBy(css = ".wiki-title a")
  private WebElement linkToMainPage;
  @FindBy(css = ".curated-content-items")
  private WebElement sectionContainer;
  @FindBy(css = "div.curated-content a")
  private List<WebElement> curatedContentItems;
  @FindBy(css = ".load-more-items")
  private WebElement loadMoreButton;

  private enum Labels {
    SECTION_TITLE("Section title"),
    LINK_TO_MAIN_PAGE("Link to main page"),
    SECTION("Section as the container of many elements"),
    SECTION_ITEM("Item in a section"),
    LOAD_MORE_BUTTON("Load more button"),
    NUMBER_OF_ITEMS("Number of items in curated content section");

    private String name;

    Labels(String name) {
      this.name = name;
    }
  }

  public CuratedContentPageObject(WebDriver driver) {
    super(driver);
  }

  public String getTitle() {
    waitForElementByElement(sectionTitle);
    return sectionTitle.getText();
  }

  public int getCuratedContentItemsNumber() {
    waitForElementByElement(curatedContentItems.get(0));
    return curatedContentItems.size();
  }

  public CuratedContentPageObject clickOnCuratedContentElementByIndex(int elementNumber) {
    waitForElementByElement(curatedContentItems.get(elementNumber));
    scrollToElement(curatedContentItems.get(elementNumber));
    curatedContentItems.get(elementNumber).click();
    return this;
  }

  public CuratedContentPageObject clickOnMainPageLink() {
    waitForElementByElement(linkToMainPage);
    scrollToElement(linkToMainPage);
    linkToMainPage.click();
    return this;
  }

  public CuratedContentPageObject clickOnLoadMoreButton() {
    waitForElementByElement(loadMoreButton);
    scrollToElement(loadMoreButton);
    loadMoreButton.click();
    return this;
  }

  public CuratedContentPageObject navigateToMainPage() {
    clickOnMainPageLink();
    waitForLoadingSpinnerToFinishReloadingPage();
    return this;
  }

  public CuratedContentPageObject isTitleVisible() {
    PageObjectLogging.log(
        Labels.SECTION_TITLE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(sectionTitle)
    );
    return this;
  }

  public CuratedContentPageObject isLinkToMainPageVisible() {
    PageObjectLogging.log(
        Labels.LINK_TO_MAIN_PAGE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(linkToMainPage)
    );
    return this;
  }

  public CuratedContentPageObject isSectionVisible() {
    PageObjectLogging.log(
        Labels.SECTION.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(sectionContainer)
    );
    return this;
  }

  public CuratedContentPageObject isCuratedContentItemVisibleByIndex(int elementNumber) {
    PageObjectLogging.log(
        Labels.SECTION_ITEM.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(curatedContentItems.get(elementNumber))
    );
    return this;
  }

  public CuratedContentPageObject isLoadMoreButtonVisible() {
    PageObjectLogging.log(
        Labels.LOAD_MORE_BUTTON.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(loadMoreButton)
    );
    return this;
  }

  public CuratedContentPageObject isLoadMoreButtonHidden() {
    PageObjectLogging.log(
        Labels.LOAD_MORE_BUTTON.name,
        MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG,
        !isElementVisible(loadMoreButton)
    );
    return this;
  }

  public CuratedContentPageObject isCurrentNumberOfItemsExpected(int expectedNumber) {
    int currentNumber = getCuratedContentItemsNumber();
    String message = "Expected: " + expectedNumber + ", get: " + currentNumber;

    PageObjectLogging.log(
        Labels.NUMBER_OF_ITEMS.name,
        message,
        expectedNumber == currentNumber
    );
    return this;
  }
}
