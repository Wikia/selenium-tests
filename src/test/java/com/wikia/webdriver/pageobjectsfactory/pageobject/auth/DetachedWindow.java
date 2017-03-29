package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public abstract class DetachedWindow extends BasePageObject {

  private void gainFocus(String title) {
    if(!driver.getTitle().startsWith(title)) {
      switchToWindowWithTitle(title);
    }
  }

  private void loseFocus() {
    switchToMainWindow();
  }

}
