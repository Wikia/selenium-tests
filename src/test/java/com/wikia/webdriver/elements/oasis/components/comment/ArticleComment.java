package com.wikia.webdriver.elements.oasis.components.comment;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticleComment extends BasePageObject {

  @FindBy(css = "#article-comm-submit")
  private WebElement commentSubmitButton;
  @FindBy(css = ".cke_wysiwyg_frame")
  private WebElement commentIFrame;
  @FindBy(css = "#article-comments-ul li:nth-child(1) .caption")
  private WebElement latestCommentCaption;

  public boolean isVideoVisible() {
    driver.switchTo().frame(commentIFrame);
    try {
      wait.forElementVisible(By.cssSelector("img"));
      return true;
    } catch(TimeoutException e) {
      Log.info("Video element is not visible", e);
      return false;
    }
  }

  public String getLatestCommentCaption() {
    wait.forElementVisible(latestCommentCaption);

    return latestCommentCaption.getText();
  }
}
