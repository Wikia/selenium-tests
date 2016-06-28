package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdsFandomObject extends AdsBaseObject {

  protected String presentFandomTopLeaderboardSelector = "div[id*='gpt-top-leaderboard']";

  protected String
      presentFandomBottomLeaderboardSelector = "div[id*='gpt-bottom-leaderboard']";
  protected String
      presentFandomBottomLeaderboardDesktopSelector = "div[id*='gpt-bottom-leaderboard-desktop']";
  protected String
      presentFandomBottomLeaderboardMobileSelector = "div[id*='gpt-bottom-leaderboard-mobile']";

  protected String presentFandomTopBoxadSelector = "div[id*='gpt-top-boxad']";
  protected String presentFandomTopBoxadDesktopSelector = "div[id*='gpt-top-boxad-desktop']";
  protected String presentFandomTopBoxadMobileSelector = "div[id*='gpt-top-boxad-mobile']";

  protected String
      presentFandomIncontentBoxadDesktopSelector = "div[id*='gpt-incontent-boxad-desktop']";

  protected String presentFandomBottomBoxadSelector = "div[id*='gpt-bottom-boxad']";
  protected String presentFandomBottomBoxadDesktopSelector = "div[id*='gpt-bottom-boxad-desktop']";
  protected String presentFandomBottomBoxadMobileSelector = "div[id*='gpt-bottom-boxad-mobile']";

  @FindBy(css = "div[id*='gpt-top-leaderboard']")
  protected WebElement presentFandomTopLeaderboard;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard']")
  protected WebElement presentFandomBottomLeaderboard;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard-desktop']")
  protected WebElement presentFandomBottomLeaderboardDesktop;

  @FindBy(css = "div[id*='gpt-bottom-leaderboard-mobile']")
  protected WebElement presentFandomBottomLeaderboardMobile;

  @FindBy(css = "div[id*='gpt-top-boxad']")
  protected WebElement presentFandomTopBoxad;

  @FindBy(css = "div[id*='gpt-top-boxad-desktop']")
  protected WebElement presentFandomTopBoxadDesktop;

  @FindBy(css = "div[id*='gpt-top-boxad-mobile']")
  protected WebElement presentFandomTopBoxadMobile;

  @FindBy(css = "div[id*='gpt-bottom-boxad']")
  protected WebElement presentFandomBottomBoxad;

  @FindBy(css = "div[id*='gpt-bottom-boxad-desktop']")
  protected WebElement presentFandomBottomBoxadDesktop;

  @FindBy(css = "div[id*='gpt-bottom-boxad-mobile']")
  protected WebElement presentFandomBottomBoxadMobile;

  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
    }

  public void verifyFandomTopLeaderboard() {
    verifyAdVisibleInSlot(presentFandomTopLeaderboardSelector, presentFandomTopLeaderboard);
  }

  public void verifyFandomBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomLeaderboardSelector)));
    verifyAdVisibleInSlot(presentFandomBottomLeaderboardSelector,
                          presentFandomBottomLeaderboard);
  }

  public void verifyFandomBottomLeaderboardDesktop() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomLeaderboardDesktopSelector)));
    verifyAdVisibleInSlot(presentFandomBottomLeaderboardDesktopSelector,
                          presentFandomBottomLeaderboardDesktop);
  }

  public void verifyFandomBottomLeaderboardMobile() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomLeaderboardMobileSelector)));
    verifyAdVisibleInSlot(presentFandomBottomLeaderboardMobileSelector,
                          presentFandomBottomLeaderboardMobile);
  }

  public void verifyFandomTopBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomTopBoxadSelector)));
    verifyAdVisibleInSlot(presentFandomTopBoxadSelector, presentFandomTopBoxad);
  }

  public void verifyFandomTopBoxadDesktop() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomTopBoxadDesktopSelector)));
    verifyAdVisibleInSlot(presentFandomTopBoxadDesktopSelector, presentFandomTopBoxadDesktop);
  }

  public void verifyFandomTopBoxadMobile() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomTopBoxadMobileSelector)));
    verifyAdVisibleInSlot(presentFandomTopBoxadMobileSelector, presentFandomTopBoxadMobile);
  }

  public void verifyFandomBottomBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomBoxadSelector)));
    verifyAdVisibleInSlot(presentFandomBottomBoxadSelector, presentFandomBottomBoxad);
  }

  public void verifyNoFandomBottomBoxadMobile() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomBoxadMobileSelector)));
    verifyNoAd(presentFandomBottomBoxadMobileSelector);
  }

  public void verifyNoFandomIncontentBoxadDesktop() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomIncontentBoxadDesktopSelector)));
    verifyNoAd(presentFandomIncontentBoxadDesktopSelector);
  }

  public void verifyNoFandomBottomLeaderboardDesktop() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomLeaderboardMobileSelector)));
    verifyNoAd(presentFandomBottomLeaderboardMobileSelector);
  }

  public void verifyNoFandomBottomLeaderboardMobile() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(
        presentFandomBottomLeaderboardMobileSelector)));
    verifyNoAd(presentFandomBottomLeaderboardMobileSelector);
  }
}
