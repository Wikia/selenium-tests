package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

/**
 * This class represents all the levels below Curated Main Page
 */
public class CuratedContentPageObject extends BasePageObject {

  @FindBy(css = ".wiki-page-title")
  private WebElement sectionTitle;
  @FindBy(css = ".curated-content-section__back")
  private WebElement linkToMainPage;
  @FindBy(css = ".curated-content-section:not(.wds-is-hidden) .curated-content-items")
  private WebElement sectionContainer;
  @FindBy(css = ".curated-content .curated-content-section:not(.wds-is-hidden) "
                + ".curated-content-item .item-figure")
  private List<WebElement> curatedContentItems;

  public int getCuratedContentItemsNumber() {
    wait.forElementVisible(curatedContentItems.get(0));
    return curatedContentItems.size();
  }

  public CuratedContentPageObject clickOnCuratedContentElementByIndex(int elementNumber) {
    wait.forElementVisible(curatedContentItems.get(elementNumber));
    jsActions.scrollToElement(curatedContentItems.get(elementNumber));
    curatedContentItems.get(elementNumber).click();
    return this;
  }

  public CuratedContentPageObject isTitleVisible() {
    wait.forElementVisible(sectionTitle);
    PageObjectLogging.logInfo(Labels.SECTION_TITLE.name + " " + MercuryMessages.VISIBLE_MSG);
    return this;
  }

  public CuratedContentPageObject isLinkToMainPageVisible() {
    wait.forElementVisible(linkToMainPage);
    PageObjectLogging.logInfo(Labels.LINK_TO_MAIN_PAGE.name + " " + MercuryMessages.VISIBLE_MSG);
    return this;
  }

  public CuratedContentPageObject isSectionVisible() {
    wait.forElementVisible(sectionContainer);
    PageObjectLogging.logInfo(Labels.SECTION.name + " " + MercuryMessages.VISIBLE_MSG);
    return this;
  }

  public CuratedContentPageObject isCuratedContentItemVisibleByIndex(int elementNumber) {
    wait.forElementVisible(curatedContentItems.get(elementNumber));
    PageObjectLogging.logInfo(Labels.SECTION_ITEM.name + " " + MercuryMessages.VISIBLE_MSG);
    return this;
  }

  private enum Labels {
    ARTICLE("Article wrapper"),
    SECTION_TITLE("Section title"),
    LINK_TO_MAIN_PAGE("Link to main page"),
    SECTION("Section as the container of many elements"),
    SECTION_ITEM("Item in a section"),
    LOAD_MORE_BUTTON("Load more button"),
    NUMBER_OF_ITEMS("Number of items in curated content section"),
    ITEM_LABELS("Curated Content items labels");

    private String name;

    Labels(String name) {
      this.name = name;
    }
  }
}
