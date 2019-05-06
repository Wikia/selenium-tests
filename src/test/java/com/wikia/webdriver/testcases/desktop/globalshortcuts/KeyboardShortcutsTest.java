package com.wikia.webdriver.testcases.desktop.globalshortcuts;

import com.wikia.webdriver.common.core.annotations.*;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;

import org.testng.annotations.Test;

@Execute(asUser = User.SUS_STAFF2, onWikia = "globalshortcuts-en", mockAds = "true")
@InBrowser(browser = Browser.CHROME)
@Test(groups = {"globalShortcuts"})
public class KeyboardShortcutsTest extends NewTestTemplate {

  @Test(groups = "globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton")
  public void globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton() {
    new HomePage().open()
        .getWikiaBar()
        .ensureBarIsNotCollapsed()
        .clickOnShortcutsLink()
        .clickCloseButton();
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts")
  public void globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts() {
    new HomePage().open().getKeyboardShortcuts().useShortcut("?").useShortcut("ESC");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut")
  @DontRun(language = "szl")
  public void globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut() {
    new HomePage().open().getKeyboardShortcuts().useShortcut("?").useShortcut("gi");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut")
  @RunOnly(language = "szl")
  public void globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcutSzl() {
    new HomePage().open().getKeyboardShortcuts().useShortcutSzl("?").useShortcutSzl("gi");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut")
  public void globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut() {
    new HomePage().open().getKeyboardShortcuts().useShortcut("?").useShortcut("gs");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut")
  public void globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut() {
    new HomePage().open().getKeyboardShortcuts().useShortcut("?").useShortcut(".");
  }
}
