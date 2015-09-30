/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class WatchPageObject extends BasePageObject {


  @FindBy(css = "[value=OK]")
  private WebElement followUnfollowConfirmation;

  public WatchPageObject(WebDriver driver) {
    super(driver);
  }

  public void confirmWatchUnwatch() {
    followUnfollowConfirmation.click();
    LOG
        .logResult("confirmWatchUnwatch", "follow/unfollow confirmation button clicked", true);
  }


}
