package com.wikia.webdriver.elements.oasis.components.globalshortcuts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class KeyboardShortcutsModal extends WikiBasePageObject {

  private static final String INSIGHTS_PAGE = "/wiki/Special:Insights";
  @FindBy(css = ".global-shortcuts-help .close")
  private WebElement closeButton;
  @FindBy(css = ".wds-global-navigation__search-input")
  private WebElement globalNavigationSearchInput;
  private By keyboardShortcutsModalSelector = By.cssSelector(".global-shortcuts-help");
  private By actionExplorerModalSelector = By.cssSelector(".global-shortcuts-search");
  private Actions actions;

  public KeyboardShortcutsModal() {
    super();

    this.actions = new Actions(driver);
  }

  public KeyboardShortcutsModal clickCloseButton() {
    wait.forElementClickable(closeButton);
    closeButton.click();

    wait.forElementNotVisible(keyboardShortcutsModalSelector);
    PageObjectLogging.logInfo("Keyboard shortcuts modal was closed by close button");

    return this;
  }

  public KeyboardShortcutsModal useShortcut(String shortcut) {
    switch (shortcut) {
      case ".":
        triggerDotShortcut();
        break;
      case "ESC":
        triggerEscapeShortcut();
        break;
      case "?":
        triggerQuestionMarkShortcut();
        break;
      case "gi":
        triggerGIShortcut();
        break;
      case "gs":
        triggerGSShortcut();
        break;
      default:
        break;
    }

    return this;
  }

  private KeyboardShortcutsModal triggerDotShortcut() {
    actions.sendKeys(".").perform();

    wait.forElementVisible(actionExplorerModalSelector);
    PageObjectLogging.logInfo("Action Explorer modal was opened by . button");

    return this;
  }

  private KeyboardShortcutsModal triggerEscapeShortcut() {
    actions.sendKeys(Keys.ESCAPE).perform();

    wait.forElementNotVisible(keyboardShortcutsModalSelector);
    PageObjectLogging.logInfo("Keyboard shortcuts modal was closed by ESC keyboard button");

    return this;
  }

  private KeyboardShortcutsModal triggerQuestionMarkShortcut() {
    actions.sendKeys("?").perform();

    wait.forElementVisible(keyboardShortcutsModalSelector);
    PageObjectLogging.logInfo("Keyboard shortcuts modal was opened by ? keyboard button");

    return this;
  }

  private KeyboardShortcutsModal triggerGIShortcut() {
    actions.sendKeys("gi").perform();

    Assertion.assertTrue(driver.getCurrentUrl().contains(INSIGHTS_PAGE),
        "You were not redirected to Insights page by gi keyboard shortcut");
    PageObjectLogging.logInfo("You were redirected to Insights page by gi keyboard shortcut");

    return this;
  }

  private KeyboardShortcutsModal triggerGSShortcut() {
    actions.sendKeys("gs").perform();

    Assertion.assertTrue(globalNavigationSearchInput.equals(driver.switchTo().activeElement()),
        "Global navigation search input is not focused by gs keyboard shortcut");
    PageObjectLogging.logInfo("Global navigation search input was focused by gs keyboard shortcut");

    Assertion.assertTrue(globalNavigationSearchInput.getAttribute("value").isEmpty(),
        "Global navigation search input is not empty");
    PageObjectLogging.logInfo("Global navigation search input is empty");

    return this;
  }
}
