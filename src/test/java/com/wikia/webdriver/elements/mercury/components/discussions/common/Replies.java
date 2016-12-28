package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Predicate;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public class Replies extends BasePageObject {

  @FindBy(className = "replies-list")
  private WebElement webElement;

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

  public Replies waitForReplyToAppearWith(final String text) {
    new FluentWait<>(driver)
        .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
        .until(new Predicate<WikiaWebDriver>() {
          @Override
          public boolean apply(@Nullable WikiaWebDriver input) {
            WebElement element = input.findElement(By.cssSelector(".discussion-reply .discussion-content"));
            return element.getText().contains(text);
          }
        });
    return this;
  }
}
