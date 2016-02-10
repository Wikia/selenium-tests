package com.wikia.webdriver.testcases.globalshortcuts;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;

@Execute(onWikia = "globalshortcuts-en")
@InBrowser(browser = Browser.CHROME)
public class KeyboardShortcutsTest extends NewTestTemplate {

  @Test(groups = "globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton")
  @Execute(asUser = User.USER)
  public void globalShortcuts_keyboardShortcuts_openModalByLinkInWikiaBar_CloseModalByCloseButton() {
    new HomePageObject(driver)
        .open()
        .getWikiaBar()
        .clickOnShortcutsLink()
        .clickCloseButton();
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts")
  public void globalShortcuts_keyboardShortcuts_openAndCloseModalByShortcuts() {
    new HomePageObject(driver)
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut("ESC");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut")
  public void globalShortcuts_keyboardShortcuts_navigateToInsightsByShortcut() {
    new HomePageObject(driver)
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut("gi");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut")
  public void globalShortcuts_keyboardShortcuts_focusGlobalNavigationSearchByShortcut() {
    new HomePageObject(driver)
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut("gs");
  }

  @Test(groups = "globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut")
  public void globalShortcuts_keyboardShortcuts_openActionExplorerByKeyboardShortcut() {
    new HomePageObject(driver)
        .open()
        .getKeyboardShortcuts()
        .useShortcut("?")
        .useShortcut(".");
  }
}
