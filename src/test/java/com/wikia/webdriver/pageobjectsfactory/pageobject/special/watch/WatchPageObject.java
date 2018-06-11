/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch;

import com.wikia.webdriver.common.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class WatchPageObject extends BasePageObject {

  @FindBy(css = "[value=ok]")
  private WebElement followUnfollowConfirmation;

  public WatchPageObject() {
    super();
  }

  public void confirmWatchUnwatch() {
    jsActions.scrollElementIntoViewPort(followUnfollowConfirmation);
    followUnfollowConfirmation.click();
    Log.log("confirmWatchUnwatch", "follow/unfollow confirmation button clicked",
        true);
  }
}
