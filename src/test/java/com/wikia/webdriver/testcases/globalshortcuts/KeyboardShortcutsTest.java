package com.wikia.webdriver.testcases.globalshortcuts;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.BrowserType;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.KeyboardShortcutsModal;
import com.wikia.webdriver.elements.oasis.components.wikiabar.WikiaBar;

@Execute(onWikia = "globalshortcuts-en")
@InBrowser(browser = BrowserType.CHROME)
public class KeyboardShortcutsTest extends NewTestTemplate {

  private KeyboardShortcutsModal keyboardShortcutsModal;
  private WikiaBar wikiaBar;

  private void init() {
    this.keyboardShortcutsModal = new KeyboardShortcutsModal(driver);
    this.wikiaBar = new WikiaBar(driver);

    new Navigate(driver).toPage("/wiki/Globalshortcuts-en_Wikia");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton")
  @Execute(asUser = User.USER)
  public void globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton() {
    init();

    wikiaBar.clickOnShortcutsLink();
    keyboardShortcutsModal.clickCloseButton();
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts")
  public void globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut("ESC");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut")
  public void globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut("gi");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut")
  public void globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut("gs");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut")
  public void globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut(".");
  }
}
