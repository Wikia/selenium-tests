package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class MessageWallCloseRemoveThreadPageObject extends WikiBasePageObject {

  @FindBy(css = ".wall-action-reason")
  private WebElement removeReasonField;
  @FindBy(css = "#WikiaConfirmOk")
  private WebElement removeConfirmButton;

  public MessageWallCloseRemoveThreadPageObject(WebDriver driver) {
    super(driver);
  }

  public MessageWall closeRemoveThread(String reason) {
    removeReasonField.sendKeys(reason);
    removeConfirmButton.click();
    wait.forElementNotPresent(By.cssSelector(modalWrapper));
    LOG.success("closeRemoveThread", "thread removed with reason " + reason);
    return new MessageWall(driver);
  }
}
