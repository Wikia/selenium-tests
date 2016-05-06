package com.wikia.webdriver.testcases.globalshortcuts;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;

@Execute(asUser = User.USER, onWikia = "globalshortcuts-en")
@InBrowser(browser = Browser.CHROME)
public class KeyboardShortcutsTest extends NewTestTemplate {

  @Test(groups = "globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton")
  public void globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton() {
    new HomePageObject()
        .open()
        .getWikiaBar()
        .clickOnShortcutsLink()
        .clickCloseButton();
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts")
  public void globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts() {
    new HomePageObject()
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut("ESC");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut")
  public void globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut() {
    new HomePageObject()
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut("gi");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut")
  public void globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut() {
    new HomePageObject()
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut("gs");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut")
  public void globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut() {
    new HomePageObject()
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut(".");
  }
}
