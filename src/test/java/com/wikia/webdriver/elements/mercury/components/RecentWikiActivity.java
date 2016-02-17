package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecentWikiActivity extends WikiBasePageObject {

  @FindBy(css = ".recent-change__see-diff")
  private WebElement seeDiffArrow;

  private Loading loading;

  public RecentWikiActivity() {
    super();

    this.loading = new Loading(driver);
  }

  public RecentWikiActivity openDiffPage() {
    wait.forElementClickable(seeDiffArrow);

    String urlToBeRedirected = seeDiffArrow.getAttribute("href");
    seeDiffArrow.click();

    loading.handleAsyncPageReload();

    PageObjectLogging.logInfo("The arrow redirecting to the diff page was clicked");

    Assertion.assertTrue(driver.getCurrentUrl().contains(urlToBeRedirected),
                         "You were not redirected to the diff page");
    PageObjectLogging.logInfo("You were redirected to the diff page");

    return this;
  }
}
