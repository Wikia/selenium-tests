package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.elements.mercury.components.RecentWikiActivity;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public class RecentWikiActivityPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final RecentWikiActivity recentWikiActivity = new RecentWikiActivity();

  public RecentWikiActivityPage() {
    super();
  }

  public RecentWikiActivityPage open() {
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(), URLsContent.RECENT_WIKI_ACTIVITY));

    return this;
  }
}
