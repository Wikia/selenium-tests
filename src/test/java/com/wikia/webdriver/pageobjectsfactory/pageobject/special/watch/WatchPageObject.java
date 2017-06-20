/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class WatchPageObject extends BasePageObject {

  @FindBy(css = "[value=OK]")
  private WebElement followUnfollowConfirmation;

  public WatchPageObject() {
    super();
  }

  public void confirmWatchUnwatch() {
    jsActions.scrollElementIntoViewPort(followUnfollowConfirmation);
    followUnfollowConfirmation.click();
    PageObjectLogging.log("confirmWatchUnwatch", "follow/unfollow confirmation button clicked",
        true);
  }
}
