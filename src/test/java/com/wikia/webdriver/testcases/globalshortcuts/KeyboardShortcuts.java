package com.wikia.webdriver.testcases.globalshortcuts;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.KeyboardShortcutsModal;
import com.wikia.webdriver.elements.oasis.components.wikiabar.WikiaBar;

import org.testng.annotations.Test;

@Execute(onWikia = "globalshortcuts-en")
@InBrowser(browser = Browser.CHROME)
public class KeyboardShortcuts extends NewTestTemplate {

  private KeyboardShortcutsModal keyboardShortcutsModal;
  private WikiaBar wikiaBar;

  private void init() {
    this.keyboardShortcutsModal = new KeyboardShortcutsModal(driver);
    this.wikiaBar = new WikiaBar(driver);

    new Navigate(driver).toPage("/wiki/Globalshortcuts-en_Wikia");
  }

  @Test()
  @Execute(asUser = User.USER)
  public void globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton() {
    init();

    wikiaBar.clickOnShortcutsLink();
    keyboardShortcutsModal.clickCloseButton();
  }

  @Test()
  public void globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut("ESC");
  }

  @Test()
  public void globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut("gi");
  }

  @Test()
  public void globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut("gs");
  }

  @Test()
  public void globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut() {
    init();

    keyboardShortcutsModal.useShortcut("?");
    keyboardShortcutsModal.useShortcut(".");
  }
}
