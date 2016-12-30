package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DeleteAllButton extends BasePageObject {

  @FindBy(className = "delete-all")
  private WebElement button;

  public boolean isVisible() {
    return button != null && button.isDisplayed();
  }

  public DeleteDialog click() {
    button.click();
    return new DeleteDialog();
  }

}
