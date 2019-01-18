package com.wikia.webdriver.elements.communities.desktop.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpamWikiReviewPage extends BasePageObject {

  private static final String SPAM_WIKI_REVIEW_SERVICE_NAME = "spam-wiki-review";
  private static final String VIEW_WIKIS_SUBPAGE = "/wiki";

  public static enum LANGUAGE_CODE {
    all, en, ar, de, es, fi, fr, id, hi, hu, it, ja, ka, kn, ko, nl, pt, pl, ru, uk, vi, zh, other
  }

  @FindBy(xpath = "//button[contains(text(),'Select language of Wikis')]")
  private WebElement selectLanguageButton;

  public SpamWikiReviewPage open() {
    getUrl(urlBuilder.getUrlForService("spam-wiki-review")+VIEW_WIKIS_SUBPAGE);
    return this;
  }

  public SpamWikiReviewPage selectLanguageOfWikis(LANGUAGE_CODE language){
    selectLanguageButton.click();
    return this;
  }

}
