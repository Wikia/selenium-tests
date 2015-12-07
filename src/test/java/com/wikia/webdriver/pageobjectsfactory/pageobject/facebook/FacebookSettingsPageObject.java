package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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
  @FindBy(xpath = "//span[text()='Log Out']")
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
    PageObjectLogging.log("verifyPageLogo", "Page logo is present", true);
  }

  /**
   * This method removes Wikia App from facebook Apps.
   */
  public FacebookSettingsPageObject removeAppIfPresent() {
    if (isAppPresent()) {
      for (WebElement element : pageElementList) {
        if (element.getText().toString().matches("^Wikia.*\n?.*")) {
          wait.forElementVisible(element);
          new Actions(driver).moveByOffset(200, 200).click().perform();
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
              PageObjectLogging.log("SLEEP INTERRUPTED", e, false);
            }

            PageObjectLogging.log("removeApp", "Wikia App removed", true);
          }
        } else {
          PageObjectLogging.log("removeApp", "Wikia App not found", true);
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
      PageObjectLogging.log("isAppPresent", e, false);
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
