package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialManageWikiaHome extends WikiBasePageObject {

  private static final String SPECIAL_MANAGE_WIKIA_HOME_PATH = "/wiki/Special:ManageWikiaHome";

  @FindBy(id = "video-games-amount")
  private WebElement videoGamesAmount;
  @FindBy(id = "entertainment-amount")
  private WebElement entertainmentAmount;
  @FindBy(id = "lifestyle-amount")
  private WebElement lifestyleAmount;

  public SpecialManageWikiaHome open() {
    getUrl(urlBuilder.getUrlForPath("wikia", SPECIAL_MANAGE_WIKIA_HOME_PATH));
    PageObjectLogging.log("openCorpSetupHomePage", "Special:ManageWikiaHome opened", true);

    return this;
  }

  /**
   * getting desired slot setup from http://corp.wikia.com/wiki/Special:ManageWikiaHome page
   */
  public Map<String, Integer> getSlotSetup() {
    Map<String, Integer> slotSetup = new HashMap<String, Integer>();
    slotSetup.put(HubName.VIDEO_GAMES.toString(),
        Integer.parseInt(videoGamesAmount.getAttribute("value")));
    slotSetup.put(HubName.ENTERTAINMENT.toString(),
        Integer.parseInt(entertainmentAmount.getAttribute("value")));
    slotSetup.put(HubName.LIFESTYLE.toString(),
        Integer.parseInt(lifestyleAmount.getAttribute("value")));
    return slotSetup;
  }
}
