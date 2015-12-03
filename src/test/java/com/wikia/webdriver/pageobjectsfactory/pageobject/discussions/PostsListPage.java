package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership Social Wikia
 */
public class PostsListPage extends WikiBasePageObject {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;
  @FindBy(css = ".sort")
  private WebElement sortEntryPointMobile;
  @FindBy(css = ".sort span")
  private WebElement labelInSortEntryPointMobile;
  @FindBy(css = ".discussion-sort")
  private WebElement sortOptionsMobile;
  @FindBy(css = "li[data-type='latest']")
  private WebElement latestLinkOnListMobile;
  @FindBy(css = "li[data-type='trending']")
  private WebElement trendingLinkOnListMobile;
  @FindBy(css = ".back-button")
  private WebElement backToWiki;
  @FindBy(css = ".user-avatar")
  private WebElement avatarImage;
  @FindBy(css = ".avatar-username")
  private WebElement avatarName;
  @FindBy(css = "#WikiaUserPagesHeader")
  private WebElement userPageHeader;

  private static final String PATH = "d/f/%s";
  private static final String DEFAULT_ID = "203236";

  public PostsListPage(WebDriver driver) {
    super(driver);
  }

  public PostsListPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }

  public PostsListPage open() {
    return open(DEFAULT_ID);
  }

  public boolean isPostListEmpty() {
    return postList.isEmpty();
  }

  public PostsListPage clickOnSortButtonMobile() {
    sortEntryPointMobile.click();
    return this;
  }

  public boolean isSortListVisibleMobile() {
    wait.forElementVisible(sortOptionsMobile);
    return sortOptionsMobile.isDisplayed();
  }

  public PostsListPage clickOnLatestLinkMobile() {
    latestLinkOnListMobile.click();
    return this;
  }

  public PostsListPage clickOnTrendingLinkMobile() {
    trendingLinkOnListMobile.click();
    return this;
  }

  public String getSortButtonLabel() {
    return labelInSortEntryPointMobile.getText();
  }

  public void scrollToBottom(WebDriver driver) {
    ((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0, document.body.scrollHeight)");
  }

  public int getPostsListLength() {
    wait.forElementNotVisible(By.className(".spinner"));
    return postList.size();
  }

  public void clickBackToWikiLink() {
    backToWiki.click();
  }

  public void verifyAvatarLoadsUserPage() {
    avatarImage.click();
    wait.forElementVisible(userPageHeader);
  }

  public void verifyUsernameLoadsUserPage() {
    avatarName.click();
    wait.forElementVisible(userPageHeader);
  }

}
