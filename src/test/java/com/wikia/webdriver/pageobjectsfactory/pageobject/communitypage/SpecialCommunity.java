package com.wikia.webdriver.pageobjectsfactory.pageobject.communitypage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialCommunity extends WikiBasePageObject {

  public static final String COMMUNITY_PAGE_URL = "Special:Community";

  public SpecialCommunity() { super(); }

  public boolean isCommunityPageOpen() {
    return isStringInURL(COMMUNITY_PAGE_URL);
  }
}