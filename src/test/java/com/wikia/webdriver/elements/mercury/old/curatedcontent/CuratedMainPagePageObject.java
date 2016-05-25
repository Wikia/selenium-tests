package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CuratedMainPagePageObject {

  @FindBy(css = ".mobile-top-leaderboard")
  private WebElement mobileTopLeaderboard;
  @FindBy(css = ".wiki-page-title")
  private WebElement articleTitle;
  @FindBy(css = ".wiki-page-header__title")
  private WebElement revisedArticleTitle;
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

  private By mainPagePadSlot = By.cssSelector(".main-page-pad-slot");

  private enum Settings {
    TIME_OUT_IN_SEC(5),
    CHECK_OUT_IN_MILLI_SEC(1000);

    private int value;

    Settings(int value) {
      this.value = value;
    }
  }

  private WebDriver driver;
  private Wait wait;
  private JavascriptActions jsActions;

  public CuratedMainPagePageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);
    this.jsActions = new JavascriptActions(driver);

    PageFactory.initElements(driver, this);
  }

  public int getElementOffsetTop(String element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return Integer
        .parseInt(js.executeScript("return $(arguments[0]).offset().top", element).toString());
  }

  public boolean isMobileTopLeaderboardVisible() {
    return isCuratedElementVisible(mobileTopLeaderboard);
  }

  public boolean isArticleTitleVisible() {
    return isCuratedElementVisible(articleTitle);
  }

  public boolean isRevisedArticleTitleVisible() {
    return isCuratedElementVisible(revisedArticleTitle);
  }

  public boolean isWikiaStatsContainerVisible() {
    return isCuratedElementVisible(wikiaStatsContainer);
  }

  public boolean isMainPagePadSlotInDOM() {
    try {
      wait.forElementPresent(mainPagePadSlot, false);
      return true;
    } catch (TimeoutException e) {
      return false;
    }
  }

  public boolean isFeaturedContentVisible() {
    return isCuratedElementVisible(featuredContent);
  }

  public boolean isCuratedContentVisible() {
    return isCuratedElementVisible(curatedContent);
  }

  public boolean isMobileInContentVisible() {
    return isCuratedElementVisible(mobileInContent);
  }

  public boolean isTrendingArticlesVisible() {
    return isCuratedElementVisible(trendingArticles);
  }

  public boolean isTrendingVideosVisible() {
    return isCuratedElementVisible(trendingVideos);
  }

  public boolean isMobilePrefooterVisible() {
    return isCuratedElementVisible(mobilePrefooter);
  }

  private boolean isCuratedElementVisible(WebElement element) {
    try {
      wait.forElementVisible(element, Settings.TIME_OUT_IN_SEC.value,
                             Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }
}
