package com.wikia.webdriver.elements.oasis.components.comment;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticleComment extends BasePageObject {

  @FindBy(css = "#article-comm-submit")
  private WebElement commentSubmitButton;
  @FindBy(css = "#cke_contents_article-comm>iframe")
  private WebElement commentIFrame;
  @FindBy(css = "#article-comments-ul li:nth-child(1) .caption")
  private WebElement latestCommentCaption;

  public ArticleComment waitForVideo() {
    driver.switchTo().frame(commentIFrame);
    wait.forElementVisible(By.cssSelector("img"));

    return this;
  }

  public ArticleComment submitComment() {
    driver.switchTo().defaultContent();
    wait.forElementClickable(commentSubmitButton);
    scrollAndClick(commentSubmitButton);

    waitForElementNotVisibleByElement(commentSubmitButton, 30);
    PageObjectLogging.log("submitComment", "comment has been submitted", true);

    return this;
  }

  public boolean isVideoCaptionVisibleInTheLatestComment(String videoCaption) {
    return wait.forTextInElement(latestCommentCaption, videoCaption);
  }
}
