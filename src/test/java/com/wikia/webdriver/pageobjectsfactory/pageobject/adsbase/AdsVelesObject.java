package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AdsVelesObject extends AdsBaseObject {

  private static final String INCONTENT_VIDEO = ".video-display-wrapper";

  public static final String SECOND_ARTICLE_HEADER_OASIS = "#Player_above";
  public static final String FIRST_ARTICLE_HEADER_MOBILE = "#Header";

  public static final String BIDDER_PLAYER_EVENT_PATTERN = ".*adengplayerinfo.*event_name=in_viewport_with_fallback_bid.*";
  public static final String DIRECT_PLAYER_EVENT_PATTERN = ".*adengplayerinfo.*event_name=in_viewport_with_direct.*";
  public static final String NO_OFFER_PLAYER_EVENT_PATTERN = ".*adengplayerinfo.*event_name=in_viewport_without_offer.*";

  public AdsVelesObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  public void verifyVelesIncontentSlotDisplayedOasis(){
    tiggerIncontentPlayerOnOasis();
    wait.forElementVisible(driver.findElement(By.cssSelector(INCONTENT_VIDEO)));
  }

  public void verifyVelesIncontentSlotDisplayedMobile(){
    tiggerIncontentPlayerOnMobile();
    wait.forElementVisible(driver.findElement(By.cssSelector(INCONTENT_VIDEO)));
  }

  public void tiggerIncontentPlayerOnMobile() {
    scrollToPosition(FIRST_ARTICLE_HEADER_MOBILE);
    fixScrollPositionByNavbarOnMobile();
  }

  public void tiggerIncontentPlayerOnOasis() {
    scrollToPosition(SECOND_ARTICLE_HEADER_OASIS);
    fixScrollPositionByNavbar();
  }
}
