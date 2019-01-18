package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;


public class SpamWikiReviewPage extends BasePageObject {

  private static final String SPAM_WIKI_REVIEW_SERVICE_NAME = "spam-wiki-review";
  private static final String VIEW_WIKIS_SUBPAGE = "/wiki";

  public SpamWikiReviewPage open() {
    getUrl(urlBuilder.getUrlForService("spam-wiki-review")+VIEW_WIKIS_SUBPAGE);
    return this;
  }
}
