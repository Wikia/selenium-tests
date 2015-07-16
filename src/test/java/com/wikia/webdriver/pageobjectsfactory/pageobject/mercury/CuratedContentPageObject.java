package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by wikia on 2015-07-15.
 */
public class CuratedContentPageObject extends MercuryBasePageObject {

  @FindBy(css = ".article-title")
  private WebElement sectionTitle;
  @FindBy(css = ".wiki-title a")
  private WebElement linkToMainPage;
  @FindBy(css = ".curated-content-items")
  private WebElement sectionContainer;
  @FindBys(@FindBy(css = "div.curated-content a"))
  private List<WebElement> curatedContentItems;


 public CuratedContentPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isTitleVisible() {
    return isElementVisible(sectionTitle);
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

  public boolean isItemVisible(int elementNumber) {
    WebElement sectionItem = curatedContentItems.get(elementNumber);
    return isElementVisible(sectionItem);
  }

  public String getTitle() {
    waitForElementByElement(sectionTitle);
    return sectionTitle.getText();
  }

  public void tapOnMainPageLink() {
    waitForElementByElement(linkToMainPage);
    scrollToElement(linkToMainPage);
    linkToMainPage.click();
  }
}
