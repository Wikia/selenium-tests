package com.wikia.webdriver.pageobjectsfactory.pageobject.special.licensedvideoswap;

/**
 * Created by kenkouot on 3/19/14.
 */

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LicensedVideoSwapPageObject extends SpecialPageObject {

  @FindBy(css = ".lvs-history-btn")
  private WebElement lvsHistoryBtn;
  @FindBy(css = ".subtitle a")
  private WebElement backLink;
  @FindBy(css = ".swap-button")
  private WebElement firstSwapButton;
  @FindBy(css = ".count")
  private WebElement swapCount;

  public LicensedVideoSwapPageObject(WebDriver driver) {
    super(driver);
  }

  public LicensedVideoSwapHistoryPageObject navigateToHistoryPage() {
    // Make sure the button has been drawn
    wait.forElementVisible(lvsHistoryBtn);
    lvsHistoryBtn.click();

    // Make sure the click above has happened and the browser has responded by looking for an element
    // on the history page
    wait.forElementVisible(backLink);
    LOG.log("navigateToHistoryPage",
            "lvs history button navigates to right page", LOG.Type.SUCCESS);

    return new LicensedVideoSwapHistoryPageObject(driver);
  }

  public void verifyOnLvsPage() {
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains(URLsContent.SPECIAL_LICENSED_VIDEO_SWAP));
    LOG.log("verifyOnLvsPage", "url is the correct one for LVS page", LOG.Type.SUCCESS);
  }

  public void verifySwapVideo() {
    // Make sure the element is on page before trying to retrieve it
    wait.forElementVisible(swapCount);
    int initialCount = Integer.parseInt(swapCount.getText());

    // Swap the first video
    wait.forElementVisible(firstSwapButton);
    scrollAndClick(firstSwapButton);

    wait.forTextInElement(swapCount, String.valueOf(initialCount - 1));

    LOG.log("verifyClickSwap", "Swap button has been clicked", LOG.Type.SUCCESS);
  }
}

