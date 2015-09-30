package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

/**
 * This class represents all the levels below Curated Main Page
 *
 * @ownership: Content X-Wing
 */
public class CuratedContentPageObject extends BasePageObject {

  @FindBy(css = ".article-wrapper")
  private WebElement articleWrapper;
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
  @FindBy(css = "#namespace-article")
  private WebElement articleItemIcon;
  @FindBy(css = "#namespace-blog")
  private WebElement blogItemIcon;
  @FindBy(css = "#namespace-image")
  private WebElement imageItemIcon;
  @FindBy(css = "#namespace-video")
  private WebElement videoItemIcon;
  @FindBy(css = ".alert-notification")
  private WebElement alertNotification;

  public CuratedContentPageObject(WebDriver driver) {
    super(driver);
  }

  public String getTitle() {
    wait.forElementVisible(sectionTitle);
    return sectionTitle.getText();
  }

  public int getCuratedContentItemsNumber() {
    wait.forElementVisible(curatedContentItems.get(0));
    return curatedContentItems.size();
  }

  public CuratedContentPageObject clickOnCuratedContentElementByIndex(int elementNumber) {
    wait.forElementVisible(curatedContentItems.get(elementNumber));
    scrollToElement(curatedContentItems.get(elementNumber));
    curatedContentItems.get(elementNumber).click();
    return this;
  }

  public CuratedContentPageObject clickOnMainPageLink() {
    wait.forElementVisible(linkToMainPage);
    scrollToElement(linkToMainPage);
    linkToMainPage.click();
    return this;
  }

  public CuratedContentPageObject clickOnLoadMoreButton() {
    wait.forElementVisible(loadMoreButton);
    scrollToElement(loadMoreButton);
    loadMoreButton.click();
    return this;
  }

  public CuratedContentPageObject navigateToMainPage() {
    clickOnMainPageLink();
    waitForLoadingSpinnerToFinish();
    return this;
  }

  public CuratedContentPageObject isAlertNotificationVisible() {
    LOG.log(Labels.ALERT_NOTIFICATION.name, MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, isElementVisible(alertNotification));
    return this;
  }

  public CuratedContentPageObject isArticleIconVisible() {
    isElementVisible(articleItemIcon);
    return this;
  }

  public CuratedContentPageObject isBlogIconVisible() {
    isElementVisible(blogItemIcon);
    return this;
  }

  public CuratedContentPageObject isImageIconVisible() {
    isElementVisible(imageItemIcon);
    return this;
  }

  public CuratedContentPageObject isVideoIconVisible() {
    isElementVisible(videoItemIcon);
    return this;
  }

  public CuratedContentPageObject isTitleVisible() {
    LOG.log(Labels.SECTION_TITLE.name, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        isElementVisible(sectionTitle));
    return this;
  }

  public CuratedContentPageObject isLinkToMainPageVisible() {
    LOG.log(Labels.LINK_TO_MAIN_PAGE.name, MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, isElementVisible(linkToMainPage));
    return this;
  }

  public CuratedContentPageObject isSectionVisible() {
    LOG.log(Labels.SECTION.name, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        isElementVisible(sectionContainer));
    return this;
  }

  public CuratedContentPageObject isCuratedContentItemVisibleByIndex(int elementNumber) {
    LOG.log(Labels.SECTION_ITEM.name, MercuryMessages.VISIBLE_MSG, MercuryMessages.INVISIBLE_MSG,
        isElementVisible(curatedContentItems.get(elementNumber)));
    return this;
  }

  public CuratedContentPageObject isLoadMoreButtonVisible() {
    LOG.log(Labels.LOAD_MORE_BUTTON.name, MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG, isElementVisible(loadMoreButton));
    return this;
  }

  public CuratedContentPageObject isLoadMoreButtonHidden() {
    LOG.log(Labels.LOAD_MORE_BUTTON.name, MercuryMessages.INVISIBLE_MSG,
        MercuryMessages.VISIBLE_MSG, !isElementVisible(loadMoreButton));
    return this;
  }

  public CuratedContentPageObject isCurrentNumberOfItemsExpected(int expectedNumber) {
    int currentNumber = getCuratedContentItemsNumber();
    String message = "Expected: " + expectedNumber + ", get: " + currentNumber;

    LOG.result(Labels.NUMBER_OF_ITEMS.name, message, expectedNumber == currentNumber);
    return this;
  }

  public CuratedContentPageObject areItemsInCuratedContentUnique() {
    List<String> itemsLabels = new ArrayList<>();
    boolean conflict = false;

    for (WebElement element : curatedContentItems) {
      element = element.findElement(By.cssSelector("div.item-caption"));
      String label = element.getText();

      if (itemsLabels.contains(label)) {
        conflict = true;
        break;
      }

      itemsLabels.add(label);
    }

    LOG.log(Labels.ITEM_LABELS.name, MercuryMessages.LIST_ITEMS_ARE_UNIQUE_MSG,
        MercuryMessages.LIST_ITEMS_ARE_NOT_UNIQUE_MSG, !conflict);

    return this;
  }

  private enum Labels {
    ARTICLE("Article wrapper"), SECTION_TITLE("Section title"), LINK_TO_MAIN_PAGE(
        "Link to main page"), SECTION("Section as the container of many elements"), SECTION_ITEM(
        "Item in a section"), LOAD_MORE_BUTTON("Load more button"), NUMBER_OF_ITEMS(
        "Number of items in curated content section"), ITEM_LABELS("Curated Content items labels"), ALERT_NOTIFICATION(
        "Alert notification");

    private String name;

    Labels(String name) {
      this.name = name;
    }
  }
}
