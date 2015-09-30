package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class FacebookSettingsPageObject extends WikiBasePageObject {

  @FindBy(css = "#pageLogo")
  private WebElement pageLogo;
  @FindBy(css = "#pop_content .uiButtonConfirm")
  private WebElement removeButton;
  @FindBy(css = ".pop_container_advanced")
  private WebElement removeAppConfirmationModal;
  @FindBy(css = "._4bl7")
  private List<WebElement> pageElementList;
  @FindBy(css = "#userNavigationLabel")
  private WebElement fbDropDown;
  @FindBy(xpath = "//span[text()='Log out']")
  private WebElement fbLogOut;

  public FacebookSettingsPageObject(WebDriver driver) {
    super(driver);
  }

  public FacebookSettingsPageObject open() {
    getUrl(URLsContent.FACEBOOK_SETTINGS_APP_TAB);

    return this;
  }

  public void verifyPageLogo() {
    wait.forElementVisible(pageLogo);
    LOG.log("verifyPageLogo", "Page logo is present", LOG.Type.SUCCESS);
  }

  /**
   * This method removes Wikia App from facebook Apps.
   */
  public FacebookSettingsPageObject removeAppIfPresent() {
    if (isAppPresent()) {
      for (WebElement element : pageElementList) {
        if (element.getText().toString().matches("^Wikia.*\n?.*")) {
          wait.forElementVisible(element);
          element.click();
          WebElement AppRemoveButton =
              element.findElement(By.xpath("//a[contains(text(), 'Remove')]"));
          if (AppRemoveButton != null) {
            wait.forElementVisible(AppRemoveButton);
            AppRemoveButton.click();
            wait.forElementVisible(removeButton);
            removeButton.click();
            waitForElementNotVisibleByElement(removeAppConfirmationModal);
            driver.navigate().refresh();
            try {
              Thread.sleep(3000);
            } catch (InterruptedException e) {
              LOG.log("SLEEP INTERRUPTED", e, LOG.Type.ERROR);
            }

            LOG.log("removeApp", "Wikia App removed", LOG.Type.SUCCESS);
          }
        } else {
          LOG.log("removeApp", "Wikia App not found", LOG.Type.SUCCESS);
        }
      }
    }
    return this;
  }

  /**
   * This method verifies if App is present on facebook apps list. It searches for Wikia string.
   */
  private boolean isAppPresent() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      LOG.log("isAppPresent", e, LOG.Type.ERROR);
    }
    boolean isPresent = false;
    for (WebElement element : pageElementList) {
      if (element.getText().toString().matches("^Wikia.*\n?.*")) {
        isPresent = true;
      }
    }
    return isPresent;
  }

  public void logOutFB() {
    wait.forElementVisible(fbDropDown);
    fbDropDown.click();
    wait.forElementVisible(fbLogOut);
    fbLogOut.click();
  }
}
