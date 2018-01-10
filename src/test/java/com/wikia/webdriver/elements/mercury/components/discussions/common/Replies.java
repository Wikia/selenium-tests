package com.wikia.webdriver.elements.mercury.components.discussions.common;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class Replies extends BasePageObject {

  @FindBy(className = "replies-list")
  private WebElement webElement;

  @FindBy(css = ".discussion-reply .discussion-content")
  private WebElement replyContent;

  @FindBy(css = ".discussion-reply .og-container")
  private WebElement replyOpenGraph;

  public boolean hasNoRepliesIcon() {
    return null != webElement.findElement(By.cssSelector(".icon.no-replies"));
  }

  public boolean isEmpty() {
    WebElement element = webElement.findElement(By.cssSelector("div:first-of-type"));
    return element.getAttribute("class").contains("discussion-no-replies");
  }

  public String getNoRepliesMessage() {
    return webElement.findElement(By.className("discussion-no-replies")).getText();
  }

  public Replies waitForReplyToAppearWithText(final String text) {
    new FluentWait<>(driver)
        .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
        .until((Function<WikiaWebDriver, Boolean>) input -> wait.forElementVisible(replyContent)
          .getText()
          .contains(text));

    return this;
  }

  public Replies waitForReplyToAppearWithOpenGraph(final String url) {
    new FluentWait<>(driver)
        .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
        .until((Function<WikiaWebDriver, Boolean>) input -> wait.forElementVisible(replyOpenGraph)
            .getAttribute("href")
            .contains(url));
    return this;
  }

  public Reply getNewestReply() {
    return new Reply(webElement.findElement(By.className("post-reply")));
  }
}
