package com.wikia.webdriver.elements.oasis.components.wikiabar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.KeyboardShortcutsModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class WikiaBar extends WikiBasePageObject{

  @FindBy(css = ".wikia-bar .global-shortcuts-help-entry-point")
  private WebElement shortcutsLink;

  private By keyboardShortcutsModalSelector = By.cssSelector(".global-shortcuts-help");

  public WikiaBar() {
   super();
  }

  public KeyboardShortcutsModal clickOnShortcutsLink() {
    wait.forElementClickable(shortcutsLink);
    shortcutsLink.click();
    PageObjectLogging.logInfo("Click on shortcuts link in Wikia bar");

    wait.forElementVisible(keyboardShortcutsModalSelector);
    PageObjectLogging.logInfo("Keyboard shortcuts modal was opened");

    return new KeyboardShortcutsModal();
  }
}
