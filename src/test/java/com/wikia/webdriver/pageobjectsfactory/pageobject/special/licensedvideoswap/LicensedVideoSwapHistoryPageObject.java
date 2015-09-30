package com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap;

/**
 * Created by kenkouot on 3/19/14.
 */

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LicensedVideoSwapHistoryPageObject extends SpecialPageObject {

  @FindBy(css = ".subtitle a")
  private WebElement backToLvsBtn;
  @FindBy(css = ".undo-link")
  private WebElement firstUndoLink;
  @FindBy(css = ".banner-notifications-wrapper .msg")
  private WebElement notification;

  public LicensedVideoSwapHistoryPageObject(WebDriver driver) {
    super(driver);
  }

  public LicensedVideoSwapPageObject navigateToLvsPage() {
    wait.forElementVisible(backToLvsBtn);
    backToLvsBtn.click();
    LOG.log("navigateToLvsPage", "lvs back button navigates to lvs page", LOG.Type.SUCCESS);
    return new LicensedVideoSwapPageObject(driver);
  }

  public void verifyOnHistoryPage() {
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains("/History"));
    LOG
        .logResult("verifyOnHistoryPage", "the url is the correct one for history page", true);
  }

  public void clickUndoSwapLink() {
    firstUndoLink.click();
    LOG.log("clickUndoSwapLink", "undo link clicked", LOG.Type.SUCCESS);
  }

  public void verifyUndoSucceeded() {
    wait.forElementVisible(notification);
    String notificationMsg = notification.getText();
    Assertion.assertEquals(notificationMsg, "You have restored the video to this list.");
    LOG.log("verifyUndoSucceeded", "able to undo a swapped video", LOG.Type.SUCCESS);
  }
}

