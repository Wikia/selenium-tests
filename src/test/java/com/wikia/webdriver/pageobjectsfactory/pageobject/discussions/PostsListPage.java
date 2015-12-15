package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownership Social Wikia
 */
public class PostsListPage extends BasePageObject {

  @FindBy(css = ".post-detail")
  private List<WebElement> postList;

  @FindBy(css = "div.sort")
  private WebElement sortEntryPointMobile;

  @FindBy(css = ".sort span")
  private WebElement labelInSortEntryPointMobile;

  @FindBy(css = ".discussion-sort")
  private WebElement sortOptionsMobile;

  @FindBy(xpath = "//li[text()='Latest']")
  private WebElement latestLinkOnListMobile;

  @FindBy(xpath = "//li[text()='Trending']")
  private WebElement trendingLinkOnListMobile;

  @FindBy(xpath = "//li[text()='Latest']")
  private WebElement latestTabDesktop;

  @FindBy(xpath = "//li[text()='Trending']")
  private WebElement trendingTabDesktop;

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

  public PostsListPage clickOnLatestTabDesktop() {
    latestTabDesktop.click();
    return this;
  }

  public PostsListPage clickOnTrendingTabDesktop() {
    trendingTabDesktop.click();
    return this;
  }
}

