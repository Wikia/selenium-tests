package com.wikia.webdriver.pageobjectsfactory.pageobject.facebook;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
  @FindBy(css = ".fbSettingsList ")
  private WebElement settingsList;
  @FindBy(css = "._4bl7")
  private List<WebElement> pageElementList;

  public FacebookSettingsPageObject(WebDriver driver) {
    super(driver);
  }

  public void verifyPageLogo() {
    waitForElementByElement(pageLogo);
    PageObjectLogging.log("verifyPageLogo", "Page logo is present", true);
  }

  public void openApps() {
    appendToUrl(URLsContent.FACEBOOK_SETTINGS_APP_TAB);
    PageObjectLogging.log("openApps", "Apps tab opened", true);
  }

  /**
   * This method removes Wikia App from facebook Apps.
   */
  public void removeAppIfPresent() {
    if (isAppPresent()) {
      for (WebElement element : pageElementList) {
        if (element.getText().toString().matches("^Wikia.*\n?.*")) {
          waitForElementByElement(element);
          element.click();
          WebElement AppRemoveButton = element.findElement(By.xpath("//a[contains(text(), 'Remove')]"));
          if (AppRemoveButton != null) {
            waitForElementByElement(AppRemoveButton);
            AppRemoveButton.click();
            waitForElementNotVisibleByElement(removeAppConfirmationModal);
            waitForElementNotVisibleByElement(removeButton);
            removeButton.click();
            PageObjectLogging.log("removeApp", "Wikia App remove button clicked", true);
          }
        }
      }
    }
    PageObjectLogging.log("removeApp", "Wikia App removed", true);
  }

  /**
   * This method verifies if App is present on facebook apps list. It searches for Wikia string.
   */
  private boolean isAppPresent() {
    boolean isPresent = false;
    for (WebElement element : pageElementList) {
      if (element.getText().toString().matches("^Wikia.*\n?.*")) {
        isPresent = true;
      }
    }
    return isPresent;
  }
}
