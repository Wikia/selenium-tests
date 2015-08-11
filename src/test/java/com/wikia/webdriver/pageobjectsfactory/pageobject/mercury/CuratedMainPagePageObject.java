package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownership: Content X-Wing
 */
public class CuratedMainPagePageObject extends BasePageObject {

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

  private enum Settings {
    TIME_OUT_IN_SEC(5),
    CHECK_OUT_IN_MILLI_SEC(1000);

    private int value;

    Settings(int value) {
      this.value = value;
    }
  }

  public CuratedMainPagePageObject(WebDriver driver) {
    super(driver);
  }

  public int getElementOffsetTop(String element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return Integer
        .parseInt(js.executeScript("return $(arguments[0]).offset().top", element).toString());
  }

  public boolean isMobileTopLeaderboardVisible() {
    try {
      wait.forElementVisible(mobileTopLeaderboard, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isArticleTitleVisible() {
    try {
      wait.forElementVisible(articleTitle, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isWikiaStatsContainerVisible() {
    try {
      wait.forElementVisible(wikiaStatsContainer, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isFeaturedContentVisible() {
    try {
      wait.forElementVisible(featuredContent, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isCuratedContentVisible() {
    try {
      wait.forElementVisible(curatedContent, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isMobileInContentVisible() {
    try {
      wait.forElementVisible(mobileInContent, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isTrendingArticlesVisible() {
    try {
      wait.forElementVisible(trendingArticles, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isTrendingVideosVisible() {
    try {
      wait.forElementVisible(trendingVideos, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }

  public boolean isMobilePrefooterVisible() {
    try {
      wait.forElementVisible(mobilePrefooter, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }
}
