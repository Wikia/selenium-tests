package com.wikia.webdriver.pageobjectsfactory.pageobject.communitypage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SpecialCommunity extends WikiBasePageObject {
  private boolean isOpen = false;
  public static final String COMMUNITY_PAGE_URL = "/Special:Community";

  @FindBy(css = ".community-page-card-module .community-page-card-module-list a")
  private List<WebElement> cardsLinks;

  public boolean isCommunityPageOpen() {
    return isStringInURL(COMMUNITY_PAGE_URL);
  }

  public SpecialCommunity open() {
    getUrl(urlBuilder.getUrlForPage(COMMUNITY_PAGE_URL));
    this.isOpen = isCommunityPageOpen();

    return this;
  }

  public WebElement getLinkFromCards() {
    this.lazyOpen();

    return cardsLinks.stream().findAny().get();
  }

  private void lazyOpen() {
    if (!this.isOpen) {
      open();
    }
  }
}
