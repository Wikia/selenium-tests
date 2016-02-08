package com.wikia.webdriver.elements.oasis.components.globalshortcuts;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EntryPointShortcuts {

  @FindBy(css = ".global-shortcuts-help-entry-point")
  private WebElement entryPointLink;

  private Wait wait;

  public EntryPointShortcuts(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public EntryPointShortcuts openKeyboardShortcutModal() {
    wait.forElementClickable(entryPointLink);
    entryPointLink.click();
    PageObjectLogging.logInfo("Keyboard shortcut modal was opened");

    return this;
  }
}
