package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MessageWallThreadPageObject extends MessageWall {

  @FindBy(css = ".replyBody")
  private WebElement replyBody;
  @FindBy(css = ".replies .edited-by")
  private List<WebElement> lastReplyEditor;
  @FindBy(css = ".replies .msg-body")
  private List<WebElement> lastReplyText;
  @FindBy(className = "msg-body")
  private WebElement messageBody;

  public MiniEditorComponentObject triggerMessageArea() {
    while (!driver.findElement(firstMessageWrapperBy).findElement(replyButtonBy).isDisplayed()) {
      jsActions.focus(replyBody);
    }
    return new MiniEditorComponentObject(driver);
  }

  public void verifyLastReply(String userName, String message) {
    wait.forElementVisible(replyBody);
    Assertion.assertEquals(lastReplyEditor.get(lastReplyEditor.size() - 1).getText(), userName);
    Assertion.assertEquals(lastReplyText.get(lastReplyEditor.size() - 1).getText(), message);
  }

  public String getMessageContents() {
    return wait.forElementVisible(messageBody).getText();
  }
}
