package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdsFandomObject extends AdsBaseObject {

  protected String topLeaderboardSelector = "div[id$='TOP_LEADERBOARD_0__container__']";
  protected String bottomLeaderboardSelector = "div[id$='BOTTOM_LEADERBOARD_0__container__']";
  protected String topBoxadSelector = "div[id$='TOP_BOXAD_0__container__']";
  protected String incontentBoxadSelector = "div[id$='INCONTENT_BOXAD_0__container__']";
  protected String bottomBoxadSelector = "div[id$='BOTTOM_BOXAD_0__container__']";

  @FindBy(css = "div[id$='TOP_LEADERBOARD_0__container__']")
  protected WebElement topLeaderboardElement;

  @FindBy(css = "div[id$='BOTTOM_LEADERBOARD_0__container__']")
  protected WebElement bottomLeaderboardElement;

  @FindBy(css = "div[id$='TOP_BOXAD_0__container__']")
  protected WebElement topBoxadElement;

  @FindBy(css = "div[id$='INCONTENT_BOXAD_0__container__']")
  protected WebElement incontentBoxadElement;

  @FindBy(css = "div[id$='BOTTOM_BOXAD_0__container__']")
  protected WebElement bottomBoxadElement;

  public AdsFandomObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  public void verifyTopLeaderboard() {
    verifyAdVisibleInSlot(topLeaderboardSelector, topLeaderboardElement);
  }

  public void verifyBottomLeaderboard() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(bottomLeaderboardSelector)));
    verifyAdVisibleInSlot(bottomLeaderboardSelector, bottomLeaderboardElement);
  }

  public void verifyTopBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(topBoxadSelector)));
    verifyAdVisibleInSlot(topBoxadSelector, topBoxadElement);
  }

  public void verifyIncontentBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(incontentBoxadSelector)));
    verifyAdVisibleInSlot(incontentBoxadSelector, incontentBoxadElement);
  }

  public void verifyBottomBoxad() {
    jsActions.scrollToElement(wait.forElementVisible(By.cssSelector(bottomBoxadSelector)));
    verifyAdVisibleInSlot(bottomBoxadSelector, bottomBoxadElement);
  }

  public void verifyNoBottomBoxad() {
    verifyNoAd(bottomBoxadSelector);
  }

  public void verifyNoIncontentBoxad() {
    verifyNoAd(incontentBoxadSelector);
  }

  public void verifyNoBottomLeaderboard() {
    verifyNoAd(bottomLeaderboardSelector);
  }
}
