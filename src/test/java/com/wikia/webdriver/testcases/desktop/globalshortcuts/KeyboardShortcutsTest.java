package com.wikia.webdriver.testcases.desktop.globalshortcuts;

import com.wikia.webdriver.common.core.annotations.*;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;

import org.testng.annotations.Test;

@Execute(asUser = User.USER, onWikia = "globalshortcuts-en", mockAds = "true")
@InBrowser(browser = Browser.CHROME)
@Test(groups = {"globalShortcuts"})
public class KeyboardShortcutsTest extends NewTestTemplate {
  // refresh() cause insights sometimes don't load on first time

  @Test(groups = "globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton")
  public void globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton() {
    new HomePage().open()
        .refresh()
        .getWikiaBar()
        .ensureBarIsNotCollapsed()
        .clickOnShortcutsLink()
        .clickCloseButton();
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts")
  public void globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts() {
    new HomePage().open().refresh().getKeyboardShortcuts().useShortcut("?").useShortcut("ESC");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut")
  public void globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut() {
    new HomePage().open().refresh().getKeyboardShortcuts().useShortcut("?").useShortcut("gi");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut")
  public void globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut() {
    new HomePage().open().refresh().getKeyboardShortcuts().useShortcut("?").useShortcut("gs");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut")
  public void globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut() {
    new HomePage().open().refresh().getKeyboardShortcuts().useShortcut("?").useShortcut(".");
  }
}
