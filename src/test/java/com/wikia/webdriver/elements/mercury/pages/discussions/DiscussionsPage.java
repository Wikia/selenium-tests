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
  private final Navigation navigation = new Navigation();

  @Getter(lazy = true)
  private final TopBar topbar = new TopBar();

  @FindBy(css = ".discussion-filter-header")
  private WebElement discussionsFilter;

  @FindBy(css = ".site-body-discussion")
  private WebElement discussionsBody;

  public DiscussionsPage() {
    super();
    getNavigate().toPageByPath(PageContent.DISCUSSIONS_LINK);
  }

  public boolean isDiscussionsFilterDisplayed() {
    try {
      wait.forElementVisible(discussionsFilter);

      return discussionsFilter.isDisplayed();
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo("Sort By filter is not displayed", e);

      return false;
    }
  }

  public boolean isDiscussions() {
    return discussionsBody.isDisplayed();
  }
}
