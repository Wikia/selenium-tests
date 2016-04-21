package com.wikia.webdriver.elements.oasis.components.comment;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticleComment extends BasePageObject {

  @FindBy(css = "#article-comm-submit")
  private WebElement commentSubmitButton;
  @FindBy(css = "#cke_contents_article-comm>iframe")
  private WebElement commentIFrame;
  String videoInCommentsSelector = ".speech-bubble-message img[data-video-name*='%videoName%']";


  public ArticleComment waitForVideo() {
    driver.switchTo().frame(commentIFrame);
    wait.forElementVisible(By.cssSelector("img"));

    return this;
  }

  public ArticleComment submitComment() {
    driver.switchTo().defaultContent();
    scrollAndClick(commentSubmitButton);
    waitForElementNotVisibleByElement(commentSubmitButton, 30);
    PageObjectLogging.log("submitComment", "comment has been submitted", true);

    return this;
  }

  public boolean isVideoVisible(String videoName) {
    try {
      WebElement element =
          driver.findElement(
              By.cssSelector(videoInCommentsSelector.replace("%videoName%", videoName)));
      return element.isDisplayed();
    } catch (ElementNotFoundException e) {
      return false;
    }
  }
}
