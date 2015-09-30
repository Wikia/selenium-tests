/**
 *
 */
package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class MessageWallThreadPageObject extends MessageWall {

  @FindBy(css = ".replyBody")
  private WebElement replyBody;
  @FindBy(css = ".replies .edited-by")
  private List<WebElement> lastReplyEditor;
  @FindBy(css = ".replies .msg-body")
  private List<WebElement> lastReplyText;

  public MessageWallThreadPageObject(WebDriver driver) {
    super(driver);
  }

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

}
