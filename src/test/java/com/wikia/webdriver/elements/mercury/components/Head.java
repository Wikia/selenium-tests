package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Head extends WikiBasePageObject {

  @FindBy(css = "head title")
  private WebElement documentTitle;

  public String getDocumentTitle() {
    return (String) jsActions.execute("return arguments[0].innerText", documentTitle);
  }
}
