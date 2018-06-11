package com.wikia.webdriver.elements.mercury.old.curatedcontent;

import com.wikia.webdriver.common.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class CuratedMainPagePageObject extends BasePageObject {

  public float getElementOffsetTop(String element) {
    return Float.parseFloat(driver
        .executeScript("var el = document.querySelector(arguments[0]), boundingClientRect = el.getBoundingClientRect(); return boundingClientRect && boundingClientRect.top + document.body.scrollTop;", element)
        .toString());
  }

  public boolean isCuratedElementVisible(String element) {
    try {
      wait.forElementVisible(By.cssSelector(element));
    } catch (TimeoutException | ElementNotVisibleException ex) {
      Log.log("Web element " + element +" not visible", ex, true);
      return false;
    }
    return true;
  }
}
