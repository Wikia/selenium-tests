package com.wikia.webdriver.testcases.globalshortcuts;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.ActionExplorerModal;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.KeyboardShortcutModal;

import org.testng.annotations.Test;

@Execute(onWikia = "globalshortcuts-en")
public class Navigating extends NewTestTemplate{

  private Navigate navigate;
  private KeyboardShortcutModal keyboardShortcutModal;
  private ActionExplorerModal actionExplorerModal;

  private void init() {
    this.navigate = new Navigate(driver);
    this.keyboardShortcutModal = new KeyboardShortcutModal(driver);
    this.actionExplorerModal = new ActionExplorerModal(driver);
  }

  @Test()
  public void usingKeyboardShortcut() {
    init();

    navigate.toPage("/wiki/global-shortcuts-en");
    keyboardShortcutModal.insightsShortcut();
  }

  @Test()
  public void searchForAPage() {
    init();

    keyboardShortcutModal.searchForPageShortcut();
    keyboardShortcutModal.writeAndRedirect();
  }

  @Test()
  public void searchBoxActionExplorerUsingKeyboard() {
    init();

    actionExplorerModal.openActionExplorerWithShortcut();
    actionExplorerModal.chooseWrittenShortcut();
    keyboardShortcutModal.visibilityOfModal();
  }

  @Test()
  public void actionExplorerUsingScrollingAndClicking() {
    init();

    actionExplorerModal.openActionExplorerWithShortcut();
    actionExplorerModal.scrollDown();
    actionExplorerModal.clickchosenShortcut();
  }
}
