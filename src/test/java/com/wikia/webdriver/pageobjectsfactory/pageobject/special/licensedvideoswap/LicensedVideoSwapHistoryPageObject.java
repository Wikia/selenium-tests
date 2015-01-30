package com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap;

/**
 * Created by kenkouot on 3/19/14.
 */

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LicensedVideoSwapHistoryPageObject extends SpecialPageObject {

  @FindBy(css = ".subtitle a")
  private WebElement backToLvsBtn;
  @FindBy(css = ".undo-link")
  private WebElement firstUndoLink;
  @FindBy(css = ".global-notification .msg")
  private WebElement notification;

  public LicensedVideoSwapHistoryPageObject(WebDriver driver) {
    super(driver);
  }

  public LicensedVideoSwapPageObject navigateToLvsPage() {
    waitForElementByElement(backToLvsBtn);
    backToLvsBtn.click();
    PageObjectLogging.log("navigateToLvsPage", "lvs back button navigates to lvs page", true);
    return new LicensedVideoSwapPageObject(driver);
  }

  public void verifyOnHistoryPage() {
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains("/History"));
    PageObjectLogging
        .log("verifyOnHistoryPage", "the url is the correct one for history page", true);
  }

  public void clickUndoSwapLink() {
    firstUndoLink.click();
    PageObjectLogging.log("clickUndoSwapLink", "undo link clicked", true);
  }

  public void verifyUndoSucceeded() {
    waitForElementByElement(notification);
    String notificationMsg = notification.getText();
    Assertion.assertEquals(notificationMsg, "You have restored the video to this list.");
    PageObjectLogging.log("verifyUndoSucceeded", "able to undo a swapped video", true);
  }
}

