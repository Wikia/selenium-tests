package com.wikia.webdriver.elements.communities.desktop.components.globalshortcuts;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class KeyboardShortcutsModal extends WikiBasePageObject {

  private static final String INSIGHTS_PAGE = "/wiki/Special:Insights";
  private static final String INSIGHTS_PAGE_SZL = "/wiki/Specjalna:Podpowiedzi";

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
    Log.info("Keyboard shortcuts modal was closed by close button");

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
        throw new IllegalArgumentException("Unrecognised shortcut");
    }

    return this;
  }

  public KeyboardShortcutsModal useShortcutSzl(String shortcut) {
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
        triggerGIShortcutSzl();
        break;
      case "gs":
        triggerGSShortcut();
        break;
      default:
        throw new IllegalArgumentException("Unrecognised shortcut");
    }

    return this;
  }
  private KeyboardShortcutsModal triggerDotShortcut() {
    actions.sendKeys(".").perform();

    wait.forElementVisible(actionExplorerModalSelector);
    Log.info("Action Explorer modal was opened by . button");

    return this;
  }

  private KeyboardShortcutsModal triggerEscapeShortcut() {
    actions.sendKeys(Keys.ESCAPE).perform();

    wait.forElementNotVisible(keyboardShortcutsModalSelector);
    Log.info("Keyboard shortcuts modal was closed by ESC keyboard button");

    return this;
  }

  private KeyboardShortcutsModal triggerQuestionMarkShortcut() {
    actions.keyDown(Keys.LEFT_SHIFT).pause(1000).sendKeys("?").keyUp(Keys.LEFT_SHIFT).perform();

    wait.forElementVisible(keyboardShortcutsModalSelector);
    Log.info("Keyboard shortcuts modal was opened by ? keyboard button");

    return this;
  }

  private KeyboardShortcutsModal triggerGIShortcut() {
    actions.sendKeys("gi").perform();

    Assertion.assertTrue(
        driver.getCurrentUrl().contains(INSIGHTS_PAGE),
        "You were not redirected to Insights page by gi keyboard shortcut"
    );
    Log.info("You were redirected to Insights page by gi keyboard shortcut");

    return this;
  }

  private KeyboardShortcutsModal triggerGIShortcutSzl() {
    actions.sendKeys("gi").perform();

    Assertion.assertStringContains(driver.getCurrentUrl(),INSIGHTS_PAGE_SZL);
    Log.info("You were redirected to Insights page by gi keyboard shortcut");

    return this;
  }


  private KeyboardShortcutsModal triggerGSShortcut() {
    actions.sendKeys("gs").perform();

    Assertion.assertTrue(
        globalNavigationSearchInput.equals(driver.switchTo().activeElement()),
        "Global navigation search input is not focused by gs keyboard shortcut"
    );
    Log.info("Global navigation search input was focused by gs keyboard shortcut");

    Assertion.assertTrue(
        globalNavigationSearchInput.getAttribute("value").isEmpty(),
        "Global navigation search input is not empty"
    );
    Log.info("Global navigation search input is empty");

    return this;
  }
}
