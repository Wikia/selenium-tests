package com.wikia.webdriver.elements.mercury.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Moderation extends BasePageObject {

  @FindBy(className = "moderation-checkbox-label")
  private WebElement showOnlyReportedContentCheckbox;

  public Moderation checkShowOnlyReportedContentCheckbox() {
    showOnlyReportedContentCheckbox.click();
    return this;
  }
}
