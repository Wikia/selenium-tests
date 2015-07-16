package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

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
  @FindBys(@FindBy(css = "div.curated-content a"))
  private List<WebElement> curatedContentItems;

  private enum PageElements {
    SECTION_TITLE("Section title"),
    LINK_TO_MAIN_PAGE("Link to main page"),
    SECTION("Section as the container of many elements"),
    SECTION_ITEM("Item in a section");

    private String name;

    PageElements(String name) {
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

  public CuratedContentPageObject clickOnCuratedContentElement(int elementNumber) {
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

  public CuratedContentPageObject isTitleVisible() {
    PageObjectLogging.log(
        PageElements.SECTION_TITLE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(sectionTitle)
    );
    return this;
  }

  public CuratedContentPageObject isLinkToMainPageVisible() {
    PageObjectLogging.log(
        PageElements.LINK_TO_MAIN_PAGE.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(linkToMainPage)
    );
    return this;
  }

  public CuratedContentPageObject isSectionVisible() {
    PageObjectLogging.log(
        PageElements.SECTION.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(sectionContainer)
    );
    return this;
  }

  public CuratedContentPageObject isItemVisible(int elementNumber) {
    PageObjectLogging.log(
        PageElements.SECTION_ITEM.name,
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        isElementVisible(curatedContentItems.get(elementNumber))
    );
    return this;
  }
}
