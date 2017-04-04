package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public abstract class DetachedWindow extends BasePageObject {

  protected void gainFocus() {
    String title = getTitle();
    if(!driver.getTitle().startsWith(title)) {
      switchToWindowWithTitle(title);
    }
  }

  protected void loseFocus() {
    String title = getTitle();
    if(driver.getTitle().startsWith(title)) {
      switchAwayFromWindowWithTitle(title);
    }
  }

  protected abstract String getTitle();

}
