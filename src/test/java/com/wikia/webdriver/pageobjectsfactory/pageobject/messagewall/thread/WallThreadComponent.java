package com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.thread;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Replies;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szymonczerwinski on 08.02.2017.
 */
public class WallThreadComponent extends WikiBasePageObject {
  @FindBy(css = ".cke_button_ModeSource > .cke_icon")
  private WebElement sourceModeButton;
  @FindBy(css = ".cke_toolbar_formatmini .cke_button_bold > .cke_icon")
  private WebElement boldButton;
  @FindBy(css = ".cke_toolbar_formatmini .cke_button_italic > .cke_icon")
  private WebElement italicButton;
  @FindBy(css = ".cke_toolbar_insert .RTEImageButton > .cke_icon")

  private String newMessageMenu =
      ".comments li.SpeechBubble.message.message-main:nth-child(1) .buttons";
  private String firstMessageMenu = ".comments li:nth-child(1) .buttons ";
  private String closeButtonString = ".close-thread";

  By messageTitleBy = By.cssSelector(".msg-title");
  By messageBodyBy = By.cssSelector(".msg-body");
  By imageBy = By.cssSelector(".thumbimage");


  public WallThreadComponent(WebDriver driver) {
    super();
  }

  public void reply(){

  }

  public List<Replies> getReplies(){
    List<Replies> repliesList = new ArrayList<Replies>();
    return repliesList;
  }
}
