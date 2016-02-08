package com.wikia.webdriver.elements.oasis.components.globalshortcuts;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActionExplorerModal {

  @FindBy(css = ".autocomplete-suggestions")
  private WebElement actionExplorer;

  @FindBy(css = ".label-in-suggestions")
  private WebElement keyboardShortcutShortcut;

  @FindBy(css = "#global_shortcuts_search_field")
  private WebElement searchField;

  @FindBy(xpath = "/html/body/div[6]/div/section/div[2]/div/div[17]/span[1]")
  private WebElement chosenShortcut;

  private Wait wait;
  private Actions actions;
  private JavascriptActions jsActions;

  public ActionExplorerModal(WebDriver driver) {
    this.wait = new Wait(driver);
    this.actions = new Actions(driver);
    this.jsActions = new JavascriptActions(driver);

    PageFactory.initElements(driver, this);
  }

  public ActionExplorerModal openActionExplorerWithShortcut() {
    actions.sendKeys(".").perform();

    PageObjectLogging.logInfo("Action Explorer modal was opened by . button");

    return this;
  }

  public ActionExplorerModal closeActionExplorerModal() {
    wait.forElementVisible(actionExplorer);
    actions.sendKeys(Keys.ESCAPE).perform();

    PageObjectLogging.logInfo("Action explorer was closed by esc button");

    return this;
  }

  public ActionExplorerModal checkVisibility() {
    wait.forElementVisible(actionExplorer);
    actionExplorer.isDisplayed();

    PageObjectLogging.logInfo("Action explorer visible");

    return this;
  }

  public ActionExplorerModal chooseWrittenShortcut() {
    wait.forElementVisible(searchField);
    actions.sendKeys("Keyboard").perform();

    PageObjectLogging.logInfo("Keyboard was written in search box");

    wait.forElementVisible(keyboardShortcutShortcut);
    actions.sendKeys(Keys.ENTER).perform();

    PageObjectLogging.logInfo("It was clicked on keyboard shortcuts help");

    return this;
  }

  public ActionExplorerModal scrollDown() {
    wait.forElementVisible(actionExplorer);
    jsActions.mouseOver(actionExplorer);
    jsActions.scrollToElement(chosenShortcut);

    PageObjectLogging.logInfo("Scrolled down");

    return this;
    }

  public ActionExplorerModal clickchosenShortcut() {
    wait.forElementClickable(chosenShortcut);
    chosenShortcut.click();

    PageObjectLogging.logInfo("Redirection to chosen Special Page");

    return this;
    }
}
