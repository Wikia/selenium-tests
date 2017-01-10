package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents all the levels below Curated Main Page
 */
public class CuratedContentPageObject {

  @FindBy(css = ".article-wrapper")
  private WebElement articleWrapper;
  @FindBy(css = ".wiki-page-title")
  private WebElement sectionTitle;
  @FindBy(css = ".wiki-title a")
  private WebElement linkToMainPage;
  @FindBy(css = ".curated-content-items")
  private WebElement sectionContainer;
  @FindBy(css = "div.curated-content a")
  private List<WebElement> curatedContentItems;

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

  private WebDriver driver;
  private Wait wait;
  private JavascriptActions jsActions;

  public CuratedContentPageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.jsActions = new JavascriptActions(driver);

    PageFactory.initElements(driver, this);
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
    jsActions.scrollToElement(curatedContentItems.get(elementNumber));
    curatedContentItems.get(elementNumber).click();
    return this;
  }

  public CuratedContentPageObject clickOnMainPageLink() {
    wait.forElementVisible(linkToMainPage);
    jsActions.scrollToElement(linkToMainPage);
    linkToMainPage.click();
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
}
