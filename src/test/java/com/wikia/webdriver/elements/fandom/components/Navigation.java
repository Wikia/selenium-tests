package com.wikia.webdriver.elements.fandom.components;

import com.wikia.webdriver.elements.fandom.pages.HubPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ludwik on 2016-07-29.
 */
public class Navigation extends BasePageObject {

  @FindBy(css = ".games.nav-links-item")
  private WebElement gamesHubButton;

  public HubPage openGamesHub() {
    gamesHubButton.click();

    return new HubPage();
  }

}