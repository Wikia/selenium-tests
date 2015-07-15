package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by wikia on 2015-07-15.
 */
public class CuratedSectionPageObject extends MercuryBasePageObject {

  @FindBy(css = ".article-title")
  private WebElement articleTitle;
  @FindBy(css = ".wiki-title a")
  private WebElement linkToMainPage;
  @FindBy(css = ".curated-content-items")
  private WebElement sectionContainer;
  @FindBys(@FindBy(css = "div.curated-content a"))
  private List<WebElement> curatedContentItems;


 public CuratedSectionPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isTitleVisible() {
    return isElementVisible(articleTitle);
  }

  public boolean isLinkToMainPageVisible() {
    return isElementVisible(linkToMainPage);
  }

  public boolean isSectionVisible() {
    return isElementVisible(sectionContainer);
  }

  private void tapOnCuratedContentElement(int elementNumber){
    WebElement curatedContentItem = curatedContentItems.get(elementNumber);

    waitForElementByElement(curatedContentItem);
    scrollToElement(curatedContentItem);
    curatedContentItem.click();
  }

  public boolean isItemVisible(int i) {
    WebElement curatedContentItem = curatedContentItems.get(elementNumber);

    waitForElementByElement(curatedContentItem);
    scrollToElement(curatedContentItem);
    curatedContentItem.click();
    return isElementVisible();
  }

  public String getTitle() {
    return null;
  }
}
