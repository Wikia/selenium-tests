package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AdsFandomObject extends AdsBaseObject {

  protected String
      presentFandomDesktopArticleTopLeaderboardSelector = "div[id*='gpt-top-leaderboard-desktop']";
  protected String
      presentFandomDesktopArticleBottomLeaderboardSelector = "div[id*='gpt-bottom-leaderboard-desktop']";
  protected String
      presentFandomMobileArticleTopLeaderboardSelector = "div[id*='gpt-top-leaderboard-mobile']";
  protected String
      presentFandomMobileArticleBottomLeaderboardSelector = "div[id*='gpt-bottom-leaderboard-mobile']";
  protected String
      presentFandomHubTopLeaderboardSelector = "div[id*='gpt-top-leaderboard']";
  protected String
      presentFandomHubBottomLeaderboardSelector = "div[id*='gpt-bottom-leaderboard']";

  protected String presentFandomTopBoxadSelector = "div[id*='gpt-top-boxad']";
  protected String presentFandomBottomBoxadSelector = "div[id*='gpt-bottom-boxad']";

  public AdsFandomObject(WebDriver driver,
                         String testedPage
                         ) {
    super(driver, testedPage);
    }

  public void verifyFandomDesktopArticleTopLeaderboard() {
    verifyAdVisibleInSlot(presentFandomDesktopArticleTopLeaderboardSelector,
                          presentFandomDesktopArticleTopLeaderboard);
  }

  public void verifyFandomDesktopArticleBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomDesktopArticleBottomLeaderboardSelector)));
    verifyAdVisibleInSlot(presentFandomDesktopArticleBottomLeaderboardSelector,
                          presentFandomDesktopArticleBottomLeaderboard);
  }

  public void verifyFandomMobileArticleTopLeaderboard() {
    verifyAdVisibleInSlot(presentFandomMobileArticleTopLeaderboardSelector,
                          presentFandomMobileArticleTopLeaderboard);
  }

  public void verifyFandomMobileArticleBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomMobileArticleBottomLeaderboardSelector)));
    verifyAdVisibleInSlot(presentFandomMobileArticleBottomLeaderboardSelector,
                          presentFandomMobileArticleBottomLeaderboard);
  }

  public void verifyFandomHubTopLeaderboard() {
    verifyAdVisibleInSlot(presentFandomHubTopLeaderboardSelector,
                          presentFandomHubTopLeaderboard);
  }

  public void verifyFandomHubBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomHubBottomLeaderboardSelector)));
    verifyAdVisibleInSlot(presentFandomHubBottomLeaderboardSelector,
                          presentFandomHubBottomLeaderboard);
  }

  public void verifyFandomTopBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomTopBoxadSelector)));
    verifyAdVisibleInSlot(presentFandomTopBoxadSelector, presentFandomTopBoxad);
  }

  public void verifyFandomBottomBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomBoxadSelector)));
    verifyAdVisibleInSlot(presentFandomBottomBoxadSelector, presentFandomBottomBoxad);
  }

}
