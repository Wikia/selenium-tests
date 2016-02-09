package com.wikia.webdriver.elements.oasis.components.globalshortcuts;

import com.wikia.webdriver.common.core.Assertion;
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
  private WebDriver driver;

  private static final String EXPECTED_URL_FOR_SEARCH =
      "http://globalshortcuts-en.wikia.com/wiki/Special:Search?search=wikia&fulltext=Search";
  private static final String EXPECTED_URL_FOR_INSIGHTS =
      "http://globalshortcuts-en.wikia.com/wiki/Special:Insights";

  public KeyboardShortcutModal(WebDriver driver) {
    this.wait = new Wait(driver);
    this.actions = new Actions(driver);
    this.driver = driver;

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

    Assertion.assertTrue(driver.getCurrentUrl().equals(EXPECTED_URL_FOR_INSIGHTS),
                         "You were not redirected to insights list page");
    PageObjectLogging.logInfo("You were redirected to insights list page");

    return this;
  }

  public KeyboardShortcutModal searchForPageShortcut(){
    actions.sendKeys("gs").perform();

    PageObjectLogging.logInfo("The focus is in the search box");

    return this;
  }

  public KeyboardShortcutModal writeAndRedirect(){
    String searchQuery = "wikia";

    actions.sendKeys(searchQuery).perform();
    PageObjectLogging.logInfo("Typed in a search box: " + searchQuery);

    actions.sendKeys(Keys.ENTER).perform();
    Assertion.assertTrue(driver.getCurrentUrl().equals(EXPECTED_URL_FOR_SEARCH),
                         "You were not redirected anywhere");
    PageObjectLogging.logInfo("You were redirected to the search page for the word: " + searchQuery);

    return this;
  }

  public KeyboardShortcutModal visibilityOfModal() {
    wait.forElementClickable(keyboardShortcutModal);
    PageObjectLogging.logInfo("Keyboard shortcut modal is visible");

    return this;
  }
}
