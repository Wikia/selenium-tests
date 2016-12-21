package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoreOptionsPopOver {

  private final PostEntity postEntity;

  private final WebElement post;

  public boolean hasReportPostOption() {
    return hasOption("report");
  }

  private boolean hasOption(String cssClassName) {
    boolean result = false;

    List<WebElement> options = post.findElements(By.tagName("svg"));
    for (WebElement option : options) {
      if (option.getAttribute("class").contains(cssClassName)) {
        result = true;
        break;
      }
    }

    return result;
  }

  public MoreOptionsPopOver clickLockPostOption() {
    clickReportLinkOption("Lock Post");
    waitFor(PostEntity::isLocked);
    return this;
  }

  private void clickReportLinkOption(String linkText) {
    List<WebElement> elements = post.findElements(By.className("report-link"));
    if (elements.size() > 1) {
      WebElement element = elements.get(1);
      if (linkText.equals(element.getText())) {
        elements.get(1).click();
      }
    }
  }

  private void waitFor(final Predicate<PostEntity> predicate) {
    new FluentWait<>(postEntity)
        .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
        .until(predicate);
  }

  public MoreOptionsPopOver clickReportPostOption() {
    post.findElement(By.className("report-link")).click();
    return this;
  }

  public ShareDialog clickSharePostOption() {
    post.findElement(By.className("share-link")).click();
    return new ShareDialog(post.findElement(By.cssSelector(".discussion-share-dialog .modal-dialog")));
  }

  public MoreOptionsPopOver clickUnlockPostOption() {
    clickReportLinkOption("Unlock Post");
    waitFor(Predicates.not(PostEntity::isLocked));
    return this;
  }

  public MoreOptionsPopOver clickViewAllPostsByOption() {
    post.findElement(By.cssSelector("a[href^='/d/u/']")).click();
    return this;
  }

  static MoreOptionsPopOver fromPostEntity(final PostEntity postEntity) {
    return new MoreOptionsPopOver(postEntity, postEntity.getPost());
  }
}
