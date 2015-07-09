package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.urlbuilder.UrlBuilder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class CuratedPageObject extends BasePageObject {

  @FindBy(css = ".mobile-top-leaderboard")
  private WebElement mobileTopLeaderboard;
  @FindBy(css = ".article-title")
  private WebElement articleTitle;
  @FindBy(css = ".wikia-stats-container")
  private WebElement wikiaStatsContainer;
  @FindBy(css = ".featured-content")
  private WebElement featuredContent;
  @FindBy(css = ".curated-content")
  private WebElement curatedContent;
  @FindBy(css = ".mobile-in-content")
  private WebElement mobileInContent;
  @FindBy(css = ".trending-articles")
  private WebElement trendingArticles;
  @FindBy(css = ".trending-videos")
  private WebElement trendingVideos;
  @FindBy(css = ".mobile-prefooter")
  private WebElement mobilePrefooter;

  public CuratedPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isMobileTopLeaderboardVisible() {
    try {
      waitForElementVisibleByElement(mobileTopLeaderboard, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isArticleTitleVisible() {
    try {
      waitForElementVisibleByElement(articleTitle, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isWikiaStatsContainerVisible() {
    try {
      waitForElementVisibleByElement(wikiaStatsContainer, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isFeaturedContentVisible() {
    try {
      waitForElementVisibleByElement(featuredContent, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isCuratedContentVisible() {
    try {
      waitForElementVisibleByElement(curatedContent, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isMobileInContentVisible() {
    try {
      waitForElementVisibleByElement(mobileInContent, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isTrendingArticlesVisible() {
    try {
      waitForElementVisibleByElement(trendingArticles, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isTrendingVideosVisible() {
    try {
      waitForElementVisibleByElement(trendingVideos, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isMobilePrefooterVisible() {
    try {
      waitForElementVisibleByElement(mobilePrefooter, 5, 1000);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isUrlPathEqualTo(String path) {
    String currentPath = new UrlBuilder().getUrlPath(driver);
    return currentPath.equals(path);
  }

  public int getElementHeight(String element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return Integer.parseInt(js.executeScript("return $('" + element + "').offset().top").toString());
  }
}
