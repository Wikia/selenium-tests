package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public abstract class DetachedWindow extends BasePageObject {

  protected void gainFocus(String title) {
    if(!driver.getTitle().startsWith(title)) {
      switchToWindowWithTitle(title);
    }
  }

  public void loseFocus() {
    switchToMainWindow();
  }

}
