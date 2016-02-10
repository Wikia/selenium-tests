package com.wikia.webdriver.elements.oasis.components.wikiabar;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiaBar {

  @FindBy(css = ".wikia-bar .global-shortcuts-help-entry-point")
  private WebElement shortcutsLink;

  private By keyboardShortcutsModalSelector = By.cssSelector(".global-shortcuts-help");

  private Wait wait;

  public WikiaBar(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public WikiaBar clickOnShortcutsLink() {
    wait.forElementClickable(shortcutsLink);
    shortcutsLink.click();
    PageObjectLogging.logInfo("Click on shortcuts link in Wikia bar");

    wait.forElementVisible(keyboardShortcutsModalSelector);
    PageObjectLogging.logInfo("Keyboard shortcuts modal was opened");

    return this;
  }
}
