/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchPageObject extends BasePageObject {

  @FindBy(css = "[value=OK]")
  private WebElement followUnfollowConfirmation;

  public WatchPageObject() {
    super();
  }

  public void confirmWatchUnwatch() {
    jsActions.scrollElementIntoViewPort(followUnfollowConfirmation);
    followUnfollowConfirmation.click();
    Log.log("confirmWatchUnwatch", "follow/unfollow confirmation button clicked", true);
  }
}
