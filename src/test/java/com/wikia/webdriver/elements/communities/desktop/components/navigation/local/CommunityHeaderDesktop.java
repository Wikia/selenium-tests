package com.wikia.webdriver.elements.communities.desktop.components.navigation.local;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.desktop.components.globalshortcuts.ActionExplorerModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.AddMediaModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.oasis.MainPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialAdminDashboardPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CommunityHeaderDesktop extends BasePageObject {

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

  @FindBy(css = ".wds-community-header .wds-tabs__tab #wds-icons-book-tiny, .wds-community-header .wds-tabs__tab use[*|href=\"#wds-icons-book-tiny\"]")
  private WebElement exploreTab;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-activity\"], .wds-dropdown a[data-tracking-label=\"explore-activity\"]")
  private WebElement exploreWikiActivityLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-random\"], .wds-dropdown a[data-tracking-label=\"explore-random\"]")
  private WebElement exploreRandomLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-community\"], .wds-dropdown a[data-tracking-label=\"explore-community\"]")
  private WebElement exploreCommunityLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-videos\"], .wds-dropdown a[data-tracking-label=\"explore-videos\"]")
  private WebElement exploreVideosLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-images\"], .wds-dropdown a[data-tracking-label=\"explore-images\"]")
  private WebElement exploreImagesLink;

  @FindBy(css = ".wds-dropdown a[data-tracking=\"explore-forum\"]")
  private WebElement exploreForumLink;

  @FindBy(css = ".wds-community-header a[data-tracking=\"discuss\"], .wds-community-header a[data-tracking=\"forum\"]")
  private WebElement discussLink;

  @FindBy(css = ".wds-community-header .wds-avatar-stack .wds-avatar a img")
  private List<WebElement> avatars;

  @FindBy(css = ".wds-community-header")
  private WebElement communityHeader;

  @FindBy(css = ".wds-community-header__wiki-buttons .wds-dropdown")
  private WebElement moreToolsDropdown;

  @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-add-new-image\"]")
  private WebElement moreToolsAddImageLink;

  @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-add-new-video\"]")
  private WebElement moreToolsAddVideoLink;

  @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-recent-changes\"]")
  private WebElement moreToolsRecentChanges;

  @FindBy(css = ".wds-community-header__wiki-buttons a[data-tracking=\"more-all-shortcuts\"]")
  private WebElement moreToolsAllShortcuts;

  public boolean isVisible() {
    return this.isElementDisplayed(communityHeader);
  }

  public MainPage clickWordmark() {
    wait.forElementClickable(wordmark).click();

    Log.info("clicked on wordmark image");

    return new MainPage();
  }

  public MainPage clickWikiName() {
    wait.forElementClickable(wikiName).click();

    Log.info("clicked on wiki name");

    return new MainPage();
  }

  public CreateArticleModalComponentObject clickAddNewPage() {
    wait.forElementClickable(addNewPageButton).click();

    Log.info("clicked Add New Page button");

    return new CreateArticleModalComponentObject(this.driver);
  }

  public SpecialWikiActivityPageObject clickWikiActivity() {
    wait.forElementClickable(wikiActivityButton).click();

    Log.info("clicked Wiki Activity Button");

    return new SpecialWikiActivityPageObject();
  }

  public SpecialAdminDashboardPageObject clickAdminDashboard() {
    wait.forElementClickable(adminDashboardButton).click();

    Log.info("clicked admin dashboard Button");

    return new SpecialAdminDashboardPageObject();
  }

  public UserProfilePage clickUserAvatar(int index) {
    wait.forElementClickable(avatars.get(index)).click();

    Log.info("clicked user avatar");

    return new UserProfilePage();
  }

  public String getUserNameFromAvatar(int index) {
    return avatars.get(index).getAttribute("alt");
  }

  public CommunityHeaderDesktop openExploreMenu() {
    scrollTo(exploreTab);
    new Actions(driver).moveToElement(exploreTab).perform();

    Log.info("explore dropdown opened");

    return this;
  }

  public SpecialWikiActivityPageObject clickExploreWikiActivityLink() {
    wait.forElementClickable(exploreWikiActivityLink);
    exploreWikiActivityLink.click();

    Log.info("explore -> wikiActivity link clicked");

    return new SpecialWikiActivityPageObject();
  }

  public ArticlePageObject clickExploreRandomLink() {
    wait.forElementClickable(exploreRandomLink);
    exploreRandomLink.click();
    Log.info("explore -> random page link clicked");

    return new ArticlePageObject();
  }

  public void clickExploreCommunityLink() {
    wait.forElementClickable(exploreCommunityLink);
    exploreCommunityLink.click();
    Log.info("explore -> community link clicked");
  }

  public void clickExploreVideosLink() {
    wait.forElementClickable(exploreVideosLink);
    exploreVideosLink.click();
    Log.info("explore -> videos link clicked");
  }

  public void clickExploreImagesLink() {
    wait.forElementClickable(exploreImagesLink);
    exploreImagesLink.click();
    Log.info("explore -> images link clicked");
  }

  public void clickDiscussLink() {
    wait.forElementClickable(discussLink);
    scrollAndClick(discussLink);

    Log.info("discuss link clicked");
  }

  public void clickExploreForumLink() {
    wait.forElementClickable(exploreForumLink);
    scrollAndClick(exploreForumLink);

    Log.info("explore->forum link clicked");
  }

  public CommunityHeaderDesktop openMoreToolsDropdown() {
    new Actions(driver).moveToElement(moreToolsDropdown).perform();

    Log.info("more tools dropdown opened");

    return this;
  }

  public void clickMoreAddImageLink() {
    wait.forElementClickable(moreToolsAddImageLink).click();

    Log.info("more -> Add image link clicked");
  }

  public AddMediaModalComponentObject clickMoreAddVideoLink() {
    wait.forElementClickable(moreToolsAddVideoLink).click();

    Log.info("more -> Add video link clicked");

    return new AddMediaModalComponentObject();
  }

  public void clickMoreRecentChanges() {
    wait.forElementClickable(moreToolsRecentChanges).click();

    Log.info("more -> Recent Changes link clicked");
  }

  public ActionExplorerModal clickMoreAllShortcuts() {
    wait.forElementClickable(moreToolsAllShortcuts).click();

    Log.info("more -> All shortcuts link clicked");

    return new ActionExplorerModal();
  }

  public boolean isDiscussLinkDisplayed() {
    return this.isElementDisplayed(discussLink);
  }

  public boolean isExploreForumLinkDisplayed() {
    return this.isElementDisplayed(exploreForumLink);
  }
}
