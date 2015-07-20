package com.wikia.webdriver.pageobjectsfactory.pageobject.special.preferences;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class PreferencesPageObject extends WikiBasePageObject {

  @FindBy(css = ".fb-disconnect")
  private WebElement facebookDisconnect;
  @FindBy(css = "#fbConnectPreferences .wikia-button-facebook")
  private WebElement fbConnect;
  @FindBy(css = "#preftoc li")
  private List<WebElement> tabs;
  @FindBy(css = "#mw-htmlform-email-me-v2 td.mw-input")
  private List<WebElement> emailMeSectionRows;
  @FindBy(css = ".mw-htmlform-submit")
  private WebElement saveButton;
  @FindBy(css = ".mw-prefs-buttons a")
  private WebElement restoreDefaultLink;
  @FindBy(css = ".global-notification.confirm")
  private WebElement saveNotfication;
  @FindBy(css = "#facebook #email")
  private WebElement facebookEmailInput;
  @FindBy(css = "#facebook #pass")
  private WebElement facebookPasswordInput;
  @FindBy(css = "#facebook input[name='login']")
  private WebElement facebookSubmitButton;
  @FindBy(css = "#mw-input-wpusenewrc")
  private WebElement useAdvancedRecentChangesCheckbox;

  public PreferencesPageObject(WebDriver driver) {
    super(driver);
  }

  public PreferencesPageObject selectTab(tabNames tab) {
    int tabNum = -1;
    switch (tab) {
      case INFO:
        tabNum = 0;
        tabs.get(tabNum).findElement(By.cssSelector("a")).click();
        break;
      case EMAIL:
        tabNum = 1;
        tabs.get(tabNum).findElement(By.cssSelector("a")).click();
        break;
      case EDITING:
        tabNum = 2;
        tabs.get(tabNum).findElement(By.cssSelector("a")).click();
        break;
      case UNDER:
        tabNum = 3;
        tabs.get(tabNum).findElement(By.cssSelector("a")).click();
        break;
      case FACEBOOK:
        tabNum = 4;
        tabs.get(tabNum).findElement(By.cssSelector("a")).click();
        break;
      default:
        throw new NoSuchElementException("Non-existing tab selected");
    }
    waitForValueToBePresentInElementsAttributeByElement(tabs.get(tabNum), "class", "selected");
    PageObjectLogging.log("selectTab", "tab " + tab.toString() + " selected", true);
    return this;
  }

  public void verifyEmailMeSection() {
    for (WebElement elem : emailMeSectionRows) {
      PageObjectLogging.log("verifyEmailSection", "verifying " + elem.getText(), true);
      Assertion.assertEquals(elem.findElement(By.cssSelector("input")).getAttribute("checked"), "true"
      );
    }
  }

  public void disconnectFromFacebook() {
    waitForElementByElement(facebookDisconnect);
    scrollAndClick(facebookDisconnect);
    waitForElementByElement(fbConnect);
    PageObjectLogging.log("disconnectFromFacebook", "account has been disconnected from Facebook",
                          true);
  }

  public PreferencesPageObject clickSaveButton() {
    waitForElementClickableByElement(saveButton);
    scrollAndClick(saveButton);
    PageObjectLogging.log("clickSaveButton", "Save button clicked", true);
    return new PreferencesPageObject(driver);
  }

  public void clickRestoreLink() {
    waitForElementClickableByElement(restoreDefaultLink);
    restoreDefaultLink.click();
    PageObjectLogging.log("clickRestoreLink", "Restore Deault Link clicked", true);
  }

  public void verifySaveNotification() {
    waitForElementVisibleByElement(saveNotfication);
    PageObjectLogging.log("verifySaveNotification", "Restore Deault Link clicked", true);
  }

  public void connectFacebook(String email, String password) {
    PageObjectLogging.log("connectFacebook", "Connecting FB via FB login dialog", true);

    waitForElementByElement(fbConnect);
    scrollAndClick(fbConnect);

    waitForNewWindow();
    Object[] windows = driver.getWindowHandles().toArray();
    driver.switchTo().window(windows[1].toString());

    waitForElementByElement(facebookEmailInput);
    facebookEmailInput.clear();
    facebookEmailInput.sendKeys(email);

    waitForElementByElement(facebookPasswordInput);
    facebookPasswordInput.clear();
    facebookPasswordInput.sendKeys(password);

    scrollAndClick(facebookSubmitButton);

    driver.switchTo().window(windows[0].toString());
    waitForElementByElement(facebookDisconnect);
  }

  public PreferencesPageObject setAdvancedRecentChangesCheckbox() {
    selectTab(PreferencesPageObject.tabNames.UNDER);
    waitForElementClickableByElement(useAdvancedRecentChangesCheckbox);
  useAdvancedRecentChangesCheckbox.click();
    PageObjectLogging.log("Use_advanced_recent_changes_checkbox", "Use_advanced_recent_changes_checkbox clicked", true);

  return this;
  }

  public boolean getAdvancedRecentChangesCheckboxValue() {
// Verify that the Get_advanced_recent_changes_checkbox_value is checked
    selectTab(PreferencesPageObject.tabNames.UNDER);
    return  useAdvancedRecentChangesCheckbox.getAttribute("checked") != null;
     }

  public PreferencesPageObject setAdvancedRecentChangesCheckboxValueToDefaultUnchecked() {
    selectTab(PreferencesPageObject.tabNames.UNDER);
    if(useAdvancedRecentChangesCheckbox.getAttribute("checked") != null) {// if Checked
      useAdvancedRecentChangesCheckbox.click();
    }
    clickSaveButton();
    PageObjectLogging.log("Set_advanced_recent_changes_checkbox_value_to_default_unchecked", "GSet_advanced_recent_changes_checkbox_value set to default unchecked", true);

    return this;
  }

  public enum tabNames {
    INFO, EMAIL, EDITING, UNDER, FACEBOOK
  }

}
