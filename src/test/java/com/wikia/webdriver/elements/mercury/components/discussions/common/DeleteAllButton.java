package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DeleteAllButton extends BasePageObject {

  @FindBy(className = "delete-all")
  private WebElement button;

  // context of DeleteAllButton in which it should be displayed
  @FindBy(css = ".discussion-page.row > div:nth-child(3).rail")
  private WebElement rightRail;

  public boolean isVisible() {
    try {
      return button.isDisplayed();
    } catch (NoSuchElementException e) {
      System.err.println(e.toString());
      return false;
    }
  }

  public boolean isNotVisible() {
    try {
      return rightRail.isEnabled() && wait.forElementNotVisible(button);
    } catch (TimeoutException e) {
      System.err.println(e.toString());
      return false;
    }
  }

  public DeleteDialog click() {
    wait.forElementVisible(button).click();
    return new DeleteDialog();
  }

}
