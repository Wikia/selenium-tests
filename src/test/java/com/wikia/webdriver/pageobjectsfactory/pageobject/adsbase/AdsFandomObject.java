package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

  protected String presentFandomTopBoxadDesktopSelector = "div[id*='gpt-top-boxad-desktop']";
  protected String presentFandomTopBoxadMobileSelector = "div[id*='gpt-top-boxad-mobile']";
  protected String presentFandomTopBoxadSelector = "div[id*='gpt-top-boxad']";
  protected String presentFandomBottomBoxadSelector = "div[id*='gpt-bottom-boxad']";

  @FindBy(css = "div[id*='gpt-top-leaderboard-desktop']")
  protected WebElement presentFandomDesktopArticleTopLeaderboard;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard-desktop']")
  protected WebElement presentFandomDesktopArticleBottomLeaderboard;

  @FindBy(css = "div[id*='gpt-top-leaderboard-mobile']")
  protected WebElement presentFandomMobileArticleTopLeaderboard;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard-mobile']")
  protected WebElement presentFandomMobileArticleBottomLeaderboard;

  @FindBy(css = "div[id*='gpt-top-leaderboard']")
  protected WebElement presentFandomHubTopLeaderboard;

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

}
