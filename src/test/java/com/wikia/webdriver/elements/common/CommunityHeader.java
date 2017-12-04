package com.wikia.webdriver.elements.common;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialAdminDashboardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

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

  @FindBy(
      css = ".wds-community-header .wds-tabs__tab #wds-icons-explore-tiny, .wds-community-header .wds-tabs__tab use[*|href=\"#wds-icons-explore-tiny\"]")
  private WebElement exploreTab;

  @FindBy(
      css = ".wds-dropdown a[data-tracking=\"explore-activity\"], .wds-dropdown a[data-tracking-label=\"explore-activity\"]")
  private WebElement exploreWikiActivityLink;

  @FindBy(
      css = ".wds-dropdown a[data-tracking=\"explore-random\"], .wds-dropdown a[data-tracking-label=\"explore-random\"]")
  private WebElement exploreRandomLink;

  @FindBy(
      css = ".wds-dropdown a[data-tracking=\"explore-community\"], .wds-dropdown a[data-tracking-label=\"explore-community\"]")
  private WebElement exploreCommunityLink;

  @FindBy(
      css = ".wds-dropdown a[data-tracking=\"explore-videos\"], .wds-dropdown a[data-tracking-label=\"explore-videos\"]")
  private WebElement exploreVideosLink;

  @FindBy(
      css = ".wds-dropdown a[data-tracking=\"explore-images\"], .wds-dropdown a[data-tracking-label=\"explore-images\"]")
  private WebElement exploreImagesLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-forum\"]")
  private WebElement exploreForumLink;

  @FindBy(
      css = ".wds-community-header a[data-tracking=\"discuss\"], .wds-community-header a[data-tracking=\"forum\"]")
  private WebElement discussLink;

  @FindBy(css = ".wds-community-header .wds-avatar-stack__avatar a")
  private List<WebElement> avatars;

  @FindBy(css = ".wds-community-header")
  private WebElement communityHeader;

  @FindBy(css = "#ShareEntryPoint")
  private WebElement shareButton;

  @FindBy(css = "#PageShareModalDialog")
  private WebElement shareDialog;

  @FindBy(css = "#ShareEntryPoint")
  private WebElement shareButton;

  public boolean isVisible() {
    return this.isElementDisplayed(communityHeader);
  }

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

  public CreateArticleModalComponentObject clickAddNewPage() {
    wait.forElementClickable(addNewPageButton).click();

    PageObjectLogging.logInfo("clicked Add New Page button");

    return new CreateArticleModalComponentObject(this.driver);
  }

  public SpecialWikiActivityPageObject clickWikiActivity() {
    wait.forElementClickable(wikiActivityButton).click();

    PageObjectLogging.logInfo("clicked Wiki Activity Button");

    return new SpecialWikiActivityPageObject();
  }

  public SpecialAdminDashboardPageObject clickAdminDashboard() {
    wait.forElementClickable(adminDashboardButton).click();

    PageObjectLogging.logInfo("clicked admin dashboard Button");

    return new SpecialAdminDashboardPageObject();
  }

  public UserProfilePage clickUserAvatar(int index) {
    wait.forElementClickable(avatars.get(index)).click();

    PageObjectLogging.logInfo("clicked user avatar");

    return new UserProfilePage();
  }

  public String getUserNameFromAvatar(int index) {
    return avatars.get(index).getAttribute("title");
  }

  public CommunityHeader openExploreMenu() {
    new Actions(driver).moveToElement(exploreTab).perform();

    PageObjectLogging.logInfo("explore dropdown opened");

    return this;
  }

  public SpecialWikiActivityPageObject clickExploreWikiActivityLink() {
    wait.forElementClickable(exploreWikiActivityLink).click();

    PageObjectLogging.logInfo("explore -> wikiActivity link clicked");

    return new SpecialWikiActivityPageObject();
  }

  public ArticlePageObject clickExploreRandomLink() {
    wait.forElementClickable(exploreRandomLink).click();

    PageObjectLogging.logInfo("explore -> random page link clicked");

    return new ArticlePageObject();
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

  public CommunityHeader clickShareButton() {
    shareButton.click();
    wait.forElementVisible(shareDialog);

    return this;
  }

  public CommunityHeader clickShareOnFacebook() {
    shareButton.click();
    wait.forElementVisible(shareDialog);

    return this;
  }

  public boolean isDiscussLinkDisplayed() {
    return this.isElementDisplayed(discussLink);
  }

  public boolean isExploreForumLinkDisplayed() {
    return this.isElementDisplayed(exploreForumLink);
  }
}
