package com.wikia.webdriver.elements.mercury.pages.discussions;


import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;

public class DiscussionsPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Header header = new Header();

  @Getter(lazy = true)
  private final Navigate navigate = new Navigate();

  @Getter(lazy = true)
  private final Navigation navigation = new Navigation(driver);

  @Getter(lazy = true)
  private final TopBar topbar = new TopBar(driver);

  public DiscussionsPage() {
    super();
    getNavigate().toPage(PageContent.DISCUSSIONS_LINK);
  }
}
