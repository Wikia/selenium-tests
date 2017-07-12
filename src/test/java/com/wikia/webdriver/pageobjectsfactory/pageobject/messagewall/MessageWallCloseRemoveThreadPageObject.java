package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessageWallCloseRemoveThreadPageObject extends WikiBasePageObject {

  @FindBy(css = ".wall-action-reason")
  private WebElement removeReasonField;
  @FindBy(css = "#WikiaConfirmOk")
  private WebElement removeConfirmButton;

  public MessageWallCloseRemoveThreadPageObject(WebDriver driver) {
    super();
  }

  public MessageWall closeRemoveThread(String reason) {
    removeReasonField.sendKeys(reason);
    removeConfirmButton.click();
    wait.forElementNotPresent(By.cssSelector(modalWrapper));
    PageObjectLogging.log("closeRemoveThread", "thread removed with reason " + reason, true);

    return new MessageWall();
  }
}
