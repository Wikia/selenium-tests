package com.wikia.webdriver.testcases.globalshortcuts;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.ActionExplorerModal;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.EntryPointShortcuts;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.KeyboardShortcutModal;

import org.testng.annotations.Test;

@Execute(asUser = User.USER, onWikia = "globalshortcuts-en")
public class Editing extends NewTestTemplate{

  private EntryPointShortcuts entryPointShortcuts;
  private KeyboardShortcutModal keyboardShortcutModal;
  private ActionExplorerModal actionExplorerModal;

  private void init() {
    this.entryPointShortcuts = new EntryPointShortcuts(driver);
    this.keyboardShortcutModal = new KeyboardShortcutModal(driver);
    this.actionExplorerModal = new ActionExplorerModal(driver);
  }

  @Test()
  public void openCloseKeyboardShortcut() {
    init();

    entryPointShortcuts.openKeyboardShortcutModal();
    keyboardShortcutModal.closeKeyboardShortcutModal();

  }
  @Test()
  public void openCloseKeyboardShortcutsWithShortcuts() {
    init();

    keyboardShortcutModal.openKeyboardShortcutModalWithShortcut();
    keyboardShortcutModal.escKeyboardShortcutModal();

  }

  @Test()
  public void openCloseActionExplorer() {
    init();

    actionExplorerModal.openActionExplorerWithShortcut();
    actionExplorerModal.closeActionExplorerModal();
  }

  @Test()
  public void openActionExplorerOverKeyboardShortcut(){
    init();

    keyboardShortcutModal.openKeyboardShortcutModalWithShortcut();
    actionExplorerModal.openActionExplorerWithShortcut();
    actionExplorerModal.checkVisibility();
  }
}
