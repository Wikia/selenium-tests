package com.wikia.webdriver.testcases.globalshortcuts;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.oasis.components.globalshortcuts.ActionExplorerModal;

import org.testng.annotations.Test;

@Execute(onWikia = "globalshortcuts-en")
@InBrowser(browser = Browser.CHROME)
public class ActionExplorer extends NewTestTemplate {

  private ActionExplorerModal actionExplorerModal;

  private void init() {
    this.actionExplorerModal = new ActionExplorerModal(driver);

    new Navigate(driver).toPage("/wiki/Globalshortcuts-en_Wikia");
  }

  @Test()
  public void globalShortcuts_actionExplorer_openAndCloseModalByShortcuts() {
    init();

    actionExplorerModal.useShortcut(".");
    actionExplorerModal.useShortcut("ESC");
  }

  @Test()
  public void globalShortcuts_actionExplorer_openKeyboardShortcutsBySearch() {
    init();

    actionExplorerModal.useShortcut(".");
    actionExplorerModal.searchFor("Keyboard");
    actionExplorerModal.selectKeyboardShortcutsFromSearchSuggestions();
  }

  @Test()
  public void globalShortcuts_actionExplorer_openSpecialAllPagesFromAutocompleteSuggestions() {
    init();

    actionExplorerModal.useShortcut(".");
    actionExplorerModal.scrollToAllPagesLink();
    actionExplorerModal.openAllPagesLink();
  }
}
