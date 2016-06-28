package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdsFandomObject extends AdsBaseObject {

  protected String
      presentFandomTopLeaderboardSelector = "div[id*='gpt-top-leaderboard']";
  protected String
      presentFandomDesktopArticleBottomLeaderboardSelector = "div[id*='gpt-bottom-leaderboard-desktop']";
  protected String
      presentFandomMobileArticleBottomLeaderboardSelector = "div[id*='gpt-bottom-leaderboard-mobile']";
  protected String
      presentFandomHubBottomLeaderboardSelector = "div[id*='gpt-bottom-leaderboard']";

  protected String presentFandomTopBoxadDesktopSelector = "div[id*='gpt-top-boxad-desktop']";
  protected String presentFandomTopBoxadMobileSelector = "div[id*='gpt-top-boxad-mobile']";
  protected String presentFandomTopBoxadSelector = "div[id*='gpt-top-boxad']";
  protected String presentFandomIncontentBoxadSelector = "div[id*='gpt-incontent-boxad']";
  protected String presentFandomBottomBoxadSelector = "div[id*='gpt-bottom-boxad']";

  @FindBy(css = "div[id*='gpt-top-leaderboard']")
  protected WebElement presentFandomTopLeaderboard;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard-desktop']")
  protected WebElement presentFandomDesktopArticleBottomLeaderboard;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard-mobile']")
  protected WebElement presentFandomMobileArticleBottomLeaderboard;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard']")
  protected WebElement presentFandomHubBottomLeaderboard;

  @FindBy(css = "div[id*='gpt-top-boxad-desktop']")
  protected WebElement presentFandomTopBoxadDesktop;

  @FindBy(css = "div[id*='gpt-top-boxad-mobile']")
  protected WebElement presentFandomTopBoxadMobile;

  @FindBy(css = "div[id*='gpt-top-boxad']")
  protected WebElement presentFandomTopBoxad;

  @FindBy(css = "div[id*='gpt-bottom-boxad']")
  protected WebElement presentFandomBottomBoxad;

  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
    }

  public void verifyFandomTopLeaderboard() {
    verifyAdVisibleInSlot(presentFandomTopLeaderboardSelector, presentFandomTopLeaderboard);
  }

  public void verifyFandomDesktopArticleBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomDesktopArticleBottomLeaderboardSelector)));
    verifyAdVisibleInSlot(presentFandomDesktopArticleBottomLeaderboardSelector,
                          presentFandomDesktopArticleBottomLeaderboard);
  }

  public void verifyFandomMobileArticleBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomMobileArticleBottomLeaderboardSelector)));
    verifyAdVisibleInSlot(presentFandomMobileArticleBottomLeaderboardSelector,
                          presentFandomMobileArticleBottomLeaderboard);
  }

  public void verifyFandomHubBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomHubBottomLeaderboardSelector)));
    verifyAdVisibleInSlot(presentFandomHubBottomLeaderboardSelector,
                          presentFandomHubBottomLeaderboard);
  }

  public void verifyFandomDesktopTopBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomTopBoxadDesktopSelector)));
    verifyAdVisibleInSlot(presentFandomTopBoxadDesktopSelector, presentFandomTopBoxadDesktop);
  }

  public void verifyFandomMobileTopBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomTopBoxadMobileSelector)));
    verifyAdVisibleInSlot(presentFandomTopBoxadMobileSelector, presentFandomTopBoxadMobile);
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

  public void verifyNoFandomBottomBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomBoxadSelector)));
    verifyNoAd(presentFandomBottomBoxadSelector);
  }

  public void verifyNoFandomIncontentBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomIncontentBoxadSelector)));
    verifyNoAd(presentFandomIncontentBoxadSelector);
  }

  public void verifyNoFandomDesktopArticleBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomMobileArticleBottomLeaderboardSelector)));
    verifyNoAd(presentFandomMobileArticleBottomLeaderboardSelector);
  }

  public void verifyNoFandomMobileArticleBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomMobileArticleBottomLeaderboardSelector)));
    verifyNoAd(presentFandomMobileArticleBottomLeaderboardSelector);
  }
}
