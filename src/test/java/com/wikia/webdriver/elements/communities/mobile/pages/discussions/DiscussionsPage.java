package com.wikia.webdriver.elements.communities.mobile.pages.discussions;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.components.navigation.global.GlobalNavigationMobile;
import com.wikia.webdriver.elements.communities.mobile.components.Header;

import lombok.Getter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiscussionsPage extends BasePage {

  @Getter(lazy = true)
  private final Header header = new Header();

  @Getter(lazy = true)
  private final Navigate navigate = new Navigate();

  @Getter(lazy = true)
  private final GlobalNavigationMobile topbar = new GlobalNavigationMobile();

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
      Log.info("Sort By filter is not displayed", e);

      return false;
    }
  }

  public boolean isDiscussionsPresent() {
    return discussionsBody.isDisplayed();
  }
}
