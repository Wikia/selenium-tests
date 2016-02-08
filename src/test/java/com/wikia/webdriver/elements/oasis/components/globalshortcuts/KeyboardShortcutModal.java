package com.wikia.webdriver.elements.oasis.components.globalshortcuts;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KeyboardShortcutModal{

  @FindBy(css =".close")
  private WebElement close;
  @FindBy(css = "#GlobalShortcutsHelp")
  private WebElement keyboardShortcutModal;

  private Wait wait;
  private Actions actions;

  public KeyboardShortcutModal(WebDriver driver) {
    this.wait = new Wait(driver);
    this.actions = new Actions(driver);

    PageFactory.initElements(driver, this);
  }

  public KeyboardShortcutModal closeKeyboardShortcutModal() {
    wait.forElementClickable(close);
    close.click();

    PageObjectLogging.logInfo("Keyboard shortcut modal was closed");

    return this;
  }

  public  KeyboardShortcutModal escKeyboardShortcutModal() {
    wait.forElementVisible(close);
    actions.sendKeys(Keys.ESCAPE);

    PageObjectLogging.logInfo("Keyboard shortcut modal was closed by esc button");

    return this;
  }

  public KeyboardShortcutModal openKeyboardShortcutModalWithShortcut() {
    actions.sendKeys("?").perform();

    PageObjectLogging.logInfo("Keyboard shortcut modal was opened by ? button");

    return this;
  }

  public KeyboardShortcutModal insightsShortcut() {
    actions.sendKeys("gi").perform();

    PageObjectLogging.logInfo("You were redirected to insights list page");

    return this;
  }

  public KeyboardShortcutModal searchForPageShortcut(){
    actions.sendKeys("gs").perform();

    PageObjectLogging.logInfo("The focus is in the search box");

    return this;
  }

  public KeyboardShortcutModal writeAndRedirect(){
    actions.sendKeys("wikia").perform();
    PageObjectLogging.logInfo("wikia was written in a search box");

    actions.sendKeys(Keys.ENTER).perform();
    PageObjectLogging.logInfo("You were redirected to...");

    return this;
  }

  public KeyboardShortcutModal visibilityofModal() {
    wait.forElementClickable(keyboardShortcutModal);
    PageObjectLogging.logInfo("Keyboard shortcut modal is visible");

    return this;
  }
}
