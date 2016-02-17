package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.RecentWikiActivity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public class RecentWikiActivityPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final RecentWikiActivity recentWikiActivity = new RecentWikiActivity();

  public RecentWikiActivityPage() {
    super();
  }

  public RecentWikiActivityPage open(){
    new Navigate(driver).toPage("/recent-wiki-activity");

    return this;
  }
}
