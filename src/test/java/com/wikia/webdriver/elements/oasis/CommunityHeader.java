package com.wikia.webdriver.elements.oasis;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CommunityHeader extends BasePageObject {

  @FindBy(css = ".wds-community-header__wordmark img")
  private WebElement wordmark;

  @FindBy(css = ".wds-community-header__sitename a")
  private WebElement wikiName;

  @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"add-new-page\"]")
  private WebElement addNewPageButton;

  @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"wiki-activity\"]")
  private WebElement wikiActivityButton;

  @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"admin-dashboard\"]")
  private WebElement adminDashboardButton;

  @FindBy(css = ".wds-community-header .wds-tabs__tab #wds-icons-explore-small")
  private WebElement exploreTab;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-activity\"]")
  private WebElement exploreWikiActivityLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-random\"]")
  private WebElement exploreRandomLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-community\"]")
  private WebElement exploreCommunityLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-videos\"]")
  private WebElement exploreVideosLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-images\"]")
  private WebElement exploreImagesLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-forum\"]")
  private WebElement exploreForumLink;

  @FindBy(css = ".wds-community-header a[data-tracking=\"discuss\"], .wds-community-header a[data-tracking=\"forum\"]")
  private WebElement discussLink;


  public MainPage clickWordmark() {
    wait.forElementClickable(wordmark).click();

    PageObjectLogging.logInfo("clicked on wordmark image");

    return new MainPage();
  }

  public MainPage clickWikiName() {
    wait.forElementClickable(wikiName).click();

    PageObjectLogging.logInfo("clicked on wiki name");

    return new MainPage();
  }

  public void clickAddNewPage() {
    wait.forElementClickable(addNewPageButton).click();

    PageObjectLogging.logInfo("clicked Add New Page button");
  }

  public void clickWikiActivity() {
    wait.forElementClickable(wikiActivityButton).click();

    PageObjectLogging.logInfo("clicked Wiki Activity Button");
  }

  public void clickAdminDashboard()  {
    wait.forElementClickable(adminDashboardButton).click();

    PageObjectLogging.logInfo("clicked admin dashboard Button");
  }

  public CommunityHeader openExploreMenu()  {
   new Actions(driver).moveToElement(exploreTab).perform();

   PageObjectLogging.logInfo("explore dropdown opened");

   return this;
  }

  public void clickExploreWikiActivityLink() {
    wait.forElementClickable(exploreWikiActivityLink).click();

    PageObjectLogging.logInfo("explore -> wikiActivity link clicked");
  }

  public void clickExploreRandomLink() {
    wait.forElementClickable(exploreRandomLink).click();

    PageObjectLogging.logInfo("explore -> random page link clicked");
  }

  public void clickExploreCommunityLink() {
    wait.forElementClickable(exploreCommunityLink).click();

    PageObjectLogging.logInfo("explore -> community link clicked");
  }

  public void clickExploreVideosLink() {
    wait.forElementClickable(exploreVideosLink).click();

    PageObjectLogging.logInfo("explore -> videos link clicked");
  }

  public void clickExploreImagesLink() {
    wait.forElementClickable(exploreImagesLink).click();

    PageObjectLogging.logInfo("explore -> images link clicked");
  }

  public void clickDiscussLink() {
    wait.forElementClickable(discussLink).click();

    PageObjectLogging.logInfo("discuss link clicked");
  }

  public void clickExploreForumLink() {
    wait.forElementClickable(exploreForumLink).click();

    PageObjectLogging.logInfo("explore->forum link clicked");
  }

  public boolean isDiscussLinkDisplayed() {
    return this.isElementDisplayed(discussLink);
  }

  public boolean isExploreForumLinkDisplayed() {
    return this.isElementDisplayed(exploreForumLink);
  }
}
