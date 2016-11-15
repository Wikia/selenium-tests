package com.wikia.webdriver.elements.mercury.pages.discussions;


import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Header;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiscussionsPage extends WikiBasePageObject {

  @Getter(lazy = true)
  private final Header header = new Header();

  @Getter(lazy = true)
  private final Navigate navigate = new Navigate();

  @Getter(lazy = true)
  private final Navigation navigation = new Navigation(driver);

  @Getter(lazy = true)
  private final TopBar topbar = new TopBar(driver);

  @FindBy(css = ".wikia-home-link")
  private WebElement wikiaHomeLink;

  public DiscussionsPage() {
    super();
    getNavigate().toPage(PageContent.DISCUSSIONS_LINK);
  }

  public boolean isWikiaHomeLinkDisplayed() {
    try {
      wait.forElementVisible(wikiaHomeLink);

      return wikiaHomeLink.isDisplayed();
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Wikia home link is not displayed", e);

      return false;
    }
  }

}
