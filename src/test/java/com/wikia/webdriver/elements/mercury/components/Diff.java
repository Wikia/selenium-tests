package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Diff extends WikiBasePageObject {

  @FindBy(css = ".sub-head--cancel")
  private WebElement backArrow;

  private Loading loading;

  public Diff() {
    super();

    this.loading = new Loading(driver);
  }

  public Diff goBackToRWA() {
    wait.forElementClickable(backArrow);
    backArrow.click();

    loading.handleAsyncPageReload();

    PageObjectLogging.logInfo("The arrow that redirect user back to RWA was clicked");

    Assertion.assertTrue(driver.getCurrentUrl().contains(URLsContent.RECENT_WIKI_ACTIVITY),
                         "You were not redirected to the recent wiki activity");
    PageObjectLogging.logInfo("You were redirected to the recent wiki activity");

    return this;
  }
}
